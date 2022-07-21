package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetupReadActivity extends AppCompatActivity {

    Button startReadBtn, viewStatsBtn;
    EditText startFromET, targetET;
    TextView bookInfoTxt;

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

        String title = intent.getStringExtra("TITLE");
        String author = intent.getStringExtra("AUTHOR");
        int pages = intent.getIntExtra("NOP", 1);
        String status = intent.getStringExtra("STATUS");
        int startPage = intent.getIntExtra("STARTPAGE", 1);


        bookInfoTxt.setText("Book: " + title + "\n" + "Author: " + author + "\n" + "No. of Pages: "  + pages + "\n" + "Status " + status );
        startFromET.setText(startPage);


        startReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newStartPage = Integer.parseInt(startFromET.getText().toString());
                int target = Integer.parseInt(targetET.getText().toString());

                Intent intent = new Intent(SetupReadActivity.this,ReadInProgressActivity.class);

                intent.putExtra("TITLE", title);
                intent.putExtra("AUTHOR", author);
                intent.putExtra("NOP", pages);
                intent.putExtra("STATUS", status);
                intent.putExtra("STARTPAGE", newStartPage);
                intent.putExtra("TARGET", target);

                startActivity(intent);
            }
        });

        viewStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetupReadActivity.this,BookDetailStatsActivity.class);
                startActivity(intent);
            }
        });
    }
}