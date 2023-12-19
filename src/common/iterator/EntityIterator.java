package common.iterator;

import server.entities.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityIterator implements Iterator {
    private int currentIndex;
    private final List<Map.Entry<Integer, Entity>> list;

    public EntityIterator(Map<Integer, Entity> enemyMap) {
        this.currentIndex = 0;
        this.list = new ArrayList<>(enemyMap.entrySet());
    }

    @Override
    public Map.Entry<Integer, Entity> getNext() {
        if (hasNext()) {
            return list.get(currentIndex++);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }
}
