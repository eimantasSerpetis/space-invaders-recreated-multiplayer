package common.iterator;

import server.StateObserver;

import java.util.List;

public class ObserverCollection implements IterableCollection {
    private final List<StateObserver> observers;

    public ObserverCollection(List<StateObserver> observers) {
        this.observers = observers;
    }

    public void add(StateObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void remove(StateObserver observer) {
        observers.remove(observer);
    }

    @Override
    public ObserverIterator createIterator() {
        return new ObserverIterator(observers);
    }
}
