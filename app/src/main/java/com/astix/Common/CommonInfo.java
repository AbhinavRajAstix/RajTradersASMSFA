package com.astix.Common;

import android.net.Uri;

import com.astix.rajtraderssfasmasales.BuildConfig;

import java.io.File;
import java.util.LinkedHashMap;

public class CommonInfo {
    //firebaseID astix8  pass: astix1234
    public static String APP_TOKEN_PREFS_NAME = "tokenPrefs";
    public static String APP_TOKEN_PREFS_KEY = "token";


    //UAT
/*public static final String LastTrackPreference = "RajTradersLastTrackPrefrenceUAT";
    public static String ContactNumber = "";
    public static String ActiveRouteSM = "0";
    public static int AnyVisit = 0;
    public static String AppLatLngJsonFile = "RajTradersOrderSFALatLngJsonUAT";

    public static int Application_TypeID = 4;
    public static final String AttandancePreference = "RajTradersOrderAttandancePreferenceUAT";
    public static int CoverageAreaNodeID = 0;
    public static int CoverageAreaNodeType = 0;
    public static String DATABASE_NAME = "AppDataSource";
    public static int DATABASE_VERSIONID = BuildConfig.VERSION_CODE;
    public static String AppVersionID = BuildConfig.VERSION_NAME;
    public static int flgDrctslsIndrctSls = 2;
    public static int DayStartClick = 0;
    public static int DistanceRange = 3000;
    public static final String DistributorCheckInXMLFolder = "RajTradersOrderDistributorCheckInXMLUAT";
    public static final String DistributorMapXMLFolder = "RajTradersOrderDistributorMapXMUATL";
    public static final String DistributorStockXMLFolder = "RajTradersOrderDistributorStockXMLUAT";
    //public static String DistributorSyncPath = "http://103.20.212.194/ReadXML_RajTradersDistributionStagingelopment/Default.aspx";
    public static String FinalLatLngJsonFile = "RajTradersOrderSFAFinalLatLngJson";
    public static int FlgDSRSO = 0;
    //public static String ImageSyncPath = "http://103.20.212.194/ReadXML_RajTradersImagesStagingelopment/Default.aspx";
    public static String ImagesFolder = "RajTradersOrderSFAImagesUAT";
    public static String ImagesFolderServer = ".RajTradersOrderSFAImagesServer";
    //public static String InvoiceSyncPath = "http://103.20.212.194/ReadXML_RajTradersInvoiceStagingelopment/DefaultGT.aspx";
    public static String InvoiceXMLFolder = "RajTradersOrderInvoiceXmlUAT";
    //public static String OrderSyncPath = "http://103.20.212.194/ReadXML_RajTradersStagingelopment/DefaultGTSFA.aspx";
    public static String OrderSyncPathDistributorMap = "http://103.20.212.194/ReadXML_RajTradersStaging/DefaultSODistributorMappingGT.aspx";
    public static String OrderTextSyncPath = "http://103.20.212.194/ReadTxtFileForRajTradersStaging/default.aspx";
    public static String OrderXMLFolder = "RajTradersOrderSFAXml";
    public static final String Preference = "RajTradersOrderPrefrence";
    public static final String CycleOrDayEndPreference = "CycleOrDayEndPreference";
    public static String SalesPersonTodaysTargetMsg = "";
    public static String SalesQuoteId = "BLANK";
    public static int SalesmanNodeId = 0;
    public static int SalesmanNodeType = 0;
    public static String TextFileFolder = "RajTradersOrderTextFile";
    public static String VersionDownloadAPKName = "RajTradersASMUAT.apk";
    public static String VersionDownloadPath = "http://103.107.67.196/downloads/";
    public static String WebServicePath = "http://103.20.212.194/WebServiceAndroidAllanaDevelopment/Service.asmx";
    public static String WebStockInUrl = "http://103.20.212.194/Allanadev/manageorder/frmstockin.aspx";
    public static String WebStockOutUrl = "http://103.20.212.194/Allanadev/manageorder/frmStockTransferToVanDetail_PDA.aspx";
    public static String clickedTagPhoto_savedInstance = null;
    public static String fileContent = "";
    public static int flgAllRoutesData = 1;
    public static int flgDataScope = 0;
    public static String globalValueOfPaymentStage = "0_0_0";
    public static File imageF_savedInstance = null;
    public static String imageName_savedInstance = null;
    public static String token = "";
    public static int VanLoadedUnloaded = 0;
    public static String newQuottionID = "NULL";
    public static String prcID = "NULL";
    public static String quatationFlag = "";
    public static String sPrefVanLoadedUnloaded = "VanLoadedUnloaded";
    public static Uri uriSavedImage_savedInstance = null;
    public static String TextFileName = "RajTradersAllDetails";
    public static String TextFileArrayName = "AllDetails";


    public static final String COMMON_SYNC_PATH_URL = "http://103.107.67.196/RajSM_PDAFileReceivingApp_UAT/Default.aspx?FileType=";

    public static String ClientFileNameOrderSync = "XML_Files";
    public static String ClientFileNameImageSyncPath = "IMAGE_ImageFiles";
    public static String ClientFileNameInvoiceSyncPath = "XML_InvoiceFile_GT";
    public static String ClientFileNameDistributorSyncPath = "XML_DistributionFile_GT";
    public static String ClientFileNameDistributorMapPath = "XML_DistributionMap_GT";

    public static final String Invoice_Database_Assistant_DB_NAME = "XMLInvoiceFile";
    public static final String Database_Assistant_Distributor_Entry_DB_NAME = "DistributorDataFile";
    public static final String Database_Assistant_DB_NAME = "DBRajTraders";

    public static final String BASE_URL = "http://103.107.67.196/Raj_PDADataAPI_UAT/";
    //    public static final String BASE_URL = "http://103.20.212.67/Allana_PDADataAPI_Test/";
    public static String RegistrationID = "NotGettingFromServer";
    public static String crntServerTimecrntAttndncTime = "";
    public static Integer flgCollDefControl = 0;
    public static Double CollectionPer = 0.00;
    public static LinkedHashMap<String, Integer> hmapAppMasterFlags = new LinkedHashMap<String, Integer>();//Filled from All Button Activicty On onCreate Method
    public static int flgLangChangeReuired = 0;
    public static String VALIDATION_CODE = "validation_code";
    public static final String MTAS_ORDER_WEB_URL = "http://103.107.67.196/RajTradersTars_UAT/pda/mTas.aspx?pdacode=";
    public static int maxAllowedPhotos=2;

    public static  String PURCHASE_ORDER_WEB_URL = "http://103.107.67.196/RajTradersTars_UAT/frmLoginPDA.aspx?IMEI=";
    public static  String WHATSAPP_ENROLL_WEB_URL = "http://103.107.67.196/RajTradersTars_UAT/PDA/frmWhatsppRegistration.aspx?sid=";
    //public static final String URLLinkToViewStoreOverWebProtal="http://103.20.212.67/LTACE_Android10/Mobile/fnProfileDataforBeat.aspx";
    public static final String URLLinkToViewStoreOverWebProtal="http://103.107.67.196/RajTradersTars_UAT/Mobile/fnProfileDataforBeat_New.aspx";

    //public static final String WebUrlLinkforLastVisitDetails = "http://103.20.212.67/LTACE_Android10/Mobile/frmOutletProfile.aspx";
    public static final String WebUrlLinkforLastVisitDetails = "http://103.107.67.196/RajTradersTars_UAT/Mobile/frmOutletProfile.aspx";

    // public static String WebPageUrlDataReport="http://103.20.212.67/RajTradersTars_Dev/Mobile/fnSalesmanWiseSummaryRpt.aspx";
    public static String WebPageUrlDataReport="http://103.107.67.196/RajTradersTars_UAT/Mobile/frmDayTrackerReport_Mobile.aspx";

    //public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmRouteTracking.aspx";
    public static String WebPageUrl="http://103.107.67.196/RajTradersTars_UAT/Mobile/frmSMTracker_Mobile.aspx";
    // public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmDayTrackerReport_Mobile.aspx";
    public static String WebAttendanceReport="http://103.107.67.196/RajTradersTars_UAT/Mobile/frmTodayAttendence.aspx";
    public static String WebScndryUpdate="http://103.107.67.196/RajTradersTars_UAT/Mobile/frmDailySecondaryUpdate.aspx";
    public static int PersonNodeID=0;
    public static int PersonNodeType=0;
    public static  LinkedHashMap<String,String> hmapStoreLocationDetails=new LinkedHashMap<String,String>();*/

//Live
/*public static final String LastTrackPreference = "RajTradersLastTrackPrefrence";
    public static String ContactNumber = "";
    public static String ActiveRouteSM = "0";
    public static int AnyVisit = 0;
    public static String AppLatLngJsonFile = "RajTradersOrderSFALatLngJson";

    public static int Application_TypeID = 4;
    public static final String AttandancePreference = "RajTradersOrderAttandancePreference";
    public static int CoverageAreaNodeID = 0;
    public static int CoverageAreaNodeType = 0;
    public static String DATABASE_NAME = "AppDataSource";
    public static int DATABASE_VERSIONID = BuildConfig.VERSION_CODE;
    public static String AppVersionID = BuildConfig.VERSION_NAME;
    public static int flgDrctslsIndrctSls = 2;
    public static int DayStartClick = 0;
    public static int DistanceRange = 3000;
    public static final String DistributorCheckInXMLFolder = "RajTradersOrderDistributorCheckInXML";
    public static final String DistributorMapXMLFolder = "RajTradersOrderDistributorMapXML";
    public static final String DistributorStockXMLFolder = "RajTradersOrderDistributorStockXML";
    //public static String DistributorSyncPath = "http://103.20.212.194/ReadXML_RajTradersDistributionStagingelopment/Default.aspx";
    public static String FinalLatLngJsonFile = "RajTradersOrderSFAFinalLatLngJson";
    public static int FlgDSRSO = 0;
    //public static String ImageSyncPath = "http://103.20.212.194/ReadXML_RajTradersImagesStagingelopment/Default.aspx";
    public static String ImagesFolder = "RajTradersOrderSFAImagesStaging";
    public static String ImagesFolderServer = ".RajTradersOrderSFAImagesServer";
    //public static String InvoiceSyncPath = "http://103.20.212.194/ReadXML_RajTradersInvoiceStagingelopment/DefaultGT.aspx";
    public static String InvoiceXMLFolder = "RajTradersOrderInvoiceXmlStaging";
    //public static String OrderSyncPath = "http://103.20.212.194/ReadXML_RajTradersStagingelopment/DefaultGTSFA.aspx";
    public static String OrderSyncPathDistributorMap = "http://103.20.212.194/ReadXML_RajTradersStaging/DefaultSODistributorMappingGT.aspx";
    public static String OrderTextSyncPath = "http://103.20.212.194/ReadTxtFileForRajTradersStaging/default.aspx";
    public static String OrderXMLFolder = "RajTradersOrderSFAXml";
    public static final String Preference = "RajTradersOrderPrefrence";
    public static final String CycleOrDayEndPreference = "CycleOrDayEndPreference";
    public static String SalesPersonTodaysTargetMsg = "";
    public static String SalesQuoteId = "BLANK";
    public static int SalesmanNodeId = 0;
    public static int SalesmanNodeType = 0;
    public static String TextFileFolder = "RajTradersOrderTextFile";
    public static String VersionDownloadAPKName = "RajTradersASM.apk";
    public static String VersionDownloadPath = "http://www.rajsuperwhite.in/downloads/";
    public static String WebServicePath = "http://103.20.212.194/WebServiceAndroidAllanaDevelopment/Service.asmx";
    public static String WebStockInUrl = "http://103.20.212.194/Allanadev/manageorder/frmstockin.aspx";
    public static String WebStockOutUrl = "http://103.20.212.194/Allanadev/manageorder/frmStockTransferToVanDetail_PDA.aspx";
    public static String clickedTagPhoto_savedInstance = null;
    public static String fileContent = "";
    public static int flgAllRoutesData = 1;
    public static int flgDataScope = 0;
    public static String globalValueOfPaymentStage = "0_0_0";
    public static File imageF_savedInstance = null;
    public static String imageName_savedInstance = null;
    public static String token = "";
    public static int VanLoadedUnloaded = 0;
    public static String newQuottionID = "NULL";
    public static String prcID = "NULL";
    public static String quatationFlag = "";
    public static String sPrefVanLoadedUnloaded = "VanLoadedUnloaded";
    public static Uri uriSavedImage_savedInstance = null;
    public static String TextFileName = "RajTradersAllDetails";
    public static String TextFileArrayName = "AllDetails";


    public static final String COMMON_SYNC_PATH_URL = "http://www.rajsuperwhite.in/RajSM_PDAFileReceivingApp_Live/Default.aspx?FileType=";

    public static String ClientFileNameOrderSync = "XML_Files";
    public static String ClientFileNameImageSyncPath = "IMAGE_ImageFiles";
    public static String ClientFileNameInvoiceSyncPath = "XML_InvoiceFile_GT";
    public static String ClientFileNameDistributorSyncPath = "XML_DistributionFile_GT";
    public static String ClientFileNameDistributorMapPath = "XML_DistributionMap_GT";

    public static final String Invoice_Database_Assistant_DB_NAME = "XMLInvoiceFile";
    public static final String Database_Assistant_Distributor_Entry_DB_NAME = "DistributorDataFile";
    public static final String Database_Assistant_DB_NAME = "DBRajTraders";

    public static final String BASE_URL = "http://www.rajsuperwhite.in/Raj_PDADataAPI_Live/";
    //    public static final String BASE_URL = "http://103.20.212.67/Allana_PDADataAPI_Test/";
    public static String RegistrationID = "NotGettingFromServer";
    public static String crntServerTimecrntAttndncTime = "";
    public static Integer flgCollDefControl = 0;
    public static Double CollectionPer = 0.00;
    public static LinkedHashMap<String, Integer> hmapAppMasterFlags = new LinkedHashMap<String, Integer>();//Filled from All Button Activicty On onCreate Method
    public static int flgLangChangeReuired = 0;
    public static String VALIDATION_CODE = "validation_code";
    public static final String MTAS_ORDER_WEB_URL = "http://www.rajsuperwhite.in/pda/mTas.aspx?pdacode=";
    public static int maxAllowedPhotos=2;

    public static  String PURCHASE_ORDER_WEB_URL = "http://www.rajsuperwhite.in/frmLoginPDA.aspx?IMEI=";
    public static  String WHATSAPP_ENROLL_WEB_URL = "http://www.rajsuperwhite.in/PDA/frmWhatsppRegistration.aspx?sid=";
    //public static final String URLLinkToViewStoreOverWebProtal="http://103.20.212.67/LTACE_Android10/Mobile/fnProfileDataforBeat.aspx";
    public static final String URLLinkToViewStoreOverWebProtal="http://www.rajsuperwhite.in/Mobile/fnProfileDataforBeat_New.aspx";

    //public static final String WebUrlLinkforLastVisitDetails = "http://103.20.212.67/LTACE_Android10/Mobile/frmOutletProfile.aspx";
    public static final String WebUrlLinkforLastVisitDetails = "http://www.rajsuperwhite.in/Mobile/frmOutletProfile.aspx";

    // public static String WebPageUrlDataReport="http://103.20.212.67/RajTradersTars_Dev/Mobile/fnSalesmanWiseSummaryRpt.aspx";
    public static String WebPageUrlDataReport="http://www.rajsuperwhite.in/Mobile/frmDayTrackerReport_Mobile.aspx";

    //public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmRouteTracking.aspx";
    public static String WebPageUrl="http://www.rajsuperwhite.in/Mobile/frmSMTracker_Mobile.aspx";
    // public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmDayTrackerReport_Mobile.aspx";
    public static String WebAttendanceReport="http://www.rajsuperwhite.in/Mobile/frmTodayAttendence.aspx";
    public static String WebScndryUpdate="http://www.rajsuperwhite.in/Mobile/frmDailySecondaryUpdate.aspx";
    public static int PersonNodeID=0;
    public static int PersonNodeType=0;
    public static  LinkedHashMap<String,String> hmapStoreLocationDetails=new LinkedHashMap<String,String>();*/


