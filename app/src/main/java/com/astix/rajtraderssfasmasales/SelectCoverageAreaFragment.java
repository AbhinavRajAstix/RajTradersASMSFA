package com.astix.rajtraderssfasmasales;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.model.CoverageAreaItem;
import com.astix.rajtraderssfasmasales.model.TblRouteListMaster;
import com.astix.rajtraderssfasmasales.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SelectCoverageAreaFragment extends BaseActivity implements InterfaceRetrofit {

    public String fDate;
    public String userDate;
    public String passDate;
    public SimpleDateFormat sdf;
    public String pickerDate;
    public String rID;

    @BindView(R.id.rl_CoverageArea)
    RecyclerView mRecyclerView;
    @BindView(R.id.bt_Next)
    Button bt_Next;
    @BindView(R.id.et_SearchCoverageArea)
    EditText et_SearchCoverageArea;
    private View view;
    private AppDataSource mDataSource;
    private Activity mActivity;
    private ArrayList<CoverageAreaItem> mCoverageAreaItems;
    private CoverageListAdapter mCoverageAreaAdapter;
    private TblRouteListMaster mSelectedRoute;
    private TextWatcher mSearchTw = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (mCoverageAreaAdapter != null)
                mCoverageAreaAdapter.getFilter().filter(s.toString().toLowerCase());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select_coverage_area);
        ButterKnife.bind(this);

        mActivity = SelectCoverageAreaFragment.this;
        /*final InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);*/

        Date date1 = new Date();
        sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        passDate = sdf.format(date1);

        //System.out.println("Selctd Date: "+ passDate);

        fDate = passDate.trim();
        pickerDate = getDateInMonthTextFormat();
        userDate = getDateInMonthTextFormat();

        //new AppUtils().callParentMethod(mActivity);
        mDataSource = AppDataSource.getInstance(mActivity);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));

        GetData getData = new GetData();
        AppUtils.executeAsyncTask(getData);
        et_SearchCoverageArea.addTextChangedListener(mSearchTw);
    }


    @OnClick(R.id.bt_Next)
    public void onNextButtonClick() {

        boolean isCoverageAreaSelected = false;
        CoverageAreaItem selectedCoverageArea = null;

        for (CoverageAreaItem coverageAreaItem : mCoverageAreaItems) {
            if (coverageAreaItem.isSelected()) {
                isCoverageAreaSelected = true;
                selectedCoverageArea = coverageAreaItem;
                break;
            }
        }

        if (!isCoverageAreaSelected) {
            AppUtils.showAlertDialog(mActivity, "Please select coverage area first.");
            return;
        }
        mSelectedRoute = mDataSource.getRouteFromPersonId(selectedCoverageArea.getCoverageAreaNodeID());

        if (!mDataSource.isStoreListAlreadyExist(mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType())) {
            if (mDataSource.isStoreListAlreadyExist(mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType())) {
                navigateToStoreList();
            } else {
                AppUtils.showAlertDialog(mActivity, "There is no store list available for the selected Coverage Area.");
            }
            //CommonFunction.getStoreListData(mActivity, this, CommonInfo.RegistrationID, "Fetching StoreList", mSelectedRoute, false);
        }
        else
            navigateToStoreList();


    }

    private void navigateToStoreList() {

      /*  Bundle args = new Bundle();
        args.putInt(AppUtils.ROUTEID, mSelectedRoute.getRouteNodeId());
        args.putInt(AppUtils.FLGJOINTVISIT, 0);
        args.putSerializable(AppUtils.ROUTE, mSelectedRoute);*/
      //  ((MarketVisitActivity) mActivity).loadFragment(args, MarketVisitActivity.STOREVISIT);
        Intent intent = new Intent(SelectCoverageAreaFragment.this, StoreSelection.class);
        intent.putExtra("imei", AppUtils.getToken(this));
        intent.putExtra("userDate", userDate);
        intent.putExtra("pickerDate", fDate);
        intent.putExtra("rID", rID);
        intent.putExtra("JOINTVISITID", "NA");
        startActivity(intent);
        finish();
    }

    @Override
    public void success() {
        if (mDataSource.isStoreListAlreadyExist(mSelectedRoute.getCoverageAreaNodeID(), mSelectedRoute.getCoverageAreaNodeType())) {
            navigateToStoreList();
        } else {
            AppUtils.showAlertDialog(mActivity, "There is no store list available for the selected Coverage Area.");
        }

    }

    @Override
    public void failure() {
        AppUtils.showAlertDialog(mActivity, "Couldn't load data this time. Please try again later!");
    }

    private class GetData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {

            mCoverageAreaItems = mDataSource.getAsmCoverageAreaItems();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            mCoverageAreaAdapter = new CoverageListAdapter(mActivity, mCoverageAreaItems);
            mRecyclerView.setAdapter(mCoverageAreaAdapter);
        }
    }


}
