package com.mylocarson.reservationapp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chootdev.recycleclick.RecycleClick;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mylocarson.reservationapp.R;
import com.mylocarson.reservationapp.adapters.TableAdapter;
import com.mylocarson.reservationapp.api.ReservationAPI;
import com.mylocarson.reservationapp.api.ReservationService;
import com.mylocarson.reservationapp.classes.Helpers;
import com.mylocarson.reservationapp.models.booking.Booking;
import com.mylocarson.reservationapp.models.booking.BookingResponse;
import com.mylocarson.reservationapp.models.booking.PlainBooking;
import com.mylocarson.reservationapp.models.tables.Table;
import com.mylocarson.reservationapp.models.tables.TableResponse;
import com.mylocarson.reservationapp.utils.DrawerUtil;
import com.mylocarson.reservationapp.utils.Preferences;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationActivity extends AppCompatActivity {
    ReservationService reservationService;
    ArrayList<Table> tables = new ArrayList<>();
    private static final String TAG = ReservationActivity.class.getSimpleName();

    @BindView(R.id.bookingName)
    EditText bookingName;

    @BindView(R.id.bookingTime)
    EditText bookingTime;

    @BindView(R.id.bookingDate)
    EditText bookingDate;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    @BindView(R.id.tableText)
            TextView tableText;



    Context context;

    Table clickedTable;

    AlertDialog tablesAlertDialog;
    String [] tableNames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        context = this;
        DrawerUtil.getDrawer(this, toolbar);
        reservationService = new ReservationAPI().getNewsService();


//        Call<TableResponse> getTablesCall = this.reservationService.getTables();
//        getTablesCall.enqueue(new Callback<TableResponse>() {
//            @Override
//            public void onResponse(Call<TableResponse> call, Response<TableResponse> response) {
//                Helpers.toogleViews(0,progressBar);
//                if (response.body()!=null){
//                    Log.i(TAG, "onResponse: done fetching tables");
//                    Log.e(TAG, "onResponse: "+response.body() );
//                    tables = (ArrayList<Table>) response.body().getTables();
//                    if (tables.size() > 0){
//                        tableNames = new String[tables.size()];
//                        for (int i=0; i<tables.size(); i++){
//                            tableNames[i] = tables.get(i).getName();
//                        }
//                        spinner.setItems(tableNames);
//
//                    }else{
//                        Snackbar.make(spinner,"No table(s) available.\nTry again later.",Snackbar.LENGTH_SHORT).show();
//                    }
//                }else{
//                    try{
//                        Log.e(TAG, "onResponse: "+response.errorBody().string() );
//                    }catch (Exception e){
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TableResponse> call, Throwable t) {
//                Helpers.toogleViews(0,progressBar);
//                Log.e(TAG, "onFailure: ", t);
//            }
//        });
//        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
//                clickedTable = tables.get(position);
//            }
//        });
//        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {
//            @Override
//            public void onNothingSelected(MaterialSpinner spinner) {
//                Snackbar.make(spinner, "No table selected.", Snackbar.LENGTH_LONG).show();
//            }
//        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // app icon in action bar clicked; go home
//                onBackPressed();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    private AlertDialog tableDialog (Context context){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.MyAlertDialogTheme);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.table_list, null);
        final RecyclerView recyclerView = view.findViewById(R.id.table_recycler);
        final ProgressBar progressBar = view.findViewById(R.id.progress);
        final TextView no_tables = view.findViewById(R.id.no_tables);

        Call<TableResponse> getTablesCall = this.reservationService.getTables();
        getTablesCall.enqueue(new Callback<TableResponse>() {
            @Override
            public void onResponse(Call<TableResponse> call, Response<TableResponse> response) {
                Helpers.toogleViews(0,progressBar);
                if (response.body()!=null){
                    Log.i(TAG, "onResponse: done fetching tables");
                    Log.e(TAG, "onResponse: "+response.body() );
                    tables = (ArrayList<Table>) response.body().getTables();
                    setViews(no_tables,recyclerView,tables);
                    setupRecycler(recyclerView,tables);

                }else{
                    try{
                        Log.e(TAG, "onResponse: "+response.errorBody().string() );
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onFailure(Call<TableResponse> call, Throwable t) {
                Helpers.toogleViews(0,progressBar);
                Log.e(TAG, "onFailure: ", t);
            }
        });

        RecycleClick.addTo(recyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                clickedTable = tables.get(position);
                Helpers.toogleViews(1,tableText);
                tableText.setText(clickedTable.getName());
                tablesAlertDialog.dismiss();
//                Log.i(TAG, "onItemClicked: "+clickedTable);
            }
        });



        builder.setView(view);
        builder.setCancelable(false);
        builder.setTitle("Select Table");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return builder.create();

    }

    private void setupRecycler(RecyclerView recyclerView, ArrayList<Table> tables){
        TableAdapter adapter = new TableAdapter(tables,context);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));
    }

    @OnClick(R.id.tableSelect)
    void selectTable(){
        tablesAlertDialog = tableDialog(this);
        tablesAlertDialog.show();
    }

    @OnClick(R.id.bookingTime)
    void selectTime(){
        Helpers.showTimePicker(this,bookingTime);
    }

    @OnClick(R.id.bookingDate)
    void selectDate(){
        Helpers.showDatePicker(this,bookingDate);
    }

    @OnClick(R.id.reserveButton)
    void reserve(){
        if (Helpers.isValid(bookingName)&&Helpers.isValid(bookingDate)&&Helpers.isValid(bookingTime)){
//            Table table = Preferences.getSelectedTable(this);
            if (clickedTable !=null){
                Helpers.toogleViews(1,progressBar);

                Booking booking = new Booking(Helpers.string(bookingName),
                        Helpers.string(bookingDate),
                        Helpers.string(bookingTime),
                        Integer.toString(clickedTable.getId()));
                Log.i(TAG, "reserve: "+booking);
                Log.i(TAG, "reserve user id: "+Preferences.getUser(this).getId());
                Call<PlainBooking> bookingResponseCall = this.reservationService.book(
                        Long.toString(Preferences.getUser(this).getId()),
                        booking);
                bookingResponseCall.enqueue(new Callback<PlainBooking>() {
                    @Override
                    public void onResponse(Call<PlainBooking> call, Response<PlainBooking> response) {
                        Helpers.toogleViews(0,progressBar);
                        if (response.body()!=null){
                            Log.i(TAG, "onResponse: "+response.body());
                            final Snackbar snackbar = Snackbar.make(bookingName,"Table reserved successfully.",3000);
                            snackbar.show();
                            gotoMyBookings();
                        }else{
                            try{
                                Log.e(TAG, "onResponse: "+response.errorBody().string() );
                            }catch (Exception e){}
                        }
                    }

                    @Override
                    public void onFailure(Call<PlainBooking> call, Throwable t) {
                        Helpers.toogleViews(0,progressBar);
                        Log.e(TAG, "onFailure: ",t );
                        Log.e(TAG, "onFailure: ========>>>>>>"+t.toString() );

                    }
                });
            }else{
                Snackbar.make(spinner,"Please Select a table.",Snackbar.LENGTH_SHORT).show();
            }

        }
    }

    void setViews(TextView no_table,RecyclerView recyclerView,ArrayList<Table> tables){
        if (tables.size() > 0){
            Helpers.toogleViews(0,no_table);
            Helpers.toogleViews(1,recyclerView);
        }else{
            Helpers.toogleViews(1,no_table);
            Helpers.toogleViews(0,recyclerView);
        }
    }

    private void gotoMyBookings(){
        Intent intent = new Intent(ReservationActivity.this, MyBookingsActivity.class);
        startActivity(intent);
    }

    public interface TaskHandle {
        void invalidate();
    }

    public static TaskHandle setTimeout(final Runnable r, long delay) {
        final Handler h = new Handler();
        h.postDelayed(r, delay);
        return new TaskHandle() {
            @Override
            public void invalidate() {
                h.removeCallbacks(r);
            }
        };
    }
}
