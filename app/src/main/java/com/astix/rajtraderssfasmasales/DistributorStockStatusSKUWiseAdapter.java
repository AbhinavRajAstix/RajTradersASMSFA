package com.astix.rajtraderssfasmasales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astix.rajtraderssfasmasales.model.TblDistributorDailyStockDetailsSKUReport;

import java.util.List;

public class DistributorStockStatusSKUWiseAdapter extends RecyclerView.Adapter<DistributorStockStatusSKUWiseAdapter.ViewHolder> {
    ArrayAdapter adapterCategory;
    private final LayoutInflater inflater;
    Context context;
    List<TblDistributorDailyStockDetailsSKUReport> mTblStoreProductMappings;

    RecyclerView recyclerView;

    public DistributorStockStatusSKUWiseAdapter(Context context,  List<TblDistributorDailyStockDetailsSKUReport> tblStoreProductMappingList ) {

        this.mTblStoreProductMappings = tblStoreProductMappingList;

        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_person_date_wise_sku_stock_list, parent, false);

        return new ViewHolder(view);
    }

    public void updateList(List<TblDistributorDailyStockDetailsSKUReport> mTblStoreProductMappings) {
        this.mTblStoreProductMappings = mTblStoreProductMappings;
        recyclerView.post(this::notifyDataSetChanged);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TblDistributorDailyStockDetailsSKUReport tblStoreProductMapping = mTblStoreProductMappings.get(holder.getAdapterPosition());
        String prductName = tblStoreProductMapping.getSKUName();

        holder.ll_HeaderSection.setVisibility(View.GONE);

        if (tblStoreProductMapping.getFlgShowCategoryHeader() == 1) {
            holder.ll_HeaderSection.setVisibility(View.VISIBLE);
        } else {
            holder.ll_HeaderSection.setVisibility(View.GONE);
        }

        holder.tvSKUType.setText(tblStoreProductMapping.getCategory());

        holder.tvProdctName.setText(prductName);

        holder.tv_stockKg.setText(""+tblStoreProductMapping.getStockKgQty());
        holder.tv_stockCase.setText(""+tblStoreProductMapping.getStockCaseQty());
        holder.tv_stockOrdVal.setText(""+tblStoreProductMapping.getStockValue());




    }


    @Override
    public int getItemCount() {
        // return listProduct.length;
        return mTblStoreProductMappings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProdctName, tvSKUType,tv_stockKg,tv_stockCase,tv_stockOrdVal;

        LinearLayout ll_HeaderSection;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSKUType = itemView.findViewById(R.id.tvSKUType);
            tvProdctName = itemView.findViewById(R.id.tvPrdName);
            tv_stockKg = itemView.findViewById(R.id.tv_stockKg);
            tv_stockCase = itemView.findViewById(R.id.tv_stockCase);
            tv_stockOrdVal = itemView.findViewById(R.id.tv_stockOrdVal);
            ll_HeaderSection = itemView.findViewById(R.id.ll_HeaderSection);

        }
    }


}
