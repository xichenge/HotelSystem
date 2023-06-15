package com.team.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.hotel.commom.R;
import com.team.hotel.mapper.BookMapper;
import com.team.hotel.mapper.TypeMapper;
import com.team.hotel.pojo.Type;
import com.team.hotel.pojo.User;
import com.team.hotel.service.BookService;
import com.team.hotel.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeService typeService;
    @Autowired
    TypeMapper typeMapper;

    /**
     * 得到所有的房间类型信息
     * @return 得到的房间类型信息
     */
    @PostMapping("/getAllTypes")
    public R<Object> getAllTypes(){
        List<Type> types=typeService.getAllTypes();
        log.info("得到所有type信息：types:{}",types);
//        log.info("types:"+types);
        return R.success(types);
    }

    /**
     * 新增房间类型
     * @param type
     * @return 新增的房间类型
     */
    @PostMapping("/addRoomType")
    public R<Object> addRoomType(@RequestBody Type type){
        log.info("controller:type"+type);
        QueryWrapper<Type> wrapper=new QueryWrapper<>();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date date = new Date();
        String str = format.format(date);
        type.setCreatetime(str);
        wrapper.eq("typename",type.getTypename());
        Type newType=typeMapper.selectOne(wrapper);
        if(newType == null){
            typeMapper.insert(type);
            log.info("新增房间类型 type:{}",type);
            return R.success(type);
        }else{
            return R.error("已有此房间类型");
        }

    }
    /**
     * 删除房间类型
     * @param id
     * @return 删除成功信息
     */
    @DeleteMapping("/delRoomType")
    public R<Object> delRoomType(@RequestParam("id")int id){
        typeService.delRoomType(id);
        return R.success("删除类型成功");
    }

    /**
     * 修改房间类型信息
     * @param type
     * @return 修改成功信息
     */
    @PostMapping("/updataTypeInfo")
    public R<Object> updataTypeInfo(@RequestBody Type type){
        typeService.alterTypeInfo(type);
        return R.success(type);
    }

    /**
     * 得到特定房间类型信息
     * @param typename
     * @return 特定房间类型信息
     */
    @PostMapping("/getOneTypeInfo")
    public R<Object> updataOneTypeInfo(@RequestParam("typename")String typename){
        log.info("typename:{}",typename);
        QueryWrapper<Type> wrapper=new QueryWrapper<>();
        wrapper.eq("typename",typename);

        Type type=typeMapper.selectOne(wrapper);
        log.info("type:{}",type);
        return R.success(type);
    }



}
