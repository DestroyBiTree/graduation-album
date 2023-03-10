package com.nwu.graduationalbum.service;

import com.nwu.graduationalbum.util.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-23 20:41
 **/
public interface VisitorService {
    Result getShareInfo(HttpServletRequest request,String shareToken);
}
