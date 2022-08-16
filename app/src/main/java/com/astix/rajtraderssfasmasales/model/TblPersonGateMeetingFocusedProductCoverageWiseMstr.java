
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblPersonGateMeetingFocusedProductCoverageWiseMstr {

    @SerializedName("CovAreaNodeID")
    @Expose
    private String CovAreaNodeID;
    @SerializedName("CovAreaNodeType")
    @Expose
    private String CovAreaNodeType;
    @SerializedName("CovArea")
    @Expose
    private String CovArea;

    @SerializedName("FocusProductNodeID")
    @Expose
    private int FocusProductNodeID;

    @SerializedName("FocusProductNodeType")
    @Expose
    private int FocusProductNodeType;

    @SerializedName("FocusProduct")
    @Expose
    private String FocusProduct ;


    @SerializedName("Dstrbn_Tgt_Focus ")
    @Expose
    private double Dstrbn_Tgt_Focus ;

    @SerializedName("Sales_Tgt_Focus")
    @Expose
    private double Sales_Tgt_Focus;

    private int Sstat;
    private int flgShowHeader;


    @SerializedName("PersonNodeID")
    @Expose
    private String PersonNodeID;

    @SerializedName("PersonNodeType")
    @Expose
    private String PersonNodeType;

    @SerializedName("PDACode")
    @Expose
    private String PDACode;

    @SerializedName("EntryDate")
    @Expose
    private String EntryDate;


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

    public String getPDACode() {
        return PDACode;
    }

    public void setPDACode(String PDACode) {
        this.PDACode = PDACode;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
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

    public int getFocusProductNodeID() {
        return FocusProductNodeID;
    }

    public void setFocusProductNodeID(int focusProductNodeID) {
        FocusProductNodeID = focusProductNodeID;
    }

    public int getFocusProductNodeType() {
        return FocusProductNodeType;
    }

    public void setFocusProductNodeType(int focusProductNodeType) {
        FocusProductNodeType = focusProductNodeType;
    }

    public String getFocusProduct() {
        return FocusProduct;
    }

    public void setFocusProduct(String focusProduct) {
        FocusProduct = focusProduct;
    }

    public double getDstrbn_Tgt_Focus() {
        return Dstrbn_Tgt_Focus;
    }

    public void setDstrbn_Tgt_Focus(double dstrbn_Tgt_Focus) {
        Dstrbn_Tgt_Focus = dstrbn_Tgt_Focus;
    }

    public double getSales_Tgt_Focus() {
        return Sales_Tgt_Focus;
    }

    public void setSales_Tgt_Focus(double sales_Tgt_Focus) {
        Sales_Tgt_Focus = sales_Tgt_Focus;
    }

    public int getSstat() {
        return Sstat;
    }

    public void setSstat(int sstat) {
        Sstat = sstat;
    }

    public int getFlgShowHeader() {
        return flgShowHeader;
    }

    public void setFlgShowHeader(int flgShowHeader) {
        this.flgShowHeader = flgShowHeader;
    }
}
