package ru.gb.calculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class SecondActivity extends Activity {
    private static final String TAG = "@@@";
    public static final String NUMBER_KEY = "calculatorModel";
    //public static DataCalculator data;
    public static CalculatorModel calculatorModel;
    private TextView numberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        calculatorModel = (CalculatorModel) getIntent().getExtras().getParcelable(NUMBER_KEY);
        initParameters();

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SecondActivity.NUMBER_KEY,calculatorModel);
    }

    private void initParameters() {
        numberTextView = findViewById(R.id.number_text_view);
        numberTextView.setText(calculatorModel.getInput());
    }
}
