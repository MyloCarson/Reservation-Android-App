package com.mylocarson.reservationapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylocarson.reservationapp.R;
import com.mylocarson.reservationapp.interfaces.CancelBookingListener;
import com.mylocarson.reservationapp.interfaces.UpdateBookingRecycler;
import com.mylocarson.reservationapp.models.booking.Booking;

import java.util.ArrayList;

public class BookingAdapter  extends RecyclerView.Adapter<BookingAdapter.ViewHolder> implements UpdateBookingRecycler {
    private ArrayList<Booking> bookings;
    private Context context;
    private CancelBookingListener cancelBookingListener;

    public BookingAdapter (ArrayList<Booking> bookings, Context context, CancelBookingListener cancelBookingListener){
        this.bookings = bookings;
        this.context = context;
        this.cancelBookingListener = cancelBookingListener;
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.booking_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder viewHolder, int i) {
        final Booking booking = bookings.get(i);
        viewHolder.bookingName.setText(booking.getBookingName());
        viewHolder.bookingTime.setText(booking.getBookingTime());
        viewHolder.bookingDate.setText(booking.getBookingDate());

        if (booking.getBookingStatus().equalsIgnoreCase("true")){
            viewHolder.bookingStatus.setText("Attending");
        }else{
            viewHolder.bookingStatus.setText("Cancelled");
        }

        viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelBookingListener.cancelBooking(booking);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    @Override
    public void updateRecycler(Booking booking) {
        notifyItemRemoved(bookings.indexOf(booking));
        notifyDataSetChanged();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView bookingName,bookingDate,bookingStatus,bookingTime;
        private ImageView cancel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingName = itemView.findViewById(R.id.bookingName);
            bookingStatus = itemView.findViewById(R.id.bookingStatus);
            bookingTime = itemView.findViewById(R.id.bookingTime);
            bookingDate = itemView.findViewById(R.id.bookingDate);
            cancel = itemView.findViewById(R.id.cancel);
        }

    }
}
