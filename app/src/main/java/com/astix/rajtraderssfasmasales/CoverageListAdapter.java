package com.astix.rajtraderssfasmasales;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.astix.rajtraderssfasmasales.model.CoverageAreaItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoverageListAdapter extends RecyclerView.Adapter<CoverageListAdapter.CoverageListViewHolder> {

    private Activity context;
    private ArrayList<CoverageAreaItem> coverageAreaItems, orgCoverageAreaItems;


    public CoverageListAdapter(Activity context, ArrayList<CoverageAreaItem> coverageAreaItems) {
        this.context = context;
        this.coverageAreaItems = coverageAreaItems;
        this.orgCoverageAreaItems = coverageAreaItems;
    }

    @Override
    public CoverageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflator = ((Activity) context).getLayoutInflater();
        View view = inflator.inflate(R.layout.layout_coveragearea_item, null);
        return new CoverageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoverageListViewHolder holder, final int position) {
        final CoverageAreaItem subordinateList = coverageAreaItems.get(position);
        if (subordinateList != null) {

            holder.rbCoverage.setText(subordinateList.getCoverageAreaName() + "(" + subordinateList.getPersonName() + ")");
            //  holder.rbCoverage.setText(subordinateList.getCoverageAreaName());//+"("+subordinateList.getPersonName()+")"
            holder.rbCoverage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        for (CoverageAreaItem coverageAreaItem : coverageAreaItems)
                            coverageAreaItem.setSelected(false);

                        subordinateList.setSelected(true);
                        holder.rbCoverage.setChecked(true);
//                        context.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                notifyDataSetChanged();
//                            }
//                        });

                    }
                }
            });

            holder.rlParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (CoverageAreaItem coverageAreaItem : coverageAreaItems)
                        coverageAreaItem.setSelected(false);

                    subordinateList.setSelected(true);
                    holder.rbCoverage.setChecked(true);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                }
            });

            if (subordinateList.isSelected())
                holder.rbCoverage.setChecked(true);
            else
                holder.rbCoverage.setChecked(false);


        }

    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getItemCount() {
        return coverageAreaItems.size();
    }

    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                coverageAreaItems = (ArrayList<CoverageAreaItem>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                List<CoverageAreaItem> FilteredArrList = new ArrayList<CoverageAreaItem>();

                if (constraint == null || constraint.length() == 0) {
                    results.count = orgCoverageAreaItems.size();
                    results.values = orgCoverageAreaItems;
                } else {
                    constraint = constraint.toString().toLowerCase();

                    for (int i = 0; i < orgCoverageAreaItems.size(); i++) {
                        CoverageAreaItem data = orgCoverageAreaItems.get(i);
                        if (data.getCoverageAreaName().toLowerCase().contains(constraint.toString()) || data.getPersonName().toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(data);
                        }
                    }

                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }

                return results;
            }
        };

        return filter;
    }

    public class CoverageListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rlParent)
        RelativeLayout rlParent;
        @BindView(R.id.rbCoverage)
        RadioButton rbCoverage;

        public CoverageListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }


}
