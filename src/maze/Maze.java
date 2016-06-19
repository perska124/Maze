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
public class Maze {
    
    private int mazeSize;   //rozmiar labiryntu
    private Node startingPoint; //punkt startowy
    private Node[][] Maze = new Node[mazeSize][mazeSize]; // tanblica z punktami labiryntu - 0 - wall, 1 - path, 2 -starting point
    Maze(Node[][] Maze,Node startingPoint){
        this.Maze=Maze;
        this.mazeSize=this.Maze.length;
        this.startingPoint=startingPoint;
    }
    //funkcja wyswietlajaca labirynt w postaci siatki
    public void printMaze() {
        
        for (int j = 0; j < mazeSize; j++) {
            for (int k = 0; k < 2; k++) { //2 times. one for walls and passages and one for 
                for (int i = 0; i < mazeSize; i++) {
                    Node n = Maze[i][j];

                    if (k == 1) {  //right
                        ArrayList<Node> list = n.neighbors;
                        boolean isPathOnRight = false;

                        System.out.print("\u25E6");   //print node 
                        
                        if (i !=mazeSize-1) { //if not last print something between 
                            for (Node right : list) {
                                if (right.position.x > n.position.x) //if there is a path to the upper node
                                {
                                    isPathOnRight = true;
                                }
                            }

                            if (isPathOnRight) {
                                System.out.print("\u25E6");    //print blank
                            } else {
                                System.out.print("\u25A0");    //print wall
                            }
                        }
                    }

                    if (k == 0 && j != 0) {    //up  j!=0 -nothing above first row , 
                        ArrayList<Node> list = n.neighbors;
                        boolean upperPath = false;
                        for (Node upper : list) {
                            if (upper.position.y < n.position.y) //if there is a path to the upper node
                            {
                                upperPath = true;
                            }
                        }

                        if (upperPath) {
                            System.out.print("\u25E6");    //print blank

                        } else {
                            System.out.print("\u25A0");    //print wall
                        }
                        
                        if (i < mazeSize - 1) {
                            System.out.print("\u25A0");   //print wall between nodes    
                        }
                    }

                }
                System.out.println();
            }

        }

    }
    //funkcja wyswietlajaca labirynt w postaci listy sasiedztwa
    public void printMazeAsAdjacecyList() {
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                System.out.println("pole: [" + i + "][" + j + "]");
                for (Node n : Maze[i][j].neighbors) {
                    System.out.println("    [" + n.position.x + "][" + n.position.y + "]");
                }
                System.out.println();
            }

        }
    }
    
    public Node[][] getNodes(){return Maze;};
    //zwraca punkt startowy
    public Node getStartingPoint(){
        return startingPoint;
    }
    //zwraca rozmiar labityntu
    public int getSize(){
        return mazeSize;
    }
}
