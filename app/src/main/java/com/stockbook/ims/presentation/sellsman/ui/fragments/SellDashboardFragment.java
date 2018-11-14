package com.stockbook.ims.presentation.sellsman.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stockbook.ims.R;
import com.stockbook.ims.domain.executor.impl.ThreadExecutor;
import com.stockbook.ims.presentation.model.DashboardModel;
import com.stockbook.ims.presentation.sellsman.presenters.SellDashboardPresenter;
import com.stockbook.ims.presentation.sellsman.presenters.impl.SellDashboardPresenterImpl;
import com.stockbook.ims.threading.MainThreadImpl;

/**
 * Created by Faizul Haque Nayan on 18/11/14.
 */

public class SellDashboardFragment extends Fragment implements SellDashboardPresenter.View{

    private View viewMain;
    private SellDashboardPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMain = inflater.inflate(R.layout.fragment_sell_dashboard, container, false);
        mPresenter = new SellDashboardPresenterImpl( ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),
                this, getContext());
        return viewMain;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void setOnDashboardData(DashboardModel dashboardModel) {


    }
}
