package com.example.ishopbillingsoftware.sales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.homepage.HomePageActivity;
import com.example.ishopbillingsoftware.items.ViewListActivity;
import com.example.ishopbillingsoftware.items.ViewListAdapter;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChargesViewListActivity extends AppCompatActivity {
    String account,plusminus,inputas,applyon,calculate,ro,slab,hsn;
    Double purchase,sale,mrp;
    int tax;

    List<ChargesList> chargesLists;
    RecyclerView otherchargesrecyclerview;
    OtherchargesAdapter otherchargesAdapter;
    public static String taxslab,hsncode,taxapplicable;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charges_view_list);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));

        otherchargesrecyclerview = findViewById(R.id.otherchargesrecyclerview);




        getAllotherchargeslist();





        // System.out.print("tax "+ in.getIntExtra("taxSlab",0));




    }

    private void getAllotherchargeslist() {
        try{


            Call<APIResponseChargesList> call = ApiClient.getUserService().viewlistcharge();

            call.enqueue(new Callback<APIResponseChargesList>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseChargesList> call, @NonNull Response<APIResponseChargesList> response) {
                    System.out.print("response"+response.toString());
                    if(response.isSuccessful()){
                        APIResponseChargesList apiResponse = response.body();
                        assert apiResponse != null;
                        String success = apiResponse.getSuccess();
                        chargesLists = new ArrayList<>();
                         chargesLists = apiResponse.getMessage();


                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        otherchargesrecyclerview.setLayoutManager(linearLayoutManager);


                        otherchargesAdapter = new OtherchargesAdapter(ChargesViewListActivity.this,
                                chargesLists,otherchargesAdapter);


                        // Setting Adapter to RecyclerView
                        otherchargesrecyclerview.setAdapter(otherchargesAdapter);


                        Toast.makeText(getApplicationContext(),success,Toast.LENGTH_LONG).show();


                    }else{

                        Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseChargesList> call, @NonNull Throwable t) {
                    Log.e("getUTL failure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getApplicationContext(), "Server Error Occured!!! Please Retry after some time", Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e){
            System.out.print("error"+e.toString());
        }
    }
}