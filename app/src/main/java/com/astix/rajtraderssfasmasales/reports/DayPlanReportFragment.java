package com.astix.rajtraderssfasmasales.reports;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.astix.Common.CommonInfo;
import com.astix.rajtraderssfasmasales.R;
import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.reports.models.TblDayWorkSummary;
import com.astix.rajtraderssfasmasales.sync.DatabaseAssistant;
import com.astix.rajtraderssfasmasales.utils.AppUtils;
import com.astix.rajtraderssfasmasales.utils.PreferenceManager;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayPlanReportFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {


    public DayPlanReportFragment() {
        // Required empty public constructor
    }

    private View view;
    private Activity mActivity;
    private PreferenceManager mPreferenceManager;
    private AppDataSource mDataSource;
    LinkedHashMap<Integer, String> hmapAgenda=new LinkedHashMap<Integer, String>();
    ArrayList<String> listTxtBxToShow;

    /*@BindView(R.id.ll_Agenda)
    LinearLayout ll_Agenda;
    @BindView(R.id.etPlanned_OnStreet)
    EditText etPlanned_OnStreet;
    @BindView(R.id.etActual_OnStreet)
    EditText etActual_OnStreet;
    @BindView(R.id.etPlanned_PlannedToday)
    EditText etPlanned_PlannedToday;
    @BindView(R.id.etPlanned_ExpectedProductive)
    EditText etPlanned_ExpectedProductive;
    @BindView(R.id.etPlanned_ExpectedTotal)
    EditText etPlanned_ExpectedTotal;

    @BindView(R.id.tvGreeting)
    TextView tvGreeting;

    public CheckBox[] cb;
    private ArrayList<CheckBox> cbArrayList, cbArrayListControl_6;
    private ArrayList<TblDayWorkSummary> mTblDayWorkSummaryArrayList;
    String ReasonId="";
    String ReasonText="";
    int iStreetExpected=0;
    int iExpectedTotalCalls=0;
    int iExpectedProductiveCalls=0;
    Integer personNodeID=0;
    Integer personNodeType=0;
    ArrayList<TblDayStartAttendanceOptions> mDayStartAttendanceOptions;*/

    public static DayPlanReportFragment newInstance(Bundle args) {

        DayPlanReportFragment fragment = new DayPlanReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        if (view != null) {
            if ((ViewGroup)view.getParent() != null)
                ((ViewGroup)view.getParent()).removeView(view);
            return view;
        }
        view= inflater.inflate(R.layout.fragment_day_plan_report, container, false);
        ButterKnife.bind(this,view);
        mActivity =getActivity();
       // getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);

       /* new AppUtils().callParentMethod(mActivity);
        mDataSource=DataSource.getInstance(mActivity);
        mPreferenceManager= PreferenceManager.getInstance(mActivity);
        personNodeID=mPreferenceManager.getIntValue(AppUtils.PERSON_NODE_ID,0);
        personNodeType=mPreferenceManager.getIntValue(AppUtils.PERSON_NODE_TYPE,0);
        hmapAgenda=mDataSource.fetch_Reason_List_for_option();
        listTxtBxToShow=mDataSource.fetch_Text_To_Show();
        mDayStartAttendanceOptions=new ArrayList<>();
        mDayStartAttendanceOptions=mDataSource.getDayStartAttendanceOptions();
        cbArrayList=new ArrayList<>();
        cbArrayListControl_6=new ArrayList<>();
        tvGreeting.setText("Let's define what you plan to achieve Today.");
        GetData getData=new GetData();
        AppUtils.executeAsyncTask(getData);*/

        return view;
    }


    private class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
         //   mTblDayWorkSummaryArrayList=mDataSource.getDayWorkSummary();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            /*for(TblDayWorkSummary tblDayWorkSummary:mTblDayWorkSummaryArrayList){
                if(tblDayWorkSummary.getGroupName().toLowerCase().equalsIgnoreCase("feet on street")){
                    etPlanned_OnStreet.setText(String.valueOf(tblDayWorkSummary.getPlanned()));
                }

                if(tblDayWorkSummary.getGroupName().toLowerCase().equalsIgnoreCase("plan calls for today")){
                    etPlanned_PlannedToday.setText(String.valueOf(tblDayWorkSummary.getPlanned()));
                }

            }
