package com.astix.rajtraderssfasmasales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.astix.Common.CommonInfo;
import com.astix.rajtraderssfasmasales.adapter.CustomVisitStockAdapter;
import com.astix.rajtraderssfasmasales.adapter.SchemeSlabAdapter;
import com.astix.rajtraderssfasmasales.model.ActualVisitProductInfo;
import com.astix.rajtraderssfasmasales.model.TblPersonGateMeetingFocusedProductCoverageWiseMstr;
import com.astix.rajtraderssfasmasales.model.TblPersonGateMeetingTarget;
import com.astix.rajtraderssfasmasales.model.TblStoreListMasterDataRetrive;
import com.astix.rajtraderssfasmasales.model.TblStoreProductMappingForDisplay;
import com.astix.rajtraderssfasmasales.model.TblUOMMaster;
import com.astix.rajtraderssfasmasales.utils.AppUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static br.com.zbra.androidlinq.Linq.stream;

public class PersonGateMeetingTargetListAdapter extends RecyclerView.Adapter<PersonGateMeetingTargetListAdapter.ViewHolder> {

    public Context context;

    List<TblPersonGateMeetingTarget> tblStoreListMasterDataRetriveList;
    String userDate, pickerDate;
    ProductFilledDataModelGateEntry productFilledDataModelGateEntry;
    private CustomKeyboard customKeyboard;
    private EditTextClickListener editTextClickListener;
    InterfaceShowPersonGateMeetingOldDataAgainstPerson interfaceShowPersonGateMeetingOldDataAgainstPerson;
    public PersonGateMeetingTargetListAdapter(Context context, List<TblPersonGateMeetingTarget> tblStoreListMasterDataRetriveList, String userDate, String pickerDate,ProductFilledDataModelGateEntry productFilledDataModelGateEntry, CustomKeyboard customKeyboard) {
        this.context = context;
        interfaceShowPersonGateMeetingOldDataAgainstPerson=(InterfaceShowPersonGateMeetingOldDataAgainstPerson) context;
        this.tblStoreListMasterDataRetriveList = tblStoreListMasterDataRetriveList;
        this.pickerDate = pickerDate;
        this.userDate = userDate;
        this.productFilledDataModelGateEntry=productFilledDataModelGateEntry;
        this.customKeyboard = customKeyboard;
        editTextClickListener = (EditTextClickListener) context;

    }
    public interface EditTextClickListener {
        void onClick(EditText editText);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gate_meeting_target_selection_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final TblPersonGateMeetingTarget masterDataRetrive = tblStoreListMasterDataRetriveList.get(position);


        holder.tvRouteName.setText(masterDataRetrive.getCovArea());
        holder.ll_RouteHeader.setVisibility(View.GONE);
        if (masterDataRetrive.getFlgShowHeader() == 1) {
            holder.ll_RouteHeader.setVisibility(View.VISIBLE);
        }

        if(productFilledDataModelGateEntry.getHmapPersonDistributionTarget()!=null && productFilledDataModelGateEntry.getHmapPersonDistributionTarget().size()>0 && productFilledDataModelGateEntry.getHmapPersonDistributionTarget().containsKey(masterDataRetrive.getPersonNodeID()))
        {
            double disval=Double.parseDouble(productFilledDataModelGateEntry.getHmapPersonDistributionTarget().get(masterDataRetrive.getPersonNodeID()));
            holder.et_DistVal.setText(""+(int)disval);
        }
        if(productFilledDataModelGateEntry.getHmapPersonSalesTarget()!=null && productFilledDataModelGateEntry.getHmapPersonSalesTarget().size()>0 && productFilledDataModelGateEntry.getHmapPersonSalesTarget().containsKey(masterDataRetrive.getPersonNodeID()))
        {
            holder.et_stckVal.setText(productFilledDataModelGateEntry.getHmapPersonSalesTarget().get(masterDataRetrive.getPersonNodeID()));
        }

