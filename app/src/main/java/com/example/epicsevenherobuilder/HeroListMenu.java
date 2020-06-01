package com.example.epicsevenherobuilder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class HeroListMenu extends AppCompatActivity{
    private ViewPager mViewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_list_page);
        //Hero List has 2 fragments, HeroList contains all the hero in the database
        //HeroListDetails is a fragment that shows the hero details of the clicked hero in the first fragment
        mViewPager = findViewById(R.id.herolistpagecontainer);

        //setting up Hero List Fragment Container by inserting the First Fragment
        setupViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager){
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        adapter.addFragment(new HeroList(),"Fragment1");
        viewPager.setAdapter(adapter);
    }
    private int changepagekekw = 0;
    public void setViewPager(int childAdapterPosition){

        //what this function do is to create the other fragment that shows the detail of the hero
        SectionStateAdapter adapter = new SectionStateAdapter(getSupportFragmentManager());
        adapter.addFragment(new HeroList(),"Fragment1");
        //the hero detail fragment
        HeroListDetails singlehero = new HeroListDetails();
        //and pass the selected hero position to the other fragment.
        Bundle bundle = new Bundle();
        bundle.putInt("Pos",childAdapterPosition);
        singlehero.setArguments(bundle);
        adapter.notifyDataSetChanged();
        adapter.addFragment(singlehero,"Fragment2");
        mViewPager.setAdapter(adapter);
        changePage(1);
        changepagekekw = 1;
    }
    public void changePage(int pos){
        mViewPager.setCurrentItem(pos);
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
