package com.team.hotel.service;

import com.team.hotel.mapper.RoomCommentMapper;
import com.team.hotel.pojo.Room;
import com.team.hotel.pojo.RoomComment;

import java.util.List;

public interface RoomCommentService {
    List<RoomComment> getAllRoomComment();
    RoomComment deleteById(int id);
    RoomComment updataRoomCommentInfo(RoomComment roomComment);
    RoomComment addRoomCommentInfo(RoomComment roomComment);
    List<RoomComment> getAllRoomCommentByRoomid(int roomid);
    List<RoomComment> getRoomCommentByRoomtype(String typename);
}
