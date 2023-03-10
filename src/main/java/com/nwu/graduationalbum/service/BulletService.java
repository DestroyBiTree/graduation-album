package com.nwu.graduationalbum.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.graduationalbum.entity.Bullet;
import com.nwu.graduationalbum.entity.vo.BulletVo;
import com.nwu.graduationalbum.util.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @program: NwuGraduationAlbum
 * @description: 弹幕服务类
 * @author: TD.Miracle
 * @create: 2022-05-19 14:05
 **/
public interface BulletService extends IService<Bullet> {

    /**
     * 设置某条留言已通过
     * @param id 留言 id
     * @param passTime 通过的时间
     * @return 通过条数
     */
    int checkedPassBullet(int id, String checkTime, String passTime);

    /**
     *
     * @param id 留言 id
     * @param checkTime 审核的时间
     * @return 审核未通过条数
     */
    int checkedNotPass(int id, String checkTime);

    /**
     * 获取审核通过的留言列表，300条
     * @return 列表
     */
    List<BulletVo> getBullets();

    Result addBullet(HttpServletRequest request, String content);

    HashMap<String, Object> getBulletPage(HttpServletRequest request,int currentPage, int size);

    Result getUnchecked(int currentPage, int size);

    Result checkBullets(List<Integer> ids, int isPass);
}
