package com.example.ishopbillingsoftware.sales;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.items.ViewListActivity;

public class ChargesViewListActivity extends AppCompatActivity {
    String account,plusminus,inputas,applyon,calculate,ro,slab,hsn;
    Double purchase,sale,mrp;
    int tax;
    TextView accounttxt,plusminustxt,inputastxt,applyontxt,calculatetxt,rotxt,taxtxt,
            slabtxt,hsntxt;
    Button viewbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charges_view_list);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));


        accounttxt = findViewById(R.id.accounttxt);
        plusminustxt = findViewById(R.id.plusminustxt);
        inputastxt = findViewById(R.id.inputastxt);
        applyontxt = findViewById(R.id.applyontxt);
        calculatetxt = findViewById(R.id.calculatetxt);
        rotxt = findViewById(R.id.rotxt);
        taxtxt = findViewById(R.id.taxtxt);
        slabtxt = findViewById(R.id.slabtxt);
        hsntxt = findViewById(R.id.hsntxt);

        viewbtn = findViewById(R.id.viewbtn);

        Intent in = getIntent();
        account = in.getStringExtra("itemname");
        plusminus= in.getStringExtra("shortname");
        inputas = in.getStringExtra("hsccode");
        tax = in.getIntExtra("tax",0);
        calculate=in.getStringExtra("company");
        applyon=in.getStringExtra("group");
        ro= in.getStringExtra("purchase");
        slab = in.getStringExtra("sale");
        hsn = in.getStringExtra("mrp");


        // System.out.print("tax "+ in.getIntExtra("taxSlab",0));

        accounttxt.setText( "Account:"+ account);
        plusminustxt.setText("+/-:"+ plusminus);
        inputastxt.setText("Input As:"+ inputas);
        applyontxt.setText("Apply on:"+ applyon);
        calculatetxt.setText("Calculate Discount:"+ calculate);
        rotxt.setText("RO:"+ ro);
        taxtxt.setText("Tax:"+tax);
        slabtxt.setText("Slab"+ slab);
        hsntxt.setText("HSN"+ hsn);




        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewListActivity.class);
                startActivity(intent);
            }
        });


    }
}