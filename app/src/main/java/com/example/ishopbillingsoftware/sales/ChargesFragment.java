package com.example.ishopbillingsoftware.sales;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ishopbillingsoftware.R;

public class ChargesFragment extends Fragment {
    // Initialize variable
    TextView textView;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_charges, container, false);


        // return view
        return view;
    }

}