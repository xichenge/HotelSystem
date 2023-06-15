package com.team.hotel.dto;

import java.util.List;

public class TopFiveDetails {
    private String typename;
    private List<String> sevendays;
    private int[] num= new int[7];

    public TopFiveDetails(String typename, List<String> sevendays, int[] num) {
        this.typename = typename;
        this.sevendays = sevendays;
        this.num = num;
    }

    public TopFiveDetails() {
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public List<String> getSevendays() {
        return sevendays;
    }

    public void setSevendays(List<String> sevendays) {
        this.sevendays = sevendays;
    }

    public int[] getNum() {
        return num;
    }

    public void setNum(int[] num) {
        this.num = num;
    }
}
