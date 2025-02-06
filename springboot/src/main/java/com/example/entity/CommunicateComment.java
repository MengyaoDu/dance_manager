package com.example.entity;

import java.io.Serializable;

/**
 * 论坛评价信息表
*/
public class CommunicateComment implements Serializable {
    private static final long serialVersionUID = 1L;

   private Integer id;
   private Integer userId;
   private String content;
   private String time;
   private Integer communicateId;

   public Integer getParentId() {
        return parentId;
    }
   public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

   private Integer parentId;

   private String userName;
   private String userAvatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCommunicateId() {
        return communicateId;
    }

    public void setCommunicateId(Integer communicateId) {
        this.communicateId = communicateId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}