package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UserAgreementActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);

        textView = (TextView) findViewById(R.id.user_details_txt);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(Html.fromHtml(readTxt()));

        findViewById(R.id.u_a_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAgreementActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private String readTxt() {
        InputStream inputStream = getResources().openRawResource(R.raw.user_doc);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }
}