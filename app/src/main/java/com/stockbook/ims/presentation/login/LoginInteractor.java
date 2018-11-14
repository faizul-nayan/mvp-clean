package com.stockbook.ims.presentation.login;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Faizul Haque Nayan on 18/10/23.
 */

public interface LoginInteractor {

    interface Callback{
        void loginSuccess(JSONObject jsonObject) throws JSONException;
        void loginFailed(JSONObject jsonObject) throws JSONException;
    }
}
