package com.stockbook.ims.presentation.sellsman.presenters.impl;

import android.content.Context;

import com.stockbook.ims.domain.executor.Executor;
import com.stockbook.ims.domain.executor.MainThread;
import com.stockbook.ims.domain.interactors.sellsman.SellDashboardInteractor;
import com.stockbook.ims.domain.interactors.sellsman.impl.SellDashboardInteractorImpl;
import com.stockbook.ims.presentation.AbstractPresenter;
import com.stockbook.ims.presentation.model.DashboardModel;
import com.stockbook.ims.presentation.sellsman.presenters.SellDashboardPresenter;

/**
 * Created by Faizul Haque Nayan on 18/11/12.
 */

public class SellDashboardPresenterImpl extends AbstractPresenter implements SellDashboardPresenter,
        SellDashboardInteractor.CallBack {

    private SellDashboardPresenter.View mView;
    private Context mContext;
    private Executor mExecutor;
    private MainThread mMainThread;

    public SellDashboardPresenterImpl(Executor executor, MainThread mainThread, View view,
                                      Context context) {
        super(executor, mainThread);
        this.mContext = context;
        this.mView = view;
        this.mExecutor = executor;
        this.mMainThread = mainThread;
    }


    @Override
    public void resume() {
        new SellDashboardInteractorImpl(mExecutor, mMainThread, mContext, this).execute();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void setDashboardInfo(DashboardModel dashboardModel) {
        if(dashboardModel != null){
            mView.setOnDashboardData(dashboardModel);
        }
    }
}
