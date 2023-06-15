package com.team.hotel.controller;

import com.team.hotel.mapper.ImgMapper;
import com.team.hotel.pojo.Img;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team.hotel.commom.R;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/img")
public class ImgController {
    @Autowired
    ImgMapper imgMapper;

    /**
     * 得到所有的图片
     * @return 所有图片list
     */
    @PostMapping("/getAllImg")
    public R<Object> getAllImg(){
        List<Img> imgs=imgMapper.selectList(null);
        return R.success(imgs);
    }
}
