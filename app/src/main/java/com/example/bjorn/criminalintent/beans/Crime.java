package com.example.bjorn.criminalintent.beans;

import java.util.UUID;

/**
 * It`s a bean class, which represents an information
 * about a crime.
 *
 * @author Vitaly Zeenko
 */
public class Crime {
    private UUID mId;
    private String mTitle;

    public Crime() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
