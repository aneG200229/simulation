package actions.turnactions;

import actions.Action;
import actions.initactions.InitHerbivoreAction;
import entities.Entity;
import entities.Herbivore;
import map.Coordinates;
import map.GameMap;

public class SpawnHerbivoreAction extends Action {
    @Override
    public void perform(GameMap map) {
        int currentHerbivore = countHerbivore(map);
        int maxHerbivore = InitHerbivoreAction.getMaxHerbivore();
        while (currentHerbivore != maxHerbivore) {
            Coordinates randomCoord = getRandomCoordinates(map);
            if (map.isCellEmpty(randomCoord)) {
                map.addEntity(randomCoord, new Herbivore(randomCoord));
                currentHerbivore++;
            }
        }
    }

    private int countHerbivore(GameMap map) {
        int count = 0;
        for (Entity entity : map.getAllEntity()) {
            if (entity instanceof Herbivore) {
                count++;
            }
        }
        return count;
    }
}
