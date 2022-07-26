package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SessionFinishedActivity extends AppCompatActivity {
    Button logTheReadBtn;
    EditText endedOnET;
    private String title, author, status, time;
    private  int pages, target, startPage, endPage;
    private TextView startPageTxt, targetTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_finished);
        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        author = intent.getStringExtra("AUTHOR");
        pages = intent.getIntExtra("NOP", 1);
        status = intent.getStringExtra("STATUS");
        target = intent.getIntExtra("TARGET", 1);
        startPage = intent.getIntExtra("STARTPAGE", 1);
        time = intent.getStringExtra("TIME");

        targetTxt = findViewById(R.id.target_txt);
        startPageTxt = findViewById(R.id.start_page_txt);
        endedOnET = findViewById(R.id.end_page_et);
        targetTxt.setText("" + target);
        startPageTxt.setText("" + startPage);


        logTheReadBtn = findViewById(R.id.log_read_btn);

        logTheReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String endPageStr = endedOnET.getText().toString();
                if(TextUtils.isEmpty(endPageStr)) {
                    Toast.makeText(SessionFinishedActivity.this, "Please Enter An End Page", Toast.LENGTH_LONG).show();
                }else{
                    int endPage = Integer.parseInt(endPageStr);
                    Intent intent = new Intent(SessionFinishedActivity.this,
                            ReadLoggedActivity.class);
                    intent.putExtra("TITLE", title);
                    intent.putExtra("AUTHOR", author);
                    intent.putExtra("NOP", pages);
                    intent.putExtra("STATUS", status);
                    intent.putExtra("STARTPAGE", startPage);
                    intent.putExtra("TARGET", target);
                    intent.putExtra("TIME" , time);
                    intent.putExtra("ENDPAGE" ,endPage);
                    startActivity(intent);

                }

            }
        });
    }
}