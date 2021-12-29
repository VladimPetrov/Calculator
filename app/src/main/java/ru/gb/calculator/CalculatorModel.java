package ru.gb.calculator;

import static ru.gb.calculator.entites.InputSymbol.EQUAL;
import static ru.gb.calculator.entites.InputSymbol.OP_DIV;
import static ru.gb.calculator.entites.InputSymbol.OP_MINUS;
import static ru.gb.calculator.entites.InputSymbol.OP_MUL;
import static ru.gb.calculator.entites.InputSymbol.OP_PLUS;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


import ru.gb.calculator.entites.InputSymbol;
import ru.gb.calculator.states.BaseState;
import ru.gb.calculator.states.SignState;

public class CalculatorModel {
    private final List<InputSymbol> operatorList = new ArrayList<>(Arrays.asList(OP_MINUS, OP_PLUS, OP_DIV, OP_MUL));
    private String firstNumber = "";
    private String result = "";
    private InputSymbol operation = null;
    private BaseState currentState = new SignState();

    public void onClickButton(InputSymbol inputSymbol) {

        if (currentState.getClass().getSimpleName().equals("SignState") & result.length() > 0) {
            result = "";
        }

        if (checkOperator(inputSymbol) & operation == null) {
            Log.d("@@@", "Operation = " + inputSymbol.name());
            firstNumber = returnNumber();
            currentState = new SignState();
            operation = inputSymbol;
            return;
        }
        if ((inputSymbol == EQUAL) & (operation != null)) {
            Log.d("@@@", "On Click Button EQUAL FirstNumber="+firstNumber);
            calculations();
            firstNumber = "";
            currentState = new SignState();
            operation = null;
            return;
        }
        BaseState newState = currentState.onClickButton(inputSymbol);
        Log.d("@@@", "Old state = " + currentState.getClass().getSimpleName());
        Log.d("@@@", "Input symbol = " + inputSymbol.name());
        Log.d("@@@", "New state = " + newState.getClass().getSimpleName());
        Log.d("@@@", "\n");
        currentState = newState;
    }

    private void calculations() {
        switch (operation) {
            case OP_MINUS:
                result = String.valueOf(Float.parseFloat(firstNumber) - Float.parseFloat(returnNumber()));
                break;
            case OP_PLUS:
                result = String.valueOf(Float.parseFloat(firstNumber) + Float.parseFloat(returnNumber()));
                break;
            case OP_MUL:
                result = String.valueOf(Float.parseFloat(firstNumber) * Float.parseFloat(returnNumber()));
                break;
            case OP_DIV:
                result = String.valueOf(Float.parseFloat(firstNumber) / Float.parseFloat(returnNumber()));
                break;
        }
    }

    private boolean checkOperator(InputSymbol inputSymbol) {

        return operatorList.contains(inputSymbol) & (currentState.getClass().getSimpleName().equals("IntState") || currentState.getClass().getSimpleName().equals("FloatState"));

    }

    public String getInput() {
        if (result.equals("")) {
            return returnNumber();
        }
        return result;
    }

    private String returnNumber() {
        final StringBuilder sb = new StringBuilder();
        for (InputSymbol inputSymbol : currentState.getInput()) {
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
