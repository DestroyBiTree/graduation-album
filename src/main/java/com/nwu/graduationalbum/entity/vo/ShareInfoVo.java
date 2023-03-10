package com.nwu.graduationalbum.entity.vo;

import lombok.Data;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-26 10:05
 **/
@Data
public class ShareInfoVo {
    private String appId;
    private String nonceStr;
    private String timeStamp;
    private String sign;
}
