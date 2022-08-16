package com.astix.rajtraderssfasmasales;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.astix.rajtraderssfasmasales.model.TblDistributorDailyStockDetailsReport;

import java.util.List;
import java.util.regex.Pattern;

public class DistributionShowListPersonAdapter extends RecyclerView.Adapter<DistributionShowListPersonAdapter.ViewHolder> {

    public Context context;

    List<TblDistributorDailyStockDetailsReport> tblStoreListMasterDataRetriveList;

    public DistributionShowListPersonAdapter(Context context,List<TblDistributorDailyStockDetailsReport> tblStoreListMasterDataRetriveList) {
        this.context = context;

        this.tblStoreListMasterDataRetriveList = tblStoreListMasterDataRetriveList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.distribution_person_selection_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final TblDistributorDailyStockDetailsReport masterDataRetrive = tblStoreListMasterDataRetriveList.get(position);


       // holder.tv_DistributorName.setText(masterDataRetrive.getCustomer().toString());
        holder.tv_Date.setText(masterDataRetrive.getLastEntryDate());
        holder.tv_StockKG.setText(""+(int)masterDataRetrive.getStockKg());
        holder.tv_StockCases.setText(""+(int)masterDataRetrive.getStockCases());
        holder.tv_StockValue.setText(""+masterDataRetrive.getStockValue());

        if(masterDataRetrive.getCustomer()==null)
        {
            masterDataRetrive.setCustomer("NA");
        }
        SpannableString spanString = new SpannableString(masterDataRetrive.getCustomer().toString());
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, android.R.color.holo_blue_dark)), 0, spanString.length(), 0);
        // spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tv_DistributorName.setText(spanString);

        holder.tv_DistributorName.setTag(masterDataRetrive.getCustomerNodeID()+"^"+masterDataRetrive.getPersonName()+"^"+masterDataRetrive.getLastEntryDate());
        holder.tv_DistributorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int DistNodeID=Integer.parseInt(view.getTag().toString().split(Pattern.quote("^"))[0]);
                String PersonName=view.getTag().toString().split(Pattern.quote("^"))[1];
                String LastEntryDate=view.getTag().toString().split(Pattern.quote("^"))[2];
                InterfaceRetrofitDistributorPersonStockDetails interfaceRetrofitDistributorPersonStockDetails = (InterfaceRetrofitDistributorPersonStockDetails) context;
                interfaceRetrofitDistributorPersonStockDetails.fnFilterDetailsForDistributor(DistNodeID,PersonName,LastEntryDate);
            }
        });
       // holder.tv_DistributorName.setTextSize(12);



   /*   holder.tvDistributorName.setText(masterDataRetrive.getCustomer().toString());
        holder.tv_PersonName.setText(masterDataRetrive.getPersonName().toString());
        holder.distributionstatus.setImageResource(R.drawable.n);
        holder.btn_ViewDetails.setEnabled(false);
        holder.btn_ViewDetails.setTag(masterDataRetrive.getCustomerNodeID()+"^"+masterDataRetrive.getPersonName());*/
      //  holder.btn_ViewDetails.setColorFilter(ContextCompat.getColor(context, R.color.graycolor), android.graphics.PorterDuff.Mode.MULTIPLY);

      /*  if(masterDataRetrive.getEntryStatus().equals("No"))
        {
            holder.btn_ViewDetails.setEnabled(false);
            holder.distributionstatus.setImageResource(R.drawable.n);
            holder.btn_ViewDetails.setColorFilter(ContextCompat.getColor(context, R.color.graycolor), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(masterDataRetrive.getEntryStatus().equals("Yes"))
        {

            holder.distributionstatus.setImageResource(R.drawable.y);
            holder.btn_ViewDetails.setEnabled(true);
            holder.btn_ViewDetails.setColorFilter(ContextCompat.getColor(context, R.color.blue), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        holder.btn_ViewDetails.setEnabled(true);
        holder.btn_ViewDetails.setColorFilter(ContextCompat.getColor(context, R.color.blue), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.btn_ViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int DistNodeID=Integer.parseInt(view.getTag().toString().split(Pattern.quote("^"))[0]);
                String PersonName=view.getTag().toString().split(Pattern.quote("^"))[1];
                InterfaceRetrofitDistributorPersonStockDetails interfaceRetrofitDistributorPersonStockDetails = (InterfaceRetrofitDistributorPersonStockDetails) context;
                interfaceRetrofitDistributorPersonStockDetails.fnFilterDetailsForDistributor(DistNodeID,PersonName);

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return tblStoreListMasterDataRetriveList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public View layout;

        public TextView tv_DistributorName, tv_Date,tv_StockKG,tv_StockCases,tv_StockValue;
      //  public ImageView distributionstatus,btn_ViewDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            tv_DistributorName = itemView.findViewById(R.id.tv_DistributorName);
            tv_Date = itemView.findViewById(R.id.tv_Date);
            tv_StockKG = itemView.findViewById(R.id.tv_StockKG);
            tv_StockCases = itemView.findViewById(R.id.tv_StockCases);
            tv_StockValue = itemView.findViewById(R.id.tv_StockValue);
           /* tv_PersonName = itemView.findViewById(R.id.tv_PersonName);
            tvDistributorName = itemView.findViewById(R.id.tvDistributorName);
            distributionstatus = itemView.findViewById(R.id.distributionstatus);
            btn_ViewDetails = itemView.findViewById(R.id.btn_ViewDetails);*/

        }
    }
}