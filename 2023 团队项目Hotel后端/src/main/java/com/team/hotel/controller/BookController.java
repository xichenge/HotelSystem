package com.team.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.hotel.commom.R;
import com.team.hotel.dto.TopFive;
import com.team.hotel.dto.TopFiveDetails;
import com.team.hotel.mapper.BookMapper;
import com.team.hotel.mapper.PaymentMapper;
import com.team.hotel.mapper.UserMapper;
import com.team.hotel.pojo.Book;
import com.team.hotel.pojo.User;
import com.team.hotel.pojo.Payment;
import com.team.hotel.service.BookService;
import com.team.hotel.service.RoomService;
import javafx.scene.input.DataFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")



public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PaymentMapper paymentMapper;

    @Autowired
    RoomService roomService;
    /**
     * 得到所有的book信息
     * @return 所有book信息
     */
    @PostMapping("/getAllBookInfo")
    public R<Object> getAllBookInfo(){
        List<Book> books=bookService.getAllBooks();
        log.info("所有的book信息 : books:{}",books);
        return R.success(books);
    }



    /**
     *添加book信息
     * @param book
     * @return 返回添加成功信息
     */
//    @PostMapping("/addBookInfo")
//    public R<Object> addBookInfo(@RequestBody Book book){
//        Book newBook=bookService.addBookInfo(book);
//
//        if(newBook == null){
//            return R.error("已有此房间订单");
//        }else{
//            return R.success("添加book信息成功");
//        }
//    }

    /**
     * 删除订单
     * @param id
     * @return 返回成功信息
     */
    @DeleteMapping("/delBookInfo")
    public R<Object> delBookInfo(@RequestParam("id")int id){
        bookService.deleteById(id);
        return R.success("删除成功");
    }

    /**
     * 更新book数据
     * @param book
     * @return 更新是否成功信息
     */
    @PostMapping("/updataBookInfo")
    public R<Object>  updataBookInfo(@RequestBody Book book){
        bookService.updataBookInfo(book);
        return R.success("更新成功");
    }

    /**
     * 设置状态同意订单（为1）
     * @param id
     * @return 同意订单信息
     */
    @GetMapping("/agree")
    public R<Object> agressBook(@RequestParam("id")Integer id){
        log.info("正在设置同意订单");
        Book book =bookService.updateBookStatus("1",id);
        log.info("book:{}",book);
        Payment payment=new Payment();


        QueryWrapper<User> wrapper1=new QueryWrapper<>();
        wrapper1.eq("email",book.getUseremail());
        User user=userMapper.selectOne(wrapper1);

        log.info("user:{}",user);

        payment.setUserid(user.getId().toString());
        log.info("id.toString:{}",user.getId().toString());
        payment.setStatus("0");
        payment.setUsername(user.getName());
        payment.setRoomid(book.getRoomid().toString());

        Date now=new Date(System.currentTimeMillis());
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String nowtime=dft.format(now);

        payment.setOrdertime(nowtime);
        payment.setTel(book.getTel());
        payment.setStart(book.getBookstart());
        payment.setEnd(book.getBookend());
        payment.setFee(book.getExpense());

        log.info("payment:{}",payment);

        paymentMapper.insert(payment);

        int roomid= Integer.parseInt(payment.getRoomid());
        roomService.setRoomStatusTo1(roomid);

        return R.success(payment);
    }

    /**
     * 设置状态拒绝订单（为-1）
     * @param id
     * @return 拒绝订单信息
     */
    @GetMapping("/refuse")
    public R<String> refuseBook(@RequestParam("id")Integer id){
        log.info("正在设置拒绝订单");
        bookService.updateBookStatus("-1",id);
        return R.success("不同意请求");
    }

    /**
     * 得到订单最多的五种房间类型
     * @return 得到的房间类型和对应的订单数量
     */
    @GetMapping("getTop5")
    public R<Object> getTopFive(){
        List<TopFiveDetails> getTopfive=bookService.getTopFive();
        log.info("controller层得到的top5：gettTopFive:{}",getTopfive);
        return R.success(getTopfive);
    }


}
