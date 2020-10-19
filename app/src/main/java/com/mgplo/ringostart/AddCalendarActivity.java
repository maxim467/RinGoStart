package com.mgplo.ringostart;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

public class AddCalendarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    public static final String EXTRA_DAY_OF_MONTH = "dayOfTheMonth";
    public static final String EXTRA_MONTH = "month";
    public static final String EXTRA_YEAR = "year";

    SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);

    }

    public void showDatePickerDialog(View view) {
        DialogFragment pickerFragment = new DatePickerFragment();
        pickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        Toast.makeText(this, "Date selected!"+" "+dayOfMonth+"/"+month+"/"+year, Toast.LENGTH_LONG).show();

        //Calendar is saved
        sharedPreferencesManager = new SharedPreferencesManager(this);
        sharedPreferencesManager.writeSavedUser(true);

        sharedPreferencesManager.writeSavedDay(dayOfMonth);
        sharedPreferencesManager.writeSavedMonth(month);
        sharedPreferencesManager.writeSavedYear(year);

        Intent intent = new Intent(this, ShowCalendarActivity.class);
        intent.putExtra(EXTRA_DAY_OF_MONTH, dayOfMonth);
        intent.putExtra(EXTRA_MONTH,month);
        intent.putExtra(EXTRA_YEAR,year);
        startActivity(intent);
    }
}
