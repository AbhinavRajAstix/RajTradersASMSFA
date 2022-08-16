
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.SerializedName;

public class TblStoreProductLvlOrderDetail {

    @SerializedName("InvoiceQty")
    private int invoiceQty;
    @SerializedName("InvoiceValue")
    private double invoiceValue;
    @SerializedName("OrderQty")
    private int orderQty;
    @SerializedName("Ordervalue")
    private double ordervalue;
    @SerializedName("ProductID")
    private int productID;
    @SerializedName("StockQty")
    private int stockQty;
    @SerializedName("StockValue")
    private int stockValue;
    @SerializedName("StoreID")
    private int storeID;

    public int getInvoiceQty() {
        return invoiceQty;
    }

    public void setInvoiceQty(int invoiceQty) {
        this.invoiceQty = invoiceQty;
    }

    public double getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(double invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getOrdervalue() {
        return ordervalue;
    }

    public void setOrdervalue(double ordervalue) {
        this.ordervalue = ordervalue;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public int getStockValue() {
        return stockValue;
    }

    public void setStockValue(int stockValue) {
        this.stockValue = stockValue;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

}
