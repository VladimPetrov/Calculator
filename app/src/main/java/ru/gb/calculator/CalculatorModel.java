package ru.gb.calculator;

import static ru.gb.calculator.entites.InputSymbol.DEL_SYMBOL;
import static ru.gb.calculator.entites.InputSymbol.DOT;
import static ru.gb.calculator.entites.InputSymbol.EQUAL;
import static ru.gb.calculator.entites.InputSymbol.NUM_0;
import static ru.gb.calculator.entites.InputSymbol.NUM_1;
import static ru.gb.calculator.entites.InputSymbol.NUM_2;
import static ru.gb.calculator.entites.InputSymbol.NUM_3;
import static ru.gb.calculator.entites.InputSymbol.NUM_4;
import static ru.gb.calculator.entites.InputSymbol.NUM_5;
import static ru.gb.calculator.entites.InputSymbol.NUM_6;
import static ru.gb.calculator.entites.InputSymbol.NUM_7;
import static ru.gb.calculator.entites.InputSymbol.NUM_8;
import static ru.gb.calculator.entites.InputSymbol.NUM_9;
import static ru.gb.calculator.entites.InputSymbol.OP_DIV;
import static ru.gb.calculator.entites.InputSymbol.OP_MINUS;
import static ru.gb.calculator.entites.InputSymbol.OP_MUL;
import static ru.gb.calculator.entites.InputSymbol.OP_PERCENT;
import static ru.gb.calculator.entites.InputSymbol.OP_PLUS;
import static ru.gb.calculator.entites.InputSymbol.OP_SQRT;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


import ru.gb.calculator.entites.InputSymbol;
import ru.gb.calculator.states.BaseState;
import ru.gb.calculator.states.FirstIntState;
import ru.gb.calculator.states.FloatState;
import ru.gb.calculator.states.IntState;
import ru.gb.calculator.states.SignState;

public class CalculatorModel implements Parcelable {
    private final List<InputSymbol> operatorList = new ArrayList<>(Arrays.asList(OP_MINUS, OP_PLUS, OP_DIV, OP_MUL, OP_SQRT));
    private String firstNumber = "";
    private String result = "";
    private InputSymbol operation = null;
    private BaseState currentState = new SignState();

    public CalculatorModel () {

    }

    private CalculatorModel (Parcel in) {
        result = in.readString();
        firstNumber = in.readString();
        byteToOperation(in.readByte());
        byteToState(in.readByte(),returnArray(in.readString()));
    }

