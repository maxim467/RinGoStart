package com.mgplo.ringostart;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getContext(), year, month, day);
    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            month++;
//            Log.d("DatePickerFragment","Date selected!"+" "+dayOfMonth+"/"+month+"/"+year);
//        Toast.makeText(requireActivity(), "Date selected!"+dayOfMonth+"/"+month+"/"+year, Toast.LENGTH_LONG).show();
//
//    }
}
