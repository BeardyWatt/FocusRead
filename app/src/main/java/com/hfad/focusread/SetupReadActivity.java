package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetupReadActivity extends AppCompatActivity {

    Button startReadBtn, viewStatsBtn;
    EditText startFromET, targetET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_read);

        startReadBtn = findViewById(R.id.start_read_btn);
        viewStatsBtn = findViewById(R.id.view_stats_btn);
        startFromET = findViewById(R.id.start_from_edit_text);
        targetET = findViewById(R.id.target_edit_text);


        startReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetupReadActivity.this,ReadInProgressActivity.class);
                startActivity(intent);
            }
        });

        viewStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetupReadActivity.this,StatsActivity.class);
                startActivity(intent);
            }
        });
    }
}