
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblSubordinateList {

    @SerializedName("CoverageArea")
    private String coverageArea;
    @Expose
    private int flgMarketVisitStarted;
    @Expose
    private int flgPersonType;
    @SerializedName("ParentPersonID")
    private int parentPersonID;
    @SerializedName("ParentPersonType")
    private int parentPersonType;
    @SerializedName("PersonName")
    private String personName;
    @SerializedName("PersonNodeID")
    private int personNodeID;
    @SerializedName("PersonNodeType")
    private int personNodeType;

    @SerializedName("SectorID")
    private int SectorID;

    public int getSectorID() {
        return SectorID;
    }

    public void setSectorID(int sectorID) {
        SectorID = sectorID;
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

    @SerializedName("CoverageAreaNodeID")
    private int CoverageAreaNodeID;
    @SerializedName("CoverageAreaNodeType")
    private int CoverageAreaNodeType;





    public String getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(String coverageArea) {
        this.coverageArea = coverageArea;
    }

    public int getFlgMarketVisitStarted() {
        return flgMarketVisitStarted;
    }

    public void setFlgMarketVisitStarted(int flgMarketVisitStarted) {
        this.flgMarketVisitStarted = flgMarketVisitStarted;
    }

    public int getFlgPersonType() {
        return flgPersonType;
    }

    public void setFlgPersonType(int flgPersonType) {
        this.flgPersonType = flgPersonType;
    }

    public int getParentPersonID() {
        return parentPersonID;
    }

    public void setParentPersonID(int parentPersonID) {
        this.parentPersonID = parentPersonID;
    }

    public int getParentPersonType() {
        return parentPersonType;
    }

    public void setParentPersonType(int parentPersonType) {
        this.parentPersonType = parentPersonType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

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

}
