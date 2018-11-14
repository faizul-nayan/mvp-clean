package com.stockbook.ims.network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Faizul Haque Nayan on 18/10/23.
 */

public interface LogoutInteractor {

    interface Callback{
        void onLogoutSuccess(JSONObject jsonObject) throws JSONException;
        void onLogoutFailed(JSONObject jsonObject) throws JSONException;
    }
}
