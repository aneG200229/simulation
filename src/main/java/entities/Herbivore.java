package entities;

import map.Coordinates;
import map.GameMap;
import path.Path;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates) {
        super(coordinates, "\uD83D\uDC30", 1, 10);
    }

    @Override
    public void makeMove(GameMap gameMap, Path path) {
        List<Coordinates> pathCoordinates = path.findPath(this.getCoordinates(), Grass.class, gameMap);
        if (pathCoordinates == null || pathCoordinates.isEmpty()) {
            return;
        }
        if (pathCoordinates.size() - 1 <= this.getSpeed()) {
            Coordinates grassCoord = pathCoordinates.get(pathCoordinates.size() - 1);
            gameMap.removeEntity(grassCoord);
            gameMap.moveEntity(this.getCoordinates(), grassCoord);
            System.out.println("\uD83D\uDC30 переместился с " + pathCoordinates.get(0)
                    + " и съел \uD83C\uDF31 на " + grassCoord);
            return;
        }
        int targetIndex = Math.min(getSpeed(), pathCoordinates.size() - 1);
        gameMap.moveEntity(this.getCoordinates(), pathCoordinates.get(targetIndex));
        System.out.println("\uD83D\uDC30 переместился с " + this.getCoordinates() + " на "
                + pathCoordinates.get(targetIndex));
    }


}
