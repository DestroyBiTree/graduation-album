package com.nwu.graduationalbum.controller;

import cn.hutool.core.util.StrUtil;
import com.nwu.graduationalbum.service.VisitorService;
import com.nwu.graduationalbum.service.WXService;
import com.nwu.graduationalbum.util.CommonUtils;
import com.nwu.graduationalbum.util.Result;
import com.nwu.graduationalbum.util.TokenUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * @create: 2022-05-23 20:38
 **/
@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Resource
    private VisitorService visitorService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据分享token获取被分享者信息
     * @param shareToken
     * @return
     */
    @GetMapping("/share/info")
    public Result shareInfo(HttpServletRequest request, @RequestParam(value = "shareToken",required = true) String shareToken){
        return visitorService.getShareInfo(request,shareToken);
    }

    /**
     * 访客入口
     * @param response
     * @param shareToken
     * @throws IOException
     */
    @RequestMapping("/index")
    public void index(HttpServletResponse response,@RequestParam(value = "shareToken",required = true) String shareToken) throws IOException {
        //统计主页访问量
        String date = StrUtil.replace(CommonUtils.simpleDateFormat2.format(new Date()),"-","");
        String key = CLICK_NUM + date;
        stringRedisTemplate.opsForValue().increment(key);
        response.sendRedirect("/visitor/index.html"+"?shareToken="+shareToken);
    }

    @Resource
    private WXService wxService;

    /**
     * 访客微信分享接口
     * @param url
     * @return
     */
    @GetMapping("/wx/config")
    public Result getVisitorWxData(@RequestParam(value = "url",required = true) String url){
        return wxService.getVisitorWxConfig(url);
    }
}
