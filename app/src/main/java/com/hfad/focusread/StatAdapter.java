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
import java.util.logging.Logger;


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

        TextView date, readingTime,pagesRead;
        ImageView targetHit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_txt);
            readingTime = itemView.findViewById(R.id.log_reading_time_txt);
            pagesRead = itemView.findViewById(R.id.log_pages_read_txt);
            targetHit = itemView.findViewById(R.id.stat_target_hit_img);
            itemView.setOnClickListener(this);
        }

        void bindTo(ReadSession currentSession) {
            final Logger LOGGER = Logger.getLogger(StatAdapter.class.getName() );

            //LOGGER.warning("HELLO THIS IS A TEST " + currentSession.getPagesRead());
            date.setText(currentSession.getDate());
            //LOGGER.warning("HELLO THIS IS A TEST " + date.getText());
            readingTime.setText(currentSession.getTime());
            //LOGGER.warning("HELLO THIS IS A TEST " + readingTime.getText());
            pagesRead.setText("" + currentSession.getPagesRead());
            if(currentSession.isTargetHit() == false){
                targetHit.setImageResource(R.drawable.target_not_hit_foreground);
            }


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
