package com.team.hotel.dto;

import java.util.List;

public class TopFive {
    private String name;
    private Integer num;

    public TopFive(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    public TopFive() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
