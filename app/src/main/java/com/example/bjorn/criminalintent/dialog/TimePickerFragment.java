package com.example.bjorn.criminalintent.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.example.bjorn.criminalintent.R;

import java.util.Date;

public class TimePickerFragment extends DialogFragment {
    public static final String EXTRA_TIME =
            "com.example.bjorn.criminalintent.time";
    private static final String ARG_DATE = "time";
    private TimePicker timePicker;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getArguments() != null;
//        Date date = (Date) getArguments().getSerializable(ARG_DATE);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//
//        int year = calendar.get(Calendar.YEAR);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.time_dialog, null);

        timePicker = v.findViewById(R.id.dialog_time_time_picker);
        //timePicker.setHour(hour);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        (dialog, which) -> {
                            int hours = 0;
                            int minutes = 0;
                            if (timePicker.getHour() != 0 && timePicker.getMinute() != 0) {
                                hours = timePicker.getHour();
                                minutes = timePicker.getMinute();
                            }

                            Log.d("myLog", "Init date");
                            Date date1 = new Date();
                            date1.setHours(hours);
                            date1.setMinutes(minutes);
                            Log.d("myLog", date1.toString());
                            sendResult(Activity.RESULT_OK, date1);
                        })
                .create();
    }


    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
