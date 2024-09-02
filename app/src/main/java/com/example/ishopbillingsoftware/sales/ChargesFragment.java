package com.example.ishopbillingsoftware.sales;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.ishopbillingsoftware.server.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChargesFragment extends Fragment {
    TextView totalamounttxt1,returnamounttxt1;
    ImageButton addnewbtn,editbtn;

    Spinner ocspinner,itemspinner;
    public  static EditText remarkedit,onvalueedit,plusedit,discountedit,amountedit;
    public static  String ocname;


    List<AccountList> accountLists ;

    SearchView searchView;
    List<ProductItem> productItems ;


    private List<ProductItem> originalProductList;




    private List<AccountList> originalAccountList;
    public ChargesFragment() {
        // Required empty public constructor
    }
    String[] partylist = null ;
    String[] itemlist = null ;




    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_charges, container, false);
        remarkedit = view.findViewById(R.id.remarkedit);
        onvalueedit = view.findViewById(R.id.onvalueedit);
        plusedit = view.findViewById(R.id.plusedit);
        discountedit = view.findViewById(R.id.discountedit);
        returnamounttxt1 = view.findViewById(R.id.returnamounttxt1);
        totalamounttxt1 = view.findViewById(R.id.totalamounttxt1);
        amountedit = view.findViewById(R.id.amountedit);


        ocspinner = view.findViewById(R.id.ocspinner);
        addnewbtn = view.findViewById(R.id.addnewbtn);

        editbtn = view.findViewById(R.id.editbtn);
        ocname = "ocname";




        addnewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddNewChargesActivity.class);
                getActivity().startActivity(intent);
            }
        });
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddNewChargesActivity.class);
                getActivity().startActivity(intent);
            }
        });
       /* ArrayAdapter ad1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,itemlist);

        // set simple layout resource file
        // for each item of spinner
        ad1.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        ocspinner.setAdapter(ad1);
        ocspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),partylist[position],Toast.LENGTH_LONG).show();
                ocname = itemlist[position].toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


        return view;
    }


}