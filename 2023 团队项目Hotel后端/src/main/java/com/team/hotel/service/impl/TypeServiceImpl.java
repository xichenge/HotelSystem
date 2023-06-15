package com.team.hotel.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.hotel.mapper.AdminMapper;
import com.team.hotel.mapper.TypeMapper;
import com.team.hotel.pojo.Admin;
import com.team.hotel.pojo.Type;
import com.team.hotel.service.AdminService;
import com.team.hotel.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    public List<Type> getAllTypes(){
        List<Type> types=typeMapper.selectList(null);
        log.info("sql层的获取types："+types);
        return types;
    }

    public Type delRoomType(int id){
        typeMapper.deleteById(id);
        log.info("删除房间类型成功");
        return null;
    }

    public Type alterTypeInfo(Type type){
        typeMapper.updateById(type);
        log.info("要修改后的类型信息：type:{}",type);
        return null;
    }
}
