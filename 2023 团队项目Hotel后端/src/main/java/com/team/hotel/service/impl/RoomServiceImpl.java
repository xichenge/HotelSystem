package com.team.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.hotel.mapper.RoomMapper;
import com.team.hotel.mapper.UserMapper;
import com.team.hotel.pojo.Room;
import com.team.hotel.pojo.User;
import com.team.hotel.service.RoomService;
import com.team.hotel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    RoomMapper roomMapper;
    public List<Room> getAllRooms(){
        List<Room> rooms=roomMapper.selectList(null);
        return rooms;
    }
    public Room deleteById(int id){
        roomMapper.deleteById(id);
        return null;
    }
    public Room updataRoomInfo(Room room){
        log.info("更新的room信息：room:{}",room);
        QueryWrapper<Room> wrapper=new QueryWrapper<>();
        wrapper.eq("id",room.getId());
        Room newRoom=roomMapper.selectOne(wrapper);
        newRoom.setRoomtype(room.getRoomtype());
        newRoom.setArea(room.getArea());
        newRoom.setNumber(room.getNumber());
        newRoom.setRoomprice(room.getRoomprice());
        newRoom.setRoomstate(room.getRoomstate());
        newRoom.setPicture(room.getPicture());
        newRoom.setRoomcreateName(room.getRoomcreateName());
        roomMapper.updateById(newRoom);
        return null;

    }
    public Room addRoomInfo(Room room){
       room.setRoomstate(0);
        log.info("room:{}",room);

        roomMapper.insert(room);
        return null;
    }
    public void setRoomStatusTo1(int id){
        QueryWrapper<Room> wrapper=new QueryWrapper<>();
        wrapper.eq("roomid",id);
        Room room=roomMapper.selectOne(wrapper);


        log.info("room{}",room);
        room.setRoomstate(1);
        roomMapper.updateById(room);
    }

}
