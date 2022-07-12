package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookAddSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add_success);

        Button startReadBtn = findViewById(R.id.start_read_btn);
        startReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAddSuccessActivity.this,SetupReadActivity.class);
                startActivity(intent);
            }
        });

        Button viewBookListBtn = findViewById(R.id.view_book_list_btn);
        viewBookListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAddSuccessActivity.this,BookListActivity.class);
                startActivity(intent);
            }
        });

        Button addAnotherBookBtn = findViewById(R.id.add_book_btn);
        addAnotherBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAddSuccessActivity.this,AddBookActivity.class);
                startActivity(intent);
            }
        });
    }
}