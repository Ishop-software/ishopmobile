package com.example.ishopbillingsoftware.accounts;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class AccounviewlistActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    List<Data> accountLists ;

    SearchView searchView;
    AccountViewListAdapter adapter;

    private List<Data> originalAccountList;

    ImageButton filterbtn;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounviewlist);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));


        recyclerView = (RecyclerView) findViewById(R.id.viewlistrecyclerview);
        searchView = (SearchView) findViewById(R.id.searchview);
        filterbtn = (ImageButton) findViewById(R.id.filterbtn);
        filterbtn.setVisibility(View.GONE);


        Intent in = getIntent();
        token = in.getStringExtra("token");
       // Toast.makeText(getApplicationContext(),token,Toast.LENGTH_LONG).show();




        getItemALL();
        search();

    }

    private void getItemALL() {

        try {

            Call<APIResponseData> call = ApiClient.getUserService().getAllAccountDetails(HomePageActivity.token);

            call.enqueue(new Callback<APIResponseData>() {

                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onResponse(@NonNull Call<APIResponseData> call, @NonNull Response<APIResponseData> response) {
                    Log.e("success", String.valueOf(response));

                    //assert response.body() != null;

                    if (response.isSuccessful()){

                        APIResponseData  apiResponseData = response.body();
                        if (apiResponseData !=null){
                            accountLists = apiResponseData.getData();
                            System.out.print(accountLists.size());
                            System.out.print(accountLists.toString());
                            // Keep a copy of the original list
                            originalAccountList = new ArrayList<>(accountLists);
                            if (accountLists!=null){

                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(linearLayoutManager);
                                adapter = new AccountViewListAdapter(AccounviewlistActivity.this,accountLists,adapter);
                                recyclerView.setAdapter(adapter);
                            }
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseData> call, @NonNull Throwable t) {

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
                for (Data item : accountLists) {
                    if (item.getName().toLowerCase().contains(lowerCaseQuery)) {
                        found = true;
                        break;
                    }
                }

                if (found) {

                    adapter.getFilter().filter(query);
                    //  adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(AccounviewlistActivity.this, "No Match found", Toast.LENGTH_LONG).show();
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
                adapter.setProductList(new ArrayList<>(originalAccountList));
                return false;

            }
        });

    }
}