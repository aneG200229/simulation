package entities;

import map.Coordinates;
import map.GameMap;
import path.Path;

import java.util.List;

public class Predator extends Creature {
    private final int damage;

    public Predator(Coordinates coordinates) {
        super(coordinates, "\uD83D\uDC3A", 2, 20);
        this.damage = 5;
    }

    @Override
    public void makeMove(GameMap gameMap, Path path) {
        List<Coordinates> pathCoordinates = path.findPath(this.getCoordinates(), Herbivore.class, gameMap);
        if (pathCoordinates == null || pathCoordinates.isEmpty()) {
            return;
        }
        if (pathCoordinates.size() - 1 <= this.getSpeed()) {
            Coordinates herbivoreCoords = pathCoordinates.get(pathCoordinates.size() - 1);
            Entity entity = gameMap.getEntity(herbivoreCoords);
            if (entity instanceof Herbivore herbivore) {
                hit(herbivore);
                System.out.println("\uD83D\uDC3A на " + pathCoordinates.get(0) + " атаковал \uD83D\uDC30 на " + herbivoreCoords);
                if (herbivore.getHp() <= 0) {
                    System.out.println("\uD83D\uDC30 убит на координатах " + herbivoreCoords);
                    gameMap.removeEntity(herbivoreCoords);
                }
            }
            return;
        }
        int targetIndex = Math.min(getSpeed(), pathCoordinates.size() - 1);
        Coordinates targetCoordinates = pathCoordinates.get(targetIndex);
        gameMap.moveEntity(this.getCoordinates(), targetCoordinates);
        System.out.println("\uD83D\uDC3A переместился с " + this.getCoordinates() + " на " + pathCoordinates.get(targetIndex));

    }

    public void hit(Herbivore herbivore) {
        int newHp = herbivore.getHp() - this.getDamage();
        herbivore.setHp(newHp);
    }

    public int getDamage() {
        return damage;
    }
}
