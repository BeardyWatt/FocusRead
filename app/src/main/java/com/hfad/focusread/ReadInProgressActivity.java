package com.hfad.focusread;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class ReadInProgressActivity extends Activity {

    private String title, author, status;
    private  int pages, target, startPage;
    private TextView timerTxt;
    private Button startPauseBtn, resetBtn, stopBtn;
    private Timer timer;
    private TimerTask timerTask;
    private Double time = 0.0;
    private boolean timeStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_in_progess);
        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");
        author = intent.getStringExtra("AUTHOR");
        pages = intent.getIntExtra("NOP", 1);
        status = intent.getStringExtra("STATUS");
        target = intent.getIntExtra("TARGET", 1);
        startPage = intent.getIntExtra("STARTPAGE", 1);

        timerTxt = findViewById(R.id.timer_txt);
        startPauseBtn = findViewById(R.id.start_pause_btn);
        resetBtn = findViewById(R.id.reset_btn);
        stopBtn = findViewById(R.id.stop_btn);
        timer = new Timer();

        startPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(timeStarted == false){
                  timeStarted = true;
                  startPauseBtn.setText("Pause");
                  startTimer();
              }else{
                  timeStarted = false;
                  startPauseBtn.setText("Continue");
                  timerTask.cancel();
              }
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder resetAlert = new AlertDialog.Builder(ReadInProgressActivity.this);
                resetAlert.setTitle("Reset Timer");
                resetAlert.setMessage("Are you sure you want to Reset the Timer?");
                resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(timerTask != null){
                            timerTask.cancel();
                            startPauseBtn.setText("Continue");
                            time = 0.0;
                            timeStarted = false;
                            timerTxt.setText(formatTime(0,0,0));
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

    private void startTimer() {
       timerTask = new TimerTask() {
           @Override
           public void run() {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       time ++;
                       timerTxt.setText(getTimerText());
                   }
               });
           }
       };
       timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private String getTimerText() {
        int rounded = (int) Math.round(time);
        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);
        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours) {
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) +  ":" +
                String.format("%02d", seconds);
    }

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
                        ,SessionFinishedActivity.class);
                intent.putExtra("TITLE", title);
                intent.putExtra("AUTHOR", author);
                intent.putExtra("NOP", pages);
                intent.putExtra("STATUS", status);
                intent.putExtra("STARTPAGE", startPage);
                intent.putExtra("TARGET", target);
                intent.putExtra("TIME" , getTimerText());

                startActivity(intent);
            }
        });

        Button noBtn = inflater.findViewById(R.id.no_btn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopDialog.dismiss();
                //continue timer
            }
        });
        stopDialog.show();
    }
}