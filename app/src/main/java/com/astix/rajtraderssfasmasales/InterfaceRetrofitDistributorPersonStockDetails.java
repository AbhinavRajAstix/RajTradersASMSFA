package com.astix.rajtraderssfasmasales;

public interface InterfaceRetrofitDistributorPersonStockDetails {
    public void successPersonStockDetails(String PersonName,String DistributorName);
    public void failurePersonStockDetails();
    public void fnFilterDetailsForDistributor(int DistNodeID,String PersonName,String LastEntryDate);
}
