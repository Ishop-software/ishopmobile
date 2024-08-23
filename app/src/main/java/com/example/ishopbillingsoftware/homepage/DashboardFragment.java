package com.example.ishopbillingsoftware.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishopbillingsoftware.R;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {

    RecyclerView homeitem;
    private ViewGroup placeholder;
    private View view;
    HomeItemsAdapter homeItemsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_dash, container, false);

         homeitem = view.findViewById(R.id.homeitem);

        // Here, we have created new array list and added data to it
        ArrayList<HomeItem> courseModelArrayList = new ArrayList<HomeItem>();
        courseModelArrayList.add(new HomeItem("Accounts"));
        courseModelArrayList.add(new HomeItem("Items"));
        courseModelArrayList.add(new HomeItem("Outstandings"));
        courseModelArrayList.add(new HomeItem("Sales"));
        courseModelArrayList.add(new HomeItem("Purchases"));
        courseModelArrayList.add(new HomeItem("POS"));


        // we are initializing our adapter class and passing our arraylist to it.
       homeItemsAdapter = new HomeItemsAdapter(getContext(), courseModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
       // LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
       GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        homeitem.setLayoutManager(layoutManager);
        homeitem.setAdapter(homeItemsAdapter);
        return view;
    }
}