package com.team.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.team.hotel.pojo.Room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper extends BaseMapper<Room> {
}
