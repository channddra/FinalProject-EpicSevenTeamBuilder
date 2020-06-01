package com.example.epicsevenherobuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.epicsevenherobuilder.Database.Team;

import java.util.ArrayList;

public class TeamListMenu extends AppCompatActivity {
    private ViewPager mViewPager;
    public ArrayList<Team> team = new ArrayList<>();
    public View temp;
    public Integer[] checker= {-1,-1,-1,-1};
    public ArrayList<String> mNames = new ArrayList<>();
    public ArrayList<String> mHeroes = new ArrayList<>();
    public ArrayList<ArrayList<String>> mBuffs = new ArrayList<>();
    public ArrayList<ArrayList<String>> mDebuffs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list_menu);
        //this is the most complicated part of the app, this activity has 4 fragments,
        //First fragment is to show all hero we have created so far and 4 little images of heroes
        //Second fragment is to show the details of the heroes in the team, and a button to delete the team or edit it
        //Third Fragment is  HeroRoster Fragment
        //Fourth Fragment is HeroRosterSelection Fragment
        mViewPager = findViewById(R.id.teamlistpagecontainer);
        setupViewPager(mViewPager);
    }
    private void setupViewPager(ViewPager viewPager){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        adapter.addFragment(new TeamListDetails(),"Fragment1");
        viewPager.setAdapter(adapter);
    }

    private int changepagekekw = 0;
    public void setViewPager(int childAdapterPosition){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        TeamListDetails bop = new TeamListDetails();
        adapter.addFragment(bop,"Fragment1");

        TeamListHeroDetails clickDetails = new TeamListHeroDetails();
        Bundle bundle = new Bundle();
        bundle.putInt("Pos",childAdapterPosition);
        clickDetails.setArguments(bundle);
        adapter.addFragment(clickDetails,"Fragment1");
        adapter.notifyDataSetChanged();

        mViewPager.setAdapter(adapter);
        changePage(1);
        changepagekekw = 1;
    }
    public void changePage(int pos){
        mViewPager.setCurrentItem(pos);
    }

    public void refresh(){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        adapter.addFragment( new TeamListDetails(),"Fragment1");
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);
    }
    public void refresh2(int kekw){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putInt("Pos",kekw);

        TeamListFragmentEditus edutus = new TeamListFragmentEditus();
        edutus.setArguments(bundle);
        adapter.addFragment(edutus,"fragmen3");
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);
    }
    public void selectedit (int kekw, int next){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putInt("Pos",kekw);

        TeamListFragmentEditus edutus = new TeamListFragmentEditus();
        edutus.setArguments(bundle);
        adapter.addFragment(edutus,"fragmen3");
        HeroRosterSelection team1 = new HeroRosterSelection();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("RecPos",next);
        bundle2.putInt("Pos",kekw);
        team1.setArguments(bundle2);

        adapter.addFragment(team1,"Fragment2");



        adapter.notifyDataSetChanged();

        mViewPager.setAdapter(adapter);
        changePage(3);
    }
    public void edit(int team){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putInt("Pos",team);

        TeamListFragmentEditus edutus = new TeamListFragmentEditus();
        edutus.setArguments(bundle);
        adapter.addFragment(edutus,"fragmen3");
        adapter.notifyDataSetChanged();

        mViewPager.setAdapter(adapter);
        changePage(2);
    }
    @Override
    public void onBackPressed() {

        if (changepagekekw == 1) {
            changePage(0);
            changepagekekw = 0;
        }
        else super.onBackPressed();
    }
}
