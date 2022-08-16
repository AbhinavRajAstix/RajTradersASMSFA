package com.astix.rajtraderssfasmasales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.model.JointVisitDetail;
import com.astix.rajtraderssfasmasales.model.JointVisitMemberDetail;
import com.astix.rajtraderssfasmasales.model.TblRouteListMaster;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.astix.rajtraderssfasmasales.utils.AppUtils.getDateInMonthTextFormat;


public class JointVisitListAdapter extends RecyclerView.Adapter<JointVisitListAdapter.JointVisitViewHolder> {

    public Context context;
    private ArrayList<JointVisitDetail> mJointVisitDetails;
    private ArrayList<JointVisitMemberDetail> mJointVisitMemberDetails;
    public AppDataSource mDataSource;

    public String fDate;
    public String userDate;
    public String passDate;
    public SimpleDateFormat sdf;
    public String pickerDate;
    public String rID;
    InterfaceRetrofitEndJointVisit interfaceRetrofitEndJointVisit;
    private LayoutInflater inflater;

     class JointVisitViewHolder extends RecyclerView.ViewHolder {


        TextView tvVisitDetails;
        Button btCompleteButton;
        public View layout;
     /*
        @BindView(R.id.tvVisitDetails)
        TextView tvVisitDetails;
        @BindView(R.id.btComplete)
        TextView btCompleteButton;*/

        public JointVisitViewHolder(View view) {
            super(view);
            layout = view;
            tvVisitDetails = (TextView) view.findViewById(R.id.tvVisitDetails);
            btCompleteButton = (Button) view.findViewById(R.id.btComplete);

            btCompleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
         //   ButterKnife.bind(this,view);
        }

      /*  View.OnContextClickListener

        @OnClick(R.id.btComplete)
        public void onItemClick(){

        }*/


    }

    public JointVisitListAdapter(Context context, ArrayList<JointVisitDetail> jointVisitDetails, ArrayList<JointVisitMemberDetail> jointVisitMemberDetails) {
        this.context = context;
        this.mJointVisitDetails=jointVisitDetails;
        this.mJointVisitMemberDetails=jointVisitMemberDetails;
        interfaceRetrofitEndJointVisit=(InterfaceRetrofitEndJointVisit)  context;
        inflater = LayoutInflater.from(context);
        mDataSource=AppDataSource.getInstance(context);
    }


    @Override
    public JointVisitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     /*   LayoutInflater inflator = ((Activity) context).getLayoutInflater();
        View view = inflator.inflate(R.layout.layout_jointvisit_item, parent,false);
        return new JointVisitViewHolder(view);*/
        View view = inflater.inflate(R.layout.layout_jointvisit_item, parent, false);
        JointVisitViewHolder holder = new JointVisitViewHolder(view);

        return holder;

      /*  View view = inflater.inflate(R.layout.layout_jointvisit_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;*/
    }

    @Override
    public void onBindViewHolder(JointVisitViewHolder holder,final int position) {
        final JointVisitDetail jointVisitDetail = mJointVisitDetails.get(position);
        if (jointVisitDetail != null) {

            String data="Joint Visit on "+mJointVisitDetails.get(position).getVisitDate()+" with ";

            ArrayList<JointVisitMemberDetail> memberDetails=new ArrayList<>();
            for(int i=0;i<mJointVisitMemberDetails.size();i++){
                if(mJointVisitMemberDetails.get(i).getJointVisitId().equalsIgnoreCase(jointVisitDetail.getJointVisitId())){
                    memberDetails.add(mJointVisitMemberDetails.get(i));
                    data=data+mJointVisitMemberDetails.get(i).getFellowPersonName()+",";
                }
            }

            holder.tvVisitDetails.setText(data.substring(0,data.length()-1));

            holder.btCompleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    interfaceRetrofitEndJointVisit.successEndJointVisit(jointVisitDetail.getJointVisitId());
                 /*   Bundle args=new Bundle();
                    args.putInt(AppUtils.ROUTEID,jointVisitDetail.getRouteId());
                    args.putInt(AppUtils.FLGJOINTVISIT,1);
                    args.putString(AppUtils.JOINTVISITID,jointVisitDetail.getJointVisitId());*/
                    TblRouteListMaster tblRouteListMaster=mDataSource.getActiveRoute();
                  /*  args.putSerializable(AppUtils.ROUTE,tblRouteListMaster);*/
                    Date date1 = new Date();
                    sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                    passDate = sdf.format(date1);

                    //System.out.println("Selctd Date: "+ passDate);

                    fDate = passDate.trim();
                    pickerDate = getDateInMonthTextFormat(context);
                    userDate = getDateInMonthTextFormat(context);

                 /*   Intent intent = new Intent(context, StoreSelection.class);
                    intent.putExtra("imei", AppUtils.getToken(context));
                    intent.putExtra(AppUtils.FLGJOINTVISIT,1);
                    intent.putExtra("userDate", userDate);
                    intent.putExtra("pickerDate", fDate);
                    intent.putExtra(AppUtils.JOINTVISITID,jointVisitDetail.getJointVisitId());
                    intent.putExtra("rID", tblRouteListMaster.getRouteNodeId());

                    context.startActivity(intent);
                    context.finish();*/

                  /*  Intent intent = new Intent(context, StoreSelection.class);
                  *//*  intent.putExtra("imei", imei);
                    intent.putExtra("userDate", userDate);
                    intent.putExtra("pickerDate", fDate);
                    intent.putExtra("rID", rID);
                    intent.putExtra("JOINTVISITID", "NA");*//*
                    context.startActivity(intent);
                    context.finish();
                   // ((MarketVisitActivity)context).loadFragment(args,MarketVisitActivity.STOREVISIT);*/
                }
            });


        }

    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getItemCount() {
        return mJointVisitDetails.size();
    }



}
