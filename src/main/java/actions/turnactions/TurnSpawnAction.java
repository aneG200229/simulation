package actions.turnactions;

import actions.Action;
import actions.CoordinatesUtil;
import entities.Entity;
import gamemap.Coordinates;
import gamemap.GameMap;

import java.util.function.Function;
import java.util.function.Predicate;

public class TurnSpawnAction extends Action {
    private final Function<Coordinates, Entity> entityCreator;
    private final int maxCount;
    private final Predicate<Entity> entityPredicate;

    public TurnSpawnAction(Function<Coordinates, Entity> entityCreator, int maxCount, Predicate<Entity> entityPredicate) {
        this.entityCreator = entityCreator;
        this.maxCount = maxCount;
        this.entityPredicate = entityPredicate;
    }

    @Override
    public void perform(GameMap map) {
        CoordinatesUtil coordinatesUtil = new CoordinatesUtil();
        int currentCount = countEntities(map, entityPredicate);
        while (currentCount < maxCount) {
            Coordinates randomCoords = coordinatesUtil.getRandomEmptyCoordinates(map, 100);
            Entity entity = entityCreator.apply(randomCoords);
            map.addEntity(randomCoords,entity);
            currentCount++;
        }
    }

    private int countEntities(GameMap map, Predicate<Entity> predicate) {
        int count = 0;
        for (Entity entity : map.getAllEntities()) {
            if (predicate.test(entity)) {
                count++;
            }
        }
        return count;
    }
}
