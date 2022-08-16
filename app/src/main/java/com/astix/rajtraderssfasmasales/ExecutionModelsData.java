package com.astix.rajtraderssfasmasales;


import com.astix.rajtraderssfasmasales.model.TblInvoiceExecutionProductList;
import com.astix.rajtraderssfasmasales.model.TblPendingInvoices;
import com.astix.rajtraderssfasmasales.model.TblProductWiseInvoice;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExecutionModelsData {

    @SerializedName("tblPendingInvoices")
    @Expose
    private List<TblPendingInvoices> tblPendingInvoices = null;


    @SerializedName("tblInvoiceExecutionProductList")
    @Expose
    private List<TblInvoiceExecutionProductList> tblInvoiceExecutionProductList = null;


    @SerializedName("tblProductWiseInvoice")
    @Expose
    private List<TblProductWiseInvoice> tblProductWiseInvoice = null;

    public List<TblPendingInvoices> getTblPendingInvoices() {
        return tblPendingInvoices;
    }

    public void setTblPendingInvoices(List<TblPendingInvoices> tblPendingInvoices) {
        this.tblPendingInvoices = tblPendingInvoices;
    }

    public List<TblInvoiceExecutionProductList> getTblInvoiceExecutionProductList() {
        return tblInvoiceExecutionProductList;
    }

    public void setTblInvoiceExecutionProductList(List<TblInvoiceExecutionProductList> tblInvoiceExecutionProductList) {
        this.tblInvoiceExecutionProductList = tblInvoiceExecutionProductList;
    }

    public List<TblProductWiseInvoice> getTblProductWiseInvoice() {
        return tblProductWiseInvoice;
    }

    public void setTblProductWiseInvoice(List<TblProductWiseInvoice> tblProductWiseInvoice) {
        this.tblProductWiseInvoice = tblProductWiseInvoice;
    }
}
