package maze;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Krzysztof on 2016-06-04.
 */
public class searchForESC {

    public void BFS(Maze MyMaze, Grid grid) throws InterruptedException {
        Node Start = MyMaze.getStartingPoint(); // Pobieranie punktu startowego
        Node v;     //Tworzenie zmiennych koniecznych do dzialania algorytmu
        Node u = null;
        Queue<Node> Q = new PriorityQueue<>();  // Tworzenie kolejki
        Q.add(Start);   // Dodawanie punktu startowego na poczatek kolejki
        Start.visited = true;   // Ustawianie pukntu startowego jako odwiedzony

        outerloop:
        while (!Q.isEmpty()) { // Poczatke petli algorytmu BFS
            v = Q.poll();   // Pobieranie i usuwanie z poczatktu kolejki wierzcholka
            for (int i = 0; i < v.neighbors.size(); i++) {  // Poczatek petli po wszystkich sasiadach wiecholka v
                u = v.neighbors.get(i); // Pobieranie kolejnych sasiadow wierzcholka v
                if (u.type == Node.Types.END) { // Warunek wyjscia z labiryntu
                    System.out.println("START at: X= " + Start.position.x + " Y= " + Start.position.y);
                    System.out.println("END at: X= " + u.position.x + " Y= " + u.position.y);
                    u.parent = v;   // Przypisywanie prodzica wierzcholka u
                    Q.clear();
                    break outerloop;
                }else
                if (!u.visited) {   // Warunek nieodwiedzonego wierzcholka
                    grid.setAsPath(u.position.x,u.position.y);
                    Thread.sleep(1);   //Czekanie w celu przedstawienie działania programu
                    u.parent = v; // Przypisywanie prodzica wierdzholka u
                    Q.add(u);   // Dodawanianie wierzcholka u na koniec kolejki
                    u.visited = true;  // Ustawianie wierzcholka u jako odwiedzony
                }
            }
        } // Koniec petli algorytmu BFS


        while (Start != u) {           // Petla ustalajaca sciezke wyjscia

            u = u.parent;   // Ustawianie rodzica wierzcholka u jako wierzcholek roboczy
            if(u.type!=Node.Types.START && u.type!=Node.Types.END) {
                grid.setAsEscapePath(u.position.x, u.position.y); //Ustawianie sciezki wyjscia
            }
        }
        grid.repaint();
    }
}


