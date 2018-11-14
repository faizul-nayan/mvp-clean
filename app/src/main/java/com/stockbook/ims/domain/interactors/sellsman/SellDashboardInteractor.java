package com.stockbook.ims.domain.interactors.sellsman;

import com.stockbook.ims.presentation.model.DashboardModel;

/**
 * Created by Faizul Haque Nayan on 18/11/12.
 */

public interface SellDashboardInteractor {

    interface CallBack{
        void setDashboardInfo(DashboardModel dashboardModel);
    }
}
