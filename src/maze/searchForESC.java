package maze;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Krzysztof on 2016-06-04.
 */
public class searchForESC {

    public void BFS(Maze MyMaze, Grid grid) throws InterruptedException {
        Node Start = MyMaze.getStartingPoint(); // Pobieranie punktu startowego
        Node v = Start;     //Tworzenie zmiennych koniecznych do dzialania algorytmu
        Node u = null;
        Queue<Node> Q = new PriorityQueue<>();  // Tworzenie kolejki priorytetowej
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
                    u.parent = v;   // Przypisywanie prodzica wierdzholka u
                    Q.clear();
                    break outerloop;
                }else
                if (!u.visited) {   // Warunek nieodwiedzonego wierzcholka
                    grid.setAsPath(u.position.x,u.position.y);
                    Thread.sleep(20);
                    u.parent = v; // Przypisywanie prodzica wierdzholka u
                    Q.add(u);   // Dodawanianie wierzcholka u na koniec kolejki
                    u.visited = true;  // Ustawianie wierzcholka u jako odwiedzony
                }
            }
        } // Koniec petli algorytmu BFS


        int max = MyMaze.getSize();  // Pobieranie rozmiaru labiryntu
        int[][] tab = new int[max ][max ];    // Tworzenie tablicy na podstawie ktorej bedzie rysowana sciezka
        tab[u.position.x][u.position.y] = 3;    // Ustawianie wyscia z labiryntu
        System.out.println(u.position.x + " " + u.position.y);
        while (Start != u) {           // Petla ustalajaca sciezke wyjscia
            tab[u.parent.position.x][u.parent.position.y] = 1;  // Ustawianie kolejnych krokow do wyjsia
            System.out.println(u.parent.position.x + " " + u.parent.position.y);
            u = u.parent;   // Ustawianie rodzica wierzcholka u jako wierzcholek roboczy
            if(u.type!=Node.Types.START && u.type!=Node.Types.END) grid.setAsEscapePath(u.position.x, u.position.y);
        }
        grid.repaint();

        tab[Start.position.x][Start.position.y] = 2;    // Dodawanie wejscia do labiryntu

//        for (int[] aTab : tab) {    // Petla po tablicy wypisujaca sciezke wyjscia z labiryntu
//            for (int anATab : aTab) {
//                if (anATab == 1) System.out.print("+");
//                else if (anATab == 2) System.out.print("S");
//                else if (anATab == 3) System.out.print("K");
//                else System.out.print("-");
//            }
//            System.out.println();
//        }
        
        for (int j = 0; j < tab.length; j++) {
            for (int  i= 0; i < tab[j].length; i++) {
                if (tab[i][j] == 1) System.out.print("+");
                else if (tab[i][j] == 2) System.out.print("S");
                else if (tab[i][j] == 3) System.out.print("K");
                else System.out.print("-");
            }
            System.out.println();
        }
    }
}


