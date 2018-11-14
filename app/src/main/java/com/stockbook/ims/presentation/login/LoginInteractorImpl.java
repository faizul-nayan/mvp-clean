package com.stockbook.ims.presentation.login;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.stockbook.ims.domain.executor.Executor;
import com.stockbook.ims.domain.executor.MainThread;
import com.stockbook.ims.domain.interactors.base.AbstractInteractor;
import com.stockbook.ims.network.ApiClient;
import com.stockbook.ims.utills.MySharePreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Faizul Haque Nayan on 18/10/23.
 */

public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {

    private Executor mExecutor;
    private MainThread mMainThread;
    private String mUserName;
    private String mPassword;
    private Context mContext;
    private Callback mCallback;

    public LoginInteractorImpl(Executor threadExecutor, MainThread mainThread, String userName,
                               String password, Context context, Callback callback) {
        super(threadExecutor, mainThread);
        this.mExecutor = threadExecutor;
        this.mMainThread = mainThread;
        this.mUserName = userName;
        this.mPassword = password;
        this.mContext = context;
        this.mCallback = callback;
    }

    @Override
    public void run() {

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                AndroidNetworking.post(ApiClient.BASE_URL+"auth")
                        .addBodyParameter("username", mUserName)
                        .addBodyParameter("password", mPassword)
                        .setTag(ApiClient.LOG_IN)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if(response.get("status").toString().equalsIgnoreCase("Success")){
                                        MySharePreferences.getInstance(mContext).setString(ApiClient.API_KEY, response.getString(ApiClient.API_KEY).toString());
                                        MySharePreferences.getInstance(mContext).setString(ApiClient.USER_ID, response.getString(ApiClient.USER_ID).toString());
                                        MySharePreferences.getInstance(mContext).setString(ApiClient.EMP_NAME, response.getString(ApiClient.EMP_NAME).toString());
                                        MySharePreferences.getInstance(mContext).setString(ApiClient.ROLE, response.getString(ApiClient.ROLE).toString());
                                        mCallback.loginSuccess(response);
                                    }
                                    else if (response.get("status").toString().equalsIgnoreCase("Failed")){
                                        mCallback.loginFailed(response);
                                    }

                                } catch (JSONException e) {
                                    Log.e("response", e.getMessage().toString());
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("onError", anError.getMessage());
                            }
                        });
            }
        });
    }
}
