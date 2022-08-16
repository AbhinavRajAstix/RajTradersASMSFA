package com.astix.rajtraderssfasmasales;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.allana.truetime.TimeUtils;
import com.astix.Common.CommonInfo;
import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.location.LocationRetreivingGlobal;
import com.astix.rajtraderssfasmasales.model.TblPersonGateMeetingFocusedProductCoverageWiseMstr;
import com.astix.rajtraderssfasmasales.model.TblPersonGateMeetingTarget;
import com.astix.rajtraderssfasmasales.model.TblStoreListMasterDataRetrive;
import com.astix.rajtraderssfasmasales.sync.DatabaseAssistant;
import com.astix.rajtraderssfasmasales.sync.SyncJobService;
import com.astix.rajtraderssfasmasales.utils.AppUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import io.reactivex.annotations.NonNull;

import static br.com.zbra.androidlinq.Linq.stream;

//import com.astix.sfatju.R;

public class PersonGateMeetingTarget extends BaseActivity implements PersonGateMeetingTargetListAdapter.EditTextClickListener,InterfaceShowPersonGateMeetingOldDataAgainstPerson {
    Button but_skip;
    public String selectedOrderComment = "";
    String newfullFileName="";
    public static final String DATASUBDIRECTORYForText = CommonInfo.TextFileFolder;
    static String seleted_CovereageIDType = "0";
    private final Context mContext = this;
    public int isPerformDayEndFirst = 0;
    public EditText ed_search;
    public int selectedCoverageIDForFilter = 0;
    public boolean addStoreBtnClick = false;
    public String[] xmlForWeb = new String[1];
    public int syncFLAG = 0;
    public String currSysDate;
    public int chkFlgForErrorToCloseApp = 0;
    public String passDate;
    public SimpleDateFormat sdf;
    public String fDate;
    public String userDate;
    public String pickerDate;
    public String imei;
    public String[] storeList;
    public String[] storeRouteIdType;
    public TableLayout tl2;
    public TextView txtview_selectstoretext;
    public int flgDayEndOrChangeRoutenew = 0;
    public String Noti_text = "Null";
    public int MsgServerID = 0;
    public boolean[] checks;
    public int noLOCflag = 0;
    public ProgressDialog pDialogSync;
    public long syncTIMESTAMP;
    public String fullFileName1;
    public String[] storeCode;
    public String[] storeName;
    public String[] storeContactNumber;
    public String[] storeStatus;
    public String[] StoreflgSubmitFromQuotation;
    public String[] storeCloseStatus;
    public String[] storeNextDayStatus;
    public ListView listView;
    public ProgressDialog pDialog2STANDBY;
    public TableRow tr;
    public String selStoreID = "";
    public String selStoreName = "";
    public String prevSelStoreID;
    public Double myCurrentLon; // removed "static"
    public Double myCurrentLat;
    public Double finalLatNow;
    public Double finalLonNow;
    public int gotLoc = 0;
    public int locStat = 0;
    public Location firstLoc;
    public float acc;
    public Location location2;
    public String[] StoreList2Procs;
    public Location finalLocation;
    public int valDayEndOrChangeRoute = 0; // 1=Clicked on Day End Button, 2=Clicked on Change Route Button
    public String[] route_name;
    public String[] route_name_id;
    public String selected_route_id = "0";
    public String temp_select_routename = "NA";
    public String temp_select_routeid = "NA";
    public String cID;
    public PowerManager pm;
    public PowerManager.WakeLock wl;
    ImageView img_bck;
    RecyclerView rv_main;
    PersonGateMeetingTargetListAdapter storeListAdapter;
    List<TblPersonGateMeetingTarget> tblPersonGateMeetingTargetMstrDataRetriveArrayList = new ArrayList<TblPersonGateMeetingTarget>();
    List<TblPersonGateMeetingTarget> tblPersonGateMeetingTargetDataRetriveArrayListForFilter = new ArrayList<TblPersonGateMeetingTarget>();
    ListView list_store;

    Spinner spinner_CoverageList;
    String[] Coverage_names = null;

