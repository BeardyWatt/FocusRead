package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button logoutBtn, viewBookListBtn, addNewBookBtn, readReminderBtn, viewStatsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewBookListBtn = findViewById(R.id.view_book_list_btn);
        addNewBookBtn = findViewById(R.id.add_book_btn);
        readReminderBtn = findViewById(R.id.set_read_reminder_btn);
        viewStatsBtn = findViewById(R.id.view_stats_btn);
        logoutBtn = findViewById(R.id.logout_btn);

        viewBookListBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,BookListActivity.class);
                startActivity(intent);
           }
        });

        addNewBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,AddBookActivity.class);
                startActivity(intent);
            }
        });

        readReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SetReminderActivity.class);
                startActivity(intent);
            }
        });

        viewStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,StatsActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}