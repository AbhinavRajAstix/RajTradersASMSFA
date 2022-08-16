
package com.astix.rajtraderssfasmasales.reports.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblRptSalesVersionIndivisual {

    @SerializedName("PersonNodeID")
    private String PersonNodeID;

    @SerializedName("Description")
    private String description;

    @Expose
    private int flgCollapse;

    @SerializedName("Dstrbn_MTD^21")
    private String DstrbnMTD_1;

    @SerializedName("Dstrbn_Yesterday^31")
    private String DstrbnYesterday_2;


    @SerializedName("Sales_MTD^22")
    private String SalesMTD_3;

    @SerializedName("Sales_Yesterday^32")
    private String SalesYesterday_4;


    @SerializedName("Visits_Yesterday^33")
    private String VisitsYesterday_5;

    @SerializedName("Ordr")
    private int Ordr;

    @SerializedName("flgLevel")
    private int flgLevel;

    @SerializedName("SectionId")
    private int SectionId;

    @SerializedName("CoverageAreaNodeId")
    private int CoverageAreaNodeID;

    @SerializedName("CoverageAreaNodeType")
    private int CoverageAreaNodeType;

    public String getPersonNodeID() {
        return PersonNodeID;
    }

    public void setPersonNodeID(String personNodeID) {
        PersonNodeID = personNodeID;
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

    public String getDstrbnMTD_1() {
        return DstrbnMTD_1;
    }

    public void setDstrbnMTD_1(String dstrbnMTD_1) {
        DstrbnMTD_1 = dstrbnMTD_1;
    }

    public String getDstrbnYesterday_2() {
        return DstrbnYesterday_2;
    }

    public void setDstrbnYesterday_2(String dstrbnYesterday_2) {
        DstrbnYesterday_2 = dstrbnYesterday_2;
    }

    public String getSalesMTD_3() {
        return SalesMTD_3;
    }

    public void setSalesMTD_3(String salesMTD_3) {
        SalesMTD_3 = salesMTD_3;
    }

    public String getSalesYesterday_4() {
        return SalesYesterday_4;
    }

    public void setSalesYesterday_4(String salesYesterday_4) {
        SalesYesterday_4 = salesYesterday_4;
    }

    public String getVisitsYesterday_5() {
        return VisitsYesterday_5;
    }

    public void setVisitsYesterday_5(String visitsYesterday_5) {
        VisitsYesterday_5 = visitsYesterday_5;
    }

    public int getOrdr() {
        return Ordr;
    }

    public void setOrdr(int ordr) {
        Ordr = ordr;
    }

    public int getFlgLevel() {
        return flgLevel;
    }

    public void setFlgLevel(int flgLevel) {
        this.flgLevel = flgLevel;
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
