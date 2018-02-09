package com.udemy.wilfredo.loggy.app;

import android.app.Application;
import android.os.SystemClock;

/**
 * Clase que se ejecuta al principio de la aplicación para retrasar la desaparición del splashscreen
 * Created by wilfredo on 08-02-18.
 */

public class LoggyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
