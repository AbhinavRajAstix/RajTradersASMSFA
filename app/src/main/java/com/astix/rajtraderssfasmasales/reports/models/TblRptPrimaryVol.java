
package com.astix.rajtraderssfasmasales.reports.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblRptPrimaryVol {

    @SerializedName("Description")
    private String description;
    @Expose
    private int flgCollapse;
    @SerializedName("MTD_Delivered")
    private String mTDDelivered;
    @SerializedName("MTD_Tgt")
    private String mTDTgt;
    @SerializedName("Pending Delivery")
    private String pendingDelivery;
    @SerializedName("Yesterday")
    private String yesterday;
    @SerializedName("flgLevel")
    private int flgLevel;

    @SerializedName("SectionId")
    private int SectionId;

    @SerializedName("CoverageAreaNodeID")
    private int CoverageAreaNodeID;

    @SerializedName("CoverageAreaNodeType")
    private int CoverageAreaNodeType;

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

    public String getMTDDelivered() {
        return mTDDelivered;
    }

    public void setMTDDelivered(String mTDDelivered) {
        this.mTDDelivered = mTDDelivered;
    }

    public String getMTDTgt() {
        return mTDTgt;
    }

    public void setMTDTgt(String mTDTgt) {
        this.mTDTgt = mTDTgt;
    }

    public String getPendingDelivery() {
        return pendingDelivery;
    }

    public void setPendingDelivery(String pendingDelivery) {
        this.pendingDelivery = pendingDelivery;
    }

    public String getYesterday() {
        return yesterday;
    }

    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
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
