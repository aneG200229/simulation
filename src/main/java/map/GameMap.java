package map;

import entities.Entity;

import java.util.Collection;
import java.util.HashMap;

public class GameMap {
    private final HashMap<Coordinates, Entity> gameMap = new HashMap<>();
    private final int MAX_X = 6;
    private final int MIN_X = 0;
    private final int MAX_Y = 6;
    private final int MIN_Y = 0;


    public void addEntity(Coordinates coordinates, Entity entity) {
        gameMap.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        gameMap.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return gameMap.get(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !gameMap.containsKey(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = gameMap.get(from);
        if (entity != null && isCellEmpty(to)) {
            gameMap.remove(from);
            entity.setCoordinates(to);
            addEntity(to, entity);
        }
    }

    public Collection<Entity> getAllEntity() {
        return gameMap.values();
    }

    public int getMAX_X() {
        return MAX_X;
    }

    public int getMIN_X() {
        return MIN_X;
    }

    public int getMAX_Y() {
        return MAX_Y;
    }

    public int getMIN_Y() {
        return MIN_Y;
    }
}
