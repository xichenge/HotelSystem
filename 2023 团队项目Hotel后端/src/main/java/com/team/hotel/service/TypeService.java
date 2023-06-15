package com.team.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.hotel.pojo.Admin;
import com.team.hotel.pojo.Book;
import com.team.hotel.pojo.Type;

import java.util.List;

public interface TypeService {
    List<Type> getAllTypes();
    Type delRoomType(int id);
    Type alterTypeInfo(Type type);

}
