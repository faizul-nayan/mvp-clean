package com.stockbook.ims.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.stockbook.ims.R;
import com.stockbook.ims.presentation.login.LoginActivity;
import com.stockbook.ims.presentation.sellsman.ui.activities.SellDashboardActivity;
import com.stockbook.ims.utills.MySharePreferences;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */

public class SplashActivity extends AppCompatActivity {

   // public static int SPLASH_TIME_OUT = 5000;
    public static int SPLASH_TIME_OUT = 1000;
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

                isChecked = preferences.getboolean(MySharePreferences.IS_REMEMBER);

                if (isChecked){
                    Log.e("check","checked");
                    Intent intent = new Intent(SplashActivity.this, SellDashboardActivity.class);
                    //intent.putExtra(ApiClient.API_KEY, jsonObject.getString(ApiClient.API_KEY));
                    startActivity(intent);
                    finish();
                }
                else {
                    Log.e("check","not checked");
                    mIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mIntent);
                    finish();
                }
//                mIntent = new Intent(SplashActivity.this, LoginActivity.class);
//                startActivity(mIntent);
//                finish();
            }

        }, SPLASH_TIME_OUT);
        //throw new RuntimeException("This is a crash");

    }

}
