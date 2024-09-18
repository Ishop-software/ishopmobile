package com.example.ishopbillingsoftware.sales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.RegisterActivity;
import com.example.ishopbillingsoftware.accounts.AccountActivity;
import com.example.ishopbillingsoftware.homepage.HomePageActivity;
import com.example.ishopbillingsoftware.items.APIResponseItem;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewChargesActivity extends AppCompatActivity {
EditText chargesedit,printasedit,hsnedit;
Spinner accounthpspinner,taxspinner;
RadioGroup pmradioGroup,iacradioGroup;
RadioButton plusRB,minusRB,aaRB,onqtyRB,percentageRB,onbagsRB,onweightsRB;
CheckBox taxapplicable;

Button savebtn,deletebtn,viewbtn;
TextView edittxt,addnewtxt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_charges);

        onqtyRB =  findViewById(R.id.onqtyRB);
        percentageRB = findViewById(R.id.percentageRB);
        onbagsRB = findViewById(R.id.onbagsRB);
        onweightsRB = findViewById(R.id.onweightsRB);
        taxapplicable = findViewById(R.id.taxapplicable);

        savebtn = findViewById(R.id.savebtn);
        deletebtn = findViewById(R.id.deletebtn);
        viewbtn = findViewById(R.id.viewbtn);
        addnewtxt = findViewById(R.id.addnewtxt);
        edittxt = findViewById(R.id.edittxt);

        chargesedit =  findViewById(R.id.chargesedit);
        printasedit = findViewById(R.id.printasedit);
        hsnedit = findViewById(R.id.hsnedit);
        accounthpspinner = findViewById(R.id.accounthpspinner);
        taxspinner = findViewById(R.id.taxspinner);

        pmradioGroup =  findViewById(R.id.pmradioGroup);
        iacradioGroup = findViewById(R.id.iacradioGroup);
        plusRB = findViewById(R.id.plusRB);
        minusRB = findViewById(R.id.minusRB);
        aaRB = findViewById(R.id.aaRB);

        edittxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(in);
            }
        });

        addnewtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(in);
            }
        });


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getaddcharges();

            }
        });


        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(), ChargesViewListActivity.class);
                startActivity(in);

            }
        });


    }

    private void getaddcharges() {
        try{
        HashMap<String, Object> taxSettings = new HashMap<>();
        taxSettings.put("taxSlab", "GST");
        taxSettings.put("HSNCode", "9987");
        taxSettings.put("taxApplicable", true);

        // Create the main hashmap
        HashMap<String, Object> chargesMap = new HashMap<>();
        chargesMap.put("chargesHeading", chargesedit.getText().toString());
        chargesMap.put("printAs", printasedit.getText().toString());
        chargesMap.put("accountHeadToPost", "Service Account");
        chargesMap.put("typesOfCharges", "Percentage");
        chargesMap.put("inputAmountOfChargesAs", "10%");

        // Use an ArrayList to store tax settings
        ArrayList<Map<String, Object>> taxSettingsList = new ArrayList<>();
        taxSettingsList.add(taxSettings);

        // Add the list to the main hashmap
        chargesMap.put("taxSettings", taxSettingsList);
            System.out.print("chrgess"+chargesMap);

        Call<APIResponseCharges> call = ApiClient.getUserService().addcharges(chargesMap);

        call.enqueue(new Callback<APIResponseCharges>() {
            @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
            @Override
            public void onResponse(@NonNull Call<APIResponseCharges> call, @NonNull Response<APIResponseCharges> response) {

 System.out.print("chrges"+response.toString());



                if(response.isSuccessful()){
                    APIResponseCharges apiResponse = response.body();
                    assert apiResponse != null;
                    String success = apiResponse.getSuccess();
                    String message = apiResponse.getMessage();
                    String productid = apiResponse.getChargeRegId();
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();



                    //Toast.makeText(getApplicationContext(),productid,Toast.LENGTH_LONG).show();
                    System.out.print("ITEM"+message+productid);
                }else{
                    Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<APIResponseCharges> call, @NonNull Throwable t) {
                Log.e("getUTL failure", Objects.requireNonNull(t.getLocalizedMessage()));
                Toast.makeText(getApplicationContext(), "Server Error Occured!!! Please Retry after some time", Toast.LENGTH_LONG).show();
            }
        });
    }catch(Exception e){
        System.out.print("error"+e.toString());
    }

    }
}