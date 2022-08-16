
package com.astix.rajtraderssfasmasales.reports.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblRptDistribution {

    @SerializedName("Description")
    private String description;
    @Expose
    private int flgCollapse;
    @SerializedName("MTD")
    private int mTD;
    @SerializedName("YesterDay")
    private int newYesterDay;
    @SerializedName("YTD_Tgt")
    private String yTDTgt;
    @SerializedName("YTD")
    private int yTDTillDate;
    @SerializedName("flgLevel")
    private int flgLevel;

    @SerializedName("Active_Cov")
    private String Active_Cov;
    @SerializedName("P3M")
    private String P3M;
    @SerializedName("New_Dstrbn")
    private String New_Dstrbn;
    @SerializedName("Dstrbn_Lost")
    private String Dstrbn_Lost;

    @SerializedName("SectionId")
    private int SectionId;

    @SerializedName("CoverageAreaNodeID")
    private int CoverageAreaNodeID;

    @SerializedName("CoverageAreaNodeType")
    private int CoverageAreaNodeType;

    public String getActive_Cov() {
        return Active_Cov;
    }

    public void setActive_Cov(String active_Cov) {
        Active_Cov = active_Cov;
    }

    public String getP3M() {
        return P3M;
    }

    public void setP3M(String p3M) {
        P3M = p3M;
    }

    public String getNew_Dstrbn() {
        return New_Dstrbn;
    }

    public void setNew_Dstrbn(String new_Dstrbn) {
        New_Dstrbn = new_Dstrbn;
    }

    public String getDstrbn_Lost() {
        return Dstrbn_Lost;
    }

    public void setDstrbn_Lost(String dstrbn_Lost) {
        Dstrbn_Lost = dstrbn_Lost;
    }

    public int getFlgLevel() {
        return flgLevel;
    }

    public void setFlgLevel(int flgLevel) {
        this.flgLevel = flgLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlgCollapse() {
        return flgCollapse;
    }

    public void setFlgCollapse(int flgCollapse) {
        this.flgCollapse = flgCollapse;
    }

    public int getMTD() {
        return mTD;
    }

    public void setMTD(int mTD) {
        this.mTD = mTD;
    }

    public int getNewYesterDay() {
        return newYesterDay;
    }

    public void setNewYesterDay(int newYesterDay) {
        this.newYesterDay = newYesterDay;
    }

    public String getYTDTgt() {
        return yTDTgt;
    }

    public void setYTDTgt(String yTDTgt) {
        this.yTDTgt = yTDTgt;
    }

    public int getYTDTillDate() {
        return yTDTillDate;
    }

    public void setYTDTillDate(int yTDTillDate) {
        this.yTDTillDate = yTDTillDate;
    }

    public int getSectionId() {
        return SectionId;
    }

    public void setSectionId(int sectionId) {
        SectionId = sectionId;
    }

    public int getCoverageAreaNodeID() {
        return CoverageAreaNodeID;
    }

    public void setCoverageAreaNodeID(int coverageAreaNodeID) {
        CoverageAreaNodeID = coverageAreaNodeID;
    }

    public int getCoverageAreaNodeType() {
        return CoverageAreaNodeType;
    }

    public void setCoverageAreaNodeType(int coverageAreaNodeType) {
        CoverageAreaNodeType = coverageAreaNodeType;
    }
}
