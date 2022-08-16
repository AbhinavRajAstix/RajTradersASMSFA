package com.astix.rajtraderssfasmasales.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TblAllSalesDistributionFleetReturnedTables {


    @SerializedName("tblSummaryReportDetails")
    @Expose
    private List<TblSummaryReportDetails> tblSummaryReportDetailsList = null;

    public List<TblSummaryReportDetails> getTblSummaryReportDetailsList() {
        return tblSummaryReportDetailsList;
    }

    public void setTblSummaryReportDetailsList(List<TblSummaryReportDetails> tblSummaryReportDetailsList) {
        this.tblSummaryReportDetailsList = tblSummaryReportDetailsList;
    }
}
