package com.mylocarson.reservationapp.api;

import com.mylocarson.reservationapp.models.PlainResponse;
import com.mylocarson.reservationapp.models.Reset;
import com.mylocarson.reservationapp.models.booking.Booking;
import com.mylocarson.reservationapp.models.booking.BookingResponse;
import com.mylocarson.reservationapp.models.booking.PlainBooking;
import com.mylocarson.reservationapp.models.tables.TableResponse;
import com.mylocarson.reservationapp.models.user.User;
import com.mylocarson.reservationapp.models.user.UserResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReservationService {
    @POST("user")
    Call<UserResponse> signup(@Body User userObject);

    @POST("login")
    Call<UserResponse> login(@Body User userObject);

    @POST("user/{id}")
    Call<UserResponse> updateUser(@Path("id") String id, @Body User userObject);

    @DELETE("user/{email}")
    Call<UserResponse> deleteUser(@Path("email") String email);

    @GET("bookings/{user_id}")
    Call<BookingResponse> getUserBookings (@Path("user_id") String user_id);

    @POST("bookings/{user_id}")
    Call<PlainBooking> book(@Path("user_id") String user_id, @Body Booking booking);

    @GET("availableTables")
    Call<TableResponse> getTables();

    @GET("bookings/cancel/{booking_id}/{table_id}")
    Call<PlainResponse> cancelBooking(@Path("booking_id") String booking_id, @Path("table_id") String table_id);

    @POST("user/forget/{email}")
    Call<UserResponse> resetPassword(@Path("email") String email, @Body Reset reset);
}
