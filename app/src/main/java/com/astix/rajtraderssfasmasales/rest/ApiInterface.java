package com.astix.rajtraderssfasmasales.rest;



import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblAllScoutingDistributorReturnedTableAfterServerSaving;
import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblAllScoutingDistributorReturnedTables;
import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblDistributorScoutingParameterData;
import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblDistributorScoutingParameterForSavingDataToServer;
import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblDistributorScoutingRetailerFeedBackParameterForSavingDataToServer;
import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblPotentialDistributor;
import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblPotentialDistributorForDelete;
import com.astix.rajtraderssfasmasales.ExecutionModelsData;
import com.astix.rajtraderssfasmasales.SignUpModule.TblAllValidationData;
import com.astix.rajtraderssfasmasales.SignUpModule.TblContactNum;
import com.astix.rajtraderssfasmasales.model.AllAddedOutletSummaryReportModel;
import com.astix.rajtraderssfasmasales.model.AllAttendanceAPIInsertTablesModel;
import com.astix.rajtraderssfasmasales.model.AllMasterTablesModel;
import com.astix.rajtraderssfasmasales.model.AllSummaryReportDay;
import com.astix.rajtraderssfasmasales.model.AllSummarySKUWiseDay;
import com.astix.rajtraderssfasmasales.model.AllSummaryStoreSKUWiseDay;
import com.astix.rajtraderssfasmasales.model.AllSummaryStoreWiseDay;
import com.astix.rajtraderssfasmasales.model.AllTargetVsAchieved;
import com.astix.rajtraderssfasmasales.model.ConfirmVanStock;
import com.astix.rajtraderssfasmasales.model.Data;
import com.astix.rajtraderssfasmasales.model.DataParametersDistributionReportsPersonSKUWise;
import com.astix.rajtraderssfasmasales.model.DistributorStockData;
import com.astix.rajtraderssfasmasales.model.DistributorTodaysStock;
import com.astix.rajtraderssfasmasales.model.IMEIVersionDetails;
import com.astix.rajtraderssfasmasales.model.IMEIVersionParentModel;
import com.astix.rajtraderssfasmasales.model.PDAConfirmVanStockModel;
import com.astix.rajtraderssfasmasales.model.PersonInfo;
import com.astix.rajtraderssfasmasales.model.RegistrationValidation;
import com.astix.rajtraderssfasmasales.model.ReportsAddedOutletSummary;
import com.astix.rajtraderssfasmasales.model.ReportsInfo;
import com.astix.rajtraderssfasmasales.model.StatusInfo;
import com.astix.rajtraderssfasmasales.model.StockData;
import com.astix.rajtraderssfasmasales.model.StockUploadedStatus;
import com.astix.rajtraderssfasmasales.model.StoreListTableModel;
import com.astix.rajtraderssfasmasales.model.SubOrdinateModel;
import com.astix.rajtraderssfasmasales.model.TblAllDistributionPersonSKUReturnTableList;
import com.astix.rajtraderssfasmasales.model.TblAllDistributionReportReturnedTables;
import com.astix.rajtraderssfasmasales.model.TblAllOrderOnMailConfirmData;
import com.astix.rajtraderssfasmasales.model.TblAllSaleManReportReturnedTablesTargetVsAch;
import com.astix.rajtraderssfasmasales.model.TblAllSalesDistributionFleetReturnedTables;
import com.astix.rajtraderssfasmasales.model.TblAllValidationNewStoreMobileNumber;
import com.astix.rajtraderssfasmasales.model.TblAllValidationRouteData;
import com.astix.rajtraderssfasmasales.model.TblAllValidationStoreContactData;
import com.astix.rajtraderssfasmasales.model.TblAttandanceDetailsDataModel;
import com.astix.rajtraderssfasmasales.model.TblCurrentServerDateTimeData;
import com.astix.rajtraderssfasmasales.model.TblDistributionReportParameterData;
import com.astix.rajtraderssfasmasales.model.TblPDAVanDayEndDetResult;
import com.astix.rajtraderssfasmasales.model.TblRequestForOrderOnMainData;
import com.astix.rajtraderssfasmasales.model.TblRouteValidation;
import com.astix.rajtraderssfasmasales.model.TblSalesReportStoreListParameterData;
import com.astix.rajtraderssfasmasales.model.TblSaveVanStockRequestResult;
import com.astix.rajtraderssfasmasales.model.TblStoreContactNum;
import com.astix.rajtraderssfasmasales.model.TblStoreNumberValidationWhileAddStore;
import com.astix.rajtraderssfasmasales.model.TblSummaryReportDetails;
import com.astix.rajtraderssfasmasales.model.TblTargetVsAchSalesPersonReportParameterData;
import com.astix.rajtraderssfasmasales.model.VanDayEnd;
import com.astix.rajtraderssfasmasales.model.VanStockRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {



    @POST("Home/PersonValidation")
    Call<IMEIVersionParentModel> Call_IMEIVersionDetailStatus(@Body IMEIVersionDetails IMEIVersionDetails);

    @POST("Home/AllDataASM")
    Call<AllMasterTablesModel> Call_AllMasterData(@Body Data data);

    @POST("Home/getinvoicelist")
    Call<ExecutionModelsData> Call_AllExecutionData(@Body Data data);

    @POST("Home/GetAllDaySummary")
    Call<AllSummaryReportDay> Call_AllSummaryReportDay(@Body ReportsInfo reportsInfo);

    @POST("Home/GetSKUWiseDaySummary")
    Call<AllSummarySKUWiseDay> Call_AllSummarySKUWiseDay(@Body ReportsInfo reportsInfo);

    @POST("Home/GetSKUWiseMTDSummary")
    Call<AllSummarySKUWiseDay> Call_AllSummarySKUWiseMTDDay(@Body ReportsInfo reportsInfo);

    @POST("Home/GetStoreWiseDaySummary")
    Call<AllSummaryStoreWiseDay> Call_AllSummaryStoreWiseDay(@Body ReportsInfo reportsInfo);

    @POST("Home/GetStoreWiseMTDSummary")
    Call<AllSummaryStoreWiseDay> Call_AllSummaryStoreWiseMTDDay(@Body ReportsInfo reportsInfo);

    @POST("Home/GetStoreSKUWiseDaySummary")
    Call<AllSummaryStoreSKUWiseDay> Call_AllSummaryStoreSKUWiseDay(@Body ReportsInfo reportsInfo);


    @POST("Home/GetStoreSKUWiseMTDSummary")
    Call<AllSummaryStoreSKUWiseDay> Call_AllSummaryStoreSKUWiseMTDDay(@Body ReportsInfo reportsInfo);


    @POST("Home/GetTargetVsAchieved")
    Call<AllTargetVsAchieved> Call_AllTargetVsAchieved(@Body Data data);


    @POST("Home/GetPDAGetAddedOutletSummaryReport")
    Call<AllAddedOutletSummaryReportModel> Call_AllPDAGetAddedOutletSummaryReport(@Body ReportsAddedOutletSummary reportsAddedOutletSummary);

    @POST("Home/GetPersonDetail")
    Call<RegistrationValidation> Call_GetRegistrationDetails(@Body PersonInfo personInfo);


    @POST("Home/SaveVanStockRequest")
    Call<TblSaveVanStockRequestResult> Call_SaveVanStockRequest(@Body VanStockRequest VanStockRequest);

    @POST("Home/PDAConfirmVanStock")
    Call<PDAConfirmVanStockModel> Call_PDAConfirmVanStock(@Body ConfirmVanStock ConfirmVanStock);

    @POST("Home/PDAVanDayEnd")
    Call<TblPDAVanDayEndDetResult> Call_PDAVanDayEnd(@Body VanDayEnd VanDayEnd);

    @POST("Home/StockMaster")
    Call<StockData> Call_StockData(@Body Data data);

    @POST("Home/GetDistributorTodaysStock")
    Call<DistributorStockData> Call_DistributorTodayStockData(@Body DistributorTodaysStock data);

    @POST("Home/GetServerTime")
    Call<TblCurrentServerDateTimeData> Call_TblCurrentServerDateTimeData();

    @POST("Home/GetStockUploadStatus")
    Call<StockUploadedStatus> Call_GetStockUploadStatus(@Body StatusInfo statusInfo);


    @POST("Home/SendSMS")
    Call<ResponseBody> sendOtp(@Query("Mobile")String mobile, @Query("Msg")String OTP);

    @POST("Home/UserValidation")
    Call<TblAllValidationData> userValidate(@Body TblContactNum tblContactNum);



    @POST("Home/fnRequestforOrdersOnMail")
    Call<TblAllOrderOnMailConfirmData> Call_fnSaveRequestforOrdersOnMail(@Body TblRequestForOrderOnMainData tblRequestForOrderOnMainData);


    @POST("Home/Call_MarkAttendanceAPI")
    Call<AllAttendanceAPIInsertTablesModel> Call_MarkAttendanceAPI(@Body TblAttandanceDetailsDataModel data);

    @POST("Home/UserValidationStoreContactDetails")
    Call<TblAllValidationStoreContactData> userValidateStoreContact(@Body TblStoreContactNum tblStoreContactNum);


    @POST("Home/PDARouteChangeApproval")
    Call<TblAllValidationRouteData> PDARouteChangeApproval(@Body TblRouteValidation tblRouteValidation);

    @POST("Home/StoreContactDuplicate")
    Call<TblAllValidationNewStoreMobileNumber> StoreContactDuplicate(@Body TblStoreNumberValidationWhileAddStore tblStoreNumberValidationWhileAddStore);

    @POST("Home/GetStoreDetailsForDay")
    Call<StoreListTableModel> Call_StoreListData(@Body Data data);


    @POST("Home/GetSubordinateList")
    Call<SubOrdinateModel> Call_SubOrdinateListData(@Body Data data);


    @POST("Home/DistributionReportMTD")
    Call<TblAllDistributionReportReturnedTables> DistributionReportMTD(@Body TblDistributionReportParameterData tblDistributionReportParameterData);

    @POST("Home/GetDailyReportDistributorStock")
    Call<TblAllDistributionPersonSKUReturnTableList> GetDailyReportDistributorStock(@Body DataParametersDistributionReportsPersonSKUWise dataParametersDistributionReportsPersonSKUWise);

    @POST("Home/GetSpTargetVsAchievement_New")
    Call<TblAllSaleManReportReturnedTablesTargetVsAch> SalesManReportTargetVsAch(@Body TblTargetVsAchSalesPersonReportParameterData tblTargetVsAchSalesPersonReportParameterData);


    @POST("Home/GetPDAGetSummaryReportDetail")
    Call<TblAllSalesDistributionFleetReturnedTables> GetPDAGetSummaryReportDetail(@Body TblSalesReportStoreListParameterData tblSalesReportStoreListParameterData);


    @POST("Home/GetPDAGetPotentialDealerList")
    Call<TblAllScoutingDistributorReturnedTables> GetPDAGetPotentialDealerList(@Body TblDistributorScoutingParameterData tblDistributorScoutingParameterData);

    @POST("Home/SavePotentialDistributorData")
    Call<TblAllScoutingDistributorReturnedTableAfterServerSaving> SavePotentialDistributorData(@Body TblPotentialDistributor reportsInfo);


    @POST("Home/SavePotentialDistributor_RetailerFeedbackData")
    Call<TblAllScoutingDistributorReturnedTableAfterServerSaving> SavePotentialDistributorRetailerFeedBackData(@Body TblDistributorScoutingRetailerFeedBackParameterForSavingDataToServer reportsInfo);


    @POST("Home/DeletePotentialDistributorData")
    Call<Object> DeletePotentialDistributorData(@Body TblPotentialDistributorForDelete reportsInfo);

    @POST("Home/DeleteDistributorData")
    Call<Object> DeleteDistributorData(@Body TblPotentialDistributorForDelete reportsInfo);


}
