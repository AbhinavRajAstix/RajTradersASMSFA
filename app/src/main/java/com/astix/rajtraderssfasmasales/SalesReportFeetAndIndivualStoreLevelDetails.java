package com.astix.rajtraderssfasmasales;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.allana.truetime.TimeUtils;
import com.astix.Common.CommonFunction;
import com.astix.rajtraderssfasmasales.customwidgets.ViewGenerator;
import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.model.TblSubordinateList;
import com.astix.rajtraderssfasmasales.model.TblSummaryReportDetails;
import com.astix.rajtraderssfasmasales.reports.models.TblRptDistribution;
import com.astix.rajtraderssfasmasales.reports.models.TblRptManDays;
import com.astix.rajtraderssfasmasales.reports.models.TblRptPrimaryVol;
import com.astix.rajtraderssfasmasales.reports.models.TblRptSalesVersionIndivisual;
import com.astix.rajtraderssfasmasales.reports.models.TblRptSecVol;
import com.astix.rajtraderssfasmasales.utils.AppUtils;
import com.astix.rajtraderssfasmasales.utils.PreferenceManager;
import com.astix.rajtraderssfasmasales.utils.SPConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static br.com.zbra.androidlinq.Linq.stream;


public class SalesReportFeetAndIndivualStoreLevelDetails extends BaseActivity implements  MultipleInterfaceRetrofit {

    String imei="";
    public Context mActitiy;

    private PreferenceManager mPreferenceManager;
    private AppDataSource mDataSource;

    ArrayList<TblSummaryReportDetails> mTblSummaryReportDetails;

    int ValueType=0;
    int PeriodType=0;
    String DetailsData="";
    String PageTitle="";
    String PageHeading="";

    int PersonNodeID=0;
    int PersonNodeType=0;

    int SectionID=0;
    int CoverageAreaNodeID=0;
    int CoverageAreaNodeType=0;



