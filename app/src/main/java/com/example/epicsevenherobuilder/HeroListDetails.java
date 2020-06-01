package com.example.epicsevenherobuilder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HeroListDetails extends Fragment {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mHeroes = new ArrayList<>();
    private ArrayList<ArrayList<String>> mBuffs = new ArrayList<>();
    private ArrayList<ArrayList<String>> mDebuffs = new ArrayList<>();
    Repo repo;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.hero_list_details,container,false);
        //database data
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
        //
        int pos = 1;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pos = bundle.getInt("Pos", 1);
        }

        ImageView imgv = view.findViewById(R.id.actuallyselected);
        Glide.with(getActivity())
                .asBitmap()
                .load(mHeroes.get(pos))
                .into(imgv);
        TextView txt = view.findViewById(R.id.heroes_name2);
        txt.setText(mNames.get(pos));
        // here we have 2 other recycler view to show buff and debuff list,

        //buff

        recyclerView = view.findViewById(R.id.buffView);
        recyclerView.setHasFixedSize(true);
        manager =new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager);

        adapter = new BuffDebuffAdapter(mBuffs.get(pos),(HeroListMenu)getActivity());
        recyclerView.setAdapter(adapter);
        //debuff
        recyclerView = view.findViewById(R.id.debuffview);
        recyclerView.setHasFixedSize(true);
        manager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager);
        adapter = new BuffDebuffAdapter(mDebuffs.get(pos),(HeroListMenu)getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
