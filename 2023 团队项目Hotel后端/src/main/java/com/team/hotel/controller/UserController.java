package com.team.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.hotel.commom.R;
import com.team.hotel.mapper.UserMapper;
import com.team.hotel.pojo.Payment;
import com.team.hotel.pojo.Type;
import com.team.hotel.pojo.User;
import com.team.hotel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.List;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;



    /**
     * 用户登陆
     * @param email
     * @param password
     * @return  登陆信息状态
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/login")
    public R<Object> loginUser( @RequestParam("email")String email, @RequestParam("password")String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user=userService.queryEmailAndPasswordExist(email,password);
        log.info("登陆得到用户信息：user:{}",user);
        if(user == null){
            return R.error("用户或密码错误");
        }
        log.info("已通过");
        return R.success(user);
    }

    /**
     * 用户注册
     * @param user
     * @return  注册信息状态
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/register")
    public R<Object> createUser(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User newUser=userService.createUsers(user);
        log.info("注册的新用户信息：newUser:{}",newUser);
        if(newUser == null){
            log.info("注册失败");
            return R.error("注册出现错误");
        }
        else{
            log.info("注册成功");
            return R.success(newUser);
        }

    }

    /**
     * 用户忘记密码，修改密码
     * @param tel
     * @param email
     * @param password
     * @return  修改信息状态
     */
    @PostMapping("/forgetPassword")
    public R<Object> forgetPassword(String tel,String email,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        log.info("tel:{},email:{},password{}",tel,email,password);
        User user=userService.forgotPassword(tel,email);
        if(user == null){
            log.info("无此用户");
            return R.error("绑定邮箱或手机号码错误");
        }
        log.info("找到用户账号");
        User newUser=userService.updataPassword(tel,email,password);
        return R.success("修改成功");
    }

    /**
     * 得到用户信息
     * @param email
     * @return  提取用户信息状态
     */
    @GetMapping("getuser")
    public R<User>getUser(@RequestParam("email")String email){
        log.info("email:{}",email);
        User user=userService.getUserInfo(email);
        if(user==null){
            return R.error("出现未知错误");
        }
        return R.success(user);
    }

    /**
     * 更新用户信息
     * @param user
     * @return 返回更新信息
     */
    @PostMapping("/updatauserinfo")
    public R<User> updataUserInfo(@RequestBody User user){
        User newUserInfo=userService.updataUserInfo(user);
        if(user  == null){
            return R.error("出现错误");
        }
        return R.success(newUserInfo);
    }

    /**
     * 删除用户账户
     * @param id
     * @return 删除用户成功信息
     */
    @DeleteMapping("/delUser")
    public R<Object> delUserInfo(@RequestParam("id")int id){
        userService.delUser(id);
        return R.success("删除用户成功");
    }

    /**
     * 获取用户性别数据分析
     * @return
     */
    @GetMapping("/getGenderNumber")
    public Map<String, Integer> getUserGenderNumber(){
        Map<String,Integer> map=userService.getUserGenderNumber();
        log.info("map;{}",map);
        return map;
    }

    /**
     * 获取vip用户和普通用户的数量
     * @return
     */
    @GetMapping("getUserTypeNumber")
    public Map<String,Integer>getUserTypeNumber(){
        Map<String,Integer>map=userService.getUserTypeNumber();
        log.info("得到的用户的map:{}",map);
        return map;
    }

    /**
     * 得到订的房间的照片和房间类型(payment状态为0)
     * @param email
     * @return 得到订的房间的照片和房间类型
     */
    @PostMapping("/getStatus0RoomTypeAndImg")
    public R<Object> getStatus0RoomTypeAndImg(@RequestParam("email") String email){
        log.info("email:{}",email);
        List<Type> types=userService.getTypeInfo(email,"0");
        log.info("types:{}",types);

        return R.success(types);
    }

    /**
     * 得到订的房间的照片和房间类型(payment状态为1)
     * @param email
     * @return 得到订的房间的照片和房间类型
     */
    @PostMapping("/getStatus1RoomTypeAndImg")
    public R<Object> getStatus1RoomTypeAndImg(@RequestParam("email") String email){
        log.info("email:{}",email);
        List<Type> types=userService.getTypeInfo(email,"1");
        log.info("types:{}",types);

        return R.success(types);
    }

    /**
     *  用户订单状态为0的所有payment
     * @param email
     * @return  用户订单状态为0的所有payment
     */
    @PostMapping("/getStatus0PaymentInfo")
    public R<Object> getPaymentInfo(@RequestParam("email")String email){
        log.info("userEmail:{}",email);
        List<Payment> payments=userService.getUserPaymentInfo(email,"0");
        log.info("payments:{}",payments);
        return R.success(payments);
    }

    /**
     * 用户订单状态为1的所有payment
     * @param email
     * @return  用户订单状态为1的所有payment
     */
    @PostMapping("/getStatus1PaymentInfo")
    public R<Object> getStatus1PaymentInfo(@RequestParam("email")String email){
        log.info("userEmail:{}",email);
        List<Payment> payments=userService.getUserPaymentInfo(email,"1");
        log.info("payments:{}",payments);
        return R.success(payments);
    }


    @GetMapping("/becomevip")
    public R<String>updataeUserVip(@RequestParam("email")String email){
        log.info("获取到的email数据：{}"+email);
        userService.updatevip(email);
//        User user=new User();
        return R.success("success");

    }


}
