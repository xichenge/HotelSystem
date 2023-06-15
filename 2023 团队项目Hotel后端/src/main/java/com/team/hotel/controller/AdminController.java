package com.team.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.hotel.commom.R;
import com.team.hotel.pojo.Admin;
import com.team.hotel.pojo.User;
import com.team.hotel.service.AdminService;
import com.team.hotel.service.UserService;
import com.team.hotel.utils.MyMD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin")
/**
 * 新建用户时，检查是否以及有了此账号
 */
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;


    /**
     * 管理员的登陆
     * @param email
     * @param password
     * @return 登陆成功信息
     * http:localhost:8080/admin/login?email=xxx&password=xxx
     */
    @PostMapping("/login")
    public R<Object> loginAdmin(@RequestParam("email")String email, @RequestParam("password")String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
          Admin admin=adminService.queryEmailAndPasswordExist(email,password);
          log.info("admin:{}",admin);
          if(admin == null){
              return R.error("用户或密码错误");
          }
          return R.success(admin);
    }

    /**
     * 设置用户为vip用户
     * @param user
     * @return 设置成功信息
     */
    @PostMapping ("/setVip")
    public R<Object> setVipUser(@RequestBody User user){
        adminService.setVipUser(user);
        return R.success("修改为vip成功");
    }

    /**
     * 创建用户
     * @param user
     * @return 创建成功信息
     */
    @PostMapping("/createUser")
    public R<Object> createUser(@RequestBody User user){
        User newUser=adminService.createUser(user);
        User userInfo = userService.getUserInfo(user.getEmail());
        if(userInfo!=null){
            return R.success(userInfo);
        }
        return R.error("数据出错，请联系管理员");

//        return R.success();
    }

    /**
     * 管理员删除用户账户
     * @param id
     * @return 删除成功信息
     */
    @DeleteMapping("/delUser")
    public R<Object> delUserInfoById(@RequestParam("id")int id){
        adminService.delUserInfo(id);
        return R.success("删除用户成功");
    }
    /**
     * 得到所有非vip用户信息
     * @return 提取成功信息
     */
    @PostMapping("/getAllNotVipUser")
    public R<Object> getAllNotVipUsers(){
        List<User> users = userService.getAllNotVipUsers();
        log.info("not vip users:{}",users);
        return R.success(users);
    }

    /**
     * 得到所有vip用户信息
     * @return 提取成功信息
     */
    @PostMapping("/getAllVipUser")
    public R<Object> getAllVipUsers(){
        List<User> users = userService.getAllVipUsers();
        log.info("  vip users:{}",users);
        return R.success(users);
    }

    /**
     * 提取所有用户信息
     * @return 提取成功信息
     */
    @PostMapping("/getAllUser")
    public R<Object> getAllUsers(){
        List<User> users = userService.getAllUsers();
        log.info("all users:{}",users);
        return R.success(users);
    }

}

