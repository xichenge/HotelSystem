package com.team.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("type")
@Data
public class Type {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String typename;
    private Double price;
    private String createtime;
    private String createname;
    private String img;
    private Integer num;
    public Type() {
    }

    public Type(Integer id, String typename, Double price, String createtime, String createname) {
        this.id = id;
        this.typename = typename;
        this.price = price;
        this.createtime = createtime;
        this.createname = createname;
    }

    public Type(Integer id, String typename, Double price, String createtime, String createname, String img) {
        this.id = id;
        this.typename = typename;
        this.price = price;
        this.createtime = createtime;
        this.createname = createname;
        this.img = img;
    }

    public Type(Integer id, String typename, Double price, String createtime, String createname, String img, Integer num) {
        this.id = id;
        this.typename = typename;
        this.price = price;
        this.createtime = createtime;
        this.createname = createname;
        this.img = img;
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }
}
