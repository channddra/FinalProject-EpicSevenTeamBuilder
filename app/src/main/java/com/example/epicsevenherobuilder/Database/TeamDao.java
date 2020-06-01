package com.example.epicsevenherobuilder.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeamDao {

    @Insert
    void insertTeam(Team team);

    @Update
    void updateTeam(Team team);

    @Delete
    void deleteTeam(Team team);

    @Query("SELECT * FROM team")
    List<Team> getAllTeam();

    @Query("SELECT COUNT(*) FROM team")
    int getCount();
}
