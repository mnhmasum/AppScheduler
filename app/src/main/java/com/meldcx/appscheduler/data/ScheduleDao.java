package com.meldcx.appscheduler.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScheduleDao {
    @Insert
    void insert(Schedule alarm);

    @Query("DELETE FROM schedule_table")
    void deleteAll();

    @Query("SELECT * FROM schedule_table ORDER BY created ASC")
    LiveData<List<Schedule>> getSchedules();

    @Update
    void update(Schedule schedule);

    @Delete
    void delete(Schedule schedule);
}
