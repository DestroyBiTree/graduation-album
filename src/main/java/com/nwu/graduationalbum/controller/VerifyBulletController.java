package com.nwu.graduationalbum.controller;

import com.nwu.graduationalbum.service.BulletService;
import com.nwu.graduationalbum.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-06-14 13:39
 **/
@RestController
@RequestMapping("/verify/bullet")
public class VerifyBulletController {

    @Resource
    private BulletService bulletService;

    /**
     * 获取未审核的弹幕列表
     * @param currentPage
     * @return
     */
    @GetMapping("/getUnchecked")
    public Result getUncheckedBullet(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                     @RequestParam(value = "size",defaultValue = "10",required = false) int size){
        Result result = bulletService.getUnchecked(currentPage,size);
        return (Integer) result.getData().get("totalUnCheckCount") == 0 ? Result.ok().message("当前无未审核弹幕"):result;
    }

    /**
     * 弹幕审核
     * @param ids 弹幕id列表
     * @param isPass 是否通过，默认为不通过
     * @return
     */
    @PostMapping("/checkBullets")
    public Result checkBullet(@RequestParam("ids") List<Integer> ids,
                              @RequestParam(value = "isPass",defaultValue = "0") int isPass){
        return bulletService.checkBullets(ids,isPass);
    }
}
