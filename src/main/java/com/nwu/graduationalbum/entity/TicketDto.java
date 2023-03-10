package com.nwu.graduationalbum.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-26 09:04
 **/
@Data
public class TicketDto {

    @JsonProperty("err_code")
    private int errCode;            // 错误代码

    @JsonProperty("err_message")
    private String errMessage;      // 错误信息

    @JsonProperty("ticket")
    private String ticket;          // ticket 值

    @JsonProperty("expires_in")
    private int expiresIn;          // 过期时间
}
