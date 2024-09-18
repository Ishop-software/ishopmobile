package com.example.ishopbillingsoftware.sales;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.items.APIResponseUpdate;
import com.example.ishopbillingsoftware.items.ProductItem;
import com.example.ishopbillingsoftware.items.ProductItemDetailsActivity;
import com.example.ishopbillingsoftware.items.ProductdetailsActivity;
import com.example.ishopbillingsoftware.items.ViewListActivity;
import com.example.ishopbillingsoftware.items.ViewListAdapter;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherchargesAdapter extends RecyclerView.Adapter<OtherchargesAdapter.Viewholder> {

    private final Context context;
    private List<ChargesList> viewListDataList;
    String productId;

    private List<ChargesList> productListFull;
    OtherchargesAdapter adapter;


    public OtherchargesAdapter(ChargesViewListActivity context, List<ChargesList> viewListDataList, OtherchargesAdapter adapter) {
        this.context = context;
        this.viewListDataList = viewListDataList;
        this.productListFull = new ArrayList<>(viewListDataList);
        this.adapter = adapter;

    }

    @NonNull
    @Override
    public OtherchargesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.otherchargesviewlist, parent, false);
        return new OtherchargesAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherchargesAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {


        // to set data to textview and imageview of each card layout
        ChargesList model = viewListDataList.get(position);

        holder.accounttxt.setText( "ChargesHeading:  "+ model.getChargesHeading());
        holder.plusminustxt.setText("Types of Charges:  "+ model.getTypesOfCharges());
        holder.inputastxt.setText("Input Amount of Charges as:  "+ model.getInputAmountOfChargesAs());
        holder.applyontxt.setText("Account Head to Post:  "+ model.getAccountHeadToPost());
        holder.calculatetxt.setText("Calculate Discount:  "+"null");
        holder.rotxt.setText("RO:  "+ "ro");
        if (!model.getTaxSettings().isEmpty()) {
            TaxSetting tax = model.getTaxSettings().get(0);  // Get first tax setting
            // You can display tax details like taxSlab, HSNCode, etc.

            holder.taxtxt.setText("Tax:  "+tax.getTaxSlab());
            holder.slabtxt.setText("Slab: "+ tax.getHSNCode());
            holder.hsntxt.setText("HSN: "+ tax.isTaxApplicable());
        }










    }





    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return viewListDataList.size();


    }


    public static class Viewholder extends RecyclerView.ViewHolder {

        TextView accounttxt,plusminustxt,inputastxt,applyontxt,calculatetxt,rotxt,taxtxt,
                slabtxt,hsntxt;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            accounttxt = itemView.findViewById(R.id.accounttxt);
            plusminustxt = itemView.findViewById(R.id.plusminustxt);
            inputastxt = itemView.findViewById(R.id.inputastxt);
            applyontxt = itemView.findViewById(R.id.applyontxt);
            calculatetxt = itemView.findViewById(R.id.calculatetxt);
            rotxt = itemView.findViewById(R.id.rotxt);
            taxtxt = itemView.findViewById(R.id.taxtxt);
            slabtxt = itemView.findViewById(R.id.slabtxt);
            hsntxt = itemView.findViewById(R.id.hsntxt);




        }
    }
}
