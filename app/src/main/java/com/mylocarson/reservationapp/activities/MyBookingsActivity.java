package com.mylocarson.reservationapp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mylocarson.reservationapp.R;
import com.mylocarson.reservationapp.adapters.BookingAdapter;
import com.mylocarson.reservationapp.api.ReservationAPI;
import com.mylocarson.reservationapp.api.ReservationService;
import com.mylocarson.reservationapp.classes.Helpers;
import com.mylocarson.reservationapp.controllers.BookingsController;
import com.mylocarson.reservationapp.interfaces.CancelBookingListener;
import com.mylocarson.reservationapp.models.PlainResponse;
import com.mylocarson.reservationapp.models.booking.Booking;
import com.mylocarson.reservationapp.models.booking.BookingResponse;
import com.mylocarson.reservationapp.models.booking.PlainBooking;
import com.mylocarson.reservationapp.utils.DrawerUtil;
import com.mylocarson.reservationapp.utils.Preferences;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingsActivity extends AppCompatActivity implements CancelBookingListener {
    @BindView(R.id.no_booking)
    TextView no_bookings;
    @BindView(R.id.bookings_recycler)
    RecyclerView bookings_recycler;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.newReservation)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeToRefresh;

    ReservationService reservationService;
    private Context context;
    private BookingAdapter adapter;

    private AlertDialog progressAlertDialog;

    private String user_id;
    private ArrayList<Booking> bookings = new ArrayList<>();
    private static final String TAG = MyBookingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        reservationService = new ReservationAPI().getNewsService();
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        DrawerUtil.getDrawer(this, toolbar);

        Helpers.toogleViews(1,progress);

        context = this;

        this.user_id = Long.toString(Preferences.getUser(context).getId());
        Log.i(TAG, "onCreate: user id === "+this.user_id );

//        bookings = new BookingsController().getMyBookings(user_id);
        getBooking();


        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBooking();
            }
        });

        progressAlertDialog = showProgressDialog();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    void  getBooking(){
        Call<BookingResponse> bookingResponseCall = this.reservationService.getUserBookings(user_id);
        bookingResponseCall.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                Helpers.toogleViews(0,progress);
                if (response.isSuccessful()){
                    Log.i(TAG, "onResponse: successful");
                    if (response.body()!=null){
                        if (swipeToRefresh.isRefreshing()){
                            swipeToRefresh.setRefreshing(false);
                        }
                        Log.i(TAG, "onResponse: "+response.body().toString());
                        bookings = response.body().getResources();
                        setViews();
                        setUpRecycler();

                    }
                }else{
                    Log.i(TAG, "onResponse: not successful");

                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                if (swipeToRefresh.isRefreshing()){
                    swipeToRefresh.setRefreshing(false);
                }
                Helpers.toogleViews(0,progress);
                Log.e(TAG, "onFailure: "+t.toString(),t );
            }
        });
    }

    void setUpRecycler(){
        adapter = new BookingAdapter(bookings,context,this);
        bookings_recycler.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        bookings_recycler.setLayoutManager(layoutManager);
        bookings_recycler.setHasFixedSize(true);
        bookings_recycler.setItemAnimator(new DefaultItemAnimator());
        bookings_recycler.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));

    }

    void setViews(){
        if (bookings.size() > 0){
            Helpers.toogleViews(0,no_bookings);
            Helpers.toogleViews(1,bookings_recycler);
        }else{
            Helpers.toogleViews(1,no_bookings);
            Helpers.toogleViews(0,bookings_recycler);
        }
    }

    @OnClick(R.id.newReservation)
    void fab(){
        Intent intent = new Intent(MyBookingsActivity.this, ReservationActivity.class);
        startActivity(intent);
    }


    @Override
    public void cancelBooking(Booking booking) {
        confirmDialog(booking).show();
    }

    AlertDialog confirmDialog(final Booking booking){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm ");
        builder.setMessage("Proceed to delete ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancel(booking);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return builder.create();
    }

    void cancel(final Booking booking){
        progressAlertDialog.show();
        Call<PlainResponse> cancelBookingCall = reservationService.cancelBooking(
                Long.toString(booking.getId()),
                booking.getTableId());
        cancelBookingCall.enqueue(new Callback<PlainResponse>() {
            @Override
            public void onResponse(Call<PlainResponse> call, Response<PlainResponse> response) {
                progressAlertDialog.dismiss();
                if (response.body()!=null){
                    if (response.body().getCode()==0){
                        adapter.updateRecycler(booking);
                        Snackbar.make(bookings_recycler,"Successfully cancelled booking.", Snackbar.LENGTH_SHORT).show();

                    }else{
                        Snackbar.make(bookings_recycler,"Failed to cancel booking.", Snackbar.LENGTH_SHORT).show();
                    }
                    Log.e(TAG, "onResponse: "+response.body() );
                }

            }

            @Override
            public void onFailure(Call<PlainResponse> call, Throwable t) {
                progressAlertDialog.dismiss();
                Log.e(TAG, "onFailure: ",t );
            }
        });
    }

    AlertDialog showProgressDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.waiting_progress_dialog,null);
        builder.setView(view);
        builder.setCancelable(false);
        return builder.create();
    }
}
