package entities;

import map.Coordinates;
import map.GameMap;
import path.Path;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;

    public Creature(Coordinates coordinates, String emoji, int speed, int hp) {
        super(coordinates, emoji);
        this.speed = speed;
        this.hp = hp;
    }

    public abstract void makeMove(GameMap gameMap, Path path);

    public int getSpeed() {
        return speed;
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
