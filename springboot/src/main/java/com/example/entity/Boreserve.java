package com.example.entity;

import java.io.Serializable;

/**
 * 公告信息表
*/
public class Boreserve implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer borrowId;
    private Integer userId;
    private String start;
    private String end;
    private String status;

    private String borrowImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBorrowImg() {
        return borrowImg;
    }

    public void setBorrowImg(String borrowImg) {
        this.borrowImg = borrowImg;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String borrowName;
    private String userName;


}