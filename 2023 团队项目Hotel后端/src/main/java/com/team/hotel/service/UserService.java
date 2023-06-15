package com.team.hotel.service;

import com.team.hotel.pojo.Admin;
import com.team.hotel.pojo.Payment;
import com.team.hotel.pojo.Type;
import com.team.hotel.pojo.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public interface UserService {
    User createUsers(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    User queryEmailAndPasswordExist(String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    User forgotPassword(String tel,String email);
    User updataPassword(String tel,String email,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    User getUserInfo(String email);

    User updataUserInfo(User user);
    User delUser(int id);
    List<User> getAllNotVipUsers();
    List<User> getAllVipUsers();
    List<User> getAllUsers();

    Map<String,Integer>getUserGenderNumber();
    User getUserInfoByUsername(String username);

    Map<String,Integer>getUserTypeNumber();
    List<Type> getTypeInfo(String email,String status);
    List<Payment> getUserPaymentInfo(String email,String status);

    void updatevip(String email);
}
