package com.nwu.graduationalbum.controller;

import cn.hutool.core.util.StrUtil;
import com.nwu.graduationalbum.service.BulletService;
import com.nwu.graduationalbum.util.Result;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-19 14:05
 **/
@RestController
@RequestMapping("/bullet")
public class BulletController {

    @Resource
    private BulletService bulletService;

    /**
     * 添加弹幕
     * @param request
     * @param content
     * @return
     */
    @PostMapping("/add")
    public Result addBullet(HttpServletRequest request, @RequestParam(value = "content",required = true) String content){
        return bulletService.addBullet(request,content);
    }

    /**
     * 分页查询弹幕列表,其中第一条是用户最新插入的弹幕
     * @param currentPage
     * @param size
     * @return
     */
    @GetMapping("/get")
    public Result getBulletPage(HttpServletRequest request,@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                                @RequestParam(value = "size",defaultValue = "100",required = false) int size){
        HashMap<String,Object> map = new HashMap<>();
        map = bulletService.getBulletPage(request,currentPage, size);
        return Result.ok().data(map);
    }
}
