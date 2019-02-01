package com.mylocarson.reservationapp.models.booking;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BookingResponse{
	@SerializedName("code")
	private int code;
	@SerializedName("resources")
	private ArrayList<Booking> bookings;
	@SerializedName("message")
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setResources(ArrayList<Booking> bookings){
		this.bookings = bookings;
	}

	public ArrayList<Booking> getResources(){
		return bookings;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"BookingResponse{" + 
			"code = '" + code + '\'' + 
			",resources = '" + bookings + '\'' +
			",message = '" + message + '\'' + 
			"}";
		}
}