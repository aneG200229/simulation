package path;

import entities.Entity;
import map.Coordinates;
import map.GameMap;

import java.util.*;

public class Path {

    public List<Coordinates> findPath(Coordinates start, Class<?> tClass, GameMap gameMap) {
        HashMap<Coordinates, Coordinates> cameFrom = new HashMap<>();
        cameFrom.put(start, null);
        Queue<Coordinates> queue = new LinkedList<>();
        queue.add(start);
        Set<Coordinates> visitedCells = new HashSet<>();
        visitedCells.add(start);
        while (!queue.isEmpty()) {
            Coordinates currentCell = queue.poll();
            Entity entity = gameMap.getEntity(currentCell);
            if (entity != null && entity.getClass() == tClass) {
                ArrayList<Coordinates> path = new ArrayList<>();
                Coordinates current = currentCell;
                while (current != null) {
                    path.add(current);
                    current = cameFrom.get(current);
                }
                Collections.reverse(path);
                return path;
            }
            for (Coordinates neighbor : getNeighbors(currentCell)) {
                entity = gameMap.getEntity(neighbor);
                if (!isInBounds(neighbor, gameMap)) {
                    continue;
                }
                if ((entity == null || entity.getClass() == tClass) && !visitedCells.contains(neighbor)) {
                    queue.add(neighbor);
                    visitedCells.add(neighbor);
                    cameFrom.put(neighbor, currentCell);
                }
            }
        }
        return null;
    }

    public List<Coordinates> getNeighbors(Coordinates coord) {
        List<Coordinates> neighbors = new ArrayList<>();
        neighbors.add(new Coordinates(coord.getX() - 1, coord.getY()));
        neighbors.add(new Coordinates(coord.getX() + 1, coord.getY()));
        neighbors.add(new Coordinates(coord.getX(), coord.getY() - 1));
        neighbors.add(new Coordinates(coord.getX(), coord.getY() + 1));
        return neighbors;
    }

    public boolean isInBounds(Coordinates coord, GameMap gameMap) {
        int x = coord.getX();
        int y = coord.getY();
        if (x <= gameMap.getMAX_X() && x >= gameMap.getMIN_X() && y <= gameMap.getMAX_Y() && y >= gameMap.getMIN_Y()) {
            return true;
        } else {
            return false;
        }
    }
}
