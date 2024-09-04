package com.example.ishopbillingsoftware.sales;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.accounts.APIResponseData;
import com.example.ishopbillingsoftware.accounts.AccountActivity;
import com.example.ishopbillingsoftware.accounts.AccountList;
import com.example.ishopbillingsoftware.items.APIResponseProductItem;
import com.example.ishopbillingsoftware.items.ProductItem;
import com.example.ishopbillingsoftware.printdesign.Salesprint1Activity;
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleFragment extends Fragment {
    TextView totalamounttxt1,returnamounttxt1;
    ImageButton addnew,printbtn,edit,calender;
    Spinner partyspinner,itemspinner,taxspinner;
    public  static EditText billnoedit,duedateedit,qtyedit,freeedit,rateedit,peredit,discperedit,
        baedit,discamountedit,taxamountedit,discedit,netvalueedit,receivedamtedit;
    public static  String partyname,itemname,taxname;
    public static  double rate;
    public static int taxper;
    RadioGroup radioGroup;
    RadioButton cash,credit;
    Button addnewcardbtn;
    List<AccountList> accountLists ;
    SearchView searchView;
    List<ProductItem> productItems ;
    private List<ProductItem> originalProductList;

    private List<AccountList> originalAccountList;
    public SaleFragment() {
        // Required empty public constructor
    }
    String[] partylist = null ;
    String[] itemlist = null ;
    Double basicamountvalue,taxamount;
    String[] tax = { "Tax Inclusive", "Tax Exclusive",
            "Tax Free",
    };
    String discountpercent;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);

        radioGroup = view.findViewById(R.id.radioGroup);
        cash = view.findViewById(R.id.cash);
        credit = view.findViewById(R.id.credit);
        addnewcardbtn = view.findViewById(R.id.addnewcardbtn);
        returnamounttxt1 = view.findViewById(R.id.returnamounttxt1);
        totalamounttxt1 = view.findViewById(R.id.totalamounttxt1);
        receivedamtedit = view.findViewById(R.id.receivedamtedit);
        billnoedit = view.findViewById(R.id.billnoedit);
        duedateedit = view.findViewById(R.id.duedateedit);
        qtyedit = view.findViewById(R.id.qtyedit);
        freeedit = view.findViewById(R.id.freeedit);
        rateedit = view.findViewById(R.id.rateedit);
        peredit = view.findViewById(R.id.peredit);
        qtyedit.setImeActionLabel("Custom text", KeyEvent.KEYCODE_ENTER);
        baedit = view.findViewById(R.id.baedit);
        taxamountedit = view.findViewById(R.id.taxamountedit);
        discperedit=view.findViewById(R.id.discperedit);

        discamountedit = view.findViewById(R.id.discamountedit);
        netvalueedit = view.findViewById(R.id.netvalueedit);
        addnew = view.findViewById(R.id.addnewbtn);
        printbtn = view.findViewById(R.id.printbtn);
        calender = view.findViewById(R.id.calender);
        edit = view.findViewById(R.id.edit);
        partyspinner = view.findViewById(R.id.partyspinner);
        itemspinner = view.findViewById(R.id.itemspinner);
        printbtn = view.findViewById(R.id.printbtn);

        taxspinner = view.findViewById(R.id.taxspinner);
        printbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Salesprint1Activity.class);
                startActivity(in);
            }
        });
        ArrayAdapter ad = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, tax);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        taxspinner.setAdapter(ad);

        taxspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),tax[position],Toast.LENGTH_LONG).show();
                taxname = tax[position];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getaccountdetails(SalesActivity.token);
        getItemDetails(SalesActivity.token);


        printbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("printnl");
            }
        });

        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                getActivity().startActivity(intent);
            }
        });

        qtyedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    qtyedit.requestFocus();
                    String quu = qtyedit.getText().toString();
                    if (quu == null || quu.trim().isEmpty()) {
                        // Handle the case where the string is null or empty
                        System.out.println("The value is null or empty");
                    } else {
                        setvalue(quu);
                    }

                }
            }
        });

        discperedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    discperedit.requestFocus();
                    discountpercent = discperedit.getText().toString();
                    if (discountpercent == null || discountpercent.trim().isEmpty()) {
                        // Handle the case where the string is null or empty
                        System.out.println("The value is null or empty");
                    } else {


                        double discpereditt = Double.parseDouble(discountpercent);
                        Double discamount = (basicamountvalue) * (discpereditt/100);
                        discamountedit.setText(String.valueOf(discamount));
                        Double netvalue = basicamountvalue - discamount+taxamount;
                        netvalueedit.setText(String.valueOf(netvalue));
                    }

                }
            }
        });





        ;


        return view;
    }

    private void getItemDetails(String token) {
        try {
            Call<APIResponseProductItem> call = ApiClient.getUserService().getAllproductIitem(token);

            call.enqueue(new Callback<APIResponseProductItem>() {

                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onResponse(@NonNull Call<APIResponseProductItem> call, @NonNull Response<APIResponseProductItem> response) {
                    Log.e("success", String.valueOf(response));

                    //assert response.body() != null;

                    if (response.isSuccessful()){

                        APIResponseProductItem  apiResponseProductItem = response.body();
                        if (apiResponseProductItem !=null){
                            productItems = apiResponseProductItem.getMessage();
                            // Keep a copy of the original list
                            originalProductList = new ArrayList<>(productItems);
                            if (productItems!=null){
                                System.out.print("productitem"+productItems.size());
                                System.out.print("productitem1"+productItems.toString());
                                itemlist = new String[productItems.size()];
                                for (int i = 0; i < productItems.size(); i++) {
                                    itemlist[i] = productItems.get(i).getItemName().toString();
                                    rate = productItems.get(i).getSalePrice();
                                   // System.out.print("ppp"+productItems.get(i).getSalePrice());

                                    taxper = productItems.get(i).getTaxSlab();
                                    System.out.print("ppp"+taxper);



                                    // You could also use productItemList.get(i).getName() if you want
                                    // Create the instance of ArrayAdapter
                                    // having the list of courses

                                }
                                ArrayAdapter ad1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,itemlist);

                                // set simple layout resource file
                                // for each item of spinner
                                ad1.setDropDownViewResource(
                                        android.R.layout
                                                .simple_spinner_dropdown_item);

                                // Set the ArrayAdapter (ad) data on the
                                // Spinner which binds data to spinner
                                itemspinner.setAdapter(ad1);




                            }
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseProductItem> call, @NonNull Throwable t) {

                    Log.e("getULDfailure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getActivity(), "Server Down!! Please Retry after some time", Toast.LENGTH_LONG).show();

                }
            });

        }catch (Exception e){
            System.out.print("eee"+e.toString());
        }
        itemspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),itemlist [position],Toast.LENGTH_LONG).show();
                itemname = itemlist[position].toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setvalue(String quantity) {

        if (taxname.equals("Tax Inclusive")){
            double quantity1 = Double.parseDouble(quantity);
            double rate1 = quantity1 * rate;
            taxamount = (rate1*taxper)/100;
            double rr = rate - taxamount;
            rateedit.setText(String.valueOf(rr));
            baedit.setText(String.valueOf(rr));
            taxamountedit.setText(String.valueOf(taxamount));
            basicamountvalue = rr;
            discountpercent = discperedit.getText().toString();

            if (discountpercent.trim().isEmpty()) {
                // Handle the case where the string is null or empty
                System.out.println("The value is null or empty");
            } else {
                double discpereditt = Double.parseDouble(discountpercent);
                Double discamount = (basicamountvalue) * (discpereditt/100);
                discamountedit.setText(String.valueOf(discamount));
                Double netvalue = basicamountvalue - discamount+taxamount;
                netvalueedit.setText(String.valueOf(netvalue));
            }



        }else if (taxname.equals("Tax Exclusive")){
            rateedit.setText(String.valueOf(rate));
            double quantity1 = Double.parseDouble(quantity);
            basicamountvalue = quantity1 * rate;
            baedit.setText(String.valueOf(basicamountvalue));
            taxamount = (basicamountvalue*taxper)/100;
            taxamountedit.setText(String.valueOf(taxamount));
            discountpercent = discperedit.getText().toString();
            if (discountpercent.trim().isEmpty()) {
                // Handle the case where the string is null or empty
                System.out.println("The value is null or empty");
            } else {
                double discpereditt = Double.parseDouble(discountpercent);
                Double discamount = (basicamountvalue) * (discpereditt/100);
                discamountedit.setText(String.valueOf(discamount));
                Double netvalue = basicamountvalue - discamount+taxamount;
                netvalueedit.setText(String.valueOf(netvalue));
            }



        }else {
            rateedit.setText(String.valueOf(rate));
            double quantity1 = Double.parseDouble(quantity);
            basicamountvalue = quantity1 * rate;
            baedit.setText(String.valueOf(basicamountvalue));
            taxamount = 0.0;
            taxamountedit.setText(String.valueOf(taxamount));

            discountpercent = discperedit.getText().toString();
            if (discountpercent.trim().isEmpty()) {
                // Handle the case where the string is null or empty
                System.out.println("The value is null or empty");
            } else {
                double discpereditt = Double.parseDouble(discountpercent);
                Double discamount = (basicamountvalue) * (discpereditt/100);
                discamountedit.setText(String.valueOf(discamount));
                Double netvalue = basicamountvalue - discamount+taxamount;
                netvalueedit.setText(String.valueOf(netvalue));
            }


        }


    }

    private void getaccountdetails(String token) {
        try {

            Call<APIResponseData> call = ApiClient.getUserService().getAllAccountDetails(token);

            call.enqueue(new Callback<APIResponseData>() {

                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onResponse(@NonNull Call<APIResponseData> call, @NonNull Response<APIResponseData> response) {
                    Log.e("success", String.valueOf(response));

                    //assert response.body() != null;

                    if (response.isSuccessful()){

                        APIResponseData  apiResponseProductItem = response.body();
                        if (apiResponseProductItem !=null){
                            accountLists = apiResponseProductItem.getData();
                            // Keep a copy of the original list
                            originalAccountList = new ArrayList<>(accountLists);
                            if (accountLists!=null){
                                System.out.print("productitem"+accountLists.size());
                                System.out.print("productitem1"+accountLists.toString());
                                //partylist = new String[]{};

                                partylist = new String[accountLists.size()];
                                for (int i = 0; i < accountLists.size(); i++) {
                                    partylist[i] = accountLists.get(i).getName().toString();
                                    // You could also use productItemList.get(i).getName() if you want
                                    // Create the instance of ArrayAdapter
                                    // having the list of courses

                                }

                                assert getActivity() != null;
                                ArrayAdapter ad1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,partylist);

                                // set simple layout resource file
                                // for each item of spinner
                                ad1.setDropDownViewResource(
                                        android.R.layout
                                                .simple_spinner_dropdown_item);

                                // Set the ArrayAdapter (ad) data on the
                                // Spinner which binds data to spinner
                                partyspinner.setAdapter(ad1);

                            }
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<APIResponseData> call, @NonNull Throwable t) {

                    Log.e("getULDfailure", Objects.requireNonNull(t.getLocalizedMessage()));
                    Toast.makeText(getActivity(), "Server Down!! Please Retry after some time", Toast.LENGTH_LONG).show();

                }
            });

        }catch (Exception e){
            System.out.print("eee"+e.toString());
        }

        partyspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),partylist[position],Toast.LENGTH_LONG).show();
                partyname = partylist[position].toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
