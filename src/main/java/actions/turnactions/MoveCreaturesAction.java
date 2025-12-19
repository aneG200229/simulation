package actions.turnactions;

import actions.Action;
import entities.Creature;
import entities.Entity;
import map.GameMap;
import path.Path;

import java.util.ArrayList;
import java.util.List;

public class MoveCreaturesAction extends Action {
    @Override
    public void perform(GameMap map) {
        List<Creature> creatures = new ArrayList<>();
        Path pathFinder = new Path();
        for (Entity entity : map.getAllEntity()) {
            if (entity instanceof Creature creature) {
                creatures.add(creature);
            }
        }
        for (Creature creature : creatures) {
            creature.makeMove(map, pathFinder);
        }
    }
}
