package com.hfad.focusread;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "loginActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;

    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.login_btn);
        String emailAddress = "user@example.com";
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = firebaseAuth -> {
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            if (currentUser != null && currentUser.isEmailVerified()){
                // user is already signed in
                updateUI();
            }
            else{
                // user not logged in
                manageLogin();
            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }
    private void manageLogin() {
        emailEditText = findViewById(R.id.email_input_et);
        passwordEditText = findViewById(R.id.password_input_et);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailPasswordLogin(emailEditText.getText().toString(), passwordEditText
                        .getText().toString());

            }
        });
        findViewById(R.id.reg_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.forgot_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this
                        ,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void emailPasswordLogin(String email, String password) {
        if(! validateForm(email, password)){
            return;
        }
        showProgressDialog("Logging in");
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,task -> {
            if  (task.isSuccessful()){
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null && currentUser.isEmailVerified()){
                    updateUI();
                }
                else{
                    Toast.makeText(LoginActivity.this
                            , "Email is not verified yet, check your email "
                                    + currentUser.getEmail(), Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(this, "Authentication failed"
                        , Toast.LENGTH_SHORT).show();
            }
            hideProgressDialog();
        });
    }
    private boolean validateForm(String email, String password) {
        boolean valid = true;
        if (TextUtils.isEmpty(email)){
            emailEditText.setError("Email Required");
            valid = false;
        } else
            emailEditText.setError(null);
        if (TextUtils.isEmpty(password)){
            passwordEditText.setError("Password Required");
            valid = false;
        } else
            passwordEditText.setError(null);
        return valid;
    }

    private void updateUI() {
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
