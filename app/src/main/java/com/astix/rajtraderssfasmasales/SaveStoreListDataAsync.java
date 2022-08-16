package com.astix.rajtraderssfasmasales;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;


import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.model.ObjCompetitorProductDetails;
import com.astix.rajtraderssfasmasales.model.StoreListTableModel;
import com.astix.rajtraderssfasmasales.model.TblStoreInvoiceLastVisitDetails;
import com.astix.rajtraderssfasmasales.model.TblStoreListMaster;
import com.astix.rajtraderssfasmasales.model.TblStoreProductLvlOrderDetail;
import com.astix.rajtraderssfasmasales.utils.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SaveStoreListDataAsync extends AsyncTask {

    private AppDataSource mDataSource;
    private StoreListTableModel mStoreListTableModel;
    private ArrayList blankTablearrayList;
    private InterfaceRetrofit interfaceRetrofit;
    private ProgressDialog mProgressDialog;
    private Activity mContext;
    private PreferenceManager mPreferenceManager;
    private int CoverageNodeId;
    private int CoverageNodeType;

    public SaveStoreListDataAsync(Activity context, StoreListTableModel storeListTableModel, int CoverageNodeID, int CoverageNodeType, InterfaceRetrofit interfaceRetrofit, ProgressDialog progressDialog){
        mContext=context;
        mDataSource=AppDataSource.getInstance(context);
        mStoreListTableModel=storeListTableModel;
        blankTablearrayList=new ArrayList();
        this.interfaceRetrofit=interfaceRetrofit;
        mProgressDialog=progressDialog;
        this.CoverageNodeId=CoverageNodeID;
        this.CoverageNodeType=CoverageNodeType;
        mPreferenceManager= PreferenceManager.getInstance(context);

    }
    @Override
    protected Object doInBackground(Object[] objects) {
        HashMap<String, String> hmapStoreIdSstat=new HashMap<String, String>();
        hmapStoreIdSstat=mDataSource.checkForStoreIdSstat();
        HashMap<String, String> hmapflgOrderType=new HashMap<String, String>();
        hmapflgOrderType=mDataSource.checkForStoreflgOrderType();

        HashMap<String, String> hmapStoreIdLastVisitDate=new HashMap<String, String>();
        hmapStoreIdLastVisitDate=mDataSource.checkForStoreIdLastVisitdate();

        HashMap<String, String> hmapStoreIdFlgProductive=new HashMap<String, String>();
        hmapStoreIdFlgProductive=mDataSource.checkForStoreIdSstat();

        System.out.println("SaveStoreListDataAsync Step 1  Starts");
        mDataSource.Delete_tblStore_for_refreshDataButNotNewStore(CoverageNodeId,CoverageNodeType);
        mDataSource.fndeleteStoreAddressMapDetailsMstr();

//                    mDataSource.deleteStoreListMaster();
//                    mDataSource.deleteStoreList();
        List<TblStoreListMaster> tblStoreListMasters=  mStoreListTableModel.getTblStoreListMaster();
        if(tblStoreListMasters.size()>0){

         //   mDataSource.insertStoreListMasters(tblStoreListMasters,CoverageNodeId,CoverageNodeType,hmapStoreIdSstat,hmapStoreIdLastVisitDate,hmapStoreIdFlgProductive);
           // mDataSource.fnUpdateStoresIssueFlg();
        }
        else{
            blankTablearrayList.add("tblStoreListMaster");
        }
        System.out.println("SaveStoreListDataAsync Step 1  Ends");

        System.out.println("SaveStoreListDataAsync Step 2  Starts");
        List<TblStoreProductLvlOrderDetail> tblStoreProductLvlOrderDetailList=mStoreListTableModel.getTblStoreProductLvlOrderDetail();
        if(tblStoreProductLvlOrderDetailList.size()>0){
            mDataSource.insertStoreProductLvlOrderDetail(tblStoreProductLvlOrderDetailList);
        }else{
            blankTablearrayList.add("TblStoreProductLvlOrderDetail");
        }
        System.out.println("SaveStoreListDataAsync Step 2  Ends");

        System.out.println("SaveStoreListDataAsync Step 3  Starts");
        List<TblStoreInvoiceLastVisitDetails> tblStoreInvoiceLastVisitDetails=mStoreListTableModel.getTblStoreInvoiceLastVisitDetails();
        if(tblStoreInvoiceLastVisitDetails.size()>0){
            mDataSource.insertStoreInvoiceLastVisitDetails(tblStoreInvoiceLastVisitDetails);
        }else{
            blankTablearrayList.add("TblStoreInvoiceLastVisitDetails");
        }

        System.out.println("SaveStoreListDataAsync Step 3  Ends");

        System.out.println("SaveStoreListDataAsync Step 4  Starts");
        //mDataSource.deleteTblCurrentVisitCompetitionProductStock();
        List<ObjCompetitorProductDetails> tblCurrentVisitCompetitionProductStock = mStoreListTableModel.getTblCurrentVisitCompetitionProductStock();
        if (tblCurrentVisitCompetitionProductStock.size() > 0) {
           // mDataSource.insertIntoTblCurrentVisitCompetitionProductStock(tblCurrentVisitCompetitionProductStock);
        }else {
            blankTablearrayList.add("tblCurrentVisitCompetitionProductStock");
        }
        System.out.println("SaveStoreListDataAsync Step 4  Ends");



//                    List<TblContentMaster> tblContentMasters=storeListTableModel.getTblCont();
//                    if(tblStoreInvoiceLastVisitDetails.size()>0){
//                        mDataSource.insertStoreProductLvlOrderDetail(tblStoreProductLvlOrderDetailList);
//                    }else{
//                        blankTablearrayList.add("tblStoreListMaster");
//                    }



        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(!mContext.isFinishing() && !mContext.isDestroyed() &&  mProgressDialog!=null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        interfaceRetrofit.success();

    }
}
