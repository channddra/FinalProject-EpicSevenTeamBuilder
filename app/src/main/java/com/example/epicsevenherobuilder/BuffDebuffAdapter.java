package com.example.epicsevenherobuilder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BuffDebuffAdapter extends RecyclerView.Adapter<BuffDebuffAdapter.myViewHolder> {

    private ArrayList<String> Debuff;
    private Context mContext;

    public BuffDebuffAdapter(ArrayList<String> Debuff, HeroListMenu mContext ) {
        this.Debuff = Debuff;
        this.mContext = mContext;
    }
    public BuffDebuffAdapter(ArrayList<String> Debuff, TeamBuilderMenu mContext ) {
        this.Debuff = Debuff;
        this.mContext = mContext;
    }

    public BuffDebuffAdapter(ArrayList<String> Debuff, TeamListMenu mContext) {
        this.Debuff = Debuff;
        this.mContext = mContext;
    }
    public BuffDebuffAdapter(ArrayList<String> Debuff, Context mContext) {
        this.Debuff = Debuff;
        this.mContext = mContext;
    }

    public  class myViewHolder extends RecyclerView.ViewHolder  {
        public CircleImageView debuff;
        public RelativeLayout parentLayout;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            debuff = itemView.findViewById(R.id.buffdebuff);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }

    }

    @NonNull


    public BuffDebuffAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buff_and_debuff_layout,parent,false);
        BuffDebuffAdapter.myViewHolder rut = new BuffDebuffAdapter.myViewHolder(view);
        return rut;
    }

    @Override
    public void onBindViewHolder(@NonNull BuffDebuffAdapter.myViewHolder holder, final int position) {
        Log.d("eeee", "onBindViewHolder: " +Debuff.get(position));

        Glide.with(mContext)
                .asBitmap()
                .load(Debuff.get(position))
                .into(holder.debuff);
        // onclick view another page

    }

    @Override
    public int getItemCount() {
        return Debuff.size();
    }

}
