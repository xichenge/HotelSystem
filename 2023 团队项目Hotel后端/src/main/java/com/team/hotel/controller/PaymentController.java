package com.team.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.hotel.commom.R;
import com.team.hotel.mapper.*;
import com.team.hotel.pojo.*;
import com.team.hotel.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.team.hotel.utils.GetMoneyUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    PaymentMapper paymentMapper;

    @Autowired
    UserMapper userMapper;
    /**
     * 获取所有订单数据
     * @return 所有订单数据
     */
    @PostMapping("/getAllOrder")
    public R<Object> getALlPayment(){
        List<Payment> payments = paymentService.getAllPayments();
        log.info("得到所有订单信息：payments：{}", payments);
        return R.success(payments);
    }

    /**
     * 添加订单
     * @param payment
     * @return 添加成功信息
     */
//    @PostMapping("/addOrderType")
//    public R<Object> addPaymentType(@RequestBody Payment payment){
//        log.info("要添加的订单数据：payment： {}" , payment);
//        paymentService.addPaymentInfo(payment);
//        return R.success("添加成功");
//    }

    /**
     * 删除订单
     * @param id
     * @return 删除成功信息
     */
    @DeleteMapping("/delOrder")
    public R<Object> delPaymentById(@RequestParam("id")int id){
        log.info("id:{}",id);
        paymentService.deleteById(id);
        return R.success("删除成功");
    }

    /**
     * 修改payment
     * @param payment
     * @return 返回修改的数据
     */
    @PostMapping("/alterPayment")
    public R<Object> alterPayment(@RequestBody Payment payment) throws Exception {
        log.info("payment:{}",payment);
        paymentService.alterPayment(payment);
        return R.success(payment);
    }

    /**
     * 根据用户需求生产订单
     * @param roomtype
     * @param idcard
     * @param tel
     * @param bookstart
     * @param bookend
     * @param useremail
     * @param bookname
     * @return 生成的订单信息
     */
    @PostMapping("/produceBook")
    public R<Object> producePayment(@RequestParam(value = "roomtype",required = false)String roomtype,
                                    @RequestParam(value = "idcard",required = false)String idcard,
                                    @RequestParam(value = "tel",required = false)String tel,
                                    @RequestParam(value = "bookstart",required = false)String bookstart,
                                    @RequestParam(value = "bookend",required = false)String bookend,
                                    @RequestParam(value = "useremail",required = false)String useremail,
                                    @RequestParam(value = "bookname",required = false)String bookname) throws Exception {
        log.info("from the begin :roomtype:{}",roomtype);
        Book book=new Book();
        book.setRoomtype(roomtype);
        book.setIdcard(idcard);
        book.setTel(tel);
        book.setBookstart(bookstart);
        book.setBookend(bookend);
        book.setUseremail(useremail);
        book.setBookname(bookname);
        book.setStatus("0");

        QueryWrapper<Type> wrapper=new QueryWrapper<>();
        wrapper.eq("typename",roomtype);
        Type type=typeMapper.selectOne(wrapper);

        book.setRoomprice(type.getPrice());

        double price=GetMoneyUtil.getMoney(bookstart,bookend,type.getPrice());

        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("email",useremail);
        User user=userMapper.selectOne(userQueryWrapper);
        if(user.getIsvip().equals("1"))
            price=price*0.8;

        book.setExpense(price);

        log.info("expense:{}",GetMoneyUtil.getMoney(bookstart,bookend,type.getPrice()));

        List<Room> rooms=new ArrayList<>();
        QueryWrapper<Room> wrapper1=new QueryWrapper<>();
        wrapper1.eq("roomtype",roomtype);

        rooms=roomMapper.selectList(wrapper1);

        log.info("得到的所有类型房间：rooms:{}",rooms);

        int flag=0;
        Iterator<Room> iterator=rooms.iterator();
        while(iterator.hasNext() && flag == 0){
            Room room=iterator.next();
            if(room.getRoomstate() == 0){
                book.setRoomid(room.getRoomid());
                flag=1;
            }

        }
        log.info("最后生成的订单：book:{}",book);
        bookMapper.insert(book);
        return R.success(book);
    }

    /**
     *  得到用户所有未支付的订单
     * @return 用户的所有未支付的订单
     */
    @PostMapping("/getUnpaidPayments")
    public R<Object> getUnpaidBooks(@RequestParam("email") String email){
        log.info("payment:  email:{}",email);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("email",email);
        int userid=userMapper.selectOne(userQueryWrapper).getId();

        QueryWrapper<Payment> wrapper=new QueryWrapper<>();
        wrapper.eq("status","0").eq("userid",userid);
        List<Payment> payments= paymentMapper.selectList(wrapper);
        log.info("从数据库得到的payments：{}",payments);

        return R.success(payments);
    }

    /**
     * 得到用户所有已支付的订单
     * @return  用户所有已支付的订单
     */
    @PostMapping("/getpaidPayments")
    public R<Object> getpaidBooks(@RequestParam("email")String email) {
        log.info("payment:  email:{}",email);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("email",email);
        int userid=userMapper.selectOne(userQueryWrapper).getId();

        QueryWrapper<Payment> wrapper = new QueryWrapper<>();
        wrapper.eq("status", "1").eq("userid",userid);
        List<Payment> payments = paymentMapper.selectList(wrapper);
        log.info("从数据库得到的payments：{}", payments);

        return R.success(payments);
    }

    /**
     * 设置payment状态为1（已支付）
     * @param id
     * @return 设置状态后的payment数据
     */
    @PostMapping("/changePaymentsStatusTo1")
    public R<Object> changePaymentsStatus(@RequestParam("id")int id){
        QueryWrapper<Payment> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        Payment payment=paymentMapper.selectOne(wrapper);

        payment.setStatus("1");
        paymentMapper.updateById(payment);
        return R.success(payment);
    }

    /**
     * 检查订单是否过期
     * @return 检查完成后的所有订单
     */
    @PostMapping("/checkTheStatusByRoomEndTime")
    public R<Object> checkTheStatusByRoomEndTime(){
        log.info("开始检查订单是否过期");
        List<Payment> payments=paymentService.checkThePaymentByEndTime();
        return R.success(payments);
    }
}
