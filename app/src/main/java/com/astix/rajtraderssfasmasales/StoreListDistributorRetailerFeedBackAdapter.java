package com.astix.rajtraderssfasmasales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.recyclerview.widget.RecyclerView;

import com.astix.Common.CommonInfo;
import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutInterface.InterfaceEditDistributorScoutingRetailerFeedBaclStoreSelectoin;
import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblPotentialDistributorRetailersFeedBackDetails;
import com.astix.rajtraderssfasmasales.utils.AppUtils;

import java.text.DecimalFormat;
import java.util.List;

public class StoreListDistributorRetailerFeedBackAdapter extends RecyclerView.Adapter<StoreListDistributorRetailerFeedBackAdapter.ViewHolder> {

    public Context context;

    List<TblPotentialDistributorRetailersFeedBackDetails> tblPotentialDistributorRetailersFeedBackDetailsList;

    public StoreListDistributorRetailerFeedBackAdapter(Context context, List<TblPotentialDistributorRetailersFeedBackDetails> tblPotentialDistributorRetailersFeedBackDetailsList) {
        this.context = context;
        this.tblPotentialDistributorRetailersFeedBackDetailsList = tblPotentialDistributorRetailersFeedBackDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.distributor_retailor_feedback_store_selection_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final TblPotentialDistributorRetailersFeedBackDetails masterDataRetrive = tblPotentialDistributorRetailersFeedBackDetailsList.get(position);

        holder.tv_DistributorRetilerAddress.setText("Not Available");
      //  holder.tv_DistributorRetilerAddress.setTag("Not Available" + "^" + masterDataRetrive.getAddress());
        holder.img_FeedBackStatus.setTag(masterDataRetrive.getNodeID()+"^"+masterDataRetrive.getRetailerSectionID());



        if (masterDataRetrive.getAddress() != null) {

            holder.tv_DistributorRetilerAddress.setText(masterDataRetrive.getAddress());
     /*       SpannableString content = new SpannableString(masterDataRetrive.getAddress());
          //  content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            holder.tv_DistributorRetilerAddress.setTextColor(Color.parseColor("#3f51b5"));
            holder.tv_DistributorRetilerAddress.setText(content);
            holder.tv_DistributorRetilerAddress.setTag(masterDataRetrive.getAddress());*/
        }
        holder.tv_ScoutingFor.setText(masterDataRetrive.getDBRName());



        SpannableString contentRetailName = new SpannableString(masterDataRetrive.getRetailerName());
        contentRetailName.setSpan(new UnderlineSpan(), 0, contentRetailName.length(), 0);
        holder.tv_DistibutorScoutingRetailerName.setTextColor(ContextCompat.getColor(context, R.color.blue));
        holder.tv_DistibutorScoutingRetailerName.setText(contentRetailName);
        holder.tv_DistibutorScoutingRetailerName.setTag(masterDataRetrive.getNodeID()+"^"+masterDataRetrive.getRetailerSectionID());
        //holder.tv_DistibutorScoutingRetailerName.setText(masterDataRetrive.getRetailerName());


        holder.tv_DistibutorScoutingRetailerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterfaceEditDistributorScoutingRetailerFeedBaclStoreSelectoin intrfc = (InterfaceEditDistributorScoutingRetailerFeedBaclStoreSelectoin) context;

                intrfc.fnCallActivityFeedbackDistributorRetailerStoreSelection( tblPotentialDistributorRetailersFeedBackDetailsList.get(position));
            }
        });

        holder.rg1distributorRetailerFeedBack.setChecked(false);
        holder.rg1distributorRetailerFeedBack.setTag(masterDataRetrive.getNodeID()+"^"+masterDataRetrive.getRetailerSectionID());


      /*  holder.tv_DistributorRetilerAddress.setOnClickListener(view -> {
            //context.showStoreAddressAlert(view.getTag().toString());
            InterfaceRetrofitStoreSeleted intrfc = (InterfaceRetrofitStoreSeleted) context;

            intrfc.ShowSelectedStoreAddress(view.getTag().toString());

        });*/


       // holder.tv_DistributorRetilerAddress.setTextColor(ContextCompat.getColor(context, R.color.blue));
        holder.rg1distributorRetailerFeedBack.setEnabled(true);
        holder.rg1distributorRetailerFeedBack.setTypeface(null, Typeface.NORMAL);
        holder.img_FeedBackStatus.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.iconpendingp));

        SpannableString contentRetailNameAfterSubmissionFeedbackStatusUnsubmitted = new SpannableString("Pending");
        holder.tv_FeedBackStatus.setTextColor(ContextCompat.getColor(context, R.color.red));
        holder.tv_FeedBackStatus.setText(contentRetailNameAfterSubmissionFeedbackStatusUnsubmitted);

        if (masterDataRetrive.getSstat()==4) {
            holder.rg1distributorRetailerFeedBack.setEnabled(false);

           // holder.tv_DistibutorScoutingRetailerName.setTextColor(ContextCompat.getColor(context, R.color.green_submitted));

            SpannableString contentRetailNameAfterSubmission = new SpannableString(masterDataRetrive.getRetailerName());
            //  content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            holder.tv_DistibutorScoutingRetailerName.setTextColor(ContextCompat.getColor(context, R.color.green_submitted));
            holder.tv_DistibutorScoutingRetailerName.setText(contentRetailNameAfterSubmission);
            holder.tv_DistibutorScoutingRetailerName.setTag(masterDataRetrive.getNodeID()+"^"+masterDataRetrive.getRetailerSectionID());
            holder.tv_DistibutorScoutingRetailerName.setText(contentRetailNameAfterSubmission);
            holder.img_FeedBackStatus.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icondoned));

            SpannableString contentRetailNameAfterSubmissionFeedbackStatus = new SpannableString("Done");
            holder.tv_FeedBackStatus.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.tv_FeedBackStatus.setText(contentRetailNameAfterSubmissionFeedbackStatus);
        }
        if (masterDataRetrive.getSstat()==3 || masterDataRetrive.getSstat()==5 || masterDataRetrive.getSstat()==6) {

               // holder.tv_DistibutorScoutingRetailerName.setTextColor(ContextCompat.getColor(context, R.color.green));
            SpannableString contentRetailNameAfterSubmission = new SpannableString(masterDataRetrive.getRetailerName());
            //  content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            holder.tv_DistibutorScoutingRetailerName.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.tv_DistibutorScoutingRetailerName.setText(contentRetailNameAfterSubmission);
            holder.tv_DistibutorScoutingRetailerName.setTag(masterDataRetrive.getNodeID()+"^"+masterDataRetrive.getRetailerSectionID());
            holder.tv_DistibutorScoutingRetailerName.setText(contentRetailNameAfterSubmission);
            holder.img_FeedBackStatus.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icondoned));


            SpannableString contentRetailNameAfterSubmissionFeedbackStatus = new SpannableString("Done");
            holder.tv_FeedBackStatus.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.tv_FeedBackStatus.setText(contentRetailNameAfterSubmissionFeedbackStatus);


        }

    }

    @Override
    public int getItemCount() {
        return tblPotentialDistributorRetailersFeedBackDetailsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public View layout;
        public RadioButton rg1distributorRetailerFeedBack;
        public TextView tv_DistributorRetilerAddress,tv_ScoutingFor,tv_DistibutorScoutingRetailerName,tv_FeedBackStatus;
        public ImageView img_FeedBackStatus;
        public CardView cv2;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            cv2 = itemView.findViewById(R.id.cv2);
            rg1distributorRetailerFeedBack = itemView.findViewById(R.id.rg1distributorRetailerFeedBack);
            tv_DistributorRetilerAddress = itemView.findViewById(R.id.tv_DistributorRetilerAddress);
            tv_DistibutorScoutingRetailerName = itemView.findViewById(R.id.tv_DistibutorScoutingRetailerName);
            tv_FeedBackStatus = itemView.findViewById(R.id.tv_FeedBackStatus);
            tv_ScoutingFor = itemView.findViewById(R.id.tv_ScoutingFor);
            img_FeedBackStatus = itemView.findViewById(R.id.img_FeedBackStatus);
        }
    }

}