package com.astix.rajtraderssfasmasales.model;

public class TblSalesReportStoreListParameterData {

    String PDACode;
    String RptDate;
    int ValueType;
    int PeriodType;
    int PersonNodeID;
    int PersonNodeType;

    int CoverageAreaNodeID;
    int CoverageAreaNodeType;
    int SectionID;

    public String getRptDate() {
        return RptDate;
    }

    public void setRptDate(String rptDate) {
        RptDate = rptDate;
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

    public int getSectionID() {
        return SectionID;
    }

    public void setSectionID(int sectionID) {
        SectionID = sectionID;
    }

    public int getPersonNodeID() {
        return PersonNodeID;
    }

    public void setPersonNodeID(int personNodeID) {
        PersonNodeID = personNodeID;
    }

    public int getPersonNodeType() {
        return PersonNodeType;
    }

    public void setPersonNodeType(int personNodeType) {
        PersonNodeType = personNodeType;
    }

    public String getPDACode() {
        return PDACode;
    }

    public void setPDACode(String PDACode) {
        this.PDACode = PDACode;
    }


    public int getValueType() {
        return ValueType;
    }

    public void setValueType(int valueType) {
        ValueType = valueType;
    }

    public int getPeriodType() {
        return PeriodType;
    }

    public void setPeriodType(int periodType) {
        PeriodType = periodType;
    }


}
