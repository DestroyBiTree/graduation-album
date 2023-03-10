package com.nwu.graduationalbum.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.graduationalbum.entity.Bullet;
import com.nwu.graduationalbum.entity.vo.BulletVo;
import com.nwu.graduationalbum.mapper.BulletMapper;
import com.nwu.graduationalbum.service.BulletService;
import com.nwu.graduationalbum.service.StudentService;
import com.nwu.graduationalbum.util.CASUtil;
import com.nwu.graduationalbum.util.Result;
import com.nwu.graduationalbum.util.SensitivewordFilter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.nwu.graduationalbum.util.Constants.*;

/**
 * @program: NwuGraduationAlbum
 * @description: 弹幕服务类
 * @author: TD.Miracle
 * @create: 2022-05-19 14:05
 **/
@Service
public class BulletServiceImpl extends ServiceImpl<BulletMapper, Bullet> implements BulletService {

    @Resource
    private StudentService studentService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int checkedPassBullet(int id, String checkTime, String passTime) {
        return baseMapper.checkedBullet(id, checkTime, passTime);
    }

    @Override
    public int checkedNotPass(int id, String checkTime) {
        return baseMapper.checkedNotPass(id, checkTime);
    }

    @Override
    public List<BulletVo> getBullets() {
        return baseMapper.getBullets();
    }

    @Override
    public Result addBullet(HttpServletRequest request, String content) {
        SensitivewordFilter filter = new SensitivewordFilter();
        //过滤敏感词
        boolean contain = filter.isContaintSensitiveWord(content, SENSITIVE_MATCH_RULE_MIN);
        if(contain){
            Set<String> set = filter.getSensitiveWord(content, SENSITIVE_MATCH_RULE_MIN);
            return Result.error().message("弹幕包含敏感词").data("sensitiveWords",set);
        }
        //添加弹幕
        String number = CASUtil.getAccountNameFromCas(request);
        if(StrUtil.isEmpty(number) || StrUtil.isEmpty(content)){
            return Result.error().message("弹幕信息获取失败");
        }
        // 设置弹幕信息
        Bullet bullet = new Bullet();
        bullet.setNumber(number);
        bullet.setName(studentService.getStudentName(number));
        bullet.setContent(content);
        bullet.setIsPass(0); // 首次提交均为未审核过e
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = format.format(date);
        bullet.setCreateTime(formatDate);
        int insert = baseMapper.insert(bullet);
        return insert > 0 ? Result.ok().message("弹幕发送成功"):Result.error().message("弹幕发送失败");
    }

    @Override
    public HashMap<String, Object> getBulletPage(HttpServletRequest request,int currentPage, int size) {
        HashMap<String, Object> map = new HashMap<>();
        //弹幕缓存
        String key = USER_BULLET;
        String bulletJson = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(bulletJson)){
            //命中，直接返回
            List<Map> bullets = JSONUtil.toList(bulletJson, Map.class);
            map.put("current",currentPage);
            map.put("total",bullets.size());
            map.put("bullets",bullets);
            return map;
        }
        //查询本人最新发布弹幕
        String number = CASUtil.getAccountNameFromCas(request);
        QueryWrapper<Bullet> wrapper = new QueryWrapper<>();
        wrapper.eq("number",number).orderByDesc("created_time").last("limit 1");
        Bullet bullet = baseMapper.selectOne(wrapper);
        BulletVo newestBulletVo = new BulletVo();
        boolean flag = false;
        if(bullet != null){
            flag = true;
            BeanUtil.copyProperties(bullet,newestBulletVo);
        }
        //未命中，查询弹幕库
        Page<Bullet> page = new Page<>(currentPage,size);
        QueryWrapper<Bullet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_check",1).eq("is_pass",1)
                        .orderByDesc("created_time").last("limit " + (currentPage - 1) * size + "," + size);
        Page<Bullet> bulletPage = this.page(page, queryWrapper);
        List<Bullet> records = bulletPage.getRecords();
        List<BulletVo> bullets = new ArrayList<>(records.size() + 1);
        if (flag) {
            bullets.add(newestBulletVo);
        }
        records.stream().forEach(s->{
            BulletVo bulletVo = new BulletVo();
            BeanUtil.copyProperties(s,bulletVo);
            bullets.add(bulletVo);
        });
        map.put("bullets",bullets);
        map.put("current",bulletPage.getCurrent());
        map.put("total",bullets.size());
        //写入缓存
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(bullets),USER_BULLET_TTL, TimeUnit.MINUTES);
        return map;
    }

    @Override
    public Result getUnchecked(int currentPage, int size) {
        Page<Bullet> page = new Page<>(currentPage,size);
        QueryWrapper<Bullet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_check",0).orderByAsc("created_time")
                .last("limit " + (currentPage - 1) * size + "," + size);
        Page<Bullet> bulletPage = this.page(page, queryWrapper);
        //查询当前未审核弹幕条数
        Integer unCheckCount = baseMapper.selectCount(new QueryWrapper<Bullet>().eq("is_check", 0));
        HashMap<String, Object> map = new HashMap<>();
        map.put("bullets", bulletPage.getRecords());
        map.put("current", bulletPage.getCurrent());
        if(unCheckCount != null){
            map.put("totalUnCheckCount", unCheckCount);
        }
        return Result.ok().data(map);
    }

    @Override
    public Result checkBullets(List<Integer> ids, int isPass) {
        String time = LocalDateTimeUtil.now().toString();
        int count;
        if(isPass == 1){//通过审核
            count = 0;
            for (Integer id : ids) {
                if(baseMapper.checkedBullet(id,time,time) > 0){
                    count ++;
                }
            }
        }else {//不通过审核
            count = 0;
            for (Integer id : ids) {
                if(baseMapper.checkedNotPass(id,time) > 0){
                    count ++;
                }
            }
        }
        return isPass == 1 ? Result.ok().message(count + "条弹幕已审核通过"):Result.ok().message(count + "条弹幕已审核未通过");
    }
}
