package com.nwu.graduationalbum.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.graduationalbum.entity.vo.ResultVo;
import com.nwu.graduationalbum.mapper.ResultVoMapper;
import com.nwu.graduationalbum.service.ResultVoService;
import com.nwu.graduationalbum.util.CASUtil;
import com.nwu.graduationalbum.util.Result;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nwu.graduationalbum.util.Constants.*;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-20 14:32
 **/
@Service
public class ResultVoServiceImpl extends ServiceImpl<ResultVoMapper, ResultVo> implements ResultVoService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result getStudentInfo(HttpServletRequest request) {
        String number = CASUtil.getAccountNameFromCas(request);
        if(StrUtil.isBlank(number)){
            return Result.error().message("请先登录");
        }
        //读取缓存
        String key = STUDENT_PREFIX + number;
        String cache = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(cache)){
            ResultVo resultVo = JSONUtil.toBean(cache, ResultVo.class);
            return Result.ok().data("data",resultVo);
        }
        ResultVo resultVo = baseMapper.selectById(number);
        if(resultVo == null){
            return Result.error().message("非毕业生，请毕业再来~");
        }
        //写入缓存
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(resultVo),STUDENT_CACHE_TTL, TimeUnit.DAYS);
        return Result.ok().data("data",resultVo);
    }

    @Override
    public Result getStudentInfoById(String id) {
        if(StrUtil.isBlank(id)){
            return Result.error().message("请输入学号");
        }
        if(!id.matches(NUMBER_REGEX)){
            return Result.error().message("请输入正确学号");
        }
        //读取缓存
        String key = STUDENT_PREFIX + id;
        String cache = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(cache)){
            ResultVo resultVo = JSONUtil.toBean(cache, ResultVo.class);
            return Result.ok().data("data",resultVo);
        }
        ResultVo resultVo = baseMapper.selectById(id);
        if(resultVo == null){
            return Result.error().message("非毕业生，请毕业再来~");
        }
        //写入缓存
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(resultVo),STUDENT_CACHE_TTL, TimeUnit.DAYS);
        return Result.ok().data("data",resultVo);
    }
}
