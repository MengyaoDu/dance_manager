package com.example.entity;

import java.io.Serializable;

/**
 * 课程信息表
*/
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String img;
    private String time;
    private Double price;
    private Integer teacherId;
    private String content;
    private String room;
    private String  difficult;

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public String getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(String minPeople) {
        this.minPeople = minPeople;
    }

    public String getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(String totalPeople) {
        this.totalPeople = totalPeople;
    }

    public String getBookedPeople() {
        return bookedPeople;
    }

    public void setBookedPeople(String bookedPeople) {
        this.bookedPeople = bookedPeople;
    }

    private String minPeople;
    private String totalPeople;
    private String bookedPeople;

    private String teacherName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

}