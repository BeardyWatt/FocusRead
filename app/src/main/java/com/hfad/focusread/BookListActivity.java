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

public class BookListActivity extends AppCompatActivity {
    Button addNewBookBtn;
    RecyclerView recyclerView;
    ArrayList<Book> bookList;
    MyAdapter adapter;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextView emptyTxt;
    ProgressBar progressBar;
    ListenerRegistration bookListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        addNewBookBtn = findViewById(R.id.add_book_btn);
        recyclerView = (RecyclerView) findViewById(R.id.stat_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookList = new ArrayList<Book>();
        adapter = new MyAdapter(this,bookList);
        recyclerView.setAdapter(adapter);
        emptyTxt = findViewById(R.id.stat_empty_view);
        progressBar = findViewById(R.id.progressBar);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        addNewBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookListActivity.this,AddBookActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        bookList.clear();
        final CollectionReference collection = firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid()).collection("books");
        bookListener = collection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null) {
                    Toast.makeText(BookListActivity.this, "Loading Data Failed ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(value.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    emptyTxt.setVisibility(View.VISIBLE);
                }
                for(DocumentChange dc:value.getDocumentChanges()){
                    DocumentSnapshot documentSnapshot = dc.getDocument();
                    Book book = documentSnapshot.toObject(Book.class);
                    bookList.add(book);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        bookListener.remove();
    }
}