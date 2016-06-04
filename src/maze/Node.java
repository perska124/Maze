/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.ArrayList;

/**
 *
 * @author Damian
 */
public class Node {
    public Node(int x, int y){
        this.position.x=x;
        this.position.y=y;
    }
    class Position {
        public int x = 0;
        public int y = 0;
    }
    public Position position=new Position();

    static enum Types {
        PATH, START, END
    };
    public Types type = Types.PATH;
    public Node parent; //who was parent during creation during Prim alghorithm
    public boolean visited = false; //changed during creation, needs to be cleared before doing another alghorithm
    public ArrayList<Node> neighbors= new ArrayList<>();
}
