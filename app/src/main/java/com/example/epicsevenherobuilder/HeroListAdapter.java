package com.example.epicsevenherobuilder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HeroListAdapter extends RecyclerView.Adapter<HeroListAdapter.myViewHolder> {
    private ArrayList<String> mHeroesNames;
    private ArrayList<String> mHeroes;
    private Context mContext;
    private View temp;
    private OnNoteListener mOnNoteListener;
    private ArrayList<ArrayList<String>> mBuffs;
    private ArrayList<ArrayList<String>> mDebuffs;
    HeroListAdapter(ArrayList<ArrayList<String>> mBuffs, ArrayList<ArrayList<String>> mDebuffs, ArrayList<String> mNames, ArrayList<String> mHeroes, TeamListMenu mContext, OnNoteListener onNoteListener) {
        this.mHeroesNames = mNames;
        this.mHeroes = mHeroes;
        this.mContext = mContext;
        this.mOnNoteListener = onNoteListener;
        this.mBuffs = mBuffs;
        this.mDebuffs = mDebuffs;
    }



    HeroListAdapter(ArrayList<ArrayList<String>> mBuffs, ArrayList<ArrayList<String>> mDebuffs, ArrayList<String> mNames, ArrayList<String> mHeroes, TeamBuilderMenu mContext, OnNoteListener onNoteListener) {
        this.mHeroesNames = mNames;
        this.mHeroes = mHeroes;
        this.mContext = mContext;
        this.mOnNoteListener = onNoteListener;
        this.mBuffs = mBuffs;
        this.mDebuffs = mDebuffs;
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
    @NonNull
    @Override

    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_builder_fragment_selection_singlelayout,parent,false);
        HeroListAdapter.myViewHolder rut = new myViewHolder(view, mOnNoteListener);

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
        RecyclerView.LayoutManager manager = new GridLayoutManager(mContext,3);
        holder.Buffs.setLayoutManager(manager);
        RecyclerView.Adapter adapter = new BuffDebuffAdapter(mBuffs.get(position), mContext);
        holder.Buffs.setAdapter(adapter);
        //debuff

        holder.Debuffs.setHasFixedSize(true);
        manager = new GridLayoutManager(mContext,3);
        holder.Debuffs.setLayoutManager(manager);
        adapter = new BuffDebuffAdapter(mDebuffs.get(position),  mContext);
        holder.Debuffs.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return mHeroesNames.size();
    }


    public  class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CircleImageView heroes;
        public TextView heroesname;
        public RelativeLayout parentLayout;
        public RecyclerView Buffs;
        public RecyclerView Debuffs;
        OnNoteListener onNoteListener;
        public myViewHolder(@NonNull View itemView, HeroListAdapter.OnNoteListener onNoteListener) {
            super(itemView);
            heroes = itemView.findViewById(R.id.selectteamlist1);
            heroesname = itemView.findViewById(R.id.selecttextteamlist1);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            Buffs = itemView.findViewById(R.id.selectbufflist1);
            Debuffs = itemView.findViewById(R.id.selectdebufflist1);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
}
