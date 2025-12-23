package rendering;

import entities.*;
import gamemap.Coordinates;
import gamemap.GameMap;

import java.util.Map;
import java.util.Optional;

public class GameMapRenderer {
    private static final Map<Class<? extends Entity>, String> SPRITES = Map.of(
            Grass.class, "\uD83C\uDF31",
            Herbivore.class, "🐰",
            Predator.class, "🐺",
            Rock.class, "🪨",
            Tree.class, "🌳"
    );
    public void drawMap(GameMap gameMap) {
        for (int y = 0; y <= gameMap.getMaxY(); y++) {
            for (int x = 0; x <= gameMap.getMaxX(); x++) {
                Coordinates cords = new Coordinates(x,y);
                Optional<Entity> entityOpt = gameMap.getEntity(cords);
                if (entityOpt.isPresent()){
                    Entity entity = entityOpt.get();
                    String sprite = SPRITES.get(entity.getClass());
                    System.out.print(sprite + " ");
                }else {
                    System.out.print("⬜ ");
                }
            }
            System.out.println();
        }
    }
}
