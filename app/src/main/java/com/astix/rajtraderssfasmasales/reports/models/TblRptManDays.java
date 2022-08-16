
package com.astix.rajtraderssfasmasales.reports.models;

import com.google.gson.annotations.SerializedName;

public class TblRptManDays {

    @SerializedName("Description")
    private String description;

    @SerializedName("In Field_YTD^1")
    private int inFieldYTD_1;

    @SerializedName("In Field_P3M^11")
    private int inFieldP3M_2;

    @SerializedName("In Field_MTD^21")
    private int inFieldMTD_3;

    @SerializedName("In Field_Yesterday^31")
    private int inFieldYesterday_4;

    @SerializedName("ValueType")
    private int ValueType;

    @SerializedName("SectionId")
    private int SectionId;

    @SerializedName("CoverageAreaNodeID")
    private int CoverageAreaNodeID;

    @SerializedName("CoverageAreaNodeType")
    private int CoverageAreaNodeType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInFieldYTD_1() {
        return inFieldYTD_1;
    }

    public void setInFieldYTD_1(int inFieldYTD_1) {
        this.inFieldYTD_1 = inFieldYTD_1;
    }

    public int getInFieldP3M_2() {
        return inFieldP3M_2;
    }

    public void setInFieldP3M_2(int inFieldP3M_2) {
        this.inFieldP3M_2 = inFieldP3M_2;
    }

    public int getInFieldMTD_3() {
        return inFieldMTD_3;
    }

    public void setInFieldMTD_3(int inFieldMTD_3) {
        this.inFieldMTD_3 = inFieldMTD_3;
    }

    public int getInFieldYesterday_4() {
        return inFieldYesterday_4;
    }

    public void setInFieldYesterday_4(int inFieldYesterday_4) {
        this.inFieldYesterday_4 = inFieldYesterday_4;
    }

    public int getValueType() {
        return ValueType;
    }

    public void setValueType(int valueType) {
        ValueType = valueType;
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
