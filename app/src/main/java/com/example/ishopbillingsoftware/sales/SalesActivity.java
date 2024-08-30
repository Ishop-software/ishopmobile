package com.example.ishopbillingsoftware.sales;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.homepage.HomePageActivity;
import com.example.ishopbillingsoftware.items.APIResponseItem;
import com.example.ishopbillingsoftware.server.ApiClient;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    Button save,delete,view;

    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.pager);
        save = findViewById(R.id.savebtn);
        delete = findViewById(R.id.deletebtn);
        view = findViewById(R.id.viewbtn);

        Intent in = getIntent();

        token = in.getStringExtra("token");
        
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getsaleregister();
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SalesViewListActivity.class);
                startActivity(in);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SalesListDetailActivity.class);
                startActivity(in);
            }
        });

        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#000000")));
        tabLayout.addTab(tabLayout.newTab().setText("Sales"));
        tabLayout.addTab(tabLayout.newTab().setText("Charges"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        TabLayoutAdapter adapter=new TabLayoutAdapter(this,getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    private void getsaleregister() {
        String partyname = SaleFragment.partyname.toString();
        int billno = Integer.parseInt(SaleFragment.billnoedit.getText().toString());
        String itemname = SaleFragment.itemname.toString();

        int qty = Integer.valueOf(SaleFragment.qtyedit.getText().toString());
        int free = Integer.valueOf(SaleFragment.freeedit.getText().toString());
        double rate = SaleFragment.rate;
        double discamount = Double.valueOf(SaleFragment.discamountedit.getText().toString());
        double basicAmount = Double.valueOf(SaleFragment.baedit.getText().toString());
        double taxAmount = Double.valueOf(SaleFragment.taxamountedit.getText().toString());
        double discs = Double.valueOf(SaleFragment.discperedit.getText().toString());
        double netValue = Double.valueOf(SaleFragment.netvalueedit.getText().toString());

        String otherCharges = ChargesFragment.ocname.toString();
        String remarks = (ChargesFragment.remarkedit.getText().toString());
       Double onValue = Double.valueOf((ChargesFragment.onvalueedit.getText().toString()));
       String discountedit = (ChargesFragment.discountedit.getText().toString());
       String plusMinus  = ChargesFragment.plusedit.getText().toString();
       Double amount = Double.valueOf(ChargesFragment.amountedit.getText().toString());

        try{
            HashMap<String, Object> saleadd = new HashMap<String, Object>();
            saleadd.put( "partyName",partyname);
            saleadd.put("billNo",billno);
            saleadd.put("dueDate","2024-12-31T00:00:00.000Z");
            saleadd.put("itemName",itemname);
            saleadd.put("qty",qty);
            saleadd.put("altQty",50);
            saleadd.put( "free",free);
            saleadd.put("per","Unit");
            saleadd.put("rate",rate);

            saleadd.put("discAmount",discamount);
            saleadd.put("basicAmount", basicAmount);
            saleadd.put( "taxAmount", taxAmount);
            saleadd.put("discs",discs);
            saleadd.put("netValue",netValue);


            saleadd.put("otherCharges",otherCharges);
            saleadd.put("remarks",remarks);
            saleadd.put( "onValue",onValue);
            saleadd.put("at",discountedit);
            saleadd.put("plusMinus",plusMinus);

            saleadd.put("amount", amount);

            System.out.print("saleadd"+saleadd);

            Call<APIResponseSale> call = ApiClient.getUserService().addsale(saleadd);

            call.enqueue(new Callback<APIResponseSale>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseSale> call, @NonNull Response<APIResponseSale> response) {
                    System.out.print("response"+response.toString());



                    if(response.isSuccessful()){
                        APIResponseSale apiResponse = response.body();
                        assert apiResponse != null;
                        String success = apiResponse.getSuccess();
                        String message = apiResponse.getMessage();
                        String productid = apiResponse.getSaleRegId();
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();



                        //Toast.makeText(getApplicationContext(),productid,Toast.LENGTH_LONG).show();
                        System.out.print("ITEM"+message+productid);
                    }else{
                        Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseSale> call, @NonNull Throwable t) {
                    Log.e("getUTL failure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getApplicationContext(), "Server Error Occured!!! Please Retry after some time", Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e){
            System.out.print("error"+e.toString());
        }
    }

}