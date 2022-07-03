package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputLayout firstNameInputLayout = findViewById(R.id.first_name_input_layout);
        TextInputLayout surnameInputLayout = findViewById(R.id.surname_input_layout);
        TextInputLayout emailInputLayout = findViewById(R.id.emails_input_layout);
        TextInputLayout passwordInputLayout = findViewById(R.id.passwords_input_layout);
        TextInputLayout cPasswordInputLayout = findViewById(R.id.confirm_passwords_input_layout);


        Button regBtn = findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,ConfirmRegDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}