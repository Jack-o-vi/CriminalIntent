package com.example.bjorn.criminalintent.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID = "com.example.bjorn.criminalintent.crime_id";

    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
