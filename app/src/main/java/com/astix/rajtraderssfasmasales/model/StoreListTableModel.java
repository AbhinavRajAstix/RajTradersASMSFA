
package com.astix.rajtraderssfasmasales.model;

import java.util.List;

public class StoreListTableModel {

    private List<TblStoreProductLvlOrderDetail> tblStoreProductLvlOrderDetail;
    private List<TblStoreListMaster> tblStoreListMaster;
    private List<TblStoreInvoiceLastVisitDetails> tblStoreInvoiceLastVisitDetails;
    private List<TblCurrentVisitProductStock> tblCurrentVisitProductStock;
    private List<ObjCompetitorProductDetails> tblCurrentVisitCompetitionProductStock;

    public List<ObjCompetitorProductDetails> getTblCurrentVisitCompetitionProductStock() {
        return tblCurrentVisitCompetitionProductStock;
    }

    public void setTblCurrentVisitCompetitionProductStock(List<ObjCompetitorProductDetails> tblCurrentVisitCompetitionProductStock) {
        this.tblCurrentVisitCompetitionProductStock = tblCurrentVisitCompetitionProductStock;
    }

    public List<TblCurrentVisitProductStock> getTblCurrentVisitProductStock() {
        return tblCurrentVisitProductStock;
    }

    public void setTblCurrentVisitProductStock(List<TblCurrentVisitProductStock> tblCurrentVisitProductStock) {
        this.tblCurrentVisitProductStock = tblCurrentVisitProductStock;
    }

    public List<TblStoreProductLvlOrderDetail> getTblStoreProductLvlOrderDetail() {
        return tblStoreProductLvlOrderDetail;
    }

    public void setTblStoreProductLvlOrderDetail(List<TblStoreProductLvlOrderDetail> tblStoreProductLvlOrderDetail) {
        this.tblStoreProductLvlOrderDetail = tblStoreProductLvlOrderDetail;
    }

    public List<TblStoreListMaster> getTblStoreListMaster() {
        return tblStoreListMaster;
    }

    public void setTblStoreListMaster(List<TblStoreListMaster> tblStoreListMaster) {
        this.tblStoreListMaster = tblStoreListMaster;
    }

    public List<TblStoreInvoiceLastVisitDetails> getTblStoreInvoiceLastVisitDetails() {
        return tblStoreInvoiceLastVisitDetails;
    }

    public void setTblStoreInvoiceLastVisitDetails(List<TblStoreInvoiceLastVisitDetails> tblStoreInvoiceLastVisitDetails) {
        this.tblStoreInvoiceLastVisitDetails = tblStoreInvoiceLastVisitDetails;
    }


}
