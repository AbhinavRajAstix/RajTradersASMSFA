
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblDistributorDailyStockDetailsReport {

    @SerializedName("CustomerNodeID")
    @Expose
    private int CustomerNodeID;
    @SerializedName("CustomerNodeType")
    @Expose
    private int CustomerNodeType;
    @SerializedName("Customer")
    @Expose
    private String Customer;
    @SerializedName("Personname")
    @Expose
    private String PersonName;

    @SerializedName("LastEntryDate")
    @Expose
    private String LastEntryDate;


    @SerializedName("StockQty(Kg)")
    @Expose
    private double StockKg;

    @SerializedName("StockQty(Cases)")
    @Expose
    private double StockCases;

    @SerializedName("Stock(Value in Lacs)")
    @Expose
    private double StockValue;




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

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getLastEntryDate() {
        return LastEntryDate;
    }

    public void setLastEntryDate(String lastEntryDate) {
        LastEntryDate = lastEntryDate;
    }

    public double getStockKg() {
        return StockKg;
    }

    public void setStockKg(double stockKg) {
        StockKg = stockKg;
    }

    public double getStockCases() {
        return StockCases;
    }

    public void setStockCases(double stockCases) {
        StockCases = stockCases;
    }

    public double getStockValue() {
        return StockValue;
    }

    public void setStockValue(double stockValue) {
        StockValue = stockValue;
    }
}
