package ru.gb.calculator.states;

import java.util.List;

import ru.gb.calculator.entites.InputSymbol;

public class FirstIntState extends BaseState {

    public FirstIntState(List<InputSymbol> input) {
        this.input.addAll(input);
    }

    @Override
    public BaseState onClickButton(InputSymbol inputSymbol) {
        switch (inputSymbol) {
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
                input.add(inputSymbol);
                return new IntState(input);
            case DOT:
            case NUM_0:
                input.add(InputSymbol.NUM_0);
                input.add(InputSymbol.DOT);
                return new FloatState(input);
            case CLEAR:
                return new SignState();
            default:
                return this;
        }
    }
}