    //Live 196 Server
    public static final String LastTrackPreference = "RajTradersLastTrackPrefrence";
    public static String ContactNumber = "";
    public static String ActiveRouteSM = "0";
    public static int AnyVisit = 0;
    public static String AppLatLngJsonFile = "RajTradersOrderSFALatLngJson";

    public static int Application_TypeID = 4;
    public static final String AttandancePreference = "RajTradersOrderAttandancePreference";
    public static int CoverageAreaNodeID = 0;
    public static int CoverageAreaNodeType = 0;
    public static String DATABASE_NAME = "AppDataSource";
    public static int DATABASE_VERSIONID = BuildConfig.VERSION_CODE;
    public static String AppVersionID = BuildConfig.VERSION_NAME;
    public static int flgDrctslsIndrctSls = 2;
    public static int DayStartClick = 0;
    public static int DistanceRange = 3000;
    public static final String DistributorCheckInXMLFolder = "RajTradersOrderDistributorCheckInXML";
    public static final String DistributorMapXMLFolder = "RajTradersOrderDistributorMapXML";
    public static final String DistributorStockXMLFolder = "RajTradersOrderDistributorStockXML";
    //public static String DistributorSyncPath = "http://103.20.212.194/ReadXML_RajTradersDistributionStagingelopment/Default.aspx";
    public static String FinalLatLngJsonFile = "RajTradersOrderSFAFinalLatLngJson";
    public static int FlgDSRSO = 0;
    //public static String ImageSyncPath = "http://103.20.212.194/ReadXML_RajTradersImagesStagingelopment/Default.aspx";
    public static String ImagesFolder = "RajTradersOrderSFAImagesStaging";
    public static String ImagesFolderServer = ".RajTradersOrderSFAImagesServer";
    //public static String InvoiceSyncPath = "http://103.20.212.194/ReadXML_RajTradersInvoiceStagingelopment/DefaultGT.aspx";
    public static String InvoiceXMLFolder = "RajTradersOrderInvoiceXmlStaging";
    //public static String OrderSyncPath = "http://103.20.212.194/ReadXML_RajTradersStagingelopment/DefaultGTSFA.aspx";
    public static String OrderSyncPathDistributorMap = "http://103.20.212.194/ReadXML_RajTradersStaging/DefaultSODistributorMappingGT.aspx";
    public static String OrderTextSyncPath = "http://103.20.212.194/ReadTxtFileForRajTradersStaging/default.aspx";
    public static String OrderXMLFolder = "RajTradersOrderSFAXml";
    public static final String Preference = "RajTradersOrderPrefrence";
    public static final String CycleOrDayEndPreference = "CycleOrDayEndPreference";
    public static String SalesPersonTodaysTargetMsg = "";
    public static String SalesQuoteId = "BLANK";
    public static int SalesmanNodeId = 0;
    public static int SalesmanNodeType = 0;
    public static String TextFileFolder = "RajTradersOrderTextFile";
    public static String VersionDownloadAPKName = "RajTradersASM.apk";
    public static String VersionDownloadPath = "http://www.rajsuperwhite.in/downloads/";
    public static String WebServicePath = "http://103.20.212.194/WebServiceAndroidAllanaDevelopment/Service.asmx";
    public static String WebStockInUrl = "http://103.20.212.194/Allanadev/manageorder/frmstockin.aspx";
    public static String WebStockOutUrl = "http://103.20.212.194/Allanadev/manageorder/frmStockTransferToVanDetail_PDA.aspx";
    public static String clickedTagPhoto_savedInstance = null;
    public static String fileContent = "";
    public static int flgAllRoutesData = 1;
    public static int flgDataScope = 0;
    public static String globalValueOfPaymentStage = "0_0_0";
    public static File imageF_savedInstance = null;
    public static String imageName_savedInstance = null;
    public static String token = "";
    public static int VanLoadedUnloaded = 0;
    public static String newQuottionID = "NULL";
    public static String prcID = "NULL";
    public static String quatationFlag = "";
    public static String sPrefVanLoadedUnloaded = "VanLoadedUnloaded";
    public static Uri uriSavedImage_savedInstance = null;
    public static String TextFileName = "RajTradersAllDetails";
    public static String TextFileArrayName = "AllDetails";


