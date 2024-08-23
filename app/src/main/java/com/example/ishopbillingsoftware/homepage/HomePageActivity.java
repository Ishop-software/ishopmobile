package com.example.ishopbillingsoftware.homepage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.ishopbillingsoftware.CashPaymentActivity;
import com.example.ishopbillingsoftware.CashReceiptActivity;
import com.example.ishopbillingsoftware.GSTActivity;
import com.example.ishopbillingsoftware.LoginActivity;
import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.ReportActivity;
import com.example.ishopbillingsoftware.TermsConditionsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomePageActivity extends AppCompatActivity {



    public TextView mhStudentImage,sbplannametxt;
    SharedPreferences.Editor netcomOfflineStoreSP;
    public static final String MY_PREFS_NAME = "NetcomOfflineStore";
    DrawerLayout homeSideDrawerLayout;
    NavigationView navigationView;
    Toolbar home_toolbar;
    Fragment selectedFragment;

   public static String token,userid;







    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        homeSideDrawerLayout  = findViewById(R.id.sideDrawerLayout);
        home_toolbar = findViewById(R.id.home_toolbar);
        navigationView = findViewById(R.id.navigationview);

        Intent intent = getIntent();

        token= intent.getStringExtra("token");
        userid = intent.getStringExtra("userId");



        NavigationView navigationView =  findViewById(R.id.navigationview);
        View headerView = navigationView.getHeaderView(0);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,homeSideDrawerLayout,home_toolbar, R.string.navigation_open,R.string.navigation_close);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        homeSideDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(item -> {
            final int buttonView = item.getItemId();
            // Handle navigation view item clicks here.
            if (buttonView == R.id.m_ledger) {
                Intent in = new Intent(this, ReportActivity.class);
                //in.putExtra("token",HomePageActivity.loginusertoken);
                startActivity(in);
            }
            else if(buttonView == R.id.m_cashpayment) {
                Intent in = new Intent(this, CashPaymentActivity.class);
                startActivity(in);
            }
            else if(buttonView == R.id.m_cashreceipt) {
                Intent in = new Intent(this, CashReceiptActivity.class);
                startActivity(in);
            }
            else if(buttonView == R.id.m_journal) {
                Intent in = new Intent(this, JournalActivity.class);
                startActivity(in);
            }
            else if (buttonView == R.id.m_gst){
                Intent in = new Intent(this, GSTActivity.class);
                startActivity(in);
            }
            else if(buttonView == R.id.m_termsandcondition) {
                Intent in = new Intent(this, TermsConditionsActivity.class);
                startActivity(in);
            }


            else if(buttonView == R.id.m_appinfo) {
                String versionCode_Name = "";
                AlertDialog.Builder versionBuilder = new AlertDialog.Builder(this);
                versionBuilder.setTitle("App Version");
                try {
                    PackageInfo pInfo = Objects.requireNonNull(getPackageManager().getPackageInfo(getPackageName(), 0));
                    String verCode = pInfo.versionName;
                    versionCode_Name = "GilGal App Version: "+ verCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                versionBuilder.setMessage(versionCode_Name);
                versionBuilder.setPositiveButton("OK", (dialogInterface, i) -> {
                });
                AlertDialog alert = versionBuilder.create();
                alert.show();
                Button oKButton = alert.getButton(alert.BUTTON_POSITIVE);
                oKButton.setBackgroundColor(ContextCompat.getColor(this,R.color.special_text_color));
                oKButton.setTextColor(ContextCompat.getColor(this,R.color.white));
                oKButton.setOnClickListener(view -> alert.dismiss());
            }
            else if(buttonView == R.id.m_signout) {
                new AlertDialog.Builder(this).setTitle("Sign out")
                        .setMessage("Are you sure to Sign out?")
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {


                                        Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);


                                        // Perform Action & Dismiss dialog
                                        dialog.dismiss();
                                    }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
            //close navigation drawer
            homeSideDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation_tab);
      //  NavigationBarView.OnItemSelectedListener navigationItemSelectedListener;
        bottomNavigationView.setOnItemSelectedListener(navigationItemSelectedListener);




    }

    private final NavigationBarView.OnItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            final int buttonView = item.getItemId();

            if (buttonView == R.id.dashboard_tab){
               selectedFragment = new DashboardFragment();
            }else if(buttonView == R.id.report_tab){
                selectedFragment = new ReportFragment();
            }else {
                selectedFragment = new SubscriptionFragment();
            }

// Then, use the selectedFragment as needed
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

// Now you can use selectedFragment as needed


            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    
}