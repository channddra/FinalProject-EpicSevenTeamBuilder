package com.example.epicsevenherobuilder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HeroList extends Fragment implements HeroListRecyclerViewAdapter.OnNoteListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mHeroes = new ArrayList<>();
    private Repo repo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.hero_list_recycler_view,container,false);

        //getting database
        repo = new Repo(getActivity());
        recyclerView = view.findViewById(R.id.hero_list_recview);
        recyclerView.setHasFixedSize(true);
        //this is not necessary, however, the first version of the program didnt have database, so we set up a temporary
        //variable to input the data, and since changing it takes a long time, we just insert the database to said variable
        for(int i=0; i<repo.heroList.size(); i++){
            mHeroes.add(repo.heroList.get(i).img);
            mNames.add(repo.heroList.get(i).name);
        }
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        // the Adapter to show data, mNames for names, mHeroes for image, and implements onNoteListener in order to enable
        //clicking on heroes that we want to see details.
        adapter = new HeroListRecyclerViewAdapter(mNames,mHeroes,(HeroListMenu)getActivity(), this);
        recyclerView.setAdapter(adapter);





        return view;
    }
    @Override
    public void onNoteClick(int position) {

        ((HeroListMenu)getActivity()).setViewPager(position);


    }

}
