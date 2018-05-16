package com.example.michaeldruyan.classscheduleait.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Patrick on 5/16/18.
 */

@Dao
public interface EventDAO {
    @Query("SELECT * FROM event")
    List<Event> getAll();

    @Query("SELECT * FROM event WHERE day LIKE :day")
    List<Event> getDayEvents(String day);

    @Insert
    long insertEvent(Event event);

    @Delete
    void delete(Event event);

    @Update
    void update(Event event);
}
