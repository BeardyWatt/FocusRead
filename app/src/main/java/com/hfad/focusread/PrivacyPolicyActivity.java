package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

        String data = "";
        StringBuffer stringBuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.privacy_policy);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if(is != null){
            try{
                while((data=reader.readLine())!= null){
                   stringBuffer.append(data + "n");
                }
                textView.setText(stringBuffer);
                is.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

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