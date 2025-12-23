package entities;

import gamemap.Coordinates;
import gamemap.GameMap;

import java.util.List;

public class Herbivore extends Creature {
    private static final int SPEED = 1;
    private static final int HP = 10;
    public Herbivore(Coordinates coordinates) {
        super(coordinates, SPEED, HP);
    }

    @Override
    Class<? extends Entity> getTarget() {
        return Grass.class;
    }

    @Override
    void performMove(Coordinates oldCoords, GameMap gameMap, List<Coordinates> pathCoordinates) {
        if (pathCoordinates.size() - 1 <= this.getSpeed()) {
            Coordinates grassCoord = pathCoordinates.get(pathCoordinates.size() - 1);
            gameMap.removeEntity(grassCoord);
            gameMap.removeEntity(oldCoords);
            this.setCoordinates(grassCoord);
            gameMap.addEntity(grassCoord, this);
            return;
        }
        int targetIndex = Math.min(getSpeed(), pathCoordinates.size() - 1);
        Coordinates newCoords = pathCoordinates.get(targetIndex);
        gameMap.removeEntity(oldCoords);
        this.setCoordinates(newCoords);
        gameMap.addEntity(newCoords, this);
    }
}
