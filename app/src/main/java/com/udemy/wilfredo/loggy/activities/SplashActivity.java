package com.udemy.wilfredo.loggy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.udemy.wilfredo.loggy.utils.Util;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        String email = Util.getValueForPreferences(prefs,"email");
        String password = Util.getValueForPreferences(prefs,"password");
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            Intent intentDashboard = new Intent(this,DashboardActivity.class);
            startActivity(intentDashboard);
        } else {
            Intent intentLogin = new Intent(this,LoggyActivity.class);
            startActivity(intentLogin);
        }
        finish();
    }
}
