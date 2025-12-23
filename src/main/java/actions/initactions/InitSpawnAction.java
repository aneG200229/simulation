package actions.initactions;

import actions.Action;
import actions.CoordinatesUtil;
import entities.Entity;
import gamemap.Coordinates;
import gamemap.GameMap;

import java.util.function.Function;

public class InitSpawnAction extends Action {
    private final Function<Coordinates, Entity> entityCreator;
    private final int count;

    public InitSpawnAction(Function<Coordinates, Entity> entityCreator, int count) {
        this.entityCreator = entityCreator;
        this.count = count;
    }

    @Override
    public void perform(GameMap map) {
        CoordinatesUtil coordinatesUtil = new CoordinatesUtil();
        for (int i = 0; i < count; i++) {
            Coordinates coords = coordinatesUtil.getRandomEmptyCoordinates(map,100);
            Entity entity = entityCreator.apply(coords);
            map.addEntity(coords,entity);
        }
    }

}
