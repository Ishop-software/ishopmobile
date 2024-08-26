package com.example.ishopbillingsoftware.homepage;

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

public class HomeItemsAdapter extends RecyclerView.Adapter<HomeItemsAdapter.Viewholder> {

    private final Context context;
    private final ArrayList<HomeItem> courseModelArrayList;


    // to check whether sub FAB buttons are visible or not.
    Boolean isAllFabsVisible;

    // Constructor
    public HomeItemsAdapter(Context context, ArrayList<HomeItem> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dassh_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        HomeItem model = courseModelArrayList.get(position);
        holder.courseNameTV.setText(model.getItemname());
        String itemname = model.getItemname();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemname1 = model.getItemname();
                Toast.makeText(context.getApplicationContext(), model.getItemname(),Toast.LENGTH_LONG).show();
                if (itemname1.equals("Accounts")){
                    Intent in = new Intent(context.getApplicationContext(), AccountActivity.class);
                    in.putExtra("token",HomePageActivity.token);
                    context.startActivity(in);
                } else if (itemname1.equals("Items")) {
                    Intent in = new Intent(context.getApplicationContext(), AddNewActivity.class);
                    in.putExtra("token",HomePageActivity.token);
                    context.startActivity(in);
                } else if (itemname1.equals("Outstandings")) {
                    Intent in = new Intent(context.getApplicationContext(), AddNewActivity.class);
                    context.startActivity(in);
                }else if (itemname1.equals("Sales")) {
                    Intent in = new Intent(context.getApplicationContext(), SalesActivity.class);
                    in.putExtra("token",HomePageActivity.token);
                    context.startActivity(in);
                } else if (itemname1.equals("Purchases")) {
                    Intent in = new Intent(context.getApplicationContext(), AddNewActivity.class);
                    context.startActivity(in);
                }else if (itemname1.equals("POS")) {
                    Intent in = new Intent(context.getApplicationContext(), AddNewActivity.class);
                    context.startActivity(in);
                }

            }
        });
        if (itemname.equals("Accounts")){
            holder.card.setBackground(context.getDrawable(R.drawable.ic_account));
        } else if (itemname.equals("Items")) {
            holder.card.setBackground(context.getDrawable(R.drawable.ic_items));
        } else if (itemname.equals("Outstandings")) {
            holder.card.setBackground(context.getDrawable(R.drawable.ic_outstanding));
        }else if (itemname.equals("Sales")) {
            holder.card.setBackground(context.getDrawable(R.drawable.ic_sales));
        } else if (itemname.equals("Purchases")) {
            holder.card.setBackground(context.getDrawable(R.drawable.ic_purchase));
        }else if (itemname.equals("POS")) {
            holder.card.setBackground(context.getDrawable(R.drawable.ic_pos));
        }


    }

    private void getItemALL() {

        try {
            Call<APIResponseProductItem> call = ApiClient.getUserService().getAllproductIitem(HomePageActivity.token);

            call.enqueue(new Callback<APIResponseProductItem>() {

                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onResponse(@NonNull Call<APIResponseProductItem> call, @NonNull Response<APIResponseProductItem> response) {
                    Log.e("success", String.valueOf(response));

                    //assert response.body() != null;

                    if (response.isSuccessful()){

                        APIResponseProductItem  apiResponseProductItem = response.body();
                        if (apiResponseProductItem !=null){
                            List<ProductItem> productItems = apiResponseProductItem.getMessage();
                            if (productItems!=null){
                                System.out.print("productitem"+productItems.size());
                                System.out.print("productitem1"+productItems.toString());
                            }
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseProductItem> call, @NonNull Throwable t) {

                    Log.e("getULDfailure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(context, "Server Down!! Please Retry after some time", Toast.LENGTH_LONG).show();

                }
            });

        }catch (Exception e){
            System.out.print("eee"+e.toString());
        }

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return courseModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class Viewholder extends RecyclerView.ViewHolder {

        private final TextView courseNameTV;


        LinearLayout card;



        public Viewholder(@NonNull View itemView) {
            super(itemView);

            courseNameTV = itemView.findViewById(R.id.idTVCourse);
            card = itemView.findViewById(R.id.card);




        }
    }


}
