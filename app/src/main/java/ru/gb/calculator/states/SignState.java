package ru.gb.calculator.states;

import java.util.List;

import ru.gb.calculator.entites.InputSymbol;

public class SignState extends BaseState{

    @Override
    public BaseState onClickButton(InputSymbol inputSymbol) {
        switch (inputSymbol) {
            case OP_MINUS:
                input.add(InputSymbol.OP_MINUS);
                return new FirstIntState(input);
            case NUM_0:
            case DOT:
                input.add(InputSymbol.NUM_0);
                input.add(InputSymbol.DOT);
                return new FloatState(input);
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
            case CLEAR:
                return new SignState();
            default:
                return this;
        }
    }

}
