package com.example.ishopbillingsoftware.printdesign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.accounts.AccountActivity;
import com.example.ishopbillingsoftware.homepage.HomeItem;
import com.example.ishopbillingsoftware.homepage.HomeItemsAdapter;
import com.example.ishopbillingsoftware.homepage.HomePageActivity;
import com.example.ishopbillingsoftware.items.APIResponseProductItem;
import com.example.ishopbillingsoftware.items.AddNewActivity;
import com.example.ishopbillingsoftware.items.ProductItem;
import com.example.ishopbillingsoftware.sales.SalesActivity;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrintAdapter extends RecyclerView.Adapter<PrintAdapter.Viewholder> {

    private final Context context;
    private final ArrayList<HomeItem> courseModelArrayList;


    // to check whether sub FAB buttons are visible or not.
    Boolean isAllFabsVisible;

    // Constructor
    public PrintAdapter(Context context, ArrayList<HomeItem> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public PrintAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dassh_layout, parent, false);
        return new PrintAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrintAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        HomeItem model = courseModelArrayList.get(position);
        holder.itemname.setText(model.getItemname());
        String itemname = model.getItemname();





    }



    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return courseModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class Viewholder extends RecyclerView.ViewHolder {

        private final TextView sno,itemname,qty,price,amount;


        LinearLayout card;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            sno = itemView.findViewById(R.id.sno);
            itemname =itemView.findViewById(R.id.itemname);
            qty = itemView.findViewById(R.id.qty);
            price = itemView.findViewById(R.id.price);
            amount = itemView.findViewById(R.id.amount);

        }
    }
}
