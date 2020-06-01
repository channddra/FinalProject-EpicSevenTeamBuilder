package com.example.epicsevenherobuilder.Database;

import java.util.ArrayList;

public class Hero {
    public String name;
    public String img;
    public ArrayList<Integer> buffs;
    public ArrayList<Integer> debuffs;
    public int id;
    public Hero(int id,String name, String img, ArrayList<Integer> buffs, ArrayList<Integer> debuffs){
        this.id = id;
        this.name=name;
        this.img=img;
        this.buffs=buffs;
        this.debuffs=debuffs;
    }
}
