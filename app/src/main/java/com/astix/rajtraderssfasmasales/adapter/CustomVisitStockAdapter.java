package com.astix.rajtraderssfasmasales.adapter;

import static br.com.zbra.androidlinq.Linq.stream;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.astix.rajtraderssfasmasales.CustomKeyboard;
import com.astix.rajtraderssfasmasales.R;
import com.astix.rajtraderssfasmasales.model.ActualVisitProductInfo;
import com.astix.rajtraderssfasmasales.model.TblUOMMapping;
import com.astix.rajtraderssfasmasales.model.TblUOMMaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class CustomVisitStockAdapter extends RecyclerView.Adapter<CustomVisitStockAdapter.ViewHolder> {

    private CustomKeyboard customKeyboard;
    private Context mContext;
    private List<ActualVisitProductInfo> productInfoList;
    private String storeId;
    private Map<String, ActualVisitProductInfo> hmapFetchPDASavedDataState = new HashMap<>();
    private boolean isDefaultProducts = false;
    private Map<String, ActualVisitProductInfo> hmapFetchPDASavedData;
    private static final String TAG = CustomVisitStockAdapter.class.getName();
    private EditTextClickListener editTextClickListener;
    HashMap<Integer, List<TblUOMMapping>> tblUOMListMaster;
    String[] UOM_names;
    ArrayAdapter adapterCategory;

    public CustomVisitStockAdapter(Context mContext, List<ActualVisitProductInfo> productInfoList, String storeId, Map<String, ActualVisitProductInfo> hmapFetchPDASavedData, boolean isDefaultProducts, CustomKeyboard customKeyboard, HashMap<Integer, List<TblUOMMapping>> tblUOMListMaster, String[] UOM_names) {
        this.mContext = mContext;
        this.productInfoList = productInfoList;
        this.storeId = storeId;
        this.hmapFetchPDASavedData = hmapFetchPDASavedData;
        this.isDefaultProducts = isDefaultProducts;
        this.customKeyboard = customKeyboard;
        this.tblUOMListMaster = tblUOMListMaster;
        this.UOM_names = UOM_names;
        editTextClickListener = (EditTextClickListener) mContext;
        //  customKeyboard = new CustomKeyboard((Activity) mContext,R.id.keyboardviewNum,R.xml.num);
        Log.d(TAG, "productInfoList :" + this.productInfoList.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.inflate_row_actual_visit, parent, false);
        return new ViewHolder(view);
    }

    RecyclerView recyclerView;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        //recyclerView.getLayoutManager().findViewByPosition()
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ActualVisitProductInfo productInfo = productInfoList.get(position);
        hmapFetchPDASavedDataState.put("" + holder.getAdapterPosition(), productInfo);
        holder.productName.setText(productInfo.getProductName());


//        holder.stockUnitET.setText(productInfo.getDisplayUnit());


        if (hmapFetchPDASavedDataState.get("" + holder.getAdapterPosition()).getStock() != null) {
            if (hmapFetchPDASavedDataState.get("" + holder.getAdapterPosition()).getStock().equals("-1")) {
                holder.stockValueET.setText("");
            } else {
                holder.stockValueET.setText(hmapFetchPDASavedDataState.get("" + holder.getAdapterPosition()).getStock());
            }
        } else {
            holder.stockValueET.setText("");
        }

        holder.stockValueET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View v, boolean b) {
                if (v instanceof EditText) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager keyboard = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    });

                    editTextClickListener.onClick((EditText) v);
                }
            }
        });

    /*    new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {

                return false;
            }
        });*/
        /*holder.stockValueET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View v, boolean hasFocus) {
                if (hasFocus) {
                    //EditText editText = (EditText) recyclerView.getLayoutManager().findViewByPosition(holder.getAdapterPosition()).findViewById(R.id.et_stckVal);
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager keyboard = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);

                            editTextClickListener.onClick((EditText) v);

                        }
                    });

                }
            }
        });*//*
        holder.stockValueET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = recyclerView.getLayoutManager().findViewByPosition(holder.getAdapterPosition()).findViewById(R.id.et_stckVal);
                customKeyboard.showCustomKeyboard(view);
            }
        });*/

        holder.stockValueET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        ActualVisitProductInfo visitProductInfo = null;
                        if (!TextUtils.isEmpty(holder.stockValueET.getText().toString().trim())) {
                            //String tagProductId = holder.stockValueET.getTag().toString().split(Pattern.quote("_"))[0];
                            String stock = holder.stockValueET.getText().toString().trim();
                            visitProductInfo = hmapFetchPDASavedDataState.get("" + holder.getAdapterPosition());
                            visitProductInfo.setStock(stock);

                            hmapFetchPDASavedData.put(visitProductInfo.getProductId(), visitProductInfo);
                            hmapFetchPDASavedDataState.put("" + holder.getAdapterPosition(), visitProductInfo);
                        } else {
                            visitProductInfo = hmapFetchPDASavedDataState.get("" + holder.getAdapterPosition());
                            visitProductInfo.setStock("");

                            hmapFetchPDASavedDataState.put("" + holder.getAdapterPosition(), visitProductInfo);
                            hmapFetchPDASavedData.remove(visitProductInfo.getProductId());
                        }
                    }
                });
            }
        });
        int flgIndex = 0;

