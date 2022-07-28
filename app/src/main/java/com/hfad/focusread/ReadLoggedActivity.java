package com.hfad.focusread;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReadLoggedActivity extends AppCompatActivity {

    Button viewStatsBtn, setReminderBtn, homeBtn;
    private String title, author, status, time;
    private  int pages, target, startPage, endPage, pagesRead;
    boolean targetHit;
    TextView pagesReadTxt, readingTimeTxt, targetHitTxt;
    ImageView starImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_logged);
        Intent intent = getIntent();

        time = intent.getStringExtra("TIME");
        pagesRead = intent.getIntExtra("PAGESREAD", 1);
        targetHit = intent.getBooleanExtra("TARGETHIT", true);


        pagesReadTxt = findViewById(R.id.page_read_txt);
        readingTimeTxt = findViewById(R.id.reading_time_txt);
        targetHitTxt = findViewById(R.id.target_hit_txt);
        starImg = findViewById(R.id.target_img);

        pagesReadTxt.setText("" + pagesRead);

        readingTimeTxt.setText(time);


        if (targetHit == true){
            targetHitTxt.setText(R.string.target_hit_txt);
        }else {
            targetHitTxt.setText(R.string.target_not_hit_txt);
            starImg.setImageResource(R.drawable.target_not_hit_foreground);
        }



        setReminderBtn = findViewById(R.id.set_read_reminder_btn);
        viewStatsBtn = findViewById(R.id.view_stats_btn);
        homeBtn = findViewById(R.id.return_home_btn);


        setReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadLoggedActivity.this
                        ,SetReadReminderActivity.class);
                startActivity(intent);
            }
        });

        viewStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadLoggedActivity
                        .this,BookDetailStatsActivity.class);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadLoggedActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

}