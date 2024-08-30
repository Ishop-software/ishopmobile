package com.example.ishopbillingsoftware.printdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.homepage.HomeItem;
import com.example.ishopbillingsoftware.homepage.HomeItemsAdapter;

import java.util.ArrayList;

public class Salesprint1Activity extends AppCompatActivity {
TextView billnotxt,usertxt,datetxt,timetxt,nametxt1,mobiletxt1;
RecyclerView printitemrecyclerview;
PrintAdapter printAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesprint1);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));

        billnotxt =findViewById(R.id.billnotxt);
        usertxt = findViewById(R.id.usertxt);
        datetxt = findViewById(R.id.datetxt);
        timetxt = findViewById(R.id.timetxt);
        nametxt1 = findViewById(R.id.nametxt1);
        mobiletxt1 = findViewById(R.id.mobiletxt1);
        printitemrecyclerview = findViewById(R.id.itemrecyclerview);


        ArrayList<HomeItem> courseModelArrayList = new ArrayList<HomeItem>();
        courseModelArrayList.add(new HomeItem("Accounts"));
        courseModelArrayList.add(new HomeItem("Items"));
        courseModelArrayList.add(new HomeItem("Outstandings"));
        courseModelArrayList.add(new HomeItem("Sales"));
        courseModelArrayList.add(new HomeItem("Purchases"));
        courseModelArrayList.add(new HomeItem("POS"));
        printAdapter = new PrintAdapter(getApplicationContext(), courseModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);


        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        printitemrecyclerview.setLayoutManager(layoutManager);
        printitemrecyclerview.setAdapter(printAdapter);

    }
}