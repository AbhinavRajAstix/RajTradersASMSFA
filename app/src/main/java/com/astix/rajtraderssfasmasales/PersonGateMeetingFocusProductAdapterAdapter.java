package com.astix.rajtraderssfasmasales;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astix.rajtraderssfasmasales.model.TblPersonGateMeetingFocusedProductCoverageWiseMstr;

import java.util.List;
import java.util.regex.Pattern;

public class PersonGateMeetingFocusProductAdapterAdapter extends RecyclerView.Adapter<PersonGateMeetingFocusProductAdapterAdapter.ViewHolder> {

    private Context mContext;
    ProductFilledDataModelGateEntry productFilledDataModelGateEntry;
    private List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> tblPersonGateMeetingFocusedProductCoverageWiseMstrList;
    private CustomKeyboard customKeyboard;
    private PersonGateMeetingTargetListAdapter.EditTextClickListener editTextClickListener;
    public PersonGateMeetingFocusProductAdapterAdapter(Context mContext, List<TblPersonGateMeetingFocusedProductCoverageWiseMstr> tblPersonGateMeetingFocusedProductCoverageWiseMstrList, CustomKeyboard customKeyboard, ProductFilledDataModelGateEntry productFilledDataModelGateEntry) {
        this.mContext = mContext;
        this.tblPersonGateMeetingFocusedProductCoverageWiseMstrList = tblPersonGateMeetingFocusedProductCoverageWiseMstrList;
        this.customKeyboard = customKeyboard;
        editTextClickListener = (PersonGateMeetingTargetListAdapter.EditTextClickListener) mContext;
        this.productFilledDataModelGateEntry=productFilledDataModelGateEntry;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.gate_meeting_focus_product_selection_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TblPersonGateMeetingFocusedProductCoverageWiseMstr tblPersonGateMeetingFocusedProductCoverageWiseMstr = tblPersonGateMeetingFocusedProductCoverageWiseMstrList.get(position);
        holder.tv_prdName.setText(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProduct());
        holder.et_prd_fcs_Tgt.setTag("ProductSales_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType());
        holder.et_prdDistiTgt.setTag("ProductDist_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType());

        if(productFilledDataModelGateEntry.getHmapPersonFocusProductDistributionTarget()!=null && productFilledDataModelGateEntry.getHmapPersonFocusProductDistributionTarget().size()>0 && productFilledDataModelGateEntry.getHmapPersonFocusProductDistributionTarget().containsKey(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType()))
        {
            double disval=Double.parseDouble(productFilledDataModelGateEntry.getHmapPersonFocusProductDistributionTarget().get(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType()));
            holder.et_prdDistiTgt.setText(""+(int)disval);
        }

        if(productFilledDataModelGateEntry.getHmapPersonFocusProductSalesTarget()!=null && productFilledDataModelGateEntry.getHmapPersonFocusProductSalesTarget().size()>0 && productFilledDataModelGateEntry.getHmapPersonFocusProductSalesTarget().containsKey(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType()))
        {
            double disval=Double.parseDouble(productFilledDataModelGateEntry.getHmapPersonFocusProductSalesTarget().get(tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType()));
            holder.et_prd_fcs_Tgt.setText(""+disval);
        }

        holder.et_prd_fcs_Tgt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View view, boolean b) {
                if (view instanceof EditText) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager keyboard = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
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
                            InputMethodManager keyboard = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    });

                    editTextClickListener.onClick((EditText) view);
                }
            }
        });

        holder.et_prdDistiTgt.addTextChangedListener(new TextWatcher() {
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
                        //"ProductSales_"+tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeID()+"_"+
                        // tblPersonGateMeetingFocusedProductCoverageWiseMstr.getCovAreaNodeType()+"_"+
                        // tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeID()+"_"+
                        // tblPersonGateMeetingFocusedProductCoverageWiseMstr.getFocusProductNodeType());
                        String CovAreaNodeID=holder.et_prdDistiTgt.getTag().toString().split(Pattern.quote("_"))[1];
                        String CovAreaNodeType=holder.et_prdDistiTgt.getTag().toString().split(Pattern.quote("_"))[2];
                        String FocusProductNodeID=holder.et_prdDistiTgt.getTag().toString().split(Pattern.quote("_"))[3];
                        String FocusProductNodeType=holder.et_prdDistiTgt.getTag().toString().split(Pattern.quote("_"))[4];
                        String ProductRealtedDetails=CovAreaNodeID+"_"+CovAreaNodeType+"_"+FocusProductNodeID+"_"+FocusProductNodeType;
                        if (!TextUtils.isEmpty(holder.et_prdDistiTgt.getText().toString().trim())) {
                            productFilledDataModelGateEntry.setFocusedProductDistributionTarget(ProductRealtedDetails,holder.et_prdDistiTgt.getText().toString());
                        }
                        else {
                            productFilledDataModelGateEntry.removeFocusedProductDistributionTarget(ProductRealtedDetails);

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
                        String CovAreaNodeID=holder.et_prdDistiTgt.getTag().toString().split(Pattern.quote("_"))[1];
                        String CovAreaNodeType=holder.et_prdDistiTgt.getTag().toString().split(Pattern.quote("_"))[2];
                        String FocusProductNodeID=holder.et_prdDistiTgt.getTag().toString().split(Pattern.quote("_"))[3];
                        String FocusProductNodeType=holder.et_prdDistiTgt.getTag().toString().split(Pattern.quote("_"))[4];
                        String ProductRealtedDetails=CovAreaNodeID+"_"+CovAreaNodeType+"_"+FocusProductNodeID+"_"+FocusProductNodeType;

                        if (!TextUtils.isEmpty(holder.et_prd_fcs_Tgt.getText().toString().trim())) {
                            productFilledDataModelGateEntry.setFocusedProductSalesTarget(ProductRealtedDetails,holder.et_prd_fcs_Tgt.getText().toString());
                        }
                        else {
                            productFilledDataModelGateEntry.removeFocusedProductSalesTarget(ProductRealtedDetails);

                        }
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return tblPersonGateMeetingFocusedProductCoverageWiseMstrList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_prdName;
        public EditText et_prd_fcs_Tgt,et_prdDistiTgt;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_prdName = itemView.findViewById(R.id.tv_prdName);
            et_prd_fcs_Tgt = itemView.findViewById(R.id.et_prd_fcs_Tgt);
            et_prdDistiTgt = itemView.findViewById(R.id.et_prdDistiTgt);
        }
    }
}
