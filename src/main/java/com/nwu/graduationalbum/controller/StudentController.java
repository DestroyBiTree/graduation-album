package com.nwu.graduationalbum.controller;

import cn.hutool.core.util.StrUtil;
import com.nwu.graduationalbum.service.ResultVoService;
import com.nwu.graduationalbum.service.StudentService;
import com.nwu.graduationalbum.util.CASUtil;
import com.nwu.graduationalbum.util.CommonUtils;
import com.nwu.graduationalbum.util.Constants;
import com.nwu.graduationalbum.util.Result;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.nwu.graduationalbum.util.Constants.CLICK_NUM;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-20 14:52
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private ResultVoService resultVoService;

    @Resource
    private StudentService studentService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * CAS登陆后获取学生信息
     * @param request
     * @return
     */
    @GetMapping("/info")
    public Result getInfoByCAS(HttpServletRequest request){
        return resultVoService.getStudentInfo(request);
    }

    /**
     * 学生入口
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isGraduate = studentService.verifyGraduate(request);
        //统计主页访问量
        String date = StrUtil.replace(CommonUtils.simpleDateFormat2.format(new Date()),"-","");
        String key = CLICK_NUM + date;
        stringRedisTemplate.opsForValue().increment(key);
        if(isGraduate){
            response.sendRedirect("/student/index.html");
        }else {
            response.sendRedirect("/nonGraduate/index.html");
        }
    }


    /**
     * 获取当前登录者基本信息
     * @param request
     * @return
     */
    @PostMapping("/verify/login")
    public Result verifyLogin(HttpServletRequest request){
        String number = CASUtil.getAccountNameFromCas(request);
        if(StrUtil.isBlank(number)){
            return Result.error().message("请先登录");
        }
        return Result.ok().message("登录验证通过").data("number",number);
    }
}
