package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;

public class BaseActivity extends AppCompatActivity {
    public ProgressDialog progressDialog;

    /** method to Show the progress bar, while data call is loading **/
    public void showProgressDialog(String message){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(message);
            progressDialog.setIndeterminate(true);
        }
        progressDialog.show();
    }

    /** method to hide the progress bar**/
    public void hideProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    /** method called at the end of data loading **/
    protected void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}