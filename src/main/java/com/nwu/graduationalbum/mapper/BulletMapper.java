package com.nwu.graduationalbum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.graduationalbum.entity.Bullet;
import com.nwu.graduationalbum.entity.vo.BulletVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BulletMapper extends BaseMapper<Bullet> {

    @Update("UPDATE def_bullet2022 SET is_pass = 1, is_check = 1, check_time = #{checkTime}, pass_time = #{passTime} where id = #{id}")
    int checkedBullet(int id, String checkTime, String passTime);

    @Update("UPDATE def_bullet2022 SET is_pass = 0, is_check = 1, check_time = #{checkTime} where id = #{id}")
    int checkedNotPass(int id, String checkTime);

    List<BulletVo> getBullets();
}
