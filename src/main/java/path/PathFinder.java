package path;

import entities.Entity;
import gamemap.Coordinates;
import gamemap.GameMap;

import java.util.*;

public class PathFinder {
    private final static List<Coordinates> SHIFT_COORDINATES = List.of(
            new Coordinates(0, 1),
            new Coordinates(0, -1),
            new Coordinates(1, 0),
            new Coordinates(-1, 0));

    public List<Coordinates> findPath(Coordinates start, Class<?> target, GameMap gameMap) {
        Queue<Node> queue = new LinkedList<>();
        Set<Coordinates> visitedCells = new HashSet<>();
        Node startNode = new Node(start, null);
        visitedCells.add(start);
        queue.add(startNode);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Coordinates currentCoords = currentNode.getCoordinates();
            Optional<Entity> entityOpt = gameMap.getEntity(currentCoords);
            if (entityOpt.isPresent() && entityOpt.get().getClass() == target) {
                ArrayList<Coordinates> path = new ArrayList<>();
                Node current = currentNode;
                while (current != null) {
                    path.add(current.getCoordinates());
                    current = current.getParent();
                }
                Collections.reverse(path);
                return path;
            }
            for (Coordinates neighbor : getNeighbors(currentCoords)) {
                if (!isInBounds(neighbor, gameMap)) {
                    continue;
                }

                Optional<Entity> neighborEntity = gameMap.getEntity(neighbor);

                if ((neighborEntity.isEmpty() || neighborEntity.get().getClass() == target)
                        && !visitedCells.contains(neighbor)) {
                    Node neighborNode = new Node(neighbor, currentNode);
                    queue.add(neighborNode);
                    visitedCells.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    public List<Coordinates> getNeighbors(Coordinates coord) {
        List<Coordinates> neighbors = new ArrayList<>();
        for (Coordinates shift : SHIFT_COORDINATES) {
            Coordinates neighbor = new Coordinates(
                    coord.x() + shift.x(),
                    coord.y() + shift.y());
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    public boolean isInBounds(Coordinates coord, GameMap gameMap) {
        int x = coord.x();
        int y = coord.y();
        if (x <= gameMap.getMaxX() && x >= 0 && y <= gameMap.getMaxY() && y >= 0) {
            return true;
        }
        return false;
    }
}