    ProgressDialog mProgressDialogReport;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.visit_date_tv)
    TextView visitDateTv;

    @BindView(R.id.tv_VD)
    TextView tv_VD;

    @BindView(R.id.btn_bck)
    Button btn_bck;

    ExpandableListView listViewSpokeDetail;


    public String mDateForFilter="";
    ExpandableSpokeWiseDateVisitValueAdapter expandableListAdapter;

    LinkedHashMap<String, List<String>> hampBeatWiseSpokeVistedonParticularDate=new LinkedHashMap<>();

    List<String> spokelist=new ArrayList<>();
    LinkedHashMap<String, List<String>> hashMap=new LinkedHashMap<>();

    private Unbinder mUnbinder;


    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            return true;

        }
        if(keyCode==KeyEvent.KEYCODE_HOME)
        {

        }
        if(keyCode==KeyEvent.KEYCODE_MENU)
        {
            return true;
        }
        if(keyCode== KeyEvent.KEYCODE_SEARCH)
        {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    //private MyService mMyService;
    public Context getCtx() {
        return mActitiy;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoke_visit_dates);
        mActitiy = this;

        mUnbinder = ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imei = AppUtils.getToken(SalesReportFeetAndIndivualStoreLevelDetails.this);

        mDataSource = AppDataSource.getInstance(mActitiy);
        mPreferenceManager = PreferenceManager.getInstance(mActitiy);

        Intent intent = getIntent();
        DetailsData= intent.getStringExtra("DetailsData");
        PeriodType=Integer.parseInt( DetailsData.split(Pattern.quote("^"))[0]);
        ValueType= Integer.parseInt( DetailsData.split(Pattern.quote("^"))[1]);
        PageTitle =DetailsData.split(Pattern.quote("^"))[2];
        PageHeading =DetailsData.split(Pattern.quote("^"))[3];
      /*  PersonNodeID=Integer.parseInt( DetailsData.split(Pattern.quote("^"))[4]);
        PersonNodeType=Integer.parseInt( DetailsData.split(Pattern.quote("^"))[5]);*/

        SectionID=Integer.parseInt( DetailsData.split(Pattern.quote("^"))[6]);
        CoverageAreaNodeID=Integer.parseInt( DetailsData.split(Pattern.quote("^"))[7]);
        CoverageAreaNodeType=Integer.parseInt( DetailsData.split(Pattern.quote("^"))[8]);




        mToolbar.setTitle(PageTitle);
        tv_VD.setVisibility(View.GONE);
        visitDateTv.setText(PageHeading);

        btn_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //fnGetLocalData();


        mProgressDialogReport = new ProgressDialog(mActitiy);
        mProgressDialogReport.setTitle("Please wait fetching data.");//context.getResources().getString(R.string.Loading));
        mProgressDialogReport.setMessage(mActitiy.getResources().getString(R.string.RetrivingDataMsg));
        mProgressDialogReport.setIndeterminate(true);
        mProgressDialogReport.setCancelable(false);
        mProgressDialogReport.show();

        /*TblSubordinateList mDSMArrayListRecord=stream(tblSubordinateLists).where(p->p.getPersonName().toLowerCase().trim().equals(PersonNameToShowReport.toLowerCase().trim())).first();
        PersonNodeId =""+ mDSMArrayListRecord.getPersonNodeID();

        PersonNodeType = ""+ mDSMArrayListRecord.getParentPersonType();*/

        MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
        CommonFunction.GetSalesSummaryStoreWiseReportDetailDownload reportDownloadAsyncTask = new CommonFunction.GetSalesSummaryStoreWiseReportDetailDownload(SalesReportFeetAndIndivualStoreLevelDetails.this, mProgressDialogReport, interfaceRetrofit, ValueType,PeriodType,PersonNodeID,PersonNodeType,0,SectionID,CoverageAreaNodeID,CoverageAreaNodeType);
        AppUtils.executeAsyncTask(reportDownloadAsyncTask);

    }
    public void fnGetLocalData()
    {
        /*tblSubordinateLists = new ArrayList<>();
        mDSMArrayList = new ArrayList<>();



        tblSubordinateLists = mDataSource.getSubOrdinateList();

        if(mDSMArrayList!=null && mDSMArrayList.size()>0)
            mDSMArrayList.clear();

        // ASM PersonNodeType = 210
        // SO PersonNodeType=220
        // DSM/DBR PersonNodeType=230/240

        for (TblSubordinateList tblSubordinateList : tblSubordinateLists) {
            if (tblSubordinateList.getPersonNodeType() == 220 || tblSubordinateList.getPersonNodeType() == 240 || tblSubordinateList.getPersonNodeType() == 230) {
                mDSMArrayList.add(tblSubordinateList);
            }
        }
        yesterdayDate = mPreferenceManager.getStringValue(SPConstants.YESTERDAY_DATE, TimeUtils.getNetworkDate(mActitiy,TimeUtils.DATE_FORMAT));*/
    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void success(int calledfor) {

        listViewSpokeDetail = (ExpandableListView)findViewById(R.id.expandableListView);
        listViewSpokeDetail.setGroupIndicator(null);

       /* int sectionToShow=calledfor;
        String areaName ="";
        if(calledfor==1 || calledfor==2 || calledfor==5)
        {
            if(calledfor==1)
            {
                areaName="All Sales Person";
            }
            else if(calledfor==5)
            {
                areaName="OverAll";
            }
            else {
                areaName = mDataSource.getSalesAreaASM();
            }
        }
        else
        {
            TblSubordinateList mDSMArrayListRecord=stream(tblSubordinateLists).where(p->p.getPersonName().toLowerCase().trim().equals(PersonNameToShowReport.toLowerCase().trim())).first();
            areaName=mDSMArrayListRecord.getCoverageArea();
        }

        String yesterdayDate = mPreferenceManager.getStringValue(SPConstants.YESTERDAY_DATE, "");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvDescr.setText(Html.fromHtml("Here is a brief summary of the performance of <b>" + areaName + "</b> till <b>" + yesterdayDate + "</b>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvDescr.setText(Html.fromHtml("Here is a brief summary of the performance of <b>" + areaName + "</b> till <b>" + yesterdayDate + "</b>"));
        }*/


        GetData getData = new GetData();
        AppUtils.executeAsyncTask(getData);
    }

    @Override
    public void failure(int flgCalledFrom) {

    }


    private class GetData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
          //  mTblSummaryReportDetails=mDataSource.gettblSummaryReportDetails();
            hampBeatWiseSpokeVistedonParticularDate=mDataSource.fnGettblSummaryReportDetailsDistinctRouteAndStores();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            init();
        }
    }


    private void init() {


        for(Map.Entry<String, List<String>> map: hampBeatWiseSpokeVistedonParticularDate.entrySet())
        {

            String BeatName=map.getKey().split(Pattern.quote("^"))[1];

            List<String> arrStores=map.getValue();
            List<String> storeValue=new ArrayList<>();
            for(int j=0;j<arrStores.size();j++)
            {


                String spokename=arrStores.get(j);

                storeValue.add(spokename);


            }
            spokelist.add(BeatName);
            hashMap.put(BeatName ,storeValue);




        }


        if(mProgressDialogReport!=null && mProgressDialogReport.isShowing())
        {
            mProgressDialogReport.dismiss();
        }
        initRecyclers();
        /***** Secondary Volume Data Ends ********************/

    }

    private void initRecyclers() {
      /*  mVisitedSpokeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mVisitedSpokeRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mVisitedSpokeRecyclerView.setAdapter(new DateWiseSpokeAdapter(this,visitedSpokesProfile));*/

        expandableListAdapter = new ExpandableSpokeWiseDateVisitValueAdapter(this, spokelist, hashMap);
        listViewSpokeDetail.setAdapter(expandableListAdapter);

        for (int i = 0; i < expandableListAdapter.getGroupCount(); i++){
            listViewSpokeDetail.expandGroup(i);
        }

     /*   mNotVisitedSpokeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNotVisitedSpokeRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mNotVisitedSpokeRecyclerView.setAdapter(new DateWiseSpokeAdapter(this,visitedSpokesProfile));*/
    }
}
