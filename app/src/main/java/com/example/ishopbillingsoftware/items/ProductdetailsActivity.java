package com.example.ishopbillingsoftware.items;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductdetailsActivity extends AppCompatActivity {

    String itemname,shortname,hsc,company,group,productid;
    Double purchase,sale,mrp;
    int tax;
    EditText itemedit,shortnameedit,hsnedit,taxedit,companynameedit,groupedit,purchaseedit,
    saleedit,mrpedit;
    Button updatebtn,viewbtn;

    RadioGroup mradioGroup,sradioGroup,pradioGroup;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));


        itemedit = findViewById(R.id.itemedit);
        shortnameedit = findViewById(R.id.shortnameedit);
        hsnedit = findViewById(R.id.hsnedit);
        purchaseedit = findViewById(R.id.purchaseedit);
        taxedit = findViewById(R.id.taxedit);
        saleedit = findViewById(R.id.saleedit);
        mrpedit = findViewById(R.id.mrpedit);
        companynameedit = findViewById(R.id.companynameedit);
        groupedit = findViewById(R.id.groupedit);
        updatebtn = findViewById(R.id.updatebtn);
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

        itemedit.setText(itemname);
        shortnameedit.setText(shortname);
        hsnedit.setText(hsc);
        taxedit.setText(String.valueOf(tax));
        companynameedit.setText(company);
        groupedit.setText(group);
        purchaseedit.setText(String.valueOf(purchase));
        saleedit.setText(String.valueOf(sale));
        mrpedit.setText(String.valueOf(mrp));
        pradioGroup = findViewById(R.id.pradioGroup);
        sradioGroup = findViewById(R.id.sradioGroup);
        mradioGroup = findViewById(R.id.mradioGroup);

        pradioGroup.setVisibility(View.GONE);
        sradioGroup.setVisibility(View.GONE);
        mradioGroup.setVisibility(View.GONE);


        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getupdate();

            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewListActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getupdate() {
        try{
            HashMap<String, Object> itemadd = new HashMap<String, Object>();
            itemadd.put("productItemId",productid);
            itemadd.put( "itemName",itemedit.getText().toString());
            itemadd.put("shortName",shortnameedit.getText().toString());
            itemadd.put("HSNCode",hsnedit.getText().toString());
            itemadd.put("taxSlab",String.valueOf(tax));
            itemadd.put("primaryUnit","box");
            itemadd.put("company",companynameedit.getText().toString());
            itemadd.put( "uploadImage","https://example.com/image.jpg");
            itemadd.put("maintainBatch",false);
            itemadd.put("group",groupedit.getText().toString());

            itemadd.put("serialNoTracking",false);
            itemadd.put("variation", "Color");
            itemadd.put( "color", "RED");
            itemadd.put("size","medium");
            itemadd.put("expDate","2024-12-31T00:00:00.000Z");


            itemadd.put("mfgDate","2024-12-31T00:00:00.000Z");
            itemadd.put("purchase",purchaseedit.getText().toString());
            itemadd.put( "salePrice",saleedit.getText().toString());
            itemadd.put("mrp",mrpedit.getText().toString());
            itemadd.put("basicPrice",140.00);

            itemadd.put("selfVal", 130.00);
            itemadd.put("minSalePrice",120.00);
            itemadd.put( "barcode",1209494);
            itemadd.put("openingPck",50);
            itemadd.put("openingValue",50000);

            itemadd.put( "delete",false);
            itemadd.put("copy",false);
            itemadd.put("details","This is a sample product used for demonstration.");
            System.out.print("update"+itemadd);


            Call<APIResponseUpdate> call = ApiClient.getUserService().update(itemadd);


            call.enqueue(new Callback<APIResponseUpdate>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})

                @Override
                public void onResponse(@NonNull Call<APIResponseUpdate> call, @NonNull Response<APIResponseUpdate> response) {
                    if(response.isSuccessful()){

                        APIResponseUpdate apiResponse = response.body();
                        assert apiResponse != null;

                        String success = apiResponse.getSuccess();

                        Toast.makeText(getApplicationContext(),success,Toast.LENGTH_LONG).show();

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