    public static final String COMMON_SYNC_PATH_URL = "http://103.107.67.196/RajSM_PDAFileReceivingApp_Live/Default.aspx?FileType=";

    public static String ClientFileNameOrderSync = "XML_Files";
    public static String ClientFileNameImageSyncPath = "IMAGE_ImageFiles";
    public static String ClientFileNameInvoiceSyncPath = "XML_InvoiceFile_GT";
    public static String ClientFileNameDistributorSyncPath = "XML_DistributionFile_GT";
    public static String ClientFileNameDistributorMapPath = "XML_DistributionMap_GT";

    public static final String Invoice_Database_Assistant_DB_NAME = "XMLInvoiceFile";
    public static final String Database_Assistant_Distributor_Entry_DB_NAME = "DistributorDataFile";
    public static final String Database_Assistant_DB_NAME = "DBRajTraders";

    public static final String BASE_URL = "http://www.rajsuperwhite.in/Raj_PDADataAPI_Live/";
    //    public static final String BASE_URL = "http://103.20.212.67/Allana_PDADataAPI_Test/";
    public static String RegistrationID = "NotGettingFromServer";
    public static String crntServerTimecrntAttndncTime = "";
    public static Integer flgCollDefControl = 0;
    public static Double CollectionPer = 0.00;
    public static LinkedHashMap<String, Integer> hmapAppMasterFlags = new LinkedHashMap<String, Integer>();//Filled from All Button Activicty On onCreate Method
    public static int flgLangChangeReuired = 0;
    public static String VALIDATION_CODE = "validation_code";
    public static final String MTAS_ORDER_WEB_URL = "http://www.rajsuperwhite.in/pda/mTas.aspx?pdacode=";
    public static int maxAllowedPhotos=2;

