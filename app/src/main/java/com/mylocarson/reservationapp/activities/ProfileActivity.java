package com.mylocarson.reservationapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.mylocarson.reservationapp.MainActivity;
import com.mylocarson.reservationapp.R;
import com.mylocarson.reservationapp.models.user.User;
import com.mylocarson.reservationapp.utils.DrawerUtil;
import com.mylocarson.reservationapp.utils.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.email)
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        DrawerUtil.getDrawer(this, toolbar);

        User user = Preferences.getUser(this);

        email.setText(user.getEmail());
        name.setText(user.getUsername());
    }

    @OnClick(R.id.signOut)
    void signout(){
        Preferences.clearAll(this);
        Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
        finish();
    }
}
