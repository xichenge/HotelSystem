package com.team.hotel.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team.hotel.pojo.Img;

import java.util.List;

public interface ImgService extends BaseMapper<Img> {
    List<Img>getAllImg();
}
