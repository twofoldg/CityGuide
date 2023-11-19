package com.example.cityguide.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cityguide.model.City;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CityDao {
    @Insert
    Single<Long> insert(City city);

    @Update
    int update(City city);

    @Delete
    int delete(City city);

    @Query("DELETE FROM cities")
    void deleteAll();

    @Query("SELECT * FROM cities")
    LiveData<List<City>> getAllCities();
}

