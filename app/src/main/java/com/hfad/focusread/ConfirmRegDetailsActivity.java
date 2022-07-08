package com.hfad.focusread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConfirmRegDetailsActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private static final String TAG = "ConfirmActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim_reg_details);
        firebaseAuth = FirebaseAuth.getInstance();
        String email = firebaseAuth.getCurrentUser().getEmail();
        TextView emailText = findViewById(R.id.user_reg_email);
        emailText.setText(email);

        Button emailConBtn = findViewById(R.id.email_con_btn);
        emailConBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                Log.d(TAG,"user = "+ firebaseUser);
                Log.d(TAG,"Email Verified" + firebaseUser.isEmailVerified());
                if(firebaseUser != null){
                    Intent intent = new Intent(ConfirmRegDetailsActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ConfirmRegDetailsActivity.this, "Please Verify your Email first", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button noEmailBtn = findViewById(R.id.no_email_btn);
        noEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmailVerification();
            }
        });

    }
    private void sendEmailVerification() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ConfirmRegDetailsActivity.this, "Confirmation has be sent to sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    if (user.isEmailVerified()){
                        Intent intent = new Intent(ConfirmRegDetailsActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(ConfirmRegDetailsActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(ConfirmRegDetailsActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}