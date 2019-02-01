package com.mylocarson.reservationapp.models.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse{
	@SerializedName("code")
	private int code;
	@SerializedName("resources")
	private User user;
	@SerializedName("message")
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
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
			"UserResponse{" + 
			"code = '" + code + '\'' + 
			",resources = '" + user + '\'' +
			",message = '" + message + '\'' + 
			"}";
		}
}