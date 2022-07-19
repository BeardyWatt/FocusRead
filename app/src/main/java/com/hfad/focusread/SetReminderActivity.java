package com.hfad.focusread;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/*public class SetReminderActivity extends AppCompatActivity implements View.OnClickListener {
    Button dateBtn, timeBtn, setBtn;
    String timeToNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        dateBtn = findViewById(R.id.select_date_btn);
        timeBtn = findViewById(R.id.select_time_btn);
        setBtn = findViewById(R.id.set_btn);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        setBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == timeBtn){
            selectTime();
        }else if (view == dateBtn) {
            selectDate();
        }else{
            submit();
        }

    }

    private void submit(){
        if(timeBtn.getText().toString().equals("Select Time")||(dateBtn.getText().toString().equals("Select Date")){
            Toast.makeText(this, "Please Select Date and Time", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    private  void selectTime(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                timeToNotify = hourOfDay + ":" + min;
                timeBtn.setText(formatTime(hourOfDay,min));
            }
        },hour,minute,false);
        timePickerDialog.show();
    }
    private void selectDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                dateBtn.setText(day + "-" + month + "-" + year);

            }
        },year, month, day);
    }

    public String formatTime(int hour, int minute) {

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
    }
}*/
