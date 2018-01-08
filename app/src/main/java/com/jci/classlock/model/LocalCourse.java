package com.jci.classlock.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 创建人: 谭火朋
 * 创建时间: 2017/11/13 0013 10:35
 */
@Entity
public class LocalCourse {
    
    @Id(autoincrement = true)
    private Long stuId;
    private String week;
    private String time;
    private String start;
    private String room;
    private String day;
    private String Classes;
    private String name;
    private String term;
    private String teacher;
    @Generated(hash = 644950429)
    public LocalCourse(Long stuId, String week, String time, String start,
            String room, String day, String Classes, String name, String term,
            String teacher) {
        this.stuId = stuId;
        this.week = week;
        this.time = time;
        this.start = start;
        this.room = room;
        this.day = day;
        this.Classes = Classes;
        this.name = name;
        this.term = term;
        this.teacher = teacher;
    }
    @Generated(hash = 489171126)
    public LocalCourse() {
    }
    public Long getStuId() {
        return this.stuId;
    }
    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }
    public String getWeek() {
        return this.week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getStart() {
        return this.start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getRoom() {
        return this.room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public String getDay() {
        return this.day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getClasses() {
        return this.Classes;
    }
    public void setClasses(String Classes) {
        this.Classes = Classes;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTerm() {
        return this.term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public String getTeacher() {
        return this.teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
