package com.example.epicsevenherobuilder;

import android.content.Context;
import android.os.AsyncTask;

import com.example.epicsevenherobuilder.Database.AppDatabase;
import com.example.epicsevenherobuilder.Database.Hero;
import com.example.epicsevenherobuilder.Database.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repo {
    private AppDatabase appDatabase;
    ArrayList<Hero> heroList;
    ArrayList<String> BuffList;
    ArrayList<String> DebuffList;
    private void fillHero(int id,String name, String img, ArrayList<Integer> buffs, ArrayList<Integer> debuffs){
        heroList.add(new Hero(id,name, img, buffs, debuffs));
    }
    private void fillBuff(String name){
        BuffList.add(name);
    }
    private void fillDebuff(String name){
        DebuffList.add(name);
    }
    public Repo(Context context){
        appDatabase=AppDatabase.getAppDatabase(context);
        heroList = new ArrayList<>();
        BuffList = new ArrayList<>();
        DebuffList = new ArrayList<>();
        fillHero(0,"Roana", "http://epic7x.com/wp-content/uploads/2020/01/loeanna-full.png", new ArrayList<Integer>(Arrays.asList(0,1,0)), new ArrayList<Integer>(Arrays.asList(1,1,0)));
        fillHero(1,"Seaside Bellona", "http://epic7x.com/wp-content/uploads/2019/07/belona-new.png", new ArrayList<Integer>(Arrays.asList(1,0,0)), new ArrayList<Integer>(Arrays.asList(1,0,1)));
        fillHero(2,"Sinful Angelica", "http://epic7x.com/wp-content/uploads/2020/04/angelica-full.png", new ArrayList<Integer>(Arrays.asList(1,0,1)), new ArrayList<Integer>(Arrays.asList(0,0,0)));
        fillHero(3,"Elphet Valentine", "https://epic7x.com/wp-content/uploads/2020/04/Valentine.png", new ArrayList<Integer>(Arrays.asList(0,1,1)), new ArrayList<Integer>(Arrays.asList(0,1,0)));
        fillHero(4,"Yufine", "http://epic7x.com/wp-content/uploads/2019/01/yufine-full.png", new ArrayList<Integer>(Arrays.asList(1,1,0)), new ArrayList<Integer>(Arrays.asList(0,0,1)));
        fillHero(5,"Arbiter Vildred", "https://external-preview.redd.it/gXF_-oWmTeNyRw66uYHKhuBCsmRvwNbwuVgB1OEgZZ4.jpg?auto=webp&s=6136609b0c6b97b47ea9a632dce6d92d4d2bbf30", new ArrayList<Integer>(Arrays.asList(1,0,0)), new ArrayList<Integer>(Arrays.asList(0,0,0)));
        fillHero(6,"Kayron", "https://i.redd.it/2cs0cg48u7y41.jpg", new ArrayList<Integer>(Arrays.asList(1,1,1)), new ArrayList<Integer>(Arrays.asList(0,0,1)));
        fillHero(7,"Elphet Valentine", "http://epic7x.com/wp-content/uploads/2020/04/Valentine.png", new ArrayList<Integer>(Arrays.asList(0,1,1)), new ArrayList<Integer>(Arrays.asList(0,1,0)));
        fillHero(8,"Elphet Valentine", "http://epic7x.com/wp-content/uploads/2020/04/Valentine.png", new ArrayList<Integer>(Arrays.asList(0,1,1)), new ArrayList<Integer>(Arrays.asList(0,1,0)));
        fillHero(9,"Elphet Valentine", "http://epic7x.com/wp-content/uploads/2020/04/Valentine.png", new ArrayList<Integer>(Arrays.asList(0,1,1)), new ArrayList<Integer>(Arrays.asList(0,1,0)));







        fillBuff("http://epic7x.com/wp-content/uploads/2018/12/stic_att_up.png");
        fillBuff("http://epic7x.com/wp-content/uploads/2018/12/stic_speed_up.png");
        fillBuff("https://epic7x.com/wp-content/uploads/2018/12/stic_def_up.png");
        fillDebuff("http://epic7x.com/wp-content/uploads/2018/12/stic_att_dn.png");
        fillDebuff("http://epic7x.com/wp-content/uploads/2018/12/stic_dodge_dn.png");
        fillDebuff("http://epic7x.com/wp-content/uploads/2018/12/stic_def_dn.png");

    }

    public void insertTask(final Team team){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                appDatabase.teamDao().insertTeam(team);
                return null;
            }

        }.execute();
    }

    public void deleteTask(final Team team){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                appDatabase.teamDao().deleteTeam(team);
                return null;
            }

        }.execute();
    }

    public void updateTask(final Team team){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids){
                appDatabase.teamDao().updateTeam(team);
                return null;
            }

        }.execute();
    }

    public List<Team> getAllTeamsTask(){
        List<Team> res=appDatabase.teamDao().getAllTeam();
        return res;
    }

    public int getCount(){
        return appDatabase.teamDao().getCount();
    }
}
