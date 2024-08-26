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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ishopbillingsoftware.homepage.HomePageActivity;
import com.example.ishopbillingsoftware.server.App;
import com.example.ishopbillingsoftware.server.UserService;
import com.example.ishopbillingsoftware.server.ApiClient;
import com.example.ishopbillingsoftware.server.APIResponseLogin;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText passwordtxt,emailtxt;
    String email,password,userId,isfirstlogin;
    Button login_btn;
    TextView forgot_password,reg;
    Boolean valid_email,
            isFirstLogin;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailtxt =  findViewById(R.id.emailtxt);
        passwordtxt = findViewById(R.id.passwordtxt);
        login_btn = findViewById(R.id.login_btn);
        forgot_password = findViewById(R.id.forgotpassword);
        reg = findViewById(R.id.createtxt);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));
        Intent in = getIntent();
        userId =  in.getStringExtra("userId");
        isfirstlogin=in.getStringExtra("isfirstlogin");

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkinternet();
                checkConnection(getApplicationContext());
                checkValidate();


            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                in.putExtra("userId",userId);
                startActivity(in);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(in);
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

        password = passwordtxt.getText().toString();
        email = emailtxt.getText().toString();

        registerValidationCheck();
        getLogin(password,email);

    }

    private Boolean registerValidationCheck() {
        password = passwordtxt.getText().toString();
        email = emailtxt.getText().toString();

        valid_email = isValidEmail(email);


        if (password == null || password.isEmpty() || password.equals("null")) {
            Toast.makeText(getApplicationContext(), "Please enter your password",
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

    private void getLogin(String password,String email) {
        try{
            HashMap<String, String> loginreg= new HashMap<String, String>();


            loginreg.put("email",email);
            loginreg.put("password",password);

            System.out.print(" userreg"+ loginreg);

            //UserService service =ApiClient.getClient(receivedData1).create(UserService.class);
            Call<APIResponseLogin> call = ApiClient.getUserService().userLogin(loginreg);

            call.enqueue(new Callback<APIResponseLogin>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseLogin> call, @NonNull Response<APIResponseLogin> response) {



                    if(response.isSuccessful()){
                        APIResponseLogin apiResponse = response.body();
                        assert apiResponse != null;
                        String success = apiResponse.getSuccess();
                        if (success.equals("true")){

                            String message = apiResponse.getMessage();
                            String token = apiResponse.getToken();
                            String activationKey = apiResponse.getActivationkey();

                            if (activationKey!=null)
                            {
                                Intent in = new Intent(getApplicationContext(), ActivationActivity.class);
                                in.putExtra("activationkey",activationKey);
                                in.putExtra("token",token);
                                in.putExtra("userId",userId);
                                startActivity(in);
                            }else {
                                Intent in = new Intent(getApplicationContext(), HomePageActivity.class);
                               // in.putExtra("activationkey",activationKey);
                                in.putExtra("token",token);
                                in.putExtra("userId",userId);
                                startActivity(in);
                            }





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

    }
    }
