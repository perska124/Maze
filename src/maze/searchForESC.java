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
        while (!Q.isEmpty()) {
            v = Q.poll();
            for (int i = 0; i < v.neighbors.size(); i++) {

                u = v.neighbors.get(i);
                if (u.type == Node.Types.END) {
                    System.out.println("START at: X= " + Start.position.x + " Y= " + Start.position.y);
                    System.out.println("END at: X= " + u.position.x + " Y= " + u.position.y);
                    return;
                }
                if (u.visited == true) {
                } else {
                    u.parent=v;
                    Q.add(u);
                    u.visited = true;
                }
            }

        }
        while(Start.position.x!=u.position.x && Start.position.y!=u.position.y){
            System.out.println(u.parent);
            u = u.parent;
        }
    }
}


