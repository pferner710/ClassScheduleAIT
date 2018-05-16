package com.example.michaeldruyan.classscheduleait.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Patrick on 5/16/18.
 */

@Entity
public class Event implements Serializable{

    @PrimaryKey
    public long id;
    public String name;
    public int startHour;
    public int startMinute;
    public int endHour;
    public int endMinute;
    public String day;

    public Event(String name, int startHour, int startMinute, int endHour, int endMinute, String day){
        this.name = name;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public long getEventId() {
        return id;
    }

    public void setEventId(long id) {
        this.id = id;
    }
}
