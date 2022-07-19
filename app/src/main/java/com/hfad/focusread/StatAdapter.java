package com.hfad.focusread;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*
public class StatAdapter extends RecyclerView.Adapter<StatAdapter.ViewHolder> {

    Context context;
    ArrayList<Statistic> statList;

    public StatAdapter(Context context, ArrayList<Statistic> statList) {
        this.context = context;
        this.statList = statList;
    }


    @NonNull
    @Override
    public StatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.stat_card, parent, false));
    }

   */
/* @Override
    public void onBindViewHolder(@NonNull StatAdapter.ViewHolder holder, int position) {
        Statistic currentStat = statList.get(position);
        holder.bindTo(currentStat);
    }*//*


    @Override
    public int getItemCount() {
        return statList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView pageStartFrom, target, targetHit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pageStartFrom = (TextView) itemView.findViewById(R.id.);
            target = (TextView) itemView.findViewById(R.id.);
            targetHit = (TextView) itemView.findViewById(R.id.);
            itemView.setOnClickListener(this);
        }

        void bindTo(Book currentBook) {
            pageStartFrom.setText(currentBook.getBookTitle());
            target.setText(currentBook.getBookAuthor());
            targetHit.setText(currentBook.getNumberOfPages());
        }

        @Override
        public void onClick(View v) {

        }
    }
}*/
