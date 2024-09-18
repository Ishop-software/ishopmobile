package com.example.ishopbillingsoftware.accounts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



import com.example.ishopbillingsoftware.R;


import com.example.ishopbillingsoftware.homepage.HomePageActivity;
import com.example.ishopbillingsoftware.items.APIResponseUpdate;
import com.example.ishopbillingsoftware.server.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
Button savebtn,deletebtn,viewbtn,addnewbtn,gst;

ImageButton settingbtn,settingbtn1;
EditText nameedit,taxnoedit,addresedit,pincodeedit,codeedit,mobileedit,printassedit,
        phoneedit,emailedit,cpdit,pancardnoedit,openingbaledit;
Spinner groupspinner,undergroupspinner,statespinner,
        drcrspinner,cityspinner;
LinearLayout gsteditlayout,gstbtnlayout;

String  printas,group,undergroup,state,groupname,undergroupname,primarygroup,
        drcr,city,token;

   List<String> undergrouplist ;

    List<String> grouplist ;

    List<AccountGroupList> accountGroupLists ;

    String[] drcrs = { "Dr", " Cr"
    };

    String drcrvalue,groupvalue,undergroupvalue,cityvalue,statevalue;



    private ArrayList<String> stateList = new ArrayList<>();
    private ArrayList<String> cityList = new ArrayList<>();
    private JSONObject indiaData;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);


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
        printassedit = findViewById(R.id.printassedit);
        cityspinner = findViewById(R.id.cityspinner);
        groupspinner = findViewById(R.id.groupspinner);
        undergroupspinner = findViewById(R.id.undergroupspinner);
        statespinner = findViewById(R.id.statespinner);
        drcrspinner = findViewById(R.id.drcrspinner);//
        ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, drcrs);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        drcrspinner.setAdapter(ad);

        drcrspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),drcrs[position],Toast.LENGTH_LONG).show();
                drcrvalue = drcrs[position];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gst = findViewById(R.id.gst);
        gstbtnlayout = findViewById(R.id.gstbtnlayout);
        settingbtn = findViewById(R.id.settingbtn);
        settingbtn1 = findViewById(R.id.settingbutton);
        addnewbtn = findViewById(R.id.addnewbtn);
        gsteditlayout = findViewById(R.id.gsteditlayout);


        Intent in = getIntent();
        token = in.getStringExtra("token");
        accountGroupLists = new ArrayList<>();

        sendDialogDataToActivity();
        gsteditlayout.setVisibility(View.GONE);

        gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsteditlayout.setVisibility(View.VISIBLE);
                gstbtnlayout.setVisibility(View.GONE);
            }
        });



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
                    EditText editText2 = customLayout.findViewById(R.id.primarygroupedit);
                   groupname = editText.getText().toString();
                    undergroupname = editText1.getText().toString();
                    primarygroup = editText2.getText().toString();
                    getgroup(groupname,undergroupname,primarygroup);

                });
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        // Load JSON data
        try {
            //indiaData = new JSONObject(Objects.requireNonNull(loadJSONFromAsset()));
            // Use this
            indiaData = new JSONObject(loadJSONFromRaw());
            JSONArray statesArray = indiaData.getJSONObject("India").getJSONArray("states");

            // Populate state spinner
            for (int i = 0; i < statesArray.length(); i++) {
                JSONObject stateObject = statesArray.getJSONObject(i);
                stateList.add(stateObject.getString("name"));
            }

            ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stateList);
            stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            statespinner.setAdapter(stateAdapter);

            // Handle state selection
            statespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                    statevalue = stateList.get(position);
                    // Get selected state and load corresponding cities
                    try {
                        loadCitiesForState(position);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String loadJSONFromRaw() {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.states_and_cities);  // Open the JSON file
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // Load cities for selected state
    private void loadCitiesForState(int statePosition) throws JSONException {
        cityList.clear();
        JSONArray statesArray = indiaData.getJSONObject("India").getJSONArray("states");
        JSONArray citiesArray = statesArray.getJSONObject(statePosition).getJSONArray("cities");

        for (int i = 0; i < citiesArray.length(); i++) {
            cityList.add(citiesArray.getString(i));
        }

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityList);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityspinner.setAdapter(cityAdapter);

        cityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
              cityvalue  = cityList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    // Function to load JSON from the assets folder
    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open(String.valueOf(R.raw.states_and_cities));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }




    private void getgroup(String groupname, String undergroupname, String primarygroup) {
        try{
            HashMap<String, String> add = new HashMap<String, String>();
            add.put( "group",groupname);
            add.put("underGroup",undergroupname);
            add.put("primaryGroup",primarygroup);



            System.out.print("itemadd"+add);
            Call<APIResponseGroup> call = ApiClient.getUserService().addgroup(add);

            call.enqueue(new Callback<APIResponseGroup>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseGroup> call, @NonNull Response<APIResponseGroup> response) {

                    System.out.print("itemaccount"+response.toString());


                    if(response.isSuccessful()){
                        APIResponseGroup apiResponse = response.body();
                        assert apiResponse != null;
                        String success = apiResponse.getSuccess();
                        String message = apiResponse.getMessage();
                        String groupid = apiResponse.getGroupAccountId();
                        System.out.print(groupid);
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseGroup> call, @NonNull Throwable t) {
                    Log.e("getUTL failure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getApplicationContext(), "Server Error Occured!!! Please Retry after some time", Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e){
            System.out.print("error"+e.toString());
        }

    }

    private void sendDialogDataToActivity() {
        try{

            Call<APIResponseGP> call = ApiClient.getUserService().grouplist();

            call.enqueue(new Callback<APIResponseGP>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseGP> call, @NonNull Response<APIResponseGP> response) {

                    System.out.print("itemaccount"+response.toString());


                    if (response.isSuccessful()) {
                        System.out.println(response);
                        APIResponseGP apiResponseGP = response.body();
                        accountGroupLists = apiResponseGP.getMessage();

                        if (accountGroupLists!=null){

                            grouplist = new ArrayList<>();
                            undergrouplist = new ArrayList<>();


                            for(int i=0;i<accountGroupLists.size();i++){

                                System.out.print("group"+accountGroupLists.get(i).getGroup()+""+accountGroupLists.get(i).getUnderGroup());
                                grouplist.add(accountGroupLists.get(i).getGroup());
                              undergrouplist.add(accountGroupLists.get(i).getUnderGroup());

                            }
                            ArrayAdapter ad1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,grouplist);

                            // set simple layout resource file
                            // for each item of spinner
                            ad1.setDropDownViewResource(
                                    android.R.layout
                                            .simple_spinner_dropdown_item);

                            // Set the ArrayAdapter (ad) data on the
                            // Spinner which binds data to spinner
                            groupspinner.setAdapter(ad1);

                            groupspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                                    groupname  = grouplist.get(position);
                                    groupvalue = groupname;

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // Do nothing
                                }
                            });

                            ArrayAdapter ad = new ArrayAdapter(getApplicationContext(),
                                    android.R.layout.simple_spinner_item,undergrouplist);

                            // set simple layout resource file
                            // for each item of spinner
                            ad.setDropDownViewResource(
                                    android.R.layout
                                            .simple_spinner_dropdown_item);

                            // Set the ArrayAdapter (ad) data on the
                            // Spinner which binds data to spinner
                            undergroupspinner.setAdapter(ad);


                        }


                    } else {
                        System.out.println("Response is null");
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseGP> call, @NonNull Throwable t) {
                    Log.e("getUTL failure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getApplicationContext(), "Server Error Occured!!! Please Retry after some time", Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e){
            System.out.print("error"+e.toString());
        }

    }

    private void getsave() {

        try{
            HashMap<String, String> add = new HashMap<String, String>();
            add.put( "name",nameedit.getText().toString());
            add.put("printAs",printassedit.getText().toString());
            add.put("group",groupvalue);
            add.put("openingBal",openingbaledit.getText().toString());
            add.put("DR_CR","CR");
            add.put("taxNo",taxnoedit.getText().toString());
            add.put("Address1",addresedit.getText().toString());
            add.put( "city",cityvalue);
            add.put("state",statevalue);
            add.put("stateCode",codeedit.getText().toString());
            add.put("mobileNo",mobileedit.getText().toString());
            add.put("email", emailedit.getText().toString());
            add.put( "contactPerson", cpdit.getText().toString());
            add.put("panCardNo",pancardnoedit.getText().toString());


            System.out.print("itemadd1"+add);
            System.out.print("itemadd11"+add+HomePageActivity.token);
            Call<APIResponseUpdate> call = ApiClient.getUserService().createaccount(add, HomePageActivity.token);

            call.enqueue(new Callback<APIResponseUpdate>() {
                @SuppressLint({"SetTextI18n", "SuspiciousIndentation"})
                @Override
                public void onResponse(@NonNull Call<APIResponseUpdate> call, @NonNull Response<APIResponseUpdate> response) {

                    System.out.print("itemaccount"+response.toString());


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