    public static  String PURCHASE_ORDER_WEB_URL = "http://www.rajsuperwhite.in/frmLoginPDA.aspx?IMEI=";
    public static  String WHATSAPP_ENROLL_WEB_URL = "http://www.rajsuperwhite.in/PDA/frmWhatsppRegistration.aspx?sid=";
    //public static final String URLLinkToViewStoreOverWebProtal="http://103.20.212.67/LTACE_Android10/Mobile/fnProfileDataforBeat.aspx";
    public static final String URLLinkToViewStoreOverWebProtal="http://www.rajsuperwhite.in/Mobile/fnProfileDataforBeat_New.aspx";

    //public static final String WebUrlLinkforLastVisitDetails = "http://103.20.212.67/LTACE_Android10/Mobile/frmOutletProfile.aspx";
    public static final String WebUrlLinkforLastVisitDetails = "http://www.rajsuperwhite.in/Mobile/frmOutletProfile.aspx";

    // public static String WebPageUrlDataReport="http://103.20.212.67/RajTradersTars_Dev/Mobile/fnSalesmanWiseSummaryRpt.aspx";
    public static String WebPageUrlDataReport="http://www.rajsuperwhite.in/Mobile/frmDayTrackerReport_Mobile.aspx";

    //public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmRouteTracking.aspx";
    public static String WebPageUrl="http://www.rajsuperwhite.in/Mobile/frmSMTracker_Mobile.aspx";
    // public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmDayTrackerReport_Mobile.aspx";
    public static String WebAttendanceReport="http://www.rajsuperwhite.in/Mobile/frmTodayAttendence.aspx";
    public static String WebScndryUpdate="http://www.rajsuperwhite.in/Mobile/frmDailySecondaryUpdate.aspx";
    public static int PersonNodeID=0;
    public static int PersonNodeType=0;
    public static  LinkedHashMap<String,String> hmapStoreLocationDetails=new LinkedHashMap<String,String>();


