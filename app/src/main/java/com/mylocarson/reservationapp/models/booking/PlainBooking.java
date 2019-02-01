
package com.mylocarson.reservationapp.models.booking;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PlainBooking {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("resources")
    private Booking mResources;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Booking getResources() {
        return mResources;
    }

    public void setResources(Booking resources) {
        mResources = resources;
    }

}
