package com.nwu.graduationalbum.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-21 18:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDto {

    @JsonProperty("access_token")
    private String accessToken;     // token 值

    @JsonProperty("expires_in")
    private int expiresIn;          // 过期时间
}
