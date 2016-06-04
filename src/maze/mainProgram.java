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
       MazeGenerator mg=new MazeGenerator();
       Maze maze = mg.generateMaze(0, 0, 4);
       maze.printMazeAsAdjacecyList();
       maze.printMaze();

        BFS bfs = new BFS();
        bfs.searchForESC(maze.getStartingPoint());
    }
}
