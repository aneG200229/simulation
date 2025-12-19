package actions.initactions;

import actions.Action;
import entities.Rock;
import map.Coordinates;
import map.GameMap;

public class InitRockAction extends Action {
    private static final int MAX_ROCKS = 2;

    @Override
    public void perform(GameMap map) {
        int counter = 0;
        int attempts = 0;
        int maxAttempts = MAX_ROCKS * 10;
        while (counter < MAX_ROCKS && attempts < maxAttempts) {
            Coordinates randomCoord = getRandomCoordinates(map);
            attempts++;
            if (map.isCellEmpty(randomCoord)) {
                map.addEntity(randomCoord, new Rock(randomCoord));
                counter++;
            }
        }

    }

}
