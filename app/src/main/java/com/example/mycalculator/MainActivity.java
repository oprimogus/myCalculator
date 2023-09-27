package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private double firstValue = 0.0, secondValue = 0.0;
    private String currentOperation = "", currentInput = "0";
    private GridView gridView;
    private CalculatorAdapter adapter;

    private void updateDisplay() {
        result.setText(currentInput);
    }

    private void updateDisplay(String value) {
        result.setText(value);
    }

    private void clearAll() {
        firstValue = 0.0;
        secondValue = 0.0;
        currentOperation = "";
        currentInput = "0";
        updateDisplay();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String appName = getResources().getString(R.string.app_name);
        getSupportActionBar().setTitle(appName);

        result = findViewById(R.id.textView5_SumResult);

        String[] buttons = {
                "C", "±", "%", "/",
                "1", "2", "3", "*",
                "4", "5", "6", "-",
                "7", "8", "9", "+",
                "0", ",", "x^y", "="
        };

        gridView = findViewById(R.id.gridView);
        adapter = new CalculatorAdapter(this, buttons);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            String buttonText = buttons[position];
            if ("0123456789".contains(buttonText)) {
                if (currentInput.equals("0")) {
                    currentInput = buttonText;
                } else {
                    currentInput += buttonText;
                }
                updateDisplay();
            } else if ("+-*/x^y".contains(buttonText)) {
                firstValue = Double.parseDouble(currentInput);
                currentOperation = buttonText;
                currentInput = "0";
                updateDisplay();
            } else if (",".contains(buttonText)) {
                if (!currentInput.contains(",")) {
                    currentInput += ".";
                }
                updateDisplay();
            } else if (buttonText.equals("=")) {
                secondValue = Double.parseDouble(currentInput);

                // TO-DO: Implementar funcionalidade + ou -, % e de x^y ou colocar raiz quadrada no lugar,

                switch (currentOperation) {
                    case "+":
                        currentInput = String.valueOf(firstValue + secondValue);
                        break;
                    case "-":
                        currentInput = String.valueOf(firstValue - secondValue);
                        break;
                    case "*":
                        currentInput = String.valueOf(firstValue * secondValue);
                        break;
                    case "x^y":
                        currentInput = String.valueOf(Math.pow(firstValue, secondValue));
                        break;
                    case "/":
                        if (secondValue != 0) {
                            currentInput = String.valueOf(firstValue / secondValue);
                        } else {
                            currentInput = "Erro";
                        }
                        break;
                }
                try {
                    double resultNumber = Double.parseDouble(currentInput);
                    if (resultNumber == (int) resultNumber) {
                        updateDisplay(String.valueOf((int) resultNumber));
                    } else {
                        updateDisplay();
                    }
                } catch (RuntimeException error) {
                    updateDisplay();
                }

            } else if (buttonText.equals("C")) {
                clearAll();
                updateDisplay();
            }
            else if (buttonText.equals("±")) {
                currentInput = String.valueOf(Double.parseDouble(currentInput) * -1);
                updateDisplay();
            }
            else if (buttonText.equals("%")) {
                currentInput = String.valueOf(Double.parseDouble(currentInput) / 100);
                updateDisplay();
            }

        });
    }
}