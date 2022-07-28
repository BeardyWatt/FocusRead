package com.hfad.focusread;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class StatAdapter extends RecyclerView.Adapter<StatAdapter.ViewHolder> {

    Context context;
    ArrayList<ReadSession> statList;

    public StatAdapter(Context context, ArrayList<ReadSession> statList) {
        this.context = context;
        this.statList = statList;
    }


    @NonNull
    @Override
    public StatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.stat_card, parent,
                false));
    }


 @Override
    public void onBindViewHolder(@NonNull StatAdapter.ViewHolder holder, int position) {
        ReadSession currentSession = statList.get(position);
        holder.bindTo(currentSession);
    }


    @Override
    public int getItemCount() {
        return statList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView date, readingTime,pagesRead,targetHit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date_txt);
            readingTime = (TextView) itemView.findViewById(R.id.reading_time_txt);
            pagesRead = (TextView) itemView.findViewById(R.id.pages_read_txt);
            //targetHit = (ImageView) itemView.findViewById(R.id.stat_target_hit_img);
            itemView.setOnClickListener(this);
        }

        void bindTo(ReadSession currentSession) {
            date.setText(currentSession.getDate());
            readingTime.setText(currentSession.getTime());
            pagesRead.setText(currentSession.getPagesRead());
            //targetHit.setBoolean(currentSession.isTargetHit())
        }

        @Override
        public void onClick(View v) {
            ReadSession currentSession = statList.get(getAdapterPosition());
            Intent intent = new Intent(context,ReadLoggedActivity.class);
            intent.putExtra("DATE", currentSession.getDate());
            intent.putExtra("READTIME", currentSession.getTime());
            intent.putExtra("PAGESREAD",currentSession.getPagesRead());
            intent.putExtra("TARGETHIT", currentSession.isTargetHit());
            context.startActivity(intent);
        }
    }
}
