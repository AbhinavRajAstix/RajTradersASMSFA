package com.astix.rajtraderssfasmasales;


import android.app.Activity;
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

import android.view.LayoutInflater;

import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.allana.truetime.TimeUtils;
import com.astix.Common.CommonFunction;
import com.astix.rajtraderssfasmasales.customwidgets.ViewGenerator;
import com.astix.rajtraderssfasmasales.database.AppDataSource;

import com.astix.rajtraderssfasmasales.model.TblSubordinateList;

import com.astix.rajtraderssfasmasales.reports.models.TblRptDistribution;
import com.astix.rajtraderssfasmasales.reports.models.TblRptManDays;
import com.astix.rajtraderssfasmasales.reports.models.TblRptPrimaryVol;
import com.astix.rajtraderssfasmasales.reports.models.TblRptSalesVersionIndivisual;
import com.astix.rajtraderssfasmasales.reports.models.TblRptSecVol;
import com.astix.rajtraderssfasmasales.utils.AppUtils;
import com.astix.rajtraderssfasmasales.utils.PreferenceManager;
import com.astix.rajtraderssfasmasales.utils.SPConstants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


import static br.com.zbra.androidlinq.Linq.stream;


public class ReportPopUpIndivisualSalesMan extends BaseActivity implements  MultipleInterfaceRetrofit {

    String imei="";
    public Context mActitiy;
    public ArrayList<TblSubordinateList> mDSMArrayList;
    public ArrayList<TblSubordinateList> tblSubordinateLists;
    private PreferenceManager mPreferenceManager;
    private AppDataSource mDataSource;

    String PersonNameToShowReport="";
    String yesterdayDate="";
    String PersonNodeId ="";


    String PersonNodeType = "";
    public ProgressDialog mProgressDialogReport;
    public int sectionToShow=1;

    @BindView(R.id.tvDescr)
    TextView tvDescr;

    @BindView(R.id.ll_Parent)
    LinearLayout ll_Parent;

    @BindView(R.id.imgCncl)
    ImageView imgCncl;


    ArrayList<TblRptDistribution> mTblDistributionArrayList;
    ArrayList<TblRptDistribution> mTblDistribution2xArrayList;
    ArrayList<TblRptSalesVersionIndivisual> mTblRptSalesVersionIndivisualArrayList;
    ArrayList<TblRptSecVol> mTblRptSecVolArrayList;
    ArrayList<TblRptPrimaryVol> mTblRptPrimaryVolArrayList;
    ArrayList<TblRptManDays> mTblRptManDayArrayList;

    //private MyService mMyService;
    public Context getCtx() {
        return mActitiy;
    }


    @Override
    protected void onDestroy() {
       // this.unregisterReceiver(this.mBatInfoReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_report_indiviusal_salesman);
        mActitiy = this;

        ButterKnife.bind(this);
        imei = AppUtils.getToken(ReportPopUpIndivisualSalesMan.this);

        mDataSource = AppDataSource.getInstance(mActitiy);
        mPreferenceManager = PreferenceManager.getInstance(mActitiy);

        Intent intent = getIntent();
        PersonNameToShowReport= intent.getStringExtra("PersonNameToShowReport");
       // intentFrom = intent.getIntExtra("IntentFrom", 0);

        fnGetLocalData();


        imgCncl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mProgressDialogReport = new ProgressDialog(mActitiy);
        mProgressDialogReport.setTitle("Please wait fetching data.");//context.getResources().getString(R.string.Loading));
        mProgressDialogReport.setMessage(mActitiy.getResources().getString(R.string.RetrivingDataMsg));
        mProgressDialogReport.setIndeterminate(true);
        mProgressDialogReport.setCancelable(false);
        mProgressDialogReport.show();
        TblSubordinateList mDSMArrayListRecord=stream(tblSubordinateLists).where(p->p.getPersonName().toLowerCase().trim().equals(PersonNameToShowReport.toLowerCase().trim())).first();
        PersonNodeId =""+ mDSMArrayListRecord.getPersonNodeID();

        PersonNodeType = ""+ mDSMArrayListRecord.getParentPersonType();
        MultipleInterfaceRetrofit interfaceRetrofit = (MultipleInterfaceRetrofit) mActitiy;
        CommonFunction.DistributionReportDownload reportDownloadAsyncTask = new CommonFunction.DistributionReportDownload(ReportPopUpIndivisualSalesMan.this, mProgressDialogReport, interfaceRetrofit, yesterdayDate,""+PersonNodeId,""+PersonNodeType,0,mDSMArrayListRecord.getCoverageAreaNodeID(),mDSMArrayListRecord.getCoverageAreaNodeType());
        AppUtils.executeAsyncTask(reportDownloadAsyncTask);

    }
    public void fnGetLocalData()
    {
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
        yesterdayDate = mPreferenceManager.getStringValue(SPConstants.YESTERDAY_DATE, TimeUtils.getNetworkDate(mActitiy,TimeUtils.DATE_FORMAT));
    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void success(int calledfor) {
        sectionToShow=calledfor;
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
        }


        GetData getData = new GetData();
        AppUtils.executeAsyncTask(getData);
    }

    @Override
    public void failure(int flgCalledFrom) {

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
                spanString1.setSpan(new UnderlineSpan(), 0, spanString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                et_tgt.setText(spanString1);

                SpannableString spanString2 = new SpannableString(et_tilldate.getText());
                spanString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString2.length(), 0);
                spanString2.setSpan(new UnderlineSpan(), 0, spanString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                et_tilldate.setText(spanString2);

                SpannableString spanString3 = new SpannableString(et_salestgt.getText());
                spanString3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString3.length(), 0);
                spanString3.setSpan(new UnderlineSpan(), 0, spanString3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                et_salestgt.setText(spanString3);

                SpannableString spanString4 = new SpannableString(et_salestilldate.getText());
                spanString4.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString4.length(), 0);
                spanString4.setSpan(new UnderlineSpan(), 0, spanString4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                et_salestilldate.setText(spanString4);

                SpannableString spanString5 = new SpannableString(et_yesterday.getText());
                spanString5.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActitiy, android.R.color.holo_blue_dark)), 0, spanString5.length(), 0);
                spanString5.setSpan(new UnderlineSpan(), 0, spanString5.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                et_yesterday.setText(spanString5);



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

                if (tblRptSalesVersionIndivisual.getFlgLevel() == 1 || tblRptSalesVersionIndivisual.getPersonNodeID().equals("0"))
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
        else{
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
}
