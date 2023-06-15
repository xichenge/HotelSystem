package com.team.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("user")
@Data
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String md5;
    private String img;
    private String birthday;
    private String tel;
    private String gender;
    private String isvip;//1或0

    public User() {
    }

    public User(String name, String email, String password, String md5, String birthday, String tel, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.md5 = md5;
        this.birthday = birthday;
        this.tel = tel;
        this.gender = gender;
    }

    public User(Integer id, String name, String email, String password, String md5, String img, String birthday, String tel, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.md5 = md5;
        this.img = img;
        this.birthday = birthday;
        this.tel = tel;
        this.gender = gender;
    }

    public User(Integer id, String name, String email, String password, String md5, String img, String birthday, String tel, String gender, String isvip) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.md5 = md5;
        this.img = img;
        this.birthday = birthday;
        this.tel = tel;
        this.gender = gender;
        this.isvip = isvip;
    }

    public String getIsvip() {
        return isvip;
    }

    public void setIsvip(String isvip) {
        this.isvip = isvip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
