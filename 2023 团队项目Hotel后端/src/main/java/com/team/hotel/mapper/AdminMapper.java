package com.team.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.hotel.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from admin where id=#{id}")
    List<Admin> queryAllPerms(@Param("id") int id);
}
