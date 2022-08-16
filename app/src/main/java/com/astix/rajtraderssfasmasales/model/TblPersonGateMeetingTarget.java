
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TblPersonGateMeetingTarget {

    @SerializedName("CovAreaNodeID")
    @Expose
    private String CovAreaNodeID;
    @SerializedName("CovAreaNodeType")
    @Expose
    private String CovAreaNodeType;
    @SerializedName("CovArea")
    @Expose
    private String CovArea;
    @SerializedName("PersonNodeID")
    @Expose
    private String PersonNodeID;
    @SerializedName("PersonNodeType")
    @Expose
    private String PersonNodeType;
    @SerializedName("Personname")
    @Expose
    private String Personname;
    @SerializedName("Dstrbn_Tgt")
    @Expose
    private double Dstrbn_Tgt;
    @SerializedName("Sales_Tgt")
    @Expose
    private double Sales_Tgt;


    @SerializedName("Month Target")
    @Expose
    private double MonthTarget;

    @SerializedName("Achievement")
    @Expose
    private double Achievement;

    @SerializedName("RR Required")
    @Expose
    private double RRRequired;

    @SerializedName("Todays Store")
    @Expose
    private double TodaysStore;

    @SerializedName("Productive Stores(P4W)")
    @Expose
    private double ProdStoreLastCall;

    @SerializedName("LastRouteVisitDate")
    @Expose
    private String LastRouteVisitDate;


    @SerializedName("TodaysRouteNodeID")
    @Expose
    private int TodaysRouteNodeID;

    @SerializedName("TodaysRouteNodeType")
    @Expose
    private int TodaysRouteNodeType;

    @SerializedName("EntryDate")
    @Expose
    private String EntryDate;

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    private List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> tblPersonGateMeetingFocusedProductCoverageWiseMstrList;

    public List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> getTblPersonGateMeetingFocusedProductCoverageWiseMstrList() {
        return tblPersonGateMeetingFocusedProductCoverageWiseMstrList;
    }

    public void setTblPersonGateMeetingFocusedProductCoverageWiseMstrList(List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> tblPersonGateMeetingFocusedProductCoverageWiseMstrList) {
        this.tblPersonGateMeetingFocusedProductCoverageWiseMstrList = tblPersonGateMeetingFocusedProductCoverageWiseMstrList;
    }






    public int getTodaysRouteNodeID() {
        return TodaysRouteNodeID;
    }

    public void setTodaysRouteNodeID(int todaysRouteNodeID) {
        TodaysRouteNodeID = todaysRouteNodeID;
    }

    public int getTodaysRouteNodeType() {
        return TodaysRouteNodeType;
    }

    public void setTodaysRouteNodeType(int todaysRouteNodeType) {
        TodaysRouteNodeType = todaysRouteNodeType;
    }

    public String getLastRouteVisitDate() {
        return LastRouteVisitDate;
    }

    public void setLastRouteVisitDate(String lastRouteVisitDate) {
        LastRouteVisitDate = lastRouteVisitDate;
    }

    public double getMonthTarget() {
        return MonthTarget;
    }

    public void setMonthTarget(double monthTarget) {
        MonthTarget = monthTarget;
    }

    public double getAchievement() {
        return Achievement;
    }

    public void setAchievement(double achievement) {
        Achievement = achievement;
    }

    public double getRRRequired() {
        return RRRequired;
    }

    public void setRRRequired(double RRRequired) {
        this.RRRequired = RRRequired;
    }

    public double getTodaysStore() {
        return TodaysStore;
    }

    public void setTodaysStore(double todaysStore) {
        TodaysStore = todaysStore;
    }

    public double getProdStoreLastCall() {
        return ProdStoreLastCall;
    }

    public void setProdStoreLastCall(double prodStoreLastCall) {
        ProdStoreLastCall = prodStoreLastCall;
    }

    private int Sstat;
    private int flgShowHeader;

    public int getFlgShowHeader() {
        return flgShowHeader;
    }

    public void setFlgShowHeader(int flgShowHeader) {
        this.flgShowHeader = flgShowHeader;
    }

    public String getCovAreaNodeID() {
        return CovAreaNodeID;
    }

    public void setCovAreaNodeID(String covAreaNodeID) {
        CovAreaNodeID = covAreaNodeID;
    }

    public String getCovAreaNodeType() {
        return CovAreaNodeType;
    }

    public void setCovAreaNodeType(String covAreaNodeType) {
        CovAreaNodeType = covAreaNodeType;
    }

    public String getCovArea() {
        return CovArea;
    }

    public void setCovArea(String covArea) {
        CovArea = covArea;
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

    public String getPersonname() {
        return Personname;
    }

    public void setPersonname(String personname) {
        Personname = personname;
    }

    public double getDstrbn_Tgt() {
        return Dstrbn_Tgt;
    }

    public void setDstrbn_Tgt(double dstrbn_Tgt) {
        Dstrbn_Tgt = dstrbn_Tgt;
    }

    public double getSales_Tgt() {
        return Sales_Tgt;
    }

    public void setSales_Tgt(double sales_Tgt) {
        Sales_Tgt = sales_Tgt;
    }

    public int getSstat() {
        return Sstat;
    }

    public void setSstat(int sstat) {
        Sstat = sstat;
    }
}
