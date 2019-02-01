
package com.mylocarson.reservationapp.models;

import com.google.gson.annotations.SerializedName;

public class PlainResponse {

    @SerializedName("code")
    private Long Code;
    @SerializedName("message")
    private String Message;
    @SerializedName("resources")
    private Boolean Resources;

    public Long getCode() {
        return Code;
    }

    public void setCode(Long code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Boolean getResources() {
        return Resources;
    }

    public void setResources(Boolean resources) {
        Resources = resources;
    }

}
