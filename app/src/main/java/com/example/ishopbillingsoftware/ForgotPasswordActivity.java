package com.example.ishopbillingsoftware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.ishopbillingsoftware.items.APIResponseUpdate;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText newpasswordtxt,reenterpasswordtxt;
    String newpassword,reenterpassword,userId;
    Button updatepassword_btn;




    Boolean valid_email;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        newpasswordtxt =  findViewById(R.id.newpasswordtxt);
        reenterpasswordtxt = findViewById(R.id.reenterpasswordtxt);
        updatepassword_btn = findViewById(R.id.updatepassword_btn);


        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));

        Intent in  = getIntent();
        userId = in.getStringExtra("userId");

        updatepassword_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkinternet();
                checkConnection(getApplicationContext());
                checkValidate();


            }
        });

    }

    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi


                    return true;
                } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        return false;
    }
    private void checkValidate() {

        newpassword = newpasswordtxt.getText().toString();
        reenterpassword = reenterpasswordtxt.getText().toString();

        registerValidationCheck();
        getUpdate(newpassword,reenterpassword);

    }

    private Boolean registerValidationCheck() {
        newpassword = newpasswordtxt.getText().toString();
        reenterpassword = reenterpasswordtxt.getText().toString();
        Toast.makeText(getApplicationContext(), newpassword+reenterpassword,Toast.LENGTH_LONG).show();



        if (newpassword == null || newpassword.isEmpty() || newpassword.equals("null")) {
            Toast.makeText(getApplicationContext(), "Please enter your password",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (reenterpassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your reenterpassword id",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (!newpassword.equals(reenterpassword)) {

            Toast.makeText(getApplicationContext(), "Please re-enter your correct password",Toast.LENGTH_LONG).show();
            return false;
        } else  {
            return true;
        }
    }


    private void getUpdate(String newpassword,String reenterpassword) {
        try{
            HashMap<String, String> loginreg= new HashMap<String, String>();
            loginreg.put("password",reenterpassword);
            loginreg.put("userId",userId);

            System.out.print(" userreg"+ loginreg);


            Call<APIResponseUpdate> call = ApiClient.getUserService().forget(loginreg);

            call.enqueue(new Callback<APIResponseUpdate>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseUpdate> call, @NonNull Response<APIResponseUpdate> response) {

                    if(response.isSuccessful()){
                        APIResponseUpdate apiResponse = response.body();
                        String success = apiResponse.getSuccess();
                        if (success.equals("true")){

                            String message = apiResponse.getMessage();



                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(in);


                            reenterpasswordtxt.setText("");
                            newpasswordtxt.setText("");


                        }else{
                            String message = apiResponse.getMessage();
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                        }

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