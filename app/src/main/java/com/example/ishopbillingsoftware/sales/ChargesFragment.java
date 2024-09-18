package com.example.ishopbillingsoftware.sales;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.accounts.Data;
import com.example.ishopbillingsoftware.items.ProductItem;

import java.util.List;

public class ChargesFragment extends Fragment {
    TextView totalamounttxt1,returnamounttxt1;
    ImageButton addnewbtn,editbtn;

    Spinner ocspinner,itemspinner;
    public  static EditText remarkedit,onvalueedit,plusedit,discountedit,amountedit;
    public static  String ocname;


    List<Data> accountLists ;

    SearchView searchView;
    List<ProductItem> productItems ;


    private List<ProductItem> originalProductList;




    private List<Data> originalAccountList;
    public ChargesFragment() {
        // Required empty public constructor
    }
    String[] partylist = null ;
    String[] itemlist = null ;


    String[] otherchargeslist = { "Additional Tax(GST)", " Discount","Freight & Forwarding Charges",
            "Host","Labour","Market Fee","Rounded off(-)","Rounded off(+)","SGST","CGST"
    };

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

        ArrayAdapter ad = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, otherchargeslist);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        ocspinner.setAdapter(ad);



        addnewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddNewChargesActivity.class);
                assert getActivity() != null;
                getActivity().startActivity(intent);
            }
        });
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddNewChargesActivity.class);
                assert getActivity() != null;
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