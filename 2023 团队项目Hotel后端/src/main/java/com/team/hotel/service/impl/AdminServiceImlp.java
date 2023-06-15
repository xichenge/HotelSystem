package com.team.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.hotel.mapper.AdminMapper;
import com.team.hotel.mapper.UserMapper;
import com.team.hotel.pojo.Admin;
import com.team.hotel.pojo.User;
import com.team.hotel.service.AdminService;
import com.team.hotel.utils.MyMD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.team.hotel.mapper.AdminMapper;

@Slf4j
@Service
public class AdminServiceImlp extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    UserMapper userMapper;

    public Admin queryEmailAndPasswordExist(String email,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
       log.info("email:{},password:{}",email,password);
        QueryWrapper<Admin> wrapper=new QueryWrapper<>();
//        wrapper.eq("email","pwd");
        wrapper.eq("email",email).eq("password",password);
        Admin ad=adminMapper.selectOne(wrapper);
        if(ad==null){
            log.info("找不到");
            return null;
        }
        log.info("找到管理员账号");
        return ad;
    }

    public User setVipUser(User user){
        user.setIsvip("1");
        log.info("设置VIP后的用户信息：user:{}",user);
        userMapper.updateById(user);
        return null;
    }

    public  User createUser(User user){
        log.info("创建用户信息：user:{}",user);
        userMapper.insert(user);
        return null;
    }

    public Admin delUserInfo(int id){
        userMapper.deleteById(id);
        log.info("用户数据已删除");
        return null;
    }

}
