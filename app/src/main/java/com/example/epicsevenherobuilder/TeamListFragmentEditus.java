package com.example.epicsevenherobuilder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.epicsevenherobuilder.Database.Team;

import java.util.ArrayList;

public class TeamListFragmentEditus extends Fragment implements HeroListAdapter.OnNoteListener  {


    View temp1;
    Repo repo;
    Button button;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private String tempname ;
    private int id = 0;

    //
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.team_builder_fragment_selection,container,false);
        temp1 = view;

        button = view.findViewById(R.id.savebutton);
        button.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                save();
            }
        });
        //change to db
        repo=new Repo(getActivity());
        recyclerView = view.findViewById(R.id.team_build_recview);
        recyclerView.setHasFixedSize(true);

        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        id = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("Pos", 1);
        }
        Team team =((TeamListMenu)getActivity()).team.get(id);
        tempname = team.name;
        Integer[] kekw = {team.hero1,team.hero2,team.hero3,team.hero4};
        ((TeamListMenu)getActivity()).mHeroes.clear();
        ((TeamListMenu)getActivity()).mNames.clear();
        ((TeamListMenu)getActivity()).mBuffs.clear();
        ((TeamListMenu)getActivity()).mDebuffs.clear();
        for (int i = 0; i < 4; i++){

            ((TeamListMenu)getActivity()).checker[i] = kekw[i];
            ArrayList<String> tempbuff = new ArrayList<>();
            ArrayList<String> tempdebuff = new ArrayList<>();
            ((TeamListMenu)getActivity()).mHeroes.add(repo.heroList.get(kekw[i]).img);
            ((TeamListMenu)getActivity()).mNames.add(repo.heroList.get(kekw[i]).name);
            for (int j = 0; j < 3;j++){
                if (repo.heroList.get(kekw[i]).buffs.get(j) == 1 ){

                    tempbuff.add( repo.BuffList.get(j));
                }
            }
            ((TeamListMenu)getActivity()).mBuffs.add(tempbuff );
            for (int j = 0; j < 3;j++){
                if (repo.heroList.get(kekw[i]).debuffs.get(j) == 1 ){
                    tempdebuff.add( repo.DebuffList.get(j));
                }
            }
            ((TeamListMenu)getActivity()).mDebuffs.add(tempdebuff );
        }
        adapter = new HeroListAdapter(((TeamListMenu)getActivity()).mBuffs,((TeamListMenu)getActivity()).mDebuffs,((TeamListMenu)getActivity()).mNames,((TeamListMenu)getActivity()).mHeroes,(TeamListMenu)getActivity(), this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onNoteClick(int position) {
        int id = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("Pos", 1);
        }
        ((TeamListMenu)getActivity()).temp = temp1;
        ((TeamListMenu)getActivity()).selectedit(id,position);


    }

    private void save(){
        for (int i = 0 ; i < 4;i ++){
            if (((TeamListMenu)getActivity()).checker[i] == -1){
                Toast.makeText(getActivity(), "You need to select 4 units!",
                        Toast.LENGTH_SHORT).show();
                return;

            }

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Insert Your Team Name");

        final EditText input = new EditText(getActivity());
        input.setText(tempname);
        builder.setView(input);
        builder.setCancelable(false);
// Set up the buttons
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


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
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean wantToCloseDialog = (input.getText().toString().trim().isEmpty());
                if (!wantToCloseDialog){



                    Team team =((TeamListMenu)getActivity()).team.get(id);
                    repo.updateTask (team);

                    getActivity().finish();
                    Toast.makeText(getActivity(), "Saved!",
                            Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getActivity(), "You can't put an empty Team Name!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
