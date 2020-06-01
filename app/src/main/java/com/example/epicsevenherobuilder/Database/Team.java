package com.example.epicsevenherobuilder.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "team")
public class Team {

    @PrimaryKey(autoGenerate = true)
    public int tid;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "hero1")
    public int hero1;
    @ColumnInfo(name = "hero2")
    public int hero2;
    @ColumnInfo(name = "hero3")
    public int hero3;
    @ColumnInfo(name = "hero4")
    public int hero4;

    public Team(String name, int hero1, int hero2, int hero3, int hero4){
        this.name=name;
        this.hero1=hero1;
        this.hero2=hero2;
        this.hero3=hero3;
        this.hero4=hero4;
    }
}
