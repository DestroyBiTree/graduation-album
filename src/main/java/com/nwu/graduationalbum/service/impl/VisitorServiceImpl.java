package com.nwu.graduationalbum.service.impl;

import cn.hutool.core.util.StrUtil;
import com.nwu.graduationalbum.service.ResultVoService;
import com.nwu.graduationalbum.service.VisitorService;
import com.nwu.graduationalbum.util.CommonUtils;
import com.nwu.graduationalbum.util.Constants;
import com.nwu.graduationalbum.util.Result;
import com.nwu.graduationalbum.util.TokenUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-23 20:42
 **/
@Service
public class VisitorServiceImpl implements VisitorService {

    @Resource
    private ResultVoService resultVoService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result getShareInfo(HttpServletRequest request,String shareToken) {
        String sharer = TokenUtil.getUserFromToken(shareToken);
        //获取调用者端口
        String vistorId = request.getRemoteHost() + ":" +request.getRemotePort();
        //统计UV,根据访客IP地址统计
        stringRedisTemplate.opsForHyperLogLog().add(Constants.STATISTIC_UV,vistorId);
        //统计每日PV
        String date = StrUtil.replace(CommonUtils.simpleDateFormat2.format(new Date()),"-","");
        String key = Constants.STATISTIC_DAILY_PV + date;
        stringRedisTemplate.opsForValue().increment(key);
        return resultVoService.getStudentInfoById(sharer);
    }
}
