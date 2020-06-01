package com.example.epicsevenherobuilder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.epicsevenherobuilder.Database.Team;

import java.util.ArrayList;

public class TeamListHeroDetails extends Fragment {
    private ArrayList<String> mNames = new ArrayList<>()  ;
    private ArrayList<String> mHeroes= new ArrayList<>()  ;
    private ArrayList<ArrayList<String>> mBuffs = new ArrayList<>();
    private ArrayList<ArrayList<String>> mDebuffs = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    Repo repo;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.team_list_fragment_click_layout,container,false);
        recyclerView = view.findViewById(R.id.team_build_recview);
        recyclerView.setHasFixedSize(true);


        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

       int pos = 1;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("Pos", 1);
        }
        repo=new Repo(getActivity());
         Team team = ((TeamListMenu)getActivity()).team.get(pos);

        Integer[] kekw = {team.hero1,team.hero2,team.hero3,team.hero4};
       //1
        for (int i = 0; i < 4; i++){
            ((TeamListMenu)getActivity()).checker[i] = repo.heroList.get(kekw[i]).id;
            ArrayList<String> tempbuff = new ArrayList<>();
            ArrayList<String> tempdebuff = new ArrayList<>();
            mHeroes.add(repo.heroList.get(kekw[i]).img);
            mNames.add(repo.heroList.get(kekw[i]).name);
            for (int j = 0; j < 3;j++){
                if (repo.heroList.get(kekw[i]).buffs.get(j) == 1 ){

                    tempbuff.add( repo.BuffList.get(j));
                }
            }
            mBuffs.add(tempbuff );
            for (int j = 0; j < 3;j++){
                if (repo.heroList.get(kekw[i]).debuffs.get(j) == 1 ){
                    tempdebuff.add( repo.DebuffList.get(j));
                }
            }
            mDebuffs.add(tempdebuff );
        }

        recyclerView = view.findViewById(R.id.team_build_recview);
        adapter = new TeamListFragmentClickRecView(mBuffs,mDebuffs,mNames,mHeroes,(TeamListMenu)getActivity());
        recyclerView.setAdapter(adapter);

        Button button =  view.findViewById(R.id.editButton);
        final int finalPos1 = pos;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editus(finalPos1);
            }
        });
         button =  view.findViewById(R.id.deletebutton);
        final int finalPos = pos;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletus(finalPos);
            }
        });
        return view;
    }
    private void editus(int team){
        ((TeamListMenu)getActivity()).edit(team);
    }
    private void deletus(final int pos){
        final Repo repo = new Repo(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure?");
        builder.setCancelable(false);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                repo.deleteTask(((TeamListMenu)getActivity()).team.get(pos));
                Toast.makeText(getActivity(), "Deleted!",
                        Toast.LENGTH_SHORT).show();
                ((TeamListMenu)getActivity()).refresh();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alert = builder.create();
        alert.show();

    }
}
