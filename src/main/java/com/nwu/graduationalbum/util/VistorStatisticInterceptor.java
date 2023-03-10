package com.nwu.graduationalbum.util;

import cn.hutool.core.util.StrUtil;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-20 10:20
 **/
public class VistorStatisticInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public VistorStatisticInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String vistorId = CASUtil.getAccountNameFromCas(request);
        if(StrUtil.isBlank(vistorId)){
            return false;
        }
        //统计UV
        stringRedisTemplate.opsForHyperLogLog().add(Constants.STATISTIC_UV,vistorId);
        //统计每日PV
        String date = StrUtil.replace(CommonUtils.simpleDateFormat2.format(new Date()),"-","");
        String key = Constants.STATISTIC_DAILY_PV + date;
        stringRedisTemplate.opsForValue().increment(key);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
