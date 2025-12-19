package actions;

import map.Coordinates;
import map.GameMap;

import java.util.Random;

public abstract class Action {

    Random random = new Random();

    public abstract void perform(GameMap map);

    public Coordinates getRandomCoordinates(GameMap map) {
        int x = random.nextInt(map.getMIN_X(), map.getMAX_X() + 1);
        int y = random.nextInt(map.getMIN_Y(), map.getMAX_Y() + 1);
        return new Coordinates(x, y);
    }


}
