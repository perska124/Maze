/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author Damian
 */
public class mainProgram {
    
    public static void main(String[] args) {
       MazeGenerator mg=new MazeGenerator();    //tworzy generator
       Maze maze = mg.generateMaze(0, 0, 10);   //generuje labirynt
       maze.printMazeAsAdjacecyList();          //wysweitla w postaci listy sasiedztwa
       maze.printMaze();                        //wyswietla w postaci siatki 2d

        searchForESC s = new searchForESC();    //tworzenie obiektu klasy szukajacej sciezki
        s.BFS(maze);    //szukanie sciezki poczatek-koniec
    }
}
