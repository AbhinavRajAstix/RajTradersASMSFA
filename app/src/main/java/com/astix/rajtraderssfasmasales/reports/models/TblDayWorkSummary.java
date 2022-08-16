
package com.astix.rajtraderssfasmasales.reports.models;

import com.google.gson.annotations.SerializedName;

public class TblDayWorkSummary {

    @SerializedName("Actual Done")
    private String actualDone;
    @SerializedName("Actual Expected")
    private String actualExpected;
    @SerializedName("Description")
    private String description;
    @SerializedName("GroupName")
    private String groupName;
    @SerializedName("Planned")
    private int planned;
    @SerializedName("Prod Expected")
    private String prodExpected;

    public int getPersonNodeID() {
        return personNodeID;
    }

    public void setPersonNodeID(int personNodeID) {
        this.personNodeID = personNodeID;
    }

    public int getPersonNodeType() {
        return personNodeType;
    }

    public void setPersonNodeType(int personNodeType) {
        this.personNodeType = personNodeType;
    }

    @SerializedName("PersonNodeID")
    private int personNodeID;

    public String getDayWorkDate() {
        return dayWorkDate;
    }

    public void setDayWorkDate(String dayWorkDate) {
        this.dayWorkDate = dayWorkDate;
    }

    @SerializedName("DayWorkDate")
    private String dayWorkDate;

    @SerializedName("PersonNodeType")
    private int personNodeType;

    public String getActualDone() {
        return actualDone;
    }

    public void setActualDone(String actualDone) {
        this.actualDone = actualDone;
    }

    public String getActualExpected() {
        return actualExpected;
    }

    public void setActualExpected(String actualExpected) {
        this.actualExpected = actualExpected;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getPlanned() {
        return planned;
    }

    public void setPlanned(int planned) {
        this.planned = planned;
    }

    public String getProdExpected() {
        return prodExpected;
    }

    public void setProdExpected(String prodExpected) {
        this.prodExpected = prodExpected;
    }

    @SerializedName("SectionId")
    private int SectionId;

    @SerializedName("CoverageAreaNodeID")
    private int CoverageAreaNodeID;

    @SerializedName("CoverageAreaNodeType")
    private int CoverageAreaNodeType;

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
