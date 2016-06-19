
package maze;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class mainProgram extends JFrame {

    public static void main(String[] args) throws InterruptedException {
        //Wprowadzanie rozmiaru labiryntu oraz wspolrzednych poczatka labiryntu
        Scanner in = new Scanner(System.in);
        int startX,startY,size;
        System.out.print("Podaj rozmiar labiryntu: ");
        size=in.nextInt();
        System.out.println("Podaj wspolrzedne wierzcholka startowego:");
        System.out.print("x: ");
        startX=in.nextInt();
        System.out.print("y: ");
        startY=in.nextInt();
        
        
        MazeGenerator mg = new MazeGenerator();    //tworzy generator
        Maze maze = mg.generateMaze(startX, startY, size);   //generuje labirynt
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
