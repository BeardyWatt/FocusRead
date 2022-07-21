package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SessionFinishedActivity extends AppCompatActivity {
    Button logTheReadBtn;
    EditText endedOnET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_finished);

        logTheReadBtn = findViewById(R.id.log_read_btn);
        endedOnET = findViewById(R.id.ended_on_et);

        logTheReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SessionFinishedActivity.this,
                        ReadLoggedActivity.class);
                startActivity(intent);
            }
        });
    }
}