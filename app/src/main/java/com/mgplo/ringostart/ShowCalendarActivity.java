package com.mgplo.ringostart;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ShowCalendarActivity extends AppCompatActivity {

    SharedPreferencesManager sharedPreferencesManager;
    TextView tvTitleStart, tvStartDate, tvFinishDate, tvTitleFinish, tvRemainingDays;
    private LocalDate startDate, finishDate;
    private int dayOfMonth, month, year;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_calendar);
//        Toolbar toolbar = findViewById(R.id.toolbar);

        sharedPreferencesManager = new SharedPreferencesManager(this);
        dayOfMonth = sharedPreferencesManager.obtainSavedDay();
        month = sharedPreferencesManager.obtainSavedMonth();
        year = sharedPreferencesManager.obtainSavedYear();
//        if (!sharedPreferencesManager.isUserSaved()) {
//            Intent intent = getIntent();
//            dayOfMonth = intent.getIntExtra(AddCalendarActivity.EXTRA_DAY_OF_MONTH, 0);
//            month = intent.getIntExtra(AddCalendarActivity.EXTRA_MONTH, 0);
//            year = intent.getIntExtra(AddCalendarActivity.EXTRA_YEAR, 0);
//
//        } else {



        tvTitleStart = findViewById(R.id.tv_start_title);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvStartDate.setText(dayOfMonth + "/" + month + "/" + year);
        tvTitleFinish = findViewById(R.id.tv_finish_title);
        calculateDates(dayOfMonth, month, year);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void calculateDates(int day, int month, int year) {

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        //Cycle duration is 21 days
        int durationDays = 21;

        startDate = LocalDate.of(year, month, day);
        finishDate = startDate.plusDays(durationDays);


        tvRemainingDays = findViewById(R.id.tv_remaining_days);

        //Days between from today until Cycle finish
        long noOfDaysBetween = ChronoUnit.DAYS.between(LocalDate.now(), finishDate);
        if (noOfDaysBetween < 0) {
            Toast.makeText(this, "El Ciclo ya ha Terminado!!!", Toast.LENGTH_LONG).show();
            tvRemainingDays.setText("0");
        } else {
            tvRemainingDays.setText(String.valueOf(noOfDaysBetween));
        }


        String formattedDate = finishDate.format(outputFormatter);
        tvFinishDate = findViewById(R.id.tv_finish_date);
        tvFinishDate.setText(formattedDate);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