       /* if(productFilledDataModelGateEntry.getHmapPersonFocusProductDistributionTarget()!=null && productFilledDataModelGateEntry.getHmapPersonFocusProductDistributionTarget().size()>0 && productFilledDataModelGateEntry.getHmapPersonFocusProductDistributionTarget().containsKey(masterDataRetrive.getPersonNodeID()))
        {
            double disval=Double.parseDouble(productFilledDataModelGateEntry.getHmapPersonFocusProductDistributionTarget().get(masterDataRetrive.getPersonNodeID()));
            holder.et_prdDistiTgt.setText(""+(int)disval);
        }

        if(productFilledDataModelGateEntry.getHmapPersonFocusProductSalesTarget()!=null && productFilledDataModelGateEntry.getHmapPersonFocusProductSalesTarget().size()>0 && productFilledDataModelGateEntry.getHmapPersonFocusProductSalesTarget().containsKey(masterDataRetrive.getPersonNodeID()))
        {
            double disval=Double.parseDouble(productFilledDataModelGateEntry.getHmapPersonFocusProductSalesTarget().get(masterDataRetrive.getPersonNodeID()));
            holder.et_prd_fcs_Tgt.setText(""+(int)disval);
        }*/
//        holder.tv_StoreName.setText(masterDataRetrive.getPersonname());


        //tv_MonthTarget,tv_Achievment,tv_RRRquired,tvTodaysStore,tvProdStoreLastCall

        double Achievement=Double.parseDouble(new DecimalFormat("##.##").format(masterDataRetrive.getMonthTarget()));
        double RRRquired=Double.parseDouble(new DecimalFormat("##.##").format(masterDataRetrive.getRRRequired()));
        holder.tv_MonthTarget.setText(""+masterDataRetrive.getMonthTarget());
        holder.tv_Achievment.setText(""+Achievement);
        holder.tv_RRRquired.setText(""+RRRquired);
        holder.tvTodaysStore.setText(""+(int)masterDataRetrive.getTodaysStore());
        holder.tvProdStoreLastCall.setText(""+(int)masterDataRetrive.getProdStoreLastCall() +" ("+masterDataRetrive.getLastRouteVisitDate()+")");

        //holder.tv_prdName.setText(masterDataRetrive.getFocusProduct());
        holder.rvFocusProductConatiner.setVisibility(View.GONE);
        if(masterDataRetrive.getTblPersonGateMeetingFocusedProductCoverageWiseMstrList()!=null && masterDataRetrive.getTblPersonGateMeetingFocusedProductCoverageWiseMstrList().size()>0)
        {
            holder.rvFocusProductConatiner.setVisibility(View.VISIBLE);
            List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> tblPersonGateMeetingFocusedProductCoverageWiseMstrList=masterDataRetrive.getTblPersonGateMeetingFocusedProductCoverageWiseMstrList();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            holder.rvFocusProductConatiner.setLayoutManager(linearLayoutManager);


                if (tblPersonGateMeetingFocusedProductCoverageWiseMstrList != null && tblPersonGateMeetingFocusedProductCoverageWiseMstrList.size()>0) {
                    holder.rvFocusProductConatiner.setVisibility(View.VISIBLE);
                    holder.rvFocusProductConatiner.setAdapter(new PersonGateMeetingFocusProductAdapterAdapter(context, tblPersonGateMeetingFocusedProductCoverageWiseMstrList,customKeyboard,productFilledDataModelGateEntry));

                }

        }

