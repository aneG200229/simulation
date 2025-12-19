package actions.initactions;

import actions.Action;
import entities.Grass;
import map.Coordinates;
import map.GameMap;

public class InitGrassAction extends Action {
    private static int maxGrass = 4;

    public static int getMaxGrass() {
        return maxGrass;
    }

    @Override
    public void perform(GameMap map) {
        int counter = 0;
        int attempts = 0;
        int maxAttempt = maxGrass * 10;
        while (counter < maxGrass && attempts < maxAttempt) {
            Coordinates randomCoord = getRandomCoordinates(map);
            attempts++;
            if (map.isCellEmpty(randomCoord)) {
                map.addEntity(randomCoord, new Grass(randomCoord));
                counter++;
            }
        }

    }
}
