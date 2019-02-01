package com.mylocarson.reservationapp.interfaces;

import com.mylocarson.reservationapp.models.booking.Booking;

public interface CancelBookingListener {
    void cancelBooking(Booking booking);
}
