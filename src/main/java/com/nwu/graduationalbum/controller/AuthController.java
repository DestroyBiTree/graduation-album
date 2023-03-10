package com.nwu.graduationalbum.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: NwuGraduationAlbum
 * @description: 企业号接入
 * @author: TD.Miracle
 * @create: 2022-06-27 10:52
 **/
@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Value("${NWU.accessTokenUrl}")
    private String accessTokenUrl;
    @Value("${NWU.appid}")
    private String appid;
    @Value("${NWU.appsecret}")
    private String appsecret;
    @Value("${NWU.redirectUrl}")
    private String redirectUrl;

    public String getToken() {
        String result = "";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(accessTokenUrl + "?appid=" + appid + "&appsecret=" + appsecret)
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ("".equals(result)) {
            log.error("获取西北大学 AccessToken 异常");
        } else {
            result = JSON.parseObject(result).getJSONObject("d").get("access_token").toString();

        }

        return result;
    }

    @GetMapping(path = "/login")
    public String getCode() {
        log.info("进入getCode函数内,当前跳转地址为：" + redirectUrl);
        return "redirect:https://app.nwu.edu.cn/uc/api/oauth/index?redirect=" + redirectUrl + "&appid=" + appid + "&state=STATE";
    }


    @GetMapping(path = "/callback")
    public String getUser(HttpServletRequest req, RedirectAttributes attributes) {
        String token = getToken();
        String code = req.getParameter("code");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request okRequest = new Request.Builder()
                .url("https://app.nwu.edu.cn/uc/api/oauth/user-by-code?code=" + code + "&access_token=" + token)
                .method("GET", null)
                .build();
        try {
            Response okResponse = client.newCall(okRequest).execute();
            String userCookie = okResponse.body().string();

            JSONObject data = JSON.parseObject(userCookie).getJSONObject("d");
            String name = data.get("realname").toString();
            String number = data.getJSONObject("role").get("number").toString();


            attributes.addAttribute("name", name);
            attributes.addAttribute("code", number);

            return "redirect:/student/index.html";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "用户登录异常";
    }
}
