package com.mylocarson.reservationapp.controllers;

import android.util.Log;

import com.mylocarson.reservationapp.api.ReservationAPI;
import com.mylocarson.reservationapp.api.ReservationService;
import com.mylocarson.reservationapp.models.booking.Booking;
import com.mylocarson.reservationapp.models.booking.BookingResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingsController {
    private ReservationService reservationService = new ReservationAPI().getNewsService();
    ArrayList<Booking>  myBookings = new ArrayList<>();
    private static final String TAG =BookingsController.class.getSimpleName();

    public ArrayList<Booking> getMyBookings(String user_id){

        Call<BookingResponse> bookingResponseCall = this.reservationService.getUserBookings(user_id);
        bookingResponseCall.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if (response.isSuccessful()){
                    if (response.body()!=null){
                        Log.i(TAG, "onResponse: "+response.body().toString());
                        myBookings = (ArrayList<Booking>) response.body().getResources();
                    }
                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {

            }
        });
        return myBookings;
    }
}
