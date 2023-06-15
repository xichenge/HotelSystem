package com.team.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("book")
@Data
public class Book implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer roomid;
    private String roomtype;
    private Double roomprice;
    private Double expense;
    private String idcard;
    private String tel;
    private String bookstart;
    private String bookend;
    private String useremail;
    private String bookname;
    private String status;

    public Book(Integer id, Integer roomid, String roomtype, Double roomprice, Double expense, String idcard, String tel, String bookstart, String bookend, String useremail, String bookname, String status) {
        this.id = id;
        this.roomid = roomid;
        this.roomtype = roomtype;
        this.roomprice = roomprice;
        this.expense = expense;
        this.idcard = idcard;
        this.tel = tel;
        this.bookstart = bookstart;
        this.bookend = bookend;
        this.useremail = useremail;
        this.bookname = bookname;
        this.status = status;
    }

    public Book(Integer roomid, String roomtype, Double roomprice, Double expense, String idcard, String tel, String bookstart, String bookend, String useremail, String bookname, String status) {
        this.roomid = roomid;
        this.roomtype = roomtype;
        this.roomprice = roomprice;
        this.expense = expense;
        this.idcard = idcard;
        this.tel = tel;
        this.bookstart = bookstart;
        this.bookend = bookend;
        this.useremail = useremail;
        this.bookname = bookname;
        this.status = status;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public Double getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(Double roomprice) {
        this.roomprice = roomprice;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBookstart() {
        return bookstart;
    }

    public void setBookstart(String bookstart) {
        this.bookstart = bookstart;
    }

    public String getBookend() {
        return bookend;
    }

    public void setBookend(String bookend) {
        this.bookend = bookend;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
