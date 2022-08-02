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
import java.util.logging.Logger;

public class StatsActivity extends AppCompatActivity {

    String title, author, bookId, time, status;
    int pages, pagesRead;
    boolean targetHit;
    Button returnHomeBtn;
    TextView bookStatDetailTxt;
    RecyclerView recyclerView;
    ArrayList<ReadSession> statList;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextView emptyTxt;
    ProgressBar progressBar;
    ListenerRegistration statListener;
    StatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        returnHomeBtn = findViewById(R.id.return_home_btn);
        bookStatDetailTxt = findViewById(R.id.book_info_txt);
        recyclerView = findViewById(R.id.stat_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        statList = new ArrayList<>();
        adapter = new StatAdapter(this, statList);
        recyclerView.setAdapter(adapter);
        emptyTxt = findViewById(R.id.stat_empty_view);
        progressBar = findViewById(R.id.progressBar);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();

        title = intent.getStringExtra("TITLE");
        author = intent.getStringExtra("AUTHOR");
        pages = intent.getIntExtra("NOP", 1);
        time = intent.getStringExtra("TIME");
        pagesRead = intent.getIntExtra("PAGESREAD", 1);
        targetHit = intent.getBooleanExtra("TARGETHIT", true);
        bookId = intent.getStringExtra("BOOKID");
        status = intent.getStringExtra("STATUS");

        bookStatDetailTxt.setText("Book: " + title + "\n" + "Author: " + author +
                "\n" + "No. of Pages: "  + pages + "\n" + "Status" + status);


        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this
                        ,HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        statList.clear();
        final Logger LOGGER = Logger.getLogger(StatsActivity.class.getName() );

        LOGGER.warning("HELLO THIS IS A TEST" + bookId);
        final CollectionReference collection = firebaseFirestore.collection("users")
                .document(firebaseAuth.getCurrentUser().getUid()).collection("books")
                .document(bookId).collection("read_logs");
        statListener = collection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(StatsActivity.this, "Loading data failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(value.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    emptyTxt.setVisibility(View.VISIBLE);
                }
                for(DocumentChange dc : value.getDocumentChanges()){
                    DocumentSnapshot documentSnapshot = dc.getDocument();
                    ReadSession readSession = documentSnapshot.toObject(ReadSession.class);
                    progressBar.setVisibility(View.GONE);
                    statList.add(readSession);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
