package server;

import java.util.Stack;

public class Caretaker {
    private Stack<Memento> mementoStack;

    public Caretaker(){
        mementoStack = new Stack<Memento>();
    }
}
