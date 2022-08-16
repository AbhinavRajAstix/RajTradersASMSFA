package com.astix.rajtraderssfasmasales.reports;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.allana.truetime.TimeUtils;
import com.astix.Common.CommonFunction;
import com.astix.rajtraderssfasmasales.AllButtonActivity;
import com.astix.rajtraderssfasmasales.DayStartActivity;
import com.astix.rajtraderssfasmasales.DistributorCheckInSecondActivity;
import com.astix.rajtraderssfasmasales.MultipleInterfaceRetrofit;
import com.astix.rajtraderssfasmasales.PersonGateMeetingTarget;
import com.astix.rajtraderssfasmasales.ProductEntryForm;
import com.astix.rajtraderssfasmasales.R;
import com.astix.rajtraderssfasmasales.ReportPopUpIndivisualSalesMan;
import com.astix.rajtraderssfasmasales.RouteApprovalOTPScreen;
import com.astix.rajtraderssfasmasales.SalesReportFeetAndIndivualStoreLevelDetails;
import com.astix.rajtraderssfasmasales.SpokeVisitDates;
import com.astix.rajtraderssfasmasales.StoreSelection;
import com.astix.rajtraderssfasmasales.customwidgets.ViewGenerator;
import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.model.TblSubordinateList;
import com.astix.rajtraderssfasmasales.reports.models.TblRptDistribution;
import com.astix.rajtraderssfasmasales.reports.models.TblRptManDays;
import com.astix.rajtraderssfasmasales.reports.models.TblRptPrimaryVol;
import com.astix.rajtraderssfasmasales.reports.models.TblRptSalesVersionIndivisual;
import com.astix.rajtraderssfasmasales.reports.models.TblRptSecVol;
import com.astix.rajtraderssfasmasales.utils.AppUtils;
import com.astix.rajtraderssfasmasales.utils.IntentConstants;
import com.astix.rajtraderssfasmasales.utils.PreferenceManager;
import com.astix.rajtraderssfasmasales.utils.SPConstants;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;

import static br.com.zbra.androidlinq.Linq.stream;

/**
 * A simple {@link Fragment} subclass.
 */
public class YesterdayReportFragment extends Fragment {
    public ArrayList<TblSubordinateList> mDSMArrayList;
    public ArrayList<TblSubordinateList> tblSubordinateLists;
    public int sectionToShow=1;
    String[] DSM_Names = null;
    String yesterdayDate="";

    private int coverageAreaNodeID= 0;
    private int coverageAreaNodeType = 0;
    private int personNodeType = 0;
    public int flgSelf = 1;
    private int flgReportLevel   = 1;
    public ProgressDialog mProgressDialogReport;
    public String From="";


    @BindView(R.id.radioGrp)
    RadioGroup radioGroup;

    @BindView(R.id.rb_byRoute)
    RadioButton rb_byRoute;

    @BindView(R.id.rb_byOverall)
    RadioButton rb_byOverall;

    @BindView(R.id.rb_byCat)
    RadioButton rb_byCat;

    @BindView(R.id.spinner_for_filterTSI)
    Spinner spinner_for_filterTSI;

    @BindView(R.id.tvGreeting)
    TextView tvGreeting;
    @BindView(R.id.ll_Parent)
    LinearLayout ll_Parent;
    @BindView(R.id.tvPersonName)
    TextView tvPersonName;
    @BindView(R.id.tvDescr)
    TextView tvDescr;
    @BindView(R.id.bt_Next)
    Button btNext;
    ArrayList<TblRptDistribution> mTblDistributionArrayList;
    ArrayList<TblRptDistribution> mTblDistribution2xArrayList;
    ArrayList<TblRptSalesVersionIndivisual> mTblRptSalesVersionIndivisualArrayList;
    ArrayList<TblRptSecVol> mTblRptSecVolArrayList;
    ArrayList<TblRptPrimaryVol> mTblRptPrimaryVolArrayList;
    ArrayList<TblRptManDays> mTblRptManDayArrayList;
    public View view;
    private Activity mActitiy;
    private PreferenceManager mPreferenceManager;
    private AppDataSource mDataSource;
    public YesterdayReportFragment() {
        // Required empty public constructor
    }

