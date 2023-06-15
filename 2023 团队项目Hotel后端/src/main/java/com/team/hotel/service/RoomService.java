package com.team.hotel.service;

import com.team.hotel.pojo.Book;
import com.team.hotel.pojo.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    Room deleteById(int id);
    Room updataRoomInfo(Room room);
    Room addRoomInfo(Room room);
    void setRoomStatusTo1(int id);
}
