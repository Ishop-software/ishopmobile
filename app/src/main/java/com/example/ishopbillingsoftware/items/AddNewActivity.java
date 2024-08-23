package com.example.ishopbillingsoftware.items;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.server.ApiClient;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewActivity extends AppCompatActivity   {


    String[] tax = { "Tax 0.5%", "Tax 12.5%",
            "Tax 28%", "Tax 3%","No Tax",
           };
    String[] altstring = { "Tax 0.5%", "Tax 12.5%",
            "Tax 28%", "Tax 3%",
    };
    String[] cfString = { "Tax 0.5%", "Tax 12.5%",
            "Tax 28%", "Tax 3%",
    };
    String[] primaryunit = { "Box", "Bag","Dozen","Gms", "Kgs","Ltr","pcs","qntl"
    };
    Spinner taxspin,altspin,companynamespin,cfspin,pricespin,groupspin,primaryunitspin;



    EditText itemedit,shortnameedit,hsccodeedit,companyedit,groupedit,purchaseedit,saleedit,mrpedit;

    ListView cl;
    List<String> company;

    Button savebtn,deletebtn,viewbtn;


ImageButton puaddnewButton;
    private static final String PREFERENCES_NAME = "my_preferences";
    private static final String LIST_KEY = "my_string_list";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    RadioGroup mradioGroup,sradioGroup,pradioGroup;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));

        itemedit = findViewById(R.id.itemedit);
        shortnameedit = findViewById(R.id.shortnameedit);
        hsccodeedit = findViewById(R.id.hsccodeedit);
        purchaseedit = findViewById(R.id.purchaseedit);
        saleedit = findViewById(R.id.saleedit);
        mrpedit = findViewById(R.id.mrpedit);
        companyedit = findViewById(R.id.companynameedit);
        groupedit = findViewById(R.id.groupnameedit);
        puaddnewButton = findViewById(R.id.puaddnewButton);
        savebtn = findViewById(R.id.savebtn);
        deletebtn = findViewById(R.id.deletebtn);
        viewbtn = findViewById(R.id.viewbtn);


        taxspin = findViewById(R.id.taxspinner);
        primaryunitspin = findViewById(R.id.primaryunitspinner);
        pradioGroup = findViewById(R.id.pradioGroup);
        sradioGroup = findViewById(R.id.sradioGroup);
        mradioGroup = findViewById(R.id.mradioGroup);

        pradioGroup.setVisibility(View.GONE);
        sradioGroup.setVisibility(View.GONE);
        mradioGroup.setVisibility(View.GONE);

        //taxspinner

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tax);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        taxspin.setAdapter(ad);

        taxspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //primaryunit spinner

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter pu
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                primaryunit);

        // set simple layout resource file
        // for each item of spinner
        pu.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        primaryunitspin.setAdapter(pu);

        primaryunitspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getsaveitem();
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

    private void getsaveitem() {

        try{
            HashMap<String, Object> itemadd = new HashMap<String, Object>();
            itemadd.put( "itemName",itemedit.getText().toString());
            itemadd.put("shortName",shortnameedit.getText().toString());
            itemadd.put("HSNCode",hsccodeedit.getText().toString());
            itemadd.put("taxSlab",18);
            itemadd.put("primaryUnit","box");
            itemadd.put("company",companyedit.getText().toString());
            itemadd.put( "uploadImage","https://example.com/image.jpg");
            itemadd.put("maintainBatch",true);
            itemadd.put("group",groupedit.getText().toString());

            itemadd.put("serialNoTracking",true);
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
            System.out.print("itemadd"+itemadd);




            Call<APIResponseItem> call = ApiClient.getUserService().addItem(itemadd);

            call.enqueue(new Callback<APIResponseItem>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseItem> call, @NonNull Response<APIResponseItem> response) {



                    if(response.isSuccessful()){
                       APIResponseItem apiResponse = response.body();
                        assert apiResponse != null;
                        String success = apiResponse.getSuccess();
                        String message = apiResponse.getMessage();
                        String productid = apiResponse.getProductItemId();
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();



                        //Toast.makeText(getApplicationContext(),productid,Toast.LENGTH_LONG).show();
                        System.out.print("ITEM"+message+productid);
                    }else{
                        Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseItem> call, @NonNull Throwable t) {
                    Log.e("getUTL failure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getApplicationContext(), "Server Error Occured!!! Please Retry after some time", Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e){
            System.out.print("error"+e.toString());
        }

    }










   /* public void showAlertDialogButtonClicked(View view) {
        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Primary Unit");

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.primary_alert, null);



        builder.setView(customLayout);
        altspin = customLayout.findViewById(R.id.altspinner);
        cfspin = customLayout.findViewById(R.id.cfspinner);
        pricespin = customLayout.findViewById(R.id.pricespinner);

        altspin.setOnItemSelectedListener(this);
        cfspin.setOnItemSelectedListener(this);
        pricespin.setOnItemSelectedListener(this);
        //alter

        ArrayAdapter alter
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                altstring);


        alter.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);


        altspin.setAdapter(alter);


        //cf
        ArrayAdapter cf
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                cfString);


        cf.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);


        cfspin.setAdapter(cf);


        //price

        ArrayAdapter price
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                priceper);


        price.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);


        pricespin.setAdapter(price);


        // add a button
        builder.setPositiveButton("OK", (dialog, which) -> {
    text = altspin.getSelectedItem().toString() +cfspin.getSelectedItem().toString()+pricespin.getSelectedItem().toString();
            // send data from the AlertDialog to the Activity
sendDialogDataToActivity(text);

        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Do something with the data coming from the AlertDialog
    private void sendDialogDataToActivity(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }*/

}


