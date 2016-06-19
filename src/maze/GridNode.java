package maze;

public class GridNode {

    boolean hasLeft, hasRight, hasUp, hasDown;

    void print() {
        System.out.println("left: " + hasLeft);
        System.out.println("right: " + hasRight);
        System.out.println("up: " + hasUp);
        System.out.println("down: " + hasDown);
    }
}
