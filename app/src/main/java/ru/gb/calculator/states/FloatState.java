package ru.gb.calculator.states;

import java.util.List;

import ru.gb.calculator.entites.InputSymbol;

public class FloatState extends BaseState{

    public FloatState(List<InputSymbol> input) {
        this.input.addAll(input);
    }

    @Override
    public BaseState onClickButton(InputSymbol inputSymbol) {
        switch (inputSymbol) {
            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
                input.add(inputSymbol);
                return this;
            case CLEAR:
                return new SignState();
            default:
                return this;
        }
    }
}
