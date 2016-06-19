
package maze;

import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator {

 
    //funkcja generujaca labitynt i zwracajaca obiekt typu Maze
    public Maze generateMaze(int startX, int startY, int size) {
        //ustawianie zmiennych przed algorytmem
        int mazeSize = size;
        Node[][] Maze = new Node[mazeSize][mazeSize]; //tablica wierzchołków
        for (int x = 0; x < mazeSize; x++) {
            for (int y = 0; y < mazeSize; y++) {
                Maze[x][y] = new Node(x, y);
            }
        }
        ArrayList<Node> walls = new ArrayList<>();   //list of walls around node

        //start algorytmu
        Node start = Maze[startX][startY];
        start.visited = true;   
        start.type = Node.Types.START;
        
        
        //dodajemy wierzchołki dookoła startowego
        if (start.position.x - 1 >= 0) {
            walls.add(Maze[start.position.x - 1][start.position.y]);
            Maze[start.position.x - 1][start.position.y].parent = start; //ustawiamy porpzednika wierzcholka
        } //left
        if (start.position.x + 1 < mazeSize) {
            walls.add(Maze[start.position.x + 1][start.position.y]); //right
            Maze[start.position.x + 1][start.position.y].parent = start;    //ustawiamy porpzednika wierzcholka
        }
        if (start.position.y - 1 >= 0) {
            walls.add(Maze[start.position.x][start.position.y - 1]); //up
            Maze[start.position.x][start.position.y - 1].parent = start;    //ustawiamy porpzednika wierzcholka
        }
        if (start.position.y - 1 < mazeSize) {
            walls.add(Maze[start.position.x][start.position.y + 1]); //down
            Maze[start.position.x][start.position.y + 1].parent = start;    //ustawiamy porpzednika wierzcholka
        }

        //dopoki są elementy nieodwiedzone
        while (!walls.isEmpty()) {

            int i = new Random().nextInt((walls.size()));
            Node n = walls.get(i);  //pobieramy losowy już dodany wierzchołek
            //System.out.println(n.position.x + " " + n.position.y);

            if (n.visited == false) {   //jesli nie jest odwiedzony to działamy
                n.visited = true;   //ustawiamy go jako odwiedzony
                n.parent.neighbors.add(n);  //dodajemy wierzcholek doo listy saseidztwa poprzednika
                n.neighbors.add(n.parent);  //dodajemy porpzednika do listy saseidztwa wierzchołka

                //dodajemy ściany dookoła
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
            walls.remove(n);    //usuwamy odwiedzony wierzcholek
        }

        //tworzenie losowo konca
        Node endPoint;
        do {
            int x = new Random().nextInt(mazeSize);
            int y = new Random().nextInt(mazeSize);
            endPoint = Maze[x][y];
        } while (endPoint.type == Node.Types.START); //if end point==start point repeat
        endPoint.type = Node.Types.END;

        //czyszczenie juz niepotrzebnych danych
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                Maze[i][j].parent=null;
                Maze[i][j].visited=false;
            }
        }
        
        return new Maze(Maze, Maze[startX][startY]);
    }

}
