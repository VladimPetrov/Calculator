package ru.gb.calculator.states;

import java.util.ArrayList;
import java.util.List;

import ru.gb.calculator.entites.InputSymbol;

abstract public class BaseState {
    protected final List<InputSymbol> input = new ArrayList<>();

    public abstract BaseState onClickButton (InputSymbol inputSymbol);

    public List<InputSymbol> getInput() {
        return new ArrayList<>(input);
    }

}
