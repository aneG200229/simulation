package entities;

import gamemap.Coordinates;
import gamemap.GameMap;
import path.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    private Coordinates coordinates;
    private final int speed;
    private int hp;

    public Creature(Coordinates coordinates, int speed, int hp) {
        this.coordinates = coordinates;
        this.speed = speed;
        this.hp = hp;
    }

    abstract Class<? extends Entity> getTarget();

    abstract void performMove(Coordinates oldCoords, GameMap gameMap, List<Coordinates> pathCoordinates);

    public void makeMove(GameMap gameMap, PathFinder pathFinder){
        List<Coordinates> pathCoordinates = pathFinder.findPath(this.getCoordinates(), getTarget(), gameMap);
        if (pathCoordinates == null || pathCoordinates.isEmpty()) {
            return;
        }

        Coordinates oldCoords = this.getCoordinates();
        performMove(oldCoords,gameMap,pathCoordinates);
    }

    public int getSpeed() {
        return speed;
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
