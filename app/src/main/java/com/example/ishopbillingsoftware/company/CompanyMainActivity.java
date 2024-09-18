package com.example.ishopbillingsoftware.company;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ishopbillingsoftware.R;

public class CompanyMainActivity extends AppCompatActivity {

    ImageButton leftarrow,rightarrow;
    private TextView textView;

    private String[] texts = {"Mar 2022 - Apr 2023","Mar 2023 - Apr 2024", "Mar 2024 - Apr 2025"}; // Array of texts
    private int currentIndex = 0; // Index to keep track of current text

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_main);
        textView = findViewById(R.id.calendertxt);
        leftarrow = findViewById(R.id.leftarrow);
        rightarrow = findViewById(R.id.rightarrow);
        rightarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the textView with the next text
                currentIndex = (currentIndex + 1) % texts.length; // Cycle through the array
                textView.setText(texts[currentIndex]);
            }
        });

        leftarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the textView with the next text
                currentIndex = (currentIndex - 1) % texts.length; // Cycle through the array
                textView.setText(texts[currentIndex]);
            }
        });

    }
}