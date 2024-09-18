package com.example.ishopbillingsoftware.accounts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishopbillingsoftware.R;

public class AccountviewdetailsActivity extends AppCompatActivity {

    TextView accounttxt,printastxt,grouptxt,openingbalancetxt,dctxt,pincodetxt,
            taxnotxt,addresstxt,mobiletxt,citytxt,Contactpersontxt,emailtxt,pancardnotxt,statetxt;
    String  name,printas,group,openingbal,taxno,address,pincode,
            drcr,mobile,email,state,statecode,panno,city,accountid,ContactPerson;
    Button listbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountviewdetails);

        accounttxt = findViewById(R.id.accounttxt);
        printastxt = findViewById(R.id.printastxt);
        grouptxt = findViewById(R.id.grouptxt);
        openingbalancetxt = findViewById(R.id.openingbalancetxt);
        dctxt = findViewById(R.id.dctxt);
        taxnotxt = findViewById(R.id.taxnotxt);
        statetxt = findViewById(R.id.statetxt);
        addresstxt = findViewById(R.id.addresstxt);
        citytxt = findViewById(R.id.citytxt);
        pincodetxt = findViewById(R.id.pincodetxt);
        mobiletxt = findViewById(R.id.mobiletxt);
        emailtxt = findViewById(R.id.emailtxt);
        Contactpersontxt = findViewById(R.id.Contactpersontxt);
        pancardnotxt = findViewById(R.id.pancardnotxt);
        listbtn = findViewById(R.id.viewbtn);


        Intent in = getIntent();

        name = in.getStringExtra("name");
        printas = in.getStringExtra("printAs");
        group = in.getStringExtra("group");
        openingbal= String.valueOf(in.getDoubleExtra("openingBal",10000));
        taxno= in.getStringExtra("taxNo");
        address = in.getStringExtra("address1");
        city = in.getStringExtra("city");
        pincode  = String.valueOf(in.getIntExtra("pincode",0));
        mobile = in.getStringExtra("mobileNo");
        state = in.getStringExtra("state");
        statecode = in.getStringExtra("statecode");
        drcr=in.getStringExtra("group");
        email = in.getStringExtra("email");
        ContactPerson = in.getStringExtra("contactPerson");

        accounttxt.setText(name);
        printastxt.setText(printas);
        grouptxt.setText(group);
        openingbalancetxt.setText(openingbal);
        taxnotxt.setText(taxno);
        addresstxt.setText(address);
        pincodetxt.setText(pincode);
        dctxt.setText(drcr);
        citytxt.setText(city);
        mobiletxt.setText(mobile);
        statetxt.setText(state);
        emailtxt.setText(email);
        Contactpersontxt.setText(ContactPerson);





        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),AccounviewlistActivity.class);
                startActivity(in);
            }
        });


    }
}