    //Dev
 /*   public static final String LastTrackPreference = "RajTradersLastTrackPrefrence";
    public static String ContactNumber = "";
    public static String ActiveRouteSM = "0";
    public static int AnyVisit = 0;
    public static String AppLatLngJsonFile = "RajTradersOrderSFALatLngJson";

    public static int Application_TypeID = 4;
    public static final String AttandancePreference = "RajTradersOrderAttandancePreference";
    public static int CoverageAreaNodeID = 0;
    public static int CoverageAreaNodeType = 0;
    public static String DATABASE_NAME = "AppDataSource";
    public static int DATABASE_VERSIONID = BuildConfig.VERSION_CODE;
    public static String AppVersionID = BuildConfig.VERSION_NAME;
    public static int flgDrctslsIndrctSls = 2;
    public static int DayStartClick = 0;
    public static int DistanceRange = 3000;
    public static final String DistributorCheckInXMLFolder = "RajTradersOrderDistributorCheckInXMLDev";
    public static final String DistributorMapXMLFolder = "RajTradersOrderDistributorMapXMLDev";
    public static final String DistributorStockXMLFolder = "RajTradersOrderDistributorStockXMLDev";
    //public static String DistributorSyncPath = "http://103.20.212.194/ReadXML_RajTradersDistributionDevelopment/Default.aspx";
    public static String FinalLatLngJsonFile = "RajTradersOrderSFAFinalLatLngJsonDev";
    public static int FlgDSRSO = 0;
    //public static String ImageSyncPath = "http://103.20.212.194/ReadXML_RajTradersImagesDevelopment/Default.aspx";
    public static String ImagesFolder = "RajTradersOrderSFAImagesDev";
    public static String ImagesFolderServer = ".RajTradersOrderSFAImagesServerDev";
    //public static String InvoiceSyncPath = "http://103.20.212.194/ReadXML_RajTradersInvoiceDevelopment/DefaultGT.aspx";
    public static String InvoiceXMLFolder = "RajTradersOrderInvoiceXmlDev";
    //public static String OrderSyncPath = "http://103.20.212.194/ReadXML_RajTradersDevelopment/DefaultGTSFA.aspx";
    public static String OrderSyncPathDistributorMap = "http://103.20.212.194/ReadXML_RajTradersDev/DefaultSODistributorMappingGT.aspx";
    public static String OrderTextSyncPath = "http://103.20.212.194/ReadTxtFileForRajTradersDev/default.aspx";
    public static String OrderXMLFolder = "RajTradersOrderSFAXmlDev";
    public static final String Preference = "RajTradersOrderPrefrence";
    public static final String CycleOrDayEndPreference = "CycleOrDayEndPreference";
    public static String SalesPersonTodaysTargetMsg = "";
    public static String SalesQuoteId = "BLANK";
    public static int SalesmanNodeId = 0;
    public static int SalesmanNodeType = 0;
    public static String TextFileFolder = "RajTradersOrderTextFile";
    public static String VersionDownloadAPKName = "RajTradersASM_Test.apk";
    //public static String VersionDownloadAPKName = "RajTradersOrderSales_Dev.apk";
    public static String VersionDownloadPath = "http://103.20.212.67/downloads/";
    public static String WebServicePath = "http://103.20.212.194/WebServiceAndroidAllanaDevelopment/Service.asmx";
    public static String WebStockInUrl = "http://103.20.212.194/Allanadev/manageorder/frmstockin.aspx";
    public static String WebStockOutUrl = "http://103.20.212.194/Allanadev/manageorder/frmStockTransferToVanDetail_PDA.aspx";
    public static String clickedTagPhoto_savedInstance = null;
    public static String fileContent = "";
    public static int flgAllRoutesData = 1;
    public static int flgDataScope = 0;
    public static String globalValueOfPaymentStage = "0_0_0";
    public static File imageF_savedInstance = null;
    public static String imageName_savedInstance = null;
    public static String token = "";
    public static int VanLoadedUnloaded = 0;
    public static String newQuottionID = "NULL";
    public static String prcID = "NULL";
    public static String quatationFlag = "";
    public static String sPrefVanLoadedUnloaded = "VanLoadedUnloaded";
    public static Uri uriSavedImage_savedInstance = null;
    public static String TextFileName = "RajTradersAllDetails";
    public static String TextFileArrayName = "AllDetails";


    public static final String COMMON_SYNC_PATH_URL = "http://103.20.212.67/RajSM_PDAFileReceivingApp_Dev/Default.aspx?FileType=";

    public static String ClientFileNameOrderSync = "XML_Files";
    public static String ClientFileNameImageSyncPath = "IMAGE_ImageFiles";
    public static String ClientFileNameInvoiceSyncPath = "XML_InvoiceFile_GT";
    public static String ClientFileNameDistributorSyncPath = "XML_DistributionFile_GT";
    public static String ClientFileNameDistributorMapPath = "XML_DistributionMap_GT";

    public static final String Invoice_Database_Assistant_DB_NAME = "XMLInvoiceFile";
    public static final String Database_Assistant_Distributor_Entry_DB_NAME = "DistributorDataFile";
    public static final String Database_Assistant_DB_NAME = "DBRajTraders";

    public static final String BASE_URL = "http://103.20.212.67/Raj_PDADataAPI_Dev/";
//    public static final String BASE_URL = "http://103.20.212.67/Allana_PDADataAPI_Test/";
    public static String RegistrationID = "NotGettingFromServer";
    public static String crntServerTimecrntAttndncTime = "";
    public static Integer flgCollDefControl = 0;
    public static Double CollectionPer = 0.00;
    public static LinkedHashMap<String, Integer> hmapAppMasterFlags = new LinkedHashMap<String, Integer>();//Filled from All Button Activicty On onCreate Method
    public static int flgLangChangeReuired = 0;
    public static String VALIDATION_CODE = "validation_code";
    public static final String MTAS_ORDER_WEB_URL = "http://103.20.212.67/RajTradersTars_Dev/pda/mTas.aspx?pdacode=";
    public static int maxAllowedPhotos=2;

    public static  String PURCHASE_ORDER_WEB_URL = "http://103.20.212.67/RajTradersTars_Dev/frmLoginPDA.aspx?IMEI=";
    public static  String WHATSAPP_ENROLL_WEB_URL = "http://103.20.212.67/RajTradersTars_Dev/PDA/frmWhatsppRegistration.aspx?sid=";
    //public static final String URLLinkToViewStoreOverWebProtal="http://103.20.212.67/LTACE_Android10/Mobile/fnProfileDataforBeat.aspx";
    public static final String URLLinkToViewStoreOverWebProtal="http://103.20.212.67/RajTradersTars_Dev/Mobile/fnProfileDataforBeat_New.aspx";

    //public static final String WebUrlLinkforLastVisitDetails = "http://103.20.212.67/LTACE_Android10/Mobile/frmOutletProfile.aspx";
    public static final String WebUrlLinkforLastVisitDetails = "http://103.20.212.67/RajTradersTars_Dev/Mobile/frmOutletProfile.aspx";

   // public static String WebPageUrlDataReport="http://103.20.212.67/RajTradersTars_Dev/Mobile/fnSalesmanWiseSummaryRpt.aspx";
   public static String WebPageUrlDataReport="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmDayTrackerReport_Mobile.aspx";

    //public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmRouteTracking.aspx";
    public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmSMTracker_Mobile.aspx";
  // public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmDayTrackerReport_Mobile.aspx";
    public static String WebAttendanceReport="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmTodayAttendence.aspx";
    public static String WebScndryUpdate="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmDailySecondaryUpdate.aspx";
    public static int PersonNodeID=0;
    public static int PersonNodeType=0;*/





