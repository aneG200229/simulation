package actions.turnactions;

import actions.Action;
import actions.initactions.InitGrassAction;
import entities.Entity;
import entities.Grass;
import map.Coordinates;
import map.GameMap;

public class SpawnGrassAction extends Action {

    @Override
    public void perform(GameMap map) {
        int currentGrass = countGrass(map);
        int maxGrass = InitGrassAction.getMaxGrass();
        while (currentGrass != maxGrass) {
            Coordinates randomCoord = getRandomCoordinates(map);
            if (map.isCellEmpty(randomCoord)) {
                map.addEntity(randomCoord, new Grass(randomCoord));
                currentGrass++;
            }
        }
    }

    private int countGrass(GameMap map) {
        int count = 0;
        for (Entity entity : map.getAllEntity()) {
            if (entity instanceof Grass) {
                count++;
            }
        }
        return count;
    }
}
