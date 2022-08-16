package com.astix.rajtraderssfasmasales;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.astix.rajtraderssfasmasales.adapter.CardArrayAdapterCategory2;
import com.astix.rajtraderssfasmasales.model.TblDistributorDailyStockDetailsSKUReport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static br.com.zbra.androidlinq.Linq.stream;


public class DistributorStockStatusSKUWise extends BaseActivity implements CategoryCommunicator {

   String PersonName="";
   String DistributorName="";
   String EntryDate="";
    TextView tv_distributorName,tv_PersonName,tv_EntryDate;
    List<TblDistributorDailyStockDetailsSKUReport> tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp = new ArrayList<>();
    List<TblDistributorDailyStockDetailsSKUReport> tblDistributorDailyStockDetailsSKUReportArrayList = new ArrayList<>(), tblDistributorDailyStockDetailsSKUReportArrayListForFilter = new ArrayList<>();
   
    public String defaultCatName_Id = "0", previousSlctdCtgry, distID = "";
    LinearLayout ll_forTableHeaderName;
    RecyclerView rvProducts;
    ImageView img_ctgry, img_back_Btn;
    EditText ed_search;
    DistributorStockStatusSKUWiseAdapter orderAdapter;
    LinkedHashMap<String, String> hmapctgry_details;
    List<String> categoryNames;

