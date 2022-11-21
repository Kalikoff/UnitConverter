// Калмыков 303
package com.example.unitconverter.kalmykov303;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private EditText editTextValue;
    private TextView textViewResult;

    private ArrayAdapter<Unit> arrayAdapter;
    private ArrayList<Unit> arrayListLength = new ArrayList<>();
    private ArrayList<Unit> arrayListSquare = new ArrayList<>();
    private ArrayList<Unit> arrayListWeight = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrom = findViewById(R.id.SpinnerFrom);
        spinnerTo = findViewById(R.id.SpinnerTo);
        editTextValue = findViewById(R.id.EditTextValue);
        textViewResult = findViewById(R.id.TextViewResult);

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        arrayListLength.add(new Unit("mm", 0.001));
        arrayListLength.add(new Unit("cm", 0.01));
        arrayListLength.add(new Unit("m", 0.1));
        arrayListLength.add(new Unit("km", 1.0));

        arrayListSquare.add(new Unit("mm2", 0.000001));
        arrayListSquare.add(new Unit("cm2", 0.0001));
        arrayListSquare.add(new Unit("m2", 1.0));
        arrayListSquare.add(new Unit("km2", 1000000.0));

        arrayListWeight.add(new Unit("mg", 0.001));
        arrayListWeight.add(new Unit("g", 1.0));
        arrayListWeight.add(new Unit("kg", 1000.0));
        arrayListWeight.add(new Unit("t", 1000000.0));

        arrayAdapter.addAll(arrayListLength);
        spinnerFrom.setAdapter(arrayAdapter);
        spinnerTo.setAdapter(arrayAdapter);

        RadioButton radioButtonLength = findViewById(R.id.RadioButtonLength);
        radioButtonLength.setOnClickListener(radioButtonClickListener);

        RadioButton radioButtonSquare = findViewById(R.id.RadioButtonSquare);
        radioButtonSquare.setOnClickListener(radioButtonClickListener);

        RadioButton radioButtonWeight = findViewById(R.id.RadioButtonWeight);
        radioButtonWeight.setOnClickListener(radioButtonClickListener);
    }


    // Обработчик radioButton-ов
    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RadioButton radioButton = (RadioButton)view;

            arrayAdapter.clear();

            switch (radioButton.getId()) {
                case R.id.RadioButtonLength:
                    arrayAdapter.addAll(arrayListLength);
                    break;
                case R.id.RadioButtonSquare:
                    arrayAdapter.addAll(arrayListSquare);
                    break;
                case R.id.RadioButtonWeight:
                    arrayAdapter.addAll(arrayListWeight);
                    break;
            }
        }
    };


    // Нажатие по кнопке Перевести
    public void buttonConvert_onClick(View v) {
        if (IsEditTextHaveError()) {
            return;
        }

        textViewResult.setText(CalculateResult().toString());
    }


    // Проверка на ошибки при вводе
    private boolean IsEditTextHaveError() {
        if (editTextValue.getText().toString().isEmpty()) {
            editTextValue.setError("Пустое поле!");
            return true;
        }

        try {
            Double.parseDouble(editTextValue.getText().toString());
        }
        catch (Exception e) {
            editTextValue.setError("Введите число!");
            return true;
        }

        return false;
    }


    // Вычисление результата
    private Double CalculateResult() {
        return Double.parseDouble(editTextValue.getText().toString()) *
                (arrayAdapter.getItem(spinnerFrom.getSelectedItemPosition()).getCoeff() / arrayAdapter.getItem(spinnerTo.getSelectedItemPosition()).getCoeff());
    }
}
// Калмыков 303