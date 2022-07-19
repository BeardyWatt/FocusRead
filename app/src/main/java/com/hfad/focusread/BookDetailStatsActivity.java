package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BookDetailStatsActivity extends AppCompatActivity {

    Button returnHomeBtn;
    TextView bookStatDetailTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_stats);

        returnHomeBtn = findViewById(R.id.return_home_btn);
        bookStatDetailTxt = findViewById(R.id.book_info_txt);

        Intent intent = getIntent();

        String title = intent.getStringExtra("TITLE");
        String author = intent.getStringExtra("AUTHOR");
        String pages = intent.getStringExtra("NOP");

        bookStatDetailTxt.setText("Book: " + title + "\n" + "Author: " + author + "\n" + "No. of Pages: "  + pages);


        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailStatsActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}