    public ProgressDialog pDialog2STANDBY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_stock_status_skuwise);
        initializeFields();
        getDataFromatabase();
    }


    private void getCategoryDetail() {
//        tblProductTypeMasterForRetrivingList = mDataSource.fetch_Category_List_OrderEntry();
        hmapctgry_details = mDataSource.fetch_Category_ListOtherFromOrderEntry(1);

        int index = 0;
        if (hmapctgry_details != null) {
            categoryNames = new ArrayList<>();
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(hmapctgry_details);
            Set set2 = map.entrySet();
            Iterator iterator = set2.iterator();
            while (iterator.hasNext()) {
                Map.Entry me2 = (Map.Entry) iterator.next();
                categoryNames.add(me2.getKey().toString());
                index = index + 1;
            }
        }

    }


    public void getDataFromatabase() {
        getCategoryDetail();

        tblDistributorDailyStockDetailsSKUReportArrayList=mDataSource.fnGetAllPersontblDistributorDailyStockDetailsSKUReport();

      /*  if (defaultCatName_Id.contains("^")) {
            ed_search.setText("");
            searchProduct(defaultCatName_Id.split(Pattern.quote("^"))[0], 0);
        }*/


        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().trim().length() == 0) {

                } else {
                    searchProduct(ed_search.getText().toString().trim(), 0);
//                    searchProduct(ed_search.getText().toString().trim(), "0", 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.clear();
        tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.addAll(stream(tblDistributorDailyStockDetailsSKUReportArrayList).toList());
        tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp = stream(tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp).orderBy(p -> p.getCategoryNodeID()).toList();

        fnMarkFirstProductCategoryRowVisible();
        orderAdapter = new DistributorStockStatusSKUWiseAdapter(this, tblDistributorDailyStockDetailsSKUReportArrayList);
        rvProducts.setAdapter(orderAdapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));



    }

    String SelectedCategoryForSchemeFileter = "All";
    int SelectedCategoryIDForSchemeFileter = 0;

    public void searchProduct(String filterSearchText, int ParentID) {
        tblDistributorDailyStockDetailsSKUReportArrayListForFilter.clear();

        SelectedCategoryForSchemeFileter = "All";
        SelectedCategoryIDForSchemeFileter = 0;

        tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.clear();


        List<String> searchStringsArray = new ArrayList<>();
        if (filterSearchText.contains(",")) {
            searchStringsArray.addAll(Arrays.asList(filterSearchText.toLowerCase().split(Pattern.quote(","))));
        }
        if (searchStringsArray.size() == 0) {
            if (filterSearchText.toLowerCase().equals("All".toLowerCase())) {
                tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.clear();
                tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.addAll(tblDistributorDailyStockDetailsSKUReportArrayList);
              //  tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp = stream(tblDistributorDailyStockDetailsSKUReportArrayList).where(p -> p.getCategory().toLowerCase().contains(filterSearchText.toLowerCase())).toList();
            } else {
                tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp = stream(tblDistributorDailyStockDetailsSKUReportArrayList).where(p -> p.getCategory().toLowerCase().contains(filterSearchText.toLowerCase()) || p.getSKUName().toLowerCase().contains(filterSearchText.toLowerCase())).toList();
            }
        } else {
            List<TblDistributorDailyStockDetailsSKUReport> tblStoreProductMappingListtempFilter = new ArrayList<>();

            if (SelectedCategoryForSchemeFileter.equals("All")) {
                for (int i = 0; i < tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.size(); i++) {
                    TblDistributorDailyStockDetailsSKUReport tblStoreProductMappingForDisplay = tblDistributorDailyStockDetailsSKUReportArrayList.get(i);
                    for (String s : searchStringsArray) {
                        if (tblStoreProductMappingForDisplay.getSKUName().toLowerCase().contains(s.toLowerCase())) {
                            tblStoreProductMappingListtempFilter.add(tblStoreProductMappingForDisplay);
                        }
                    }
                }
            } else {
                tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp = stream(tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp).where(p -> p.getCategoryNodeID() == ParentID).toList();
                for (int i = 0; i < tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.size(); i++) {
                    TblDistributorDailyStockDetailsSKUReport tblStoreProductMappingForDisplay = tblDistributorDailyStockDetailsSKUReportArrayList.get(i);
                    for (String s : searchStringsArray) {
                        if (tblStoreProductMappingForDisplay.getSKUName().toLowerCase().contains(s.toLowerCase())) {
                            tblStoreProductMappingListtempFilter.add(tblStoreProductMappingForDisplay);
                        }
                    }
                }
            }
            tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.clear();
            tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.addAll(tblStoreProductMappingListtempFilter);
        }


        Iterator<TblDistributorDailyStockDetailsSKUReport> crunchifyIteratornew = tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.iterator();
        if (tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp != null && tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.size() > 0) {

            while (crunchifyIteratornew.hasNext()) {
                TblDistributorDailyStockDetailsSKUReport tblStoreProductMappingRecord = crunchifyIteratornew.next();
                // if (tblStoreProductMappingRecord.getFlgBaseProduct() == 0 && tblStoreProductMappingRecord.getSBDGroupID() == 0 && tblStoreProductMappingRecord.getSBDPrdCount() == 0) {
                tblDistributorDailyStockDetailsSKUReportArrayListForFilter.add(tblStoreProductMappingRecord);
                // }
            }
        }


        fnMarkFirstProductCategoryRowVisible();
        orderAdapter.updateList(tblDistributorDailyStockDetailsSKUReportArrayListForFilter);

    }

    public void fnMarkFirstProductCategoryRowVisible() {

        tblDistributorDailyStockDetailsSKUReportArrayListForFilter.clear();

        Iterator<TblDistributorDailyStockDetailsSKUReport> crunchifyIterator = tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.iterator();
        if (tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp != null && tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.size() > 0) {
            int catid = 0;
            Set catSet = new HashSet();
            while (crunchifyIterator.hasNext()) {
                TblDistributorDailyStockDetailsSKUReport tblStoreProductMappingRecord = crunchifyIterator.next();
                if (!catSet.contains(tblStoreProductMappingRecord.getCategoryNodeID())) {
                    tblStoreProductMappingRecord.setFlgShowCategoryHeader(1);
                    tblStoreProductMappingRecord.setFlgMakeFrameVisible(1);
                    tblStoreProductMappingRecord.setFlgMakeRowVisible(1);
                    catSet.add(tblStoreProductMappingRecord.getCategoryNodeID());
                } else {
                    tblStoreProductMappingRecord.setFlgShowCategoryHeader(0);
                    tblStoreProductMappingRecord.setFlgMakeFrameVisible(1);
                    tblStoreProductMappingRecord.setFlgMakeRowVisible(1);
                }

            }
        }
        Iterator<TblDistributorDailyStockDetailsSKUReport> crunchifyIteratornew = tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.iterator();
        if (tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp != null && tblDistributorDailyStockDetailsSKUReportArrayListForFiltertemp.size() > 0) {

            while (crunchifyIteratornew.hasNext()) {
                TblDistributorDailyStockDetailsSKUReport tblStoreProductMappingRecord = crunchifyIteratornew.next();
                tblDistributorDailyStockDetailsSKUReportArrayListForFilter.add(tblStoreProductMappingRecord);
            }
        }
    }

    private void getDataFromIntent() {
        Intent i = getIntent();
        PersonName = i.getStringExtra("PersonName");
        DistributorName = i.getStringExtra("DistributorName");
        EntryDate = i.getStringExtra("EntryDate");

    }

    private void initializeFields() {

        getDataFromIntent();

        tv_distributorName=findViewById(R.id.tv_distributorName);
        tv_distributorName.setText(DistributorName);

        tv_PersonName=findViewById(R.id.tv_PersonName);
        tv_PersonName.setText(PersonName);

        tv_EntryDate=findViewById(R.id.tv_EntryDate);
        tv_EntryDate.setText("On:"+EntryDate);


        rvProducts = findViewById(R.id.rvProducts);

        img_ctgry = (ImageView) findViewById(R.id.img_ctgry);
        img_back_Btn = (ImageView) findViewById(R.id.img_back_Btn);
        ed_search = (EditText) findViewById(R.id.ed_search);


        ll_forTableHeaderName = (LinearLayout) findViewById(R.id.ll_forTableHeaderName);


        img_ctgry.setOnClickListener(view -> {
            customAlertStoreList(categoryNames, "Filter");
        });

        img_back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        fnGetDistributorList();

    }

    public void fnGetDistributorList() {

    }

    public void customAlertStoreList(List<String> listOption, String sectionHeader) {

        final Dialog listDialog = new Dialog(DistributorStockStatusSKUWise.this);
        listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        listDialog.setContentView(R.layout.search_list);
        listDialog.setCanceledOnTouchOutside(false);
        listDialog.setCancelable(false);
        WindowManager.LayoutParams parms = listDialog.getWindow().getAttributes();
        parms.gravity = Gravity.CENTER;
        //there are a lot of settings, for dialog, check them all out!
        parms.dimAmount = (float) 0.5;


        TextView txt_section = listDialog.findViewById(R.id.txt_section);
        txt_section.setText(sectionHeader);
        TextView txtVwCncl = listDialog.findViewById(R.id.txtVwCncl);

        final ListView list_store = listDialog.findViewById(R.id.list_store);
        final CardArrayAdapterCategory2 cardArrayAdapter = new CardArrayAdapterCategory2(DistributorStockStatusSKUWise.this, listOption, listDialog, previousSlctdCtgry, 0);

        //img_ctgry.setText(previousSlctdCtgry);


        list_store.setAdapter(cardArrayAdapter);
        //	editText.setBackgroundResource(R.drawable.et_boundary);
        img_ctgry.setEnabled(true);


        txtVwCncl.setOnClickListener(v -> {
            listDialog.dismiss();
            img_ctgry.setEnabled(true);
        });


        //now that the dialog is set up, it's time to show it
        listDialog.show();

    }

    @Override
    public void selectedOption(String selectedCategory, Dialog dialog) {
        dialog.dismiss();
        previousSlctdCtgry = selectedCategory;
        String lastTxtSearch = ed_search.getText().toString().trim();
        //img_ctgry.setText(selectedCategory);
        ed_search.setText(previousSlctdCtgry);
        if (hmapctgry_details.containsKey(selectedCategory)) {
            int patID = Integer.parseInt(hmapctgry_details.get(selectedCategory));
            searchProduct(selectedCategory, patID);
//            searchProduct(selectedCategory,hmapctgry_details.get(selectedCategory),0,patID);
        } else {
            searchProduct(selectedCategory, 0);
//            searchProduct(selectedCategory,"0",0,0);
        }


    }

    @Override
    public void selectedOption(String selectedCategory, Dialog dialog, int flgCompanyCompetitorProducts) {

    }




}
