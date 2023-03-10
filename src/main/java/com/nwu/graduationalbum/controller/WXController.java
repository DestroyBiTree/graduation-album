package com.nwu.graduationalbum.controller;

import com.nwu.graduationalbum.service.WXService;
import com.nwu.graduationalbum.util.Result;
import com.nwu.graduationalbum.util.TokenUtil;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-21 18:44
 **/
@RestController
@RequestMapping("/wx")
public class WXController {

    @Resource
    private WXService wxService;

    /**
     * 生成微信分享配置信息
     * @return
     */
    @GetMapping("/config")
    public Result getWxData(){
        return wxService.getWxConfig();
    }

    /**
     * 生成本人分享码
     * @param request
     * @return
     */
    @GetMapping("/share")
    public Result shareMine(HttpServletRequest request){
        return wxService.shareMine(request);
    }

}
