package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmRegDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ConfirmActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim_reg_details);
        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        TextView emailText = findViewById(R.id.user_reg_email);
        emailText.setText(email);

        Button goToLogin = findViewById(R.id.go_to_login);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmRegDetailsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}