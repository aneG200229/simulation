package actions.initactions;

import actions.Action;
import entities.Herbivore;
import map.Coordinates;
import map.GameMap;

public class InitHerbivoreAction extends Action {
    private static int maxHerbivore = 3;

    public static int getMaxHerbivore() {
        return maxHerbivore;
    }

    @Override
    public void perform(GameMap map) {
        int counter = 0;
        int attempts = 0;
        int maxAttempts = maxHerbivore * 10;
        while (counter < maxHerbivore && attempts < maxAttempts) {
            Coordinates randomCoord = getRandomCoordinates(map);
            attempts++;
            if (map.isCellEmpty(randomCoord)) {
                map.addEntity(randomCoord, new Herbivore(randomCoord));
                counter++;
            }
        }
    }
}
