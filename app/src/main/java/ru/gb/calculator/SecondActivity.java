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
    public static final String NUMBER_KEY = "number_key";
    public static DataCalculator data;
    private TextView numberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        data = (DataCalculator) getIntent().getExtras().getParcelable(NUMBER_KEY);
        Log.d(TAG,"SECOND onCreate   NUMBER_KEY="+SecondActivity.NUMBER_KEY+", data.number="+SecondActivity.data.getNumberOnScreen());
        initParameters();

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        SecondActivity.data.setNumberOnScreen(numberTextView.getText().toString());
        Log.d(TAG,"SECOND onSave   NUMBER_KEY="+SecondActivity.NUMBER_KEY+", data.number="+SecondActivity.data.getNumberOnScreen());
        outState.putParcelable(SecondActivity.NUMBER_KEY,SecondActivity.data);
    }
    public static Intent getIntentForLaunch(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(NUMBER_KEY,data);
        return intent;
    }
    private void initParameters() {
        numberTextView = findViewById(R.id.number_text_view);
        numberTextView.setText(data.getNumberOnScreen());
    }
}
