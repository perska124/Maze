package maze;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Krzysztof on 2016-06-04.
 */
public class searchForESC {

    public void BFS(Node Start) {
        System.out.println("BFS");
        Node v = Start;
        Node u = null;
        Queue<Node> Q = new PriorityQueue<>();
        Q.add(v);
        Start.visited = true;
        int max = 0;

        outerloop:
        while (!Q.isEmpty()) {
            v = Q.poll();
            for (int i = 0; i < v.neighbors.size(); i++) {

                u = v.neighbors.get(i);
                max = Math.max(u.position.x, u.position.y);
                if (u.type == Node.Types.END) {
                    System.out.println("START at: X= " + Start.position.x + " Y= " + Start.position.y);
                    System.out.println("END at: X= " + u.position.x + " Y= " + u.position.y);
                    u.parent = v;
                    break outerloop;
                }
                if (!u.visited) {
                    u.parent = v;
                    Q.add(u);
                    u.visited = true;
                }
            }
        }

        int[][] tab = new int[max + 1][max + 1];
        tab[u.position.x][u.position.y] = 3;
        System.out.println(u.position.x + " " + u.position.y);
        while (Start != u) {
            if (u.parent.position.x > max || u.parent.position.y > max) continue;
            else {
                tab[u.parent.position.x][u.parent.position.y] = 1;
            }
            System.out.println(u.parent.position.x + " " + u.parent.position.y);
            u = u.parent;
        }

        tab[Start.position.x][Start.position.y] = 2;

        for (int[] aTab : tab) {
            for (int anATab : aTab) {
                if (anATab == 1) System.out.print("+");
                else if (anATab == 2) System.out.print("S");
                else if (anATab == 3) System.out.print("K");
                else System.out.print("-");
            }
            System.out.println();
        }
    }
}


