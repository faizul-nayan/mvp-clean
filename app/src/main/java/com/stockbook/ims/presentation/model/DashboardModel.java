package com.stockbook.ims.presentation.model;

import java.util.ArrayList;

/**
 * Created by Faizul Haque Nayan on 18/11/12.
 */

public class DashboardModel {

    public String empName;
    public String role;
    public String locationName;
    public String status;
    public ArrayList<StockInfo> stockInfo;
    public ArrayList<StockInfo> lowestStockInfo;
    public ArrayList<StockInfo> outOfStockInfo;
    public AtGlance atGlanceInfo;
    public ArrayList<PurchaseInfo> purchaseInfo;
    public ArrayList<OrderInfo> orderInformation;
    public String customerDueInformation;
    public String retailerDueInformation;

}
