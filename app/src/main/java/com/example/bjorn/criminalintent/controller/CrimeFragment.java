package com.example.bjorn.criminalintent.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bjorn.criminalintent.R;
import com.example.bjorn.criminalintent.beans.Crime;

/**
 * This class is a controller, which is interacted with model objects
 * and views.
 *
 * @author Vitaly Zeenko
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = v.findViewById(R.id.crime_title);
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

        return v;
    }
}
