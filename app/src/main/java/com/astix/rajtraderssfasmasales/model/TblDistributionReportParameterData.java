package com.astix.rajtraderssfasmasales.model;

public class TblDistributionReportParameterData {

    String PDACode;
    String ForDate;
    String PersonNodeID;
    String PersonNodeType;
    int CoverageAreaNodeID;
    int CoverageAreaNodeType;

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

    public String getPDACode() {
        return PDACode;
    }

    public void setPDACode(String PDACode) {
        this.PDACode = PDACode;
    }

    public String getForDate() {
        return ForDate;
    }

    public void setForDate(String forDate) {
        ForDate = forDate;
    }

    public String getPersonNodeID() {
        return PersonNodeID;
    }

    public void setPersonNodeID(String personNodeID) {
        PersonNodeID = personNodeID;
    }

    public String getPersonNodeType() {
        return PersonNodeType;
    }

    public void setPersonNodeType(String personNodeType) {
        PersonNodeType = personNodeType;
    }

    @Override
    public String toString() {
        return "TblDistributionReportParameterData{" +
                "PDACode='" + PDACode + '\'' +
                ", ForDate='" + ForDate + '\'' +
                ", PersonNodeID='" + PersonNodeID + '\'' +
                ", PersonNodeType='" + PersonNodeType + '\'' +
                ", CoverageAreaNodeID=" + CoverageAreaNodeID +
                ", CoverageAreaNodeType=" + CoverageAreaNodeType +
                '}';
    }
}
