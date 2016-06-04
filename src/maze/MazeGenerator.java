/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Damian
 */
public class MazeGenerator {

    MazeGenerator() {

    }

    public Maze generateMaze(int startX, int startY, int size) {

        //defining var before
        int mazeSize = size;
        Node[][] Maze = new Node[mazeSize][mazeSize]; //0 - wall, 1 - path, 2 -starting point
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                Maze[i][j] = new Node(i, j);
            }
        }
        ArrayList<Node> walls = new ArrayList<>();   //list of walls around node

        //start alghorithm
        Node start = Maze[startX][startY];
        start.visited = true;
        start.type = Node.Types.START;
        if (start.position.x - 1 >= 0) {
            walls.add(Maze[start.position.x - 1][start.position.y]);
            Maze[start.position.x - 1][start.position.y].parent = start;
        } //left
        if (start.position.x + 1 < mazeSize) {
            walls.add(Maze[start.position.x + 1][start.position.y]); //right
            Maze[start.position.x + 1][start.position.y].parent = start;
        }
        if (start.position.y - 1 >= 0) {
            walls.add(Maze[start.position.x][start.position.y - 1]); //up
            Maze[start.position.x][start.position.y - 1].parent = start;
        }
        if (start.position.y - 1 < mazeSize) {
            walls.add(Maze[start.position.x][start.position.y + 1]); //down
            Maze[start.position.x][start.position.y + 1].parent = start;
        }

        while (!walls.isEmpty()) {

            int i = new Random().nextInt((walls.size()));
            Node n = walls.get(i);
            //System.out.println(n.position.x + " " + n.position.y);

            if (n.visited == false) {
                n.visited = true;
                n.parent.neighbors.add(n);
                n.neighbors.add(n.parent);

                //add neighbor walls
                if (n.position.x - 1 >= 0) {    //left
                    walls.add(Maze[n.position.x - 1][n.position.y]);
                    Maze[n.position.x - 1][n.position.y].parent = n;
                }
                if (n.position.x + 1 < mazeSize) {  //right
                    walls.add(Maze[n.position.x + 1][n.position.y]);
                    Maze[n.position.x + 1][n.position.y].parent = n;
                }
                if (n.position.y - 1 >= 0) {    //up
                    walls.add(Maze[n.position.x][n.position.y - 1]);
                    Maze[n.position.x][n.position.y - 1].parent = n;
                }
                if (n.position.y + 1 < mazeSize) {  //down
                    walls.add(Maze[n.position.x][n.position.y + 1]);
                    Maze[n.position.x][n.position.y + 1].parent = n;
                }
            }
            walls.remove(n);
        }

        //create random end
        Node endPoint;
        do {
            int x = new Random().nextInt(mazeSize);
            int y = new Random().nextInt(mazeSize);
            endPoint = Maze[x][y];
        } while (endPoint.type == Node.Types.START); //if end point==start point repeat
        endPoint.type = Node.Types.END;

        //clearing node unimportant info
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                Maze[i][j].parent=null;
                Maze[i][j].visited=false;
            }
        }
        
        return new Maze(Maze, Maze[startX][startY]);
    }

}
