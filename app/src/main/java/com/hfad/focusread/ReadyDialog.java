package com.hfad.focusread;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;


public class ReadyDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are You Ready To Begin?").setMessage("Make sure you have your book open and the correct page, and anything else ypu need before starting the timer")
                .setPositiveButton("Im Ready!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do Nothing
                    }
                });
        return builder.create();
    }
}
