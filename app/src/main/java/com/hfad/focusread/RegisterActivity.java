package com.hfad.focusread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText nameInputEditText;
    private TextInputEditText emailInputEditText;
    private TextInputEditText passwordInputEditText;
    private TextInputEditText cPasswordInputEditText;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private static final String TAG = "RegisterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInputEditText = findViewById(R.id.username_input_edit);
        emailInputEditText = findViewById(R.id.emails_input_edit);
        passwordInputEditText = findViewById(R.id.passwords_input_edit);
        cPasswordInputEditText = findViewById(R.id.confirm_passwords_input_edit);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        Button regBtn = findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = nameInputEditText.getText().toString();
            String email = emailInputEditText.getText().toString();
            String password = passwordInputEditText.getText().toString();
            String cPassword = cPasswordInputEditText.getText().toString();

            createAccount(name, email, password, cPassword);



            }
        });
    }

    private void createAccount(String name, String email, String password, String cPassword) {
        if (! validForm(name, email, password, cPassword)){
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,task ->{
                    if(task.isSuccessful()){
                        User user = new User(name, email);
                        firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid())
                                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //Toast.makeText(RegisterActivity.this,"User Successfully Created",Toast.LENGTH_SHORT);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, "Error User Not Created", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        sendEmailVerification();
                    }else {
                        Toast.makeText(this, "Create User With Email Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Create User With Email Failed" + task.getException());
                    }

                } );


    }

    private void sendEmailVerification() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    //Toast.makeText(RegisterActivity.this, "Confirmation has be sent to sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,ConfirmRegDetailsActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validForm(String name, String email, String password, String cPassword){

        boolean valid = true;
        if (TextUtils.isEmpty(name)){
            nameInputEditText.setError("Username Required");
            valid = false;
        } else
            nameInputEditText.setError(null);
        if (TextUtils.isEmpty(email)){
            emailInputEditText.setError("Email Required");
            valid = false;
        } else
            emailInputEditText.setError(null);
        if (TextUtils.isEmpty(password)){
            passwordInputEditText.setError("Strong Password Required");
            valid = false;
        } else
            passwordInputEditText.setError(null);
        if (TextUtils.isEmpty(cPassword)){
            cPasswordInputEditText.setError("Password Confirmation Required");
            valid = false;
        } else
            cPasswordInputEditText.setError(null);
        if (!password.equals(cPassword)){
            cPasswordInputEditText.setError("Passwords don't match");
            valid = false;
        } else
            cPasswordInputEditText.setError(null);
        return valid;
    }

}