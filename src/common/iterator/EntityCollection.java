package common.iterator;

import server.entities.Entity;

import java.util.Map;

public class EntityCollection implements IterableCollection {
    private final Map<Integer, Entity> enemyEntities;

    public EntityCollection(Map<Integer, Entity> enemyEntities) {
        this.enemyEntities = enemyEntities;
    }

    @Override
    public EntityIterator createIterator() {
        return new EntityIterator(enemyEntities);
    }

    public void put(int id, Entity entity) {
        this.enemyEntities.put(id, entity);
    }

    public Entity remove(int id) {
        return this.enemyEntities.remove(id);
    }

    public Map<Integer, Entity> getAll() {
        return enemyEntities;
    }
}
