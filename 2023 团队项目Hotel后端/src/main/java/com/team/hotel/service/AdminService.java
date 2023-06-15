package com.team.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.hotel.pojo.Admin;
import com.team.hotel.pojo.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface AdminService extends IService<Admin> {

    Admin queryEmailAndPasswordExist(String email,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
//    Admin loginAdmin(Admin admin);
    User setVipUser(User user);
    User createUser(User user);
    Admin delUserInfo(int id);
}
