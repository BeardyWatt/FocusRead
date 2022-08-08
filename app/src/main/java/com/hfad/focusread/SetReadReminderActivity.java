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
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hfad.focusread.databinding.ActivityMainBinding;

import java.nio.channels.Channel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class SetReadReminderActivity extends AppCompatActivity {
    Button dateBtn, timeBtn, setBtn, cancelBtn;
    String  date, time;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    int year, month, day, hour, minute;
    private ActivityMainBinding binding;
    private MaterialTimePicker timePicker;
    private MaterialDatePicker datePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_read_reminder);
        createNotificationChannel();

        calendar = Calendar.getInstance();
        timeBtn = findViewById(R.id.select_time_btn);
        dateBtn = findViewById(R.id.select_date_btn);
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

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

    }

    private void showDatePicker() {
       datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Reminder Date").build();
       datePicker.show(getSupportFragmentManager(), "Focus Read");
       datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
           @Override
           public void onPositiveButtonClick(Long selection) {
               calendar.setTimeInMillis(selection);
               SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
               String formatted = format.format(calendar.getTime());
               dateBtn.setText(formatted);
           }
       });
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
        pendingIntent = PendingIntent.getBroadcast(this , 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        }
        else
            { pendingIntent = PendingIntent.getActivity (this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT); }*/
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Reminder Set Successfully", Toast.LENGTH_SHORT).show();

    }

    private void showTimePicker() {
        timePicker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12).setMinute(00).setTitleText("Select Reminder Time").build();
        timePicker.show(getSupportFragmentManager(), "focusRead");
        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetReadReminderActivity.this, "Get Hour" + timePicker.getHour(), Toast.LENGTH_SHORT).show();
                if(timePicker.getHour() > 12) {
                    timeBtn.setText(String.format("%02d", (timePicker.getHour()-12))
                            + ":" + String.format("%02d", timePicker.getMinute()) + "PM");
                }
                else if(timePicker.getHour() == 12){
                    timeBtn.setText(String.format("%02d", (timePicker.getHour()))
                            + ":" + String.format("%02d", timePicker.getMinute()) + "PM");
                }
                else{

                    timeBtn.setText(String.format("%02d", timePicker.getHour()) +":" + String.format("%02d", timePicker.getMinute()) + "AM");
                }
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
            NotificationChannel channel = new NotificationChannel("focusRead", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}