package com.nwu.graduationalbum.service;

import com.nwu.graduationalbum.util.Result;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

public interface WXService {

    /**
     * 通过链接和token方式，分享我的数据（VisitorController接收）
     * @return
     */
    public Result shareMine(HttpServletRequest request);

    /**
     * 微信分享功能前台微信配置获取
     * @return
     */
    public Result getWxConfig();

    /**
     * 访客微信分享功能前台微信配置获取
     * @return
     */
    public Result getVisitorWxConfig(String url);
}
