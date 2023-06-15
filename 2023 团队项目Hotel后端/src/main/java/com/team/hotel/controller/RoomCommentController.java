package com.team.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.hotel.commom.R;
import com.team.hotel.mapper.RoomCommentMapper;
import com.team.hotel.mapper.RoomMapper;
import com.team.hotel.mapper.UserMapper;
import com.team.hotel.pojo.Room;
import com.team.hotel.pojo.RoomComment;
import com.team.hotel.pojo.User;
import com.team.hotel.service.RoomCommentService;
import com.team.hotel.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/roomComment")
public class RoomCommentController {
    @Autowired
    RoomCommentService roomCommentService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoomMapper roomMapper;
    /**
     *  得到所有的评论
     * @return 得到的所有评论信息
     */
    @PostMapping("/getAllRoomComment")
    public R<Object> getAllRoomComment(){
        List<RoomComment> roomComments=roomCommentService.getAllRoomComment();
        log.info("得到的所有评论信息：{}",roomComments);
        return R.success(roomComments);
    }

    /**
     * 根据roomid得到特定房间的评论
     * @param roomid
     * @return 得到的房间评论
     */
    @PostMapping("/getAllRoomCommentByRoomid")
    public R<Object> getAllRoomCommentByRoomid(int roomid) {
        log.info("得到的roomid：{}",roomid);
        List<RoomComment> roomComments=roomCommentService.getAllRoomCommentByRoomid(roomid);
        log.info("得到的roomid房间的评论：{}",roomComments);
        return R.success(roomComments);
    }

    /**
     * 增加评论
     * @param email
     * @param roomid
     * @param content
     * @param roomtype
     * @return 增加成功信息
     */
    @PostMapping("/addRoomComment")
    public R<Object> addRoomComment(@RequestParam("email")String email,@RequestParam("roomid")int roomid,@RequestParam("content")String content){
        log.info("email:{},roomid:{},content:{}",email,roomid,content);
        RoomComment roomComment=new RoomComment();
        roomComment.setContent(content);

        QueryWrapper<Room> roomQueryWrapper=new QueryWrapper<>();
        roomQueryWrapper.eq("roomid",roomid);
        Room room=roomMapper.selectOne(roomQueryWrapper);
        String roomtype=room.getRoomtype();

        roomComment.setRoomtype(roomtype);
        roomComment.setRoomid(roomid);

        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("email",email);
        User user=userMapper.selectOne(wrapper);

        roomComment.setUserid(user.getId());
        roomComment.setCreatename(user.getName());
        log.info("新增评论时得到的roomComment：{}",roomComment);
        roomCommentService.addRoomCommentInfo(roomComment);
        return R.success("增加成功");
    }

    /**
     * 删除评论
     * @param id
     * @return  删除成功信息
     */
    @DeleteMapping("/delRoomComment")
    public R<Object> delRoomComment(int id){
        log.info("得到id：{}",id);
        roomCommentService.deleteById(id);
        return R.success("删除成功");
    }

    /**
     * 修改评论
     * @param roomComment
     * @return 修改后的评论数据
     */
    @PostMapping("/updataRoomComment")
    public R<Object> updataRoomComment(@RequestBody RoomComment roomComment){
        log.info("修改评论时的数据：roomComment: {}",roomComment);
        roomCommentService.updataRoomCommentInfo(roomComment);
        return R.success(roomComment);
    }

    /**
     * 根据房间类型得到评论
     * @param roomtype
     * @return 根据房间类型得到评论
     */
    @PostMapping("/getRoomCommentByRoomtype")
    public R<Object> getRoomCommentByRoomtype(@RequestParam("roomtype")String roomtype){
        log.info("roomtype:{}",roomtype);
        List<RoomComment> roomComments=roomCommentService.getRoomCommentByRoomtype(roomtype);
        return R.success(roomComments);
    }
}
