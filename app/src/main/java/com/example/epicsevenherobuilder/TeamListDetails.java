package com.example.epicsevenherobuilder;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.epicsevenherobuilder.Database.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamListDetails extends Fragment implements TeamListRecyclerViewAdapter.OnNoteListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mHeroes = new ArrayList<>();
    private ArrayList<String> TeamName = new ArrayList<>();
    private List<Team> teams;
    private Repo repo;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_list_fragment_details,container,false);
        recyclerView = view.findViewById(R.id.teamListRecView);
        recyclerView.setHasFixedSize(true);
        //getting all the team we created so far in database
        repo = new Repo(getActivity());
        new LoadTeamTask().execute();

        return view;
    }


    public void prepareRecycler(ArrayList<String> TeamName, ArrayList<String> mHeroes){
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        adapter = new TeamListRecyclerViewAdapter(TeamName,mHeroes,(TeamListMenu)getActivity(), this);

        recyclerView.setAdapter(adapter);
    }

    class LoadTeamTask extends AsyncTask<Void, Void, Void>{
        Repo repo;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            repo=new Repo(getActivity());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            teams=repo.getAllTeamsTask();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for(int i=0; i<teams.size(); i++){
                ((TeamListMenu)getActivity()).team.add(teams.get(i))  ;
                TeamName.add(teams.get(i).name);
                mHeroes.add(repo.heroList.get(teams.get(i).hero1).img);
                mNames.add(repo.heroList.get(teams.get(i).hero1).name);
                mHeroes.add(repo.heroList.get(teams.get(i).hero2).img);
                 mNames.add(repo.heroList.get(teams.get(i).hero2).name);
                mHeroes.add(repo.heroList.get(teams.get(i).hero3).img);
                mNames.add(repo.heroList.get(teams.get(i).hero3).name);
                mHeroes.add(repo.heroList.get(teams.get(i).hero4).img);
                mNames.add(repo.heroList.get(teams.get(i).hero4).name);
            }

            prepareRecycler(TeamName, mHeroes);
        }
    }

    @Override
    public void onNoteClick(int position) {
        (
                //when clicked get all the hero details
                (TeamListMenu)getActivity()).setViewPager(position);
    }
}
