package com.example.ishopbillingsoftware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ishopbillingsoftware.server.APIResponse;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText usernametxt,companytxt,passwordtxt,emailtxt,mobilenotxt;
    String name,companyname,email,mobile,password;
    Button reg_btn;
    Boolean valid_email;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernametxt =  findViewById(R.id.usernametxt);
        companytxt=findViewById(R.id.companytxt);
        passwordtxt = findViewById(R.id.passwordtxt);
        emailtxt =  findViewById(R.id.emailtxt);
        mobilenotxt = findViewById(R.id.mobilenotxt);
        reg_btn =findViewById(R.id.register_btn);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));

         reg_btn.setOnClickListener(new View.OnClickListener() {
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
        name = usernametxt.getText().toString();
        companyname = companytxt.getText().toString();
        password = passwordtxt.getText().toString();
        email = emailtxt.getText().toString();
        mobile = mobilenotxt.getText().toString();

       registerValidationCheck();
       getregister(name,companyname,password,email,mobile);

    }



    private Boolean registerValidationCheck() {
        name = usernametxt.getText().toString();
        companyname = companytxt.getText().toString();
        password = passwordtxt.getText().toString();
        email = emailtxt.getText().toString();
        mobile = mobilenotxt.getText().toString();
        valid_email = isValidEmail(email);

        if (name == null || name.isEmpty() || name.equals("null")) {
            Toast.makeText(getApplicationContext(), "Please enter your name",
                    Toast.LENGTH_LONG).show();
            return false;
        } if (companyname == null || companyname.isEmpty() || companyname.equals("null")) {
            Toast.makeText(getApplicationContext(), "Please enter your company name",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        else if (mobile == null || mobile.isEmpty() || mobile.equals("null") || mobile.length() != 10) {
            Toast.makeText(getApplicationContext(), "Please enter your mobile no",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (email == null || email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your email id",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (!valid_email) {
            emailtxt.setError("Please enter your valid email id");
            return false;
        }else {
            return true;
        }
    }

    private boolean isValidEmail(String semail) {
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\\-_\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\"\\.]]+");
        Matcher m = emailPattern.matcher(semail);
        return m.matches();
    }

    private void getregister(String name,String companyname, String password, String email, String mobile) {
        try{

            // Creating the paymentDetails map
            HashMap<String, String> paymentDetails = new HashMap<>();
            paymentDetails.put("transactionId", "12345");
            paymentDetails.put("amount", "500");
            paymentDetails.put("currency", "INR");
            paymentDetails.put("paymentDate", "07-08-2024");

            // Creating the subscription map
            HashMap<String, Object> subscription = new HashMap<>();
            subscription.put("planName", "basic");
            subscription.put("isFreeTrial", "true");
            subscription.put("isFreeTrialUsed", "true");
            subscription.put("paymentFrequency", "monthly");
            subscription.put("startDate", "08-08-2024");
            subscription.put("endDate", "08-09-2024");
            subscription.put("paymentDetails", paymentDetails);
            subscription.put("paymentLogs", "Array");

            // Creating the main user map
            HashMap<String, Object> user = new HashMap<>();
            user.put("companyName", companyname);
            user.put("name", name);
            user.put("mobileNo", "+91"+mobile);
            user.put("email", email);
            user.put("password", password);
            user.put("isFirstLogin", false);
            user.put("subscription", subscription);



            System.out.print(" userreg"+user);


            Call<APIResponse> call = ApiClient.getUserService().userReg(user);

            call.enqueue(new Callback<APIResponse>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                    Log.e("reister",response.toString());



                    if(response.isSuccessful()){
                        APIResponse apiResponse = response.body();
                        assert apiResponse != null;
                        String success = apiResponse.getSuccess();

                        if (success.equals("true")){

                            String message = apiResponse.getMessage();
                            String userId = apiResponse.getUserId();
                            System.out.print("userId"+ password);
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

                            usernametxt.setText("");
                            passwordtxt.setText("");
                            companytxt.setText("");
                            emailtxt.setText("");
                            mobilenotxt.setText("");

                            Intent in  = new Intent(getApplicationContext(), LoginActivity.class);
                            in.putExtra("userId",userId);
                            startActivity(in);

                        }else{
                            String message = apiResponse.getMessage();
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                    Log.e("getUTL failure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getApplicationContext(), "Server Error Occured!!! Please Retry after some time", Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e){
            System.out.print("error"+e.toString());
        }

    }
}