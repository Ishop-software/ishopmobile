package com.example.ishopbillingsoftware;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
public class CashReceiptActivity extends AppCompatActivity {
    private EditText searchEditText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> itemList;
    private List<String> filteredList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_receipt);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.btn_color));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color));

        searchEditText = findViewById(R.id.searchEditText);
        listView = findViewById(R.id.listView);

        itemList = new ArrayList<>();
        // Add sample data to the list
        itemList.add("Apple");
        itemList.add("ABanana");
        itemList.add("cApple");
        itemList.add("DApple");
        itemList.add("EApple");
        itemList.add("Apple");
        itemList.add("Banana");
        itemList.add("Cherry");
        itemList.add("Date");
        itemList.add("Fig");
        itemList.add("Grapes");
        itemList.add("Kiwi");

        filteredList = new ArrayList<>(itemList);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList);
        listView.setAdapter(adapter);

        // Set up the TextWatcher to listen for text changes in the EditText
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(itemList);
        } else {
            for (String item : itemList) {
                if (item.toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged(); // Notify the adapter of data changes
    }
}

