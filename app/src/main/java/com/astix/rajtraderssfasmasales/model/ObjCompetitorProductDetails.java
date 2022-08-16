package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.SerializedName;

public class ObjCompetitorProductDetails {

    private int sStat;

    private int competitorBrandId;
    private String competitorBrandName;
    private int businessUnitId;
    private String businessUnitName;

    @SerializedName("StoreID")
    private int storeId;

    @SerializedName("CompProductID")
    private int competitorProductId;

    private String competitorProductName;
    //Stock qty bt DSM
    @SerializedName("StockQty")
    private int stockQty = -1;
    //Stock qty bt ASM

    @SerializedName("PStockQty")
    private int parentStockQty = -1;

    @SerializedName("PTR")
    private double ptr = -1;

    @SerializedName("PPTR")
    private double parentPtr = -1;

    @SerializedName("PTC")
    private double ptc = -1;

    @SerializedName("PPTC")
    private double parentPtc = -1;

    private String productImagePath;
    private double unitInGrams;
    private double minRate;
    private double maxRate;
    private String storeVisitCode;
    private String jointVisitID;
    private boolean isProductAvailable;

    public ObjCompetitorProductDetails() {
    }

    public ObjCompetitorProductDetails(int competitorBrandId, int businessUnitId, int storeId, int competitorProductId,
                                       int stockQty, int parentStockQty, double ptr, double parentPtr, double ptc,
                                       double parentPtc, String competitorProductName, String productImagePath) {
        this.competitorBrandId = competitorBrandId;
        this.businessUnitId = businessUnitId;
        this.storeId = storeId;
        this.competitorProductId = competitorProductId;
        this.stockQty = stockQty;
        this.parentStockQty = parentStockQty;
        this.ptr = ptr;
        this.parentPtr = parentPtr;
        this.ptc = ptc;
        this.parentPtc = parentPtc;
        this.competitorProductName = competitorProductName;
        this.productImagePath = productImagePath;
    }

    public ObjCompetitorProductDetails(int competitorBrandId, String competitorBrandName, int businessUnitId, String businessUnitName, int storeId, int competitorProductId, String competitorProductName, int stockQty, int parentStockQty, double ptr, double parentPtr, double ptc, double parentPtc, String productImagePath, double unitInGrams, double minRate, double maxRate) {
        this.competitorBrandId = competitorBrandId;
        this.competitorBrandName = competitorBrandName;
        this.businessUnitId = businessUnitId;
        this.businessUnitName = businessUnitName;
        this.storeId = storeId;
        this.competitorProductId = competitorProductId;
        this.competitorProductName = competitorProductName;
        this.stockQty = stockQty;
        this.parentStockQty = parentStockQty;
        this.ptr = ptr;
        this.parentPtr = parentPtr;
        this.ptc = ptc;
        this.parentPtc = parentPtc;
        this.productImagePath = productImagePath;
        this.unitInGrams = unitInGrams;
        this.minRate = minRate;
        this.maxRate = maxRate;
    }

    public ObjCompetitorProductDetails(int competitorBrandId, String competitorBrandName, int businessUnitId, int storeId, int competitorProductId,
                                       String competitorProductName, int parentStockQty, int parentPtr, int parentPtc, String productImagePath) {
        this.competitorBrandId = competitorBrandId;
        this.competitorBrandName = competitorBrandName;
        this.businessUnitId = businessUnitId;
        this.storeId = storeId;
        this.competitorProductId = competitorProductId;
        this.competitorProductName = competitorProductName;
        this.parentStockQty = parentStockQty;
        this.parentPtr = parentPtr;
        this.parentPtc = parentPtc;
        this.productImagePath = productImagePath;
    }

    public String getJointVisitID() {
        return jointVisitID;
    }

    public void setJointVisitID(String jointVisitID) {
        this.jointVisitID = jointVisitID;
    }

    public String getStoreVisitCode() {
        return storeVisitCode;
    }

    public void setStoreVisitCode(String storeVisitCode) {
        this.storeVisitCode = storeVisitCode;
    }

    public boolean isProductAvailable() {
        return isProductAvailable;
    }

    public void setProductAvailable(boolean productAvailable) {
        isProductAvailable = productAvailable;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public String getProductImagePath() {
        return productImagePath;
    }

    public int getsStat() {
        return sStat;
    }

    public void setsStat(int sStat) {
        this.sStat = sStat;
    }

    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }

    public String getCompetitorBrandName() {
        return competitorBrandName;
    }

    public void setCompetitorBrandName(String competitorBrandName) {
        this.competitorBrandName = competitorBrandName;
    }

    public int getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(int businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getCompetitorProductName() {
        return competitorProductName;
    }

    public void setCompetitorProductName(String competitorProductName) {
        this.competitorProductName = competitorProductName;
    }

    public int getCompetitorBrandId() {
        return competitorBrandId;
    }

    public void setCompetitorBrandId(int competitorBrandId) {
        this.competitorBrandId = competitorBrandId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getCompetitorProductId() {
        return competitorProductId;
    }

    public void setCompetitorProductId(int competitorProductId) {
        this.competitorProductId = competitorProductId;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public int getParentStockQty() {
        return parentStockQty;
    }

    public void setParentStockQty(int parentStockQty) {
        this.parentStockQty = parentStockQty;
    }

    public double getPtr() {
        return ptr;
    }

    public void setPtr(double ptr) {
        this.ptr = ptr;
    }

    public double getParentPtr() {
        return parentPtr;
    }

    public void setParentPtr(double parentPtr) {
        this.parentPtr = parentPtr;
    }

    public double getPtc() {
        return ptc;
    }

    public void setPtc(double ptc) {
        this.ptc = ptc;
    }

    public double getParentPtc() {
        return parentPtc;
    }

    public void setParentPtc(double parentPtc) {
        this.parentPtc = parentPtc;
    }

    public double getUnitInGrams() {
        return unitInGrams;
    }

    public void setUnitInGrams(double unitInGrams) {
        this.unitInGrams = unitInGrams;
    }

    public double getMinRate() {
        return minRate;
    }

    public void setMinRate(double minRate) {
        this.minRate = minRate;
    }

    public double getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(double maxRate) {
        this.maxRate = maxRate;
    }

    @Override
    public String toString() {
        return "ObjCompetitorProductDetails{" +
                "sStat=" + sStat +
                ", competitorBrandId=" + competitorBrandId +
                ", competitorBrandName='" + competitorBrandName + '\'' +
                ", businessUnitId=" + businessUnitId +
                ", businessUnitName='" + businessUnitName + '\'' +
                ", storeId=" + storeId +
                ", competitorProductId=" + competitorProductId +
                ", competitorProductName='" + competitorProductName + '\'' +
                ", stockQty=" + stockQty +
                ", parentStockQty=" + parentStockQty +
                ", ptr=" + ptr +
                ", parentPtr=" + parentPtr +
                ", ptc=" + ptc +
                ", parentPtc=" + parentPtc +
                ", productImagePath='" + productImagePath + '\'' +
                ", unitInGrams=" + unitInGrams +
                ", minRate=" + minRate +
                ", maxRate=" + maxRate +
                ", storeVisitCode='" + storeVisitCode + '\'' +
                ", isProductAvailable=" + isProductAvailable +
                '}';
    }
}
