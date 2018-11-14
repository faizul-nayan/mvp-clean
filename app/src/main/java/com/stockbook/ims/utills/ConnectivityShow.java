package com.stockbook.ims.utills;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.Window;

import com.stockbook.ims.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Faizul Haque Nayan on 18/10/23.
 */

public class ConnectivityShow extends Dialog {

    private Context mContext;

    public ConnectivityShow(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showconnectivity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mobileDataLayout)
    void mobileData(){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.settings",
                "com.android.settings.Settings$DataUsageSummaryActivity"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    @OnClick(R.id.wifiDataLayout)
    void wifiData(){
        mContext.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }
}
