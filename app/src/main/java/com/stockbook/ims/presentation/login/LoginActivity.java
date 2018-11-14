package com.stockbook.ims.presentation.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.stockbook.ims.R;
import com.stockbook.ims.domain.executor.impl.ThreadExecutor;
import com.stockbook.ims.presentation.sellsman.ui.activities.SellDashboardActivity;
import com.stockbook.ims.threading.MainThreadImpl;
import com.stockbook.ims.utills.AeSimpleMD5;
import com.stockbook.ims.utills.CheckInternetConnection;
import com.stockbook.ims.utills.ConnectivityShow;
import com.stockbook.ims.utills.MySharePreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.usernameTV)
    TextView mUserName;
    @BindView(R.id.passwordTV)
    TextView mPassword;
    @BindView(R.id.rememberCheck)
    CheckBox mRememberCheck;
    private ProgressDialog mProgressDialog;

    private LoginPresenter mPresenter;
    private ConnectivityShow mConnectivityShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, this);
    }

    public void login(View view) {
        if (mUserName.getText().toString().isEmpty()) {

            mUserName.setError("Username can't be empty...");
        } else if (mPassword.getText().toString().isEmpty()) {

            mPassword.setError("Password can't be empty...");
        }
        else {
            if(new CheckInternetConnection().haveNetworkConnection(this)){
                mPresenter.validateCredentials(mUserName.getText().toString(), convert(mPassword.getText().toString()));
            }
            else {
                mConnectivityShow = new ConnectivityShow(this);
                mConnectivityShow.show();
            }
        }
    }

    @Override
    public void showProgress() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mConnectivityShow != null) {
            mConnectivityShow.dismiss();
        }
    }

    private String convert(String s) {

        String hash = null;

        try {
            hash = AeSimpleMD5.MD5(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Log.e("Hashing", hash);

        return hash;

    }

    @Override
    public void onLoginSuccess(JSONObject jsonObject) throws JSONException{
        if(mRememberCheck.isChecked()){
            Log.e("checklogin","checked");
            new MySharePreferences(this).setBoolean(MySharePreferences.IS_REMEMBER, true);
        }
        Intent intent = new Intent(this, SellDashboardActivity.class);
        //intent.putExtra(ApiClient.API_KEY, jsonObject.getString(ApiClient.API_KEY));
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailed(JSONObject jsonObject) throws JSONException {
        showError(jsonObject.getString("message").toString());
    }
}
