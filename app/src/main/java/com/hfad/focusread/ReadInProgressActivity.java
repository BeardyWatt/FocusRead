package com.hfad.focusread;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
/**
 * ReadInProgress works as the one on the main features of Focus Read
 * the user can operate a timer while they engage with their reading material
 * allow the chance to pause, continue, reset and stop the timer is needed.
 * **/

public class ReadInProgressActivity extends Activity {

    private String title, author, status, bookId;
    private int pages, target, startPage;
    private TextView timerTxt;
    private Button startPauseBtn, resetBtn, stopBtn;
    private Timer timer;
    private TimerTask timerTask;
    private Double time = 0.0;
    private boolean timeStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_in_progess);

        showReadyDialog();

        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        author = intent.getStringExtra("AUTHOR");
        pages = intent.getIntExtra("NOP", 1);
        status = intent.getStringExtra("STATUS");
        target = intent.getIntExtra("TARGET", 1);
        startPage = intent.getIntExtra("STARTPAGE", 1);
        bookId = intent.getStringExtra("BOOKID");

        timerTxt = findViewById(R.id.timer_txt);
        startPauseBtn = findViewById(R.id.start_pause_btn);
        resetBtn = findViewById(R.id.reset_btn);
        stopBtn = findViewById(R.id.stop_btn);
        timer = new Timer();

        /**
         * the start button is programed to change to a pause or continue button when relevant
         * **/
        startPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeStarted == false) {
                    timeStarted = true;
                    startPauseBtn.setText("Pause");
                    startTimer();
                } else {
                    timeStarted = false;
                    startPauseBtn.setText("Continue");
                    timerTask.cancel();
                }
            }
        });

        /**
         * the reset button will start the timer back at zero after the user conforms their decision
         * in the dialog
         * **/
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder resetAlert = new AlertDialog.Builder(ReadInProgressActivity.this);
                resetAlert.setTitle("Reset Timer");
                resetAlert.setMessage("Are you sure you want to Reset the Timer?");
                resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (timerTask != null) {
                            timerTask.cancel();
                            startPauseBtn.setText("Continue");
                            time = 0.0;
                            timeStarted = false;
                            timerTxt.setText(formatTime(0, 0, 0));
                        }
                    }
                });
                resetAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do Nothing.
                    }
                });
                resetAlert.show();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStopDialog();
            }
        });


    }
    /**
     * this dialog is set to assess the users readiness to learn, giving them a chance to make sure
     * they have everything they need to start
     * **/
    private void showReadyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflater = getLayoutInflater().inflate(R.layout.ready_dialog, null);
        builder.setView(inflater);
        final AlertDialog readyDialog = builder.create();
        Button yesBtn = inflater.findViewById(R.id.ready_btn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyDialog.dismiss();
            }
        });
        readyDialog.show();
    }


    /**
     * called in the startBtn onClick this wil start the timer running
     * **/
    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerTxt.setText(getTimerText());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
    /**
     * using some math to round the timer attributes so the time stored is valid
     * **/
    private String getTimerText() {
        int rounded = (int) Math.round(time);
        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);
        return formatTime(seconds, minutes, hours);
    }
    /**
     * method to format the recorded time correctly
     * **/
    private String formatTime(int seconds, int minutes, int hours) {
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" +
                String.format("%02d", seconds);
    }
    /**
     * this dialog is called when the endBtn is clicked, to ensure that the user wants to end the
     * session they will have to confirm in this dialog before the time is logged for the stat list
     * **/
    private void showStopDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflater = getLayoutInflater().inflate(R.layout.end_read_dialog, null);
        builder.setView(inflater);
        final AlertDialog stopDialog = builder.create();
        Button yesBtn = inflater.findViewById(R.id.yes_btn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadInProgressActivity.this
                        , SessionFinishedActivity.class);
                intent.putExtra("TITLE", title);
                intent.putExtra("AUTHOR", author);
                intent.putExtra("NOP", pages);
                intent.putExtra("STATUS", status);
                intent.putExtra("STARTPAGE", startPage);
                intent.putExtra("TARGET", target);
                intent.putExtra("TIME", getTimerText());
                intent.putExtra("BOOKID", bookId);

                startActivity(intent);
            }
        });

        Button noBtn = inflater.findViewById(R.id.no_btn);
        noBtn.setOnClickListener(new View.OnClickListener() {

            /**
             * to close the dialog when either button hase been clicked
             * **/
            @Override
            public void onClick(View v) {
                stopDialog.dismiss();
                //continue timer
            }
        });


            stopDialog.show();
        }
}
