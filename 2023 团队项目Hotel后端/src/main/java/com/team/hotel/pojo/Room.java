package com.team.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("room")
@Data
public class Room {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer roomid;
    private String roomtype;
    private Double area;
    private Integer number;
    private String picture;
    private Double roomprice;
    private String roomcreatetime;
    private String roomcreatename;
    private Integer roomstate;

    public Room(Integer id, Integer roomid, String roomtype, Double area, Integer number, String picture, Double roomprice, String roomcreateTime, String roomcreateName, Integer roomstate) {
        this.id = id;
        this.roomid = roomid;
        this.roomtype = roomtype;
        this.area = area;
        this.number = number;
        this.picture = picture;
        this.roomprice = roomprice;
        this.roomcreatetime = roomcreateTime;
        this.roomcreatename = roomcreateName;
        this.roomstate = roomstate;
    }

    public Room() {
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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(Double roomprice) {
        this.roomprice = roomprice;
    }

    public String getRoomcreateTime() {
        return roomcreatetime;
    }

    public void setRoomcreateTime(String roomcreateTime) {
        this.roomcreatetime = roomcreateTime;
    }

    public String getRoomcreateName() {
        return roomcreatename;
    }

    public void setRoomcreateName(String roomcreateName) {
        this.roomcreatename = roomcreateName;
    }

    public Integer getRoomstate() {
        return roomstate;
    }

    public void setRoomstate(Integer roomstate) {
        this.roomstate = roomstate;
    }
}
