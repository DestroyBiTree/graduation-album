package com.nwu.graduationalbum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Select("select name from result_2022_all where XH = #{number}")
    String getStudentName(String number);

}
