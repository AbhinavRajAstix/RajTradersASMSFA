package com.astix.rajtraderssfasmasales;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.astix.Common.CommonFunction;
import com.astix.rajtraderssfasmasales.model.TblDistributorDailyStockDetailsReport;
import com.astix.rajtraderssfasmasales.model.TblSubordinateList;
import com.astix.rajtraderssfasmasales.utils.AppUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import static br.com.zbra.androidlinq.Linq.stream;

//import com.astix.sfatju.R;

public class DistributionStockStatus extends BaseActivity implements  InterfaceRetrofit , DatePickerDialog.OnDateSetListener,InterfaceRetrofitDistributorPersonStockDetails{
    private ArrayList<TblSubordinateList> tblSubordinateLists;
    List<TblSubordinateList> tblSubordinateListsArrayListForFilter = new ArrayList<TblSubordinateList>();
    List<TblDistributorDailyStockDetailsReport> tblDistributorDailyStockDetailsReportArrayList=new ArrayList<TblDistributorDailyStockDetailsReport>();
    List<TblDistributorDailyStockDetailsReport> tblDistributorDailyStockDetailsReportArrayListForFilter=new ArrayList<TblDistributorDailyStockDetailsReport>();

    String[] TSO_Names = null;
    LinkedHashMap<String,Integer> hmapTSOIDName=new  LinkedHashMap<String,Integer>();
    ImageView imgCncl;
    TextView tv_ofDate,ofDate;
    Spinner spinner_TSOList;
    Calendar calendar ;
    DatePickerDialog datePickerDialog ;
    TextView tv_ofDatenew;
    int Year, Month, Day ;
    ProgressDialog mProgressDialog;
    ProgressDialog mProgressDialogLodingStores;
    String imei="";
    LinearLayout ll_RouteChangeStatusSection;
    String EntryDate="";

    int newSelectedRouteID=0;
    int newSelectedRouteNodeType=0;





    RecyclerView rv_main;

    DistributionShowListPersonAdapter storeListAdapter;


    ProgressDialog pDialog2;


    @Override
    public void success() {
        //showAlertRefreshSucessFully("Information", "Data Refreshed Sucessfully");
        fnloadDefaultData();
    }

public  void fnloadDefaultData()
{
    tblDistributorDailyStockDetailsReportArrayList.clear();
    tblDistributorDailyStockDetailsReportArrayList=mDataSource.fnGetAllPersontblDistributorDailyStockDetailsReport();
    tblDistributorDailyStockDetailsReportArrayListForFilter.clear();
    tblDistributorDailyStockDetailsReportArrayListForFilter.addAll(tblDistributorDailyStockDetailsReportArrayList);
    storeListAdapter = new DistributionShowListPersonAdapter(DistributionStockStatus.this, tblDistributorDailyStockDetailsReportArrayListForFilter);
    rv_main.setAdapter(storeListAdapter);
}
    // *****SYNC******

    @Override
    public void failure() {
        //showAlertException(getResources().getString(R.string.txtError), getResources().getString(R.string.txtErrRetrieving));
    }


    public void onDestroy() {
        super.onDestroy();

    }


    public void setUpVariable() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributionpersonlist);

        imei = AppUtils.getToken(DistributionStockStatus.this);
        EntryDate = getDateInMonthTextFormat();

        Intent passedvals = getIntent();

        tblSubordinateLists = new ArrayList<>();
        rv_main = findViewById(R.id.rv_main);


            mProgressDialog = new ProgressDialog(DistributionStockStatus.this);
            mProgressDialog.setTitle(getResources().getString(R.string.genTermPleaseWaitNew));
            mProgressDialog.setMessage(getResources().getString(R.string.txtRefreshingData));

            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);

            initializeAllView();
        spinner_TSOList = findViewById(R.id.spinner_TSOList);
            getTSOList();



