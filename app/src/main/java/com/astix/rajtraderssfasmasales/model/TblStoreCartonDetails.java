
package com.astix.rajtraderssfasmasales.model;



public class TblStoreCartonDetails {

    private String StoreID;
    private String OrderID;
    private String TmpInvoiceCodePDA;
    private String CartonID;
    private int PrdID;
    private int CatID;
    private String UOMType;
    private int NoOfCarton;
    private int PrdQty;
    private int TotalExpectedQty;
    private int TotalActualQty;
    private double CartonProductDiscount;
    private int Sstat;

    public double getCartonProductDiscount() {
        return CartonProductDiscount;
    }

    public void setCartonProductDiscount(double cartonProductDiscount) {
        CartonProductDiscount = cartonProductDiscount;
    }

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getTmpInvoiceCodePDA() {
        return TmpInvoiceCodePDA;
    }

    public void setTmpInvoiceCodePDA(String tmpInvoiceCodePDA) {
        TmpInvoiceCodePDA = tmpInvoiceCodePDA;
    }

    public String getCartonID() {
        return CartonID;
    }

    public void setCartonID(String cartonID) {
        CartonID = cartonID;
    }

    public int getPrdID() {
        return PrdID;
    }

    public void setPrdID(int prdID) {
        PrdID = prdID;
    }

    public int getCatID() {
        return CatID;
    }

    public void setCatID(int catID) {
        CatID = catID;
    }

    public String getUOMType() {
        return UOMType;
    }

    public void setUOMType(String UOMType) {
        this.UOMType = UOMType;
    }

    public int getNoOfCarton() {
        return NoOfCarton;
    }

    public void setNoOfCarton(int noOfCarton) {
        NoOfCarton = noOfCarton;
    }

    public int getPrdQty() {
        return PrdQty;
    }

    public void setPrdQty(int prdQty) {
        PrdQty = prdQty;
    }

    public int getTotalExpectedQty() {
        return TotalExpectedQty;
    }

    public void setTotalExpectedQty(int totalExpectedQty) {
        TotalExpectedQty = totalExpectedQty;
    }

    public int getTotalActualQty() {
        return TotalActualQty;
    }

    public void setTotalActualQty(int totalActualQty) {
        TotalActualQty = totalActualQty;
    }

    public int getSstat() {
        return Sstat;
    }

    public void setSstat(int sstat) {
        Sstat = sstat;
    }
}
