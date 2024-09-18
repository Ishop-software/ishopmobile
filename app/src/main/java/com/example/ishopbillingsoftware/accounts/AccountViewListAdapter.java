package com.example.ishopbillingsoftware.accounts;

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
import com.example.ishopbillingsoftware.items.APIResponseUpdate;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountViewListAdapter extends RecyclerView.Adapter<AccountViewListAdapter.Viewholder> {

    private final Context context;
    private List<Data> accountLists;
    String productId;

    private List<Data> accountListFull;
    AccountViewListAdapter adapter;


    public AccountViewListAdapter(AccounviewlistActivity context, List<Data> accountLists, AccountViewListAdapter adapter) {
        this.context = context;
        this.accountLists = accountLists;

        this.accountListFull = new ArrayList<>(accountLists);
        this.adapter = adapter;

    }



    // Method to reset the list data
    @SuppressLint("NotifyDataSetChanged")
    public void setProductList(List<Data> accountLists) {
        this.accountLists = accountLists;
        this.accountListFull = new ArrayList<>(accountLists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accountlist, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        Data model = accountLists.get(position);

        holder.accountname.setText(model.getName());
        holder.groupname.setText(model.getGroup());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(context, AccountEditListActivity.class);
                in.putExtra("accountId", model.getAccountId());
                in.putExtra("name", model.getName());
                in.putExtra("printAs", model.getPrintAs());
                in.putExtra("group", model.getGroup());
                in.putExtra("openingBal", model.getOpeningBal());
                in.putExtra("taxNo", model.getTaxNo());
                in.putExtra("address1", model.getAddress1());
                in.putExtra("city", model.getCity());
                in.putExtra("pincode", model.getPincode());
                in.putExtra("state", model.getState());
                in.putExtra("stateCode", model.getStateCode());
                in.putExtra("mobileNo",model.getMobileNo());
                in.putExtra("email", model.getEmail());
                in.putExtra("contactPerson", model.getContactPerson());
                context.startActivity(in);

            }
        });


        holder.itemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, AccountviewdetailsActivity.class);
                in.putExtra("accountId", model.getAccountId());
                in.putExtra("name", model.getName());
                in.putExtra("printAs", model.getPrintAs());
                in.putExtra("group", model.getGroup());
                in.putExtra("openingBal", model.getOpeningBal());
                in.putExtra("taxNo", model.getTaxNo());
                in.putExtra("address1", model.getAddress1());
                in.putExtra("city", model.getCity());
                in.putExtra("pincode", model.getPincode());
                in.putExtra("mobileNo",model.getMobileNo());
                in.putExtra("state", model.getState());
                in.putExtra("stateCode", model.getStateCode());
                in.putExtra("email", model.getEmail());
                in.putExtra("contactPerson", model.getContactPerson());
                context.startActivity(in);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pp = model.getAccountId();
                getDelete(pp, position);
            }
        });


    }

    private void getDelete(String prodId, int position) {
        System.out.print(prodId);
        try {
            HashMap<String, String> itemadd = new HashMap<String, String>();
            itemadd.put("productItemId", prodId);

            Call<APIResponseUpdate> call = ApiClient.getUserService().delete(itemadd);


            call.enqueue(new Callback<APIResponseUpdate>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(Call<APIResponseUpdate> call, Response<APIResponseUpdate> response) {
                    System.out.print("del" + String.valueOf(response));
                    Log.e("success", String.valueOf(response));
                    if (response.isSuccessful()) {
                        removeItem(position);

                        String success = response.body().getSuccess();
                        String message = response.body().getMessage();


                        Toast.makeText(context, success + message, Toast.LENGTH_LONG).show();
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
        } catch (Exception e) {
            System.out.print("error" + e.toString());
        }
    }

    private void removeItem(int position) {
        accountLists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, accountLists.size());
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return accountLists.size();


    }


    public Filter getFilter() {
        return productFilter;
    }

    private final Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Data> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(accountListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Data account : accountListFull) {
                    if (account.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(account);
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
            accountLists.clear();
            if (results.values != null) {
                accountLists.addAll((List) results.values);
            }
            notifyDataSetChanged();
        }
    };

    // View holder class for initializing of your views such as TextView and Imageview
    public static class Viewholder extends RecyclerView.ViewHolder {

        private final TextView accountname, groupname;


        ImageButton edit, delete;

        CardView itemcard;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            accountname = itemView.findViewById(R.id.accountnametxt);
            groupname = itemView.findViewById(R.id.groupnametxt);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            itemcard = itemView.findViewById(R.id.itemcard);


        }
    }
}
