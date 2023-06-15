package com.team.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.hotel.mapper.PaymentMapper;
import com.team.hotel.mapper.RoomMapper;
import com.team.hotel.pojo.Payment;
import com.team.hotel.pojo.Room;
import com.team.hotel.service.PaymentService;
import com.team.hotel.utils.GetMoneyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.team.hotel.utils.GetMoneyUtil.getMoney;
import static com.team.hotel.utils.GetMoneyUtil.getSevenDate;

@Slf4j
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    RoomMapper roomMapper;
    public List<Payment> getAllPayments(){
        List<Payment> payments = paymentMapper.selectList(null);
        log.info("payments:{}", payments);
        return payments;
    }
    public Payment deleteById(int id){
        log.info("删除的id：id：{}",id);
        paymentMapper.deleteById(id);
        return null;
    }

    public Payment alterPayment(Payment payment) throws Exception {
        log.info("payment:{}",payment);
//        Double money= GetMoneyUtil.getMoney(payment.getStart(),payment.getEnd(),)
        QueryWrapper<Room> wrapper=new QueryWrapper<>();
        wrapper.eq("roomid",payment.getRoomid());
        Room room=roomMapper.selectOne(wrapper);
        Double money = getMoney(payment.getStart(), payment.getEnd(), room.getRoomprice());

        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date now = new Date();//开始时间
            String nowDay=dft.format(now);
            payment.setOrdertime(nowDay);
        }catch (Exception e){
            e.printStackTrace();
        }

        payment.setFee(money);
        paymentMapper.updateById(payment);
        return null;
    }

    public List<Payment> checkThePaymentByEndTime(){
        List<Payment> payments=paymentMapper.selectList(null);

        Iterator<Payment> iterator=payments.iterator();

        while(iterator.hasNext()){
            Payment payment=iterator.next();
            if(payment.getStatus().equals("1"))     //已支付的订单
            {
                try {
                    DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
                    Date date=new Date();
                    String now=dft.format(date);
                    if(now.equals(payment.getEnd())){
                        payment.setStatus("2");
                        paymentMapper.updateById(payment);
                        QueryWrapper<Room> roomQueryWrapper=new QueryWrapper<>();
                        roomQueryWrapper.eq("roomid",payment.getRoomid());
                        Room room=roomMapper.selectOne(roomQueryWrapper);
                        room.setRoomstate(0);
                        roomMapper.updateById(room);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        List<Payment> payments2=paymentMapper.selectList(null);
        log.info("检查完成");
        return payments2;
    }

}
