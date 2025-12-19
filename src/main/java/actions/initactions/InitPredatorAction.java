package actions.initactions;

import actions.Action;
import entities.Predator;
import map.Coordinates;
import map.GameMap;

public class InitPredatorAction extends Action {
    private static final int MAX_PREDATORS = 2;

    @Override
    public void perform(GameMap map) {
        int counter = 0;
        int attempts = 0;
        int maxAttempts = MAX_PREDATORS * 10;
        while (counter < MAX_PREDATORS && attempts < maxAttempts) {
            Coordinates randomCoord = getRandomCoordinates(map);
            attempts++;
            if (map.isCellEmpty(randomCoord)) {
                map.addEntity(randomCoord, new Predator(randomCoord));
                counter++;
            }
        }
    }

}
