package path;

import gamemap.Coordinates;
public class Node {
    private Coordinates coordinates;
    private Node parent;

    public Node(Coordinates coordinates, Node node) {
        this.coordinates = coordinates;
        this.parent = node;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Node getParent() {
        return parent;
    }

}
