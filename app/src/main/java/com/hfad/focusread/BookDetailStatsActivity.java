package com.hfad.focusread;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BookDetailStatsActivity extends AppCompatActivity {

    Button returnHomeBtn;
    TextView bookStatDetailTxt;
    RecyclerView recyclerView;
    ArrayList<Statistic> statList;
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
        recyclerView = (RecyclerView) findViewById(R.id.stat_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        statList = new ArrayList<Statistic>();
        emptyTxt = findViewById(R.id.stat_empty_view);
        progressBar = findViewById(R.id.progressBar);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

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
    @Override
    protected void onStart() {
        super.onStart();
        statList.clear();
        final CollectionReference collection = firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid()).collection("books");
        statListener = collection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null) {
                    Toast.makeText(BookDetailStatsActivity.this, "Loading Data Failed ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(value.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    emptyTxt.setVisibility(View.VISIBLE);
                }
                for(DocumentChange dc:value.getDocumentChanges()){
                    DocumentSnapshot documentSnapshot = dc.getDocument();
                    Statistic stat = documentSnapshot.toObject(Statistic.class);
                    statList.add(stat);
                    //adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        statListener.remove();
    }
}