    HashMap<String, String> hmapCoverageIDNameDetails = new HashMap<String, String>();

    RelativeLayout relativeLayout1;
    int battLevel = 0;

    ProgressDialog mProgressDialog;
    ProgressDialog mProgressDialogLodingStores;
    DatabaseAssistant DA;
    public ProductFilledDataModelGateEntry prdctModelArrayList = new ProductFilledDataModelGateEntry();

    CustomKeyboard customKeyboard;
    CustomKeyboard customKeyboard2;
    public String From="";

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent intent) {

            battLevel = intent.getIntExtra("level", 0);

        }
    };
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            customKeyboard.hideCustomKeyboard();
            return false;

        }
        if (keyCode == KeyEvent.KEYCODE_HOME) {

        }
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    public static String[] checkNumberOfFiles(File dir) {
        int NoOfFiles = 0;
        String[] Totalfiles = null;

        if (dir.isDirectory()) {
            String[] children = dir.list();
            NoOfFiles = children.length;
            Totalfiles = new String[children.length];

            for (int i = 0; i < children.length; i++) {
                Totalfiles[i] = children[i];
            }
        }
        return Totalfiles;
    }

    public static void zip(String[] files, String zipFile) throws IOException {
        BufferedInputStream origin = null;
        final int BUFFER_SIZE = 2048;

        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
        try {
            byte[] data = new byte[BUFFER_SIZE];

            for (int i = 0; i < files.length; i++) {
                FileInputStream fi = new FileInputStream(files[i]);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                try {
                    ZipEntry entry = new ZipEntry(files[i].substring(files[i].lastIndexOf("/") + 1));
                    out.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }
                } finally {
                    origin.close();
                }
            }
        } finally {
            out.close();
        }
    }

    public void locationRetrievingAndDistanceCalculating() {
        LocationRetreivingGlobal llaaa = new LocationRetreivingGlobal();
        llaaa.locationRetrievingAndDistanceCalculating(this, true, true, 20, 1);


    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }


    public void onDestroy() {
        super.onDestroy();
        // unregister receiver
        this.unregisterReceiver(this.mBatInfoReceiver);

        //this.unregisterReceiver(this.KillME);
    }


    public void setUpVariable() {


        Button but_submit = findViewById(R.id.but_submit);
        but_submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                for(TblPersonGateMeetingTarget  tblPersonGateMeetingTarget:tblPersonGateMeetingTargetMstrDataRetriveArrayList)
                {
                    tblPersonGateMeetingTarget.setEntryDate(TimeUtils.getNetworkDate(PersonGateMeetingTarget.this,"dd-MMM-yyyy"));
                    tblPersonGateMeetingTarget.setDstrbn_Tgt(0.0);
                    tblPersonGateMeetingTarget.setSales_Tgt(0.0);
                    if(prdctModelArrayList.getHmapPersonDistributionTarget()!=null && prdctModelArrayList.getHmapPersonDistributionTarget().size()>0)
                    {
                        if(prdctModelArrayList.getHmapPersonDistributionTarget().containsKey(tblPersonGateMeetingTarget.getPersonNodeID()))
                        {
                            tblPersonGateMeetingTarget.setDstrbn_Tgt(Double.parseDouble(prdctModelArrayList.getHmapPersonDistributionTarget().get(tblPersonGateMeetingTarget.getPersonNodeID())));
                        }

                    }
                    if(prdctModelArrayList.getHmapPersonSalesTarget()!=null && prdctModelArrayList.getHmapPersonSalesTarget().size()>0)
                    {
                        if(prdctModelArrayList.getHmapPersonSalesTarget().containsKey(tblPersonGateMeetingTarget.getPersonNodeID()))
                        {
                            tblPersonGateMeetingTarget.setSales_Tgt(Double.parseDouble(prdctModelArrayList.getHmapPersonSalesTarget().get(tblPersonGateMeetingTarget.getPersonNodeID())));
                        }

                    }
                    List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> tblPersonGateMeetingFocusedProductCoverageWiseMstrsList=tblPersonGateMeetingTarget.getTblPersonGateMeetingFocusedProductCoverageWiseMstrList();
                    if(tblPersonGateMeetingFocusedProductCoverageWiseMstrsList!=null && tblPersonGateMeetingFocusedProductCoverageWiseMstrsList.size()>0)
                    {
                        for(TblPersonGateMeetingFocusedProductCoverageWiseMstr tblPersonGateMeetingFocusedProductCoverageWiseMstr:tblPersonGateMeetingFocusedProductCoverageWiseMstrsList)
                        {
                            tblPersonGateMeetingFocusedProductCoverageWiseMstr.setPDACode(AppUtils.getToken(PersonGateMeetingTarget.this));
                            tblPersonGateMeetingFocusedProductCoverageWiseMstr.setPersonNodeID(tblPersonGateMeetingTarget.getPersonNodeID());
                            tblPersonGateMeetingFocusedProductCoverageWiseMstr.setPersonNodeType(tblPersonGateMeetingTarget.getPersonNodeType());
                            tblPersonGateMeetingFocusedProductCoverageWiseMstr.setEntryDate(TimeUtils.getNetworkDate(PersonGateMeetingTarget.this,"dd-MMM-yyyy"));
                            tblPersonGateMeetingFocusedProductCoverageWiseMstr.setSales_Tgt_Focus(0.0);
                            tblPersonGateMeetingFocusedProductCoverageWiseMstr.setDstrbn_Tgt_Focus(0.0);
                            if(prdctModelArrayList.getHmapPersonFocusProductSalesTarget()!=null && prdctModelArrayList.getHmapPersonFocusProductSalesTarget().size()>0)
                            {
                                if(prdctModelArrayList.getHmapPersonFocusProductSalesTarget().containsKey(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType()))
                                {
                                    tblPersonGateMeetingFocusedProductCoverageWiseMstr.setSales_Tgt_Focus(Double.parseDouble(prdctModelArrayList.getHmapPersonFocusProductSalesTarget().get(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType())));
                                }

                            }


                            if(prdctModelArrayList.getHmapPersonFocusProductDistributionTarget()!=null && prdctModelArrayList.getHmapPersonFocusProductDistributionTarget().size()>0)
                            {
                                if(prdctModelArrayList.getHmapPersonFocusProductDistributionTarget().containsKey(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType()))
                                {
                                    tblPersonGateMeetingFocusedProductCoverageWiseMstr.setDstrbn_Tgt_Focus(Double.parseDouble(prdctModelArrayList.getHmapPersonFocusProductDistributionTarget().get(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType())));
                                }

                            }
                        }
                    }


                }




                try {
                    mDataSource.delete_tblPersonGateMeetingTargetDetails();
                    mDataSource.delete_tblPersonGateMeetingFocusedProductCoverageWiseDetails();
                    /*if (tblPersonGateMeetingTargetsWithData != null && tblPersonGateMeetingTargetsWithData.size()>0)
                    {

                        if(tblPersonGateMeetingTargetsWithData!=null && tblPersonGateMeetingTargetsWithData.size()>0)
                        {
                            mDataSource.insert_tblPersonGateMeetingTargetDetails(tblPersonGateMeetingTargetsWithData);
                        }
                        for(TblPersonGateMeetingTarget  tblPersonGateMeetingTarget:tblPersonGateMeetingTargetMstrDataRetriveArrayList)
                        {
                            List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> tblPersonGateMeetingFocusedProductCoverageWiseMstrsList=tblPersonGateMeetingTarget.getTblPersonGateMeetingFocusedProductCoverageWiseMstrList();
                            if(tblPersonGateMeetingFocusedProductCoverageWiseMstrsList!=null && tblPersonGateMeetingFocusedProductCoverageWiseMstrsList.size()>0)
                            {
                                mDataSource.insert_tblPersonGateMeetingFocusedProductCoverageWiseDetails(tblPersonGateMeetingFocusedProductCoverageWiseMstrsList);
                            }
                        }
                        FullSyncDataNow task = new FullSyncDataNow(PersonGateMeetingTarget.this);
                        task.execute();
                    }*/
                    if (tblPersonGateMeetingTargetMstrDataRetriveArrayList != null && tblPersonGateMeetingTargetMstrDataRetriveArrayList.size()>0)
                    {
                        for(TblPersonGateMeetingTarget  tblPersonGateMeetingTarget:tblPersonGateMeetingTargetMstrDataRetriveArrayList)
                        {

                           List<TblPersonGateMeetingTarget> tblPersonGateMeetingTargetsWithData=stream(tblPersonGateMeetingTargetMstrDataRetriveArrayList).where(p->(p.getDstrbn_Tgt()>0.0 || p.getSales_Tgt()>0.0) && p.getPersonNodeID().equals(tblPersonGateMeetingTarget.getPersonNodeID())).toList();// || p.getSales_Tgt_Focus()>0.0 || p.getDstrbn_Tgt_Focus()>0.0

                            if (tblPersonGateMeetingTargetsWithData != null && tblPersonGateMeetingTargetsWithData.size()>0)
                            {
                                mDataSource.insert_tblPersonGateMeetingTargetDetails(tblPersonGateMeetingTargetsWithData);
                            }

                                List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> tblPersonGateMeetingFocusedProductCoverageWiseMstrsList=tblPersonGateMeetingTarget.getTblPersonGateMeetingFocusedProductCoverageWiseMstrList();
                                if(tblPersonGateMeetingFocusedProductCoverageWiseMstrsList!=null && tblPersonGateMeetingFocusedProductCoverageWiseMstrsList.size()>0)
                                {
                                    mDataSource.insert_tblPersonGateMeetingFocusedProductCoverageWiseDetails(tblPersonGateMeetingFocusedProductCoverageWiseMstrsList);
                                }
                            }
                        FullSyncDataNow task = new FullSyncDataNow(PersonGateMeetingTarget.this);
                        task.execute();
                    }
                    else
                    {
                        showAlertSingleButtonInfo("Can not submit the data without any target entry.");
                    }

                } catch (Exception e) {

                }


            }
        });


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_gate_meeting_target);

        imei = AppUtils.getToken(PersonGateMeetingTarget.this);
        pickerDate = getDateInMonthTextFormat();
        userDate = getDateInMonthTextFormat();
        DA = new DatabaseAssistant(this);

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        rv_main = findViewById(R.id.rv_main);
        relativeLayout1 = findViewById(R.id.relativeLayout1);
        customKeyboard = new CustomKeyboard(this, R.id.keyboardviewNum, R.xml.num);
        customKeyboard2 = new CustomKeyboard(this, R.id.keyboardviewNum, R.xml.num);

        mProgressDialog = new ProgressDialog(PersonGateMeetingTarget.this);
        mProgressDialog.setTitle(getResources().getString(R.string.genTermPleaseWaitNew));
        mProgressDialog.setMessage(getResources().getString(R.string.txtRefreshingData));

        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);

        Date date1 = new Date();
        sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        passDate = sdf.format(date1);

        fDate = passDate.trim();
        initializeAllView();


        getRouteDetail();

        spinner_CoverageList = findViewById(R.id.spinner_CoverageList);
        ArrayAdapter adapterCoverageList = new ArrayAdapter(this, R.layout.spinner_item_route_store_selection, Coverage_names);
        adapterCoverageList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_CoverageList.setAdapter(adapterCoverageList);


        spinner_CoverageList.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                seleted_CovereageIDType = hmapCoverageIDNameDetails.get(arg0.getItemAtPosition(arg2).toString());

                cID = seleted_CovereageIDType.split(Pattern.quote("_"))[0];
                selectedCoverageIDForFilter = Integer.parseInt(cID);


                ed_search.setText("");
                ed_search.clearFocus();
                fnFilterBasedOnCoverage();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        setUpVariable();

        new GetData().execute();


    }

    public void initializeAllView() {
        img_bck=findViewById(R.id.img_bck);

        but_skip=findViewById(R.id.but_skip);
        but_skip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(From.equals("DayStart"))
                {

                    new NextActivityCustomDialog(PersonGateMeetingTarget.this).show();

                }
                else {
                    finish();
                }
            }
        });

        Intent passedvals = getIntent();
        if(passedvals!=null)
        {
            From=passedvals.getStringExtra("From");

        }
        /*if(From.equals("DayStart"))
        {
            img_bck.setVisibility(View.GONE);
            but_skip.setVisibility(View.VISIBLE);
        }
        else
        {
            but_skip.setVisibility(View.GONE);
        }*/


        if(From.equals("DayStart"))
        {
            img_bck.setVisibility(View.GONE);
        }

        img_bck.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ed_search = findViewById(R.id.ed_search);

        ed_search.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                customKeyboard.hideCustomKeyboard();
            }
        });


        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().trim().length() == 0 && selectedCoverageIDForFilter != 0) {
                    fnFilterBasedOnCoverage();

                } else {
                    tblPersonGateMeetingTargetDataRetriveArrayListForFilter.clear();

                    tblPersonGateMeetingTargetDataRetriveArrayListForFilter = stream(tblPersonGateMeetingTargetMstrDataRetriveArrayList).where(p -> p.getPersonname().toLowerCase().contains(s.toString().trim().toLowerCase())).orderBy(p -> p.getCovArea()).toList();// && p.getRouteID() == selectedCoverageIDForFilter

                    fnMarkFirstRouteNameRowVisible();
                    storeListAdapter = new PersonGateMeetingTargetListAdapter(PersonGateMeetingTarget.this, tblPersonGateMeetingTargetDataRetriveArrayListForFilter, userDate, pickerDate,prdctModelArrayList,customKeyboard);
                    rv_main.setAdapter(storeListAdapter);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void fnFilterBasedOnCoverage() {

        tblPersonGateMeetingTargetDataRetriveArrayListForFilter.clear();

        String stext = ed_search.getText().toString();
        if (!TextUtils.isEmpty(ed_search.getText())) {
            stext = ed_search.getText().toString();
        }


        String finalStext = stext;
        if (selectedCoverageIDForFilter == 0) {
            tblPersonGateMeetingTargetDataRetriveArrayListForFilter = stream(tblPersonGateMeetingTargetMstrDataRetriveArrayList).where(p -> p.getPersonname().toLowerCase().contains(finalStext.toLowerCase())).orderBy(p -> p.getCovAreaNodeID()).toList();
        } else {
            tblPersonGateMeetingTargetDataRetriveArrayListForFilter = stream(tblPersonGateMeetingTargetMstrDataRetriveArrayList).where(p -> p.getPersonname().toLowerCase().contains(finalStext.toLowerCase()) && Integer.parseInt(p.getCovAreaNodeID()) == selectedCoverageIDForFilter).orderBy(p -> Integer.parseInt(p.getCovAreaNodeID())).toList();
        }
        fnMarkFirstRouteNameRowVisible();
        storeListAdapter = new PersonGateMeetingTargetListAdapter(PersonGateMeetingTarget.this, tblPersonGateMeetingTargetDataRetriveArrayListForFilter, userDate, pickerDate,prdctModelArrayList,customKeyboard);
        rv_main.setAdapter(storeListAdapter);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        imei = AppUtils.getToken(mContext);

    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

    }

    private void getRouteDetail() {

        hmapCoverageIDNameDetails = mDataSource.fetch_Coverage_ListForGateMeeting();

        int index = 0;
        if (hmapCoverageIDNameDetails != null) {
            Coverage_names = new String[hmapCoverageIDNameDetails.size()];
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(hmapCoverageIDNameDetails);
            Set set2 = map.entrySet();
            Iterator iterator = set2.iterator();
            while (iterator.hasNext()) {
                Map.Entry me2 = (Map.Entry) iterator.next();
                Coverage_names[index] = me2.getKey().toString();
                index = index + 1;
            }
        }


    }

    public void fnMarkFirstRouteNameRowVisible() {
        Iterator<TblPersonGateMeetingTarget> crunchifyIteratorForSeacrh = tblPersonGateMeetingTargetDataRetriveArrayListForFilter.iterator();
        if (tblPersonGateMeetingTargetDataRetriveArrayListForFilter != null && tblPersonGateMeetingTargetDataRetriveArrayListForFilter.size() > 0) {
            int catid = 0;
            Set catSet = new HashSet();
            while (crunchifyIteratorForSeacrh.hasNext()) {
                TblPersonGateMeetingTarget tblStoreProductMappingRecordForSeach = crunchifyIteratorForSeacrh.next();
                if (!catSet.contains(tblStoreProductMappingRecordForSeach.getCovArea())) {
                    tblStoreProductMappingRecordForSeach.setFlgShowHeader(1);
                    catSet.add(tblStoreProductMappingRecordForSeach.getCovArea());
                } else {
                    tblStoreProductMappingRecordForSeach.setFlgShowHeader(0);
                }
            }
        }

    }

    @Override
    public void onClick(EditText editText) {
        customKeyboard.registerEditText(editText);
        customKeyboard.showCustomKeyboard(editText);
    }



    public class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialogLodingStores = new ProgressDialog(PersonGateMeetingTarget.this);
            mProgressDialogLodingStores.setTitle(PersonGateMeetingTarget.this.getResources().getString(R.string.genTermPleaseWaitNew));
            mProgressDialogLodingStores.setMessage(PersonGateMeetingTarget.this.getResources().getString(R.string.Loading));
            mProgressDialogLodingStores.setIndeterminate(true);
            mProgressDialogLodingStores.setCancelable(false);
            mProgressDialogLodingStores.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            tblPersonGateMeetingTargetMstrDataRetriveArrayList = mDataSource.fnGetAllPersonListForGateMeetingTarget();
            for(TblPersonGateMeetingTarget  tblPersonGateMeetingTarget:tblPersonGateMeetingTargetMstrDataRetriveArrayList)
            {
                if(tblPersonGateMeetingTarget.getDstrbn_Tgt()>0.0)
                {
                        prdctModelArrayList.setPersonDistributionTarget(tblPersonGateMeetingTarget.getPersonNodeID(),""+tblPersonGateMeetingTarget.getDstrbn_Tgt());


                }
                if(tblPersonGateMeetingTarget.getSales_Tgt()>0.0)
                {
                        prdctModelArrayList.setPersonSalesTarget(tblPersonGateMeetingTarget.getPersonNodeID(), ""+tblPersonGateMeetingTarget.getSales_Tgt());
                }

                List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> tblPersonGateMeetingFocusedProductCoverageWiseMstrsList=tblPersonGateMeetingTarget.getTblPersonGateMeetingFocusedProductCoverageWiseMstrList();
                if(tblPersonGateMeetingFocusedProductCoverageWiseMstrsList!=null && tblPersonGateMeetingFocusedProductCoverageWiseMstrsList.size()>0) {
                    for (TblPersonGateMeetingFocusedProductCoverageWiseMstr tblPersonGateMeetingFocusedProductCoverageWiseMstr : tblPersonGateMeetingFocusedProductCoverageWiseMstrsList) {
                        if(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getSales_Tgt_Focus()>0.0)
                        {
                            prdctModelArrayList.setFocusedProductSalesTarget(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType(),""+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getSales_Tgt_Focus());


                        }
                        if(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getDstrbn_Tgt_Focus()>0.0)
                        {
                            prdctModelArrayList.setFocusedProductDistributionTarget(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType(),""+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getDstrbn_Tgt_Focus());


                        }
                    }
                }

               /* if(tblPersonGateMeetingTarget.getSales_Tgt_Focus()>0.0)
                {
                    prdctModelArrayList.setFocusedProductSalesTarget(tblPersonGateMeetingTarget.getPersonNodeID(),""+tblPersonGateMeetingTarget.getSales_Tgt_Focus());


                }

                if(tblPersonGateMeetingTarget.getDstrbn_Tgt_Focus()>0.0)
                {
                    prdctModelArrayList.setFocusedProductDistributionTarget(tblPersonGateMeetingTarget.getPersonNodeID(),""+tblPersonGateMeetingTarget.getDstrbn_Tgt_Focus());


                }*/
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            if (tl2 != null) {
                tl2.removeAllViews();
            }

            fnFilterBasedOnCoverage();
            if (mProgressDialogLodingStores != null) {
                mProgressDialogLodingStores.dismiss();
            }


        }
    }

    private class FullSyncDataNow extends AsyncTask<Void, Void, Void> {


        ProgressDialog pDialogGetStores;

        public FullSyncDataNow(PersonGateMeetingTarget activity) {
            if (pDialog2STANDBY != null) {
                if (pDialog2STANDBY.isShowing()) {
                    pDialog2STANDBY.dismiss();
                }
            }
            pDialogGetStores = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            pDialogGetStores.setTitle(getText(R.string.genTermPleaseWaitNew));
            pDialogGetStores.setMessage("Submitting Target Entry Details...");
            pDialogGetStores.setIndeterminate(false);
            pDialogGetStores.setCancelable(false);
            pDialogGetStores.setCanceledOnTouchOutside(false);
            pDialogGetStores.show();


        }

        @Override

        protected Void doInBackground(Void... params) {

            int Outstat = 3;

            long syncTIMESTAMP = System.currentTimeMillis();
            Date dateobj = new Date(syncTIMESTAMP);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
            String StampEndsTime = df.format(dateobj);


            //mDataSource.open();
            String presentRoute = mDataSource.GetActiveRouteIDForDistributor();
            //mDataSource.close();


            SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss", Locale.ENGLISH);

            newfullFileName = imei + "." + presentRoute + "." + df1.format(dateobj);


            try {


                File MeijiDistributorEntryXMLFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.OrderXMLFolder);

                if (!MeijiDistributorEntryXMLFolder.exists()) {
                    MeijiDistributorEntryXMLFolder.mkdirs();

                }


            } catch (Exception e) {

                e.printStackTrace();
                if (pDialogGetStores.isShowing()) {
                    pDialogGetStores.dismiss();
                }
            }
            return null;
        }

        @Override
        protected void onCancelled() {

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialogGetStores.isShowing()) {
                pDialogGetStores.dismiss();
            }

            try {

                  /*  task2 = new SyncXMLfileData(DistributorCheckInSecondActivity.this);
                    task2.execute();*/
                if(isOnline()) {
                    String routeID = mDataSource.GetActiveRouteIDSunil();
                    Intent mMyServiceIntent = new Intent(PersonGateMeetingTarget.this, SyncJobService.class);  //is any of that needed?  idk.
                    mMyServiceIntent.putExtra("routeID", routeID);//
                    mMyServiceIntent.putExtra("storeID", "0");
                    mMyServiceIntent.putExtra("whereTo", "Regular");//
                    mMyServiceIntent.putExtra("StoreVisitCode", "NA");//
                    mMyServiceIntent.putExtra("tmpInvoicePDACode", "NA");//

                    SyncJobService.enqueueWork(PersonGateMeetingTarget.this, mMyServiceIntent);
                    showDataSaved();
                }
                else
                {
                    showNoConnAlertforLocalDataSaved();
                }

            } catch (Exception e) {

            }


        }
    }
    public void showDataSaved() {
        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(PersonGateMeetingTarget.this);
        alertDialogNoConn.setTitle(R.string.genTermInformation);
        alertDialogNoConn.setIcon(R.drawable.info_ico);
        alertDialogNoConn.setMessage("Data Saved Successfully");
        alertDialogNoConn.setNeutralButton(R.string.AlertDialogOkButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(From.equals("DayStart"))
                        {
                            Intent i = new Intent(PersonGateMeetingTarget.this, AllButtonActivity.class);
                            i.putExtra("token",  AppUtils.getToken(PersonGateMeetingTarget.this));
                            i.putExtra("fDate", fDate);
                            startActivity(i);
                            finish();
                        }
                        else {
                            finish();
                        }
                    }
                });

        AlertDialog alert = alertDialogNoConn.create();
        alert.show();
    }
    public void showNoConnAlertforLocalDataSaved() {
        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(PersonGateMeetingTarget.this);
        alertDialogNoConn.setTitle(R.string.genTermNoDataConnection);

        alertDialogNoConn.setMessage(R.string.genlocaldataMsg);
        alertDialogNoConn.setNeutralButton(R.string.AlertDialogOkButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        if(From.equals("DayStart"))
                        {
                            Intent i = new Intent(PersonGateMeetingTarget.this, AllButtonActivity.class);
                            i.putExtra("token",  AppUtils.getToken(PersonGateMeetingTarget.this));
                            i.putExtra("fDate", fDate);
                            startActivity(i);
                            finish();
                        }
                        else {
                            finish();
                        }
                    }
                });
        alertDialogNoConn.setIcon(R.drawable.error_ico);
        AlertDialog alert = alertDialogNoConn.create();
        alert.show();
    }

    @Override
    public void fnCallOldDetailsAgainstSalesPerson(int PersonID,String PersonName) {
        List<TblPersonGateMeetingTarget> tblPersonGateMeetingTargetDataSinglePerson = new ArrayList<TblPersonGateMeetingTarget>();

        tblPersonGateMeetingTargetDataSinglePerson.clear();

        tblPersonGateMeetingTargetDataSinglePerson =  stream(tblPersonGateMeetingTargetMstrDataRetriveArrayList).where(p -> p.getPersonNodeID().equals(""+PersonID)).toList();
        Intent intent=new Intent(PersonGateMeetingTarget.this,ReportPopUpSalesManTargetVsAchDayWise.class);
        intent.putExtra("PersonID",Integer.parseInt(tblPersonGateMeetingTargetDataSinglePerson.get(0).getPersonNodeID()));
        intent.putExtra("PersonName", tblPersonGateMeetingTargetDataSinglePerson.get(0).getPersonname());
        startActivity(intent);

    }


    public class NextActivityCustomDialog extends Dialog {

        RadioGroup radiogroupCloseCall;

        EditText commenttext;
        Button bt_save_and_continue;
        ImageView imgCncl;
        TextView tv_Header;

        private AppDataSource mDataSource;

        public NextActivityCustomDialog(@NonNull Context context) {
            super(context);
            mDataSource = new AppDataSource(context);

            // bt_save_and_continueOutStation.setVisibility(View.GONE);

        }


        @Override
        public void onBackPressed() {
            //super.onBackPressed();
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.popup_order_comment_layout);


            setCancelable(false);
            commenttext = (EditText) findViewById(R.id.commenttext);
            bt_save_and_continue = (Button) findViewById(R.id.bt_save_and_continue);
            tv_Header=(TextView) findViewById(R.id.tv_Header);
            imgCncl = (ImageView) findViewById(R.id.imgCncl);

            tv_Header.setText("Please provide reason for skipping");

            imgCncl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            if (!selectedOrderComment.equals("NA")) {
                commenttext.setText(selectedOrderComment.replace("&amps;", "&"));
            }


            bt_save_and_continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedOrderComment = "NA";
                    if (commenttext != null && !TextUtils.isEmpty(commenttext.getText().toString().trim())) {
                        selectedOrderComment = commenttext.getText().toString().trim().replace("&", "&amps;");
                    }
                  //  mDataSource.fnUpdateFlgOrderComment(selectedOrderComment, storeID, StoreVisitCode);
                    dismiss();

                    Intent i = new Intent(PersonGateMeetingTarget.this, AllButtonActivity.class);
                    i.putExtra("token",  AppUtils.getToken(PersonGateMeetingTarget.this));
                    i.putExtra("fDate", fDate);
                    startActivity(i);
                    finish();
                   // fnCallSubmitProcess(3);

                }
            });

        }

    }
}
