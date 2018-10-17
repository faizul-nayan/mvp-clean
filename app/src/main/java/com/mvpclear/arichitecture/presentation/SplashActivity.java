package com.mvpclear.arichitecture.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mvpclear.arichitecture.R;
import com.mvpclear.arichitecture.presentation.login.LoginActivity;
import com.mvpclear.arichitecture.utills.MySharePreferences;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */

public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 5000;
   // private SecureSharedPreferences msecureSharedPreferences;
    private Intent mIntent;
    private MySharePreferences preferences;
    private boolean isChecked;

    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler = new Handler();
        //msecureSharedPreferences = SecureSharedPreferences.getInstance(this, "MyPref");
        preferences = new MySharePreferences(this);


        /*new Thread(new Runnable() {
            public void run() {



                while (progressBarValue < 100) {
                    progressBarValue += 4;
                    // Update the progress bar and display the
                    //current value in the text view
                    mHandler.post(new Runnable() {
                        public void run() {
                            //mProgressBar.setProgress(progressBarValue);

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                isChecked = preferences.getboolean(MySharePreferences.CHECKED);
                if (isChecked){
                    mIntent = new Intent(SplashActivity.this, FoMainActivity.class);
                    startActivity(mIntent);
                    finish();
                }
                else {
                    mIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mIntent);
                    finish();
                }

            }
        }).start();*/



       mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                /*if (msecureSharedPreferences.getBoolean("is_loggedIn", false)) {

                    mIntent = new Intent(SplashActivity.this, FoMainActivity.class);
                    startActivity(mIntent);
                    finish();
                } else {

                    mIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mIntent);
                    finish();
                } */

//                isChecked = preferences.getboolean(MySharePreferences.CHECKED);
//                if (isChecked){
//                    if(preferences.getString(MySharePreferences.WHO).equalsIgnoreCase(MySharePreferences.FIELDOFFICER)){
//                        mIntent = new Intent(SplashActivity.this, FoMainActivity.class);
//                    }
//                    else if(preferences.getString(MySharePreferences.WHO).equalsIgnoreCase(MySharePreferences.HEADOFFICE)) {
//                        //mIntent = new Intent(SplashActivity.this, FoMainActivity.class);
//                        mIntent = new Intent(SplashActivity.this, HoMainActivity.class);
//                    }
//                    startActivity(mIntent);
//                    finish();
//                }
//                else {
//                    mIntent = new Intent(SplashActivity.this, LoginActivity.class);
//                    startActivity(mIntent);
//                    finish();
//                }
                mIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(mIntent);
                finish();
            }

        }, SPLASH_TIME_OUT);
        //throw new RuntimeException("This is a crash");

    }

}