//        for (int ji = 0; ji < productInfoList.getTblUOMMasterList().size(); ji++) {
//            //for (TblUOMMaster uomMaster : tblStoreProductMapping.getTblUOMMasterList()) {
//            int i = 0;
//            if (tblStoreProductMapping.getTblUOMMasterList().get(ji).getFlgSelected() == 1) {
//                flgIndex = ji;
//                break;
//            }
//
//        }

        List<String> UOMMasterDAta = stream(productInfo.getTblUOMMasterList()).select(p -> p.getBUOMName()).toList();
        holder.spinnerUnit.setTag(productInfo.getProductId() + "^" + position);


        adapterCategory = new ArrayAdapter(mContext, R.layout.spinner_item_3, UOMMasterDAta);
        adapterCategory.setDropDownViewResource(R.layout.spina2);
        holder.spinnerUnit.setAdapter(adapterCategory);


        List<TblUOMMaster> tblUOMMastersForPrd = stream(productInfo.getTblUOMMasterList()).where(p -> p.getFlgSelected() == 1).toList();

        int index = 0;
        if (tblUOMListMaster != null && tblUOMListMaster.size() > 0) {
            Set set2 = tblUOMListMaster.entrySet();
            Iterator iterator = set2.iterator();
            boolean isSelected = false;
            while (iterator.hasNext()) {
                Map.Entry me2 = (Map.Entry) iterator.next();
                //if (prdctModelArrayList.getHmapflgPicsOrCases().get(prductId).equals(me2.getValue())) {
                if (tblUOMMastersForPrd.size()>0 && tblUOMMastersForPrd.get(0).getBUOMName().equals(me2.getValue())) {
                    isSelected = true;
                    break;
                }
                index = index + 1;
            }
            if (isSelected) {
                holder.spinnerUnit.setSelection(index);
            } else {
                holder.spinnerUnit.setSelection(0);
                // spinner_RouteChangeListOptions.setSelection(0);
            }
        }


        holder.spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                Spinner v1 = (Spinner) view.getParent();
                int i = Integer.parseInt(v1.getTag().toString().trim().split(Pattern.quote("^"))[1]);
                productInfoList.get(i).setDisplayUnit(v1.getSelectedItem().toString());