    //Staging

   /* public static final String LastTrackPreference = "RajTradersLastTrackPrefrence";
    public static String ContactNumber = "";
    public static String ActiveRouteSM = "0";
    public static int AnyVisit = 0;
    public static String AppLatLngJsonFile = "RajTradersOrderSFALatLngJson";

    public static int Application_TypeID = 4;
    public static final String AttandancePreference = "RajTradersOrderAttandancePreference";
    public static int CoverageAreaNodeID = 0;
    public static int CoverageAreaNodeType = 0;
    public static String DATABASE_NAME = "AppDataSource";
    public static int DATABASE_VERSIONID = BuildConfig.VERSION_CODE;
    public static String AppVersionID = BuildConfig.VERSION_NAME;
    public static int flgDrctslsIndrctSls = 2;
    public static int DayStartClick = 0;
    public static int DistanceRange = 3000;
    public static final String DistributorCheckInXMLFolder = "RajTradersOrderDistributorCheckInXMLStaging";
    public static final String DistributorMapXMLFolder = "RajTradersOrderDistributorMapXMLStaging";
    public static final String DistributorStockXMLFolder = "RajTradersOrderDistributorStockXMLStaging";
    //public static String DistributorSyncPath = "http://103.20.212.194/ReadXML_RajTradersDistributionStagingelopment/Default.aspx";
    public static String FinalLatLngJsonFile = "RajTradersOrderSFAFinalLatLngJsonStaging";
    public static int FlgDSRSO = 0;
    //public static String ImageSyncPath = "http://103.20.212.194/ReadXML_RajTradersImagesStagingelopment/Default.aspx";
    public static String ImagesFolder = "RajTradersOrderSFAImagesStaging";
    public static String ImagesFolderServer = ".RajTradersOrderSFAImagesServerStaging";
    //public static String InvoiceSyncPath = "http://103.20.212.194/ReadXML_RajTradersInvoiceStagingelopment/DefaultGT.aspx";
    public static String InvoiceXMLFolder = "RajTradersOrderInvoiceXmlStaging";
    //public static String OrderSyncPath = "http://103.20.212.194/ReadXML_RajTradersStagingelopment/DefaultGTSFA.aspx";
    public static String OrderSyncPathDistributorMap = "http://103.20.212.194/ReadXML_RajTradersStaging/DefaultSODistributorMappingGT.aspx";
    public static String OrderTextSyncPath = "http://103.20.212.194/ReadTxtFileForRajTradersStaging/default.aspx";
    public static String OrderXMLFolder = "RajTradersOrderSFAXmlStaging";
    public static final String Preference = "RajTradersOrderPrefrence";
    public static final String CycleOrDayEndPreference = "CycleOrDayEndPreference";
    public static String SalesPersonTodaysTargetMsg = "";
    public static String SalesQuoteId = "BLANK";
    public static int SalesmanNodeId = 0;
    public static int SalesmanNodeType = 0;
    public static String TextFileFolder = "RajTradersOrderTextFile";
    public static String VersionDownloadAPKName = "RajTradersASM_Staging.apk";
    //public static String VersionDownloadAPKName = "RajTradersOrderSales_Dev.apk";
    public static String VersionDownloadPath = "http://103.20.212.67/downloads/";
    public static String WebServicePath = "http://103.20.212.194/WebServiceAndroidAllanaDevelopment/Service.asmx";
    public static String WebStockInUrl = "http://103.20.212.194/Allanadev/manageorder/frmstockin.aspx";
    public static String WebStockOutUrl = "http://103.20.212.194/Allanadev/manageorder/frmStockTransferToVanDetail_PDA.aspx";
    public static String clickedTagPhoto_savedInstance = null;
    public static String fileContent = "";
    public static int flgAllRoutesData = 1;
    public static int flgDataScope = 0;
    public static String globalValueOfPaymentStage = "0_0_0";
    public static File imageF_savedInstance = null;
    public static String imageName_savedInstance = null;
    public static String token = "";
    public static int VanLoadedUnloaded = 0;
    public static String newQuottionID = "NULL";
    public static String prcID = "NULL";
    public static String quatationFlag = "";
    public static String sPrefVanLoadedUnloaded = "VanLoadedUnloaded";
    public static Uri uriSavedImage_savedInstance = null;
    public static String TextFileName = "RajTradersAllDetails";
    public static String TextFileArrayName = "AllDetails";


    public static final String COMMON_SYNC_PATH_URL = "http://103.20.212.67/RajSM_PDAFileReceivingApp_Staging/Default.aspx?FileType=";

    public static String ClientFileNameOrderSync = "XML_Files";
    public static String ClientFileNameImageSyncPath = "IMAGE_ImageFiles";
    public static String ClientFileNameInvoiceSyncPath = "XML_InvoiceFile_GT";
    public static String ClientFileNameDistributorSyncPath = "XML_DistributionFile_GT";
    public static String ClientFileNameDistributorMapPath = "XML_DistributionMap_GT";

    public static final String Invoice_Database_Assistant_DB_NAME = "XMLInvoiceFile";
    public static final String Database_Assistant_Distributor_Entry_DB_NAME = "DistributorDataFile";
    public static final String Database_Assistant_DB_NAME = "DBRajTraders";

    public static final String BASE_URL = "http://103.20.212.67/Raj_PDADataAPI_Staging/";
    //    public static final String BASE_URL = "http://103.20.212.67/Allana_PDADataAPI_Test/";
    public static String RegistrationID = "NotGettingFromServer";
    public static String crntServerTimecrntAttndncTime = "";
    public static Integer flgCollDefControl = 0;
    public static Double CollectionPer = 0.00;
    public static LinkedHashMap<String, Integer> hmapAppMasterFlags = new LinkedHashMap<String, Integer>();//Filled from All Button Activicty On onCreate Method
    public static int flgLangChangeReuired = 0;
    public static String VALIDATION_CODE = "validation_code";
    public static final String MTAS_ORDER_WEB_URL = "http://103.20.212.67/RajTradersTars_Staging/pda/mTas.aspx?pdacode=";
    public static int maxAllowedPhotos=2;

    public static  String PURCHASE_ORDER_WEB_URL = "http://103.20.212.67/RajTradersTars_Staging/frmLoginPDA.aspx?IMEI=";
    public static  String WHATSAPP_ENROLL_WEB_URL = "http://103.20.212.67/RajTradersTars_Staging/PDA/frmWhatsppRegistration.aspx?sid=";
    //public static final String URLLinkToViewStoreOverWebProtal="http://103.20.212.67/LTACE_Android10/Mobile/fnProfileDataforBeat.aspx";
    public static final String URLLinkToViewStoreOverWebProtal="http://103.20.212.67/RajTradersTars_Staging/Mobile/fnProfileDataforBeat_New.aspx";

    //public static final String WebUrlLinkforLastVisitDetails = "http://103.20.212.67/LTACE_Android10/Mobile/frmOutletProfile.aspx";
    public static final String WebUrlLinkforLastVisitDetails = "http://103.20.212.67/RajTradersTars_Staging/Mobile/frmOutletProfile.aspx";

    // public static String WebPageUrlDataReport="http://103.20.212.67/RajTradersTars_Dev/Mobile/fnSalesmanWiseSummaryRpt.aspx";
    public static String WebPageUrlDataReport="http://103.20.212.67/RajTradersTars_Staging/Mobile/frmDayTrackerReport_Mobile.aspx";

    //public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmRouteTracking.aspx";
    public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Staging/Mobile/frmSMTracker_Mobile.aspx";
    // public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmDayTrackerReport_Mobile.aspx";
    public static String WebAttendanceReport="http://103.20.212.67/RajTradersTars_Staging/Mobile/frmTodayAttendence.aspx";
    public static String WebScndryUpdate="http://103.20.212.67/RajTradersTars_Staging/Mobile/frmDailySecondaryUpdate.aspx";
    public static int PersonNodeID=0;
    public static int PersonNodeType=0;
    public static  LinkedHashMap<String,String> hmapStoreLocationDetails=new LinkedHashMap<String,String>();*/
    //ASM DEMO DB
   /* public static final String LastTrackPreference = "RajTradersLastTrackPrefrence";
    public static String ContactNumber = "";
    public static String ActiveRouteSM = "0";
    public static int AnyVisit = 0;
    public static String AppLatLngJsonFile = "RajTradersOrderSFALatLngJson";

    public static int Application_TypeID = 4;
    public static final String AttandancePreference = "RajTradersOrderAttandancePreference";
    public static int CoverageAreaNodeID = 0;
    public static int CoverageAreaNodeType = 0;
    public static String DATABASE_NAME = "AppDataSource";
    public static int DATABASE_VERSIONID = BuildConfig.VERSION_CODE;
    public static String AppVersionID = BuildConfig.VERSION_NAME;
    public static int flgDrctslsIndrctSls = 2;
    public static int DayStartClick = 0;
    public static int DistanceRange = 3000;
    public static final String DistributorCheckInXMLFolder = "RajTradersOrderDistributorCheckInXMLStaging";
    public static final String DistributorMapXMLFolder = "RajTradersOrderDistributorMapXMLStaging";
    public static final String DistributorStockXMLFolder = "RajTradersOrderDistributorStockXMLStaging";
    //public static String DistributorSyncPath = "http://103.20.212.194/ReadXML_RajTradersDistributionStagingelopment/Default.aspx";
    public static String FinalLatLngJsonFile = "RajTradersOrderSFAFinalLatLngJsonStaging";
    public static int FlgDSRSO = 0;
    //public static String ImageSyncPath = "http://103.20.212.194/ReadXML_RajTradersImagesStagingelopment/Default.aspx";
    public static String ImagesFolder = "RajTradersOrderSFAImagesStaging";
    public static String ImagesFolderServer = ".RajTradersOrderSFAImagesServerStaging";
    //public static String InvoiceSyncPath = "http://103.20.212.194/ReadXML_RajTradersInvoiceStagingelopment/DefaultGT.aspx";
    public static String InvoiceXMLFolder = "RajTradersOrderInvoiceXmlStaging";
    //public static String OrderSyncPath = "http://103.20.212.194/ReadXML_RajTradersStagingelopment/DefaultGTSFA.aspx";
    public static String OrderSyncPathDistributorMap = "http://103.20.212.194/ReadXML_RajTradersStaging/DefaultSODistributorMappingGT.aspx";
    public static String OrderTextSyncPath = "http://103.20.212.194/ReadTxtFileForRajTradersStaging/default.aspx";
    public static String OrderXMLFolder = "RajTradersOrderSFAXmlStaging";
    public static final String Preference = "RajTradersOrderPrefrence";
    public static final String CycleOrDayEndPreference = "CycleOrDayEndPreference";
    public static String SalesPersonTodaysTargetMsg = "";
    public static String SalesQuoteId = "BLANK";
    public static int SalesmanNodeId = 0;
    public static int SalesmanNodeType = 0;
    public static String TextFileFolder = "RajTradersOrderTextFile";
    public static String VersionDownloadAPKName = "RajTradersASM_Demo.apk";
    //public static String VersionDownloadAPKName = "RajTradersOrderSales_Dev.apk";
    public static String VersionDownloadPath = "http://103.20.212.67/downloads/";
    public static String WebServicePath = "http://103.20.212.194/WebServiceAndroidAllanaDevelopment/Service.asmx";
    public static String WebStockInUrl = "http://103.20.212.194/Allanadev/manageorder/frmstockin.aspx";
    public static String WebStockOutUrl = "http://103.20.212.194/Allanadev/manageorder/frmStockTransferToVanDetail_PDA.aspx";
    public static String clickedTagPhoto_savedInstance = null;
    public static String fileContent = "";
    public static int flgAllRoutesData = 1;
    public static int flgDataScope = 0;
    public static String globalValueOfPaymentStage = "0_0_0";
    public static File imageF_savedInstance = null;
    public static String imageName_savedInstance = null;
    public static String token = "";
    public static int VanLoadedUnloaded = 0;
    public static String newQuottionID = "NULL";
    public static String prcID = "NULL";
    public static String quatationFlag = "";
    public static String sPrefVanLoadedUnloaded = "VanLoadedUnloaded";
    public static Uri uriSavedImage_savedInstance = null;
    public static String TextFileName = "RajTradersAllDetails";
    public static String TextFileArrayName = "AllDetails";


    public static final String COMMON_SYNC_PATH_URL = "http://103.20.212.67/RajSM_PDAFileReceivingApp_ASMDemo/Default.aspx?FileType=";

    public static String ClientFileNameOrderSync = "XML_Files";
    public static String ClientFileNameImageSyncPath = "IMAGE_ImageFiles";
    public static String ClientFileNameInvoiceSyncPath = "XML_InvoiceFile_GT";
    public static String ClientFileNameDistributorSyncPath = "XML_DistributionFile_GT";
    public static String ClientFileNameDistributorMapPath = "XML_DistributionMap_GT";

    public static final String Invoice_Database_Assistant_DB_NAME = "XMLInvoiceFile";
    public static final String Database_Assistant_Distributor_Entry_DB_NAME = "DistributorDataFile";
    public static final String Database_Assistant_DB_NAME = "DBRajTraders";

    public static final String BASE_URL = "http://103.20.212.67/Raj_PDADataAPI_ASMDemo/";
    //    public static final String BASE_URL = "http://103.20.212.67/Allana_PDADataAPI_Test/";
    public static String RegistrationID = "NotGettingFromServer";
    public static String crntServerTimecrntAttndncTime = "";
    public static Integer flgCollDefControl = 0;
    public static Double CollectionPer = 0.00;
    public static LinkedHashMap<String, Integer> hmapAppMasterFlags = new LinkedHashMap<String, Integer>();//Filled from All Button Activicty On onCreate Method
    public static int flgLangChangeReuired = 0;
    public static String VALIDATION_CODE = "validation_code";
    public static final String MTAS_ORDER_WEB_URL = "http://103.20.212.67/Raj_PDADataAPI_ASMDemo/pda/mTas.aspx?pdacode=";
    public static int maxAllowedPhotos=2;

    public static  String PURCHASE_ORDER_WEB_URL = "http://103.20.212.67/RajTradersTars_Staging/frmLoginPDA.aspx?IMEI=";
    public static  String WHATSAPP_ENROLL_WEB_URL = "http://103.20.212.67/RajTradersTars_Staging/PDA/frmWhatsppRegistration.aspx?sid=";
    //public static final String URLLinkToViewStoreOverWebProtal="http://103.20.212.67/LTACE_Android10/Mobile/fnProfileDataforBeat.aspx";
    public static final String URLLinkToViewStoreOverWebProtal="http://103.20.212.67/RajTradersTars_Staging/Mobile/fnProfileDataforBeat_New.aspx";

    //public static final String WebUrlLinkforLastVisitDetails = "http://103.20.212.67/LTACE_Android10/Mobile/frmOutletProfile.aspx";
    public static final String WebUrlLinkforLastVisitDetails = "http://103.20.212.67/RajTradersTars_Staging/Mobile/frmOutletProfile.aspx";

    // public static String WebPageUrlDataReport="http://103.20.212.67/RajTradersTars_Dev/Mobile/fnSalesmanWiseSummaryRpt.aspx";
    public static String WebPageUrlDataReport="http://103.20.212.67/RajTradersTars_Staging/Mobile/frmDayTrackerReport_Mobile.aspx";

    //public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmRouteTracking.aspx";
    public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Staging/Mobile/frmSMTracker_Mobile.aspx";
    // public static String WebPageUrl="http://103.20.212.67/RajTradersTars_Dev/Mobile/frmDayTrackerReport_Mobile.aspx";
    public static String WebAttendanceReport="http://103.20.212.67/RajTradersTars_Staging/Mobile/frmTodayAttendence.aspx";
    public static String WebScndryUpdate="http://103.20.212.67/RajTradersTars_Staging/Mobile/frmDailySecondaryUpdate.aspx";
    public static int PersonNodeID=0;
    public static int PersonNodeType=0;
    public static  LinkedHashMap<String,String> hmapStoreLocationDetails=new LinkedHashMap<String,String>();*/

//Contains 0=NoApplicale and 1=Applicable



