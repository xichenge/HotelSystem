package com.team.hotel.service;

import com.team.hotel.pojo.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment deleteById(int id);
//    Order updataOrderInfo(Order order);
//    Payment addPaymentInfo(Payment payment);
    Payment alterPayment(Payment payment) throws Exception;
    List<Payment> checkThePaymentByEndTime();
}
