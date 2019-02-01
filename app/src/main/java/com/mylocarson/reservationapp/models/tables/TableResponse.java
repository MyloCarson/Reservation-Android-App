package com.mylocarson.reservationapp.models.tables;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TableResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("resources")
    private List<Table> tables;
    @SerializedName("message")
    private String message;

    public void setCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public void setTables(List<Table> tables){
        this.tables = tables;
    }

    public List<Table> getTables(){
        return tables;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString() {
        return "TableResponse{" +
                "code=" + code +
                ", tables=" + tables +
                ", message='" + message + '\'' +
                '}';
    }
}
