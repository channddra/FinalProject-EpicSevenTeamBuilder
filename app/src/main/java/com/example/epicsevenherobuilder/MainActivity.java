package com.example.epicsevenherobuilder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void openHeroList(View v){
        Intent inte = new Intent(this, HeroListMenu.class);
        startActivity(inte);
    }
    public void openCreateList(View v){
        Intent inte = new Intent(this, TeamBuilderMenu.class);
        startActivity(inte);
    }
    public void openSavedList(View v){
        Intent inte = new Intent(this, TeamListMenu.class);
        startActivity(inte);
    }
    // add button down right corner to add teams
}
