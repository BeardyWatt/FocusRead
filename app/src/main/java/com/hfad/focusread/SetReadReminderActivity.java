package com.hfad.focusread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class SetReadReminderActivity extends BaseActivity implements View.OnClickListener {
    Button dateBtn, timeBtn, setBtn;
    String  date, time;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_read_reminder);
        dateBtn = findViewById(R.id.select_date_btn);
        timeBtn = findViewById(R.id.select_time_btn);
        setBtn = findViewById(R.id.set_btn);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        setBtn.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }


    @Override
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
    }

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