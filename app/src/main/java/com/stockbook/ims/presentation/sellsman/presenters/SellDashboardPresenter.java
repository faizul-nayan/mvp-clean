package com.stockbook.ims.presentation.sellsman.presenters;

import com.stockbook.ims.presentation.BasePresenter;
import com.stockbook.ims.presentation.BaseView;
import com.stockbook.ims.presentation.model.DashboardModel;

/**
 * Created by Faizul Haque Nayan on 18/11/12.
 */

public interface SellDashboardPresenter extends BasePresenter{

    interface View extends BaseView{
        void setOnDashboardData(DashboardModel dashboardModel);
    }
}
