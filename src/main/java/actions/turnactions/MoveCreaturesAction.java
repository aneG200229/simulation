package actions.turnactions;

import actions.Action;
import entities.Creature;
import entities.Entity;
import gamemap.GameMap;
import path.PathFinder;

import java.util.ArrayList;
import java.util.List;

public class MoveCreaturesAction extends Action {
    @Override
    public void perform(GameMap map) {
        List<Creature> creatures = new ArrayList<>();
        PathFinder pathFinder = new PathFinder();
        for (Entity entity : map.getAllEntities()) {
            if (entity instanceof Creature creature) {
                creatures.add(creature);
            }
        }
        for (Creature creature : creatures) {
            creature.makeMove(map, pathFinder);
        }
    }
}
