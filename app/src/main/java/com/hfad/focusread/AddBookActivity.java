package com.hfad.focusread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookActivity extends AppCompatActivity {
    Button btnAddBook, btnHome;
    EditText bookTitle, authorName, numberOfPages;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        btnAddBook = findViewById(R.id.add_book_btn);
        btnHome= findViewById(R.id.return_home_btn);
        bookTitle = findViewById(R.id.book_title_input_edit);
        authorName = findViewById(R.id.book_author_input_edit);
        numberOfPages = findViewById(R.id.nop_input_edit);
        databaseUsers = FirebaseDatabase.getInstance().getReference();


        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertBookData();
                Intent intent = new Intent(AddBookActivity.this,BookAddSuccessActivity.class);
                startActivity(intent);
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
        String bookNumberOfPages = numberOfPages.getText().toString();
        String id = databaseUsers.push().getKey();

        Book book = new Book(bookBookTitle, bookAuthorName, bookNumberOfPages);
        databaseUsers.child("Book").child(id).setValue(book)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(AddBookActivity.this, "Book Details Added", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}