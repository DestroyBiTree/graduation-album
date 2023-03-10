package com.nwu.graduationalbum.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.nwu.graduationalbum.entity.AccessTokenDto;
import com.nwu.graduationalbum.entity.TicketDto;
import com.nwu.graduationalbum.entity.vo.ShareInfoVo;
import com.nwu.graduationalbum.service.WXService;
import com.nwu.graduationalbum.util.CASUtil;
import com.nwu.graduationalbum.util.Result;
import com.nwu.graduationalbum.util.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.concurrent.TimeUnit;

import static com.nwu.graduationalbum.util.Constants.*;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-21 18:45
 **/
@Service
public class WXServiceImpl implements WXService {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.noncestr}")
    private String noncestr;

    @Value("${union.appURL}")
    private String appUrl;

    @Value("${wx.grant_type}")
    private String grantType;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result shareMine(HttpServletRequest request) {
        String number = CASUtil.getAccountNameFromCas(request);
        if(StrUtil.isBlank(number)){
            return Result.error().message("请先登录");
        }
        //读取缓存
        String key = TOKEN_PREFIX + number;
        String cacheToken = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(cacheToken)){
            // 有缓存，直接返回
            return Result.ok().data("shareToken",cacheToken);
        }
        //无缓存，创建缓存
        String shareToken = TokenUtil.createToken(number);//生成令牌
        stringRedisTemplate.opsForValue().set(key,shareToken,TOKEN_CACHE_TTL, TimeUnit.DAYS);
        return Result.ok().data("shareToken",shareToken);
    }

    @Override
    public Result getWxConfig() {
        //统计分享次数
        stringRedisTemplate.opsForValue().increment(SHARE_NUM);
        ShareInfoVo shareInfoVo = new ShareInfoVo();
        String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
        shareInfoVo.setAppId(appid);
        shareInfoVo.setTimeStamp(timeStamp);
        shareInfoVo.setNonceStr(noncestr);
        shareInfoVo.setSign(getSignature(timeStamp,"http://bys.nwu.edu.cn/student/index.html"));
        return Result.ok().data("shareInfo",shareInfoVo);
    }

    @Override
    public Result getVisitorWxConfig(String url) {
        //统计分享次数
        stringRedisTemplate.opsForValue().increment(SHARE_NUM);
        //创建微信配置信息
        ShareInfoVo shareInfoVo = new ShareInfoVo();
        String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
        shareInfoVo.setAppId(appid);
        shareInfoVo.setTimeStamp(timeStamp);
        shareInfoVo.setNonceStr(noncestr);
        shareInfoVo.setSign(getSignature(timeStamp,url));
        return Result.ok().data("shareInfo",shareInfoVo);
    }

    /**
     * 获取AccessToken
     * @return
     */
    public String getAccessToken() {
        String cacheToken = stringRedisTemplate.opsForValue().get(ACCESS_TOKEN);
        if(StrUtil.isNotBlank(cacheToken)){
            return cacheToken;
        }
        RestTemplate restTemplate = new RestTemplate();

        AccessTokenDto tokenDto = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=" +
                grantType + "&appid=" + appid + "&secret=" + secret, AccessTokenDto.class);
        stringRedisTemplate.opsForValue().set(ACCESS_TOKEN,tokenDto.getAccessToken(),ACCESS_TOKEN_TTL,TimeUnit.SECONDS);
        return tokenDto.getAccessToken();
    }

    /**
     * 获取ticket
     * @return
     */
    public String getTicket() {
        String ticket = stringRedisTemplate.opsForValue().get(JS_API_TICKET);
        if(StrUtil.isNotBlank(ticket)){
            return ticket;
        }
        RestTemplate restTemplate = new RestTemplate();

        TicketDto ticketDto = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                + getAccessToken() + "&type=jsapi",TicketDto.class);
//        System.out.println(ticketDto);
        stringRedisTemplate.opsForValue().set(JS_API_TICKET,ticketDto.getTicket(),ACCESS_TOKEN_TTL,TimeUnit.SECONDS);
        return ticketDto.getTicket();
    }

    /**
     * 获取签名
     * @param timestamp
     * @param url
     * @return
     */
    public String getSignature(String timestamp, String url) {
        String plainText = "jsapi_ticket=" + getTicket() + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        return DigestUtil.sha1Hex(plainText);
    }
}
