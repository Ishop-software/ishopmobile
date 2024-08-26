package com.example.ishopbillingsoftware.sales;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.accounts.APIResponseData;
import com.example.ishopbillingsoftware.accounts.AccountActivity;
import com.example.ishopbillingsoftware.accounts.AccountList;
import com.example.ishopbillingsoftware.accounts.AccountViewListAdapter;
import com.example.ishopbillingsoftware.accounts.AccounviewlistActivity;
import com.example.ishopbillingsoftware.items.APIResponseProductItem;
import com.example.ishopbillingsoftware.items.ProductItem;
import com.example.ishopbillingsoftware.items.ViewListActivity;
import com.example.ishopbillingsoftware.items.ViewListAdapter;
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

Spinner partyspinner,itemspinner;
EditText billnoedit,duedateedit,qtyedit,freeedit,rateedit,peredit,
        baedit,daedit,taedit,discedit,netvalueedit,receivedamtedit;

RadioGroup radioGroup;
RadioButton cash,credit;
Button addnewcardbtn;
    List<AccountList> accountLists ;

    SearchView searchView;
    List<ProductItem> productItems ;


    private List<ProductItem> originalProductList;

    ImageButton filterbtn;


    private List<AccountList> originalAccountList;
    public SaleFragment() {
        // Required empty public constructor
    }
    String[] partylist = null ;
    String[] itemlist = null ;


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
        baedit = view.findViewById(R.id.baedit);
        daedit = view.findViewById(R.id.daedit);
        taedit = view.findViewById(R.id.taedit);
        discedit = view.findViewById(R.id.discedit);
        netvalueedit = view.findViewById(R.id.netvalueedit);
        addnew = view.findViewById(R.id.addnewbtn);
        printbtn = view.findViewById(R.id.printbtn);
        calender = view.findViewById(R.id.calender);
        edit = view.findViewById(R.id.edit);
        partyspinner = view.findViewById(R.id.partyspinner);
        itemspinner = view.findViewById(R.id.itemspinner);

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
                                    // You could also use productItemList.get(i).getName() if you want
                                    // Create the instance of ArrayAdapter
                                    // having the list of courses

                                }

                                assert getActivity() != null;
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

    }

}
