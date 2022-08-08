package com.hfad.focusread;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
/** activity to add books to the book list taking user input storing the data into the Book class
 * sending to data to the book collection in firebase stored in a sub-collection of user**/

public class AddBookActivity extends BaseActivity {
    Button btnAddBook, btnHome;
    EditText bookTitleEt, authorNameEt, numberOfPagesEt;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        btnAddBook = findViewById(R.id.add_book_btn);
        btnHome = findViewById(R.id.return_home_btn);

        bookTitleEt = findViewById(R.id.book_title_input_et);
        authorNameEt = findViewById(R.id.book_author_input_et);
        numberOfPagesEt = findViewById(R.id.nop_input_et);
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
                Intent intent = new Intent(AddBookActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * method to add a Book object to the database
     **/
    private void InsertBookData() {
        String bookBookTitle = bookTitleEt.getText().toString();
        String bookAuthorName = authorNameEt.getText().toString();
        String nop = numberOfPagesEt.getText().toString();
        if(! validForm(bookBookTitle, bookAuthorName, nop)){
            return;
        }
        int bookNumberOfPages = Integer.parseInt(nop);
        DocumentReference bookRef = firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser()
                .getUid()).collection("books").document();
        String bookId = bookRef.getId();

        Book book = new Book(bookBookTitle, bookAuthorName, bookNumberOfPages, bookId);
        showProgressDialog("please wait while we add your book");
        bookRef.set(book).addOnSuccessListener(aVoid -> {
                    hideProgressDialog();
                    Intent intent = new Intent(AddBookActivity.this
                            , BookAddSuccessActivity.class);
                    intent.putExtra("TITLE", bookBookTitle);
                    intent.putExtra("AUTHOR", bookAuthorName);
                    intent.putExtra("NOP", bookNumberOfPages);
                    startActivity(intent);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Book was unsuccessfully added to the list"
                            , Toast.LENGTH_SHORT).show();
                    hideProgressDialog();
                });

    }
    /** method to ensure data input is valid **/
    private boolean validForm(String bookBookTitle, String bookAuthorName, String nop){


        boolean valid = true;
        if (TextUtils.isEmpty(bookBookTitle)) {
            bookTitleEt.setError("Book Title Required");
            valid = false;
        } else
            bookTitleEt.setError(null);
        if (TextUtils.isEmpty(bookAuthorName)) {
            authorNameEt.setError("Book Author Required");
            valid = false;
        } else
            authorNameEt.setError(null);
        if (TextUtils.isEmpty(nop)) {
            numberOfPagesEt.setError("Number of Pages Required");
            valid = false;
        } else
            numberOfPagesEt.setError(null);

        return valid;

    }
}