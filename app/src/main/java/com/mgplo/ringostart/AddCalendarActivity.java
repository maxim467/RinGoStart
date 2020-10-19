package com.mgplo.ringostart;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

public class AddCalendarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);

    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        Toast.makeText(this, "Date selected!"+dayOfMonth+"/"+month+"/"+year, Toast.LENGTH_LONG).show();

    }
}
