package com.nwu.graduationalbum.config;

import com.nwu.graduationalbum.util.VistorStatisticInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-20 10:19
 **/
@Configuration
public class MVConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VistorStatisticInterceptor(stringRedisTemplate))
                .addPathPatterns("/bullet/**","/student/**","/wx/**").order(0);
    }
}
