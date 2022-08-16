package com.astix.rajtraderssfasmasales.model;

import com.astix.rajtraderssfasmasales.reports.models.TblDayWorkSummary;
import com.astix.rajtraderssfasmasales.reports.models.TblRptDistribution;
import com.astix.rajtraderssfasmasales.reports.models.TblRptDistributionDate;
import com.astix.rajtraderssfasmasales.reports.models.TblRptManDays;
import com.astix.rajtraderssfasmasales.reports.models.TblRptPrimaryVol;
import com.astix.rajtraderssfasmasales.reports.models.TblRptSalesVersionIndivisual;
import com.astix.rajtraderssfasmasales.reports.models.TblRptSecVol;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TblAllDistributionPersonSKUReturnTableList {

    @SerializedName("tblDistributorDailyStockDetailsReport")
    @Expose
    private List<TblDistributorDailyStockDetailsReport> tblDistributorDailyStockDetailsReport = null;

    @SerializedName("tblDistributorDailySKUDetailsReport")
    @Expose
    private List<TblDistributorDailyStockDetailsSKUReport> tblDistributorDailyStockDetailsSKUReport = null;

    public List<TblDistributorDailyStockDetailsReport> getTblDistributorDailyStockDetailsReport() {
        return tblDistributorDailyStockDetailsReport;
    }

    public void setTblDistributorDailyStockDetailsReport(List<TblDistributorDailyStockDetailsReport> tblDistributorDailyStockDetailsReport) {
        this.tblDistributorDailyStockDetailsReport = tblDistributorDailyStockDetailsReport;
    }

    public List<TblDistributorDailyStockDetailsSKUReport> getTblDistributorDailyStockDetailsSKUReport() {
        return tblDistributorDailyStockDetailsSKUReport;
    }

    public void setTblDistributorDailyStockDetailsSKUReport(List<TblDistributorDailyStockDetailsSKUReport> tblDistributorDailyStockDetailsSKUReport) {
        this.tblDistributorDailyStockDetailsSKUReport = tblDistributorDailyStockDetailsSKUReport;
    }
}
