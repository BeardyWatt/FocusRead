package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadLoggedActivity extends AppCompatActivity {

    Button viewStatsBtn, setReminderBtn, homeBtn;
    private String title, author, status, time, bookId;
    private  int pages, target, startPage, endPage, pagesRead;
    boolean targetHit;
    TextView pagesReadTxt, readingTimeTxt, targetHitTxt;
    ImageView starImg;

    /**
     * details from the current book have been pulled and will be shown in a TextView
     * along with the stats from the current read
     * **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_logged);
        Intent intent = getIntent();

        title = intent.getStringExtra("TITLE");
        author = intent.getStringExtra("AUTHOR");
        pages = intent.getIntExtra("NOP", 1);
        time = intent.getStringExtra("TIME");
        pagesRead = intent.getIntExtra("PAGESREAD", 1);
        targetHit = intent.getBooleanExtra("TARGETHIT", true);
        bookId = intent.getStringExtra("BOOKID");
        status = intent.getStringExtra("STATUS");


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

        /**
         * book data will be carried over to the next Activity
         * **/
        viewStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadLoggedActivity
                        .this, StatsActivity.class);
                intent.putExtra("TITLE", title);
                intent.putExtra("AUTHOR", author);
                intent.putExtra("NOP", pages);
                intent.putExtra("TIME",time);
                intent.putExtra("PAGESREAD",pagesRead);
                intent.putExtra("TARGETHIT", targetHit);
                intent.putExtra("BOOKID", bookId);
                intent.putExtra("STATUS", status);
                startActivity(intent);
            }
        });

        /**
         * return to the home screen
         * **/
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadLoggedActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

}