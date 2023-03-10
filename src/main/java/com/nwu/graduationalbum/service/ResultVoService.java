package com.nwu.graduationalbum.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.graduationalbum.entity.vo.ResultVo;
import com.nwu.graduationalbum.util.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: NwuGraduationAlbum
 * @description:
 * @author: TD.Miracle
 * @create: 2022-05-20 14:32
 **/
public interface ResultVoService extends IService<ResultVo> {
    Result getStudentInfo(HttpServletRequest request);

    Result getStudentInfoById(String id);
}
