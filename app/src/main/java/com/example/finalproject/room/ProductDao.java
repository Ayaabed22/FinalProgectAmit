package com.example.finalproject.room;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict=REPLACE)
    void insertAll(FavouriteModel...data);

    @Query("SELECT*FROM PRODUCTDATABASE" )
    List<FavouriteModel> getAll();

    @Delete
    void delete (FavouriteModel data);

    @Query("SELECT* FROM PRODUCTDATABASE WHERE id IN (:productId)")
    List<FavouriteModel> loadAllById(int[]productId);




}
