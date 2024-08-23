package com.example.ishopbillingsoftware;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishopbillingsoftware.homepage.HomePageActivity;


public class ActivationActivity extends AppCompatActivity {
    EditText codetxt;
    String code;
    Button activate_btn;

    String activationKey,userId,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        codetxt =  findViewById(R.id.codetxt);




        activate_btn = findViewById(R.id.activate_btn);
        Intent in = getIntent();
        activationKey = in.getStringExtra("activationkey");
        token= in.getStringExtra("token");
        userId = in.getStringExtra("userId");

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));

        activate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkinternet();
                checkConnection(getApplicationContext());


                //checkValidate();


                code =codetxt.getText().toString();




               if (code.equals(activationKey)){
                    Intent in = new Intent(getApplicationContext(), HomePageActivity.class);
                    startActivity(in);
                    Toast.makeText(getApplicationContext(), "code",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "please recheck ur activation key",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    private void checkValidate() {

       code =codetxt.getText().toString();
       System.out.print("code"+code);
        System.out.print("activationKey"+activationKey);


        registerValidationCheck();

        if (code.equals(activationKey)){
            Intent in = new Intent(getApplicationContext(), HomePageActivity.class);
            startActivity(in);
            Toast.makeText(getApplicationContext(), "code",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "please recheck ur activation key",
                    Toast.LENGTH_LONG).show();
        }
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


    private Boolean registerValidationCheck() {

        code = codetxt.getText().toString();


        if (code == null || code.isEmpty() || code.equals("null")) {
            Toast.makeText(getApplicationContext(), "Please enter your code",
                    Toast.LENGTH_LONG).show();
            return false;

        }else {
            return true;
        }
    }



    private void getActivation(String code) {
      /*  try{
            HashMap<String, String> loginreg= new HashMap<String, String>();


            loginreg.put("email",email);
            loginreg.put("password",password);

            System.out.print(" userreg"+ loginreg);


            Call<APIResponseLogin> call = ApiClient.getUserService().userLogin(loginreg);

            call.enqueue(new Callback<APIResponseLogin>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseLogin> call, @NonNull Response<APIResponseLogin> response) {



                    if(response.isSuccessful()){
                        APIResponseLogin apiResponse = response.body();
                        String success = apiResponse.getSuccess();
                        if (success.equals("true")){

                            String message = apiResponse.getMessage();
                            String token = apiResponse.getToken();

                            System.out.print("email"+token);
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getApplicationContext(), ActivationActivity.class);
                            startActivity(in);

                            passwordtxt.setText("");
                            emailtxt.setText("");


                        }else{
                            String message = apiResponse.getMessage();
                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseLogin> call, @NonNull Throwable t) {
                    Log.e("getUTL failure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getApplicationContext(), "Server Error Occured!!! Please Retry after some time", Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e){
            System.out.print("error"+e.toString());
        }
*/
    }
}