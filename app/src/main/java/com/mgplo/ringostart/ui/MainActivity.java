package com.mgplo.ringostart.ui;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mgplo.ringostart.DatePickerFragment;
import com.mgplo.ringostart.MyReceiver;
import com.mgplo.ringostart.R;
import com.mgplo.ringostart.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String EXTRA_DAY_OF_MONTH = "dayOfTheMonth";
    public static final String EXTRA_MONTH = "month";
    public static final String EXTRA_YEAR = "year";
    private static final String CHANNEL_ID = "001";

    SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createNotificationChannel();

        sharedPreferencesManager = new SharedPreferencesManager(this);
        if (sharedPreferencesManager.isUserSaved()) {
            Intent intent = new Intent(this, ShowCalendarActivity.class);
            startActivity(intent);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    public void showDatePickerDialog() {
        DialogFragment pickerFragment = new DatePickerFragment();
        pickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void forgetUser(MenuItem item) {
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getApplicationContext());
        sharedPreferencesManager.forgetUser();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        Toast.makeText(this, "Date selected!" + " " + dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

        //Calendar is saved
        sharedPreferencesManager = new SharedPreferencesManager(this);
        sharedPreferencesManager.writeSavedUser(true);

        sharedPreferencesManager.writeSavedDay(dayOfMonth);
        sharedPreferencesManager.writeSavedMonth(month);
        sharedPreferencesManager.writeSavedYear(year);

        Intent intent = new Intent(this, ShowCalendarActivity.class);
        intent.putExtra(EXTRA_DAY_OF_MONTH, dayOfMonth);
        intent.putExtra(EXTRA_MONTH, month);
        intent.putExtra(EXTRA_YEAR, year);
        startActivity(intent);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Prueba de notificacion";
            String description = "Esto es una notificación de prueba";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



}