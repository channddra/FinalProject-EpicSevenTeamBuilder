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

public class HeroRoster extends Fragment implements HeroListAdapter.OnNoteListener {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mHeroes = new ArrayList<>();
    private ArrayList<ArrayList<String>> mBuffs = new ArrayList<>();
    private ArrayList<ArrayList<String>> mDebuffs = new ArrayList<>();
    View temp1;
    Repo repo;
    Button button;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.team_builder_fragment_selection,container,false);
        //temporary variable to hold view to pass into second fragment
        temp1 = view;
        //onclick listener for saving
        button = view.findViewById(R.id.savebutton);
        button.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                save();
            }
        });
        //as explained before, we dont have database yet in the first version of the app
        repo=new Repo(getActivity());
        for(int i=0; i<repo.heroList.size(); i++){
            mHeroes.add(repo.heroList.get(i).img);
            mNames.add(repo.heroList.get(i).name);
            ArrayList<String> tempbuff = new ArrayList<>();
            for (int j = 0; j < 3;j++){
                if (repo.heroList.get(i).buffs.get(j) == 1 ){
                    tempbuff.add( repo.BuffList.get(j));
                }
            }
            mBuffs.add(tempbuff );
            ArrayList<String> tempdebuff = new ArrayList<>();
            for (int j = 0; j < 3;j++){
                if (repo.heroList.get(i).debuffs.get(j) == 1 ){
                    tempdebuff.add( repo.DebuffList.get(j));
                }
            }
            mDebuffs.add(tempdebuff );
        }
        //to show the content
        recyclerView = view.findViewById(R.id.team_build_recview);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new HeroListAdapter(((TeamBuilderMenu)getActivity()).mBuffsTemp,((TeamBuilderMenu)getActivity()).mDebuffsTemp,   ((TeamBuilderMenu)getActivity()).mNamesTemp,((TeamBuilderMenu)getActivity()).mHeroesTemp,(TeamBuilderMenu)getActivity(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onNoteClick(int position) {
        //on click, save the view on this fragment and to be called later.
        ((TeamBuilderMenu)getActivity()).temp = temp1 ;
        //create 2nd fragment
        ((TeamBuilderMenu)getActivity()).setViewPager(position);


    }

    private void save(){
        //check whether user already selected 4 heroes
        for (int i = 0 ; i < 4;i ++){
            if (((TeamBuilderMenu)getActivity()).checker[i] == -1){
                Toast.makeText(getActivity(), "You need to select 4 heroes to make a team!",
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //make a alert dialog to put team name
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Insert Your Team Name");
        final EditText input = new EditText(getActivity());
        builder.setView(input);
        builder.setCancelable(false);
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
                //check whether team name is empty
                Boolean wantToCloseDialog = (input.getText().toString().trim().isEmpty());

                if (!wantToCloseDialog){

                    Team team = new Team(input.getText().toString(),
                            ((TeamBuilderMenu)getActivity()).checker[0],
                            ((TeamBuilderMenu)getActivity()).checker[1],
                            ((TeamBuilderMenu)getActivity()).checker[2],
                            ((TeamBuilderMenu)getActivity()).checker[3] );
                    repo.insertTask(team);

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
