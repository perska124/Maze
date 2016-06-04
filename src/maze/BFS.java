package maze;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Krzysztof on 2016-06-04.
 */
public class BFS {

    public void searchForESC(maze.Node Start){
        maze.Node v = Start;
        maze.Node u;
        Queue<maze.Node> Q = new PriorityQueue<>();
        Q.add(v);
        Start.visited = true;
        while (!Q.isEmpty()){
            v = Q.poll();
            for(int i = 0; i<v.neighbors.size(); i++){
                u = v.neighbors.get(i);

                if(u.type == Node.Types.END){
                    System.out.print("END");
                }
                if(u.visited == true){}
                else{
                    Q.add(u);
                    u.visited = true;
                }
            }
        }
    }
}
