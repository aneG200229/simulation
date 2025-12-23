package gamemap;

import entities.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameMap {
    private final int width;
    private final int height;
    private final Map<Coordinates, Entity> entities = new HashMap<>();

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public Optional<Entity> getEntity(Coordinates coordinates) {
        return Optional.ofNullable(entities.get(coordinates));
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        validateCoordinates(coordinates);
        return !entities.containsKey(coordinates);
    }

    public Collection<Entity> getAllEntities() {
        return entities.values();
    }

    public int getMaxX() {
        return width - 1;
    }

    public int getMaxY() {
        return height - 1;
    }

    private void validateCoordinates(Coordinates coordinates) {
        if (coordinates.x() < 0 && coordinates.x() > width ||
                coordinates.y() < 0 && coordinates.y() > height) {
            throw new IllegalArgumentException("Координаты вне карты " + coordinates);
        }
    }
}
