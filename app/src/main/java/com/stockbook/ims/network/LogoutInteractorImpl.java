package com.stockbook.ims.network;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.stockbook.ims.domain.executor.Executor;
import com.stockbook.ims.domain.executor.MainThread;
import com.stockbook.ims.domain.interactors.base.AbstractInteractor;
import com.stockbook.ims.utills.MySharePreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Faizul Haque Nayan on 18/10/23.
 */

public class LogoutInteractorImpl extends AbstractInteractor implements LogoutInteractor{

    private Executor mExecutor;
    private MainThread mMainThread;
    private String mId;
    private Context mContext;
    private Callback mCallback;

    public LogoutInteractorImpl(Executor threadExecutor, MainThread mainThread, String id,
                                Context context, Callback callback) {
        super(threadExecutor, mainThread);
        this.mExecutor = threadExecutor;
        this.mMainThread = mainThread;
        this.mId = id;
        this.mCallback = callback;
        this.mContext = context;
    }

    @Override
    public void run() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                AndroidNetworking.post(ApiClient.BASE_URL+"auth/logout")
                        .addHeaders("Authorization", MySharePreferences.getInstance(mContext).getString(ApiClient.API_KEY))
                        .addBodyParameter(ApiClient.USER_ID, MySharePreferences.getInstance(mContext).getString(ApiClient.USER_ID))
                        .setTag(ApiClient.LOG_OUT)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if(response.get("status").toString().equalsIgnoreCase("Success")){
                                        mCallback.onLogoutSuccess(response);
                                    }
                                    else if (response.get("status").toString().equalsIgnoreCase("Failed")){
                                        mCallback.onLogoutFailed(response);
                                    }

                                } catch (JSONException e) {
                                    Log.e("response", e.getMessage().toString());
                                }
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });
    }
}
