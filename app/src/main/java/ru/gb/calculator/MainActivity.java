package ru.gb.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ru.gb.calculator.entites.InputSymbol;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "@@@";
    private CalculatorModel calculatorModel;
    private TextView screenCalculatorTextView;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button zeroButton;
    private Button separatorButton;
    private Button clearButton;
    private Button deleteButton;
    private Button minusButton;
    private Button plusButton;
    private Button divisionButton;
    private Button multiplicationButton;
    private Button squareRootButton;
    private Button percentButton;
    private Button equalButton;
    private Button openSecondActivityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParameters();
        initListeners();
        initModel();
        if (savedInstanceState != null && savedInstanceState.containsKey(SecondActivity.NUMBER_KEY)) {
            calculatorModel = (CalculatorModel) savedInstanceState.getParcelable(SecondActivity.NUMBER_KEY);
        }
        screenCalculatorTextView.setText(calculatorModel.getInput());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SecondActivity.NUMBER_KEY, calculatorModel);
    }

    private void initModel() {
        calculatorModel = new CalculatorModel();
    }

    private void initParameters() {
        oneButton = findViewById(R.id.digit_one_button);
        twoButton = findViewById(R.id.digit_two_button);
        threeButton = findViewById(R.id.digit_three_button);
        fourButton = findViewById(R.id.digit_four_button);
        fiveButton = findViewById(R.id.digit_five_button);
        sixButton = findViewById(R.id.digit_six_button);
        sevenButton = findViewById(R.id.digit_seven_button);
        eightButton = findViewById(R.id.digit_eight_button);
        nineButton = findViewById(R.id.digit_nine_button);
        zeroButton = findViewById(R.id.digit_zero_button);
        separatorButton = findViewById(R.id.separator_button);
        clearButton = findViewById(R.id.clear_button);
        deleteButton = findViewById(R.id.delete_button);
        minusButton = findViewById(R.id.operation_minus_button);
        plusButton = findViewById(R.id.operation_plus_button);
        divisionButton = findViewById(R.id.operation_division_button);
        multiplicationButton = findViewById(R.id.operation_multiplication_button);
        squareRootButton = findViewById(R.id.operation_square_root_button);
        percentButton = findViewById(R.id.operation_percent_button);
        equalButton = findViewById(R.id.equal_button);
        openSecondActivityButton = findViewById(R.id.open_second_activity);
        screenCalculatorTextView = findViewById(R.id.screen_calculator_text_view);
    }

    public void initListeners() {
        oneButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_1));
        twoButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_2));
        threeButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_3));
        fourButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_4));
        fiveButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_5));
        sixButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_6));
        sevenButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_7));
        eightButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_8));
        nineButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_9));
        zeroButton.setOnClickListener(view -> updateInput(InputSymbol.NUM_0));
        separatorButton.setOnClickListener(view -> updateInput(InputSymbol.DOT));
        clearButton.setOnClickListener(view -> updateInput(InputSymbol.CLEAR));
        deleteButton.setOnClickListener(view -> updateInput(InputSymbol.DEL_SYMBOL));
        minusButton.setOnClickListener(view -> updateInput(InputSymbol.OP_MINUS));
        plusButton.setOnClickListener(view -> updateInput(InputSymbol.OP_PLUS));
        divisionButton.setOnClickListener(view -> updateInput(InputSymbol.OP_DIV));
        multiplicationButton.setOnClickListener(view -> updateInput(InputSymbol.OP_MUL));
        squareRootButton.setOnClickListener(view -> updateInput(InputSymbol.OP_SQRT));
        percentButton.setOnClickListener(view -> updateInput(InputSymbol.OP_PERCENT));
        equalButton.setOnClickListener(view -> updateInput(InputSymbol.EQUAL));
        openSecondActivityButton.setOnClickListener(view -> {
            Intent intent = getIntentForLaunch(MainActivity.this);
            Log.d("@@@", "Intent create - OK! ");
            startActivity(intent);
        });

    }

    private void updateInput(InputSymbol input) {
        calculatorModel.onClickButton(input);
        screenCalculatorTextView.setText(calculatorModel.getInput());
    }

    private Intent getIntentForLaunch(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(SecondActivity.NUMBER_KEY,calculatorModel);
        return intent;
    }

    private String convertInputSymbolsToString(List<InputSymbol> inputSymbolList) {
        final StringBuilder sb = new StringBuilder();
        for (InputSymbol inputSymbol : inputSymbolList) {
            switch (inputSymbol) {
                case NUM_0:
                    sb.append("0");
                    break;
                case NUM_1:
                    sb.append("1");
                    break;
                case NUM_2:
                    sb.append("2");
                    break;
                case NUM_3:
                    sb.append("3");
                    break;
                case NUM_4:
                    sb.append("4");
                    break;
                case NUM_5:
                    sb.append("5");
                    break;
                case NUM_6:
                    sb.append("6");
                    break;
                case NUM_7:
                    sb.append("7");
                    break;
                case NUM_8:
                    sb.append("8");
                    break;
                case NUM_9:
                    sb.append("9");
                    break;
                case DOT:
                    sb.append(".");
                    break;
                case OP_MINUS:
                    sb.append("-");
                    break;
                default:
                    sb.append("@");
                    break;
            }
        }
        return sb.toString();
    }
}