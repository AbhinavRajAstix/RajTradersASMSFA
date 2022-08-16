
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblDistributorDailyStockDetailsSKUReport {

    @SerializedName("CustomerNodeID")
    @Expose
    private int CustomerNodeID;
    @SerializedName("CustomerNodeType")
    @Expose
    private int CustomerNodeType;
    @SerializedName("CategoryNodeID")
    @Expose
    private int CategoryNodeID;
    @SerializedName("CategoryNodeType")
    @Expose
    private int CategoryNodeType;
    @SerializedName("Category")
    @Expose
    private String Category;
    @SerializedName("ProductNodeID")
    @Expose
    private int ProductNodeID;
    @SerializedName("ProductNodeType")
    @Expose
    private int ProductNodeType;
    @SerializedName("SKUName")
    @Expose
    private String SKUName;
    @SerializedName("StockQty(Kg)")
    @Expose
    private double StockKgQty;
    @SerializedName("StockQty(Cases)")
    @Expose
    private double StockCaseQty;
    @SerializedName("Stock(Value in Rs)")
    @Expose
    private double StockValue;


    @SerializedName("flgShowCategoryHeader")
    private int flgShowCategoryHeader;


    @SerializedName("flgMakeFrameVisible")
    private int flgMakeFrameVisible;

    @SerializedName("flgMakeRowVisible")
    private int flgMakeRowVisible;

    public int getFlgShowCategoryHeader() {
        return flgShowCategoryHeader;
    }

    public void setFlgShowCategoryHeader(int flgShowCategoryHeader) {
        this.flgShowCategoryHeader = flgShowCategoryHeader;
    }

    public int getFlgMakeFrameVisible() {
        return flgMakeFrameVisible;
    }

    public void setFlgMakeFrameVisible(int flgMakeFrameVisible) {
        this.flgMakeFrameVisible = flgMakeFrameVisible;
    }

    public int getFlgMakeRowVisible() {
        return flgMakeRowVisible;
    }

    public void setFlgMakeRowVisible(int flgMakeRowVisible) {
        this.flgMakeRowVisible = flgMakeRowVisible;
    }

    public int getCustomerNodeID() {
        return CustomerNodeID;
    }

    public void setCustomerNodeID(int customerNodeID) {
        CustomerNodeID = customerNodeID;
    }

    public int getCustomerNodeType() {
        return CustomerNodeType;
    }

    public void setCustomerNodeType(int customerNodeType) {
        CustomerNodeType = customerNodeType;
    }

    public int getCategoryNodeID() {
        return CategoryNodeID;
    }

    public void setCategoryNodeID(int categoryNodeID) {
        CategoryNodeID = categoryNodeID;
    }

    public int getCategoryNodeType() {
        return CategoryNodeType;
    }

    public void setCategoryNodeType(int categoryNodeType) {
        CategoryNodeType = categoryNodeType;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getProductNodeID() {
        return ProductNodeID;
    }

    public void setProductNodeID(int productNodeID) {
        ProductNodeID = productNodeID;
    }

    public int getProductNodeType() {
        return ProductNodeType;
    }

    public void setProductNodeType(int productNodeType) {
        ProductNodeType = productNodeType;
    }

    public String getSKUName() {
        return SKUName;
    }

    public void setSKUName(String SKUName) {
        this.SKUName = SKUName;
    }

    public double getStockKgQty() {
        return StockKgQty;
    }

    public void setStockKgQty(double stockKgQty) {
        StockKgQty = stockKgQty;
    }

    public double getStockCaseQty() {
        return StockCaseQty;
    }

    public void setStockCaseQty(double stockCaseQty) {
        StockCaseQty = stockCaseQty;
    }

    public double getStockValue() {
        return StockValue;
    }

    public void setStockValue(double stockValue) {
        StockValue = stockValue;
    }
}
