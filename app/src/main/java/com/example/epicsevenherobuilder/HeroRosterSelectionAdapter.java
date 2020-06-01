package com.example.epicsevenherobuilder;

import android.content.Context;
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

public class HeroRosterSelectionAdapter extends RecyclerView.Adapter<HeroRosterSelectionAdapter.myViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mHeroesNames;
    private ArrayList<String> mHeroes;
    private Context mContext;
    private HeroRosterSelectionAdapter.OnNoteListener mOnNoteListener;
    public interface OnNoteListener{
        void onNoteClick(int position);
    }

    public  class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CircleImageView heroes;
        public TextView heroesname;
        public RelativeLayout parentLayout;
        HeroRosterSelectionAdapter.OnNoteListener onNoteListener;
        public myViewHolder(@NonNull View itemView, HeroRosterSelectionAdapter.OnNoteListener onNoteListener) {
            super(itemView);
            heroes = itemView.findViewById(R.id.heroesselectclick);
            heroesname = itemView.findViewById(R.id.heroes_name_selectclick);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    HeroRosterSelectionAdapter(ArrayList<String> mHeroesNames, ArrayList<String> mHeroes, Context mContext, HeroRosterSelectionAdapter.OnNoteListener onNoteListener) {
        this.mHeroesNames = mHeroesNames;
        this.mHeroes = mHeroes;
        this.mContext = mContext;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull


    public HeroRosterSelectionAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_builder_fragment_select_hero_click,parent,false);



        HeroRosterSelectionAdapter.myViewHolder rut = new HeroRosterSelectionAdapter.myViewHolder(view, mOnNoteListener);
        return rut;
    }



    @Override
    public void onBindViewHolder(@NonNull HeroRosterSelectionAdapter.myViewHolder holder, final int position) {
       Glide.with(mContext)
                .asBitmap()
                .load(mHeroes.get(position))
                .into(holder.heroes);
        holder.heroesname.setText(mHeroesNames.get(position));

        // onclick view another page

    }

    @Override
    public int getItemCount() {
        return mHeroesNames.size();
    }
}
