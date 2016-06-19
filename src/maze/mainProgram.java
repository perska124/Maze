/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Damian
 */
public class mainProgram extends JFrame {

    public static void main(String[] args) throws InterruptedException {
        MazeGenerator mg = new MazeGenerator();    //tworzy generator
        Maze maze = mg.generateMaze(0, 0, 100);   //generuje labirynt
        maze.printMazeAsAdjacecyList();          //wysweitla w postaci listy sasiedztwa
        //maze.printMaze();                        //wyswietla w postaci siatki 2d

        mainProgram mp = new mainProgram();
        Grid grid = new Grid(maze);
        mp.getContentPane().add(grid);
        mp.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mp.pack();
        mp.setLocationRelativeTo(null);
        mp.setVisible(true);
        mp.setMinimumSize(new Dimension(550, 550));
        grid.repaint();

        searchForESC s = new searchForESC();    //tworzenie obiektu klasy szukajacej sciezki
        s.BFS(maze,grid);    //szukanie sciezki poczatek-koniec

    }
}
