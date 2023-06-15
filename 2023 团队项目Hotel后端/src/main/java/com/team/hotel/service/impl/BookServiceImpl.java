package com.team.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.hotel.dto.TopFiveDetails;
import com.team.hotel.mapper.BookMapper;
import com.team.hotel.pojo.Book;
import com.team.hotel.dto.TopFive;
import com.team.hotel.pojo.User;
import com.team.hotel.service.BookService;
import com.team.hotel.service.UserService;
import com.team.hotel.utils.GetMoneyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.OSEnvironment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Autowired
    UserService userService;
    public List<Book> getAllBooks(){
        QueryWrapper<Book> wrapper=new QueryWrapper<>();
        wrapper.eq("status","0");
        List<Book> books=bookMapper.selectList(wrapper);
        log.info("sql层的获取books：{}",books);
        return books;
    }
    public Book deleteById(int id){
        bookMapper.deleteById(id);
        return null;
    }
    public Book updataBookInfo(Book book){
        log.info("初始更新的book：{}",book);
        double price=GetMoneyUtil.getMoney(book.getBookstart(),book.getBookend(),book.getRoomprice());

        User user=userService.getUserInfoByUsername(book.getBookname());

        if(user.getIsvip() == "1")
            price=price*0.8;//vip用户打八折

        book.setExpense(price);

        bookMapper.updateById(book);
        log.info("设置完price后的数据为：book:{}",book);
        return null;
    }
    public Book addBookInfo(Book book) throws ParseException {
        QueryWrapper<Book> wrapper=new QueryWrapper<>();
        wrapper.eq("roomid",book.getRoomid());//如果房间没有被预定，就可以新增订单
        Book newBook=bookMapper.selectOne(wrapper);
        if(newBook == null){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            String str = format.format(date);
            book.setBookstart(str);


            DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");

            Date star = dft.parse(str);//开始时间
            Date endDay=dft.parse(book.getBookend());//结束时间
            Long starTime=star.getTime();
            Long endTime=endDay.getTime();
            Long num=endTime-starTime;//时间戳相差的毫秒数
            Long dayNum=num/24/60/60/1000;//除以一天的毫秒数
            double totalPrice=dayNum*book.getRoomprice();
            User user=userService.getUserInfoByUsername(book.getBookname());
            if(user.getIsvip() == "1")
                totalPrice=totalPrice*0.8;//vip用户打八折
            log.info("totalPrice:{}",totalPrice);
            book.setExpense(totalPrice);
            book.setStatus("0");
            bookMapper.insert(book);
            return book;
        }
        return null;
    }

    @Override
    public Book updateBookStatus(String status,Integer id) {
       QueryWrapper<Book> wrapper=new QueryWrapper<>();
       wrapper.eq("id",id);
       Book newBook=bookMapper.selectOne(wrapper);
       newBook.setStatus(status);
       bookMapper.updateById(newBook);
       return newBook;
    }

    public List<TopFiveDetails> getTopFive(){
        List<String> sevenDays= GetMoneyUtil.getSevenDate();
        log.info("latest seven days : {}",sevenDays);
//        List<Book> books=bookMapper.selectList(null);//得到所有的预定

        List<TopFive> topFive=bookMapper.getTopFive();//自定义sql语句，得到前5多的预定，可能有错



        log.info("topFive:{}",topFive);//算出次数
        int nums[][] = new int[5][8];//存前五多预定房间类型在前七个日期被预定的次数

        for(int i=0;i<5;i++)//
            for(int j=0;j<7;j++)
            {
                if(i==3){

                    System.out.println("参数"+getDateBookNum(topFive.get(i).getName(),sevenDays.get(j)));
                }
                nums[i][7-j]=getDateBookNum(topFive.get(i).getName(),sevenDays.get(j));//调用函数得到特定日期特定房间类型被预定的次数
            }

        for(int i=0;i<5;i++)
        log.info("nums:{}",nums[i]);



        List<TopFiveDetails> topFiveDetails=new ArrayList<>();
        for(int i=0;i<5;i++)
        {
            TopFiveDetails newT=new TopFiveDetails();//最终要传的值
            newT.setTypename(topFive.get(i).getName());//设置房间类型名
            newT.setNum(nums[i]);//设置每个日期被预定的次数
            newT.setSevendays(sevenDays);//设置前七个日期
            topFiveDetails.add(newT);//放入list中
        }
//        System.out.println("top:"+topFiveDetails)
        log.info("topFiveDetails:{}",topFiveDetails);
        return topFiveDetails;
    }
    public int getDateBookNum(String typeName,String date){
        QueryWrapper<Book>wrapper=new QueryWrapper<>();
        wrapper.eq("roomtype",typeName);
        List<Book> books=bookMapper.selectList(wrapper);
        int num=0;
        Iterator<Book> iterator=books.iterator();
        while(iterator.hasNext()){
            Book book=iterator.next();
            if(book.getBookstart().equals(date))
                num=num+1;
        }
        return num;
    }


}
