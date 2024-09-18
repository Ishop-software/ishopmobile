package com.example.ishopbillingsoftware.sales;

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
import com.example.ishopbillingsoftware.items.ProductItem;
import com.example.ishopbillingsoftware.items.ProductItemDetailsActivity;
import com.example.ishopbillingsoftware.items.ProductdetailsActivity;
import com.example.ishopbillingsoftware.items.ViewListAdapter;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleschargesAdapter extends RecyclerView.Adapter<SaleschargesAdapter.Viewholder> {

    private final Context context;
    private List<Salescharges> viewListDataList;
    String salesRegID;

    private List<Salescharges> productListFull;
    SaleschargesAdapter adapter;


    public SaleschargesAdapter(SalesViewListActivity context, List<Salescharges> viewListDataList, SaleschargesAdapter adapter) {
        this.context = context;
        this.viewListDataList = viewListDataList;
        this.productListFull = new ArrayList<>(viewListDataList);
        this.adapter = adapter;

    }
    // Method to reset the list data
    @SuppressLint("NotifyDataSetChanged")
    public void setProductList(List<Salescharges> productList) {
        this.viewListDataList = productList;
        this.productListFull = new ArrayList<>(productList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SaleschargesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salesviewlist, parent, false);
        return new SaleschargesAdapter.Viewholder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SaleschargesAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {


        // to set data to textview and imageview of each card layout
        Salescharges model = viewListDataList.get(position);
        holder.partynametxt.setText("Party Name:  "+model.getPartyName());
        holder.billnotxt.setText("Bill No:  "+String.valueOf(model.getBillNo()));
        holder.duedatetxt.setText("Due Date:  "+model.getDueDate());
        holder.itemnametxt.setText("Item Name:  "+model.getItemName());
        holder.qtytxt.setText("Qty:  "+String.valueOf(model.getQty()));
        holder.altqtytxt.setText("AltQty:  "+String.valueOf(model.getAltQty()));
        holder.freetxt.setText("Free:  "+String.valueOf(model.getFree()));
        holder.pertxt.setText("Per:  "+model.getPer());
        String rate = String.valueOf(model.getRate());
        holder.ratetxt.setText("Rate:  "+rate);
        holder.discstxt.setText("Dics:  "+String.valueOf(model.getDiscs()) );
        holder.discamounttxt.setText("Disc Amount:  "+String.valueOf(model.getDiscAmount()));
        holder.taxamounttxt.setText("Tax Amount:  "+String.valueOf(model.getTaxAmount()));
        holder.netvaluetxt.setText("NetValue:  "+String.valueOf(model.getNetValue()));
        holder.othrchargestxt.setText( "OtherCharges:  "+model.getOtherCharges());
        holder.remarkstxt.setText( "Remarks:  "+model.getRemarks());
        holder.onvaluetxt.setText("OnValue:  "+String.valueOf(model.getOnValue()));
        holder.percentagetxt.setText( "Percentage:  "+model.getPer());
        holder.plusminustxt.setText("Plus/Minus:  "+ model.getPlusMinus());
        holder.amounttxt.setText("Amount:  "+String.valueOf(model.getAmount()));
        salesRegID = model.getSaleRegId();






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
            List<Salescharges> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Salescharges product : productListFull) {
                    if (product.getItemName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(product);
                    }
                    else  if (product.getPartyName().toLowerCase().contains(filterPattern)){
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

      TextView partynametxt,billnotxt,duedatetxt,itemnametxt,qtytxt,
                altqtytxt,freetxt, pertxt,ratetxt,discamounttxt,
                taxamounttxt,discstxt,netvaluetxt,othrchargestxt,remarkstxt,onvaluetxt,
                percentagetxt,plusminustxt,amounttxt;


        ImageButton edit,delete;

        CardView itemcard;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            partynametxt = itemView.findViewById(R.id.partynametxt);
            billnotxt = itemView.findViewById(R.id.billnotxt);
            duedatetxt = itemView.findViewById(R.id.duedatetxt);
            itemnametxt = itemView.findViewById(R.id.itemnametxt);
            qtytxt = itemView.findViewById(R.id.qtytxt);
            altqtytxt = itemView.findViewById(R.id.altqtytxt);
            freetxt = itemView.findViewById(R.id.freetxt);
            pertxt = itemView.findViewById(R.id.pertxt);
            ratetxt = itemView.findViewById(R.id.ratetxt);
            discamounttxt = itemView.findViewById(R.id.discamounttxt);

            taxamounttxt = itemView.findViewById(R.id.taxamounttxt);
            discstxt = itemView.findViewById(R.id.discstxt);
            netvaluetxt = itemView.findViewById(R.id.netvaluetxt);
            remarkstxt = itemView.findViewById(R.id.remarkstxt);
            onvaluetxt = itemView.findViewById(R.id.onvaluetxt);
            percentagetxt = itemView.findViewById(R.id.percentagetxt);
            othrchargestxt = itemView.findViewById(R.id.othrchargestxt);
            plusminustxt = itemView.findViewById(R.id.plusminustxt);
            amounttxt = itemView.findViewById(R.id.amounttxt);

        }
    }

}
