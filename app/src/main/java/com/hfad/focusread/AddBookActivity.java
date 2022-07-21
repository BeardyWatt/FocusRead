package com.hfad.focusread;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddBookActivity extends BaseActivity {
    Button btnAddBook, btnHome;
    EditText bookTitle, authorName, numberOfPages;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        btnAddBook = findViewById(R.id.add_book_btn);
        btnHome= findViewById(R.id.return_home_btn);

        bookTitle = findViewById(R.id.book_title_input_et);
        authorName = findViewById(R.id.book_author_input_et);
        numberOfPages = findViewById(R.id.nop_input_et);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertBookData();
            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBookActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void InsertBookData() {
        String bookBookTitle = bookTitle.getText().toString();
        String bookAuthorName = authorName.getText().toString();
        int bookNumberOfPages = Integer.parseInt(numberOfPages.getText().toString());

        Book book = new Book(bookBookTitle, bookAuthorName, bookNumberOfPages);
        showProgressDialog("please wait while we add your book");
        firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser()
                        .getUid())
                    .collection("books").add(book).addOnSuccessListener(aVoid -> {
                        hideProgressDialog();
                        Intent intent = new Intent(AddBookActivity.this
                                ,BookAddSuccessActivity.class);
                        intent.putExtra("TITLE",bookBookTitle);
                        intent.putExtra("AUTHOR",bookAuthorName);
                        intent.putExtra("NOP",bookNumberOfPages);
                        startActivity(intent);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Book was unsuccessfully added to the list"
                                , Toast.LENGTH_SHORT).show();
                        hideProgressDialog();
                    });
    }
}