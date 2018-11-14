package com.stockbook.ims.presentation.login;

import android.content.Context;
import android.util.Log;

import com.stockbook.ims.domain.executor.Executor;
import com.stockbook.ims.domain.executor.MainThread;
import com.stockbook.ims.presentation.AbstractPresenter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Faizul Haque Nayan on 18/10/23.
 */

public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenter, LoginInteractor.Callback {

    private LoginView mView;
    private Context mContext;
    private Executor mExecutor;
    private MainThread mMainThread;

    public LoginPresenterImpl(Executor executor, MainThread mainThread, LoginView view, Context context) {
        super(executor, mainThread);
        this.mView = view;
        this.mContext = context;
        this.mExecutor = executor;
        this.mMainThread = mainThread;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void validateCredentials(String userName, String password) {
        if (mView != null) {
            mView.showProgress();
        }
        Log.e("presenter", "validate");
        new LoginInteractorImpl(mExecutor, mMainThread, userName, password,mContext, this).execute();
    }

    @Override
    public void loginSuccess(JSONObject jsonObject) throws JSONException {
        mView.onLoginSuccess(jsonObject);
    }

    @Override
    public void loginFailed(JSONObject jsonObject) throws JSONException {
        mView.onLoginFailed(jsonObject);
    }
}