//                Spinner v1 = (Spinner) view.getParent();
//                TableLayout v2 = (TableLayout) v1.getParent().getParent();
//                String crntPrdID = v1.getTag().toString().trim().split(Pattern.quote("^"))[0];
//                int i = Integer.parseInt(v1.getTag().toString().trim().split(Pattern.quote("^"))[1]);
//                List<TblUOMMaster> UOMMasterDAtaSingleDat = stream(mTblStoreProductMappings.get(i).getTblUOMMasterList()).where(p -> p.getBUOMName().equals(v1.getSelectedItem().toString())).toList();
//                prdctModelArrayList.setPrdctQtyMappedToPicsOrCases(crntPrdID, "" + UOMMasterDAtaSingleDat.get(0).getBUOMID());
//                //Will change to dropdown selected  OptionID
//
//                List<TblUOMMaster> tblUOMMasterListAgainstProduct = mTblStoreProductMappings.get(i).getTblUOMMasterList();
//                for (TblUOMMaster tblUOMMaster : tblUOMMasterListAgainstProduct) {
//                    tblUOMMaster.setFlgSelected(0);
//                    if (tblUOMMaster.getBUOMID() == Integer.parseInt(prdctModelArrayList.getHmapflgPicsOrCases().get("" + crntPrdID))) {
//                        tblUOMMaster.setFlgSelected(1);
//                    }
//                }
//
//
//                List<TblUOMMaster> tblUOMMastersForPrd = stream(mTblStoreProductMappings.get(i).getTblUOMMasterList()).where(p -> p.getFlgSelected() == 1).toList();
//                String prdctOrderQtyEntered = prdctModelArrayList.getPrdctOrderQtyToShow("" + crntPrdID);
//                double StandardRate = tblUOMMastersForPrd.get(0).getStandardRate();
//                Double PrdRateCases = tblUOMMastersForPrd.get(0).getStandardRate();// * conversionUnit;
//                PrdRateCases = Double.parseDouble(new DecimalFormat("##.##").format(PrdRateCases));
//                TextView txtVwRate = v2.findViewWithTag(crntPrdID + "_etPrdRate");
//                txtVwRate.setText("" + PrdRateCases);
//
//                int conversionUnit = 1;
//                if (UOMMasterDAtaSingleDat.get(0).getBUOMID() != 3) {
//                    conversionUnit = (int) (tblUOMMastersForPrd.get(0).getRelConversionUnit());
//                }
//
//                if (!prdctOrderQtyEntered.equals("")) {
//
//
//                    Double OrderValPrdQtyBasis = StandardRate * Double.parseDouble(prdctOrderQtyEntered);
//
//                    prdctModelArrayList.setPrdctVal("" + crntPrdID, "" + OrderValPrdQtyBasis);
//                    prdctModelArrayList.getHmapOrderQtyDataToShow().put(crntPrdID, prdctOrderQtyEntered);
//                    prdctModelArrayList.getHmapPrdctOrderQty().put(crntPrdID, "" + (Integer.parseInt(prdctOrderQtyEntered) * conversionUnit));
//                    double OrderValPrdQtyBasisToDisplay = Double.parseDouble(new DecimalFormat("##.##").format(OrderValPrdQtyBasis));
//                    TextView txtOrderVal = v2.findViewWithTag(crntPrdID + "_tvOrderval");
//                    txtOrderVal.setText("" + OrderValPrdQtyBasisToDisplay);
//                    //interfacefocusLostCalled.fcsLstCld(false, (EditText) v2.findViewWithTag(crntPrdID + "_etOrderQty"));
//
//                } else {
//                    Double OrderValPrdQtyBasis = StandardRate * 0;
//
//                    prdctModelArrayList.setPrdctVal("" + crntPrdID, "" + OrderValPrdQtyBasis);
//                    prdctModelArrayList.getHmapOrderQtyDataToShow().get(crntPrdID);
//                    prdctModelArrayList.getHmapPrdctOrderQty().remove(crntPrdID);
//                    double OrderValPrdQtyBasisToDisplay = Double.parseDouble(new DecimalFormat("##.##").format(OrderValPrdQtyBasis));
//                    TextView txtOrderVal = v2.findViewWithTag(crntPrdID + "_tvOrderval");
//                    txtOrderVal.setText("" + OrderValPrdQtyBasisToDisplay);
//                }
//
//                interfacefocusLostCalled.fcsLstCld(false, (EditText) v2.findViewWithTag(crntPrdID + "_etOrderQty"));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void refreshData(ArrayList<ActualVisitProductInfo> updatedActualVisitProductInfos) {
        for (ActualVisitProductInfo visitProductInfo : updatedActualVisitProductInfos) {
            if (hmapFetchPDASavedData.containsKey(visitProductInfo.getProductId())) {
                visitProductInfo.setStock(hmapFetchPDASavedData.get(visitProductInfo.getProductId()).getStock());
            }
        }
        this.productInfoList = updatedActualVisitProductInfos;
        hmapFetchPDASavedDataState.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productInfoList.size();
    }

    public interface EditTextClickListener {
        void onClick(EditText editText);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private EditText stockValueET;
        private TextView productName;
        //        private LabeledSwitch swPcsToCase;
        public Spinner spinnerUnit;

        public ViewHolder(final View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.tvPrdName);
            stockValueET = (EditText) itemView.findViewById(R.id.et_stckVal);
            spinnerUnit = itemView.findViewById(R.id.spinnerUnit);
//            swPcsToCase = itemView.findViewById(R.id.swPcsToCase);
//            swPcsToCase.setLabelOn("Pcs");
//            swPcsToCase.setLabelOff("Case");

            stockValueET.setOnClickListener(v -> {
                InputMethodManager keyboard = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
            });
        }
    }
}
