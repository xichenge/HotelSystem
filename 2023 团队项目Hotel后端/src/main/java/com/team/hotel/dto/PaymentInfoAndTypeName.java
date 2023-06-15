package com.team.hotel.dto;

public class PaymentInfoAndTypeName {
    private String starttime;
    private String ordertime;
    private String endtime;
    private String tel;
    private Integer paymentid;
    private String typename;
    private String img;

    public PaymentInfoAndTypeName(String starttime, String ordertime, String endtime, String tel, Integer paymentid, String typename, String img) {
        this.starttime = starttime;
        this.ordertime = ordertime;
        this.endtime = endtime;
        this.tel = tel;
        this.paymentid = paymentid;
        this.typename = typename;
        this.img = img;
    }

    public PaymentInfoAndTypeName() {
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Integer paymentid) {
        this.paymentid = paymentid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
