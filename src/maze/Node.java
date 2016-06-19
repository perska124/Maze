/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.ArrayList;

//klasa reprezentujaca punkt w labiryncie
public class Node  implements Comparable<Node>{
    public Node(int x, int y){
        this.position.x=x;
        this.position.y=y;
    }

    @Override
    public int compareTo(Node o) {
        return 1;
    }
    //pozycja w tablicy 2d labiryntu
    class Position {
        public int x = 0;
        public int y = 0;
    }
    public Position position=new Position();

    enum Types {    //typy - sciezka, start, koniec
        PATH, START, END, ESCAPE_PATH
    }
    public Types type = null;
    public Node parent; //who was parent during creation during Prim alghorithm
    public boolean visited = false; //changed during creation, needs to be cleared before doing another alghorithm
    public ArrayList<Node> neighbors= new ArrayList<>();
}
