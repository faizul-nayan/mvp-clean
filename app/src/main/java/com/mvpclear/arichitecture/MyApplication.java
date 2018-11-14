package com.mvpclear.arichitecture;

import android.app.Application;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
}
