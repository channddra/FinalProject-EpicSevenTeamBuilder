package com.example.epicsevenherobuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class TeamBuilderMenu extends AppCompatActivity  {
    private ViewPager mViewPager;
    public View temp;
    public Integer[] checker= {-1,-1,-1,-1};

    public ArrayList<String> mNamesTemp = new ArrayList<>();
    public ArrayList<String> mHeroesTemp = new ArrayList<>();
    public ArrayList<ArrayList<String>> mBuffsTemp = new ArrayList<>();
    public ArrayList<ArrayList<String>> mDebuffsTemp = new ArrayList<>();

    public void init(){
        mNamesTemp.add("Please Select Your Hero!");
        mNamesTemp.add("Please Select Your Hero!");
        mNamesTemp.add("Please Select Your Hero!");
        mNamesTemp.add("Please Select Your Hero!");
        mHeroesTemp.add("https://toppng.com/uploads/preview/epic-seven-logo-115628650238rvr8yrfhx.png");
        mHeroesTemp.add("https://toppng.com/uploads/preview/epic-seven-logo-115628650238rvr8yrfhx.png");
        mHeroesTemp.add("https://toppng.com/uploads/preview/epic-seven-logo-115628650238rvr8yrfhx.png");
        mHeroesTemp.add("https://toppng.com/uploads/preview/epic-seven-logo-115628650238rvr8yrfhx.png");
        ArrayList<String> temp = new ArrayList<>();
        temp.add("https://toppng.com/uploads/preview/epic-seven-logo-115628650238rvr8yrfhx.png");
        mBuffsTemp.add(temp);
        mDebuffsTemp.add(temp);
        mBuffsTemp.add(temp);
        mDebuffsTemp.add(temp);
        mBuffsTemp.add(temp);
        mDebuffsTemp.add(temp);
        mBuffsTemp.add(temp);
        mDebuffsTemp.add(temp);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_builder_menu);
        //team builder has 2 fragments, HeroRoster is to show the current heroes that we already selected
        // and and HeroSelection shows all the heroes available to be selected
        mViewPager = findViewById(R.id.teambuilderpagecontainer);
        setupViewPager(mViewPager);
    }
    private void setupViewPager(ViewPager viewPager){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        adapter.addFragment(new HeroRoster(),"Fragment1");
        // this function is used once to show temporary holder for the hero selection
        init();
        viewPager.setAdapter(adapter);


    }
    public void refresh(){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        adapter.addFragment(new HeroRoster(),"Fragment1");
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);
    }
    private int changepagekekw = 0;
    public void setViewPager(int adapterpos){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        HeroRosterSelection team1 = new HeroRosterSelection();
        adapter.addFragment(new HeroRoster(),"Fragment1");
        //send the current position to 2nd fragment
        Bundle bundle = new Bundle();
        bundle.putInt("RecPos",adapterpos);
        team1.setArguments(bundle);
        adapter.addFragment(team1,"Fragment2");
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);
        changePage(1);
        changepagekekw = 1;

    }
    public void onBackPressed() {

        if (changepagekekw == 1) {
            changePage(0);
            changepagekekw = 0;
        }
        else super.onBackPressed();
    }
    public void changePage(int pos){

        mViewPager.setCurrentItem(pos);
    }


}
