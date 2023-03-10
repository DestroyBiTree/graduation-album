package com.nwu.graduationalbum.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: NwuGraduationAlbum
 * @description: 前端展示弹幕列表
 * @author: TD.Miracle
 * @create: 2022-05-19 14:05
 **/
@Data
public class BulletVo implements Serializable {

    private int id;             // 弹幕 id
    private String number;      // 发送弹幕的学生学号
    private String name;        // 发送弹幕的学生名称
    private String content;     // 弹幕内容

}
