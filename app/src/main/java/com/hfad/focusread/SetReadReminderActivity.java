package com.hfad.focusread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hfad.focusread.databinding.ActivityMainBinding;

import java.nio.channels.Channel;
import java.util.Calendar;

public class SetReadReminderActivity extends AppCompatActivity {
    Button dateBtn, timeBtn, setBtn, cancelBtn;
    String  date, time;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    int year, month, day, hour, minute;
    private ActivityMainBinding binding;
    private MaterialTimePicker timePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_read_reminder);
        createNotificationChannel();

        timeBtn = findViewById(R.id.select_time_btn);
        setBtn = findViewById(R.id.set_btn);
        cancelBtn = findViewById(R.id.cancel_btn);

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });

       /* dateBtn = findViewById(R.id.select_date_btn);
        timeBtn = findViewById(R.id.select_time_btn);
        setBtn = findViewById(R.id.set_btn);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        setBtn.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();*/
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this , AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this , 0, intent, 0);
        if(alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Reminder Cancelled", Toast.LENGTH_SHORT).show();
    }

    private void setAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this , AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this , 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Reminder Set Successfully", Toast.LENGTH_SHORT).show();

    }

    private void showTimePicker() {
        timePicker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12).setMinute(0).setTitleText("Select Reminder Time").build();
        timePicker.show(getSupportFragmentManager(), "focusRead");
        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timePicker.getHour() > 12) {
                    timeBtn.setText(String.format("%02d", (timePicker.getHour()-12))
                            + ":" + String.format("%02d", timePicker.getMinute()) + "PM");
                }
                else{
                    timeBtn.setText(timePicker.getHour() +":" + timePicker.getMinute() + "AM");
                }
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

            }
        });
    }

    private void createNotificationChannel() {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            CharSequence name = "focusReadReminderChannel";
            String description = "ChannelForAlarmManger";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = null;
            channel = new NotificationChannel("focusRead", name, importance);
            channel.setDescription(description);
            NotificationManagerCompat notificationManager = getSystemService(NotificationManagerCompat.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


   /* @Override
    public void onClick(View view) {
        if (view == timeBtn){
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            showTimePicker(hour, minute);
        }else if (view == dateBtn) {
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            showDatePicker(day, month, year);
        }else{
            submit();
        }

    }

    private void submit(){
        if(timeBtn.getText().toString().equals("Select Time")||
                (dateBtn.getText().toString().equals("Select Date"))){
            Toast.makeText(this, "Please Select Date and Time",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Date ="  + date + "time =" + time , Toast.LENGTH_SHORT).show();

        Reminder reminder = new Reminder(date, time);
        showProgressDialog("Please wait while we add your reminder");
        firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser()
                .getUid()).collection("reminders").add(reminder).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

            @Override
            public void onSuccess(DocumentReference documentReference) {
                hideProgressDialog();
                Toast.makeText(SetReadReminderActivity.this, "Reminder set Successfully", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                hideProgressDialog();
                Toast.makeText(SetReadReminderActivity.this, "Set Reminder Failed", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showTimePicker(int hour, int minute){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                //timeToNotify = hour + ":" + minute;
                timeBtn.setText(hour + ":" + minute);
            }
        },hour,minute,false);
        timePickerDialog.show();
    }
    private void showDatePicker(int day, int month, int year){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                dateBtn.setText(day + "-" + month + "-" + year);

            }
        },year, month, day);
        datePickerDialog.show();
    }*/

    /*public String formatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 ==0) {
            formattedMinute = "0" + minute;
        }else {
            formattedMinute = "" + minute;
        }

        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }
        return time;
    }*/
}