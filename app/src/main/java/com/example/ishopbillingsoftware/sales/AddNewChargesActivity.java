package com.example.ishopbillingsoftware.sales;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ishopbillingsoftware.R;
import com.example.ishopbillingsoftware.RegisterActivity;
import com.example.ishopbillingsoftware.accounts.AccountActivity;

public class AddNewChargesActivity extends AppCompatActivity {
EditText chargesedit,printasedit,hsnedit;
Spinner accounthpspinner,taxspinner;
RadioGroup pmradioGroup,iacradioGroup;
RadioButton plusRB,minusRB,aaRB,onqtyRB,percentageRB,onbagsRB,onweightsRB;
CheckBox taxapplicable;

Button savebtn,deletebtn,viewbtn;
TextView edittxt,addnewtxt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_charges);

        onqtyRB =  findViewById(R.id.onqtyRB);
        percentageRB = findViewById(R.id.percentageRB);
        onbagsRB = findViewById(R.id.onbagsRB);
        onweightsRB = findViewById(R.id.onweightsRB);
        taxapplicable = findViewById(R.id.taxapplicable);

        savebtn = findViewById(R.id.savebtn);
        deletebtn = findViewById(R.id.deletebtn);
        viewbtn = findViewById(R.id.viewbtn);
        addnewtxt = findViewById(R.id.addnewtxt);
        edittxt = findViewById(R.id.edittxt);

        chargesedit =  findViewById(R.id.chargesedit);
        printasedit = findViewById(R.id.printasedit);
        hsnedit = findViewById(R.id.hsnedit);
        accounthpspinner = findViewById(R.id.accounthpspinner);
        taxspinner = findViewById(R.id.taxspinner);

        pmradioGroup =  findViewById(R.id.pmradioGroup);
        iacradioGroup = findViewById(R.id.iacradioGroup);
        plusRB = findViewById(R.id.plusRB);
        minusRB = findViewById(R.id.minusRB);
        aaRB = findViewById(R.id.aaRB);

        edittxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AccountActivity.class);

                startActivity(in);
            }
        });

        addnewtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(in);
            }
        });



    }
}