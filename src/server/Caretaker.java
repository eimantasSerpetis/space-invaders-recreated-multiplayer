package server;

import java.util.Stack;

public class Caretaker {
    private Stack<Memento> mementoStack;

    public Caretaker(){
        mementoStack = new Stack<Memento>();
    }

    public void Save(Memento memento){
        mementoStack.push(memento);
    }

    public void Restore(){
        if(!mementoStack.isEmpty()){
            mementoStack.pop().Restore();
        }
    }
}
