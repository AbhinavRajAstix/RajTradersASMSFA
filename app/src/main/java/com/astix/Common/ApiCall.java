package com.astix.Common;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import androidx.appcompat.app.AlertDialog;

import com.allana.truetime.TimeUtils;
import com.astix.rajtraderssfasmasales.InterfaceRetrofit;
import com.astix.rajtraderssfasmasales.R;
import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.model.AllMasterTablesModel;
import com.astix.rajtraderssfasmasales.model.Data;
import com.astix.rajtraderssfasmasales.model.InvoiceList;
import com.astix.rajtraderssfasmasales.model.TblBankMaster;
import com.astix.rajtraderssfasmasales.model.TblCategoryMaster;
import com.astix.rajtraderssfasmasales.model.TblCoverageDsr;
import com.astix.rajtraderssfasmasales.model.TblCoverageMaster;
import com.astix.rajtraderssfasmasales.model.TblCycleID;
import com.astix.rajtraderssfasmasales.model.TblDistributorIDOrderIDLeft;
import com.astix.rajtraderssfasmasales.model.TblDistributorProductStock;
import com.astix.rajtraderssfasmasales.model.TblGetPDAQuestGrpMapping;
import com.astix.rajtraderssfasmasales.model.TblGetPDAQuestMstr;
import com.astix.rajtraderssfasmasales.model.TblGetPDAQuestOptionMstr;
import com.astix.rajtraderssfasmasales.model.TblGetPDAQuestionDependentMstr;
import com.astix.rajtraderssfasmasales.model.TblGetReturnsReasonForPDAMstr;
import com.astix.rajtraderssfasmasales.model.TblInstrumentMaster;
import com.astix.rajtraderssfasmasales.model.TblInvoiceCaption;
import com.astix.rajtraderssfasmasales.model.TblInvoiceExecutionProductList;
import com.astix.rajtraderssfasmasales.model.TblInvoiceLastVisitDetails;
import com.astix.rajtraderssfasmasales.model.TblIsDBRStockSubmitted;
import com.astix.rajtraderssfasmasales.model.TblIsSchemeApplicable;
import com.astix.rajtraderssfasmasales.model.TblLastOutstanding;
import com.astix.rajtraderssfasmasales.model.TblManagerMstr;
import com.astix.rajtraderssfasmasales.model.TblNoOrderReasonMaster;
import com.astix.rajtraderssfasmasales.model.TblOptionSurvey;
import com.astix.rajtraderssfasmasales.model.TblPDAGetExecutionSummary;
import com.astix.rajtraderssfasmasales.model.TblPDAGetLODQty;
import com.astix.rajtraderssfasmasales.model.TblPDAGetLastOrderDate;
import com.astix.rajtraderssfasmasales.model.TblPDAGetLastOrderDetails;
import com.astix.rajtraderssfasmasales.model.TblPDAGetLastOrderDetailsTotalValues;
import com.astix.rajtraderssfasmasales.model.TblPDAGetLastVisitDate;
import com.astix.rajtraderssfasmasales.model.TblPDAGetLastVisitDetails;
import com.astix.rajtraderssfasmasales.model.TblPDANotificationMaster;
import com.astix.rajtraderssfasmasales.model.TblPDAQuestOptionDependentMstr;
import com.astix.rajtraderssfasmasales.model.TblPDAQuestOptionValuesDependentMstr;
import com.astix.rajtraderssfasmasales.model.TblPendingInvoices;
import com.astix.rajtraderssfasmasales.model.TblPreAddedStores;
import com.astix.rajtraderssfasmasales.model.TblPreAddedStoresDataDetails;
import com.astix.rajtraderssfasmasales.model.TblPriceApplyType;
import com.astix.rajtraderssfasmasales.model.TblProductListLastVisitStockOrOrderMstr;
import com.astix.rajtraderssfasmasales.model.TblProductListMaster;
import com.astix.rajtraderssfasmasales.model.TblProductSegementMap;
import com.astix.rajtraderssfasmasales.model.TblProductWiseInvoice;
import com.astix.rajtraderssfasmasales.model.TblQuestIDForName;
import com.astix.rajtraderssfasmasales.model.TblQuestIDForOutChannel;
import com.astix.rajtraderssfasmasales.model.TblQuestionsSurvey;
import com.astix.rajtraderssfasmasales.model.TblReasonOrderCncl;
import com.astix.rajtraderssfasmasales.model.TblSalesPersonTodaysTarget;
import com.astix.rajtraderssfasmasales.model.TblStateCityMaster;
import com.astix.rajtraderssfasmasales.model.TblStockUploadedStatus;
import com.astix.rajtraderssfasmasales.model.TblStoreCloseReasonMaster;
import com.astix.rajtraderssfasmasales.model.TblStoreCountDetails;
import com.astix.rajtraderssfasmasales.model.TblStoreImageList;
import com.astix.rajtraderssfasmasales.model.TblStoreLastDeliveryNoteNumber;
import com.astix.rajtraderssfasmasales.model.TblStoreListMaster;
import com.astix.rajtraderssfasmasales.model.TblStoreListWithPaymentAddress;
import com.astix.rajtraderssfasmasales.model.TblStoreSomeProdQuotePriceMstr;
import com.astix.rajtraderssfasmasales.model.TblUOMMapping;
import com.astix.rajtraderssfasmasales.model.TblUOMMaster;
import com.astix.rajtraderssfasmasales.model.TblUserName;
import com.astix.rajtraderssfasmasales.model.TblVanIDOrderIDLeft;
import com.astix.rajtraderssfasmasales.model.TblVanProductStock;
import com.astix.rajtraderssfasmasales.model.TblVanStockOutFlg;
import com.astix.rajtraderssfasmasales.rest.ApiClient;
import com.astix.rajtraderssfasmasales.rest.ApiInterface;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCall {



   /* public static void callAllDataAsm(Context context, String imei, String RegistrationID, int CoverageNodeID, int CoverageNodeType, final int flgCldFrm)
    {
        final AppDataSource dbengine = AppDataSource.getInstance(context);
        final KProgressHUD progressHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(context.getResources().getString(R.string.RetrivingDataMsg))
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        final InterfaceRetrofit interfaceRetrofit = (InterfaceRetrofit) context;
        final ArrayList blankTablearrayList = new ArrayList();
        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        final String fDate = TimeUtils.getNetworkDateTime(context, TimeUtils.DATE_FORMAT);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        CommonInfo.flgDrctslsIndrctSls = dbengine.fnGetflgDrctslsIndrctSlsForDSR(CoverageNodeID,CoverageNodeType);
        int FlgAllRoutesData = 1;
        String serverDateForSPref = dbengine.fnGetServerDate();
        ArrayList<InvoiceList> arrDistinctInvoiceNumbersNew = dbengine.getDistinctInvoiceNumbersNew();
        Data data = new Data();
        data.setApplicationTypeId(CommonInfo.Application_TypeID);
        data.setIMEINo(imei);
       // data.setiMEINo2(imei);
        data.setVersionId(CommonInfo.DATABASE_VERSIONID);
        data.setRegistrationId(RegistrationID);
        data.setForDate(fDate);
        data.setAppVersionNo(CommonInfo.AppVersionID);
        data.setFlgAllRouteData(1);

        data.setInvoiceList(arrDistinctInvoiceNumbersNew);
        // data.setInvoiceList(null);
        data.setRouteNodeId(0);
        data.setRouteNodeType(0);
        data.setCoverageAreaNodeId(CoverageNodeID);
        data.setCoverageAreaNodeType(CoverageNodeType);
       *//* data.setCoverageAreaNodeId(0);
        data.setCoverageAreaNodeType(0);*//*

        Call<AllMasterTablesModel> call = apiService.Call_AllMasterData(data);
        call.enqueue(new Callback<AllMasterTablesModel>() {
            @Override
            public void onResponse(Call<AllMasterTablesModel> call, Response<AllMasterTablesModel> response) {
                if (response.code() == 200) {
                    AllMasterTablesModel allMasterTablesModel = response.body();
                    System.out.println("DATAENSERTEDSP");

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {

                            //table 2
                           *//* if (allMasterTablesModel.getTblRouteListMaster() != null) {
                                dbengine.Delete_tblRouteMasterMstr(CoverageNodeID,CoverageNodeType);
                                List<TblRouteListMaster> tblRouteListMaster = allMasterTablesModel.getTblRouteListMaster();
                                if (tblRouteListMaster.size() > 0) {

                                    int AutoIdStore = 0;
                                    for (TblRouteListMaster RouteListMaster : tblRouteListMaster) {

                                        dbengine.saveRoutesInfo("" + RouteListMaster.getRouteNodeId(), "" + RouteListMaster.getRoutenodetype(), RouteListMaster.getRoute(), RouteListMaster.getActive(), RouteListMaster.getActive(), fDate);
                                    }
                                } else {
                                    blankTablearrayList.add("tblRouteListMaster");
                                }
                            }*//*


                            if (allMasterTablesModel.getTblSalesPersonTodaysTarget() != null) {
                                CommonInfo.SalesPersonTodaysTargetMsg = "";
                                List<TblSalesPersonTodaysTarget> tblSalesPersonTodaysTarget = allMasterTablesModel.getTblSalesPersonTodaysTarget();
                                if (tblSalesPersonTodaysTarget.size() > 0) {
                                    for (TblSalesPersonTodaysTarget SalesPersonTodaysTarget : tblSalesPersonTodaysTarget) {
                                        CommonInfo.SalesPersonTodaysTargetMsg = SalesPersonTodaysTarget.getValueTarget();
                                    }
                                }
                            }


                            if (allMasterTablesModel.getTblIsDBRStockSubmitted() != null) {
                                dbengine.deletetblIsDBRStockSubmitted();
                                List<TblIsDBRStockSubmitted> tblIsDBRStockSubmitted = allMasterTablesModel.getTblIsDBRStockSubmitted();
                                if (tblIsDBRStockSubmitted.size() > 0) {
                                    for (TblIsDBRStockSubmitted IsDBRStockSubmitted : tblIsDBRStockSubmitted) {

                                        dbengine.savetblIsDBRStockSubmitted(IsDBRStockSubmitted.getIsDBRStockSubmitted());
                                    }
                                }
                            }

                            //table 5
                            if (allMasterTablesModel.getTblQuestIDForOutChannel() != null) {
                                dbengine.Delete_tblQuestIDForOutChannel();
                                List<TblQuestIDForOutChannel> tblQuestIDForOutChannel = allMasterTablesModel.getTblQuestIDForOutChannel();

                              *//*  if (tblQuestIDForOutChannel.size() > 0) {
                                    for (TblQuestIDForOutChannel QuestIDForOutChannel : tblQuestIDForOutChannel) {*//*
                                        dbengine.saveOutletChammetQstnIdGrpId(tblQuestIDForOutChannel);

                                 *//*   }

                                } else {
                                    blankTablearrayList.add("tblQuestIDForOutChannel");
                                }*//*
                            }
                            //table 6
                            if (allMasterTablesModel.getTblGetPDAQuestMstr() != null) {
                                dbengine.Delete_tblGetPDAQuestMstr();
                                List<TblGetPDAQuestMstr> tblGetPDAQuestMstr = allMasterTablesModel.getTblGetPDAQuestMstr();

                                if (tblGetPDAQuestMstr.size() > 0) {
                                    int AutoIdStore = 0;

                                    dbengine.savetblQuestionMstrRetroFit(tblGetPDAQuestMstr);


                                } else {
                                    blankTablearrayList.add("tblGetPDAQuestMstr");
                                }
                            }

                            if (allMasterTablesModel.getTblStoreCloseReasonMaster() != null) {
                                dbengine.deletetblStoreCloseReasonMaster();
                                List<TblStoreCloseReasonMaster> tblStoreCloseReasonMaster = allMasterTablesModel.getTblStoreCloseReasonMaster();


                                if (tblStoreCloseReasonMaster.size() > 0) {

                                   // for (TblStoreCloseReasonMaster StoreCloseReasonMaster : tblStoreCloseReasonMaster) {
                                        dbengine.savetblStoreCloseReasonMaster(tblStoreCloseReasonMaster);
                                   // }

                                } else {
                                    blankTablearrayList.add("tblStoreCloseReasonMaster");
                                }
                            }


                            //table 7
                            if (allMasterTablesModel.getTblQuestIDForName() != null) {
                                dbengine.Delete_tblQuestIDForName();
                                List<TblQuestIDForName> tblQuestIDForName = allMasterTablesModel.getTblQuestIDForName();

                                if (tblQuestIDForName.size() > 0) {

                                  //  for (TblQuestIDForName QuestIDForName : tblQuestIDForName) {
                                        dbengine.savetblQuestIDForName(tblQuestIDForName);
                                   // }

                                } else {
                                    blankTablearrayList.add("tblQuestIDForName");
                                }
                            }

                            //table 8----------------
                            if (allMasterTablesModel.getTblGetPDAQuestGrpMapping() != null) {
                                dbengine.Delete_tblPDAQuestGrpMappingMstr();
                                List<TblGetPDAQuestGrpMapping> tblGetPDAQuestGrpMapping = allMasterTablesModel.getTblGetPDAQuestGrpMapping();

                                if (tblGetPDAQuestGrpMapping.size() > 0) {


                                    dbengine.savetblPDAQuestGrpMappingMstr(tblGetPDAQuestGrpMapping);
                                } else {
                                    blankTablearrayList.add("tblGetPDAQuestGrpMapping");
                                }
                            }

                            //table 9-------------------------------
                            if (allMasterTablesModel.getTblGetPDAQuestOptionMstr() != null) {
                                dbengine.Delete_tblOptionMstr();
                                List<TblGetPDAQuestOptionMstr> tblGetPDAQuestOptionMstr = allMasterTablesModel.getTblGetPDAQuestOptionMstr();

                                if (tblGetPDAQuestOptionMstr.size() > 0) {
                                    dbengine.savetblOptionMstrRetrofit(tblGetPDAQuestOptionMstr);


                                } else {
                                    blankTablearrayList.add("tblGetPDAQuestOptionMstr");
                                }
                            }
                            //table 10-------------------------------
                            if (allMasterTablesModel.getTblGetPDAQuestionDependentMstr() != null) {
                                dbengine.Delete_tblQuestionDependentMstr();
                                List<TblGetPDAQuestionDependentMstr> tblGetPDAQuestionDependentMstr = allMasterTablesModel.getTblGetPDAQuestionDependentMstr();

                                if (tblGetPDAQuestionDependentMstr.size() > 0) {
                                  //  for (TblGetPDAQuestionDependentMstr GetPDAQuestionDependentMstr : tblGetPDAQuestionDependentMstr) {
                                        dbengine.savetblQuestionDependentMstr(tblGetPDAQuestionDependentMstr);
                                   // }

                                } else {
                                    blankTablearrayList.add("tblGetPDAQuestionDependentMstr");
                                }
                            }

                            //table 11-------------------------------
                            if (allMasterTablesModel.getTblPDAQuestOptionDependentMstr() != null) {
                                dbengine.Delete_tblPDAQuestOptionDependentMstr();
                                List<TblPDAQuestOptionDependentMstr> tblPDAQuestOptionDependentMstr = allMasterTablesModel.getTblPDAQuestOptionDependentMstr();

                                if (tblPDAQuestOptionDependentMstr.size() > 0) {
                                   // for (TblPDAQuestOptionDependentMstr PDAQuestOptionDependentMstr : tblPDAQuestOptionDependentMstr) {
                                        dbengine.savetblPDAQuestOptionDependentMstr(tblPDAQuestOptionDependentMstr);
                                    //}

                                } else {
                                    blankTablearrayList.add("tblPDAQuestOptionDependentMstr");
                                }
                            }
                            //table 12-------------------------------
                            if (allMasterTablesModel.getTblPDAQuestOptionValuesDependentMstr() != null) {
                                dbengine.Delete_tblPDAQuestOptionValuesDependentMstr();
                                List<TblPDAQuestOptionValuesDependentMstr> tblPDAQuestOptionValuesDependentMstr = allMasterTablesModel.getTblPDAQuestOptionValuesDependentMstr();

                                if (tblPDAQuestOptionValuesDependentMstr.size() > 0) {
                                    //for (TblPDAQuestOptionValuesDependentMstr PDAQuestOptionValuesDependentMstr : tblPDAQuestOptionValuesDependentMstr) {
                                        dbengine.savetblPDAQuestOptionValuesDependentMstr(tblPDAQuestOptionValuesDependentMstr);
                                   // }

                                } else {
                                    blankTablearrayList.add("tblPDAQuestOptionValuesDependentMstr");
                                }
                            }
                            //table 13-------------------------------
                            if (allMasterTablesModel.getTblPDANotificationMaster() != null) {
                                dbengine.Delete_tblNotificationMstr();
                                List<TblPDANotificationMaster> tblPDANotificationMaster = allMasterTablesModel.getTblPDANotificationMaster();

                                if (tblPDANotificationMaster.size() > 0) {
                                    int SerialNo = 0;
                                    for (TblPDANotificationMaster PDANotificationMaster : tblPDANotificationMaster) {
                                        SerialNo = SerialNo++;
                                        dbengine.inserttblNotificationMstr(SerialNo, imei, PDANotificationMaster.getNotificationMessage(), PDANotificationMaster.getMsgSendingTime(), 0, 0, "0", 0, PDANotificationMaster.getMsgServerID());
                                    }

                                } else {
                                    blankTablearrayList.add("tblPDANotificationMaster");
                                }
                            }
                            //table 14-------------------------------
                            if (allMasterTablesModel.getTblUserName() != null) {
                                dbengine.delete_all_storeDetailTables();//deleting all tables related to
                                List<TblUserName> tblUserName = allMasterTablesModel.getTblUserName();

                                if (tblUserName.size() > 0) {

                                    for (TblUserName UserName : tblUserName) {
                                        dbengine.saveTblUserName(UserName.getUserName());
                                    }

                                } else {
                                    blankTablearrayList.add("tblUserName");
                                }
                            }


                            if (allMasterTablesModel.getTblStoreCountDetails() != null) {
                                List<TblStoreCountDetails> tblStoreCountDetails = allMasterTablesModel.getTblStoreCountDetails();

                                if (tblStoreCountDetails.size() > 0) {

                                    for (TblStoreCountDetails StoreCountDetails : tblStoreCountDetails) {

                                        dbengine.saveTblStoreCountDetails("" + StoreCountDetails.getTotStoreAdded(), "" + StoreCountDetails.getTodayStoreAdded());
                                    }

                                } else {
                                    blankTablearrayList.add("tblStoreCountDetails");
                                }
                            }
                            //table 16-------------------------------
                            //already deleted above

                            if (allMasterTablesModel.getTblPreAddedStores() != null) {
                                List<TblPreAddedStores> tblPreAddedStores = allMasterTablesModel.getTblPreAddedStores();

                                HashMap<String, String> hmapPreAddedStoreIdSstat = new HashMap<String, String>();
                                hmapPreAddedStoreIdSstat = dbengine.checkForPreAddedStoreIdSstat();
                                if (tblPreAddedStores.size() > 0) {

                                    dbengine.saveTblPreAddedStoresRetrofit(tblPreAddedStores, hmapPreAddedStoreIdSstat);
                                } else {
                                    blankTablearrayList.add("tblPreAddedStores");
                                }


                                if (hmapPreAddedStoreIdSstat != null && hmapPreAddedStoreIdSstat.size() > 0) {
                                    hmapPreAddedStoreIdSstat.clear();
                                    hmapPreAddedStoreIdSstat = null;
                                }
                                //table 17-------------------------------
                                //already deleted above
                                List<TblPreAddedStoresDataDetails> tblPreAddedStoresDataDetails = allMasterTablesModel.getTblPreAddedStoresDataDetails();

                                if (tblPreAddedStoresDataDetails.size() > 0) {

                                    dbengine.saveTblPreAddedStoresDataDetailsRetrofit(tblPreAddedStoresDataDetails);
                                } else {
                                    blankTablearrayList.add("tblPreAddedStoresDataDetails");
                                }

                                List<TblStoreImageList> tblStoreImageListDetails = allMasterTablesModel.getTblStoreImageList();

                                if (tblStoreImageListDetails!=null && tblStoreImageListDetails.size() > 0) {

                                    dbengine.savetblStoreImageListDetails(tblStoreImageListDetails);
                                } else {
                                    blankTablearrayList.add("tblStoreImageListDetails");
                                }
                            }

                            //table 18-------------------------------
                            if (allMasterTablesModel.getTblStateCityMaster() != null) {
                                dbengine.deletetblStateCityMaster();
                                List<TblStateCityMaster> tblStateCityMaster = allMasterTablesModel.getTblStateCityMaster();

                                if (tblStateCityMaster.size() > 0) {
                                    //for (TblStateCityMaster StateCityMaster : tblStateCityMaster) {
                                        dbengine.fnsavetblStateCityMaster(tblStateCityMaster);
                                    //}
                                } else {
                                    blankTablearrayList.add("tblStateCityMaster");
                                }
                            }

                            //table 19-------------------------------
                            if (allMasterTablesModel.getTblProductListMaster() != null) {
                                dbengine.Delete_tblProductList_for_refreshData();
                                List<TblProductListMaster> tblProductListMaster = allMasterTablesModel.getTblProductListMaster();

                                if (tblProductListMaster.size() > 0) {

                                    dbengine.saveSOAPdataProductListRetrofit(tblProductListMaster);

                                } else {
                                    blankTablearrayList.add("tblProductListMaster");
                                }
                            }

                            //table 20-------------------------------
                            //deleted above
                            if (allMasterTablesModel.getTblProductSegementMap() != null) {
                                List<TblProductSegementMap> tblProductSegementMap = allMasterTablesModel.getTblProductSegementMap();

                                if (tblProductSegementMap.size() > 0) {

                                    dbengine.saveProductSegementMapRetrofit(tblProductSegementMap);

                                } else {
                                    blankTablearrayList.add("tblProductSegementMap");
                                }
                            }

                            //table 21-------------------------------
                            //deleted above
                            if (allMasterTablesModel.getTblPriceApplyType() != null) {
                                List<TblPriceApplyType> tblPriceApplyType = allMasterTablesModel.getTblPriceApplyType();

                                if (tblPriceApplyType.size() > 0) {
                                    for (TblPriceApplyType priceApplyType : tblPriceApplyType) {
                                        dbengine.savetblPriceApplyType(priceApplyType.getDiscountLevel(), priceApplyType.getCutoffvalue());
                                    }

                                } else {
                                    blankTablearrayList.add("tblPriceApplyType");
                                }
                            }

                            //table 22-------------------------------
                            //deleted above
                            if (allMasterTablesModel.getTblUOMMaster() != null) {
                                List<TblUOMMaster> tblUOMMaster = allMasterTablesModel.getTblUOMMaster();

                                if (tblUOMMaster.size() > 0) {
                                  //  for (TblUOMMaster priceApplyType : tblUOMMaster) {
                                        dbengine.insertUOMMstr(tblUOMMaster);
                                   // }

                                } else {
                                    blankTablearrayList.add("tblUOMMaster");
                                }
                            }
                            //table 23-------------------------------
                            //deleted above
                            if (allMasterTablesModel.getTblUOMMapping() != null) {
                                List<TblUOMMapping> tblUOMMapping = allMasterTablesModel.getTblUOMMapping();

                                if (tblUOMMapping.size() > 0) {
                                    for (TblUOMMapping UOMMapping : tblUOMMapping) {
                                        dbengine.insertUOMMapping(UOMMapping.getNodeID(), UOMMapping.getNodeType(), UOMMapping.getBaseUOMID(), UOMMapping.getPackUOMID(), UOMMapping.getRelConversionUnits(), UOMMapping.getFlgVanLoading());
                                    }

                                } else {
                                    blankTablearrayList.add("tblUOMMapping");
                                }
                            }

                            //table 24-------------------------------
                            //deleted above
                            if (allMasterTablesModel.getTblManagerMstr() != null) {
                                dbengine.delete_tblManagerMstr();
                                List<TblManagerMstr> tblManagerMstr = allMasterTablesModel.getTblManagerMstr();

                                if (tblManagerMstr.size() > 0) {
                                    for (TblManagerMstr ManagerMstr : tblManagerMstr) {
                                        dbengine.savetblManagerMstr("" + ManagerMstr.getPersonID(), "" + ManagerMstr.getPersonType(), ManagerMstr.getPersonName(), "" + ManagerMstr.getManagerID(), "" + ManagerMstr.getManagerType(), ManagerMstr.getManagerName());
                                    }

                                } else {
                                    blankTablearrayList.add("tblManagerMstr");
                                }
                            }
                            //table 25-------------------------------
                            //deleted above
                            if (allMasterTablesModel.getTblCategoryMaster() != null) {
                                dbengine.Delete_tblCategory_for_refreshData();
                                List<TblCategoryMaster> tblCategoryMaster = allMasterTablesModel.getTblCategoryMaster();

                                if (tblCategoryMaster.size() > 0) {
                                    for (TblCategoryMaster CategoryMaster : tblCategoryMaster) {
                                        dbengine.saveCategory(CategoryMaster.getNODEID(), CategoryMaster.getCATEGORY(), 0,CategoryMaster.getProductTypeNodeID(),CategoryMaster.getProductType(),CategoryMaster.getPrdClassOrdr(),CategoryMaster.getPrdTypeOrdr());
                                    }

                                } else {
                                    blankTablearrayList.add("tblCategoryMaster");
                                }
                            }

                            //table 26-------------------------------
                            if (allMasterTablesModel.getTblBankMaster() != null) {
                                dbengine.deletetblBankMaster();
                                List<TblBankMaster> tblBankMaster = allMasterTablesModel.getTblBankMaster();

                                if (tblBankMaster.size() > 0) {

                                    dbengine.savetblBankMaster(tblBankMaster);

                                } else {
                                    blankTablearrayList.add("tblBankMaster");
                                }
                            }

                            if(allMasterTablesModel.getTblNoOrderReasonMaster()!=null) {
                                dbengine.deletetblNoOrderReasonMaster();
                                List<TblNoOrderReasonMaster> tblNoOrderReasonMasterList = allMasterTablesModel.getTblNoOrderReasonMaster();

                                if (tblNoOrderReasonMasterList.size() > 0) {

                                    dbengine.savetblNoOrderReasonMaser(tblNoOrderReasonMasterList);

                                } else {
                                    blankTablearrayList.add("tblNoOrderReasonMaster");
                                }
                            }
                            //table 27-------------------------------
                            //deleted above

                            if (allMasterTablesModel.getTblInstrumentMaster() != null) {
                                List<TblInstrumentMaster> tblInstrumentMaster = allMasterTablesModel.getTblInstrumentMaster();

                                if (tblInstrumentMaster.size() > 0) {
                                   // for (TblInstrumentMaster instrumentMaster : tblInstrumentMaster) {
                                        dbengine.savetblInstrumentMaster(tblInstrumentMaster);
                                   // }

                                } else {
                                    blankTablearrayList.add("tblInstrumentMaster");
                                }
                            }


                           *//* dbengine.delete_tblRptDistribution();
                            List<TblRptDistribution> tblRptDistributionList = allMasterTablesModel.getTblRptDistribution();
                            if (tblRptDistributionList!=null && tblRptDistributionList.size() > 0) {

                                dbengine.insert_tblRptDistribution(tblRptDistributionList);
                            } else {
                                blankTablearrayList.add("TblRptDistribution");
                            }*//*


                           *//* dbengine.delete_tblRptSecVol();
                            List<TblRptSecVol> tblRptSecVolList = allMasterTablesModel.getTblRptSecVol();
                            if (tblRptSecVolList!=null && tblRptSecVolList.size() > 0) {

                                dbengine.insert_tblRptSecVol(tblRptSecVolList);
                            } else {
                                blankTablearrayList.add("tblRptSecVol");
                            }


                            dbengine.delete_tblRptManDays();
                            List<TblRptManDays> tblRptManDaysList = allMasterTablesModel.getTblRptManDays();
                            if (tblRptManDaysList!=null && tblRptManDaysList.size() > 0) {

                                dbengine.insert_tblRptManDays(tblRptManDaysList);
                            } else {
                                blankTablearrayList.add("tblRptManDays");
                            }*//*
                            //table 28-------------------------------

                           *//* List<TblRptDistributionDate> tblRptDistributionDates = allMasterTablesModel.getTblRptDistributionDate();


                            //table 29-------------------------------
                            if (allMasterTablesModel.getTblCycleID() != null) {
                                dbengine.deleteCompleteDataDistStock();

                                List<TblCycleID> tblCycleID = allMasterTablesModel.getTblCycleID();
                                if (CommonInfo.flgDrctslsIndrctSls == 1) {
                                    if (tblCycleID.size() > 0) {
                                        for (TblCycleID CycleID : tblCycleID) {
                                            dbengine.insertCycleId(CycleID.getCycleID(), CycleID.getCycStartTime(), CycleID.getCycleTime());
                                        }
                                    } else {
                                        blankTablearrayList.add("tblCycleID");
                                    }
                                }
                            }
*//*
                          //  dbengine.delete_tblCoverageDsr();
                            List<TblCoverageDsr> tblCoverageDsr = allMasterTablesModel.getTblCoverageDsr();
                            if (tblCoverageDsr!=null && tblCoverageDsr.size() > 0) {

                                dbengine.insertintotblCoveragedsr(tblCoverageDsr,CoverageNodeID,CoverageNodeType);
                            } else {
                                blankTablearrayList.add("tblCoverage");
                            }
                            List<TblCoverageMaster> tblCoverageMaster = allMasterTablesModel.getTblCoverage();
                            if (tblCoverageMaster!=null && tblCoverageMaster.size() > 0) {

                                dbengine.insertintotblCoverageMstr(tblCoverageMaster,CoverageNodeID,CoverageNodeType);
                            } else {
                                blankTablearrayList.add("tblCoverageMaster");
                            }

                            //table 30-------------------------------
                            //deleted above

                            if(CommonInfo.hmapAppMasterFlags.get("flgVanStockInApp")!=null && CommonInfo.hmapAppMasterFlags.get("flgVanStockInApp")!=0)
                            {

                                dbengine.Delete_tblStockUploadedStatus();

                                List<TblStockUploadedStatus> tblStockUploadedStatus=  allMasterTablesModel.getTblStockUploadedStatus();

                                if(tblStockUploadedStatus.size()>0){
                                    for(TblStockUploadedStatus StockUploadedStatus:tblStockUploadedStatus){
                                        dbengine.inserttblStockUploadedStatus(StockUploadedStatus.getFlgStockTrans(),StockUploadedStatus.getVanLoadUnLoadCycID(),StockUploadedStatus.getCycleTime(),StockUploadedStatus.getStatusID(),StockUploadedStatus.getFlgDayEnd());
                                    }

                                }
                                else{
                                    blankTablearrayList.add("tblStockUploadedStatus");
                                }

                                List<TblVanStockOutFlg> tblVanStockOutFlg=  allMasterTablesModel.getTblVanStockOutFlg();

                                if(tblVanStockOutFlg.size()>0){
                                    for(TblVanStockOutFlg VanStockOutFlg:tblVanStockOutFlg){
                                        dbengine.insertStockOut(VanStockOutFlg.getFlgStockOutEntryDone());
                                    }
                                }
                                else{
                                    blankTablearrayList.add("tblDistributorStockOutFlg");
                                }

//deleted above
                                List<TblVanIDOrderIDLeft> tblVanIDOrderIDLeft=  allMasterTablesModel.getTblVanIDOrderIDLeft();

                                if(tblVanIDOrderIDLeft.size()>0){
                                    for(TblVanIDOrderIDLeft DistributorIDOrderIDLeft:tblVanIDOrderIDLeft){
                                        dbengine.insertDistributorLeftOrderId(DistributorIDOrderIDLeft.getCustomer(),DistributorIDOrderIDLeft.getPDAOrderId(),DistributorIDOrderIDLeft.getFlgInvExists());
                                    }
                                }
                                else{
                                    blankTablearrayList.add("tblVanIDOrderIDLeft");
                                }

                                List<TblVanProductStock> tblVanProductStock=  allMasterTablesModel.getTblVanProductStock();

                                if(tblVanProductStock.size()>0){
                                    // if(CommonInfo.flgDrctslsIndrctSls==1) {
                                    dbengine.insertDistributorStock(tblVanProductStock);
                                    //  if(CommonInfo.hmapAppMasterFlags.get("flgDBRStockInApp")==1 && CommonInfo.hmapAppMasterFlags.get("flgDBRStockCalculate")==1 ) {
                                    int statusId = dbengine.confirmedStock();
                                    if (statusId == 2) {
                                        dbengine.insertConfirmWArehouse(tblVanProductStock.get(0).getCustomer(), "1");
                                        dbengine.inserttblDayCheckIn(1);
                                    }
                                    // }
                                    //  }

                                }
                                else{
                                    blankTablearrayList.add("tblVanProductStock");
                                }
                            }
                            //deleted above
                            if (allMasterTablesModel.getTblVanIDOrderIDLeft() != null) {
                                List<TblVanIDOrderIDLeft> tblVanIDOrderIDLeft = allMasterTablesModel.getTblVanIDOrderIDLeft();

                                if (tblVanIDOrderIDLeft.size() > 0) {
                                    for (TblVanIDOrderIDLeft DistributorIDOrderIDLeft : tblVanIDOrderIDLeft) {
                                        dbengine.insertDistributorLeftOrderId(DistributorIDOrderIDLeft.getCustomer(), DistributorIDOrderIDLeft.getPDAOrderId(), DistributorIDOrderIDLeft.getFlgInvExists());
                                    }
                                } else {
                                    blankTablearrayList.add("tblVanIDOrderIDLeft");
                                }
                            }
                            //table 32-------------------------------
                            if (allMasterTablesModel.getTblInvoiceCaption() != null) {
                                dbengine.Delete_tblInvoiceCaption();
                                List<TblInvoiceCaption> tblInvoiceCaption = allMasterTablesModel.getTblInvoiceCaption();

                                if (tblInvoiceCaption.size() > 0) {
                                    for (TblInvoiceCaption InvoiceCaption : tblInvoiceCaption) {
                                        dbengine.savetblInvoiceCaption(InvoiceCaption.getInvPrefix(), InvoiceCaption.getVanIntialInvoiceIds(), InvoiceCaption.getInvSuffix());
                                    }
                                } else {
                                    blankTablearrayList.add("tblInvoiceCaption");
                                }
                            }

                            //table 33-------------------------------
                            if (allMasterTablesModel.getTblGetReturnsReasonForPDAMstr() != null) {
                                dbengine.Delete_tblGetReturnsReasonForPDAMstr();
                                List<TblGetReturnsReasonForPDAMstr> tblGetReturnsReasonForPDAMstr = allMasterTablesModel.getTblGetReturnsReasonForPDAMstr();

                                if (tblGetReturnsReasonForPDAMstr.size() > 0) {
                                    for (TblGetReturnsReasonForPDAMstr GetReturnsReasonForPDAMstr : tblGetReturnsReasonForPDAMstr) {
                                        dbengine.fnInsertTBLReturnRsn(GetReturnsReasonForPDAMstr.getStockStatusId(), GetReturnsReasonForPDAMstr.getStockStatus());
                                    }
                                } else {
                                    blankTablearrayList.add("tblGetReturnsReasonForPDAMstr");
                                }
                            }
                            //table 34-------------------------------
                            if (allMasterTablesModel.getTblIsSchemeApplicable() != null) {
                                dbengine.Delete_tblGetReturnsReasonForPDAMstr();
                                List<TblIsSchemeApplicable> tblIsSchemeApplicable = allMasterTablesModel.getTblIsSchemeApplicable();

                                if (tblIsSchemeApplicable.size() > 0) {
                                    for (TblIsSchemeApplicable IsSchemeApplicable : tblIsSchemeApplicable) {
                                        dbengine.SavePDAIsSchemeApplicable(IsSchemeApplicable.getIsSchemeApplicable());
                                    }
                                } else {
                                    blankTablearrayList.add("tblIsSchemeApplicable");
                                }
                            }
                            //table 35-------------------------------


                            //Nitish--------------------------------------------------

                            //table 51-------------------------------
                            if (allMasterTablesModel.getTblLastOutstanding() != null) {
                                dbengine.Delete_tblLastOutstanding_for_refreshData();
                                //deleted above
                                List<TblLastOutstanding> tblLastOutstanding = allMasterTablesModel.getTblLastOutstanding();

                                if (tblLastOutstanding.size() > 0) {

                                    dbengine.savetblLastOutstanding(tblLastOutstanding);

                                } else {
                                    blankTablearrayList.add("tblLastOutstanding");
                                }
                            }
                            //table 50-------------------------------
                            if (allMasterTablesModel.getTblInvoiceLastVisitDetails() != null) {
                                List<TblInvoiceLastVisitDetails> tblInvoiceLastVisitDetails = allMasterTablesModel.getTblInvoiceLastVisitDetails();

                                if (tblInvoiceLastVisitDetails.size() > 0) {

                                    dbengine.savetblInvoiceLastVisitDetails(tblInvoiceLastVisitDetails);

                                } else {
                                    blankTablearrayList.add("tblInvoiceLastVisitDetails");
                                }
                            }


                            //table 49-------------------------------
                            if (allMasterTablesModel.getTblPDAGetExecutionSummary() != null) {
                                dbengine.deltblPDAGetExecutionSummary();
                                //deleted above
                                List<TblPDAGetExecutionSummary> tblPDAGetExecutionSummary = allMasterTablesModel.getTblPDAGetExecutionSummary();

                                if (tblPDAGetExecutionSummary.size() > 0) {

                                    dbengine.inserttblForPDAGetExecutionSummary(tblPDAGetExecutionSummary);

                                } else {
                                    blankTablearrayList.add("tblPDAGetExecutionSummary");
                                }
                            }


                            //table 48-------------------------------
                            if (allMasterTablesModel.getTblPDAGetLastOrderDetailsTotalValues() != null) {
                                dbengine.deletetblPDAGetLastOrderDetailsTotalValues();
                                //deleted above
                                List<TblPDAGetLastOrderDetailsTotalValues> tblPDAGetLastOrderDetailsTotalValues = allMasterTablesModel.getTblPDAGetLastOrderDetailsTotalValues();

                                if (tblPDAGetLastOrderDetailsTotalValues.size() > 0) {

                                    dbengine.inserttblspForPDAGetLastOrderDetails_TotalValues(tblPDAGetLastOrderDetailsTotalValues);

                                } else {
                                    blankTablearrayList.add("tblPDAGetLastOrderDetailsTotalValues");
                                }
                            }


                            //table 47-------------------------------
                            if (allMasterTablesModel.getTblPDAGetLastOrderDetails() != null) {
                                dbengine.deltblPDAGetLastOrderDetailsData();
                                //deleted above
                                List<TblPDAGetLastOrderDetails> tblPDAGetLastOrderDetails = allMasterTablesModel.getTblPDAGetLastOrderDetails();

                                if (tblPDAGetLastOrderDetails.size() > 0) {

                                    dbengine.inserttblForPDAGetLastOrderDetails(tblPDAGetLastOrderDetails);

                                } else {
                                    blankTablearrayList.add("tblPDAGetLastOrderDetails");
                                }
                            }

                            //table 46-------------------------------
                            if (allMasterTablesModel.getTblPDAGetLastVisitDetails() != null) {
                                dbengine.deletetblPDAGetLastVisitDetails();
                                //deleted above
                                List<TblPDAGetLastVisitDetails> tblPDAGetLastVisitDetails = allMasterTablesModel.getTblPDAGetLastVisitDetails();

                                if (tblPDAGetLastVisitDetails.size() > 0) {

                                    dbengine.inserttblForPDAGetLastVisitDetails(tblPDAGetLastVisitDetails);

                                } else {
                                    blankTablearrayList.add("tblPDAGetLastVisitDetails");
                                }
                            }

                            //table 45-------------------------------
                            if (allMasterTablesModel.getTblPDAGetLastOrderDate() != null) {
                                dbengine.deletetblPDAGetLastOrderDateData();
                                //deleted above
                                List<TblPDAGetLastOrderDate> tblPDAGetLastOrderDate = allMasterTablesModel.getTblPDAGetLastOrderDate();

                                if (tblPDAGetLastOrderDate.size() > 0) {

                                    dbengine.inserttblForPDAGetLastOrderDate(tblPDAGetLastOrderDate);

                                } else {
                                    blankTablearrayList.add("tblPDAGetLastOrderDate");
                                }
                            }
                            //table 44-------------------------------
                            if (allMasterTablesModel.getTblPDAGetLastVisitDate() != null) {
                                dbengine.deletetblPDAGetLastVisitDate();
                                //deleted above
                                List<TblPDAGetLastVisitDate> tblPDAGetLastVisitDate = allMasterTablesModel.getTblPDAGetLastVisitDate();

                                if (tblPDAGetLastVisitDate.size() > 0) {

                                    dbengine.inserttblForPDAGetLastVisitDate(tblPDAGetLastVisitDate);

                                } else {
                                    blankTablearrayList.add("tblPDAGetLastVisitDate");
                                }
                            }
                            //table 43-------------------------------
                            if (allMasterTablesModel.getTblPDAGetLODQty() != null) {
                                dbengine.deletetblPDAGetLODQty();
                                //deleted above
                                List<TblPDAGetLODQty> tblPDAGetLODQty = allMasterTablesModel.getTblPDAGetLODQty();

                                if (tblPDAGetLODQty.size() > 0) {

                                    dbengine.inserttblLODOnLastSalesSummary(tblPDAGetLODQty);

                                } else {
                                    blankTablearrayList.add("tblPDAGetLODQty");
                                }
                            }
                            //table 42-------------------------------
                            if (allMasterTablesModel.getTblProductListLastVisitStockOrOrderMstr() != null) {
                                dbengine.deletetblProductListLastVisitStockOrOrderMstr();
                                //deleted above
                                List<TblProductListLastVisitStockOrOrderMstr> tblProductListLastVisitStockOrOrderMstr = allMasterTablesModel.getTblProductListLastVisitStockOrOrderMstr();

                                if (tblProductListLastVisitStockOrOrderMstr.size() > 0) {

                                    dbengine.savetblProductListLastVisitStockOrOrderMstr(tblProductListLastVisitStockOrOrderMstr);

                                } else {
                                    blankTablearrayList.add("tblProductListLastVisitStockOrOrderMstr");
                                }
                            }


                            //table 41-------------------------------
                            if (allMasterTablesModel.getTblStoreListMaster() != null) {
                                HashMap<String, String> hmapStoreIdSstat = new HashMap<String, String>();
                                hmapStoreIdSstat = dbengine.checkForStoreIdSstat();
                                HashMap<String, String> hmapflgOrderType = new HashMap<String, String>();
                                hmapflgOrderType = dbengine.checkForStoreflgOrderType();
                                HashMap<String, String> hmapStoreIdNewStore = new HashMap<String, String>();
                                hmapStoreIdNewStore = dbengine.checkForStoreIdIsNewStore();


                                HashMap<String, String> hmapStoreIdflgMapped = new HashMap<String, String>();
                                hmapStoreIdflgMapped = dbengine.checkForStoreIdFlgMapped();

                                HashMap<String, String> hmapStoreIdflgisDiscountApplicable = new HashMap<String, String>();
                                hmapStoreIdflgisDiscountApplicable = dbengine.checkForStoreIdIsDiscountApplicable();

                                HashMap<String, String> hmapStoreIdflgisDiscountAllowed = new HashMap<String, String>();
                                hmapStoreIdflgisDiscountAllowed = dbengine.checkForStoreIdIsDiscountAllowed();

                                HashMap<String, String> hmapStoreIdUpdatedContactNumber = new HashMap<String, String>();
                                hmapStoreIdUpdatedContactNumber = dbengine.checkForStoreIdUpdatedContactNumber();

                                //  dbengine.Delete_tblStore_for_refreshDataButNotNewStore();
                                //    dbengine.fndeleteStoreAddressMapDetailsMstr();

                                //deleted above
                                List<TblStoreListMaster> tblStoreListMaster = allMasterTablesModel.getTblStoreListMaster();

                                if (tblStoreListMaster.size() > 0) {
                                    dbengine.saveSOAPdataStoreList(tblStoreListMaster, hmapStoreIdSstat, hmapflgOrderType, hmapStoreIdNewStore, hmapStoreIdflgMapped, hmapStoreIdUpdatedContactNumber,hmapStoreIdflgisDiscountApplicable, hmapStoreIdflgisDiscountAllowed);

                                } else {
                                    blankTablearrayList.add("tblStoreListMaster");
                                }
                                if (hmapStoreIdSstat != null && hmapStoreIdSstat.size() > 0) {
                                    hmapStoreIdSstat.clear();
                                    hmapflgOrderType.clear();
                                    hmapStoreIdNewStore.clear();
                                    hmapStoreIdUpdatedContactNumber.clear();
                                    hmapStoreIdflgMapped.clear();
                                    hmapStoreIdSstat = null;
                                    hmapflgOrderType = null;
                                    hmapStoreIdNewStore = null;
                                    hmapStoreIdUpdatedContactNumber = null;
                                    hmapStoreIdflgMapped = null;

                                }
                            }


                            //table 40-------------------------------
                            if (allMasterTablesModel.getTblStoreListWithPaymentAddress() != null) {
                                //deleted above
                                List<TblStoreListWithPaymentAddress> tblStoreListWithPaymentAddress = allMasterTablesModel.getTblStoreListWithPaymentAddress();

                                if (tblStoreListWithPaymentAddress.size() > 0) {

                                    dbengine.saveSOAPdataStoreListAddressMap(tblStoreListWithPaymentAddress);

                                } else {
                                    blankTablearrayList.add("tblStoreListWithPaymentAddressMR");
                                }
                            }


                            //table 40-------------------------------

                            //deleted above
                            if (allMasterTablesModel.getTblStoreSomeProdQuotePriceMstr() != null) {
                                List<TblStoreSomeProdQuotePriceMstr> tblStoreSomeProdQuotePriceMstr = allMasterTablesModel.getTblStoreSomeProdQuotePriceMstr();

                                if (tblStoreSomeProdQuotePriceMstr.size() > 0) {

                                    dbengine.insertMinDelQty(tblStoreSomeProdQuotePriceMstr);

                                } else {
                                    blankTablearrayList.add("tblStoreSomeProdQuotePriceMstr");
                                }
                            }

                            //table 39-------------------------------

                            //deleted above
                            if (allMasterTablesModel.getTblStoreLastDeliveryNoteNumber() != null) {
                                List<TblStoreLastDeliveryNoteNumber> tblStoreLastDeliveryNoteNumber = allMasterTablesModel.getTblStoreLastDeliveryNoteNumber();

                                if (tblStoreLastDeliveryNoteNumber.size() > 0) {

                                    for (TblStoreLastDeliveryNoteNumber tblStoreLastDeliveryNoteNumberData : tblStoreLastDeliveryNoteNumber) {
                                        int LastDeliveryNoteNumber = 0;
                                        LastDeliveryNoteNumber = tblStoreLastDeliveryNoteNumberData.getLastDeliveryNoteNumber();
                                        int valExistingDeliveryNoteNumber = 0;
                                        valExistingDeliveryNoteNumber = dbengine.fnGettblStoreLastDeliveryNoteNumber();
                                        if (valExistingDeliveryNoteNumber < LastDeliveryNoteNumber) {
                                            dbengine.Delete_tblStoreLastDeliveryNoteNumber();
                                            dbengine.savetblStoreLastDeliveryNoteNumber(LastDeliveryNoteNumber);
                                        }
                                    }


                                } else {
                                    blankTablearrayList.add("tblStoreLastDeliveryNoteNumber");
                                }
                            }


                            HashMap<String, String> hmapInvoiceOrderIDandStatus = new HashMap<String, String>();
                            hmapInvoiceOrderIDandStatus = dbengine.fetchHmapInvoiceOrderIDandStatus();
                            if (allMasterTablesModel.getTblPendingInvoices() != null) {
                                List<TblPendingInvoices> tblPendingInvoices = allMasterTablesModel.getTblPendingInvoices();

                                if (tblPendingInvoices.size() > 0) {

                                    dbengine.inserttblPendingInvoices(tblPendingInvoices, hmapInvoiceOrderIDandStatus, imei);

                                } else {
                                    blankTablearrayList.add("tblPendingInvoices");
                                }
                            }


                            if (allMasterTablesModel.getTblInvoiceExecutionProductList() != null) {

                                List<TblInvoiceExecutionProductList> tblInvoiceExecutionProductList = allMasterTablesModel.getTblInvoiceExecutionProductList();
                                dbengine.fnDeletetblInvoiceExecutionProductList();
                                if (tblInvoiceExecutionProductList.size() > 0) {

                                    dbengine.inserttblInvoiceExecutionProductList(tblInvoiceExecutionProductList);

                                } else {
                                    blankTablearrayList.add("tblInvoiceExecutionProductList");
                                }
                            }


                            if (allMasterTablesModel.getTblProductWiseInvoice() != null) {
                                List<TblProductWiseInvoice> tblProductWiseInvoice = allMasterTablesModel.getTblProductWiseInvoice();
                                if (tblProductWiseInvoice.size() > 0) {

                                    dbengine.inserttblProductWiseInvoice(tblProductWiseInvoice, hmapInvoiceOrderIDandStatus);

                                } else {
                                    blankTablearrayList.add("tblProductWiseInvoice");
                                }
                            }

                            //nitishtable
                            boolean isDistributorStockSave = false;
                            if(flgCldFrm==0)
                            {
                                isDistributorStockSave=true;
                            }
                            else if((flgCldFrm==1) &&(CommonInfo.hmapAppMasterFlags.get("flgDBRStockCalculate")==1))
                            {
                                isDistributorStockSave=true;
                            }
                            if (isDistributorStockSave) {
                                if (allMasterTablesModel.getTblDistributorIDOrderIDLeft() != null) {
                                    dbengine.deleteDistStock();
                                    List<TblDistributorIDOrderIDLeft> tblDistributorIDOrderIDLeft = allMasterTablesModel.getTblDistributorIDOrderIDLeft();
                                    if (tblDistributorIDOrderIDLeft.size() > 0) {

                                        for (TblDistributorIDOrderIDLeft tblDistributorIDOrderIDLeftData : tblDistributorIDOrderIDLeft) {
                                            dbengine.inserttblDistributorIDOrderIDLeft(tblDistributorIDOrderIDLeftData.getCustomer(), tblDistributorIDOrderIDLeftData.getPDAOrderId());
                                        }


                                    } else {
                                        blankTablearrayList.add("tblDistributorIDOrderIDLeft");
                                    }
                                }

                                if (allMasterTablesModel.getTblDistributorProductStock() != null) {
                                    List<TblDistributorProductStock> tblDistributorProductStock = allMasterTablesModel.getTblDistributorProductStock();
                                    if (tblDistributorProductStock.size() > 0) {


                                        dbengine.inserttblDistributorProductStock(tblDistributorProductStock);

                                    } else {
                                        blankTablearrayList.add("tblDistributorProductStock");
                                    }
                                }

                            }


                            if (allMasterTablesModel.getTblReasonOrderCncl() != null) {
                                dbengine.fnDeleteUnWantedSubmitedInvoiceOrders();

                                hmapInvoiceOrderIDandStatus = null;

                                dbengine.delTblReasonOrderCncl();
                                List<TblReasonOrderCncl> tblReasonOrderCncl = allMasterTablesModel.getTblReasonOrderCncl();

                                if (tblReasonOrderCncl.size() > 0) {
                                    for (TblReasonOrderCncl tblReasonOrderCnclModel : tblReasonOrderCncl) {
                                        dbengine.insertReasonCanclOrder(tblReasonOrderCnclModel.getReasonCodeID(), tblReasonOrderCnclModel.getReasonDescr());
                                    }
                                } else {
                                    blankTablearrayList.add("tblReasonOrderCncl");
                                }
                            }


                *//*    dbengine.deleteIncentivesTbles();


                    List<TblIncentiveMainMaster> tblIncentiveMaster=  allMasterTablesModel.getTblIncentiveMainMaster();

                    if(tblIncentiveMaster.size()>0){
                        for(TblIncentiveMainMaster tblIncentiveMasterModel:tblIncentiveMaster){
                            dbengine.savetblIncentiveMaster(tblIncentiveMasterModel.getIncId(),tblIncentiveMasterModel.getOutputType(),tblIncentiveMasterModel.getIncentiveName(),""+tblIncentiveMasterModel.getFlgAcheived(),""+tblIncentiveMasterModel.getEarning());
                        }
                    }
                    else{
                        blankTablearrayList.add("tblIncentiveMaster");
                    }

                    List<TblIncentiveSecondaryMaster> tblIncentiveSecondaryMaster=  allMasterTablesModel.getTblIncentiveSecondaryMaster();

                    if(tblIncentiveSecondaryMaster.size()>0){
                        for(TblIncentiveSecondaryMaster tblIncentiveSecondaryMasterModel:tblIncentiveSecondaryMaster){
                            dbengine.savetblIncentiveSeondaryMaster(tblIncentiveSecondaryMasterModel.getIncSlabId(),tblIncentiveSecondaryMasterModel.getIncId(), tblIncentiveSecondaryMasterModel.getOutputType(), tblIncentiveSecondaryMasterModel.getIncSlabName(),""+tblIncentiveSecondaryMasterModel.getFlgAcheived(),""+tblIncentiveSecondaryMasterModel.getEarning());
                        }
                    }
                    else{
                        blankTablearrayList.add("tblIncentiveSecondaryMaster");
                    }
                    Object tblIncentiveDetailsData=allMasterTablesModel.getTblIncentiveDetailsData();
                    ArrayList<HashMap<String,String>> tblIncentiveDetailsDataTable=(ArrayList<HashMap<String,String>>) tblIncentiveDetailsData;

                    if( tblIncentiveDetailsDataTable.size()>0) {
                        for(int i=0;i<10;i++)
                        {
                           String sdqeqwe= tblIncentiveDetailsDataTable.get(0).toString();
                        }
                    }


*//*

                            if (allMasterTablesModel.getTblQuestionsSurvey() != null) {

                                dbengine.deleteSurveyTables();
                                List<TblQuestionsSurvey> tblQuestionsSurvey = allMasterTablesModel.getTblQuestionsSurvey();

                                if (tblQuestionsSurvey.size() > 0) {
                                    for (TblQuestionsSurvey tblQuestionsSurveyModel : tblQuestionsSurvey) {
                                        dbengine.fnsavetblQuestionsSurvey("" + tblQuestionsSurveyModel.getQstnID(), tblQuestionsSurveyModel.getQstnText(), "" + tblQuestionsSurveyModel.getFlgActive(), "" + tblQuestionsSurveyModel.getFlgOrder());
                                    }
                                } else {
                                    blankTablearrayList.add("tblQuestionsSurvey");
                                }
                            }

                            if (allMasterTablesModel.getTblOptionSurvey() != null) {
                                List<TblOptionSurvey> tblOptionSurvey = allMasterTablesModel.getTblOptionSurvey();

                                if (tblOptionSurvey.size() > 0) {
                                    for (TblOptionSurvey tblOptionSurveyModel : tblOptionSurvey) {
                                        dbengine.fnsavetblOptionSurvey("" + tblOptionSurveyModel.getQstnID(), tblOptionSurveyModel.getOptionText(), "" + tblOptionSurveyModel.getQstnID(), "" + tblOptionSurveyModel.getFlgaActive());
                                    }
                                } else {
                                    blankTablearrayList.add("tblOptionSurvey");
                                }
                            }


                            dbengine.fnInsertOrUpdate_tblAllServicesCalledSuccessfull(1);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            if (progressHUD != null && progressHUD.isShowing())
                                progressHUD.dismiss();
                            interfaceRetrofit.success();
                        }
                    }.execute();
                    // sendIntentToOtherActivityAfterAllDataFetched();
                } else {
                    if (progressHUD != null && progressHUD.isShowing())
                        progressHUD.dismiss();
                    interfaceRetrofit.failure();
                    // showAlertForError("Error while retreiving data from server");
                }
            }

            @Override
            public void onFailure(Call<AllMasterTablesModel> call, Throwable t) {
                System.out.println();
                t.printStackTrace();
                dbengine.fnInsertOrUpdate_tblAllServicesCalledSuccessfull(0);
                if (progressHUD != null && progressHUD.isShowing())
                    progressHUD.dismiss();
                interfaceRetrofit.failure();//t
                //   showAlertForError("Error while retreiving data from server");
            }
        });


    }

    public static void showAlertSingleButtonError(String msg, Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.AlertDialogHeaderErrorMsg))
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(R.drawable.error_ico)
                .setPositiveButton(activity.getResources().getString(R.string.AlertDialogOkButton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }*/

}
