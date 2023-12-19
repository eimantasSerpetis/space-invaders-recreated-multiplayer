package common.iterator;

import server.StateObserver;

import java.util.List;

public class ObserverIterator implements Iterator {
    private int currentIndex;
    private final StateObserver[] arr;

    public ObserverIterator(List<StateObserver> observers) {
        this.currentIndex = 0;
        this.arr = observers.toArray(new StateObserver[0]);
    }

    @Override
    public StateObserver getNext() {
        if (hasNext()) {
            return arr[currentIndex++];
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < arr.length;
    }
}
