package com.stockbook.ims.network;


/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */

public interface ApiClient {

    public static final String BASE_URL = "http://192.168.2.12/ims/index.php/api/";
    String LOG_IN = "login";
    String API_KEY = "api_key";
    String USER_ID = "user_id";
    String LOG_OUT = "logout";
    String EMP_NAME = "empName";
    String ROLE = "role";
}
