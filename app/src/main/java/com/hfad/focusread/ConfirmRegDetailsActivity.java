package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/** ConfirmDetails is a chance for the user to confirm the account details are correct, and to check
 * that the activation email made it to their inbox
 * the user can move to the login screen from this screen**/
public class ConfirmRegDetailsActivity extends AppCompatActivity {
    Button goToLogin;
    TextView emailText;
    private static final String TAG = "ConfirmActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrim_reg_details);
        goToLogin = findViewById(R.id.go_to_login_btn);

        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        emailText = findViewById(R.id.user_reg_email_txt);
        emailText.setText(email);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmRegDetailsActivity.this
                        , LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}