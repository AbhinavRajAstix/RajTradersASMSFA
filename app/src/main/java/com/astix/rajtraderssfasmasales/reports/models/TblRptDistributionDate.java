
package com.astix.rajtraderssfasmasales.reports.models;

import com.google.gson.annotations.SerializedName;

public class TblRptDistributionDate {

    @SerializedName("RptDate")
    private String rptDate;

    public String getRptDate() {
        return rptDate;
    }

    public void setRptDate(String rptDate) {
        this.rptDate = rptDate;
    }

}
