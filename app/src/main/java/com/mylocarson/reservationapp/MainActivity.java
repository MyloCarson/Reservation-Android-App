package com.mylocarson.reservationapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mylocarson.reservationapp.activities.ForgotPasswordActivity;
import com.mylocarson.reservationapp.activities.MyBookingsActivity;
import com.mylocarson.reservationapp.activities.SignUpActivity;
import com.mylocarson.reservationapp.api.ReservationAPI;
import com.mylocarson.reservationapp.api.ReservationService;
import com.mylocarson.reservationapp.classes.Helpers;
import com.mylocarson.reservationapp.controllers.UserController;
import com.mylocarson.reservationapp.models.user.User;
import com.mylocarson.reservationapp.models.user.UserResponse;
import com.mylocarson.reservationapp.utils.Preferences;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.username)
    EditText email;
//    @BindView(R.id.password)
            EditText password;
//    @BindView(R.id.progress)
    ProgressBar progress;
    Button btn;
    ReservationService reservationService;
    private Context context;
    private static final  String TAG = MainActivity.class.getSimpleName();
    private ArrayList<User> userArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        reservationService = new ReservationAPI().getNewsService();
        context = this;

        btn = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progress = findViewById(R.id.progress);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i(TAG, "login: called");
//                User user = new User(Helpers.string(username),Helpers.string(password));
//                Log.i(TAG, "login: " +user.toString());
//                ArrayList<User> users = new UserController().login(user);
//                Log.i(TAG, "login: Done with User Controller");
//                Log.i(TAG, "onClick: "+users.size());
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Preferences.getUser(context) != null){
            goToBookings();
        }
    }

    @Override
    public void onBackPressed() {
//        finish();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    //    @OnClick(R.id.login)
    void login(){
//        Toast.makeText(this, "Logging ", Toast.LENGTH_SHORT).show();
        progress.setVisibility(View.VISIBLE);
        if (Helpers.isValid(email) && Helpers.isValid(password)){
            if (Helpers.isValidEmail(email)){
                Log.i(TAG, "login: called");
                User user = new User(Helpers.string(email),Helpers.string(password));
                Log.i(TAG, "login: " +user.toString());

                Call<UserResponse> userCall = reservationService.login(user);
                userCall.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        progress.setVisibility(View.GONE);
                        if (response.isSuccessful()){
                            if (response.body()!=null){
                                Log.i(TAG, "onResponse: "+response.body().toString());
                                if (response.body().getCode() == 0){
                                    User user = response.body().getUser();
                                    if (user != null){
                                        Preferences.saveUser(context,user);
                                        goToBookings();
                                    }
                                }else {
                                    final Snackbar snackbar = Snackbar.make(progress,response.body().getMessage(),Snackbar.LENGTH_INDEFINITE);
                                    snackbar.setAction("OK", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            snackbar.dismiss();
                                        }
                                    });
                                    snackbar.show();
                                }
                            }
                        }else{
                            try {
                                Log.i(TAG, "onResponse: "+response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                        Log.e(TAG, "onFailure: ",t );
                    }
                });

            }else{
                Snackbar.make(email,"Enter valid e-mail!", Snackbar.LENGTH_SHORT).show();
                email.setError("Enter valid e-mail!");
            }

        }

    }

    void goToBookings(){
        Intent intent = new Intent(MainActivity.this, MyBookingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @OnClick(R.id.register)
    void register(){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @OnClick(R.id.forgot)
    void forgot(){
        Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }






}