/*
            String prsnCvrgId_NdTyp = mDataSource.fngetSalesPersonCvrgIdCvrgNdTyp();
            int CoverageNodeId = Integer.parseInt(prsnCvrgId_NdTyp.split(Pattern.quote("^"))[0]);
            int CoverageNodeType = Integer.parseInt(prsnCvrgId_NdTyp.split(Pattern.quote("^"))[1]);
            CommonInfo.flgDrctslsIndrctSls = mDataSource.fnGetflgDrctslsIndrctSlsForDSR(CoverageNodeId, CoverageNodeType);
            if (CommonInfo.hmapAppMasterFlags != null && CommonInfo.hmapAppMasterFlags.size() == 0) {
                CommonInfo.hmapAppMasterFlags = mDataSource.fnGetAppMasterFlags(CommonInfo.flgDrctslsIndrctSls);

            }*/

            setUpVariable();

        //    String routeNametobeSelectedInSpinner = mDataSource.GetActiveRouteDescr(sharedPrefCove.getInt("CoverageAreaNodeID",0), sharedPrefCove.getInt("CoverageAreaNodeType",0));
            int index = 0;
          /*  if (hmapRouteIdNameDetails != null) {

                String SelectedRouteDefaultActive = "0";
                Set set2 = hmapRouteIdNameDetails.entrySet();
                Iterator iterator = set2.iterator();
                boolean isRouteSelected = false;
                while (iterator.hasNext()) {
                    Map.Entry me2 = (Map.Entry) iterator.next();
                    if(me2.getKey().toString().contains("-"))
                    {
                         if (routeNametobeSelectedInSpinner.equals(me2.getKey().toString().split(Pattern.quote("-"))[1])) {
                        isRouteSelected = true;
                        SelectedRouteDefaultActive = me2.getValue().toString().split(Pattern.quote("_"))[0];
                        selectedRouteIDForFilter = Integer.parseInt(SelectedRouteDefaultActive);
                        rID = SelectedRouteDefaultActive;
                       *//* if(mDataSource.fnChkRouteApprovedOrNot(Integer.parseInt(me2.getValue().toString().split(Pattern.quote("_"))[0]),Integer.parseInt(me2.getValue().toString().split(Pattern.quote("_"))[1]))==1)
                        {
                            SpannableString spanString = new SpannableString("Approved");
                            spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(StoreSelection.this, android.R.color.holo_green_dark)), 0, spanString.length(), 0);
                           // spanString.setSpan(new StrikethroughSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv_RouteStatus.setText(spanString);
                            but_clickForRouteApproval.setVisibility(View.GONE);
                        }
                         else if(mDataSource.fnChkRouteApprovedOrNot(Integer.parseInt(me2.getValue().toString().split(Pattern.quote("_"))[0]),Integer.parseInt(me2.getValue().toString().split(Pattern.quote("_"))[1]))==0)
                             {

                                 SpannableString spanString = new SpannableString("Not Approved");
                                 spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(StoreSelection.this, android.R.color.holo_red_dark)), 0, spanString.length(), 0);
                                // spanString.setSpan(new StrikethroughSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                 tv_RouteStatus.setText(spanString);
                                 but_clickForRouteApproval.setVisibility(View.VISIBLE);

                             }*//*
                        //Do Nothing
                        break;
                    }
                    }

                    index = index + 1;
                }
                if (isRouteSelected) {
                    spinner_TSOList.setSelection(index);
                    ll_RouteChangeStatusSection.setVisibility(View.GONE);
                } else {
                    spinner_TSOList.setSelection(0);
                    ll_RouteChangeStatusSection.setVisibility(View.GONE);

                    // spinner_RouteChangeListOptions.setSelection(0);
                }

                if (mDataSource.fnChkFlgTodayRoute(SelectedRouteDefaultActive) == 0) {
                    // ll_RouteChangeReason.setVisibility(View.VISIBLE);//Original
                    ll_RouteChangeReason.setVisibility(View.GONE);
                } else {
                    ll_RouteChangeReason.setVisibility(View.GONE);
                }
            }*/

        try {
            CommonFunction.getDistributionPersonDailyStockstatus(DistributionStockStatus.this, "Please wait loading data.", 0,EntryDate,0,0,1);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void initializeAllView() {
        imgCncl=findViewById(R.id.imgCncl);
        imgCncl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_ofDate= findViewById(R.id.tv_ofDate);
        ofDate= findViewById(R.id.ofDate);
        ofDate.setText(getDateInMonthTextFormat());
        ofDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //fromDateBool=true;
                tv_ofDatenew = (TextView) arg0;
                calendar = Calendar.getInstance();
                Year = calendar.get(Calendar.YEAR) ;
                Month = calendar.get(Calendar.MONTH);
                Day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = DatePickerDialog.newInstance(DistributionStockStatus.this, Year, Month, Day);

                datePickerDialog.setThemeDark(false);

                datePickerDialog.showYearPickerFirst(false);
                Calendar calendarForSetDate = Calendar.getInstance();
                calendarForSetDate.setTimeInMillis(System.currentTimeMillis());

                // calendar.setTimeInMillis(System.currentTimeMillis()+24*60*60*1000);
                //YOU can set min or max date using this code
                 datePickerDialog.setMaxDate(Calendar.getInstance());
                // datePickerDialog.setMinDate(calendar);
               // datePickerDialog.setMinDate(calendarForSetDate);

                datePickerDialog.setAccentColor(Color.parseColor("#544f88"));

                datePickerDialog.setTitle("SELECT DATE");
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");



            }});


        //  ed_search = findViewById(R.id.ed_search);

     /*   ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().trim().length() == 0 && selectedRouteIDForFilter != 0) {
                    fnFilterBasedOnRoute();

                } else {
                    tblStoreListMasterDataRetriveArrayListForFilter.clear();

                    tblStoreListMasterDataRetriveArrayListForFilter = stream(tblStoreListMasterDataRetriveArrayList).where(p -> p.getStoreName().toLowerCase().contains(s.toString().trim().toLowerCase())).orderBy(p -> p.getRouteID()).toList();// && p.getRouteID() == selectedRouteIDForFilter

                    ll_BrandP3MMTDSection.setVisibility(View.VISIBLE);
                    ll_LastVisitDate.setVisibility(View.VISIBLE);

                    fnMarkFirstRouteNameRowVisible();
                    storeListAdapter = new StoreListAdapterAdapter(DistributionStockStatus.this, tblStoreListMasterDataRetriveArrayListForFilter, userDate, pickerDate,0);
                    rv_main.setAdapter(storeListAdapter);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

    }



    public void fnFilterBasedOnRoute() {

      //  tblStoreListMasterDataRetriveArrayListForFilter.clear();

       /* String stext = ed_search.getText().toString();
        if (!TextUtils.isEmpty(ed_search.getText())) {
            stext = ed_search.getText().toString();
        }*/


     /*   String finalStext = stext;
        if (selectedRouteIDForFilter == 0) {
            tblStoreListMasterDataRetriveArrayListForFilter = stream(tblStoreListMasterDataRetriveArrayList).where(p -> p.getStoreName().toLowerCase().contains(finalStext.toLowerCase())).orderBy(p -> p.getRouteID()).toList();
        } else {
            tblStoreListMasterDataRetriveArrayListForFilter = stream(tblStoreListMasterDataRetriveArrayList).where(p -> p.getStoreName().toLowerCase().contains(finalStext.toLowerCase()) && p.getRouteID() == selectedRouteIDForFilter).orderBy(p -> p.getRouteID()).toList();
        }*/

       // fnMarkFirstRouteNameRowVisible();
       /* storeListAdapter = new StoreListAdapterAdapter(DistributionStockStatus.this, tblStoreListMasterDataRetriveArrayListForFilter, userDate, pickerDate,0);
        rv_main.setAdapter(storeListAdapter);*/
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        imei = AppUtils.getToken(DistributionStockStatus.this);


    }



    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

    }


    private void getTSOList() {
        tblSubordinateLists = mDataSource.getSubOrdinateList();
        if(tblSubordinateLists!=null && tblSubordinateLists.size()>0)
        {
            TSO_Names = new String[tblSubordinateLists.size()+1];
            TSO_Names[0] = "Select TSI";
            hmapTSOIDName.put("Select TSI",0);
            int i=0;
            for(TblSubordinateList tblSubordinateList:tblSubordinateLists)
            {
                TSO_Names[i + 1] = tblSubordinateList.getPersonName();// + "(" + tblSubordinateList.getCoverageArea() + ")"
                hmapTSOIDName.put(tblSubordinateList.getPersonName(),tblSubordinateList.getParentPersonID());// + "(" + tblSubordinateList.getCoverageArea() + ")"
                i++;
            }
        }
        else
        {
            TSO_Names = new String[1];
            TSO_Names[0] = "Select TSI";
            hmapTSOIDName.put("Select TSI",0);
        }
        ArrayAdapter adapterRouteList = new ArrayAdapter(this, R.layout.spinner_item_route_store_selection, TSO_Names);
        adapterRouteList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_TSOList.setAdapter(adapterRouteList);
        spinner_TSOList.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int selectedTSOID=hmapTSOIDName.get(arg0.getItemAtPosition(arg2).toString());
                String SelectedTSOName=arg0.getItemAtPosition(arg2).toString();

                if (selectedTSOID == 0) {
                    tblDistributorDailyStockDetailsReportArrayListForFilter.addAll(tblDistributorDailyStockDetailsReportArrayList);
                } else {
                    tblDistributorDailyStockDetailsReportArrayListForFilter = stream(tblDistributorDailyStockDetailsReportArrayList).where(p -> p.getPersonName().equals(SelectedTSOName)).toList();
                }
                storeListAdapter = new DistributionShowListPersonAdapter(DistributionStockStatus.this, tblDistributorDailyStockDetailsReportArrayListForFilter);
                rv_main.setAdapter(storeListAdapter);

             /*   ed_search.setText("");
                ed_search.clearFocus();
                fnFilterBasedOnRoute();
*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*SharedPreferences sharedPrefReportForReport = getSharedPreferences(CommonInfo.Preference, MODE_PRIVATE);
        hmapRouteIdNameDetails = mDataSource.fetch_Route_List(sharedPrefReportForReport.getInt("CoverageAreaNodeID",0));

        int index = 0;
        if (hmapRouteIdNameDetails != null) {
            Route_names = new String[hmapRouteIdNameDetails.size()];
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(hmapRouteIdNameDetails);
            Set set2 = map.entrySet();
            Iterator iterator = set2.iterator();
            while (iterator.hasNext()) {
                Map.Entry me2 = (Map.Entry) iterator.next();
                Route_names[index] = me2.getKey().toString();
                index = index + 1;
            }
        }
*/

    }




    public void fnMarkFirstRouteNameRowVisible() {
      /*  Iterator<TblStoreListMasterDataRetrive> crunchifyIteratorForSeacrh = tblStoreListMasterDataRetriveArrayListForFilter.iterator();
        if (tblStoreListMasterDataRetriveArrayListForFilter != null && tblStoreListMasterDataRetriveArrayListForFilter.size() > 0) {
            int catid = 0;
            Set catSet = new HashSet();
            while (crunchifyIteratorForSeacrh.hasNext()) {
                TblStoreListMasterDataRetrive tblStoreProductMappingRecordForSeach = crunchifyIteratorForSeacrh.next();
                if (!catSet.contains(tblStoreProductMappingRecordForSeach.getRouteName())) {
                    tblStoreProductMappingRecordForSeach.setFlgShowHeader(1);
                    catSet.add(tblStoreProductMappingRecordForSeach.getRouteName());
                } else {
                    tblStoreProductMappingRecordForSeach.setFlgShowHeader(0);
                }
            }
        }
*/
    }
