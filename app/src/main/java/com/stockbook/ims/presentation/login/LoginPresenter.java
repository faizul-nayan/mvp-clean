package com.stockbook.ims.presentation.login;

import com.stockbook.ims.presentation.BasePresenter;

/**
 * Created by Faizul Haque Nayan on 18/10/23.
 */

public interface LoginPresenter extends BasePresenter{
    void validateCredentials(String userName, String password);
}
