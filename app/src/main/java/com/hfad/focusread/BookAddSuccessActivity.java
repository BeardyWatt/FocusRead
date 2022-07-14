package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookAddSuccessActivity extends AppCompatActivity {
    Button startReadBtn ,viewBookListBtn, addAnotherBookBtn;
    private static final String TAG = "BookAddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add_success);
        startReadBtn = findViewById(R.id.start_read_btn);
        viewBookListBtn = findViewById(R.id.view_book_list_btn);
        addAnotherBookBtn = findViewById(R.id.add_book_btn);
        TextView bookInfoTxt = findViewById(R.id.book_info_added);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("TITLE");
        String author = bundle.getString("AUTHOR");
        String pages = bundle.getString("PAGE");
        Log.d(TAG, "Title = " + title + "Author" + author + "Pages" + pages);

        bookInfoTxt.setText("Book: " + title + "\n" + "Author: " + author + "\n" + "No. of Pages: "  + pages);


        startReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAddSuccessActivity.this,SetupReadActivity.class);
                startActivity(intent);
            }
        });

        viewBookListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAddSuccessActivity.this,BookListActivity.class);
                startActivity(intent);
            }
        });


        addAnotherBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAddSuccessActivity.this,AddBookActivity.class);
                startActivity(intent);
            }
        });
    }
}