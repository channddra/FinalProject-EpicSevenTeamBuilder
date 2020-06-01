package com.example.epicsevenherobuilder;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.epicsevenherobuilder.Database.Team;

import java.util.ArrayList;

public class HeroRosterSelection extends Fragment implements HeroRosterSelectionAdapter.OnNoteListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mHeroes = new ArrayList<>();
    private ArrayList<ArrayList<String>> mBuffs = new ArrayList<>();
    private ArrayList<ArrayList<String>> mDebuffs = new ArrayList<>();
    private ArrayList<Integer> mID = new ArrayList<>();
    Repo repo;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.team_builder_fragment_select_hero,container,false);
        recyclerView = view.findViewById(R.id.heroselection2);
        recyclerView.setHasFixedSize(true);

        repo=new Repo(getActivity());
        //here we haave an array to see which heroes we already selected...(1)
        Integer[] kekw;
        try{
             kekw = ((TeamBuilderMenu)getActivity()).checker;
        }catch (Exception e){
             kekw = ((TeamListMenu)getActivity()).checker;
        }
        //
        for(int i=0; i<repo.heroList.size(); i++){
            //(1)... to here so we cant select the same hero as we already did before
            boolean checkifdupeheroes=false;
            for (int j = 0 ; j < 4; j++){
                if (kekw[j] != -1 && repo.heroList.get(i) == repo.heroList.get(kekw[j]   )) {
                    checkifdupeheroes = true;
                    break;
                }

            }
            if (checkifdupeheroes){

                continue;
            }
            mID.add(repo.heroList.get(i).id);
            mHeroes.add(repo.heroList.get(i).img);
            mNames.add(repo.heroList.get(i).name);
            ArrayList<String> tempbuff = new ArrayList<>();
            for (int j = 0; j < 3;j++){
                if (repo.heroList.get(i).buffs.get(j) == 1 ){
                  //
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

        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new HeroRosterSelectionAdapter(mNames,mHeroes, getActivity(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onNoteClick(int position) {
        //get which hero slot, 0,1,2,3 that we selected in 1st fragment
        int id = 1;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("RecPos", 1);
        }
        View view;
        //right here, this fragment is used in two different menu, TeamBuilder to create Team
        // and on TeamList so we can edit team.
        //so we create a try catch to see whether it's coming from TeamBuilder or TeamList
        try{
            view= ((TeamBuilderMenu)getActivity()).temp;
        }catch (Exception e){
            view = ((TeamListMenu)getActivity()).temp;
        }
        // pos = Which hero we selected (getting heroID)
        int pos = mID.get(position);
        //variable check is to check whether its TeamBuilder or TeamList
        int check = 0;
        //here we have a temporary variable to put image link of the buffs/debuffs as the
        //database only hold 0,1 value whether the hero has it or not
        ArrayList<String> tempbuff = new ArrayList<>();
        for (int j = 0; j < 3;j++){
            if (repo.heroList.get(pos).buffs.get(j) == 1 ){
                tempbuff.add( repo.BuffList.get(j));
            }
        }
        ArrayList<String> tempdebuff = new ArrayList<>();
        for (int j = 0; j < 3;j++){
            if (repo.heroList.get(pos).debuffs.get(j) == 1 ){
                tempdebuff.add( repo.DebuffList.get(j));
            }
        }
        // checking whether it's coming from TeamBuilder or TeamList
        try{
            ((TeamBuilderMenu)getActivity()).mHeroesTemp.set(id,repo.heroList.get(pos).img);
        }catch(Exception e){
            //set the hero data to the previous fragment
            check = 1;
            int teamteam = 0;
            if (bundle != null) {
                teamteam = bundle.getInt("Pos", 1);
            }
            Team team =((TeamListMenu)getActivity()).team.get(teamteam);
            if ( id == 0){
               team.hero1 = repo.heroList.get(pos).id;
            }
            else if (id == 1){
                team.hero2 = repo.heroList.get(pos).id;
            }
            else if (id == 2){
                team.hero3 = repo.heroList.get(pos).id;
            }
            else if (id == 3){
                team.hero4 = repo.heroList.get(pos).id;
            }

//            ((TeamListMenu)getActivity()).mHeroes.set(id,repo.heroList.get(pos).img);
//            ((TeamListMenu)getActivity()).mNames.set(id,repo.heroList.get(pos).name);
//            ((TeamListMenu)getActivity()).mBuffs.set(id,tempbuff);
//            ((TeamListMenu)getActivity()).mDebuffs.set(id,tempdebuff);
            ((TeamListMenu)getActivity()). checker[id] = pos;
            ImageView imgv = view.findViewById(R.id.selectteamlist1);
            TextView txt= view.findViewById(R.id.selecttextteamlist1);
            Glide.with(getActivity())
                    .asBitmap()
                    .load(repo.heroList.get(pos).img)
                    .into(imgv);
            txt.setText(repo.heroList.get(pos).name);
                //buff
            recyclerView = view.findViewById(R.id.selectbufflist1);
            recyclerView.setHasFixedSize(true);
            manager =new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(manager);
            adapter = new BuffDebuffAdapter(tempbuff,(TeamListMenu)getActivity());
            recyclerView.setAdapter(adapter);
            //debuff
            recyclerView = view.findViewById(R.id.selectdebufflist1);
            recyclerView.setHasFixedSize(true);
            manager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(manager);
            adapter = new BuffDebuffAdapter(tempdebuff,(TeamListMenu)getActivity());
            recyclerView.setAdapter(adapter);
        };
        //if it's not coming from TeamList, then do this
            if (check == 0){
                //set the hero data to the previous fragment
                ((TeamBuilderMenu)getActivity()).mNamesTemp.set(id,repo.heroList.get(pos).name);
                ((TeamBuilderMenu)getActivity()).mBuffsTemp.set(id,tempbuff);
                ((TeamBuilderMenu)getActivity()).mDebuffsTemp.set(id,tempdebuff);

                ((TeamBuilderMenu)getActivity()). checker[id] = pos;

                ImageView imgv = view.findViewById(R.id.selectteamlist1);
                TextView txt= view.findViewById(R.id.selecttextteamlist1);
                Glide.with(getActivity())
                        .asBitmap()
                        .load(repo.heroList.get(pos).img)
                        .into(imgv);
                txt.setText(repo.heroList.get(pos).name);
                //buff
                recyclerView = view.findViewById(R.id.selectbufflist1);
                recyclerView.setHasFixedSize(true);

                manager =new GridLayoutManager(getActivity(),3);
                recyclerView.setLayoutManager(manager);
                adapter = new BuffDebuffAdapter(tempbuff,(TeamBuilderMenu)getActivity());
                recyclerView.setAdapter(adapter);
                //debuff
                recyclerView = view.findViewById(R.id.selectdebufflist1);
                recyclerView.setHasFixedSize(true);
                manager = new GridLayoutManager(getActivity(),3);
                recyclerView.setLayoutManager(manager);
                adapter = new BuffDebuffAdapter(tempdebuff,(TeamBuilderMenu)getActivity());
                recyclerView.setAdapter(adapter);
            }
        //refreshing so first fragment is updated
        try{

            ((TeamBuilderMenu)getActivity()).refresh();
        }catch(Exception e){

            if (bundle != null) {
                id = bundle.getInt("Pos", 1);
            }
            ((TeamListMenu)getActivity()).refresh2(id);
        }

    }
}
