package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
/** Home Screen with intent buttons to navigate to further activities
 * users can log out from this screen bringing them back to the log in screen
 * if the user is logged in already they will be brought to this screen upon opening the app
 * **/
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
                Intent intent = new Intent(HomeActivity.this,SetReadReminderActivity
                        .class);
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