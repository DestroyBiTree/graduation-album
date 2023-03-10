package com.nwu.graduationalbum.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-19 14:32
 **/
public interface StudentService {
    /**
     * 获取用户的姓名
     * @param number 学号
     * @return 当前学号的姓名
     */
    String getStudentName(String number);

    boolean verifyGraduate(HttpServletRequest request);
}
