package entities;

import map.Coordinates;

public abstract class Entity {
    private Coordinates coordinates;
    private String emoji;

    public Entity(Coordinates coordinates, String emoji) {
        this.coordinates = coordinates;
        this.emoji = emoji;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getEmoji() {
        return emoji;
    }

    @Override
    public String toString() {
        return "entities.Entity{" +
                "coordinates=" + coordinates +
                ", emoji='" + emoji + '\'' +
                '}';
    }
}
