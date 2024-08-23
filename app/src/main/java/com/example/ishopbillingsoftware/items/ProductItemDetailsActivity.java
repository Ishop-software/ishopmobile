package com.example.ishopbillingsoftware.items;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishopbillingsoftware.R;

public class ProductItemDetailsActivity extends AppCompatActivity {
    String itemname,shortname,hsc,company,group,productid;
    Double purchase,sale,mrp;
    int tax;
    TextView itemedit,shortnameedit,hsnedit,taxedit,companynameedit,groupedit,purchaseedit,
            saleedit,mrpedit;
    Button viewbtn;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item_details);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));


        itemedit = findViewById(R.id.itemtxt);
        shortnameedit = findViewById(R.id.shortnametxt);
        hsnedit = findViewById(R.id.hsccodetxt);
        purchaseedit = findViewById(R.id.purchasetxt);
        taxedit = findViewById(R.id.taxslabtxt);
        saleedit = findViewById(R.id.saletxt);
        mrpedit = findViewById(R.id.mrptxt);
        companynameedit = findViewById(R.id.companytxt);
        groupedit = findViewById(R.id.grouptxt);

        viewbtn = findViewById(R.id.viewbtn);

        Intent in = getIntent();
        itemname = in.getStringExtra("itemname");
        shortname= in.getStringExtra("shortname");
        hsc = in.getStringExtra("hsccode");
        tax = in.getIntExtra("tax",0);


        company=in.getStringExtra("company");
        group=in.getStringExtra("group");
        purchase= in.getDoubleExtra("purchase",0);
        sale = in.getDoubleExtra("sale",0);
        mrp = in.getDoubleExtra("mrp",0);
        productid = in.getStringExtra("productid");


        // System.out.print("tax "+ in.getIntExtra("taxSlab",0));

        itemedit.setText( "ItemName:"+ itemname);
        shortnameedit.setText("ShortName:"+ shortname);
        hsnedit.setText("HSN Code:"+ hsc);
        taxedit.setText("Tax:"+ String.valueOf(tax));
        companynameedit.setText("Company Name:"+ company);
        groupedit.setText("Group Name:"+ group);
        purchaseedit.setText("Purchase Rate:"+ String.valueOf(purchase));
        saleedit.setText("Sale Rate"+ String.valueOf(sale));
        mrpedit.setText("Mrp Rate"+ String.valueOf(mrp));




        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewListActivity.class);
                startActivity(intent);
            }
        });


    }
}