package com.astix.rajtraderssfasmasales.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astix.rajtraderssfasmasales.R;
import com.astix.rajtraderssfasmasales.model.TblGetPDAStoreOrderHistory;
import com.astix.rajtraderssfasmasales.utils.AppUtils;
import com.astix.rajtraderssfasmasales.utils.InvHistoryDialog;

import java.util.ArrayList;

public class StoreOrderHistoryAdapter extends RecyclerView.Adapter<StoreOrderHistoryAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<TblGetPDAStoreOrderHistory> orderHistoryArrayList;

    public StoreOrderHistoryAdapter(Context mContext, ArrayList<TblGetPDAStoreOrderHistory> orderHistoryArrayList) {
        this.mContext = mContext;
        this.orderHistoryArrayList = orderHistoryArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_store_order_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TblGetPDAStoreOrderHistory orderHistoryPerProduct = orderHistoryArrayList.get(position);
        holder.tvSrNo.setText("" + (position + 1));
        holder.tvOrderDate.setText(orderHistoryPerProduct.getInvDate());
        holder.tvOrderQty.setText("" + orderHistoryPerProduct.getOrderQty());

        int orderValue = Math.round(Float.parseFloat(orderHistoryPerProduct.getOrderValue()));
        String value = "\u20b9 " + AppUtils.checkNullOrEmpty("" + orderValue, "", "NA");
//        SpannableString content = new SpannableString(value);
//        content.setSpan(new UnderlineSpan(), 0, value.length(), 0);
        holder.tvOrderVal.setText(value);

        holder.ivViewProd.setOnClickListener(view -> {
            InvHistoryDialog orderHistoryDialog = new InvHistoryDialog(mContext, orderHistoryPerProduct);
            orderHistoryDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return orderHistoryArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSrNo, tvOrderQty, tvOrderVal, tvOrderDate;
        public ImageView ivViewProd;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSrNo = itemView.findViewById(R.id.tvSrNo);
            ivViewProd = itemView.findViewById(R.id.ivViewProd);
            tvOrderQty = itemView.findViewById(R.id.tvOrderQty);
            tvOrderVal = itemView.findViewById(R.id.tvOrderVal);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
        }
    }
}
