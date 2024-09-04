package com.example.ishopbillingsoftware.accounts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



import com.example.ishopbillingsoftware.R;


import com.example.ishopbillingsoftware.items.APIResponseUpdate;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
Button savebtn,deletebtn,viewbtn,addnewbtn;
EditText nameedit,taxnoedit,addresedit,pincodeedit,codeedit,mobileedit,
        phoneedit,emailedit,cpdit,pancardnoedit,openingbaledit;
Spinner printasspinner,groupspinner,undergroupspinner,statespinner,
        drcrspinner,cityspinner;

String  printas,group,undergroup,state,
        drcr,city,token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


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
        savebtn = findViewById(R.id.savebtn);
        deletebtn = findViewById(R.id.deletebtn);
        viewbtn = findViewById(R.id.viewbtn);
        printasspinner = findViewById(R.id.printasspinner);
        cityspinner = findViewById(R.id.cityspinner);
        groupspinner = findViewById(R.id.groupspinner);
        undergroupspinner = findViewById(R.id.undergroupspinner);
        statespinner = findViewById(R.id.statespinner);
        drcrspinner = findViewById(R.id.drcrspinner);//

       // savebtn = findViewById(R.id.savebtn);
       // deletebtn = findViewById(R.id.deletebtn);
        //viewbtn = findViewById(R.id.viewbtn);
        addnewbtn = findViewById(R.id.addnewbtn);


        Intent in = getIntent();
        token = in.getStringExtra("token");



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"save",Toast.LENGTH_LONG).show();
             getsave();
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"delete",Toast.LENGTH_LONG).show();
            }
        });
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"view",Toast.LENGTH_LONG).show();

                Intent in = new Intent(getApplicationContext(),AccounviewlistActivity.class);
                startActivity(in);

            }
        });

        addnewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
                builder.setTitle("Account Group Entry");

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                builder.setView(customLayout);

                // add a button
                builder.setPositiveButton("Save", (dialog, which) -> {
                    // send data from the AlertDialog to the Activity
                    EditText editText = customLayout.findViewById(R.id.groupnameedit);
                    EditText editText1 = customLayout.findViewById(R.id.undergroupedit);
                    String groupname = editText.getText().toString();
                    String undergroupname = editText1.getText().toString();
                    sendDialogDataToActivity(groupname,undergroupname);
                });
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void sendDialogDataToActivity(String groupname, String undergroupname) {


    }

    private void getsave() {

        try{
            HashMap<String, String> add = new HashMap<String, String>();
            add.put( "name",nameedit.getText().toString());
            add.put("printAs","printas");
            add.put("group","group");
            add.put("openingBal",openingbaledit.getText().toString());
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

            Call<APIResponseUpdate> call = ApiClient.getUserService().create(add);

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