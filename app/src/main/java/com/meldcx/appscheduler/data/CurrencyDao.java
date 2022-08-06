package com.meldcx.appscheduler.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CurrencyData rate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Rate rate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Rate> rates);

    @Query("DELETE FROM schedule_table")
    void deleteAll();

    @Query("SELECT * FROM currency_rate")
    LiveData<List<Rate>> getRates();

    @Query("SELECT * FROM currency_base LIMIT 1")
    CurrencyData getCurrencyBase();

    @Update
    void update(Schedule schedule);

    @Delete
    void delete(Schedule schedule);
}
