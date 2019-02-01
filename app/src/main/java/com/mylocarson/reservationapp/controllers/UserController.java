package com.mylocarson.reservationapp.controllers;


import android.util.Log;

import com.mylocarson.reservationapp.api.ReservationAPI;
import com.mylocarson.reservationapp.api.ReservationService;
import com.mylocarson.reservationapp.models.user.User;
import com.mylocarson.reservationapp.models.user.UserResponse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserController {
    private ReservationService reservationService = new ReservationAPI().getNewsService();
    private ArrayList<User> userArrayList = new ArrayList<>();
    private static  final String TAG = UserController.class.getSimpleName();


//    public ArrayList<User> login (User user){
//        Log.i(TAG, "login: Called");
//        Call<UserResponse> userCall = reservationService.login(user);
//        userCall.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                if (response.isSuccessful()){
//                    if (response.body()!=null){
//                        Log.i(TAG, "onResponse: "+response.body().toString());
//                        userArrayList = (ArrayList<User>) response.body().getResources();
//                    }
//                }else{
//                    try {
//                        Log.i(TAG, "onResponse: "+response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                Log.e(TAG, "onFailure: ",t );
//            }
//        });
//        return userArrayList;
//    }
}
