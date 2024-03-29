package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * Setting up the read from this activity can be done by setting the start page, if this is the
 * first read of this book the start page will default as page 1
 * if you are returning the default page will be the page you ended on in the previous session.
 * users will also have to provide their target end page, if left blank the default will be the
 * end page of the book.
 * **/
public class SetupReadActivity extends AppCompatActivity {

    Button startReadBtn, viewStatsBtn;
    EditText startFromET, targetET;
    TextView bookInfoTxt;
    String bookId, title, author, status;
    int pages, startPage;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_read);

        startReadBtn = findViewById(R.id.start_read_btn);
        viewStatsBtn = findViewById(R.id.view_stats_btn);
        startFromET = findViewById(R.id.start_from_et);
        targetET = findViewById(R.id.target_et);

        bookInfoTxt = findViewById(R.id.start_book_info_added_txt);

        Intent intent = getIntent();

        title = intent.getStringExtra("TITLE");
        author = intent.getStringExtra("AUTHOR");
        pages = intent.getIntExtra("NOP", 1);
        status = intent.getStringExtra("STATUS");
        startPage = intent.getIntExtra("STARTPAGE", 1);
        bookId = intent.getStringExtra("BOOKID");

        bookInfoTxt.setText("Book: " + title + "\n" + "Author: " + author + "\n" + "No. of Pages: "
                + pages);
        startFromET.setText("" + startPage);


        startReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newStartPage = Integer.parseInt(startFromET.getText().toString());
                String targetStr = targetET.getText().toString();
                int target = pages;
                if (!TextUtils.isEmpty(targetStr)) {
                    target = Integer.parseInt(targetStr);
                }


                Intent intent = new Intent(SetupReadActivity.this,
                        ReadInProgressActivity.class);

                intent.putExtra("TITLE", title);
                intent.putExtra("AUTHOR", author);
                intent.putExtra("NOP", pages);
                intent.putExtra("STATUS", status);
                intent.putExtra("STARTPAGE", newStartPage);
                intent.putExtra("TARGET", target);
                intent.putExtra("BOOKID", bookId);

                startActivity(intent);
            }
        });

        viewStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetupReadActivity.this,
                        StatsActivity.class);

                intent.putExtra("BOOKID",bookId);
                intent.putExtra("TITLE", title);
                intent.putExtra("AUTHOR", author);
                intent.putExtra("NOP", pages);
                intent.putExtra("STATUS", status);

                startActivity(intent);
            }
        });
    }

}