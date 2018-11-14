package com.stockbook.ims.presentation.login;

import com.stockbook.ims.presentation.BaseView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Faizul Haque Nayan on 18/10/23.
 */

public interface LoginView extends BaseView {

    void onLoginSuccess(JSONObject jsonObject) throws JSONException;
    void onLoginFailed(JSONObject jsonObject) throws JSONException;
}
