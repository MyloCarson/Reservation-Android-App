package com.mylocarson.reservationapp.activities;

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
import com.mylocarson.reservationapp.models.user.User;
import com.mylocarson.reservationapp.models.user.UserResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.passwordAgain)
    EditText passwordAgain;

    @BindView(R.id.progress)
    ProgressBar progress;

    ReservationService reservationService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        reservationService = new ReservationAPI().getNewsService();
        ButterKnife.bind(this);

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


    @OnClick(R.id.register)
    void register(){
        if (Helpers.isValid(email) && Helpers.isValid(username)
                && Helpers.isValid(password) && Helpers.isValid(passwordAgain)){
            if (Helpers.isValidEmail(email)){
                if (password.getText().toString().equalsIgnoreCase(passwordAgain.getText().toString())){
                    User user = new User(email.getText().toString(),
                            username.getText().toString(),
                            password.getText().toString());
                    Helpers.toogleViews(1,progress);
                    Call<UserResponse> userResponseCall = reservationService.signup(user);
                    userResponseCall.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            Helpers.toogleViews(0,progress);
                            if (response.body() != null){
                                if (response.body().getCode() == 0 ){
                                    Snackbar.make(email,"Sign up successful.",Snackbar.LENGTH_SHORT).show();
                                    Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Helpers.toogleViews(0,progress);
                            Log.e(TAG, "onFailure: ",t );
                            Snackbar.make(email,"Error, try again.",Snackbar.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Snackbar.make(email,"Password doesn't match.", Snackbar.LENGTH_SHORT).show();
                    passwordAgain.setError("Password doesn't match.");
                }
            }else{
                Snackbar.make(email,"Enter valid e-mail!", Snackbar.LENGTH_SHORT).show();
                email.setError("Enter valid e-mail!");
            }
        }else{
            Snackbar.make(email,"Fill required fields.", Snackbar.LENGTH_SHORT).show();
        }
    }
}
