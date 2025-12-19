package map;

import entities.Entity;

public class Renderer {

    public void drawMap(GameMap gameMap) {
        for (int y = gameMap.getMIN_Y(); y <= gameMap.getMAX_Y(); y++) {
            for (int x = gameMap.getMIN_X(); x <= gameMap.getMAX_X(); x++) {
                Entity entity = gameMap.getEntity(new Coordinates(x, y));
                if (entity == null) {
                    System.out.print("⬜" + " ");
                } else {
                    System.out.print(entity.getEmoji() + " ");
                }
            }
            System.out.println();
        }
    }
}
