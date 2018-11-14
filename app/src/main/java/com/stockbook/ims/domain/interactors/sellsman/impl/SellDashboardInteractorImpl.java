package com.stockbook.ims.domain.interactors.sellsman.impl;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.stockbook.ims.domain.executor.Executor;
import com.stockbook.ims.domain.executor.MainThread;
import com.stockbook.ims.domain.interactors.base.AbstractInteractor;
import com.stockbook.ims.domain.interactors.sellsman.SellDashboardInteractor;
import com.stockbook.ims.network.ApiClient;
import com.stockbook.ims.network.LogoutInteractor;
import com.stockbook.ims.presentation.model.DashboardModel;
import com.stockbook.ims.presentation.model.StockInfo;
import com.stockbook.ims.presentation.sellsman.presenters.SellDashboardPresenter;
import com.stockbook.ims.utills.MySharePreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 18/11/12.
 */

public class SellDashboardInteractorImpl extends AbstractInteractor implements SellDashboardInteractor {

    private Executor mExecutor;
    private MainThread mMainThread;
    private Context mContext;
    private String mUserId;
    private String mApiKey;
    private CallBack mCallBack;

    public SellDashboardInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                       Context context, CallBack callBack) {
        super(threadExecutor, mainThread);
        this.mExecutor = threadExecutor;
        this.mMainThread = mainThread;
        this.mContext = context ;
        this.mCallBack = callBack;

        mUserId = new MySharePreferences(mContext).getString(ApiClient.USER_ID);
        mApiKey = new MySharePreferences(mContext).getString(ApiClient.API_KEY);
    }

    @Override
    public void run() {



        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                AndroidNetworking.post(ApiClient.BASE_URL+"dashboards")
                        .addBodyParameter("user_id", mUserId)
                        .addHeaders("Authorization", mApiKey)
                        .setTag(ApiClient.LOG_IN)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsObject(DashboardModel.class, new ParsedRequestListener<DashboardModel>() {
                            @Override
                            public void onResponse(DashboardModel dashboardModel) {
                                // do anything with response
                                if (dashboardModel.status.equalsIgnoreCase("Success")){
                                    mCallBack.setDashboardInfo(dashboardModel);
                                }
                                else if(dashboardModel.status.equalsIgnoreCase("Failed")){
                                    Log.e("onRespone", dashboardModel.status);
                                }
                            }
                            @Override
                            public void onError(ANError anError) {
                                // handle error
                            }
                        });
            }
        });
    }
}