    public static YesterdayReportFragment newInstance(Bundle args) {

        YesterdayReportFragment fragment = new YesterdayReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) mActitiy.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view != null) {
            if (view.getParent() != null)

                ((ViewGroup) view.getParent()).removeView(view);
            ll_Parent.removeAllViews();
            GetData getData = new GetData();
            AppUtils.executeAsyncTask(getData);
            return view;
        }
        view = inflater.inflate(R.layout.fragment_yesterday_report, container, false);
        ButterKnife.bind(this, view);
        mActitiy = getActivity();


        new AppUtils().callParentMethod(mActitiy);
        mDataSource = AppDataSource.getInstance(mActitiy);
        mPreferenceManager = PreferenceManager.getInstance(mActitiy);

        tvGreeting.setText(AppUtils.getGreetingFortheDay() + ", ");
        tvPersonName.setText(mPreferenceManager.getStringValue(AppUtils.PERSON_NAME, ""));


        flgSelf=1;
        String CovAreaNodeIDType=mDataSource.fngetSalesPersonCvrgIdCvrgNdTyp();
        coverageAreaNodeID= Integer.parseInt(CovAreaNodeIDType.split(Pattern.quote("^"))[0]);
        coverageAreaNodeType= Integer.parseInt(CovAreaNodeIDType.split(Pattern.quote("^"))[1]);
        flgReportLevel   = 1;
        spinner_for_filterTSI.setVisibility(View.GONE);


        tblSubordinateLists = new ArrayList<>();
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

        if (mDSMArrayList != null && mDSMArrayList.size() > 0) {
            DSM_Names = new String[mDSMArrayList.size() + 1];
            DSM_Names[0] = "Select TSI";
          //  DSM_Names[1] = "OverAll";
            for (int i = 0; i < mDSMArrayList.size(); i++) {
                DSM_Names[i + 1] = mDSMArrayList.get(i).getPersonName();// + "(" + mDSMArrayList.get(i).getCoverageArea() + ")";
            }
        } else {
            DSM_Names = new String[1];
            DSM_Names[0] = "Select TSI";
        }

        // spinner_for_filterTSI.setSelection(0);
        radioGroup.check(R.id.rb_byOverall);
        yesterdayDate = mPreferenceManager.getStringValue(SPConstants.YESTERDAY_DATE, TimeUtils.getNetworkDate(mActitiy,TimeUtils.DATE_FORMAT));
        /*String PersonNodeIdAndNodeType = mDataSource.fngetSalesPersonMstrData();

        int PersonNodeId = 0;

        int PersonNodeType = 0;
        if (!PersonNodeIdAndNodeType.equals("0^0")) {
            PersonNodeId = Integer.parseInt(PersonNodeIdAndNodeType.split(Pattern.quote("^"))[0]);
            PersonNodeType = Integer.parseInt(PersonNodeIdAndNodeType.split(Pattern.quote("^"))[1]);
        }*/


        String PersonNodeId ="";

        String PersonNodeType = "";

        String PersonNodeIdAndNodeType = mDataSource.fngetSalesPersonMstrData();
        if (!PersonNodeIdAndNodeType.equals("0^0")) {
            PersonNodeId =PersonNodeIdAndNodeType.split(Pattern.quote("^"))[0];
            PersonNodeType =PersonNodeIdAndNodeType.split(Pattern.quote("^"))[1];
        }


        getRadioGroupClickReportForAgainstSelfOrTSI();

        mProgressDialogReport = new ProgressDialog(mActitiy);
        mProgressDialogReport.setTitle("Please wait fetching data.");//context.getResources().getString(R.string.Loading));
        mProgressDialogReport.setMessage(mActitiy.getResources().getString(R.string.RetrivingDataMsg));
        mProgressDialogReport.setIndeterminate(true);
        mProgressDialogReport.setCancelable(false);
        mProgressDialogReport.show();

      /*  MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
        CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,1);
        AppUtils.executeAsyncTask(reportDownloadAsyncTask);*/

        MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
        CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,5,0,0);
        AppUtils.executeAsyncTask(reportDownloadAsyncTask);


       /* String areaName = mDataSource.getSalesArea();
        String yesterdayDate = mPreferenceManager.getStringValue(SPConstants.YESTERDAY_DATE, "");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvDescr.setText(Html.fromHtml("Here is a brief summary of the performance of <b>" + areaName + "</b> on <b>" + yesterdayDate + "</b>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvDescr.setText(Html.fromHtml("Here is a brief summary of the performance of <b>" + areaName + "</b> on <b>" + yesterdayDate + "</b>"));
        }

        if (getArguments() != null) {
            int flgShowNextButton = getArguments().getInt(IntentConstants.flgShowNextButton);
            if (flgShowNextButton == 0)
                btNext.setVisibility(View.GONE);
            else
                btNext.setVisibility(View.VISIBLE);
        }

        GetData getData = new GetData();
        AppUtils.executeAsyncTask(getData);*/
        return view;
    }

    public void getRadioGroupClickReportForAgainstSelfOrTSI()
    {

      /*  ArrayAdapter arrayAdapter1 = new ArrayAdapter(mActitiy, android.R.layout.simple_spinner_dropdown_item,tsi_names);
        spinner_for_filterTSI.setAdapter(arrayAdapter1);
*/

        ArrayAdapter adapterCategory = new ArrayAdapter(mActitiy, android.R.layout.simple_spinner_item, DSM_Names);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_for_filterTSI.setAdapter(adapterCategory);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = group.findViewById(checkedId);
                int index = group.indexOfChild(button);

                if(button.getText().equals("By Salesmen"))
                {
                    flgSelf=1;
                    String CovAreaNodeIDType=mDataSource.fngetSalesPersonCvrgIdCvrgNdTyp();
                    coverageAreaNodeID= Integer.parseInt(CovAreaNodeIDType.split(Pattern.quote("^"))[0]);
                    coverageAreaNodeType= Integer.parseInt(CovAreaNodeIDType.split(Pattern.quote("^"))[1]);
                    flgReportLevel   = 1;
                    spinner_for_filterTSI.setVisibility(View.GONE);
                    mProgressDialogReport = new ProgressDialog(mActitiy);
                    mProgressDialogReport.setTitle("Please wait fetching data.");//context.getResources().getString(R.string.Loading));
                    mProgressDialogReport.setMessage(mActitiy.getResources().getString(R.string.RetrivingDataMsg));
                    mProgressDialogReport.setIndeterminate(true);
                    mProgressDialogReport.setCancelable(false);
                    mProgressDialogReport.show();


                    String PersonNodeIdAndNodeType = mDataSource.fngetSalesPersonMstrData();

                    int PersonNodeId = 0;

                    int PersonNodeType = 0;
                    if (!PersonNodeIdAndNodeType.equals("0^0")) {
                        PersonNodeId = Integer.parseInt(PersonNodeIdAndNodeType.split(Pattern.quote("^"))[0]);
                        PersonNodeType = Integer.parseInt(PersonNodeIdAndNodeType.split(Pattern.quote("^"))[1]);
                    }



                    MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
                  //  CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,1,coverageAreaNodeID,coverageAreaNodeType);
                    CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,1,0,0);
                        AppUtils.executeAsyncTask(reportDownloadAsyncTask);
                }

                if(button.getText().equals("OverAll"))
                {
                    spinner_for_filterTSI.setVisibility(View.GONE);
                    mProgressDialogReport = new ProgressDialog(mActitiy);
                    mProgressDialogReport.setTitle("Please wait fetching data.");//context.getResources().getString(R.string.Loading));
                    mProgressDialogReport.setMessage(mActitiy.getResources().getString(R.string.RetrivingDataMsg));
                    mProgressDialogReport.setIndeterminate(true);
                    mProgressDialogReport.setCancelable(false);
                    mProgressDialogReport.show();
                       /* coverageAreaNodeID=tblTSIDetails.get(spinner_for_filterTSI.getSelectedItemPosition() - 1).getCovAreaNodeID();
                        coverageAreaNodeType=tblTSIDetails.get(spinner_for_filterTSI.getSelectedItemPosition() - 1).getCovAreaNodeType();*/


                    String PersonNodeId ="";

                    String PersonNodeType = "";

                            String PersonNodeIdAndNodeType = mDataSource.fngetSalesPersonMstrData();
                            if (!PersonNodeIdAndNodeType.equals("0^0")) {
                                PersonNodeId =PersonNodeIdAndNodeType.split(Pattern.quote("^"))[0];
                                PersonNodeType =PersonNodeIdAndNodeType.split(Pattern.quote("^"))[1];
                            }

                            MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
                            CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,5,0,0);
                            AppUtils.executeAsyncTask(reportDownloadAsyncTask);





                }
                if(button.getText().equals("Individual TSI"))
                {
                    String CovAreaNodeIDTypeTSI=mDataSource.fngetSalesPersonCvrgIdCvrgNdTyp();
                    spinner_for_filterTSI.setVisibility(View.VISIBLE);
                    flgSelf=2;
                    if(spinner_for_filterTSI.getSelectedItemPosition()!=0)
                    {

                        mProgressDialogReport = new ProgressDialog(mActitiy);
                        mProgressDialogReport.setTitle("Please wait fetching data.");//context.getResources().getString(R.string.Loading));
                        mProgressDialogReport.setMessage(mActitiy.getResources().getString(R.string.RetrivingDataMsg));
                        mProgressDialogReport.setIndeterminate(true);
                        mProgressDialogReport.setCancelable(false);
                        mProgressDialogReport.show();
                       /* coverageAreaNodeID=tblTSIDetails.get(spinner_for_filterTSI.getSelectedItemPosition() - 1).getCovAreaNodeID();
                        coverageAreaNodeType=tblTSIDetails.get(spinner_for_filterTSI.getSelectedItemPosition() - 1).getCovAreaNodeType();*/


                        String PersonNodeId ="";

                        String PersonNodeType = "";


                      /*  if(spinner_for_filterTSI.getSelectedItem().equals("OverAll"))
                        {
                            String PersonNodeIdAndNodeType = mDataSource.fngetSalesPersonMstrData();
                            if (!PersonNodeIdAndNodeType.equals("0^0")) {
                                PersonNodeId =PersonNodeIdAndNodeType.split(Pattern.quote("^"))[0];
                                PersonNodeType =PersonNodeIdAndNodeType.split(Pattern.quote("^"))[1];
                            }

                            MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
                            CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,2);
                            AppUtils.executeAsyncTask(reportDownloadAsyncTask);
                        }
                        else
                        {*/
                            TblSubordinateList mDSMArrayListRecord=stream(tblSubordinateLists).where(p->p.getPersonName().equals(spinner_for_filterTSI.getSelectedItem())).first();
                             PersonNodeId =""+ mDSMArrayListRecord.getPersonNodeID();

                             PersonNodeType = ""+ mDSMArrayListRecord.getParentPersonType();
                            MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
                            CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,0,mDSMArrayListRecord.getCoverageAreaNodeID(),mDSMArrayListRecord.getCoverageAreaNodeType());
                            AppUtils.executeAsyncTask(reportDownloadAsyncTask);
                       // }




                    }
                    else
                    {
                        ll_Parent.removeAllViews();
                    }

                }

            }
        });



        spinner_for_filterTSI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flgSelf=2;

                if(position==0)
                {
                    coverageAreaNodeID=0;
                    coverageAreaNodeType=0;

                }
                else {
                   /* coverageAreaNodeID = tblTSIDetails.get(position - 1).getCovAreaNodeID();
                    coverageAreaNodeType = tblTSIDetails.get(position - 1).getCovAreaNodeType();*/
                    mProgressDialogReport = new ProgressDialog(mActitiy);
                    mProgressDialogReport.setTitle("Please wait fetching data.");//context.getResources().getString(R.string.Loading));
                    mProgressDialogReport.setMessage(mActitiy.getResources().getString(R.string.RetrivingDataMsg));
                    mProgressDialogReport.setIndeterminate(true);
                    mProgressDialogReport.setCancelable(false);
                    mProgressDialogReport.show();


                  /*  TblSubordinateList mDSMArrayListRecord=stream(tblSubordinateLists).where(p->p.getPersonName().equals(spinner_for_filterTSI.getSelectedItem())).first();
                    String PersonNodeId =""+ mDSMArrayListRecord.getPersonNodeID();

                    String PersonNodeType = ""+ mDSMArrayListRecord.getParentPersonType();*/

                    String PersonNodeId ="";

                    String PersonNodeType = "";
                   /* if(spinner_for_filterTSI.getSelectedItem().equals("OverAll"))
                    {
                        String PersonNodeIdAndNodeType = mDataSource.fngetSalesPersonMstrData();
                        if (!PersonNodeIdAndNodeType.equals("0^0")) {
                            PersonNodeId =PersonNodeIdAndNodeType.split(Pattern.quote("^"))[0];
                            PersonNodeType =PersonNodeIdAndNodeType.split(Pattern.quote("^"))[1];
                        }

                        MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
                        CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,2);
                        AppUtils.executeAsyncTask(reportDownloadAsyncTask);
                    }
                    else
                    {*/
                        TblSubordinateList mDSMArrayListRecord=stream(tblSubordinateLists).where(p->p.getPersonName().equals(spinner_for_filterTSI.getSelectedItem())).first();
                        PersonNodeId =""+ mDSMArrayListRecord.getPersonNodeID();

                        PersonNodeType = ""+ mDSMArrayListRecord.getParentPersonType();
                        MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
                        CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,0,mDSMArrayListRecord.getCoverageAreaNodeID(),mDSMArrayListRecord.getCoverageAreaNodeType());
                        AppUtils.executeAsyncTask(reportDownloadAsyncTask);
                    //}

                  /*  MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
                     CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(mActitiy, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,0);
                        AppUtils.executeAsyncTask(reportDownloadAsyncTask);*/
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void init() {


        /***** Distributor Data Starts ********************/


        if(sectionToShow==1)
        {
            LayoutInflater inflater = (LayoutInflater) mActitiy.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.layout_overall_salesheader, null);
            LinearLayout ll_PersonSalesData = ViewGenerator.createVerticalLayout(mActitiy, "personSales");
            LinearLayout ll_PersonSalesChildData = ViewGenerator.createVerticalLayout(mActitiy, "personSalesChild");
            for (int i = 0; i < mTblRptSalesVersionIndivisualArrayList.size(); i++) {
                TblRptSalesVersionIndivisual tblRptSalesVersionIndivisual = mTblRptSalesVersionIndivisualArrayList.get(i);
                View distributionChildView = null;
                if (tblRptSalesVersionIndivisual.getFlgLevel() == 1)
                    distributionChildView = inflater.inflate(R.layout.layout_personindiviusalitem, null);
                else
                    distributionChildView = inflater.inflate(R.layout.layout_personindiviusalitem2, null);
                TextView etDescription = distributionChildView.findViewById(R.id.et_descr);
                TextView et_tgt = distributionChildView.findViewById(R.id.et_tgt);
                TextView et_tilldate = distributionChildView.findViewById(R.id.et_tilldate);

                TextView et_salestgt = distributionChildView.findViewById(R.id.et_salestgt);
                TextView et_salestilldate = distributionChildView.findViewById(R.id.et_salestilldate);

                TextView et_yesterday = distributionChildView.findViewById(R.id.et_yesterday);

                etDescription.setText(tblRptSalesVersionIndivisual.getDescription());
                et_tgt.setText("" + tblRptSalesVersionIndivisual.getDstrbnMTD_1());
                et_tilldate.setText(""+tblRptSalesVersionIndivisual.getDstrbnYesterday_2());
                et_salestgt.setText("" + tblRptSalesVersionIndivisual.getSalesMTD_3());
                et_salestilldate.setText(""+tblRptSalesVersionIndivisual.getSalesYesterday_4());
                et_yesterday.setText("" + tblRptSalesVersionIndivisual.getVisitsYesterday_5());




                et_tilldate.setTag("21^"+tblRptSalesVersionIndivisual.getFlgLevel()+"^"+tblRptSalesVersionIndivisual.getDescription()+"^"+"Distribution Yesterday^"+tblRptSalesVersionIndivisual.getPersonNodeID()+"^0"+"^"+tblRptSalesVersionIndivisual.getSectionId()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeID()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeType());
                et_tgt.setTag("31^"+tblRptSalesVersionIndivisual.getFlgLevel()+"^"+tblRptSalesVersionIndivisual.getDescription()+"^"+"Distribution MTD^"+tblRptSalesVersionIndivisual.getPersonNodeID()+"^0"+"^"+tblRptSalesVersionIndivisual.getSectionId()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeID()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeType());
                et_salestilldate.setTag("22^"+tblRptSalesVersionIndivisual.getFlgLevel()+"^"+tblRptSalesVersionIndivisual.getDescription()+"^"+"Sales Yesterday^"+tblRptSalesVersionIndivisual.getPersonNodeID()+"^0"+"^"+tblRptSalesVersionIndivisual.getSectionId()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeID()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeType());
                et_salestgt.setTag("32^"+tblRptSalesVersionIndivisual.getFlgLevel()+"^"+tblRptSalesVersionIndivisual.getDescription()+"^"+"Sales MTD^"+tblRptSalesVersionIndivisual.getPersonNodeID()+"^0"+"^"+tblRptSalesVersionIndivisual.getSectionId()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeID()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeType());
                et_yesterday.setTag("33^"+tblRptSalesVersionIndivisual.getFlgLevel()+"^"+tblRptSalesVersionIndivisual.getDescription()+"^"+"Visit Yesterday^"+tblRptSalesVersionIndivisual.getPersonNodeID()+"^0"+"^"+tblRptSalesVersionIndivisual.getSectionId()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeID()+"^"+tblRptSalesVersionIndivisual.getCoverageAreaNodeType());

                etDescription.setTag(tblRptSalesVersionIndivisual.getDescription());


                SpannableString spanString = new SpannableString(tblRptSalesVersionIndivisual.getDescription());
                spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
               // spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                etDescription.setText(spanString);
                etDescription.setTextSize(12);


                SpannableString spanString1 = new SpannableString(et_tgt.getText());
                spanString1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString1.length(), 0);

                SpannableString spanString2 = new SpannableString(et_tilldate.getText());
                spanString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString2.length(), 0);

                SpannableString spanString3 = new SpannableString(et_salestgt.getText());
                spanString3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString3.length(), 0);

                SpannableString spanString4 = new SpannableString(et_salestilldate.getText());
                spanString4.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString4.length(), 0);

                SpannableString spanString5 = new SpannableString(et_yesterday.getText());
                spanString5.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString5.length(), 0);

                if(!tblRptSalesVersionIndivisual.getDescription().toLowerCase().trim().equals("Total".toLowerCase().trim()))
                {
                    spanString1.setSpan(new UnderlineSpan(), 0, spanString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spanString2.setSpan(new UnderlineSpan(), 0, spanString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spanString3.setSpan(new UnderlineSpan(), 0, spanString3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spanString4.setSpan(new UnderlineSpan(), 0, spanString4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    spanString5.setSpan(new UnderlineSpan(), 0, spanString5.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }


                et_tgt.setText(spanString1);
                et_tilldate.setText(spanString2);
                et_salestgt.setText(spanString3);
                et_salestilldate.setText(spanString4);
                et_yesterday.setText(spanString5);


                if(!tblRptSalesVersionIndivisual.getDescription().toLowerCase().trim().equals("Total".toLowerCase().trim())) {
                    et_tgt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActitiy, SalesReportFeetAndIndivualStoreLevelDetails.class);
                            intent.putExtra("DetailsData", view.getTag().toString());
                            intent.putExtra("Color_Code", "0");
                            mActitiy.startActivity(intent);
                        }
                    });

                    et_tilldate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActitiy, SalesReportFeetAndIndivualStoreLevelDetails.class);
                            intent.putExtra("DetailsData", view.getTag().toString());
                            intent.putExtra("Color_Code", "0");
                            mActitiy.startActivity(intent);
                        }
                    });

                    et_salestgt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActitiy, SalesReportFeetAndIndivualStoreLevelDetails.class);
                            intent.putExtra("DetailsData", view.getTag().toString());
                            intent.putExtra("Color_Code", "0");
                            mActitiy.startActivity(intent);
                        }
                    });

                    et_salestilldate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActitiy, SalesReportFeetAndIndivualStoreLevelDetails.class);
                            intent.putExtra("DetailsData", view.getTag().toString());
                            intent.putExtra("Color_Code", "0");
                            mActitiy.startActivity(intent);
                        }
                    });

                    et_yesterday.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActitiy, SalesReportFeetAndIndivualStoreLevelDetails.class);
                            intent.putExtra("DetailsData", view.getTag().toString());
                            intent.putExtra("Color_Code", "0");
                            mActitiy.startActivity(intent);
                        }
                    });
                }

                etDescription.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mActitiy, ReportPopUpIndivisualSalesMan.class);
                        intent.putExtra("PersonNameToShowReport", view.getTag().toString());
                        mActitiy.startActivity(intent);
                    }
                });


                ImageButton ibExpandCollapse = distributionChildView.findViewById(R.id.ibExpand);
                if (tblRptSalesVersionIndivisual.getFlgLevel() == 1 && tblRptSalesVersionIndivisual.getFlgCollapse() == 1)
                    ibExpandCollapse.setVisibility(View.VISIBLE);
                else
                    ibExpandCollapse.setVisibility(View.INVISIBLE);

                if (tblRptSalesVersionIndivisual.getFlgLevel() == 1 || tblRptSalesVersionIndivisual.getCoverageAreaNodeID()==0)
                    ll_PersonSalesData.addView(distributionChildView);
                else
                    ll_PersonSalesChildData.addView(distributionChildView);

                ibExpandCollapse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_PersonSalesChildData.getVisibility() == View.VISIBLE) {
                            ll_PersonSalesChildData.setVisibility(View.GONE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                        } else {
                            ll_PersonSalesChildData.setVisibility(View.VISIBLE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                        }
                    }
                });

                ll_PersonSalesData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_PersonSalesChildData.getVisibility() == View.VISIBLE) {
                            ll_PersonSalesChildData.setVisibility(View.GONE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                        } else {
                            ll_PersonSalesChildData.setVisibility(View.VISIBLE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                        }
                    }
                });
            }

            //
            ll_Parent.addView(view);
            ll_Parent.addView(ll_PersonSalesData);
            ll_Parent.addView(ll_PersonSalesChildData);

            ll_PersonSalesChildData.setVisibility(View.GONE);

            ll_PersonSalesChildData.addView(ViewGenerator.createMarginView(mActitiy));
        }
        else
            {
            LayoutInflater inflater = (LayoutInflater) mActitiy.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.layout_distributionheader, null);
            LinearLayout ll_distributorParentData = ViewGenerator.createVerticalLayout(mActitiy, "distribution");
            LinearLayout ll_distributorChildData = ViewGenerator.createVerticalLayout(mActitiy, "distributionChild");
            for (int i = 0; i < mTblDistributionArrayList.size(); i++) {
                TblRptDistribution tblRptDistribution = mTblDistributionArrayList.get(i);
                View distributionChildView = null;
                if (tblRptDistribution.getFlgLevel() == 1)
                    distributionChildView = inflater.inflate(R.layout.layout_distributionitem, null);
                else
                    distributionChildView = inflater.inflate(R.layout.layout_distributionitem2, null);
                TextView etDescription = distributionChildView.findViewById(R.id.et_descr);



                EditText et_tgt = distributionChildView.findViewById(R.id.et_tgt);
                EditText et_tilldate = distributionChildView.findViewById(R.id.et_tilldate);
                EditText et_mtd = distributionChildView.findViewById(R.id.et_mtd);



                TextView tv_cov= distributionChildView.findViewById(R.id.tv_cov);
                EditText et_p3m = distributionChildView.findViewById(R.id.et_p3m);
                EditText et_yesterday = distributionChildView.findViewById(R.id.et_yesterday);
                EditText et_distributionnew = distributionChildView.findViewById(R.id.et_distributionnew);
                EditText et_distributionlost = distributionChildView.findViewById(R.id.et_distributionlost);

                tv_cov.setText(tblRptDistribution.getActive_Cov());
                et_p3m.setText(tblRptDistribution.getP3M());
                et_yesterday.setText(""+tblRptDistribution.getNewYesterDay());
                et_distributionnew.setText(tblRptDistribution.getNew_Dstrbn());
                et_distributionlost.setText(tblRptDistribution.getDstrbn_Lost());


                etDescription.setText(tblRptDistribution.getDescription());
                et_tgt.setText(tblRptDistribution.getYTDTgt());
                et_tilldate.setText("" + tblRptDistribution.getYTDTillDate());
                et_mtd.setText("" + tblRptDistribution.getMTD());

                ImageButton ibExpandCollapse = distributionChildView.findViewById(R.id.ibExpand);
                if (tblRptDistribution.getFlgLevel() == 1 && tblRptDistribution.getFlgCollapse() == 1) {
                   // ibExpandCollapse.setVisibility(View.VISIBLE);

                    SpannableString spanString = new SpannableString(tblRptDistribution.getDescription());
                    spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
                    spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    etDescription.setText(spanString);
                    etDescription.setTextSize(12);

                    etDescription.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ll_distributorChildData.getVisibility() == View.VISIBLE) {
                                ll_distributorChildData.setVisibility(View.GONE);
                             //   ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                            } else {
                                ll_distributorChildData.setVisibility(View.VISIBLE);
                             //   ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                            }
                        }
                    });

                }
                else   if (tblRptDistribution.getFlgLevel() == 1 && tblRptDistribution.getFlgCollapse() == 0) {
                    SpannableString spanString = new SpannableString(tblRptDistribution.getDescription());
                    spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
                  //  spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    etDescription.setText(spanString);
                    etDescription.setTextSize(12);


                }
               /* else
                    ibExpandCollapse.setVisibility(View.INVISIBLE);*/

                if (tblRptDistribution.getFlgLevel() == 1)
                    ll_distributorParentData.addView(distributionChildView);
                else
                    ll_distributorChildData.addView(distributionChildView);

             /*   ibExpandCollapse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_distributorChildData.getVisibility() == View.VISIBLE) {
                            ll_distributorChildData.setVisibility(View.GONE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                        } else {
                            ll_distributorChildData.setVisibility(View.VISIBLE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                        }
                    }
                });
*/
                /*ll_distributorParentData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_distributorChildData.getVisibility() == View.VISIBLE) {
                            ll_distributorChildData.setVisibility(View.GONE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                        } else {
                            ll_distributorChildData.setVisibility(View.VISIBLE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                        }
                    }
                });*/
            }

            //
            ll_Parent.addView(view);
            ll_Parent.addView(ll_distributorParentData);
            ll_Parent.addView(ll_distributorChildData);
            ll_distributorChildData.setVisibility(View.GONE);

           // ll_Parent.addView(ViewGenerator.createMarginView(mActitiy));

            /***** Distributor Data Ends ********************/


            /***** Distributor2x Data Starts ********************/
            //LayoutInflater inflater2x = (LayoutInflater) mActitiy.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // View view2x = inflater.inflate(R.layout.layout_distribution2xheader, null);
            LinearLayout ll_distributorParentData2x = ViewGenerator.createVerticalLayout(mActitiy, "distribution2x");
            LinearLayout ll_distributorChildData2x = ViewGenerator.createVerticalLayout(mActitiy, "distributionChild2x");
            for (int i = 0; i < mTblDistribution2xArrayList.size(); i++) {
                TblRptDistribution tblRptDistribution = mTblDistribution2xArrayList.get(i);
                View distributionChildView = null;
                if (tblRptDistribution.getFlgLevel() == 1)
                    distributionChildView = inflater.inflate(R.layout.layout_distributionitem2x, null);
                else
                    distributionChildView = inflater.inflate(R.layout.layout_distributionitem22x, null);
                TextView etDescription = distributionChildView.findViewById(R.id.et_descr);




                EditText et_tgt = distributionChildView.findViewById(R.id.et_tgt);
                EditText et_tilldate = distributionChildView.findViewById(R.id.et_tilldate);
                EditText et_mtd = distributionChildView.findViewById(R.id.et_mtd);



                TextView tv_cov= distributionChildView.findViewById(R.id.tv_cov);
                EditText et_p3m = distributionChildView.findViewById(R.id.et_p3m);
                EditText et_yesterday = distributionChildView.findViewById(R.id.et_yesterday);
                EditText et_distributionnew = distributionChildView.findViewById(R.id.et_distributionnew);
                EditText et_distributionlost = distributionChildView.findViewById(R.id.et_distributionlost);

                tv_cov.setText(tblRptDistribution.getActive_Cov());
                et_p3m.setText(tblRptDistribution.getP3M());
                et_yesterday.setText(""+tblRptDistribution.getNewYesterDay());
                et_distributionnew.setText(tblRptDistribution.getNew_Dstrbn());
                et_distributionlost.setText(tblRptDistribution.getDstrbn_Lost());


                etDescription.setText(tblRptDistribution.getDescription());
                et_tgt.setText(tblRptDistribution.getYTDTgt());
                et_tilldate.setText("" + tblRptDistribution.getYTDTillDate());
                et_mtd.setText("" + tblRptDistribution.getMTD());



                ImageButton ibExpandCollapse = distributionChildView.findViewById(R.id.ibExpand2x);
                if (tblRptDistribution.getFlgLevel() == 1 && tblRptDistribution.getFlgCollapse() == 1) {
                    // ibExpandCollapse.setVisibility(View.VISIBLE);

                    SpannableString spanString = new SpannableString(tblRptDistribution.getDescription());
                    spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
                    spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    etDescription.setText(spanString);
                    etDescription.setTextSize(12);

                    etDescription.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ll_distributorChildData2x.getVisibility() == View.VISIBLE) {
                                ll_distributorChildData2x.setVisibility(View.GONE);
                                //   ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                            } else {
                                ll_distributorChildData2x.setVisibility(View.VISIBLE);
                                //   ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                            }
                        }
                    });

                }
                else   if (tblRptDistribution.getFlgLevel() == 1 && tblRptDistribution.getFlgCollapse() == 0) {
                    SpannableString spanString = new SpannableString(tblRptDistribution.getDescription());
                    spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
                   // spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    etDescription.setText(spanString);
                    etDescription.setTextSize(12);


                }
               /* else
                    ibExpandCollapse.setVisibility(View.INVISIBLE);*/

                if (tblRptDistribution.getFlgLevel() == 1)
                    ll_distributorParentData2x.addView(distributionChildView);
                else
                    ll_distributorChildData2x.addView(distributionChildView);

             /*   ibExpandCollapse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_distributorChildData.getVisibility() == View.VISIBLE) {
                            ll_distributorChildData.setVisibility(View.GONE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                        } else {
                            ll_distributorChildData.setVisibility(View.VISIBLE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                        }
                    }
                });
*/

               /* ll_distributorParentData2x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_distributorChildData2x.getVisibility() == View.VISIBLE) {
                            ll_distributorChildData2x.setVisibility(View.GONE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                        } else {
                            ll_distributorChildData2x.setVisibility(View.VISIBLE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                        }
                    }
                });*/
            }
            // if(mTblDistribution2xArrayList!=null && mTblDistribution2xArrayList.size()>0) {

            ll_Parent.addView(ll_distributorParentData2x);
            ll_Parent.addView(ll_distributorChildData2x);
            ll_distributorChildData2x.setVisibility(View.GONE);
            // }

            /***** Distributor2x Data Ends ********************/


            /***** Secondary Volume Data Starts ********************/

            ll_Parent.addView(ViewGenerator.createMarginView(mActitiy));
            View secondaryVolumeView = inflater.inflate(R.layout.layout_secondaryvolumeheader, null);

            LinearLayout ll_secondaryVolumeParent = ViewGenerator.createVerticalLayout(mActitiy, "secondaryvolumeparent");
            LinearLayout ll_secondaryVolumeChild = ViewGenerator.createVerticalLayout(mActitiy, "secondaryvolumechild");


            for (int i = 0; i < mTblRptSecVolArrayList.size(); i++) {
                TblRptSecVol tblRptSecVol = mTblRptSecVolArrayList.get(i);


                View distributionChildView;
                if (tblRptSecVol.getFlgLevel() == 1)
                    distributionChildView = inflater.inflate(R.layout.layout_secondaryvol_item, null);
                else
                    distributionChildView = inflater.inflate(R.layout.layout_secondaryvol_item2, null);


                ImageButton ibExpandCollapse = distributionChildView.findViewById(R.id.ibExpand);
            /*    if (tblRptSecVol.getFlgLevel() == 1 && tblRptSecVol.getFlgCollapse() == 1)
                    ibExpandCollapse.setVisibility(View.VISIBLE);
                else
                    ibExpandCollapse.setVisibility(View.INVISIBLE);
*/
                TextView etDescription = distributionChildView.findViewById(R.id.et_descr);
                EditText et_tgt = distributionChildView.findViewById(R.id.et_tgt);
                EditText et_tilldate = distributionChildView.findViewById(R.id.et_tilldate);
                EditText et_mtd = distributionChildView.findViewById(R.id.et_mtd);
                EditText et_rr_rqd = distributionChildView.findViewById(R.id.et_rr_rqd);

                etDescription.setText(tblRptSecVol.getDescription());
                et_tgt.setText(tblRptSecVol.getMTDTgt());
                et_tilldate.setText("" + tblRptSecVol.getMTDTillDate());
                et_mtd.setText("" + tblRptSecVol.getYesterday());
                et_rr_rqd.setText("" + tblRptSecVol.getRRRequired());


                if (tblRptSecVol.getFlgLevel() == 1 && tblRptSecVol.getFlgCollapse() == 1) {
                    // ibExpandCollapse.setVisibility(View.VISIBLE);

                    SpannableString spanString = new SpannableString(tblRptSecVol.getDescription());
                    spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
                    spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    etDescription.setText(spanString);
                    etDescription.setTextSize(12);

                    etDescription.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ll_secondaryVolumeChild.getVisibility() == View.VISIBLE) {
                                ll_secondaryVolumeChild.setVisibility(View.GONE);
                                //   ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                            } else {
                                ll_secondaryVolumeChild.setVisibility(View.VISIBLE);
                                //   ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                            }
                        }
                    });

                }
                else   if (tblRptSecVol.getFlgLevel() == 1 && tblRptSecVol.getFlgCollapse() == 0) {
                    SpannableString spanString = new SpannableString(tblRptSecVol.getDescription());
                    spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
                 //   spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    etDescription.setText(spanString);
                    etDescription.setTextSize(12);


                }
               /* else
                    ibExpandCollapse.setVisibility(View.INVISIBLE);*/

                if (tblRptSecVol.getFlgLevel() == 1)
                    ll_secondaryVolumeParent.addView(distributionChildView);
                else
                    ll_secondaryVolumeChild.addView(distributionChildView);


               /* ll_distributorParentData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_secondaryVolumeChild.getVisibility() == View.VISIBLE) {
                            ll_secondaryVolumeChild.setVisibility(View.GONE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                        } else {
                            ll_secondaryVolumeChild.setVisibility(View.VISIBLE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                        }
                    }
                });*/
            }

            ll_Parent.addView(secondaryVolumeView);
            ll_Parent.addView(ll_secondaryVolumeParent);
            ll_Parent.addView(ll_secondaryVolumeChild);
            ll_secondaryVolumeChild.setVisibility(View.GONE);


            /***** Secondary Volume Data Ends ********************/

            /***** Primary Volume Data Starts ********************/
            ll_Parent.addView(ViewGenerator.createMarginView(mActitiy));
            View primaryVolumeView = inflater.inflate(R.layout.layout_primaryvolumeheader, null);

            LinearLayout ll_primaryVolumeParent = ViewGenerator.createVerticalLayout(mActitiy, "primaryvolumeparent");
            LinearLayout ll_primaryVolumeChild = ViewGenerator.createVerticalLayout(mActitiy, "primaryvolumechild");


            for (int i = 0; i < mTblRptPrimaryVolArrayList.size(); i++) {

                TblRptPrimaryVol tblRptPrimaryVol = mTblRptPrimaryVolArrayList.get(i);

                View distributionChildView;
                if (tblRptPrimaryVol.getFlgLevel() == 1)
                    distributionChildView = inflater.inflate(R.layout.layout_secondaryvol_item, null);
                else
                    distributionChildView = inflater.inflate(R.layout.layout_secondaryvol_item2, null);


                ImageButton ibExpandCollapse = distributionChildView.findViewById(R.id.ibExpand);
              /*  if (tblRptPrimaryVol.getFlgLevel() == 1 && tblRptPrimaryVol.getFlgCollapse() == 1)
                    ibExpandCollapse.setVisibility(View.VISIBLE);
                else
                    ibExpandCollapse.setVisibility(View.INVISIBLE);*/


                TextView etDescription = distributionChildView.findViewById(R.id.et_descr);
                EditText et_tgt = distributionChildView.findViewById(R.id.et_tgt);
                EditText et_tilldate = distributionChildView.findViewById(R.id.et_tilldate);
                EditText et_mtd = distributionChildView.findViewById(R.id.et_mtd);
                EditText et_rr_rqd = distributionChildView.findViewById(R.id.et_rr_rqd);

                etDescription.setText(tblRptPrimaryVol.getDescription());
                et_tgt.setText(tblRptPrimaryVol.getMTDTgt());
                et_tilldate.setText("" + tblRptPrimaryVol.getMTDDelivered());
                et_mtd.setText("" + tblRptPrimaryVol.getPendingDelivery());
                et_rr_rqd.setText("" + tblRptPrimaryVol.getYesterday());



                if (tblRptPrimaryVol.getFlgLevel() == 1 && tblRptPrimaryVol.getFlgCollapse() == 1) {
                    // ibExpandCollapse.setVisibility(View.VISIBLE);

                    SpannableString spanString = new SpannableString(tblRptPrimaryVol.getDescription());
                    spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
                    spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    etDescription.setText(spanString);
                    etDescription.setTextSize(12);

                    etDescription.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ll_primaryVolumeChild.getVisibility() == View.VISIBLE) {
                                ll_primaryVolumeChild.setVisibility(View.GONE);
                                //   ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                            } else {
                                ll_primaryVolumeChild.setVisibility(View.VISIBLE);
                                //   ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                            }
                        }
                    });

                }
                if (tblRptPrimaryVol.getFlgLevel() == 1 && tblRptPrimaryVol.getFlgCollapse() == 0) {
                    // ibExpandCollapse.setVisibility(View.VISIBLE);

                    SpannableString spanString = new SpannableString(tblRptPrimaryVol.getDescription());
                    spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
                   // spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    etDescription.setText(spanString);
                    etDescription.setTextSize(12);
                }

               /* else
                    ibExpandCollapse.setVisibility(View.INVISIBLE);*/

                if (tblRptPrimaryVol.getFlgLevel() == 1)
                    ll_primaryVolumeParent.addView(distributionChildView);
                else
                    ll_primaryVolumeChild.addView(distributionChildView);



              /*  if (tblRptPrimaryVol.getFlgLevel() == 1)
                    ll_primaryVolumeParent.addView(distributionChildView);
                else
                    ll_primaryVolumeChild.addView(distributionChildView);


                ibExpandCollapse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_primaryVolumeChild.getVisibility() == View.VISIBLE) {
                            ll_primaryVolumeChild.setVisibility(View.GONE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                        } else {
                            ll_primaryVolumeChild.setVisibility(View.VISIBLE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                        }
                    }
                });*/

                /*ll_primaryVolumeParent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ll_primaryVolumeChild.getVisibility() == View.VISIBLE) {
                            ll_primaryVolumeChild.setVisibility(View.GONE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_more_black_18dp);
                        } else {
                            ll_primaryVolumeChild.setVisibility(View.VISIBLE);
                            ibExpandCollapse.setImageResource(R.drawable.baseline_expand_less_black_18dp);
                        }
                    }
                });*/
            }

            ll_Parent.addView(primaryVolumeView);
            ll_Parent.addView(ll_primaryVolumeParent);
            ll_Parent.addView(ll_primaryVolumeChild);
            ll_primaryVolumeChild.setVisibility(View.GONE);

            /***** Secondary Volume Data Ends ********************/


            /***** ManDays Data Starts ********************/
            ll_Parent.addView(ViewGenerator.createMarginView(mActitiy));
            View manDaysView = inflater.inflate(R.layout.layout_mandaysheader, null);

            LinearLayout ll_manDays = ViewGenerator.createVerticalLayout(mActitiy, "mandays");
            for (int i = 0; i < mTblRptManDayArrayList.size(); i++) {
                View manworkChildView = inflater.inflate(R.layout.layout_dayworkitem, null);

                TblRptManDays tblRptManDays = mTblRptManDayArrayList.get(i);

                TextView etDescription = manworkChildView.findViewById(R.id.et_descr);
               // EditText et_tgt = manworkChildView.findViewById(R.id.et_tgt);
                TextView et_tilldate = manworkChildView.findViewById(R.id.et_tilldate);
                TextView et_mtd = manworkChildView.findViewById(R.id.et_mtd);
                TextView et_YTD = manworkChildView.findViewById(R.id.et_YTD);
                TextView et_P3M = manworkChildView.findViewById(R.id.et_P3M);

                etDescription.setText(tblRptManDays.getDescription());
                et_YTD.setText("" + tblRptManDays.getInFieldYTD_1());
                et_P3M.setText("" + tblRptManDays.getInFieldP3M_2());
                //et_tgt.setText("" + tblRptManDays.getPlanned());
                et_tilldate.setText("" + tblRptManDays.getInFieldYesterday_4());
                et_mtd.setText("" + tblRptManDays.getInFieldMTD_3());






                et_YTD.setTag("1^"+tblRptManDays.getValueType()+"^"+tblRptManDays.getDescription()+"^"+"YTD^0^0"+"^"+tblRptManDays.getSectionId()+"^"+tblRptManDays.getCoverageAreaNodeID()+"^"+tblRptManDays.getCoverageAreaNodeType());
                et_P3M.setTag("11^"+tblRptManDays.getValueType()+"^"+tblRptManDays.getDescription()+"^"+"P3M^0^0"+"^"+tblRptManDays.getSectionId()+"^"+tblRptManDays.getCoverageAreaNodeID()+"^"+tblRptManDays.getCoverageAreaNodeType());
                et_tilldate.setTag("31^"+tblRptManDays.getValueType()+"^"+tblRptManDays.getDescription()+"^"+"Yesterday^0^0"+"^"+tblRptManDays.getSectionId()+"^"+tblRptManDays.getCoverageAreaNodeID()+"^"+tblRptManDays.getCoverageAreaNodeType());
                et_mtd.setTag("21^"+tblRptManDays.getValueType()+"^"+tblRptManDays.getDescription()+"^"+"MTD^0^0"+"^"+tblRptManDays.getSectionId()+"^"+tblRptManDays.getCoverageAreaNodeID()+"^"+tblRptManDays.getCoverageAreaNodeType());

                if(i!=0 && i!=1 && i!=2 && i!=3)
                {
                    SpannableString spanString = new SpannableString(et_YTD.getText());
                    spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
                    spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    et_YTD.setText(spanString);


                    SpannableString spanString1 = new SpannableString(et_P3M.getText());
                    spanString1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString1.length(), 0);
                    spanString1.setSpan(new UnderlineSpan(), 0, spanString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    et_P3M.setText(spanString1);

                    SpannableString spanString2 = new SpannableString(et_tilldate.getText());
                    spanString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString2.length(), 0);
                    spanString2.setSpan(new UnderlineSpan(), 0, spanString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    et_tilldate.setText(spanString2);

                    SpannableString spanString3 = new SpannableString(et_mtd.getText());
                    spanString3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString3.length(), 0);
                    spanString3.setSpan(new UnderlineSpan(), 0, spanString3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    et_mtd.setText(spanString3);


                    et_YTD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActitiy, SalesReportFeetAndIndivualStoreLevelDetails.class);
                            intent.putExtra("DetailsData", view.getTag().toString());
                            intent.putExtra("Color_Code", "0");
                            mActitiy.startActivity(intent);
                        }
                    });
                    et_P3M.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActitiy, SalesReportFeetAndIndivualStoreLevelDetails.class);
                            intent.putExtra("DetailsData", view.getTag().toString());

                            mActitiy.startActivity(intent);
                        }
                    });
                    et_tilldate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActitiy, SalesReportFeetAndIndivualStoreLevelDetails.class);
                            intent.putExtra("DetailsData", view.getTag().toString());

                            mActitiy.startActivity(intent);
                        }
                    });
                    et_mtd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActitiy, SalesReportFeetAndIndivualStoreLevelDetails.class);
                            intent.putExtra("DetailsData", view.getTag().toString());

                            mActitiy.startActivity(intent);
                        }
                    });
                }



                ll_manDays.addView(manworkChildView);
            }

            ll_Parent.addView(manDaysView);
            ll_Parent.addView(ll_manDays);
        }


        if(mProgressDialogReport!=null && mProgressDialogReport.isShowing())
        {
            mProgressDialogReport.dismiss();
        }
        /***** Secondary Volume Data Ends ********************/

    }

    @OnClick(R.id.bt_Next)
    public void onNextButtonClick() {
        String fDate=   TimeUtils.getNetworkDateTime(mActitiy, TimeUtils.DATE_FORMAT);
       // ((ReportsActivity) mActitiy).loadFragment(null, ReportsActivity.DAY_PLAN_REPORT);
       /*
        Intent intent = new Intent(mActitiy, DistributorCheckInSecondActivity.class);
        intent.putExtra("imei", AppUtils.getToken(mActitiy));
        intent.putExtra("fDate", fDate);
        intent.putExtra("From", "DayStart");
        startActivity(intent);
        mActitiy.finish();*/

        Intent i = new Intent(mActitiy, PersonGateMeetingTarget.class);
        i.putExtra("token",  AppUtils.getToken(mActitiy));
        i.putExtra("fDate", fDate);
        i.putExtra("From", "DayStart");
        startActivity(i);
        mActitiy.finish();
    }

    private class GetData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            mTblDistributionArrayList = mDataSource.getRptDistributions();
            mTblDistribution2xArrayList = mDataSource.getRptDistributions2x();
            mTblRptSalesVersionIndivisualArrayList=mDataSource.getRptSalesVersionIndivisual();
            mTblRptSecVolArrayList = mDataSource.getRptSecondaryVols();
            mTblRptPrimaryVolArrayList = mDataSource.getRptPrimaryVols();
            mTblRptManDayArrayList = mDataSource.getManDays();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            init();
        }
    }
    public void fnRefreshDownloadedDataAgainstPerson(int calledfor)
    {

        sectionToShow=calledfor;
            ll_Parent.removeAllViews();



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
                TblSubordinateList mDSMArrayListRecord=stream(tblSubordinateLists).where(p->p.getPersonName().equals(spinner_for_filterTSI.getSelectedItem())).first();
                areaName=mDSMArrayListRecord.getCoverageArea();
            }

            String yesterdayDate = mPreferenceManager.getStringValue(SPConstants.YESTERDAY_DATE, "");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvDescr.setText(Html.fromHtml("Here is a brief summary of the performance of <b>" + areaName + "</b> till <b>" + yesterdayDate + "</b>", Html.FROM_HTML_MODE_COMPACT));
            } else {
                tvDescr.setText(Html.fromHtml("Here is a brief summary of the performance of <b>" + areaName + "</b> till <b>" + yesterdayDate + "</b>"));
            }

            if (getArguments() != null) {
                String From = getArguments().getString("From");
                if (!From.equals("DayStart"))
                    btNext.setVisibility(View.GONE);
                else
                    btNext.setVisibility(View.VISIBLE);
            }
            GetData getData = new GetData();
            AppUtils.executeAsyncTask(getData);
           // init();

    }






}
