package entities;

import gamemap.Coordinates;
import gamemap.GameMap;

import java.util.List;
import java.util.Optional;

public class Predator extends Creature {
    private static final int SPEED = 2;
    private static final int HP = 20;
    private static final int ATTACK_DAMAGE = 5;
    private final int damage;

    public Predator(Coordinates coordinates) {
        super(coordinates, SPEED, HP);
        this.damage = ATTACK_DAMAGE;
    }

    public void hit(Herbivore herbivore) {
        int newHp = herbivore.getHp() - this.getDamage();
        herbivore.setHp(newHp);
    }

    @Override
    Class<? extends Entity> getTarget() {
        return Herbivore.class;
    }

    @Override
    void performMove(Coordinates oldCoords, GameMap gameMap, List<Coordinates> pathCoordinates) {
        if (pathCoordinates.size() - 1 <= this.getSpeed()) {
            Coordinates herbivoreCoords = pathCoordinates.get(pathCoordinates.size() - 1);
            Optional<Entity> entityOpt = gameMap.getEntity(herbivoreCoords);
            if (entityOpt.isPresent()) {
                Entity entity = entityOpt.get();
                if (entity instanceof Herbivore herbivore) {
                    hit(herbivore);
                    if (herbivore.getHp() <= 0) {
                        gameMap.removeEntity(herbivoreCoords);
                    }
                }
            }
            return;
        }
        int targetIndex = Math.min(getSpeed(), pathCoordinates.size() - 1);
        Coordinates targetCoordinates = pathCoordinates.get(targetIndex);
        gameMap.removeEntity(oldCoords);
        this.setCoordinates(targetCoordinates);
        gameMap.addEntity(targetCoordinates, this);
    }

    public int getDamage() {
        return damage;
    }
}
