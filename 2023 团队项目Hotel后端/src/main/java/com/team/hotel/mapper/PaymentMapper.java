package com.team.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.hotel.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
    public List<Payment>getPayment();
}
