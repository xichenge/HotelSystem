package com.team.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.hotel.mapper.*;
import com.team.hotel.pojo.*;
import com.team.hotel.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    PaymentMapper paymentMapper;

    @Autowired
    RoomMapper roomMapper;
    public User createUsers(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("email",user.getEmail());//根据email来查找是否以及有此email创建的账号，没有就允许创建新账户
        User newUser=userMapper.selectOne(wrapper);
        if( newUser == null){
            user.setIsvip("0");
            log.info("新建user:{}",user);
            userMapper.insert(user);
            return user;
        }else{
            return null;
        }

    }

    public User queryEmailAndPasswordExist(String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
//        wrapper.eq("email","pwd");
        wrapper.eq("email",email).eq("password",password);
        User user=userMapper.selectOne(wrapper);
        return user;
    }
    public User forgotPassword(String tel,String email){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("tel",tel).eq("email",email);
        User user=userMapper.selectOne(wrapper);
        return user;
    }
    public User updataPassword(String tel,String email,String newPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("email",email).eq("tel",tel);
        User user=userMapper.selectOne(wrapper);
        user.setPassword(newPassword);
        userMapper.updateById(user);
        return user;
    }

    @Override
    public User getUserInfo(String email) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("email",email);
        User user=userMapper.selectOne(wrapper);
        log.info("得到用户数据 ：user:{}",user);
        return user;
    }
    public User getUserInfoByUsername(String username) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("name",username);
        User user=userMapper.selectOne(wrapper);
        log.info("得到用户数据 ：user:{}",user);
        return user;
    }



    @Override
    public User updataUserInfo(User user){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("email",user.getEmail());
        User newUser=userMapper.selectOne(wrapper);
        newUser.setName(user.getName());
        newUser.setTel(user.getTel());
        newUser.setEmail(user.getEmail());
        newUser.setBirthday(user.getBirthday());
        newUser.setGender(user.getGender());
        userMapper.updateById(newUser);
        log.info("用户数据更新：newUser:{}",newUser);
        return newUser;
    }
    public User delUser(int id){
        userMapper.deleteById(id);
        log.info("用户数据已删除");
        return null;
    }

    @Override
    public List<User> getAllNotVipUsers() {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("isvip","0");
        List<User>users=userMapper.selectList(wrapper);
        log.info("users:"+users);
        return users;
    }
    @Override
    public List<User> getAllVipUsers() {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("isvip","1");
        List<User>users=userMapper.selectList(wrapper);
        log.info("users:"+users);
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("isvip",null);
        List<User>users=userMapper.selectList(wrapper);
        log.info("users:"+users);
        return users;
    }

    @Override
    public Map<String, Integer> getUserGenderNumber() {
        QueryWrapper<User>wrapper=new QueryWrapper<>();
        wrapper.eq("gender","男");
        Integer maleNumber= count(wrapper);//获取男性的数量
        QueryWrapper<User>wrapper1=new QueryWrapper<>();
        wrapper1.eq("gender","女");
        Integer femaleNumber=count(wrapper1);
        Map<String,Integer>map=new HashMap<>();
        map.put("male",maleNumber);
        map.put("female",femaleNumber);
        log.info("map:{}",map);
        return map;
    }

    @Override
    public Map<String, Integer> getUserTypeNumber() {
        QueryWrapper<User>wrapper=new QueryWrapper<>();
        wrapper.eq("isvip",1);
        Integer vip=count(wrapper);
        log.info("vip:{}",vip);
        QueryWrapper<User>wrapper2=new QueryWrapper<>();
        wrapper.eq("isvip",0);
        Integer user=count(wrapper2);
        Map<String,Integer>map=new HashMap<>();
        map.put("vip",vip);
        map.put("user",user);
        log.info("map:{}",map);
        return map;
    }


    public List<Type> getTypeInfo(String email,String status){
        log.info("seviceImlp: email:{}",email);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("email",email);

        int userid=userMapper.selectOne(userQueryWrapper).getId();


        QueryWrapper<Payment> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid).eq("status",status);

        List<Payment> payments =paymentMapper.selectList(wrapper);

        Iterator<Payment> iterator=payments.iterator();
//        List<Payment> payments1=new ArrayList<>();

        List<Type> types=new ArrayList<>();

        while(iterator.hasNext()){
            String roomid=iterator.next().getRoomid();
            QueryWrapper<Room> wrapper1=new QueryWrapper<>();
            wrapper.eq("roomid",roomid);
            Room room=roomMapper.selectOne(wrapper1);
            String typename=room.getRoomtype();

            Type type=new Type();

            QueryWrapper<Type> wrapper2=new QueryWrapper<>();
            wrapper2.eq("typename",typename);
            type=typeMapper.selectOne(wrapper2);
            types.add(type);
        }


        log.info("types:{}",types);
        return types;

    }

    public List<Payment> getUserPaymentInfo(String email,String status){
        log.info("email:{}",email);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("email",email);

        User user=userMapper.selectOne(wrapper);
        int userid=user.getId();

        QueryWrapper<Payment> paymentQueryWrapper=new QueryWrapper<>();
        paymentQueryWrapper.eq("userid",userid).eq("status",status);
        List<Payment> payments=paymentMapper.selectList(paymentQueryWrapper);

        log.info("最后得到的paymentList：{}",payments);
        return payments;

    }

    @Override
    public void updatevip(String email) {
        QueryWrapper<User>wrapper=new QueryWrapper<>();
        wrapper.eq("email",email);
        User newUser=userMapper.selectOne(wrapper);
        newUser.setIsvip("1");
        userMapper.updateById(newUser);
        log.info("newUser:{}",newUser);

    }

}