    public static final Creator<CalculatorModel> CREATOR = new Creator<CalculatorModel>() {
        @Override
        public CalculatorModel createFromParcel(Parcel in) {
            return new CalculatorModel(in);
        }

        @Override
        public CalculatorModel[] newArray(int size) {
            return new CalculatorModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(result);
        parcel.writeString(firstNumber);
        parcel.writeByte(operationToByte());

        parcel.writeByte(currentStateToByte());
        parcel.writeString(returnNumber());
    }

    private void byteToState (Byte i, List<InputSymbol> input) {
        switch (i) {
            case 1:
                currentState = new SignState();
                break;
            case 2:
                currentState = new FirstIntState(input);
                break;
            case 3:
                currentState = new IntState(input);
                break;
            case 4:
                currentState = new FloatState(input);
                break;
        }
    }

    private Byte currentStateToByte () {
        if(currentState.getClass().getSimpleName().equals("SignState")) {
            return 1;
        }
        if(currentState.getClass().getSimpleName().equals("FirstIntState")) {
            return 2;
        }
        if(currentState.getClass().getSimpleName().equals("IntState")) {
            return 3;
        }
        return 4;
    }

    private void byteToOperation (Byte digit) {
        switch (digit) {
            case 1:
                operation = OP_MINUS;
                break;
            case 2:
                operation = OP_PLUS;
                break;
            case 3:
                operation = OP_DIV;
                break;
            case 4:
                operation = OP_MUL;
                break;
        }
    }

    private Byte operationToByte () {
        switch (operation) {
            case OP_MINUS:
                return 1;
            case OP_PLUS:
                return 2;
            case OP_DIV:
                return 3;
            case OP_MUL:
                return 4;
        }
        return 0;
    }

    public void onClickButton(InputSymbol inputSymbol) {

        if (currentState.getClass().getSimpleName().equals("SignState") & result.length() > 0) {
            result = "";
        }

        if (inputSymbol == DEL_SYMBOL) {
            deleteSymbol();
            return;
        }

        if (checkOperator(inputSymbol) & operation == null) {
            Log.d("@@@", "Operation = " + inputSymbol.name());
            firstNumber = returnNumber();
            currentState = new SignState();
            operation = inputSymbol;
            if (inputSymbol == OP_SQRT) {
                baseCalculations();
            }
            return;
        }
        if ((inputSymbol == EQUAL) & (operation != null)) {
            Log.d("@@@", "On Click Button EQUAL FirstNumber=" + firstNumber);
            baseCalculations();
            return;
        }
        if ((inputSymbol == OP_PERCENT) & (operation == OP_MUL)) {
            Log.d("@@@", "On Click Button PERCENT FirstNumber=" + firstNumber + "  SecondNumber=" + returnNumber());
            percentCalculations();
            return;
        }
        if ((inputSymbol == OP_PERCENT) & (operation == OP_PLUS)) {
            Log.d("@@@", "On Click Button PERCENT FirstNumber=" + firstNumber + "  SecondNumber=" + returnNumber());
            percentCalculations();
            return;
        }
        BaseState newState = currentState.onClickButton(inputSymbol);
        Log.d("@@@", "Old state = " + currentState.getClass().getSimpleName());
        Log.d("@@@", "Input symbol = " + inputSymbol.name());
        Log.d("@@@", "New state = " + newState.getClass().getSimpleName());
        Log.d("@@@", "\n");
        currentState = newState;
    }

    private void deleteSymbol(){
        List<InputSymbol> input = currentState.getInput();
        if (input.get(input.size()-1) == DOT) {
                input.remove(input.size()-1);
                currentState = new IntState(input);
        }
        if(currentState.getClass().getSimpleName().equals("FirstIntState")) {
            currentState = new SignState();
        }
        if(currentState.getClass().getSimpleName().equals("IntState")) {
            input.remove(input.size()-1);
            currentState = new IntState(input);
        }
        if(currentState.getClass().getSimpleName().equals("FloatState")) {
            input.remove(input.size()-1);
            currentState = new FloatState(input);
        }
    }

    private void percentCalculations() {
        switch (operation) {
            case OP_PLUS:
                result = String.valueOf(Float.parseFloat(firstNumber) + ((Float.parseFloat(firstNumber) / 100) * Float.parseFloat(returnNumber())));
                break;
            case OP_MUL:
                result = String.valueOf((Float.parseFloat(firstNumber) / 100) * Float.parseFloat(returnNumber()));
                break;
        }
        firstNumber = "";
        currentState = new SignState();
        operation = null;
    }

    private void baseCalculations() {
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
            case OP_SQRT:
                result = String.valueOf(Math.sqrt(Float.parseFloat(firstNumber)));
                break;
        }
        firstNumber = "";
        currentState = new SignState();
        operation = null;
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

    private List<InputSymbol> returnArray (String str) {
        final List<InputSymbol> inputList = new ArrayList<>();
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i++ ) {
            switch (chars[i]) {
                case '0':
                    inputList.add(NUM_0);
                    break;
                case '1':
                    inputList.add(NUM_1);
                    break;
                case '2':
                    inputList.add(NUM_2);
                    break;
                case '3':
                    inputList.add(NUM_3);
                    break;
                case '4':
                    inputList.add(NUM_4);
                    break;
                case '5':
                    inputList.add(NUM_5);
                    break;
                case '6':
                    inputList.add(NUM_6);
                    break;
                case '7':
                    inputList.add(NUM_7);
                    break;
                case '8':
                    inputList.add(NUM_8);
                    break;
                case '9':
                    inputList.add(NUM_9);
                    break;
                case '.':
                    inputList.add(DOT);
                    break;
                case '-':
                    inputList.add(OP_MINUS);
                    break;
            }
        }
        return inputList;
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
