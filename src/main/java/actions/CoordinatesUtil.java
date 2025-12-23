package actions;

import gamemap.Coordinates;
import gamemap.GameMap;

import java.util.Random;

public class CoordinatesUtil {
    private final Random random = new Random();

    public Coordinates getRandomEmptyCoordinates(GameMap map, int maxAttempts){
        for (int i = 0; i < maxAttempts; i++) {
            int x = random.nextInt(0, map.getMaxX() + 1);
            int y = random.nextInt(0, map.getMaxY() + 1);
            Coordinates randomCoords = new Coordinates(x,y);
            if (map.isCellEmpty(randomCoords)) {
                return randomCoords;
            }
        }
        throw new IllegalArgumentException("Нет свободных клеток!");
    }
}
