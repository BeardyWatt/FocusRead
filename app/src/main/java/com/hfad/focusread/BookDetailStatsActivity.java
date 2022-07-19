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
        bookStatDetailTxt = findViewById(R.id.book_details_txt);

        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailStatsActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}