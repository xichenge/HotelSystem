package com.team.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.hotel.mapper.PaymentMapper;
import com.team.hotel.mapper.RoomCommentMapper;
import com.team.hotel.pojo.Payment;
import com.team.hotel.pojo.Room;
import com.team.hotel.pojo.RoomComment;
import com.team.hotel.service.PaymentService;
import com.team.hotel.service.RoomCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RoomCommentServiceImpl extends ServiceImpl<RoomCommentMapper, RoomComment> implements RoomCommentService {
    @Autowired
    RoomCommentMapper roomCommentMapper;
    public List<RoomComment> getAllRoomComment(){
        List<RoomComment> roomComments=roomCommentMapper.selectList(null);
        return roomComments;
    }

    public List<RoomComment> getAllRoomCommentByRoomid(int roomid){
        log.info("得到的roomid：{}",roomid);
        QueryWrapper<RoomComment> wrapper=new QueryWrapper<>();
        wrapper.eq("roomid",roomid);
        List<RoomComment> roomComments=roomCommentMapper.selectList(wrapper);
        log.info("得到的房间的评论：{}",roomComments);
        return roomComments;
    }

    public RoomComment deleteById(int id){
        log.info("id:{}",id);
        roomCommentMapper.deleteById(id);
        return null;
    }
    /*

     */
    public RoomComment updataRoomCommentInfo(RoomComment roomComment){//根据用户id和房间id来找到库中的评论，并修改评论内容与评论时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String commentTime = format.format(System.currentTimeMillis());

        roomComment.setCommenttime(commentTime);

        log.info("更新后的评论内容：roomComment:{}",roomComment);
        roomCommentMapper.updateById(roomComment);
        return null;
    }
    public RoomComment addRoomCommentInfo(RoomComment roomComment){
        log.info("要添加的房间评论信息：{}",roomComment);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String commentTime = format.format(System.currentTimeMillis());

        roomComment.setCommenttime(commentTime);
        log.info("最后生成的要添加的房间评论信息：{}",roomComment);
        roomCommentMapper.insert(roomComment);
        return null;
    }
    public List<RoomComment> getRoomCommentByRoomtype(String typename){
        QueryWrapper<RoomComment> wrapper=new QueryWrapper<>();
        wrapper.eq("roomType",typename);

        List<RoomComment> roomComments=roomCommentMapper.selectList(wrapper);
        log.info("得到的符合房间类型的roomcomment：{}",roomComments);
        return roomComments;
    }
}
