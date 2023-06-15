package com.team.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.hotel.commom.R;
import com.team.hotel.mapper.RoomMapper;
import com.team.hotel.pojo.Room;
import com.team.hotel.pojo.Type;
import com.team.hotel.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    /**
     * 得到所有房间的信息
     * @return 所有房间信息
     */
    @PostMapping("/getAllRooms")
    public R<Object> getAllTypes(){
        List<Room> rooms=roomService.getAllRooms();
        log.info("得到所有房间信息：rooms:{}",rooms);
//        log.info("types:"+types);
        return R.success(rooms);
    }

    /**
     * 新增房间
     * @param room
     * @return 新增房间的数据
     */
    @PostMapping("/addRoom")
    public R<Object> addRoom(@RequestBody Room room){
        log.info("新增房间 room:{}",room
        );

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        Date date = new Date();
        String str = format.format(date);
        room.setRoomcreateTime(str);

        roomService.addRoomInfo(room);

        log.info("添加成功");
        return R.success(room);
    }

    /**
     * 删除房间信息
     * @param id
     * @return 删除成功信息
     */
    @DeleteMapping("/delRoomType")
    public R<Object> delRoomType(@RequestParam("id")int id){
        roomService.deleteById(id);
        log.info("删除成功");
        return R.success("删除房间信息成功");
    }

    /**
     * 更新信息
     * @param room
     * @return 更新后的房间数据
     */
    @PostMapping("/updataTypeInfo")
    public R<Object> updataTypeInfo(@RequestBody Room room){
        log.info("更新信息为：room:{}",room);
        roomService.updataRoomInfo(room);
        log.info("更新成功");
        return R.success(room);
    }
}