        holder.et_DistVal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View view, boolean b) {
                if (view instanceof EditText) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });

                    editTextClickListener.onClick((EditText) view);
                }
            }
        });

        holder.et_stckVal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View view, boolean b) {
                if (view instanceof EditText) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });

                    editTextClickListener.onClick((EditText) view);
                }
            }
        });

     /*   holder.et_prd_fcs_Tgt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View view, boolean b) {
                if (view instanceof EditText) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });

                    editTextClickListener.onClick((EditText) view);
                }
            }
        });

        holder.et_prdDistiTgt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View view, boolean b) {
                if (view instanceof EditText) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });

                    editTextClickListener.onClick((EditText) view);
                }
            }
        });
*/

       /* TextChangedListener textChangedListenerOrderQty = new TextChangedListener(holder.et_DistVal, holder.et_stckVal, 1,masterDataRetrive.getPersonNodeID());
        TextChangedListener textChangedListenerDiscount = new TextChangedListener(holder.et_DistVal, holder.et_stckVal, 4,masterDataRetrive.getPersonNodeID());
        holder.et_DistVal.addTextChangedListener(textChangedListenerOrderQty);
        holder.et_stckVal.addTextChangedListener(textChangedListenerDiscount);*/


        holder.et_DistVal.setTag("Dist_"+masterDataRetrive.getPersonNodeID());
        holder.et_stckVal.setTag("Sales_"+masterDataRetrive.getPersonNodeID());

        /* holder.et_prd_fcs_Tgt.setTag("ProductSales_"+masterDataRetrive.getPersonNodeID());
        holder.et_prdDistiTgt.setTag("ProductDist_"+masterDataRetrive.getPersonNodeID());*/

        /*holder.tv_oldPlan.setTag("OldPlanTarget_"+masterDataRetrive.getPersonNodeID()+"_"+masterDataRetrive.getPersonname());
        holder.tv_oldPlan.setTypeface(null, Typeface.BOLD);
        SpannableString spanString = new SpannableString(holder.tv_oldPlan.getText());
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.tv_oldPlan.setText(spanString);
        holder.tv_oldPlan.setTextSize(12);

        holder.tv_oldPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceShowPersonGateMeetingOldDataAgainstPerson.fnCallOldDetailsAgainstSalesPerson(Integer.parseInt(view.getTag().toString().split(Pattern.quote("_"))[0]),view.getTag().toString().split(Pattern.quote("_"))[1]);
            }
        });*/

        holder.et_DistVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String PersonNodeID=holder.et_DistVal.getTag().toString().split(Pattern.quote("_"))[1];
                        if (!TextUtils.isEmpty(holder.et_DistVal.getText().toString().trim())) {
                            productFilledDataModelGateEntry.setPersonDistributionTarget(PersonNodeID,holder.et_DistVal.getText().toString());
                        }
                        else {
                            productFilledDataModelGateEntry.removePersonDistributionTarget(PersonNodeID);

                        }
                    }
                });
            }
        });

        holder.et_stckVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String PersonNodeID=holder.et_stckVal.getTag().toString().split(Pattern.quote("_"))[1];
                        if (!TextUtils.isEmpty(holder.et_stckVal.getText().toString().trim())) {
                            productFilledDataModelGateEntry.setPersonSalesTarget(PersonNodeID,holder.et_stckVal.getText().toString());
                        }
                        else {
                            productFilledDataModelGateEntry.removePersonSalesTarget(PersonNodeID);
                        }
                    }
                });
            }
        });



