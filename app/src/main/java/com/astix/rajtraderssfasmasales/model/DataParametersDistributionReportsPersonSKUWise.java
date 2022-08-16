
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataParametersDistributionReportsPersonSKUWise {

    @SerializedName("PDACode")
    @Expose
    private String PDACode;
    @SerializedName("EntryDate")
    @Expose
    private String EntryDate;
    @SerializedName("flgReportLevel")
    @Expose
    private int flgReportLevel;


    @SerializedName("CustomerNodeId")
    @Expose
    private int CustomerNodeId;
    @SerializedName("CustomerNodeType")
    @Expose
    private int CustomerNodeType;

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

    public int getFlgReportLevel() {
        return flgReportLevel;
    }

    public void setFlgReportLevel(int flgReportLevel) {
        this.flgReportLevel = flgReportLevel;
    }

    public int getCustomerNodeId() {
        return CustomerNodeId;
    }

    public void setCustomerNodeId(int customerNodeId) {
        CustomerNodeId = customerNodeId;
    }

    public int getCustomerNodeType() {
        return CustomerNodeType;
    }

    public void setCustomerNodeType(int customerNodeType) {
        CustomerNodeType = customerNodeType;
    }

    @Override
    public String toString() {
        return "DataParametersDistributionReportsPersonSKUWise{" +
                "PDACode='" + PDACode + '\'' +
                ", EntryDate='" + EntryDate + '\'' +
                ", flgReportLevel=" + flgReportLevel +
                ", CustomerNodeId=" + CustomerNodeId +
                ", CustomerNodeType=" + CustomerNodeType +
                '}';
    }
}
