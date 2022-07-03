package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button logoutBtn = findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        Button viewBookListBtn = findViewById(R.id.view_book_list_btn);
        viewBookListBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,BookListActivity.class);
                startActivity(intent);
           }
        });

        Button addNewBookBtn = findViewById(R.id.add_another_book_to_list_btn);
        addNewBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,AddBookActivity.class);
                startActivity(intent);
            }
        });

        Button readReminderBtn = findViewById(R.id.set_read_reminder_btn);
        readReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SetReminderActivity.class);
                startActivity(intent);
            }
        });

        Button viewStatsBtn = findViewById(R.id.view_stats_btn);
        viewStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,StatsActivity.class);
                startActivity(intent);
            }
        });


    }
}