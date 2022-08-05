package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * PrivacyPolicy give the user a chance to read over this documentation
 * and can return to the register screen from here
 * **/
public class PrivacyPolicyActivity extends AppCompatActivity {
    //RecyclerView recyclerView;
    //RecyclerView.LayoutManager;
    // ArrayList<TextClass> myArrayList = new ArrayList<TextClass>();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        textView = (TextView) findViewById(R.id.privacy_details_txt);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(Html.fromHtml(readTxt()));

        findViewById(R.id.privacy_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrivacyPolicyActivity.this
                        ,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
/**
 * this method is called to read the HTML doc file for the privacy_doc
 * **/
    private String readTxt() {
        InputStream inputStream = getResources().openRawResource(R.raw.privacy_doc);
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