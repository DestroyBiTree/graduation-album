package com.nwu.graduationalbum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-13 16:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulletDto {

    //弹幕内容
    private String content;
    //是否包含敏感词
    private Boolean isContain;
    //敏感词集合
    private Set<String> sensitiveWords;

}
