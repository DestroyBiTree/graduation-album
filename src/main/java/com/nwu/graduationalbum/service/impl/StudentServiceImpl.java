package com.nwu.graduationalbum.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.graduationalbum.entity.vo.ResultVo;
import com.nwu.graduationalbum.mapper.StudentMapper;
import com.nwu.graduationalbum.service.ResultVoService;
import com.nwu.graduationalbum.service.StudentService;
import com.nwu.graduationalbum.util.CASUtil;
import com.nwu.graduationalbum.util.Result;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.nwu.graduationalbum.util.Constants.STUDENT_PREFIX;


@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ResultVoService resultVoService;

    @Override
    public String getStudentName(String number) {
        return studentMapper.getStudentName(number);
    }

    @Override
    public boolean verifyGraduate(HttpServletRequest request) {
        String number = CASUtil.getAccountNameFromCas(request);
        if(StrUtil.isBlank(number)){
            return false;
        }
        //读取缓存
        String key = STUDENT_PREFIX + number;
        String cache = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(cache)){
            //有缓存数据，是毕业生
            return true;
        }
        //查询是否有该学生毕业生数据
        QueryWrapper<ResultVo> wrapper = new QueryWrapper<>();
        wrapper.eq("XH",number);
        int count = resultVoService.count(wrapper);
        return count > 0;
    }
}
