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

public class HeroListRecyclerViewAdapter extends RecyclerView.Adapter<HeroListRecyclerViewAdapter.myViewHolder>{

    private ArrayList<String> mHeroesNames;
    private ArrayList<String> mHeroes;
    private Context mContext;
    private OnNoteListener mOnNoteListener;
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
    public HeroListRecyclerViewAdapter(ArrayList<String> mNames, ArrayList<String> mHeroes, HeroListMenu mContext, OnNoteListener onNoteListener) {
        this.mHeroesNames = mNames;
        this.mHeroes = mHeroes;
        this.mContext = mContext;
        this.mOnNoteListener = onNoteListener;
    }


    public  class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CircleImageView heroes;
        public TextView heroesname;
        public RelativeLayout parentLayout;
        OnNoteListener onNoteListener;
        public myViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            heroes = itemView.findViewById(R.id.actuallyselected);
            heroesname = itemView.findViewById(R.id.actuallyselected_name2);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    @NonNull


    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_list_recycler_view_single,parent,false);
        myViewHolder  rut = new myViewHolder(view, mOnNoteListener);
        return rut;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {

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

