package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputLayout firstNameInputLayout = findViewById(R.id.fname_input_layout);
        TextInputLayout surnameInputLayout = findViewById(R.id.sname_input_layout);
        TextInputLayout emailInputLayout = findViewById(R.id.emails_input_layout);
        TextInputLayout passwordInputLayout = findViewById(R.id.passwords_input_layout);
        TextInputLayout cPasswordInputLayout = findViewById(R.id.cpasswords_input_layout);
    }
}