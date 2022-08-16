package com.astix.rajtraderssfasmasales;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.allana.truetime.TimeUtils;
import com.astix.Common.CommonFunction;
import com.astix.Common.CommonInfo;
import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.model.JointVisitDetail;
import com.astix.rajtraderssfasmasales.model.JointVisitMemberDetail;
import com.astix.rajtraderssfasmasales.model.TblRouteListMaster;
import com.astix.rajtraderssfasmasales.model.TblSubordinateList;
import com.astix.rajtraderssfasmasales.sync.DatabaseAssistant;
import com.astix.rajtraderssfasmasales.utils.AppUtils;
import com.astix.rajtraderssfasmasales.utils.PreferenceManager;
import com.google.android.gms.common.internal.service.Common;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class JointVisitActivity extends BaseActivity implements InterfaceRetrofit, JSReceiver.RouteUpdateListener {

    DatabaseAssistant DASFA;
    String OnBehalfOfPersonName="";
    private Dialog dialog;
    private MyTimerTask myTimerTask;
    public String fDate;
    public String userDate;
    public String passDate;
    public SimpleDateFormat sdf;
    public String pickerDate;
    public String rID;
    SharedPreferences sharedPref;
    static ProgressDialog progressDialog;

    @BindView(R.id.sp_SO)
    Spinner spSO;



    @BindView(R.id.img_side_back)
    ImageView img_side_back;

    @BindView(R.id.sp_dsm)
    Spinner spDSM;
    @BindView(R.id.sp_coverageArea)
    Spinner spCoverageArea;
    @BindView(R.id.btnNext)
    Button btnNext;

    @BindView(R.id.tvWorkingWith)
    TextView tvWorkingWith;

    @BindView(R.id.btRefresh)
    Button btRefresh;


    private AppDataSource mDataSource;
    private Activity mActitiy;

    String[] So_Names = null;
    String[] DSM_Names = null;
    String[] Route_Names = null;

    private int selected_SO = -1;
    private int selected_DSM = -1;
    private int selected_CoverageArea = -1;

    /****** Logic For NodeTypes
     // ASM PersonNodeType = 210
     // SO PersonNodeType=220
     // DSM/DBR PersonNodeType=230/240
     *****/
    private ArrayList<TblSubordinateList> tblSubordinateLists;
    private ArrayList<TblSubordinateList> mSOArrayList;
    private ArrayList<TblSubordinateList> mDSMArrayList;
    private ArrayList<TblRouteListMaster> mRouteListArrayList;
    private ArrayList<TblRouteListMaster> mSelectedRouteArrayList;

    private TblSubordinateList mSelectedSo = null;
    private TblSubordinateList mSelectedDSM = null;
    private TblRouteListMaster mSelectedRoute = null;

    private GPS mGps;
    private View view;

    private int requestData = -1;  // 0- SO/DSM list, 1- RouteList


    public String AllProvidersLocation = "";
    public String FusedLocationLatitudeWithFirstAttempt = "0";
    public String FusedLocationLongitudeWithFirstAttempt = "0";
    public String FusedLocationAccuracyWithFirstAttempt = "0";
    public String FusedLocationLatitude = "0";
    public String FusedLocationLongitude = "0";
    public String FusedLocationProvider = "";
    public String FusedLocationAccuracy = "0";

    public String GPSLocationLatitude = "0";
    public String GPSLocationLongitude = "0";
    public String GPSLocationProvider = "";
    public String GPSLocationAccuracy = "0";

    public String NetworkLocationLatitude = "0";
    public String NetworkLocationLongitude = "0";
    public String NetworkLocationProvider = "";
    public String NetworkLocationAccuracy = "0";
    boolean isGPSEnabled = false;
    public String fnAccurateProvider = "";
    public String fnLati = "0";
    public String fnLongi = "0";
    public Double fnAccuracy = 0.0;
    public Location location;
    private PreferenceManager mPreferenceManager;

    private int CoverageAreaNodeID = 0;
    private int CoverageAreaNodeType = 0;
    private int VisitType=0;
    int FLGJOINTVISIT=0;
    TextView txtview_selectstoretext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_joint_visit);
       // view = inflater.inflate(R.layout.fragment_joint_visit, container, false);
        ButterKnife.bind(this);
        mActitiy = this;
        sharedPref = getSharedPreferences(CommonInfo.Preference, MODE_PRIVATE);
        Date date1 = new Date();
        sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        passDate = sdf.format(date1);
        Intent passedvals = getIntent();
        VisitType = passedvals.getIntExtra(AppUtils.VisitType,0);
        FLGJOINTVISIT=passedvals.getIntExtra(AppUtils.FLGJOINTVISIT,0);
        DASFA= new DatabaseAssistant(this);
        //System.out.println("Selctd Date: "+ passDate);

        fDate = passDate.trim();
        pickerDate = getDateInMonthTextFormat();
        userDate = getDateInMonthTextFormat();

        //new AppUtils().callParentMethod(mActitiy);
        mDataSource = AppDataSource.getInstance(mActitiy);
        mPreferenceManager = PreferenceManager.getInstance(mActitiy);
        mGps = GPS.getInstance(mActitiy);
        tblSubordinateLists = new ArrayList<>();
        mSOArrayList = new ArrayList<>();
        mDSMArrayList = new ArrayList<>();
        mRouteListArrayList = new ArrayList<>();
        mSelectedRouteArrayList = new ArrayList<>();
        rID = mDataSource.GetActiveRouteID(CommonInfo.CoverageAreaNodeID, CommonInfo.CoverageAreaNodeType);
        if (rID.equals("0")) {
            rID = mDataSource.GetNotActiveRouteID();
        }

        img_side_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(JointVisitActivity.this, AllButtonActivity.class);
                startActivity(i);
                finish();
            }
        });

        txtview_selectstoretext=findViewById(R.id.txtview_selectstoretext);


        if(VisitType==1)
        {

        }
        if(VisitType==2)
        {
            tvWorkingWith.setText("Working on Behalf Of:");
            txtview_selectstoretext.setText("Market Visit");
          //  btRefresh.setVisibility(View.VISIBLE);
        }
        if(VisitType==3)
        {
            tvWorkingWith.setText("Working With:");
            txtview_selectstoretext.setText("Joint Visit");
            btRefresh.setVisibility(View.GONE);
        }
        init();
    }

    private void init() {
      /*  GetData getData = new GetData();
        AppUtils.executeAsyncTask(getData);*/
        tblSubordinateLists = mDataSource.getSubOrdinateList();
        mRouteListArrayList = mDataSource.getRoutes();
        if(mSOArrayList!=null && mSOArrayList.size()>0)
            mSOArrayList.clear();

        if(mDSMArrayList!=null && mDSMArrayList.size()>0)
            mDSMArrayList.clear();

        // ASM PersonNodeType = 210
        // SO PersonNodeType=220
        // DSM/DBR PersonNodeType=230/240

        for (TblSubordinateList tblSubordinateList : tblSubordinateLists) {
           /* if (tblSubordinateList.getPersonNodeType() == 220 || tblSubordinateList.getPersonNodeType() == 222) {
                mSOArrayList.add(tblSubordinateList);
            } else if (tblSubordinateList.getPersonNodeType() == 240 || tblSubordinateList.getPersonNodeType() == 230) {
                mDSMArrayList.add(tblSubordinateList);
            }*/
           /* if (tblSubordinateList.getPersonNodeType() == 220 || tblSubordinateList.getPersonNodeType() == 222) {
                mSOArrayList.add(tblSubordinateList);
            } else*/ if (tblSubordinateList.getPersonNodeType() == 220 || tblSubordinateList.getPersonNodeType() == 240 || tblSubordinateList.getPersonNodeType() == 230) {
                mDSMArrayList.add(tblSubordinateList);
            }
        }

        if (mSOArrayList != null && mSOArrayList.size() > 0) {
            So_Names = new String[mSOArrayList.size() + 1];
            So_Names[0] = "Select SO";
            for (int i = 0; i < mSOArrayList.size(); i++) {
                So_Names[i + 1] = mSOArrayList.get(i).getPersonName() + "(" + mSOArrayList.get(i).getCoverageArea() + ")";
            }
        } else {
            So_Names = new String[1];
            So_Names[0] = "Select SO";
        }

        if (mDSMArrayList != null && mDSMArrayList.size() > 0) {
            DSM_Names = new String[mDSMArrayList.size() + 1];
            DSM_Names[0] = "Select TSI";
            for (int i = 0; i < mDSMArrayList.size(); i++) {
                DSM_Names[i + 1] = mDSMArrayList.get(i).getPersonName() + "(" + mDSMArrayList.get(i).getCoverageArea() + ")";
            }
        } else {
            DSM_Names = new String[1];
            DSM_Names[0] = "Select TSI";
        }




        createSOSpinnerList();
        createDSMSpinnerList();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedPersonId=0;

                if(VisitType==2)
                {
                   /* if (selected_SO ==-1 &&  selected_DSM ==-1)
                    {
                        showAlertSingleButtonInfo("Please select SO or DSM first.");
                    }
                    else*/ if (selected_DSM ==-1)
                    {
                        showAlertSingleButtonInfo("Please select TSI to proceed.");
                    }
                    else {
                        if (selected_SO > 0 && selected_DSM <= 0) {
                            mSelectedSo = mSOArrayList.get(selected_SO);

                            CoverageAreaNodeID = mSelectedSo.getCoverageAreaNodeID();
                            CoverageAreaNodeType = mSelectedSo.getCoverageAreaNodeType();
                        }

                      /*  if ((selected_DSM > 0 && selected_SO <= 0) || (selected_DSM > 0 && selected_SO >0)) {
                            mSelectedDSM = mDSMArrayList.get(selected_DSM);
                            CoverageAreaNodeID = mSelectedDSM.getCoverageAreaNodeID();
                            CoverageAreaNodeType =mSelectedDSM.getCoverageAreaNodeType();
                        }

                        for (TblRouteListMaster tblRouteListMaster : mRouteListArrayList) {
                            if (tblRouteListMaster.getCoverageAreaNodeID() == CoverageAreaNodeID) {

                                mSelectedRoute=tblRouteListMaster;
                                break;
                            }
                        }*/

                    if ((selected_DSM > -1)) {
                        mSelectedDSM = mDSMArrayList.get(selected_DSM);
                        CoverageAreaNodeID = mSelectedDSM.getCoverageAreaNodeID();
                        CoverageAreaNodeType =mSelectedDSM.getCoverageAreaNodeType();
                    }
                    int flgCheckRoutesCount=0;

                    for (TblRouteListMaster tblRouteListMaster : mRouteListArrayList) {
                        if (tblRouteListMaster.getCoverageAreaNodeID() == CoverageAreaNodeID) {

                            flgCheckRoutesCount=1;
                            mSelectedRoute=tblRouteListMaster;
                            break;
                        }
                    }


                       /* if(VisitType==3)
                        {
                            navigateToStoreList();
                        }
                        else {*/
                    if(flgCheckRoutesCount==1) {
                       /* if (mDataSource.isStoreListAlreadyExist(CoverageAreaNodeID, CoverageAreaNodeType)) {
                            navigateToStoreList();
                        } else {*/
                            shardPrefForCoverageArea(CoverageAreaNodeID, CoverageAreaNodeType);


                            if (isOnline()) {

                                if (timer != null) {
                                    timer.cancel();
                                    timer = null;
                                }
                                timer = new Timer();
                                myTimerTask = new MyTimerTask();

                                timer.schedule(myTimerTask, 30000);


                                try {
                                    progressDialog = new ProgressDialog(JointVisitActivity.this);
                                    progressDialog.setMessage("Please Wait...");
                                    progressDialog.setCancelable(false);


                                    OpenPopUpDialog();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } else {

                                requestData = 1;
                                if (!mDataSource.isStoreListAlreadyExist(mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType())) {
                                    if (isOnline()) {
                                        CommonFunction.getAllMasterTableModelData(JointVisitActivity.this, AppUtils.getToken(JointVisitActivity.this), "", "Please wait loading Data!", 5);

                                    } else {
                                        showNoConnAlert();
                                    }
                                } else
                                    navigateToStoreList();
                            }

                            //ApiCall.callAllDataAsm(JointVisitActivity.this, AppUtils.getToken(JointVisitActivity.this), "", CoverageAreaNodeID, CoverageAreaNodeType, 1);
                        //}
                    }
                    else
                    {
                        showAlertSingleButtonInfo("There is no routes available.");
                    }
                        //}


       /* if (selected_CoverageArea >= 0) {
            mSelectedRoute = mSelectedRouteArrayList.get(selected_CoverageArea);
        } else {
            AppUtils.showAlertDialog(mActitiy, "Please select route first.");
            return;
        }*/

           /* if (isOnline()) {

                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
                timer = new Timer();
                myTimerTask = new MyTimerTask();

                timer.schedule(myTimerTask, 30000);


                try {
                    progressDialog = new ProgressDialog(JointVisitActivity.this);
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.setCancelable(false);


                    OpenPopUpDialog();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                showNoConnAlert();
                requestData = 1;
                if (!mDataSource.isStoreListAlreadyExist(mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType())) {
                    // CommonFunction.getStoreListData(mActitiy, this, CommonInfo.RegistrationID, "Fetching StoreList", mSelectedRoute, false);
                    //   CommonFunction.getAllMasterTableModelData(JointVisitActivity.this,  AppUtils.getToken(JointVisitActivity.this), RegistrationID, "Please wait loading data.", 0, mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType());

                } else
                    navigateToStoreList();
            }*/

                    }
                }

                if(VisitType==3)
                {
                    if (selected_SO ==-1 &&  selected_DSM ==-1)
                    {
                        showAlertSingleButtonInfo("Please select SO or TSI first.");
                    }
                    else {
                        if (selected_SO > 0 && selected_DSM <= 0) {
                            mSelectedSo = mSOArrayList.get(selected_SO);

                            CoverageAreaNodeID = mSelectedSo.getCoverageAreaNodeID();
                            CoverageAreaNodeType = mSelectedSo.getCoverageAreaNodeType();
                        }

                       /* if ((selected_DSM > -1 && selected_SO <= 0) || (selected_DSM > 0 && selected_SO >0)) {
                            mSelectedDSM = mDSMArrayList.get(selected_DSM);
                            CoverageAreaNodeID = mSelectedDSM.getCoverageAreaNodeID();
                            CoverageAreaNodeType =mSelectedDSM.getCoverageAreaNodeType();
                        }*/

                        if ((selected_DSM > -1)) {
                            mSelectedDSM = mDSMArrayList.get(selected_DSM);
                            CoverageAreaNodeID = mSelectedDSM.getCoverageAreaNodeID();
                            CoverageAreaNodeType =mSelectedDSM.getCoverageAreaNodeType();
                        }
                        int flgCheckRoutesCount=0;
                        for (TblRouteListMaster tblRouteListMaster : mRouteListArrayList) {
                            if (tblRouteListMaster.getCoverageAreaNodeID() == CoverageAreaNodeID) {
                                flgCheckRoutesCount=1;
                                mSelectedRoute=tblRouteListMaster;
                                break;
                            }
                        }


                           // navigateToStoreList();

                        if(flgCheckRoutesCount==1) {
                            if (mDataSource.isStoreListAlreadyExist(CoverageAreaNodeID, CoverageAreaNodeType)) {
                                navigateToStoreList();
                            }
                            else {
                                shardPrefForCoverageArea(CoverageAreaNodeID,CoverageAreaNodeType);


                                if (isOnline()) {

                                    if (timer != null) {
                                        timer.cancel();
                                        timer = null;
                                    }
                                    timer = new Timer();
                                    myTimerTask = new MyTimerTask();

                                    timer.schedule(myTimerTask, 30000);


                                    try {
                                        progressDialog = new ProgressDialog(JointVisitActivity.this);
                                        progressDialog.setMessage("Please Wait...");
                                        progressDialog.setCancelable(false);


                                        OpenPopUpDialog();


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                } else {

                                    requestData = 1;
                                    if (!mDataSource.isStoreListAlreadyExist(mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType())) {
                                        if (isOnline()) {
                                            CommonFunction.getAllMasterTableModelData(JointVisitActivity.this, AppUtils.getToken(JointVisitActivity.this), "", "Please wait loading Data!", 5);

                                        }
                                        else
                                        {
                                            showNoConnAlert();
                                        }
                                    }
                                    else
                                        navigateToStoreList();
                                }

                                //ApiCall.callAllDataAsm(JointVisitActivity.this, AppUtils.getToken(JointVisitActivity.this), "", CoverageAreaNodeID, CoverageAreaNodeType, 1);
                            }
                        }
                        else
                        {
                            showAlertSingleButtonInfo("There is no routes available.");
                        }




       /* if (selected_CoverageArea >= 0) {
            mSelectedRoute = mSelectedRouteArrayList.get(selected_CoverageArea);
        } else {
            AppUtils.showAlertDialog(mActitiy, "Please select route first.");
            return;
        }*/

           /* if (isOnline()) {

                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
                timer = new Timer();
                myTimerTask = new MyTimerTask();

                timer.schedule(myTimerTask, 30000);


                try {
                    progressDialog = new ProgressDialog(JointVisitActivity.this);
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.setCancelable(false);


                    OpenPopUpDialog();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                showNoConnAlert();
                requestData = 1;
                if (!mDataSource.isStoreListAlreadyExist(mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType())) {
                    // CommonFunction.getStoreListData(mActitiy, this, CommonInfo.RegistrationID, "Fetching StoreList", mSelectedRoute, false);
                    //   CommonFunction.getAllMasterTableModelData(JointVisitActivity.this,  AppUtils.getToken(JointVisitActivity.this), RegistrationID, "Please wait loading data.", 0, mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType());

                } else
                    navigateToStoreList();
            }*/

                    }
                }
            }
        });
    }

    @Override
    public void success() {
        if (mDataSource.isStoreListAlreadyExist(CoverageAreaNodeID, CoverageAreaNodeType)) {
            navigateToStoreList();
        } else {
            dialog.dismiss();
            AppUtils.showAlertDialog(mActitiy, "There is no store list available for the selected Coverage Area.");
        }
       /* switch (requestData) {
            case 0:
                init();
                break;
            case 1:
                if (mDataSource.isStoreListAlreadyExist(CoverageAreaNodeID, CoverageAreaNodeType)) {
                    navigateToStoreList();
                } else {
                    AppUtils.showAlertDialog(mActitiy, "There is no store list available for the selected Coverage Area.");
                }
                break;
        }*/

    }

    @Override
    public void failure() {
        requestData = -1;
        AppUtils.showAlertDialog(mActitiy, "Couldn't load data this time. Please try again later!");
    }

    @Override
    public void onUpdateRoute(String routeId, String routeType) {
        /*for (int i = 0; i < mSelectedRouteArrayList.size(); i++) {
            TblRouteListMaster tblRouteListMaster = mSelectedRouteArrayList.get(i);
            if (tblRouteListMaster.getRouteNodeId() == Integer.valueOf(routeId)) {
                int finalI = i;
                mSelectedRoute = tblRouteListMaster;

                mActitiy.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        spCoverageArea.setSelection(finalI + 1);
                    }
                });
                break;
            }
        }*/
    }


    public class GetData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            tblSubordinateLists = mDataSource.getSubOrdinateList();
            mRouteListArrayList = mDataSource.getRoutes();
            if(mSOArrayList!=null && mSOArrayList.size()>0)
                mSOArrayList.clear();

            if(mDSMArrayList!=null && mDSMArrayList.size()>0)
                mDSMArrayList.clear();

            // ASM PersonNodeType = 210
            // SO PersonNodeType=220
            // DSM/DBR PersonNodeType=230/240

            for (TblSubordinateList tblSubordinateList : tblSubordinateLists) {
               /* if (tblSubordinateList.getPersonNodeType() == 220 || tblSubordinateList.getPersonNodeType() == 222) {
                    mSOArrayList.add(tblSubordinateList);
                } else if (tblSubordinateList.getPersonNodeType() == 240 || tblSubordinateList.getPersonNodeType() == 230) {
                    mDSMArrayList.add(tblSubordinateList);
                }*/
               if (tblSubordinateList.getPersonNodeType() == 220 || tblSubordinateList.getPersonNodeType() == 230) {
                    mDSMArrayList.add(tblSubordinateList);
                }
            }

            if (mSOArrayList != null && mSOArrayList.size() > 0) {
                So_Names = new String[mSOArrayList.size() + 1];
                So_Names[0] = "Select SO";
                for (int i = 0; i < mSOArrayList.size(); i++) {
                    So_Names[i + 1] = mSOArrayList.get(i).getPersonName() + "(" + mSOArrayList.get(i).getCoverageArea() + ")";
                }
            } else {
                So_Names = new String[1];
                So_Names[0] = "Select SO";
            }

            if (mDSMArrayList != null && mDSMArrayList.size() > 0) {
                DSM_Names = new String[mDSMArrayList.size() + 1];
                DSM_Names[0] = "Select TSI";
                for (int i = 0; i < mDSMArrayList.size(); i++) {
                    DSM_Names[i + 1] = mDSMArrayList.get(i).getPersonName() + "(" + mDSMArrayList.get(i).getCoverageArea() + ")";
                }
            } else {
                DSM_Names = new String[1];
                DSM_Names[0] = "Select TSI";
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            createSOSpinnerList();
            createDSMSpinnerList();

        }
    }
    private void fnBindSpinnerList(){
        if (mDSMArrayList != null && mDSMArrayList.size() > 0) {
            DSM_Names = new String[mDSMArrayList.size() + 1];
            DSM_Names[0] = "Select TSI";
            for (int i = 0; i < mDSMArrayList.size(); i++) {
                if(selected_SO==-1)
                DSM_Names[i + 1] = mDSMArrayList.get(i).getPersonName() + "(" + mDSMArrayList.get(i).getCoverageArea() + ")";
                else
                {
                  TblSubordinateList tblSubordinateListSORecord = mSOArrayList.get(selected_SO);
                    if(mDSMArrayList.get(i).getParentPersonID()==tblSubordinateListSORecord.getPersonNodeID())
                        DSM_Names[i + 1] = mDSMArrayList.get(i).getPersonName() + "(" + mDSMArrayList.get(i).getCoverageArea() + ")";
                }
            }
        } else {
            DSM_Names = new String[1];
            DSM_Names[0] = "Select TSI";
        }

       // ArrayAdapter adapterCategory = new ArrayAdapter(this, android.R.layout.spinner_item_route_store_selection, DSM_Names);
        ArrayAdapter adapterCategory = new ArrayAdapter(this, R.layout.spinner_item_route_store_selection_jointvisit, DSM_Names);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDSM.setAdapter(adapterCategory);
    }

    private void createSOSpinnerList() {

        ArrayAdapter adapterCategory = new ArrayAdapter(mActitiy, android.R.layout.simple_spinner_item, So_Names);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSO.setAdapter(adapterCategory);
        spSO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selected_SO=-1;

                if (arg2 <= 0)
                {
                    prepareRoutes(-1, true);
                }
                else
                {
                    TblSubordinateList selectedSO = mSOArrayList.get(arg2 - 1);
                    selected_SO = arg2-1 ;
                    int selectedSoPersonId =selectedSO.getPersonNodeID();
                    prepareRoutes(selectedSoPersonId, false);
                  /*
                    CoverageAreaNodeID = selectedSO.getCoverageAreaNodeID();
                    CoverageAreaNodeType = selectedSO.getCoverageAreaNodeType();
                    if (selected_SO != -1) {
                        // btnNext.setEnabled(true);
                        *//*if (selected_DSM == -1) {*//*
                            prepareRoutes(selectedSoPersonId, true);
                        //}
                    }*/

                }
                createDSMSpinnerList();
                /*if (arg2 <= 0) {
                    selected_SO = -1;
                    if (selected_DSM < 0)
                       // btnNext.setEnabled(false);
                    prepareRoutes(-1, true);
                    return;
                }
                else {
                    TblSubordinateList selectedSO = mSOArrayList.get(arg2 - 1);

//                if(selectedSO.getFlgMarketVisitStarted()==0){
//                    AppUtils.showAlertDialog(mActitiy,selectedSO.getPersonName()+" has not yet started the Day. Please ask him to start on the app. If done already, Please click refresh button.");
//                   spSO.setSelection(0);
//                    return;
//                }
                    selected_SO = arg2 ;
                    int selectedSoPersonId = mSOArrayList.get(selected_SO).getPersonNodeID();
                    CoverageAreaNodeID = mSOArrayList.get(selected_SO).getCoverageAreaNodeID();
                    CoverageAreaNodeType = mSOArrayList.get(selected_SO).getCoverageAreaNodeType();
                    if (selected_SO != -1) {
                        // btnNext.setEnabled(true);
                        if (selected_DSM == -1) {
                            prepareRoutes(selectedSoPersonId, true);
                        }
                    }
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createDSMSpinnerList() {
      /*  ArrayAdapter adapterCategory = new ArrayAdapter(mActitiy, android.R.layout.simple_spinner_item, DSM_Names);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDSM.setAdapter(adapterCategory);*/
        fnBindSpinnerList();
        spDSM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selected_DSM = -1;
                if (arg2 <= 0)
                {

                    prepareRoutes(-1, false);
                    return;
                }
               else if(arg2>0)
               {
                   selected_DSM = arg2-1;
                   int selectedDSMPersonId = mDSMArrayList.get(arg2-1).getPersonNodeID();
                   CoverageAreaNodeID = mDSMArrayList.get(arg2-1).getCoverageAreaNodeID();
                   CoverageAreaNodeType = mDSMArrayList.get(arg2-1).getCoverageAreaNodeType();
                   if (selected_DSM != -1) {
                       prepareRoutes(selectedDSMPersonId, false);
                       //btnNext.setEnabled(true);

                   }
               }

               /* if (arg2 <= 0) {
                    selected_DSM = -1;
                    if (selected_SO < 0)
                        //btnNext.setEnabled(false);
                    prepareRoutes(-1, false);
                    return;
                }
                else {

                    selected_DSM = arg2 ;
                    int selectedDSMPersonId = mDSMArrayList.get(selected_DSM).getPersonNodeID();
                    CoverageAreaNodeID = mDSMArrayList.get(selected_DSM).getCoverageAreaNodeID();
                    CoverageAreaNodeType = mDSMArrayList.get(selected_DSM).getCoverageAreaNodeType();
                    if (selected_DSM != -1) {
                        prepareRoutes(selectedDSMPersonId, false);
                        //btnNext.setEnabled(true);

                    }
                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void prepareRoutes(int selectedPersonId, boolean isPersonSO) {
       // btnNext.setEnabled(false);
        if (mSelectedRouteArrayList != null && mSelectedRouteArrayList.size() > 0) {
            mSelectedRouteArrayList.clear();
        }
        if (selectedPersonId == -1 && !isPersonSO) {
            if (selected_SO != -1) {
                selectedPersonId = mSOArrayList.get(selected_SO).getPersonNodeID();
                if( mSOArrayList.get(selected_SO).getPersonName()!=null &&  mSOArrayList.get(selected_SO).getPersonName().equals("NA"))
                OnBehalfOfPersonName = mSOArrayList.get(selected_SO).getPersonName();
                else
                    OnBehalfOfPersonName="NA";
            }
        }
        for (TblRouteListMaster tblRouteListMaster : mRouteListArrayList) {
            if (tblRouteListMaster.getPersonNodeID() == selectedPersonId) {
                if (isPersonSO) {
                    if (tblRouteListMaster.getPersonNodetype() != 220 && tblRouteListMaster.getPersonNodetype() != 222)
                        continue;
                } else {
                   /* if (tblRouteListMaster.getPersonNodetype() != 230 && tblRouteListMaster.getPersonNodetype() != 240)
                        continue;*/
                    /*if (tblRouteListMaster.getPersonNodetype() != 230 && tblRouteListMaster.getPersonNodetype() != 240)
                        continue;*/
                    if (tblRouteListMaster.getPersonNodetype() ==220)
                        mSelectedRouteArrayList.add(tblRouteListMaster);
                }
               // mSelectedRouteArrayList.add(tblRouteListMaster);
            }
        }

        if (mSelectedRouteArrayList != null && mSelectedRouteArrayList.size() > 0) {
            Route_Names = new String[mSelectedRouteArrayList.size() + 1];
            Route_Names[0] = "Select Route";
            for (int i = 0; i < mSelectedRouteArrayList.size(); i++) {
                Route_Names[i + 1] = mSelectedRouteArrayList.get(i).getRoute();
                if( mSelectedRouteArrayList.get(i).getFlgTodayRoute()==1)
                    mSelectedRoute=mSelectedRouteArrayList.get(i);
            }

        } else {
            Route_Names = new String[1];
            Route_Names[0] = "Select Route";
        }
        createRouteList();

    }

    private void createRouteList() {
        ArrayAdapter adapterCategory = new ArrayAdapter(mActitiy, android.R.layout.simple_spinner_item, Route_Names);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCoverageArea.setAdapter(adapterCategory);

        spCoverageArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 <= 0) {
                    selected_CoverageArea = -1;
                   // btnNext.setEnabled(false);
                    return;
                }
                selected_CoverageArea = arg2 - 1;

                //btnNext.setEnabled(true);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    class MyTimerTask extends TimerTask {
        @Override
        public void run() {

           JointVisitActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    if (progressDialog.isShowing()) {
                        // progressDialog.cancel();
                        //  webView.setVisibility(View.GONE);
                        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(JointVisitActivity.this);
                        alertDialogNoConn.setTitle("Internet issue");
                        //	alertDialogNoConn.setMessage(getText(R.string.syncAlertErrMsggg));
                        alertDialogNoConn.setMessage(getText(R.string.internetslowMsggg));
                        alertDialogNoConn.setCancelable(false);
                        alertDialogNoConn.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();


                                    }
                                });
                        alertDialogNoConn.setNegativeButton("Abort/Cancle",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        progressDialog.cancel();
                                        dialog.dismiss();

                                        Bundle bundle = new Bundle();
                                        onCreate(bundle);

                                    }
                                });
                        alertDialogNoConn.setIcon(R.drawable.error_info_ico);
                        AlertDialog alert = alertDialogNoConn.create();
                        alert.show();

                    }


                }
            });
        }

    }

    public Timer timer;



   /* @OnClick(R.id.btnNext)
    public void onNextButtonClick() {

        if (selected_DSM >= 0) {
            mSelectedDSM = mDSMArrayList.get(selected_DSM);
//            if(mSelectedDSM.getFlgMarketVisitStarted()==0){
//                AppUtils.showAlertDialog(mActitiy,mSelectedDSM.getPersonName()+" has not yet started the Market Visit. Please ask him to start Market Visit the app. If done already, Please click refresh button.");
//                spDSM.setSelection(0);
//                return;
//            }
        }

        if (selected_SO >= 0) {
            mSelectedSo = mSOArrayList.get(selected_SO);
//                            if(mSelectedDSM==null && mSelectedSo.getFlgMarketVisitStarted()==0){
//                    AppUtils.showAlertDialog(mActitiy,mSelectedSo.getPersonName()+" has not yet started the Market Visit. Please ask him to start Market Visit the app. If done already, Please click refresh button.");
//                    spSO.setSelection(0);
//               return;
//                }
        }

        if (selected_CoverageArea >= 0) {
            mSelectedRoute = mSelectedRouteArrayList.get(selected_CoverageArea);
        } else {
            AppUtils.showAlertDialog(mActitiy, "Please select route first.");
            return;
        }

        if (isOnline()) {

            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            timer = new Timer();
            myTimerTask = new MyTimerTask();

            timer.schedule(myTimerTask, 30000);


            try {
                progressDialog = new ProgressDialog(JointVisitActivity.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);


                OpenPopUpDialog();


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            showNoConnAlert();
            requestData = 1;
            if (!mDataSource.isStoreListAlreadyExist(mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType()))
                CommonFunction.getStoreListData(mActitiy, null, CommonInfo.RegistrationID, "Fetching StoreList", mSelectedRoute, false);
            else
                navigateToStoreList();
        }


    }*/

    protected void OpenPopUpDialog() {
        dialog = new Dialog(JointVisitActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.web_veiw_image);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WindowManager.LayoutParams parms = dialog.getWindow().getAttributes();

        parms.gravity = Gravity.TOP | Gravity.LEFT;
        parms.width = parms.MATCH_PARENT;
        parms.height = parms.MATCH_PARENT;

        WebView web_view_imageStore = (WebView) dialog.findViewById(R.id.web_view_imageStore);
        Button button_exit = (Button) dialog.findViewById(R.id.button_exit);
        button_exit.setText("Next");
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            /*    dialog.dismiss();*/

                try {
                    requestData = 1;
                   /* if (mDataSource.isStoreListAlreadyExist(CoverageAreaNodeID, CoverageAreaNodeType)) {
                        navigateToStoreList();
                    }*/


                    if (!mDataSource.isStoreListAlreadyExist(mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType()))
                       // CommonFunction.getStoreListData(mActitiy, null, CommonInfo.RegistrationID, "Fetching StoreList", mSelectedRoute, false);
                        CommonFunction.getAllMasterTableModelData(JointVisitActivity.this, AppUtils.getToken(JointVisitActivity.this), "", "Please wait loading Data!", 5);
                    else
                        navigateToStoreList();

                } catch (Exception e) {
                    dialog.dismiss();
                }

            }
        });

        int ApplicationID = CommonInfo.Application_TypeID;

        String ImageUrl = CommonInfo.URLLinkToViewStoreOverWebProtal.trim();

        ImageUrl = ImageUrl + "?StoreID="+ DynamicQuestionsFragment.selStoreID;//+"?ContactNo="+ContactNo+"&userName="+userName+"&ApplicationID="+ApplicationID;

        Log.d("ImageUrl ", ImageUrl);
        //String ImageUrl=CommonInfo.URLImageLinkToViewStoreOverWebProtal.trim();


        try {
            SharedPreferences sharedPrefCove= getSharedPreferences(CommonInfo.Preference, MODE_PRIVATE);
            web_view_imageStore.setWebViewClient(new MyBrowserRouteSummary(progressDialog,mSelectedRoute.getRouteNodeId(), mSelectedRoute.getRoutenodetype(),mPreferenceManager.getIntValue(AppUtils.PERSON_NODE_ID, 0),mPreferenceManager.getIntValue(AppUtils.PERSON_NODE_TYPE, 210),AppUtils.getToken(JointVisitActivity.this),AppUtils.getDateInMonthTextFormat(JointVisitActivity.this),sharedPrefCove.getInt("CoverageAreaNodeID",0), sharedPrefCove.getInt("CoverageAreaNodeType",0)));
            web_view_imageStore.setWebChromeClient(new WebChromeClient());
            //webView.getSettings().setLoadsImagesAutomatically(true);
            web_view_imageStore.getSettings().setJavaScriptEnabled(true);
            web_view_imageStore.getSettings().setBuiltInZoomControls(true);
            web_view_imageStore.getSettings().setSupportZoom(true);
            //webView.getSettings().setUserAgentString("Android Chrome/10.0.648.204");
            web_view_imageStore.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
            web_view_imageStore.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            web_view_imageStore.getSettings().setLoadWithOverviewMode(true);
            web_view_imageStore.getSettings().setUseWideViewPort(true);
            web_view_imageStore.getSettings().setDomStorageEnabled(true);
            web_view_imageStore.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            web_view_imageStore.getSettings().setPluginState(WebSettings.PluginState.ON);
            //ImageNameToOpenWithImageType= URLEncoder.encode(ImageNameToOpenWithImageType, "UTF-8");
          //  web_view_imageStore.addJavascriptInterface(new JSReceiver(JointVisitActivity.this, JointVisitActivity.this), "AndroidDataHandler");
            web_view_imageStore.loadUrl(ImageUrl);
            //  web_view_imageStore.loadUrl("javascript:fnGetfromPDA('4','140','0','0'',0987654321','26','1.4')");

            //web_view_imageStore.postUrl(ImageUrl,"wewewe");L̥
            //web_view_imageStore.postUrl(ImageUrl, ImageNameToOpenWithImageType.getBytes());

            if (timer != null) {
                timer.cancel();
                timer = null;
            }

        } catch (Exception e) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }

        }
		/*web_view_imageStore.setWebViewClient(new MyBrowser(progressDialog));
		//webView.getSettings().setLoadsImagesAutomatically(true);
		web_view_imageStore.getSettings().setJavaScriptEnabled(true);
		web_view_imageStore.getSettings().setBuiltInZoomControls(true);
		web_view_imageStore.getSettings().setSupportZoom(true);
		//webView.getSettings().setUserAgentString("Android Chrome/10.0.648.204");
		web_view_imageStore.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
		web_view_imageStore.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		web_view_imageStore.getSettings().setLoadWithOverviewMode(true);
		web_view_imageStore.getSettings().setUseWideViewPort(true);

		web_view_imageStore.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
		web_view_imageStore.getSettings().setPluginState(WebSettings.PluginState.ON);

		//web_view_imageStore.loadUrl(ImageUrl);
		web_view_imageStore.postUrl(ImageUrl,"wewewe");*/


        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
    private void setLocationData(JointVisitDetail jointVisitDetail) {

    }/*{

        isGPSEnabled = ((MarketVisitActivity) mActitiy).locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        String GpsLat = "0";
        String GpsLong = "0";
        String GpsAccuracy = "0";
        String GpsAddress = "0";
        if (isGPSEnabled) {

            Location nwLocation = ((MarketVisitActivity) mActitiy).appLocationService.getLocation(((MarketVisitActivity) mActitiy).locationManager, LocationManager.GPS_PROVIDER, location);

            if (nwLocation != null) {
                double lattitude = nwLocation.getLatitude();
                double longitude = nwLocation.getLongitude();
                double accuracy = nwLocation.getAccuracy();
                GpsLat = "" + lattitude;
                GpsLong = "" + longitude;
                GpsAccuracy = "" + accuracy;
                if (AppUtils.isOnline(mActitiy)) {
                    GpsAddress = AppUtils.getAddressOfProviders(mActitiy, GpsLat, GpsLong);
                } else {
                    GpsAddress = "NA";
                }
                GPSLocationLatitude = "" + lattitude;
                if (GPSLocationLatitude.contains("E") || GPSLocationLatitude.contains("e")) {
                    GPSLocationLatitude = AppUtils.convertExponential(lattitude);
                }
                GPSLocationLongitude = "" + longitude;
                if (GPSLocationLongitude.contains("E") || GPSLocationLongitude.contains("e")) {
                    GPSLocationLongitude = AppUtils.convertExponential(longitude);
                }
                GPSLocationProvider = "GPS";
                GPSLocationAccuracy = "" + accuracy;
                AllProvidersLocation = "GPS=Lat:" + lattitude + "Long:" + longitude + "Acc:" + accuracy;

            }
        }

        Location gpsLocation = ((MarketVisitActivity) mActitiy).appLocationService.getLocation(((MarketVisitActivity) mActitiy).locationManager, LocationManager.NETWORK_PROVIDER, location);
        String NetwLat = "0";
        String NetwLong = "0";
        String NetwAccuracy = "0";
        String NetwAddress = "0";
        if (gpsLocation != null) {
            double lattitude1 = gpsLocation.getLatitude();
            double longitude1 = gpsLocation.getLongitude();
            double accuracy1 = gpsLocation.getAccuracy();

            NetwLat = "" + lattitude1;
            NetwLong = "" + longitude1;
            NetwAccuracy = "" + accuracy1;
            if (AppUtils.isOnline(mActitiy)) {
                NetwAddress = AppUtils.getAddressOfProviders(mActitiy, NetwLat, NetwLong);
            } else {
                NetwAddress = "NA";
            }


            NetworkLocationLatitude = "" + lattitude1;
            if (NetworkLocationLatitude.contains("E") || NetworkLocationLatitude.contains("e")) {
                NetworkLocationLatitude = AppUtils.convertExponential(lattitude1);
            }
            NetworkLocationLongitude = "" + longitude1;
            if (NetworkLocationLongitude.contains("E") || NetworkLocationLongitude.contains("e")) {
                NetworkLocationLongitude = AppUtils.convertExponential(longitude1);
            }
            NetworkLocationProvider = "Network";
            NetworkLocationAccuracy = "" + accuracy1;
            if (!AllProvidersLocation.equals("")) {
                AllProvidersLocation = AllProvidersLocation + "$Network=Lat:" + lattitude1 + "Long:" + longitude1 + "Acc:" + accuracy1;
            } else {
                AllProvidersLocation = "Network=Lat:" + lattitude1 + "Long:" + longitude1 + "Acc:" + accuracy1;
            }
            System.out.println("LOCATION(N/W)  LATTITUDE: " + lattitude1 + "LONGITUDE:" + longitude1 + "accuracy:" + accuracy1);

        }
									 *//* TextView accurcy=(TextView) findViewById(R.id.Acuracy);
									  accurcy.setText("GPS:"+GPSLocationAccuracy+"\n"+"NETWORK"+NetworkLocationAccuracy+"\n"+"FUSED"+fusedData);*//*

        jointVisitDetail.setGpsAccuracy(Double.parseDouble(NetworkLocationAccuracy));
        jointVisitDetail.setGpsLatitude(Double.parseDouble(NetworkLocationLatitude));
        jointVisitDetail.setGpsLongitude(Double.parseDouble(NetworkLocationLongitude));
        jointVisitDetail.setGpsAddress(GpsAddress);
        if (isGPSEnabled)
            jointVisitDetail.setFlgGpsOff(1);
        else
            jointVisitDetail.setFlgGpsOff(0);

        String FusedLat = "0";
        String FusedLong = "0";
        String FusedAccuracy = "0";
        String FusedAddress = "0";

        if (!FusedLocationProvider.equals("")) {
            jointVisitDetail.setFlgFusedOnOff(1);
            fnAccurateProvider = "Fused";
            fnLati = FusedLocationLatitude;
            fnLongi = FusedLocationLongitude;
            fnAccuracy = Double.parseDouble(FusedLocationAccuracy);

            FusedLat = FusedLocationLatitude;
            FusedLong = FusedLocationLongitude;
            FusedAccuracy = FusedLocationAccuracy;
            FusedLocationLatitudeWithFirstAttempt = FusedLocationLatitude;
            FusedLocationLongitudeWithFirstAttempt = FusedLocationLongitude;
            FusedLocationAccuracyWithFirstAttempt = FusedLocationAccuracy;
            if (AppUtils.isOnline(mActitiy)) {
                FusedAddress = AppUtils.getAddressOfProviders(mActitiy, FusedLat, FusedLong);
            } else {
                FusedAddress = "NA";
            }

            if (!AllProvidersLocation.equals("")) {
                AllProvidersLocation = AllProvidersLocation + "$Fused=Lat:" + FusedLocationLatitude + "Long:" + FusedLocationLongitude + "Acc:" + fnAccuracy;
            } else {
                AllProvidersLocation = "Fused=Lat:" + FusedLocationLatitude + "Long:" + FusedLocationLongitude + "Acc:" + fnAccuracy;
            }
        }


        fnAccurateProvider = "";
        fnLati = "0";
        fnLongi = "0";
        fnAccuracy = 0.0;

        jointVisitDetail.setFlgFusedOnOff(0);

        if (!FusedLocationProvider.equals("")) {
            fnAccurateProvider = "Fused";
            fnLati = FusedLocationLatitude;
            fnLongi = FusedLocationLongitude;
            fnAccuracy = Double.parseDouble(FusedLocationAccuracy);
        }

        if (!fnAccurateProvider.equals("")) {
            if (!GPSLocationProvider.equals("")) {
                if (Double.parseDouble(GPSLocationAccuracy) < fnAccuracy) {
                    fnAccurateProvider = "Gps";
                    fnLati = GPSLocationLatitude;
                    fnLongi = GPSLocationLongitude;
                    fnAccuracy = Double.parseDouble(GPSLocationAccuracy);
                }
            }
        } else {
            if (!GPSLocationProvider.equals("")) {
                fnAccurateProvider = "Gps";
                fnLati = GPSLocationLatitude;
                fnLongi = GPSLocationLongitude;
                fnAccuracy = Double.parseDouble(GPSLocationAccuracy);
            }
        }

        if (!fnAccurateProvider.equals("")) {
            if (!NetworkLocationProvider.equals("")) {
                if (Double.parseDouble(NetworkLocationAccuracy) < fnAccuracy) {
                    fnAccurateProvider = "Network";
                    fnLati = NetworkLocationLatitude;
                    fnLongi = NetworkLocationLongitude;
                    fnAccuracy = Double.parseDouble(NetworkLocationAccuracy);
                }
            }
        } else {
            if (!NetworkLocationProvider.equals("")) {
                fnAccurateProvider = "Network";
                fnLati = NetworkLocationLatitude;
                fnLongi = NetworkLocationLongitude;
                fnAccuracy = Double.parseDouble(NetworkLocationAccuracy);
            }
        }


        jointVisitDetail.setFlgNetworkOff(0);
        jointVisitDetail.setFusedAccuracy(Double.parseDouble(FusedAccuracy));
        jointVisitDetail.setFusedAddress(FusedAddress);
        jointVisitDetail.setFusedLatitude(Double.parseDouble(fnLati));
        jointVisitDetail.setFusedLongitude(Double.parseDouble(fnLongi));


        jointVisitDetail.setAccuracy(fnAccuracy);
        jointVisitDetail.setAddress(AllProvidersLocation);
        jointVisitDetail.setAllProviderData(AllProvidersLocation);
        jointVisitDetail.setLocProvider(fnAccurateProvider);


    }*/

    public void shardPrefForSalesman(int salesmanNodeId, int salesmanNodeType) {


        SharedPreferences.Editor editor = sharedPref.edit();


        editor.putInt("SalesmanNodeId", salesmanNodeId);
        editor.putInt("SalesmanNodeType", salesmanNodeType);

        editor.commit();

    }

    public void shardPrefForCoverageArea(int coverageAreaNodeID, int coverageAreaNodeType) {


        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("CoverageAreaNodeID", coverageAreaNodeID);
        editor.putInt("CoverageAreaNodeType", coverageAreaNodeType);


        editor.commit();

    }

    public void shardPrefForCoverageAreaJointVisitPersonName(String PersonName) {


        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("JointVisitPersonName", PersonName);



        editor.commit();

    }

    public void shardPrefForCoverageAreaOnBehalfVisitPersonName(String PersonName) {


        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("OnBehalfVisitPersonName", PersonName);

        editor.commit();

    }
    private void navigateToStoreList() {

        if(mSelectedRoute!=null)
        shardPrefForCoverageArea(mSelectedRoute.getCoverageAreaNodeID(),mSelectedRoute.getCoverageAreaNodeType());
        CommonInfo.CoverageAreaNodeID=mSelectedRoute.getCoverageAreaNodeID();
        CommonInfo.CoverageAreaNodeType=mSelectedRoute.getCoverageAreaNodeType();

        if(VisitType==3)
        {



            JointVisitDetail jointVisitDetail = new JointVisitDetail();
            jointVisitDetail.setImei(AppUtils.getToken(mActitiy));
            jointVisitDetail.setManagerNodeId(mPreferenceManager.getIntValue(AppUtils.PERSON_NODE_ID, 0));
            jointVisitDetail.setManagerNodeType(mPreferenceManager.getIntValue(AppUtils.PERSON_NODE_TYPE, 210));

            if(mSelectedRoute!=null) {
                jointVisitDetail.setRouteId(mSelectedRoute.getRouteNodeId());
                jointVisitDetail.setRouteType(mSelectedRoute.getRoutenodetype());
            }
            else
            {
                jointVisitDetail.setRouteId(0);
                jointVisitDetail.setRouteType(0);
            }

          /*  jointVisitDetail.setCoverageId(mSelectedRoute.getCoverageAreaNodeID());
            jointVisitDetail.setCoverageType(mSelectedRoute.getCoverageAreaNodeType());*/

            jointVisitDetail.setCoverageId(CoverageAreaNodeID);
            jointVisitDetail.setCoverageType(CoverageAreaNodeType);

            jointVisitDetail.setActualLatitude(mGps.mCurrentLocation.getLatitude());
            jointVisitDetail.setActualLongitude(mGps.mCurrentLocation.getLongitude());
            jointVisitDetail.setLocProvider(mGps.mCurrentLocation.getProvider());
            jointVisitDetail.setAccuracy(mGps.mCurrentLocation.getAccuracy());
            jointVisitDetail.setVisitDate(AppUtils.getCurrentDate(this));
            jointVisitDetail.setVisitStartTime( TimeUtils.getCurrentDeviceTime(TimeUtils.DATE_TIME_FORMAT));
            jointVisitDetail.setSstat(1);
            jointVisitDetail.setIsCompleted(0);
            setLocationData(jointVisitDetail);

            String uniqueVisitId = mDataSource.createJointVisit(jointVisitDetail);

            if (uniqueVisitId != null && uniqueVisitId.length() > 0) {

                JointVisitMemberDetail jointVisitMemberDetail = new JointVisitMemberDetail();
                jointVisitMemberDetail.setJointVisitId(uniqueVisitId);
                if (mSelectedSo != null) {

                        shardPrefForSalesman(mSelectedSo.getPersonNodeID(),mSelectedSo.getPersonNodeType());


                    jointVisitMemberDetail.setFellowPersonNodeId(mSelectedSo.getPersonNodeID());
                    jointVisitMemberDetail.setFellowPersonNodeType(mSelectedSo.getPersonNodeType());
                    jointVisitMemberDetail.setFellowPersonName(mSelectedSo.getPersonName());
                    shardPrefForCoverageAreaJointVisitPersonName(mSelectedSo.getPersonName());
                    mDataSource.insertJointVisitFellowDetails(jointVisitMemberDetail);
                }
                if (mSelectedDSM != null) {
                    shardPrefForSalesman(mSelectedDSM.getPersonNodeID(),mSelectedDSM.getPersonNodeType());
                    jointVisitMemberDetail.setFellowPersonNodeId(mSelectedDSM.getPersonNodeID());
                    jointVisitMemberDetail.setFellowPersonNodeType(mSelectedDSM.getPersonNodeType());
                    jointVisitMemberDetail.setFellowPersonName(mSelectedDSM.getPersonName());
                    shardPrefForCoverageAreaJointVisitPersonName(mSelectedDSM.getPersonName());
                    mDataSource.insertJointVisitFellowDetails(jointVisitMemberDetail);
                }
            }
            // intent.putExtra(AppUtils.FLGJOINTVISIT, 1);
            // intent.putExtra(AppUtils.VisitType, VisitType);
          //  fnJointVisitStartSuccessfully();

            Intent intent = new Intent(JointVisitActivity.this, StoreSelection.class);
            intent.putExtra("imei", AppUtils.getToken(JointVisitActivity.this));
            intent.putExtra("userDate", userDate);
            intent.putExtra("pickerDate", fDate);
            intent.putExtra("rID", rID);
            intent.putExtra("JOINTVISITID", uniqueVisitId);
            intent.putExtra(AppUtils.FLGJOINTVISIT, FLGJOINTVISIT);
            intent.putExtra(AppUtils.VisitType, VisitType);

            if(dialog!=null && dialog.isShowing())
                dialog.dismiss();
            startActivity(intent);
            finish();
        }
        else {
            shardPrefForCoverageAreaOnBehalfVisitPersonName(OnBehalfOfPersonName);
            shardPrefForCoverageAreaOnBehalfVisitPersonName(mSelectedDSM.getPersonName());


            shardPrefForSalesman(mSelectedDSM.getPersonNodeID(), mSelectedDSM.getPersonNodeType());

            //mSelectedRoute
            Intent intent = new Intent(JointVisitActivity.this, StoreSelection.class);
            intent.putExtra("imei", AppUtils.getToken(this));
            intent.putExtra("userDate", userDate);
            intent.putExtra("pickerDate", fDate);
            intent.putExtra("rID", rID);
            intent.putExtra("JOINTVISITID", "NA");
            intent.putExtra(AppUtils.FLGJOINTVISIT, FLGJOINTVISIT);
            intent.putExtra(AppUtils.VisitType, VisitType);

            if(dialog!=null && dialog.isShowing())
                dialog.dismiss();
            startActivity(intent);
            finish();

       /* Bundle args = new Bundle();
        args.putInt(AppUtils.ROUTEID, mSelectedRoute.getRouteNodeId());
        args.putInt(AppUtils.FLGJOINTVISIT, 1);
        args.putString(AppUtils.JOINTVISITID, uniqueVisitId);
        args.putSerializable(AppUtils.ROUTE, mSelectedRoute);
        ((MarketVisitActivity) mActitiy).removeCurrentFragment();
        ((MarketVisitActivity) mActitiy).loadFragment(args, MarketVisitActivity.STOREVISIT);*/

/*
            if (isOnline()) {
                //fullFileName1 = df.format(dateobj);
                String newfullFileName = AppUtils.fnGetFileNameToSave(JointVisitActivity.this);//AppUtils.getToken(DSR_Registration.this) + "." + presentRoute + "." + AppUtils.doGetTime("dd.MMM.yyyy.HH.mm.ss");//AppUtils.getToken(DSR_Registration.this) + "." + presentRoute + "." + df.format(dateobj);

                try {

                    File OrderXMLFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.OrderXMLFolder);

                    if (!OrderXMLFolder.exists()) {
                        OrderXMLFolder.mkdirs();
                    }
                    mDataSource.updateImageRecordsSyncdForDSRRegistrationAndSelfi(3);

                    DASFA.export(CommonInfo.DATABASE_NAME, newfullFileName, "0");

                    mDataSource.savetbl_XMLfiles(newfullFileName, "3", "1");

                    mDataSource.updateImageRecordsSyncdForDSRRegistrationAndSelfi(4);
                } catch (Exception ex) {

                }

                Intent mMyServiceIntent = new Intent(JointVisitActivity.this, MyService.class);
                mMyServiceIntent.putExtra("xmlPathForSync", Environment.getExternalStorageDirectory() + "/" + CommonInfo.OrderXMLFolder + "/" + newfullFileName + ".xml");
                mMyServiceIntent.putExtra("OrigZipFileName", newfullFileName);
                mMyServiceIntent.putExtra("whereTo", "DayStart");//
                if (!isMyServiceRunning(MyService.class)) {
                    startService(mMyServiceIntent);
                }

            }
            Intent intent = new Intent(JointVisitActivity.this, StoreSelection.class);
            intent.putExtra("imei", AppUtils.getToken(this));
            intent.putExtra("userDate", userDate);
            intent.putExtra("pickerDate", fDate);
            intent.putExtra("rID", rID);
            intent.putExtra("JOINTVISITID", uniqueVisitId);
            startActivity(intent);
            finish();*/
        }

    }

    private void fnJointVisitStartSuccessfully()
    {


        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(JointVisitActivity.this);
        builder.setTitle(R.string.app_nameAlertInfo)
                .setMessage("Joint Visit started successfully!")
                .setCancelable(false)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i=new Intent(JointVisitActivity.this,AllButtonActivity.class);
                        startActivity(i);
                        finish();
                    }
                });

        builder.show();


        //Code Else Ends Here
        // end else


    }
 /*   @OnClick(R.id.btRefresh)
    public void onRefreshButtonClick() {
        requestData = 0;
        CommonFunction.getSubOrdinateListData(mActitiy, JointVisitActivity.this, CommonInfo.RegistrationID, "Fetching data", 0);
    }
*/
 private boolean isMyServiceRunning(Class<?> serviceClass) {
     ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
     for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
         if (serviceClass.getName().equals(service.service.getClassName())) {
             Log.i("isMyServiceRunning?", true + "");
             return true;
         }
     }
     Log.i("isMyServiceRunning?", false + "");
     return false;
 }



    public void showNoConnAlert() {
        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(JointVisitActivity.this);
        alertDialogNoConn.setTitle(R.string.genTermNoDataConnection);
        alertDialogNoConn.setMessage("No Internet Connection is Available");
        alertDialogNoConn.setNeutralButton(R.string.txtOk,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();


                    }
                });

        alertDialogNoConn.setIcon(R.drawable.error_ico);
        AlertDialog alert = alertDialogNoConn.create();
        alert.show();

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) JointVisitActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
