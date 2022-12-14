
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblDistributorStock {

    @SerializedName("Customer")
    @Expose
    private String customer;
    @SerializedName("ProductNodeID")
    @Expose
    private Integer productNodeID;
    @SerializedName("ProductNodeType")
    @Expose
    private Integer productNodeType;

    @SerializedName("SKUName")
    @Expose
    private String sKUName;

    @SerializedName("StockDate")
    @Expose
    private String stockDate;

    @SerializedName("StockQty")
    @Expose
    private String stockQty;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getProductNodeID() {
        return productNodeID;
    }

    public void setProductNodeID(Integer productNodeID) {
        this.productNodeID = productNodeID;
    }

    public Integer getProductNodeType() {
        return productNodeType;
    }

    public void setProductNodeType(Integer productNodeType) {
        this.productNodeType = productNodeType;
    }

    public String getsKUName() {
        return sKUName;
    }

    public void setsKUName(String sKUName) {
        this.sKUName = sKUName;
    }

    public String getStockDate() {
        return stockDate;
    }

    public void setStockDate(String stockDate) {
        this.stockDate = stockDate;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }
}
