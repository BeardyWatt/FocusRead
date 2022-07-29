package com.hfad.focusread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SessionFinishedActivity extends BaseActivity {
    Button logTheReadBtn;
    EditText endedOnET;
    private String title, author, status, time, bookId;

    private  int pages, target, startPage, endPage;
    private TextView startPageTxt, targetTxt;
    private  boolean targetHit = true;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_finished);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        author = intent.getStringExtra("AUTHOR");
        pages = intent.getIntExtra("NOP", 1);
        status = intent.getStringExtra("STATUS");
        target = intent.getIntExtra("TARGET", 1);
        startPage = intent.getIntExtra("STARTPAGE", 1);
        time = intent.getStringExtra("TIME");
        bookId = intent.getStringExtra("BOOKID");

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
                    endPage = Integer.parseInt(endPageStr);
                    //save read log to database.

                    insertReadSession();
                    //update start page to end page in database.
                }

            }
        });
    }
    /**method to add current session to the database**/
    private void insertReadSession() {
        int pagesRead = endPage - startPage;
        Toast.makeText(this, "end page : " + endPage, Toast.LENGTH_LONG).show();

       Toast.makeText(this, "Pages Read : " + pagesRead, Toast.LENGTH_SHORT).show();
        if (endPage < target){
            targetHit = false;
        }
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.UK).format(new Date());


        ReadSession readSession = new ReadSession(pagesRead,targetHit, currentDate, time);
        showProgressDialog("please wait while we add your Reading Session");
        firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser()
                        .getUid())
                .collection("books").document(bookId).collection("read_logs")
                .add(readSession).addOnSuccessListener(aVoid -> {
                    hideProgressDialog();

                    //update start page - to end page of previous session.
                    DocumentReference startPageRef = firebaseFirestore.collection("users").
                            document(firebaseAuth.getCurrentUser().getUid()).
                            collection("books").document(bookId);
                    startPageRef.update("startPage", endPage + 1)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(SessionFinishedActivity.this, "read session logged successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SessionFinishedActivity.this, "read session failed" + e, Toast.LENGTH_SHORT).show();
                                }
                            });

                    Intent intent = new Intent(SessionFinishedActivity.this
                            ,ReadLoggedActivity.class);

                    intent.putExtra("TITLE", title);
                    intent.putExtra("AUTHOR", author);
                    intent.putExtra("NOP", pages);
                    intent.putExtra("TIME",time);
                    intent.putExtra("PAGESREAD",pagesRead);
                    intent.putExtra("TARGETHIT", targetHit);
                    intent.putExtra("BOOKID", bookId);
                    startActivity(intent);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Reading Session was unsuccessfully added to the Collection"
                            , Toast.LENGTH_SHORT).show();
                    hideProgressDialog();
                });


    }
}