package com.example.bjorn.criminalintent.model;

import java.util.Date;
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
    private Date date;
    private boolean solved;

    public Crime() {
        mId = UUID.randomUUID();
        date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
