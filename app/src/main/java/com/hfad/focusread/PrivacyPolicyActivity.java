package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class PrivacyPolicyActivity extends AppCompatActivity {

    RecyclerView recyclerView;
   //RecyclerView.LayoutManager;
    //ArrayList<TextClass> myArrayList = new ArrayList<TextClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        findViewById(R.id.privacy_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrivacyPolicyActivity.this
                        ,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}