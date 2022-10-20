package com.example.finalproject.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {FavouriteModel.class}, version = 2)
//@TypeConverters({dataRoomBase.Converters.class})
public abstract class FavouriteDatabase extends RoomDatabase {

    public abstract ProductDao productsDao();

    private static FavouriteDatabase INSTANCE;

    public static FavouriteDatabase getDbInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(),
                            FavouriteDatabase.class, "favourite Product")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }

        return INSTANCE;
    }


}

//    private  static  AppDataBase getInstance(Context context){
//        if (instance==null){
//            instance= Room.databaseBuilder(context
//                    ,AppDataBase.class
//                    ,"MylocationDatabase")
//                    .allowMainThreadQueries()
//                    .build();
//
//        }
//        return instance;
//    }