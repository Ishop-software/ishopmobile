package com.example.ishopbillingsoftware.sales;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ishopbillingsoftware.R;
import com.google.android.material.tabs.TabLayout;

public class SalesActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    Button save,delete,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.pager);
        save = findViewById(R.id.savebtn);
        delete = findViewById(R.id.deletebtn);
        view = findViewById(R.id.viewbtn);

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

}