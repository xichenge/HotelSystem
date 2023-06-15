package com.team.hotel.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("payment")
@Data
public class Payment implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userid;
    private String username;
    private String roomid;
    private String ordertime;
    private String tel;
    private String start;
    private String end;
    private String status;//状态0为待支付，1是支付成功，2是订单完成
    private Double fee;

    public Payment(Integer id, String userid, String roomid, String ordertime, String tel, String start, String end, String status, Double fee) {
        this.id = id;
        this.userid = userid;
        this.roomid = roomid;
        this.ordertime = ordertime;
        this.tel = tel;
        this.start = start;
        this.end = end;
        this.status = status;
        this.fee = fee;
    }


    public Payment() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
