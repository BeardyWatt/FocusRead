package com.hfad.focusread;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReadLoggedActivity extends AppCompatActivity {

    Button viewStatsBtn, setReminderBtn, homeBtn;
    private String title, author, status, time;
    private  int pages, target, startPage, endPage, pageRead;
    boolean targetHit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_logged);
        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        author = intent.getStringExtra("AUTHOR");
        pages = intent.getIntExtra("NOP", 1);
        status = intent.getStringExtra("STATUS");
        target = intent.getIntExtra("TARGET", 1);
        startPage = intent.getIntExtra("STARTPAGE", 1);
        time = intent.getStringExtra("TIME");
        endPage = intent.getIntExtra("ENDPAGE", 1);

        pageRead = endPage - startPage;
        if (endPage >= target){
            targetHit = true;
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
                        .this,StatsActivity.class);
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