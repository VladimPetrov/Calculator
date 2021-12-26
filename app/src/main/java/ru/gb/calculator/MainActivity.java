package ru.gb.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "@@@";
    private TextView screenCalculatorTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParameters();
        if (savedInstanceState != null && savedInstanceState.containsKey(SecondActivity.NUMBER_KEY)) {
            SecondActivity.data = (DataCalculator) savedInstanceState.getParcelable(SecondActivity.NUMBER_KEY);
        }
        Log.d(TAG,"MAIN onCreate   NUMBER_KEY="+SecondActivity.NUMBER_KEY+", data.number="+SecondActivity.data.getNumberOnScreen());
        showNumberOnTextView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        SecondActivity.data.setNumberOnScreen(screenCalculatorTextView.getText().toString());
        Log.d(TAG,"MAIN onSave   NUMBER_KEY="+SecondActivity.NUMBER_KEY+", data.number="+SecondActivity.data.getNumberOnScreen());
        outState.putParcelable(SecondActivity.NUMBER_KEY,SecondActivity.data);
    }


    private void showNumberOnTextView() {
        screenCalculatorTextView.setText(SecondActivity.data.getNumberOnScreen());
    }


    private void initParameters() {

        final Button oneButton = findViewById(R.id.digit_one_button);
        final Button twoButton = findViewById(R.id.digit_two_button);
        final Button threeButton = findViewById(R.id.digit_three_button);
        final Button fourButton = findViewById(R.id.digit_four_button);
        final Button fiveButton = findViewById(R.id.digit_five_button);
        final Button sixButton = findViewById(R.id.digit_six_button);
        final Button sevenButton = findViewById(R.id.digit_seven_button);
        final Button eightButton = findViewById(R.id.digit_eight_button);
        final Button nineButton = findViewById(R.id.digit_nine_button);
        final Button zeroButton = findViewById(R.id.digit_zero_button);
        final Button separatorButton = findViewById(R.id.separator_button);
        final Button clearButton = findViewById(R.id.clear_button);
        final Button deleteButton = findViewById(R.id.delete_button);
        final Button openSecondActivityButton = findViewById(R.id.open_second_activity);
        screenCalculatorTextView = findViewById(R.id.screen_calculator_text_view);
        SecondActivity.data = new DataCalculator();

        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '1');
            }
        });
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '2');
            }
        });
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '3');
            }
        });
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '4');
            }
        });
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '5');
            }
        });
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '6');
            }
        });
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '7');
            }
        });
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '8');
            }
        });
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '9');
            }
        });
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screenCalculatorTextView.getText() != "") {
                    screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '0');
                }
            }
        });
        separatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!(screenCalculatorTextView.getText().toString().contains("."))) && (screenCalculatorTextView.getText() != "")) {
                    screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString() + '.');
                }
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenCalculatorTextView.setText("");
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (screenCalculatorTextView.getText().toString().length() > 0) {
                    screenCalculatorTextView.setText(screenCalculatorTextView.getText().toString().substring(0, screenCalculatorTextView.getText().toString().length() - 1));
                }
            }
        });
        openSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SecondActivity.getIntentForLaunch(MainActivity.this);
                SecondActivity.data.setNumberOnScreen(screenCalculatorTextView.getText().toString());
                startActivity(intent);
            }
        });
    }
}