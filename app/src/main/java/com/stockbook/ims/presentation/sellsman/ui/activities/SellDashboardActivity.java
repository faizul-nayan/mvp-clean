package com.stockbook.ims.presentation.sellsman.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stockbook.ims.R;
import com.stockbook.ims.domain.executor.MainThread;
import com.stockbook.ims.domain.executor.impl.ThreadExecutor;
import com.stockbook.ims.network.ApiClient;
import com.stockbook.ims.network.LogoutInteractor;
import com.stockbook.ims.network.LogoutInteractorImpl;
import com.stockbook.ims.presentation.login.LoginActivity;
import com.stockbook.ims.presentation.model.DashboardModel;
import com.stockbook.ims.presentation.sellsman.presenters.SellDashboardPresenter;
import com.stockbook.ims.presentation.sellsman.presenters.impl.SellDashboardPresenterImpl;
import com.stockbook.ims.presentation.sellsman.ui.fragments.SellDashboardFragment;
import com.stockbook.ims.presentation.sellsman.ui.fragments.SellFragment;
import com.stockbook.ims.threading.MainThreadImpl;
import com.stockbook.ims.utills.MySharePreferences;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellDashboardActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        LogoutInteractor.Callback{

    private TextView mUserNameTV;

    private TextView mRoleTV;

    private MySharePreferences mySharePreferences;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new SellFragment()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new SellDashboardFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_deshboard);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, new SellDashboardFragment()).commit();
        mUserNameTV = (TextView) view.findViewById(R.id.nameTextView);
        mRoleTV = (TextView) view.findViewById(R.id.roleTextView);
        mySharePreferences = new MySharePreferences(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            new LogoutInteractorImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                    MySharePreferences.getInstance(this).getString(ApiClient.USER_ID), this, this).execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onLogoutSuccess(JSONObject jsonObject) throws JSONException {
        if (jsonObject.get("status").toString().equalsIgnoreCase("Success")){
            new MySharePreferences(this).setBoolean(MySharePreferences.IS_REMEMBER, false);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onLogoutFailed(JSONObject jsonObject) throws JSONException {
        Toast.makeText(this, "Faild to logout", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mUserNameTV.setText(""+ new MySharePreferences(this).getString(ApiClient.EMP_NAME));
        mRoleTV.setText(""+""+ new MySharePreferences(this).getString(ApiClient.ROLE));
    }

}
