package com.example.ishopbillingsoftware.sales;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.homepage.HomePageActivity;
import com.example.ishopbillingsoftware.items.APIResponseProductItem;
import com.example.ishopbillingsoftware.items.ProductItem;
import com.example.ishopbillingsoftware.items.ViewListActivity;
import com.example.ishopbillingsoftware.items.ViewListAdapter;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesViewListActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    List<Salescharges> salescharges ;

    SearchView searchView;
    SaleschargesAdapter adapter;
    CheckBox itemname,billno,rate;

    private List<Salescharges> saleschargesList;

    ImageButton filterbtn;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_view_list);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));

        recyclerView = (RecyclerView) findViewById(R.id.viewlistrecyclerview);
        searchView = (SearchView) findViewById(R.id.searchview);
        filterbtn = (ImageButton) findViewById(R.id.filterbtn);
        filterbtn.setVisibility(View.GONE);

        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SalesViewListActivity.this);
                alertDialog.setTitle("Filter");
                String[] items = {"Item Name","Bill No","Rate"};
                boolean[] checkedItems = {false, false, false, false, false,false};
                alertDialog.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        switch (which) {
                            case 0:
                                if(isChecked)
                                    message = "itemname";
                                //Toast.makeText(ViewListActivity.this, "Clicked on java", Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                if(isChecked)
                                    message = "billno";
                                //Toast.makeText(ViewListActivity.this, "Clicked on android", Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                if(isChecked)
                                    message = "rate";
                                //Toast.makeText(ViewListActivity.this, "Clicked on Data Structures", Toast.LENGTH_LONG).show();
                                break;

                        }
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(true);
                alert.show();

                //  filtersearch(message);
            }
        });
        getAllSalesList();
        search();
    }
    private void  getAllSalesList() {

        try {
            Call<APIResponseSalesList> call = ApiClient.getUserService().viewsaleschargelist(HomePageActivity.token);

            call.enqueue(new Callback<APIResponseSalesList>() {

                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onResponse(@NonNull Call<APIResponseSalesList> call, @NonNull Response<APIResponseSalesList> response) {
                    Log.e("success", String.valueOf(response));

                    //assert response.body() != null;

                    if (response.isSuccessful()){

                        APIResponseSalesList  apiResponseProductItem = response.body();
                        if (apiResponseProductItem !=null){
                            salescharges = apiResponseProductItem.getMessage();
                            // Keep a copy of the original list
                            saleschargesList = new ArrayList<>(salescharges);
                            if (salescharges!=null){
                                System.out.print("productitem"+salescharges.size());
                                System.out.print("productitem1"+salescharges.toString());
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(linearLayoutManager);


                                adapter = new SaleschargesAdapter(SalesViewListActivity.this,saleschargesList,adapter);


                                // Setting Adapter to RecyclerView
                                recyclerView.setAdapter(adapter);


                            }
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseSalesList> call, @NonNull Throwable t) {

                    Log.e("getULDfailure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getApplicationContext(), "Server Down!! Please Retry after some time", Toast.LENGTH_LONG).show();

                }
            });

        }catch (Exception e){
            System.out.print("eee"+e.toString());
        }

    }

    private void search() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onQueryTextSubmit(String query) {

                // Assume Product class has a getName() method
                String lowerCaseQuery = query.toLowerCase();

                boolean found = false;
                for (Salescharges item : salescharges) {
                    if (item.getItemName().toLowerCase().contains(lowerCaseQuery)) {
                        found = true;
                        break;
                    }
                    else  if (item.getPartyName().toLowerCase().contains(lowerCaseQuery)){
                        found = true;
                        break;
                    }
                }

                if (found) {

                    adapter.getFilter().filter(query);
                    //  adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SalesViewListActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        // Reset list when search view is closed
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onClose() {
                // Reset the adapter data to the original list
                adapter.setProductList(new ArrayList<>(saleschargesList));
                return false;

            }
        });

    }
}