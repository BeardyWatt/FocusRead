package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;

public class BookDetailStatsActivity extends AppCompatActivity {

    Button returnHomeBtn;
    TextView bookStatDetailTxt;
    RecyclerView recyclerView;
    ArrayList<ReadSession> statList;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextView emptyTxt;
    ProgressBar progressBar;
    ListenerRegistration statListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_stats);

        returnHomeBtn = findViewById(R.id.return_home_btn);
        bookStatDetailTxt = findViewById(R.id.book_info_txt);
        recyclerView = (RecyclerView) findViewById(R.id.stat_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        statList = new ArrayList<ReadSession>();
        //adapter = new MyAdapter(this, statList);
        emptyTxt = findViewById(R.id.stat_empty_view);
        progressBar = findViewById(R.id.progressBar);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();

        String title = intent.getStringExtra("TITLE");
        String author = intent.getStringExtra("AUTHOR");
        int pages = intent.getIntExtra("NOP", 1);

        bookStatDetailTxt.setText("Book: " + title + "\n" + "Author: " + author +
                "\n" + "No. of Pages: "  + pages);


        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailStatsActivity.this
                        ,HomeActivity.class);
                startActivity(intent);
            }
        });


    }

}
