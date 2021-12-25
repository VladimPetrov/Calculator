package ru.gb.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String NUMBER_KEY = "number_key";
    private TextView screenCalculatorTextView;
    private DataCalculator data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParametrs();
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_KEY)) {
            data.setNumberOnScreen(savedInstanceState.getString(NUMBER_KEY));
        }
        showNumberOnTextView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        data.setNumberOnScreen(screenCalculatorTextView.getText().toString());
        outState.putParcelable(NUMBER_KEY,data);
    }


    private void showNumberOnTextView() {
        screenCalculatorTextView.setText(data.getNumberOnScreen());
    }


    private void initParametrs() {

        final Button oneButton = findViewById(R.id.one_button);
        final Button twoButton = findViewById(R.id.two_button);
        final Button threeButton = findViewById(R.id.three_button);
        final Button fourButton = findViewById(R.id.four_button);
        final Button fiveButton = findViewById(R.id.five_button);
        final Button sixButton = findViewById(R.id.six_button);
        final Button sevenButton = findViewById(R.id.seven_button);
        final Button eightButton = findViewById(R.id.eight_button);
        final Button nineButton = findViewById(R.id.nine_button);
        final Button zeroButton = findViewById(R.id.zero_button);
        final Button separatorButton = findViewById(R.id.separator_button);
        final Button clearButton = findViewById(R.id.сlear_button);
        final Button deleteButton = findViewById(R.id.delete_button);
        final Button openSecondActivityButton = findViewById(R.id.open_second_activity);
        screenCalculatorTextView = findViewById(R.id.screen_calculator_textview);
        data = new DataCalculator();

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
                Intent intent = SecondActivity.getIntentForLaunch(this, number);
                startActivity(intent);
            }
        });
    }
}