package com.udemy.wilfredo.loggy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.udemy.wilfredo.loggy.R;
import com.udemy.wilfredo.loggy.utils.Util;

public class DashboardActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean selection;
        switch (item.getItemId()) {
            case R.id.menu_logout:
                logOutApplication();
                selection = true;
                break;
            case R.id.menu_forgetlogout:
                Util.forgetFields(prefs);
                logOutApplication();
                selection = true;
                break;
            default:
                selection = super.onOptionsItemSelected(item);
        }
        return selection;
    }

    /**
     *
     */
    private void logOutApplication() {
        Intent intentLogOut = new Intent(this, LoggyActivity.class);
        intentLogOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentLogOut);
    }
}
