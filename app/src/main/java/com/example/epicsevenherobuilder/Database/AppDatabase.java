package com.example.epicsevenherobuilder.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Team.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TeamDao teamDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "team-database").build();
        }
        return INSTANCE;
    }
}
