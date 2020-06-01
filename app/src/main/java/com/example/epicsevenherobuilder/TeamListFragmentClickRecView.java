package com.example.epicsevenherobuilder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeamListFragmentClickRecView  extends RecyclerView.Adapter<TeamListFragmentClickRecView .myViewHolder>{
    private ArrayList<String> mHeroesNames;
    private ArrayList<String> mHeroes;
    private Context mContext;
    private View temp;
    private ArrayList<ArrayList<String>> mBuffs;
    private ArrayList<ArrayList<String>> mDebuffs;



    TeamListFragmentClickRecView(ArrayList<ArrayList<String>> mBuffs, ArrayList<ArrayList<String>> mDebuffs,ArrayList<String> mNames, ArrayList<String> mHeroes, TeamListMenu mContext) {
        this.mHeroesNames = mNames;
        this.mHeroes = mHeroes;
        this.mContext = mContext;
        this.mBuffs = mBuffs;
        this.mDebuffs = mDebuffs;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_fragment_click_singlelayout,parent,false);
        TeamListFragmentClickRecView.myViewHolder rut = new TeamListFragmentClickRecView.myViewHolder(view);

        temp = view;



        return rut;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
       Glide.with(mContext)
                .asBitmap()
                .load(mHeroes.get(position))
                .into(holder.heroes);
        holder.heroesname.setText(mHeroesNames.get(position));
        holder.Buffs .setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new GridLayoutManager(mContext, 3);
        holder.Buffs.setLayoutManager(manager);
        RecyclerView.Adapter adapter = new BuffDebuffAdapter(mBuffs.get(position), (TeamListMenu) mContext);
        holder.Buffs.setAdapter(adapter);
        //debuff

        holder.Debuffs.setHasFixedSize(true);
        manager = new GridLayoutManager(mContext, 3);
        holder.Debuffs.setLayoutManager(manager);
        adapter = new BuffDebuffAdapter(mDebuffs.get(position), (TeamListMenu) mContext);
        holder.Debuffs.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mHeroesNames.size();
    }


    public  class myViewHolder extends RecyclerView.ViewHolder  {
        public CircleImageView heroes;
        public TextView heroesname;
        public RelativeLayout parentLayout;
        public RecyclerView Buffs;
        public RecyclerView Debuffs;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            heroes = itemView.findViewById(R.id.teamlist1);
            heroesname = itemView.findViewById(R.id.textteamlist1);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            Buffs = itemView.findViewById(R.id.bufflist1);
            Debuffs = itemView.findViewById(R.id.debufflist1);

        }

    }

}
