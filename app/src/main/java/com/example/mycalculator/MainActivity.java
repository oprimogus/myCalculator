package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText firstNumberEditTextSum, secondNumberEditTextSum;
    private Button calculateButton;
    private TextView resultTextViewSum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Minha Calculadora");

        firstNumberEditTextSum = findViewById(R.id.editTextNumberDecimal_firstNumberSum);
        secondNumberEditTextSum = findViewById(R.id.editTextNumberDecimal4_SecondNumberSum);
        calculateButton = findViewById(R.id.button_calculate);
        resultTextViewSum = findViewById(R.id.textView5_SumResult);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(firstNumberEditTextSum.getText().toString());
                double num2 = Double.parseDouble(secondNumberEditTextSum.getText().toString());
                double sum = num1 + num2;
                resultTextViewSum.setText(String.valueOf(sum));
            }
        });
    }
}