/*       holder.et_prdDistiTgt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String PersonNodeID=holder.et_prdDistiTgt.getTag().toString().split(Pattern.quote("_"))[1];
                        if (!TextUtils.isEmpty(holder.et_prdDistiTgt.getText().toString().trim())) {
                            productFilledDataModelGateEntry.setFocusedProductDistributionTarget(PersonNodeID,holder.et_prdDistiTgt.getText().toString());
                        }
                        else {
                            productFilledDataModelGateEntry.removeFocusedProductDistributionTarget(PersonNodeID);

                        }
                    }
                });
            }
        });


        holder.et_prd_fcs_Tgt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String PersonNodeID=holder.et_prd_fcs_Tgt.getTag().toString().split(Pattern.quote("_"))[1];
                        if (!TextUtils.isEmpty(holder.et_prd_fcs_Tgt.getText().toString().trim())) {
                            productFilledDataModelGateEntry.setFocusedProductSalesTarget(PersonNodeID,holder.et_prd_fcs_Tgt.getText().toString());
                        }
                        else {
                            productFilledDataModelGateEntry.removeFocusedProductSalesTarget(PersonNodeID);

                        }
                    }
                });
            }
        });*/



    }

    @Override
    public int getItemCount() {
        return tblStoreListMasterDataRetriveList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public View layout;
        public TextView tv_StoreName,tvRouteName,tv_MonthTarget,tv_Achievment,tv_RRRquired,tvTodaysStore,tvProdStoreLastCall,tv_oldPlan,tv_prdName;
        public EditText et_DistVal, et_stckVal,et_prd_fcs_Tgt,et_prdDistiTgt;
        public CardView cv1;
        RecyclerView rvFocusProductConatiner;

        LinearLayout ll_RouteHeader;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            cv1 = itemView.findViewById(R.id.cv1);
            ll_RouteHeader = itemView.findViewById(R.id.ll_RouteHeader);
            tvRouteName = itemView.findViewById(R.id.tvRouteName);

            tv_RRRquired = itemView.findViewById(R.id.tv_RRRquired);
            tv_MonthTarget = itemView.findViewById(R.id.tv_MonthTarget);
            tv_Achievment = itemView.findViewById(R.id.tv_Achievment);
            tvTodaysStore = itemView.findViewById(R.id.tvTodaysStore);
            tvProdStoreLastCall = itemView.findViewById(R.id.tvProdStoreLastCall);

            et_DistVal = itemView.findViewById(R.id.et_DistVal);
            et_stckVal = itemView.findViewById(R.id.et_stckVal);
            rvFocusProductConatiner=itemView.findViewById(R.id.rvFocusProductConatiner);
           /* et_prd_fcs_Tgt = itemView.findViewById(R.id.et_prd_fcs_Tgt);
            et_prdDistiTgt = itemView.findViewById(R.id.et_prdDistiTgt);
            tv_prdName=itemView.findViewById(R.id.tv_prdName);*/
            //tv_oldPlan=itemView.findViewById(R.id.tv_oldPlan);

            tv_StoreName = itemView.findViewById(R.id.tv_StoreName);

            et_DistVal.setOnClickListener(v -> {
                InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
            });
            et_stckVal.setOnClickListener(v -> {
                InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
            });

           /* et_prd_fcs_Tgt.setOnClickListener(v -> {
                InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
            });

            et_prdDistiTgt.setOnClickListener(v -> {
                InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
            });*/

        }
    }



    class TextChangedListener implements TextWatcher {
        EditText etdistri,etSales;
        int flgClickBtn = 0;
        String PersonNodeID="0";


        public TextChangedListener(EditText etdistri, EditText etSales, int flgClickBtn,String PersonNodeID) {//, int upc
            this.etdistri = etdistri;
            this.etSales = etSales;
            this.flgClickBtn = flgClickBtn;
            this.PersonNodeID=PersonNodeID;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(flgClickBtn==1) {
                if (!TextUtils.isEmpty(etdistri.getText().toString().trim())) {
                    productFilledDataModelGateEntry.setPersonDistributionTarget(PersonNodeID,etdistri.getText().toString());
                }
                else {
                    productFilledDataModelGateEntry.removePersonDistributionTarget(PersonNodeID);

                }
            }


            if(flgClickBtn==4)
            {
                if (!TextUtils.isEmpty(etSales.getText().toString().trim())) {
                    productFilledDataModelGateEntry.setPersonSalesTarget(PersonNodeID,etSales.getText().toString());
                }
                else {
                    productFilledDataModelGateEntry.removePersonSalesTarget(PersonNodeID);
                }

            }
        }
    }
}