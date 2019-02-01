package com.mylocarson.reservationapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mylocarson.reservationapp.MainActivity;
import com.mylocarson.reservationapp.R;
import com.mylocarson.reservationapp.api.ReservationAPI;
import com.mylocarson.reservationapp.api.ReservationService;
import com.mylocarson.reservationapp.classes.Helpers;
import com.mylocarson.reservationapp.models.Reset;
import com.mylocarson.reservationapp.models.user.User;
import com.mylocarson.reservationapp.models.user.UserResponse;
import com.mylocarson.reservationapp.utils.Preferences;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.password1)
        EditText password1;
    @BindView(R.id.password2)
    EditText password2;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.progress)
    ProgressBar progress;

    private ReservationService reservationService;
    User user = null;
    private static  final String TAG = ForgotPasswordActivity.class.getSimpleName();
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        reservationService = new ReservationAPI().getNewsService();
        context = this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.reset)
    void reset() {
        if (Helpers.isValid(email) && Helpers.isValid(password1) && Helpers.isValid(password2)){
            if (Helpers.isValidEmail(email)){
                if (Helpers.string(password1).equalsIgnoreCase(Helpers.string(password2))){
                    Reset reset = new Reset(Helpers.string(password1));
                    Helpers.toogleViews(1,progress);

                    Call<UserResponse> resetPassword = reservationService.resetPassword(Helpers.string(email),reset);
                    resetPassword.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            Helpers.toogleViews(1,progress);
                            if (response.body() != null){
                                if (response.body().getCode() == 0 ){
                                    Snackbar.make(email,"Reset successful.",Snackbar.LENGTH_SHORT).show();
                                    Toast.makeText(context, "Reset successful.", Toast.LENGTH_SHORT).show();
                                    Preferences.clearAll(context);
                                    Intent intent = new Intent(ForgotPasswordActivity.this,MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);
                                    finish();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Helpers.toogleViews(1,progress);
                            Log.e(TAG, "onFailure: ",t );
                            Snackbar.make(email,"Error, try again.",Snackbar.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Snackbar.make(email,"Password doesn't match.", Snackbar.LENGTH_SHORT).show();
                    password2.setError("Password doesn't match.");
                }
            }
            else{
                Snackbar.make(email,"Enter valid e-mail!", Snackbar.LENGTH_SHORT).show();
                email.setError("Enter valid e-mail!");
            }
        }else{
            Snackbar.make(email,"Fill required fields.", Snackbar.LENGTH_SHORT).show();
        }

    }
}
