
package com.mylocarson.reservationapp.models;

import com.google.gson.annotations.SerializedName;

public class Reset {

    public Reset(String mNewPassword) {
        this.mNewPassword = mNewPassword;
    }

    @SerializedName("new_password")
    private String mNewPassword;

    public String getNewPassword() {
        return mNewPassword;
    }

    public void setNewPassword(String newPassword) {
        mNewPassword = newPassword;
    }

}
