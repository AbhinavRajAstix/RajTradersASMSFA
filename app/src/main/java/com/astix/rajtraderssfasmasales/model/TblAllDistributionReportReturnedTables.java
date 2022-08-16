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

public class TblAllDistributionReportReturnedTables {
    private List<TblDayWorkSummary> tblDayWorkSummary;

    @SerializedName("tblRptDistributionDate")
    @Expose
    private List<TblRptDistributionDate> tblRptDistributionDate = null;

    @SerializedName("tblRptManDays")
    @Expose
    private List<TblRptManDays> tblRptManDays = null;

    @SerializedName("tblRptPrimaryVol")
    @Expose
    private List<TblRptPrimaryVol> tblRptPrimaryVol = null;

    @SerializedName("tblRptSecVol")
    @Expose
    private List<TblRptSecVol> tblRptSecVol = null;

    @SerializedName("tblRptDistribution")
    @Expose
    private List<TblRptDistribution> tblRptDistribution = null;

    @SerializedName("tblRptDistribution2X")
    @Expose
    private List<TblRptDistribution> tblRptDistribution2x = null;

    @SerializedName("tblRptSalesmanData")
    @Expose
    private List<TblRptSalesVersionIndivisual> tblRptSalesVersionIndivisual = null;

    public List<TblRptSalesVersionIndivisual> getTblRptSalesVersionIndivisual() {
        return tblRptSalesVersionIndivisual;
    }

    public void setTblRptSalesVersionIndivisual(List<TblRptSalesVersionIndivisual> tblRptSalesVersionIndivisual) {
        this.tblRptSalesVersionIndivisual = tblRptSalesVersionIndivisual;
    }

    public List<TblRptDistribution> getTblRptDistribution2x() {
        return tblRptDistribution2x;
    }

    public void setTblRptDistribution2x(List<TblRptDistribution> tblRptDistribution2x) {
        this.tblRptDistribution2x = tblRptDistribution2x;
    }

    public List<TblRptDistributionDate> getTblRptDistributionDate() {
        return tblRptDistributionDate;
    }

    public void setTblRptDistributionDate(List<TblRptDistributionDate> tblRptDistributionDate) {
        this.tblRptDistributionDate = tblRptDistributionDate;
    }

    public List<TblRptManDays> getTblRptManDays() {
        return tblRptManDays;
    }

    public void setTblRptManDays(List<TblRptManDays> tblRptManDays) {
        this.tblRptManDays = tblRptManDays;
    }

    public List<TblRptPrimaryVol> getTblRptPrimaryVol() {
        return tblRptPrimaryVol;
    }

    public void setTblRptPrimaryVol(List<TblRptPrimaryVol> tblRptPrimaryVol) {
        this.tblRptPrimaryVol = tblRptPrimaryVol;
    }

    public List<TblRptSecVol> getTblRptSecVol() {
        return tblRptSecVol;
    }

    public void setTblRptSecVol(List<TblRptSecVol> tblRptSecVol) {
        this.tblRptSecVol = tblRptSecVol;
    }

    public List<TblRptDistribution> getTblRptDistribution() {
        return tblRptDistribution;
    }

    public void setTblRptDistribution(List<TblRptDistribution> tblRptDistribution) {
        this.tblRptDistribution = tblRptDistribution;
    }

    public List<TblDayWorkSummary> getTblDayWorkSummary() {
        return tblDayWorkSummary;
    }

    public void setTblDayWorkSummary(List<TblDayWorkSummary> tblDayWorkSummary) {
        this.tblDayWorkSummary = tblDayWorkSummary;
    }
}