*/
            prepareAgendaView();
        }
    }

    private void prepareAgendaView() {

       /* RadioGroup radioGroup = new RadioGroup(mActivity);
        for (TblDayStartAttendanceOptions tblDayStartAttendanceOptions : mDayStartAttendanceOptions) {
            if (tblDayStartAttendanceOptions.getControlType() == 5 || tblDayStartAttendanceOptions.getControlType() == 6) {
                CheckBox cb = new CheckBox(mActivity);
                cb.setText(tblDayStartAttendanceOptions.getReasonDescr().replaceAll("\n", ""));
                cb.setTag(tblDayStartAttendanceOptions.getReasonId());
                //cb.setOnCheckedChangeListener(this::onCheckedChanged);
                if (tblDayStartAttendanceOptions.getControlType() == 6)
                    cb.setButtonDrawable(R.drawable.custom_radio_button);

                cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (tblDayStartAttendanceOptions.getControlType() == 6) {
                        updateAllCheckBoxState(cb.getTag().toString(), isChecked, 6);
                    } else {
                        updateAllCheckBoxState(cb.getTag().toString(), isChecked, 5);
                    }
                });

                if (tblDayStartAttendanceOptions.getControlType() == 5)
                    cbArrayList.add(cb);
                else
                    cbArrayListControl_6.add(cb);

                ll_Agenda.addView(cb);


            }


        }

        if (radioGroup.getChildCount() > 0) {
            ll_Agenda.addView(radioGroup);
        }*/
    }


    private void updateAllCheckBoxState(String tag, boolean isChecked, int controlType) {

  /*      if (isChecked && controlType == 6) {
            for (CheckBox checkBox : cbArrayListControl_6) {
                if (!checkBox.getTag().toString().equalsIgnoreCase(tag)) {
                    checkBox.setChecked(false);
                    //checkBox.setEnabled(false);
                } else {
                    checkBox.setEnabled(true);
                }
            }
            for (CheckBox checkBox : cbArrayList) {
                checkBox.setChecked(false);
            }

        } else if (isChecked && controlType == 5) {
            for (CheckBox checkBox : cbArrayListControl_6) {
                checkBox.setChecked(false);

            }
        } else {
            for (CheckBox checkBox : cbArrayListControl_6) {
                checkBox.setEnabled(true);
            }
            for (CheckBox checkBox : cbArrayList) {
                checkBox.setEnabled(true);
            }
        }*/

    }



    private void createCheckBoxForAgenda()
    {
     /*   cb = new CheckBox[hmapAgenda.size()];

        int i = 0;
        for (Map.Entry<Integer, String> entry : hmapAgenda.entrySet())
        {
            EditText editText = null;
            cb[i] = new CheckBox(mActivity);
            cb[i].setText(entry.getValue());
            cb[i].setTag(entry.getKey().toString().trim());
            cb[i].setOnCheckedChangeListener(this::onCheckedChanged);

            if(listTxtBxToShow!=null && listTxtBxToShow.contains(entry.getKey().toString().trim()))
            {
                editText= getEditText(entry.getKey().toString().trim()+"_ed");
            }

            ll_Agenda.addView(cb[i]);
            if(editText!=null)
            {
                ll_Agenda.addView(editText);
                editText.setVisibility(View.GONE);
            }
            cb[i].setOnCheckedChangeListener(this);
            i = i + 1;
        }
*/
    }


    public EditText getEditText(String tagVal)
    {
        EditText editText=new EditText(mActivity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                ((int) LinearLayout.LayoutParams.WRAP_CONTENT, (int) LinearLayout.LayoutParams.WRAP_CONTENT);

        params.leftMargin = 50;
        params.rightMargin = 50;
        editText.setBackground(getResources().getDrawable(R.drawable.et_boundary_retrun));
        editText.setLayoutParams(params);
        editText.setTag(tagVal);
        editText.setEms(10);
        return editText;
    }

    public void onCheckedChanged(CompoundButton cb, boolean isChecked){
        String checkedText = cb.getText()+"";
        String checkedID = cb.getTag()+"";

    }



    @OnClick(R.id.bt_Next)
    public void onNextButtonClick(){

/*

        String streetExpected=etActual_OnStreet.getText().toString();
        String expectedTotalCalls=etPlanned_ExpectedTotal.getText().toString();
        String expectedProductiveCalls=etPlanned_ExpectedProductive.getText().toString();



        if(streetExpected!=null && streetExpected.length()>0){
            iStreetExpected=Integer.parseInt(streetExpected);
        }
        else{
            AppUtils.showAlertDialog(mActivity,"Please enter value in Expected Street data.");
            return;
        }

        if(expectedTotalCalls!=null && expectedTotalCalls.length()>0){
            iExpectedTotalCalls=Integer.parseInt(expectedTotalCalls);
        }
        else{
            AppUtils.showAlertDialog(mActivity,"Please enter value in Expected Total calls.");
            return;
        }

        if(expectedProductiveCalls!=null && expectedProductiveCalls.length()>0){
            iExpectedProductiveCalls=Integer.parseInt(expectedProductiveCalls);
        }
        else{
            AppUtils.showAlertDialog(mActivity,"Please enter value in Expected Productive calls.");
            return;
        }
        boolean agendaSelected=false;

        for(CheckBox checkBox:cbArrayList){
            if(checkBox.isChecked()){
                agendaSelected=true;
                if(ReasonId.equals(""))
                {
                    ReasonId= ReasonId+Integer.parseInt(checkBox.getTag().toString());
                }
                else
                {
                    ReasonId=ReasonId+"$"+Integer.parseInt(checkBox.getTag().toString());
                }

                if(ReasonText.equals(""))
                {
                    ReasonText= ReasonText+checkBox.getText();
                }
                else
                {
                    ReasonText=ReasonText+"$"+checkBox.getText();
                }

            }
        }


        for (CheckBox checkBox : cbArrayListControl_6) {
            if (checkBox.isChecked()) {
                agendaSelected = true;
                if (ReasonId.equals("")) {
                    ReasonId = ReasonId + Integer.parseInt(checkBox.getTag().toString());
                } else {
                    ReasonId = ReasonId + "$" + Integer.parseInt(checkBox.getTag().toString());
                }

                if (ReasonText.equals("")) {
                    ReasonText = ReasonText + checkBox.getText();
                } else {
                    ReasonText = ReasonText + "$" + checkBox.getText();
                }
            }
        }
        if(!agendaSelected){
            AppUtils.showAlertDialog(mActivity,"Please select agenda for the day.");
            return;
        }

        ((ReportsActivity) mActivity).startLocationTracking();

*/


    }

    public void navigateToNextView(){

       /* Intent intent=new Intent(mActivity, DashBoardActivity.class);
        startActivity(intent);
        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String fDate = sdf.format(date1).toString().trim();
       // mPreferenceManager.setValue(AppUtils.Flg_Attendance,fDate);
        ((ReportsActivity) mActivity).finish();*/
    }

    public void syncData(){
       /* mDataSource.updateDayWork(iStreetExpected,iExpectedTotalCalls,iExpectedProductiveCalls,personNodeID,personNodeType);
        mDataSource.updatetblAttandanceDetails("33","Working",ReasonId,ReasonText,"","0","0","NA");


        long syncTIMESTAMP = System.currentTimeMillis();
        Date dateobj = new Date(syncTIMESTAMP);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH);
        String startTS = df.format(dateobj);
        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String fDate = sdf.format(date1).trim();

        mDataSource.insertTblDayStartEndDetails(CommonInfo.imei,startTS,0,fDate, CommonInfo.AppVersionID);
        FullSyncDataNow task = new FullSyncDataNow(mActivity);
        task.execute();*/
    }



    private class FullSyncDataNow extends AsyncTask<Void, Void, Void> {


      /*  ProgressDialog pDialogGetStores;
        DataSource mDataSource;
        public String newfullFileName;
        private DatabaseAssistant mDatabaseAssistant;*/

        public FullSyncDataNow(Activity activity) {
          /*  pDialogGetStores = new ProgressDialog(activity);
            mDataSource=DataSource.getInstance(activity);
            mDatabaseAssistant=new DatabaseAssistant(activity);*/
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

/*

            pDialogGetStores.setTitle(getText(R.string.genTermPleaseWaitNew));
            pDialogGetStores.setMessage("Submitting Day start details.");
            pDialogGetStores.setIndeterminate(false);
            pDialogGetStores.setCancelable(false);
            pDialogGetStores.setCanceledOnTouchOutside(false);
            pDialogGetStores.show();
*/


        }

        @Override

        protected Void doInBackground(Void... params) {

            int Outstat = 1;

/*
            long syncTIMESTAMP = System.currentTimeMillis();
            Date dateobj = new Date(syncTIMESTAMP);
            SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss", Locale.ENGLISH);

            newfullFileName = CommonInfo.imei + "." + df1.format(dateobj);


            try {


                File OrderXMLFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.OrderXMLFolder);

                if (!OrderXMLFolder.exists()) {
                    OrderXMLFolder.mkdirs();

                }

                mDataSource.UpdateAttendanceDetils(3);
                mDataSource.UpdateDayStartEndDetails(3);
                mDatabaseAssistant.export(CommonInfo.DATABASE_NAME, newfullFileName);


                mDataSource.savetbl_XMLfiles(newfullFileName, "3", "1");
                mDataSource.UpdateAttendanceDetils(5);
                mDataSource.UpdateDayStartEndDetails(5);

            } catch (Exception e) {

                e.printStackTrace();
                if (pDialogGetStores.isShowing()) {
                    pDialogGetStores.dismiss();
                }
            }*/
            return null;
        }

        @Override
        protected void onCancelled() {

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
           /* if (pDialogGetStores.isShowing()) {
                pDialogGetStores.dismiss();
            }
            try {


                Intent mMyServiceIntent = new Intent(mActivity, MyService.class);
                mMyServiceIntent.putExtra("xmlPathForSync", Environment.getExternalStorageDirectory() + "/" + CommonInfo.OrderXMLFolder + "/" + newfullFileName + ".xml");
                mMyServiceIntent.putExtra(AppUtils.StoreId, "0");
                mMyServiceIntent.putExtra("OrigZipFileName", newfullFileName);
                mMyServiceIntent.putExtra("whereTo", "Regular");//
                if (!isMyServiceRunning(MyService.class)) {
                    mActivity.startService(mMyServiceIntent);
                }


                navigateToNextView();

            } catch (Exception e) {

                e.printStackTrace();
            }
*/
        }

        private boolean isMyServiceRunning(Class<?> serviceClass) {
            ActivityManager manager = (ActivityManager) mActivity.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceClass.getName().equals(service.service.getClassName())) {
                    Log.i("isMyServiceRunning?", true + "");
                    return true;
                }
            }
            Log.i("isMyServiceRunning?", false + "");
            return false;
        }
    }

}
