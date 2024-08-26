package com.example.ishopbillingsoftware.items;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.homepage.HomePageActivity;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewListActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    List<ProductItem> productItems ;

    SearchView searchView;
    ViewListAdapter adapter;

    private List<ProductItem> originalProductList;

    ImageButton filterbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));


        recyclerView = (RecyclerView) findViewById(R.id.viewlistrecyclerview);
        searchView = (SearchView) findViewById(R.id.searchview);
        filterbtn = (ImageButton) findViewById(R.id.filterbtn);
        filterbtn.setVisibility(View.GONE);

        getItemALL();
        search();


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
                        productItems = apiResponseProductItem.getMessage();
                            // Keep a copy of the original list
                            originalProductList = new ArrayList<>(productItems);
                            if (productItems!=null){
                                System.out.print("productitem"+productItems.size());
                                System.out.print("productitem1"+productItems.toString());
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(linearLayoutManager);


                                adapter = new ViewListAdapter(ViewListActivity.this,productItems,adapter);


                                // Setting Adapter to RecyclerView
                                recyclerView.setAdapter(adapter);


                            }
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseProductItem> call, @NonNull Throwable t) {

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
                for (ProductItem item : productItems) {
                    if (item.getItemName().toLowerCase().contains(lowerCaseQuery)) {
                        found = true;
                        break;
                    }
                }

                if (found) {

                    adapter.getFilter().filter(query);
                  //  adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ViewListActivity.this, "No Match found", Toast.LENGTH_LONG).show();
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
                adapter.setProductList(new ArrayList<>(originalProductList));
                return false;

            }
        });

    }

}