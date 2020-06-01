package com.example.epicsevenherobuilder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeamListRecyclerViewAdapter extends RecyclerView.Adapter<TeamListRecyclerViewAdapter.myViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> TeamName;
    private ArrayList<String> mHeroes;
    private Context mContext;
    private TeamListRecyclerViewAdapter.OnNoteListener mOnNoteListener;


    public  class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CircleImageView heroes;
        public CircleImageView heroes2;
        public CircleImageView heroes3;
        public CircleImageView heroes4;
        public TextView heroesname;
        public RelativeLayout parentLayout;
        TeamListRecyclerViewAdapter.OnNoteListener onNoteListener;
        public myViewHolder(@NonNull View itemView, TeamListRecyclerViewAdapter.OnNoteListener onNoteListener) {
            super(itemView);
            heroes = itemView.findViewById(R.id.teamhero1);
            heroes2 = itemView.findViewById(R.id.teamhero2);
            heroes3 = itemView.findViewById(R.id.teamhero3);
            heroes4 = itemView.findViewById(R.id.teamhero4);
            heroesname = itemView.findViewById(R.id.teamname);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    TeamListRecyclerViewAdapter(ArrayList<String> mHeroesNames, ArrayList<String> mHeroes, TeamListMenu mContext, OnNoteListener onNoteListener) {
        this.TeamName = mHeroesNames;
        this.mHeroes = mHeroes;
        this.mContext = mContext;
        this.mOnNoteListener = onNoteListener;
    }





    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_fragment_listteam_layout,parent,false);
        TeamListRecyclerViewAdapter.myViewHolder rut = new TeamListRecyclerViewAdapter.myViewHolder(view, mOnNoteListener);
        return rut;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mHeroes.get(position*4+0))
                .into(holder.heroes);
        Glide.with(mContext)
                .asBitmap()
                .load(mHeroes.get(position*4+1))
                .into(holder.heroes2);
        Glide.with(mContext)
                .asBitmap()
                .load(mHeroes.get(position*4+2))
                .into(holder.heroes3);
        Glide.with(mContext)
                .asBitmap()
                .load(mHeroes.get(position*4+3))
                .into(holder.heroes4);
        holder.heroesname.setText(TeamName.get(position));


    }

    @Override
    public int getItemCount() {
        return TeamName.size() ;
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }


}
