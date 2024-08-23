package com.example.ishopbillingsoftware.accounts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.items.APIResponseUpdate;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountEditListActivity extends AppCompatActivity {
    Button savebtn,deletebtn,viewbtn;
    EditText nameedit,taxnoedit,addresedit,pincodeedit,codeedit,mobileedit,
            phoneedit,emailedit,cpdit,pancardnoedit,openingbaledit;
    EditText printasedit,groupedit,undergroupedit,stateedit,
            drcredit,cityedit;

    String  name,printas,group,undergroup,openingbal,taxno,drcr,address,pincode,state,
            statecode,mobile,email,phone,cp,panno,city,accountid;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit_list);
        nameedit = findViewById(R.id.nameedit);
        taxnoedit = findViewById(R.id.taxnoedit);
        addresedit = findViewById(R.id.addresedit);
        pincodeedit = findViewById(R.id.pincodeedit);
        codeedit = findViewById(R.id.codeedit);
        openingbaledit = findViewById(R.id.openingbaledit);
        mobileedit = findViewById(R.id.mobileedit);
        phoneedit = findViewById(R.id.phoneedit);
        emailedit = findViewById(R.id.emailedit);
        cpdit = findViewById(R.id.cpedit);
        pancardnoedit = findViewById(R.id.pancardnoedit);
        savebtn = findViewById(R.id.updatebtn);
        deletebtn = findViewById(R.id.deletebtn);
        viewbtn = findViewById(R.id.viewbtn);
        printasedit = findViewById(R.id.printasedit);
        cityedit = findViewById(R.id.cityedit);
        groupedit = findViewById(R.id.groupedit);
        undergroupedit = findViewById(R.id.undergroupedit);
        stateedit = findViewById(R.id.stateedit);
        drcredit = findViewById(R.id.drcredit);//

        Intent in = getIntent();
        accountid =in.getStringExtra("accountId");
        name = in.getStringExtra("name");
        printas= in.getStringExtra("printAs");
        group = in.getStringExtra("group");
        undergroup = in.getStringExtra("tax");


        openingbal=in.getStringExtra("openingBal");
        drcr=in.getStringExtra("group");
        taxno= in.getStringExtra("taxNo");
        address = in.getStringExtra("address1");
        city = in.getStringExtra("city");
        state = in.getStringExtra("state");
        pincode = in.getStringExtra("pincode");

        statecode=in.getStringExtra("stateCode");
        mobile=in.getStringExtra("stateCode");
        email= in.getStringExtra("email");
        cp = in.getStringExtra("contactPerson");
       // panno = in.getStringExtra("mrp");



        // System.out.print("tax "+ in.getIntExtra("taxSlab",0));

        nameedit.setText(name);
        printasedit.setText(printas);
        groupedit.setText(group);
        undergroupedit.setText(undergroup);
        taxnoedit.setText(taxno);
        openingbaledit.setText(openingbal);
        drcredit.setText(drcr);
        addresedit.setText(address);
        cityedit.setText(city);
        stateedit.setText(state);
        codeedit.setText(statecode);
        pincodeedit.setText(pincode);
        mobileedit.setText(mobile);
        emailedit.setText(email);
        cpdit.setText(cp);
        pancardnoedit.setText("panno");



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"save",Toast.LENGTH_LONG).show();
                getupdate();
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(),AccounviewlistActivity.class);
                startActivity(in);

            }
        });
    }

    private void getupdate() {

        try{
            HashMap<String, String> add = new HashMap<String, String>();
            add.put("accountId",accountid);
            add.put( "name",nameedit.getText().toString());
            add.put("printAs","printas");
            add.put("group","group");
            add.put("openingBal",openingbaledit.getText().toString());
            add.put("DR_CR",openingbaledit.getText().toString());
            add.put("taxNo",taxnoedit.getText().toString());
            add.put("Address1",addresedit.getText().toString());
            add.put( "city","city");
            add.put("state","state");
            add.put("stateCode",codeedit.getText().toString());
            add.put("mobileNo",mobileedit.getText().toString());
            add.put("email", emailedit.getText().toString());
            add.put( "contactPerson", cpdit.getText().toString());
            add.put("panCardNo",pancardnoedit.getText().toString());

            System.out.print("itemadd"+add);




            Call<APIResponseUpdate> call = ApiClient.getUserService().accountupdate(add);

            call.enqueue(new Callback<APIResponseUpdate>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseUpdate> call, @NonNull Response<APIResponseUpdate> response) {



                    if(response.isSuccessful()){
                        APIResponseUpdate apiResponse = response.body();
                        assert apiResponse != null;
                        String success = apiResponse.getSuccess();
                        String message = apiResponse.getMessage();
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();




                    }else{
                        Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseUpdate> call, @NonNull Throwable t) {
                    Log.e("getUTL failure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getApplicationContext(), "Server Error Occured!!! Please Retry after some time", Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e){
            System.out.print("error"+e.toString());
        }


    }
}