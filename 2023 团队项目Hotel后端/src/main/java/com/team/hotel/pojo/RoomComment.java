package com.team.hotel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("room_comment")
@Data
public class RoomComment {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer roomid;
    private String commenttime;
    private Integer userid;
    private String content;
    private String createname;
    private String roomtype;

    public RoomComment(Integer id, Integer roomid, String commenttime, Integer userid, String content, String createname, String roomtype) {
        this.id = id;
        this.roomid = roomid;
        this.commenttime = commenttime;
        this.userid = userid;
        this.content = content;
        this.createname = createname;
        this.roomtype = roomtype;
    }

    public RoomComment() {
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

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }
}
