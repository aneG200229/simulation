package actions.initactions;

import actions.Action;
import entities.Tree;
import map.Coordinates;
import map.GameMap;

public class InitTreeAction extends Action {
    private static final int MAX_TREES = 2;


    @Override
    public void perform(GameMap map) {
        int counter = 0;
        int attempts = 0;
        int maxAttempts = MAX_TREES * 10;
        while (counter < MAX_TREES && attempts < maxAttempts) {
            Coordinates randomCoord = getRandomCoordinates(map);
            attempts++;
            if (map.isCellEmpty(randomCoord)) {
                map.addEntity(randomCoord, new Tree(randomCoord));
                counter++;
            }
        }
    }


}
