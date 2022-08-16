
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.SerializedName;

public class TblStoreInvoiceLastVisitDetails {

    @SerializedName("InvCode")
    private String invCode;
    @SerializedName("InvDate")
    private String invDate;
    @SerializedName("OutStandingAmt")
    private int outStandingAmt;
    @SerializedName("Storeid")
    private int storeid;

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public int getOutStandingAmt() {
        return outStandingAmt;
    }

    public void setOutStandingAmt(int outStandingAmt) {
        this.outStandingAmt = outStandingAmt;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

}
