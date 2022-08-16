package com.astix.rajtraderssfasmasales;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.astix.Common.CommonInfo;
import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.model.JointVisitDetail;
import com.astix.rajtraderssfasmasales.model.JointVisitMemberDetail;
import com.astix.rajtraderssfasmasales.utils.AppUtils;

import java.util.ArrayList;


public class PendingJointVisitActivity extends BaseActivity implements InterfaceRetrofitEndJointVisit {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO: Rename and change types and number of parameters
    SharedPreferences sharedPref;
    int FLGJOINTVISIT=0;

    int VisitType=0;
    public String userDate;
    public String pickerDate;

    ImageView img_side_back;

    RecyclerView mRecyclerView;

    private View view;
    private ArrayList<JointVisitDetail> mJointVisitArrayList;
    private ArrayList<JointVisitMemberDetail> mJointVisitMemberList;

    private Activity mActivity;
private AppDataSource mDataSource;
private JointVisitListAdapter mJointVisitListAdapter;
Context ctx;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pending_joint_visit);

     //   ButterKnife.bind(this,view);
        ctx= PendingJointVisitActivity.this;
        mJointVisitArrayList=new ArrayList<>();
        mActivity= PendingJointVisitActivity.this;
        pickerDate = getDateInMonthTextFormat();
        userDate = getDateInMonthTextFormat();
        sharedPref = getSharedPreferences(CommonInfo.Preference, MODE_PRIVATE);
        Intent passedvals = getIntent();
        FLGJOINTVISIT=passedvals.getIntExtra(AppUtils.FLGJOINTVISIT,0);
        VisitType=passedvals.getIntExtra(AppUtils.VisitType,0);
       /* new AppUtils().callParentMethod(mActivity);*/
        mDataSource=AppDataSource.getInstance(mActivity);
        mRecyclerView=(RecyclerView)findViewById(R.id.rlJointVisits);
         /*  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
     mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
*/

        mJointVisitArrayList=mDataSource.getPendingJointVisits();
        mJointVisitMemberList=mDataSource.getJointVisitMembers();

        img_side_back=(ImageView)findViewById(R.id.img_side_back);

        img_side_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PendingJointVisitActivity.this, AllButtonActivity.class);
                startActivity(i);
                finish();
            }
        });



        mJointVisitListAdapter=new JointVisitListAdapter(PendingJointVisitActivity.this ,mJointVisitArrayList,mJointVisitMemberList);
        mRecyclerView.setAdapter(mJointVisitListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
      /*  GetData getData=new GetData();
        AppUtils.executeAsyncTask(getData);*/
    }

    @Override
    public void successEndJointVisit(String JointVisitID) {

        fnEndJointVisit(JointVisitID);



    }


    @Override
    public void failureEndJointVisit() {

    }

    private void fnEndJointVisit(String JointVisitID)
    {


       String rID = mDataSource.GetActiveRouteID(  sharedPref.getInt("CoverageAreaNodeID",0),   sharedPref.getInt("CoverageAreaNodeType",0));
        CommonInfo.CoverageAreaNodeID=sharedPref.getInt("CoverageAreaNodeID",0);
        CommonInfo.CoverageAreaNodeType= sharedPref.getInt("CoverageAreaNodeType",0);
        if (rID.equals("0")) {
            rID = mDataSource.GetNotActiveRouteID();
        }
        Intent intent = new Intent(PendingJointVisitActivity.this, StoreSelection.class);
        intent.putExtra("imei", AppUtils.getToken(PendingJointVisitActivity.this));
        intent.putExtra("userDate", userDate);
        intent.putExtra("pickerDate", pickerDate);
        intent.putExtra("rID", rID);
        intent.putExtra("JOINTVISITID", JointVisitID);
        intent.putExtra(AppUtils.FLGJOINTVISIT, FLGJOINTVISIT);
        intent.putExtra(AppUtils.VisitType, VisitType);


        startActivity(intent);
        finish();


       /* androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PendingJointVisitActivity.this);
        builder.setTitle(R.string.app_nameAlertInfo)
                .setMessage("Are you sure you want to end this Visit?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDataSource.endJointVisit(JointVisitID,mActivity);

                        mDataSource.UpdateStoreVisitWiseTables("NA", 3,"NA","NA",JointVisitID);
                        Intent i=new Intent(PendingJointVisitActivity.this,AllButtonActivity.class);
                        startActivity(i);
                        finish();
                    }
                });

        builder.show();
*/

        //Code Else Ends Here
        // end else


    }

    private class GetData extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }
    }






}