					/*hmapAppMasterFlags.put("flgDistributorCheckIn", cursor.getInt(0));
                    hmapAppMasterFlags.put("flgDBRStockInApp", cursor.getInt(1));
                    hmapAppMasterFlags.put("flgDBRStockEdit", cursor.getInt(2));
                    hmapAppMasterFlags.put("flgDBRStockCalculate", cursor.getInt(3));
                    hmapAppMasterFlags.put("flgDBRStockControl", cursor.getInt(4));
                    hmapAppMasterFlags.put("flgCollRequired", cursor.getInt(5));   //0=Not To be mapped Again,1=Can Map Distributor
                    hmapAppMasterFlags.put("flgCollReqOrdr", cursor.getInt(6));
                    hmapAppMasterFlags.put("flgCollTab", cursor.getInt(7));
                    hmapAppMasterFlags.put("flgCollDefControl", cursor.getInt(8));
                    hmapAppMasterFlags.put("flgCollControlRule", cursor.getInt(9));
                    hmapAppMasterFlags.put("flgSchemeAvailable", cursor.getInt(10));
                    hmapAppMasterFlags.put("flgSchemeAllowEntry", cursor.getInt(11));
                    hmapAppMasterFlags.put("flgSchemeAllowEdit", cursor.getInt(12));
                    hmapAppMasterFlags.put("flgQuotationIsAvailable", cursor.getInt(13));
                    hmapAppMasterFlags.put("flgExecutionIsAvailable", cursor.getInt(14));
                    hmapAppMasterFlags.put("flgExecutionPhotoCompulsory", cursor.getInt(15));
                    hmapAppMasterFlags.put("flgTargetShowatStart", cursor.getInt(16));
                    hmapAppMasterFlags.put("flgIncentiveShowtStart", cursor.getInt(17));
                    hmapAppMasterFlags.put("flgInvoicePrint", cursor.getInt(18));
                    hmapAppMasterFlags.put("flgShowPOSM", cursor.getInt(19));
                    hmapAppMasterFlags.put("flgVisitStartOutstandingDetails", cursor.getInt(20));
                    hmapAppMasterFlags.put("flgVisitStartSchemeDetails", cursor.getInt(21));
                    hmapAppMasterFlags.put("flgStoreDetailsEdit", cursor.getInt(22));
                    hmapAppMasterFlags.put("flgShowDeliveryAddressButtonOnOrder", cursor.getInt(23));
                    hmapAppMasterFlags.put("flgShowManagerOnStoreList", cursor.getInt(24));
                    hmapAppMasterFlags.put("flgRptTargetVsAchived", cursor.getInt(25));
                    hmapAppMasterFlags.put("flgVanSockManage", cursor.getInt(26));
                    hmapAppMasterFlags.put("flgVanSockManage", cursor.getInt(27));*/
}