@Override
	public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
		String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		String mon=MONTHS[monthOfYear];

		tv_ofDatenew.setText(dayOfMonth+"-"+mon+"-"+year);
        EntryDate=tv_ofDatenew.getText().toString();
    try {
        CommonFunction.getDistributionPersonDailyStockstatus(DistributionStockStatus.this, "Please wait loading data.", 0,EntryDate,0,0,1);


    } catch (Exception e) {
        e.printStackTrace();
    }
	}


    @Override
    public void successPersonStockDetails(String PersonName,String DistributorName) {



        Intent intent = new Intent(DistributionStockStatus.this, DistributorStockStatusSKUWise.class);
        intent.putExtra("PersonName", PersonName);
        intent.putExtra("DistributorName", DistributorName);
        intent.putExtra("EntryDate", EntryDate);
        startActivity(intent);

    }

    @Override
    public void failurePersonStockDetails() {

    }

    @Override
    public void fnFilterDetailsForDistributor(int DistNodeID, String PersonName,String LastEntryDate) {
        //TblSubordinateList tblSubordinateList=stream(tblSubordinateLists).where(p->p.)
        List<TblDistributorDailyStockDetailsReport> tblDistributorDailyStockDetailsReportCrnList = stream(tblDistributorDailyStockDetailsReportArrayList).where(p -> p.getCustomerNodeID()==DistNodeID && p.getPersonName().equals(PersonName)).toList();
        if(tblDistributorDailyStockDetailsReportCrnList!=null && tblDistributorDailyStockDetailsReportCrnList.size()>0)
        {
            TblDistributorDailyStockDetailsReport tblDistributorDailyStockDetailsReport = stream(tblDistributorDailyStockDetailsReportArrayList).where(p -> p.getCustomerNodeID()==DistNodeID && p.getPersonName().equals(PersonName)).first();
            try {
                EntryDate=LastEntryDate;
                CommonFunction.getDistributionPersonDailyStockSKUList(DistributionStockStatus.this, "Please wait loading data.", 0,LastEntryDate,tblDistributorDailyStockDetailsReport.getCustomerNodeID(),tblDistributorDailyStockDetailsReport.getCustomerNodeType(),2,tblDistributorDailyStockDetailsReport.getPersonName(),tblDistributorDailyStockDetailsReport.getCustomer());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            showAlertSingleButtonError("No Distributor Mapped against this TSI");
        }

    }
}
