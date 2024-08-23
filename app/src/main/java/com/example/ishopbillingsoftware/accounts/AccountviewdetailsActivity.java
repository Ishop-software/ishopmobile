package com.example.ishopbillingsoftware.accounts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishopbillingsoftware.R;

public class AccountviewdetailsActivity extends AppCompatActivity {

    TextView accounttxt,grouptxt,citytxt,mobiletxt,taxnotxt,openingbalancetxt,dctxt;
    String  name,group,openingbal,taxno,drcr,mobile,email,phone,cp,panno,city,accountid;
    Button listbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountviewdetails);

        accounttxt = findViewById(R.id.accounttxt);
        grouptxt = findViewById(R.id.grouptxt);
        citytxt = findViewById(R.id.citytxt);
        taxnotxt = findViewById(R.id.taxnotxt);
        mobiletxt = findViewById(R.id.mobiletxt);
        openingbalancetxt = findViewById(R.id.openingbalancetxt);
        dctxt = findViewById(R.id.dctxt);
        listbtn = findViewById(R.id.viewbtn);


        Intent in = getIntent();

        name = in.getStringExtra("name");
        group = in.getStringExtra("group");
        openingbal=in.getStringExtra("openingBal");
        drcr=in.getStringExtra("group");
        taxno= in.getStringExtra("taxNo");
        city = in.getStringExtra("city");
        mobile=in.getStringExtra("stateCode");
        accounttxt.setText(name);
        grouptxt.setText(group);
        taxnotxt.setText(taxno);
        openingbalancetxt.setText(openingbal);
        dctxt.setText(drcr);
        citytxt.setText(city);
        mobiletxt.setText(mobile);





        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),AccounviewlistActivity.class);
                startActivity(in);
            }
        });


    }
}