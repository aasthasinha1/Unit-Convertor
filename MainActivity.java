package com.example.unitc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText valueInput;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Button convertButton;
    private TextView resultOutput;

    // Units and their conversion factors relative to the base unit, which is meter.
    private String[] unitNames = {"Meter", "Kilometer", "Centimeter", "Millimeter", "Mile", "Yard", "Foot", "Inch"};
    private double[] unitValues = {1.0, 1000.0, 0.01, 0.001, 1609.34, 0.9144, 0.3048, 0.0254};

    private DecimalFormat decimalFormat = new DecimalFormat("#.##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueInput = findViewById(R.id.editTextValue);
        fromSpinner = findViewById(R.id.spinnerFrom);
        toSpinner = findViewById(R.id.spinnerTo);
        convertButton = findViewById(R.id.buttonConvert);
        resultOutput = findViewById(R.id.textViewResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        fromSpinner.setSelection(0);
        toSpinner.setSelection(1);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double inputValue = Double.parseDouble(valueInput.getText().toString());
                double fromValue = unitValues[fromSpinner.getSelectedItemPosition()];
                double toValue = unitValues[toSpinner.getSelectedItemPosition()];

                double result = inputValue * fromValue / toValue;

                resultOutput.setText(decimalFormat.format(result));
            }
        });
    }
}
