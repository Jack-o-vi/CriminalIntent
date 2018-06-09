package com.example.bjorn.criminalintent.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.bjorn.criminalintent.R;
import com.example.bjorn.criminalintent.dialog.DatePickerFragment;
import com.example.bjorn.criminalintent.dialog.TimePickerFragment;
import com.example.bjorn.criminalintent.model.Crime;
import com.example.bjorn.criminalintent.model.CrimeLab;

import java.util.Date;
import java.util.UUID;

/**
 * This class is a controller, which is interacted with model objects
 * and views.
 *
 * @author Vitaly Zeenko
 */
public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.getInstance(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // Здесь намеренно оставлено пустое место
                Log.d("[CrimeFragment]", "beforeTextChanged()");
            }

            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mCrime.setTitle(c.toString());
                Log.d("[CrimeFragment]", "onTextChanged()" + mCrime.getTitle());
            }

            @Override
            public void afterTextChanged(Editable c) {
                // И здесь тоже
                Log.d("[CrimeFragment]", "beforeTextChanged()");
            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        String time = mCrime.getDate().toString();
        if (time != null) updateDate(time);
        mDateButton.setOnClickListener(v1 -> {
            FragmentManager manager = getFragmentManager();
            DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
            dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
            dialog.show(manager, DIALOG_DATE);
        });

         mTimeButton = v.findViewById(R.id.crime_time);
        mTimeButton.setOnClickListener(v12 -> {
            FragmentManager manager = getFragmentManager();
            TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getDate());
            dialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
            dialog.show(manager, DIALOG_TIME);
        });

        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Назначение флага раскрытия преступления
            mCrime.setSolved(isChecked);
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        Date date;

        if (requestCode == REQUEST_DATE) {
            date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate(mCrime.getFormattedDate());
        } else if (requestCode == REQUEST_TIME) {

            Date time = (Date) data
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);

            if( (date = mCrime.getDate()) != null){
                date.setHours(time.getMinutes());
                date.setMinutes(time.getMinutes());
                mCrime.setDate(date);
            }
            updateDate(mCrime.getFormattedDate());
        }

    }

    private void updateDate(String s) {
        mDateButton.setText(s);
    }

}


