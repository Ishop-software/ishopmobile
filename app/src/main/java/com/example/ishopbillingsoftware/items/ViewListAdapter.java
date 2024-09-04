package com.example.ishopbillingsoftware.items;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewListAdapter extends RecyclerView.Adapter<ViewListAdapter.Viewholder> {

    private final Context context;
    private List<ProductItem> viewListDataList;
    String productId;

    private List<ProductItem> productListFull;
    ViewListAdapter adapter;


    public ViewListAdapter(ViewListActivity context, List<ProductItem> viewListDataList, ViewListAdapter adapter) {
        this.context = context;
        this.viewListDataList = viewListDataList;
        this.productListFull = new ArrayList<>(viewListDataList);
        this.adapter = adapter;

    }
    // Method to reset the list data
    @SuppressLint("NotifyDataSetChanged")
    public void setProductList(List<ProductItem> productList) {
        this.viewListDataList = productList;
        this.productListFull = new ArrayList<>(productList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlist, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {


        // to set data to textview and imageview of each card layout
        ProductItem model = viewListDataList.get(position);
        holder.itemname.setText(model.getItemName());
        holder.shortname.setText(model.getShortName());
         productId = model.getProductItemId();

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(context,ProductdetailsActivity.class);
                in.putExtra("productid",productId);
                in.putExtra("itemname",model.getItemName());
                in.putExtra("shortname",model.getShortName());
                in.putExtra("hsccode",model.getHSNCode());
                in.putExtra("taxSlab",model.getTaxSlab());
                in.putExtra("company",model.getCompany());
                in.putExtra("group",model.getGroup());
                in.putExtra("purchase",model.getPurchase());
                in.putExtra("sale",model.getSalePrice());
                in.putExtra("mrp",model.getMrp());
                context.startActivity(in);

            }
        });


        holder.itemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,ProductItemDetailsActivity.class);
                in.putExtra("productid",productId);
                in.putExtra("itemname",model.getItemName());
                in.putExtra("shortname",model.getShortName());
                in.putExtra("hsccode",model.getHSNCode());
                in.putExtra("taxSlab",model.getTaxSlab());
                in.putExtra("company",model.getCompany());
                in.putExtra("group",model.getGroup());
                in.putExtra("purchase",model.getPurchase());
                in.putExtra("sale",model.getSalePrice());
                in.putExtra("mrp",model.getMrp());
                context.startActivity(in);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pp = model.getProductItemId();
                getDelete(pp,position);
            }
        });


    }

    private void getDelete(String prodId, int position) {
       System.out.print(prodId);
        try{
            HashMap<String, String> itemadd = new HashMap<String, String>();
            itemadd.put( "productItemId",prodId);

            Call<APIResponseUpdate> call = ApiClient.getUserService().delete(itemadd);


            call.enqueue(new Callback<APIResponseUpdate>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(Call<APIResponseUpdate> call, Response<APIResponseUpdate> response) {
                    System.out.print("del"+String.valueOf(response));
                    Log.e("success", String.valueOf(response));
                    if (response.isSuccessful()) {
                        removeItem(position);

                        String success = response.body().getSuccess();
                        String message  = response.body().getMessage();





                        Toast.makeText(context,success+message,Toast.LENGTH_LONG).show();
                        // Handle success
                    } else {
                        // Handle error response
                    }
                }

                @Override
                public void onFailure(Call<APIResponseUpdate> call, Throwable t) {
                    // Handle failure
                }
            });
        }catch(Exception e){
            System.out.print("error"+e.toString());
        }
    }

    private void removeItem(int position) {
        viewListDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, viewListDataList.size());
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return viewListDataList.size();


    }


    public Filter getFilter() {
        return productFilter;
    }

    private final Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ProductItem product : productListFull) {
                    if (product.getItemName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(product);
                    }
                    else  if (product.getShortName().toLowerCase().contains(filterPattern)){
                        filteredList.add(product);
                        //break;
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            viewListDataList.clear();
            if (results.values != null) {
                viewListDataList.addAll((List) results.values);
            }
            notifyDataSetChanged();
        }

    };
    // View holder class for initializing of your views such as TextView and Imageview
    public static class Viewholder extends RecyclerView.ViewHolder {

        private final TextView itemname,shortname;


       ImageButton edit,delete;

CardView itemcard;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            itemname = itemView.findViewById(R.id.itemtxt);
            shortname = itemView.findViewById(R.id.shortnametxt);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            itemcard = itemView.findViewById(R.id.itemcard);



        }
    }

}
