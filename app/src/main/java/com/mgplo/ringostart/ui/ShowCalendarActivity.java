package com.mgplo.ringostart.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mgplo.ringostart.MyReceiver;
import com.mgplo.ringostart.R;
import com.mgplo.ringostart.SharedPreferencesManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ShowCalendarActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "001";

    SharedPreferencesManager sharedPreferencesManager;
    TextView tvTitleStart, tvStartDate, tvFinishDate, tvTitleFinish, tvRemainingDays;
    ProgressBar progressBar;
    private LocalDate startDate, finishDate;
    private int dayOfMonth, month, year;
    private long durationDays;
    private long noOfDaysBetween;
    private NotificationCompat.Builder builder;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_calendar);
//        Toolbar toolbar = findViewById(R.id.toolbar);

        progressBar = findViewById(R.id.progressBar);
        FloatingActionButton fab = findViewById(R.id.fab_delete);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCalendar();
            }
        });

        sharedPreferencesManager = new SharedPreferencesManager(this);
        dayOfMonth = sharedPreferencesManager.obtainSavedDay();
        month = sharedPreferencesManager.obtainSavedMonth();
        year = sharedPreferencesManager.obtainSavedYear();

        tvTitleStart = findViewById(R.id.tv_start_title);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvStartDate.setText(dayOfMonth + "/" + month + "/" + year);
        tvTitleFinish = findViewById(R.id.tv_finish_title);
        calculateDates(dayOfMonth, month, year);

//        createNotificaction();
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(001, builder.build());


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void calculateDates(int day, int month, int year) {

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        //Cycle duration is 21 days
        durationDays = 21;

        startDate = LocalDate.of(year, month, day);
        finishDate = startDate.plusDays(durationDays);


        tvRemainingDays = findViewById(R.id.tv_remaining_days);

        //Days between from today until Cycle finish
        noOfDaysBetween = ChronoUnit.DAYS.between(LocalDate.now(), finishDate);

        if (noOfDaysBetween < 0) {
            Toast.makeText(this, "El Ciclo ya ha Terminado!!!", Toast.LENGTH_LONG).show();
            tvRemainingDays.setText("0");
        } else {
            tvRemainingDays.setText(String.valueOf(noOfDaysBetween));
            calculateProgress();
            createAlarm();
        }


        String formattedDate = finishDate.format(outputFormatter);
        tvFinishDate = findViewById(R.id.tv_finish_date);
        tvFinishDate.setText(formattedDate);


    }

    private void calculateProgress() {
        progressBar.setProgress(50);
    }


    public void deleteCalendar() {
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getApplicationContext());
        sharedPreferencesManager.forgetUser();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void createNotificaction() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notificación de prueba")
                .setContentText("Esto es una notificación de prueba")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createAlarm() {

        LocalDateTime ldt = LocalDateTime.of(2020, Month.OCTOBER, 22, 20, 34);
//        LocalDateTime ldt2 = LocalDateTime.parse("2018-12-20T12:10:00");

        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Europe/Madrid"));
        long currentTimeMillis = zdt.toInstant().toEpochMilli();

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("myAction", "mDoNotify");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP, currentTimeMillis, pendingIntent);
    }

}
