package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.SerializedName;

public class TblCurrentVisitProductStock {

    @SerializedName("StoreId")
    private int storeID;
    @SerializedName("ProductId")
    private int productID;
    @SerializedName("StockQty")
    private int stockQty = -1;
    @SerializedName("PStockQty")
    private int stockQtyByASM = -1;

    private String storeVisitCode;
    private String unit;
    private String productName;
    private int isDefaultProduct;
    private int sStat;

    public TblCurrentVisitProductStock() {
    }

    public TblCurrentVisitProductStock(int productID, String unit, String productName) {
        this.productID = productID;
        this.unit = unit;
        this.productName = productName;
    }

    public TblCurrentVisitProductStock(int storeID, int productID, int stockQty, int stockQtyByASM, String unit, String productName) {
        this.storeID = storeID;
        this.productID = productID;
        this.stockQty = stockQty;
        this.stockQtyByASM = stockQtyByASM;
        this.unit = unit;
        this.productName = productName;
    }

    public TblCurrentVisitProductStock(int storeID, int productID, int stockQty, int stockQtyByASM, String unit, int isDefaultProduct) {
        this.storeID = storeID;
        this.productID = productID;
        this.stockQty = stockQty;
        this.stockQtyByASM = stockQtyByASM;
        this.unit = unit;
        this.isDefaultProduct = isDefaultProduct;
    }

    public String getStoreVisitCode() {
        return storeVisitCode;
    }

    public void setStoreVisitCode(String storeVisitCode) {
        this.storeVisitCode = storeVisitCode;
    }

    public int getIsDefaultProduct() {
        return isDefaultProduct;
    }

    public void setIsDefaultProduct(int isDefaultProduct) {
        this.isDefaultProduct = isDefaultProduct;
    }

    public int getsStat() {
        return sStat;
    }

    public void setsStat(int sStat) {
        this.sStat = sStat;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public int getStockQtyByASM() {
        return stockQtyByASM;
    }

    public void setStockQtyByASM(int stockQtyByASM) {
        this.stockQtyByASM = stockQtyByASM;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "TblCurrentVisitProductStock{" +
                "storeID=" + storeID +
                ", productID=" + productID +
                ", stockQty=" + stockQty +
                ", stockQtyByASM=" + stockQtyByASM +
                ", unit='" + unit + '\'' +
                ", productName='" + productName + '\'' +
                ", isDefaultProduct=" + isDefaultProduct +
                ", sStat=" + sStat +
                '}';
    }
}
