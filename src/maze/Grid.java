package maze;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grid extends JPanel {

    class GridNode {

        boolean hasLeft, hasRight, hasUp, hasDown;
        void print(){
            System.out.println("left: "+hasLeft);
            System.out.println("right: "+hasRight);
            System.out.println("up: "+hasUp);
            System.out.println("down: "+hasDown);
        }
    }
    private boolean[][] squares;
    final int size;
    Maze maze;
    GridNode[][] gridNodes;

    public Grid(Maze maze) {
        this.maze = maze;
        size = maze.getSize();
        gridNodes = new GridNode[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                gridNodes[y][x] = new GridNode();//tworzenie wierzcholka siatki do wyswietlania
                Node n = maze.getNodes()[x][y]; 
                
                //dodawanie scian
                for (Node neighbor : n.neighbors) {
                    if (neighbor.position.x > n.position.x) {
                        gridNodes[y][x].hasRight = true;
                        
                    }
                    if (neighbor.position.x < n.position.x) {
                        gridNodes[y][x].hasLeft = true;
                    }
                    if (neighbor.position.y < n.position.y) {
                        gridNodes[y][x].hasUp = true;
                    }
                    if (neighbor.position.y > n.position.y) {
                        gridNodes[y][x].hasDown = true;
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //get width and heigth
        int heigth = (int) getSize().getHeight();
        int width = (int) getSize().getWidth();

        //draw rectangles and fill them
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int cellX = ((width - 10) / size) * x + 5;
                int cellY = ((heigth - 10) / size) *y + 5;

                
                //koloruj drogi labiryntu
                if (maze.getNodes()[x][y].type == null) {
                    g.setColor(Color.blue);
                    g.fillRect(cellX, cellY, ((width - 10) / size), ((heigth - 10) / size));
                }
                //koloruj sciezkie szukania
                if (maze.getNodes()[x][y].type == Node.Types.PATH) {
                    g.setColor(Color.yellow);
                    g.fillRect(cellX, cellY, ((width - 10) / size), ((heigth - 10) / size));
                }
                //koloruj sciezkie wyjscia
                if (maze.getNodes()[x][y].type == Node.Types.ESCAPE_PATH) {
                    g.setColor(Color.green);
                    g.fillRect(cellX, cellY, ((width - 10) / size), ((heigth - 10) / size));
                }
                
                //koloruj poczatek i koniec
                if(maze.getNodes()[x][y].type == Node.Types.START || maze.getNodes()[x][y].type == Node.Types.END){
                    g.setColor(Color.red);
                    g.fillRect(cellX, cellY, ((width - 10) / size), ((heigth - 10) / size));
                }
                
                
                
                //edges
                g.setColor(Color.black);
                
               //x,y 
                if ((!gridNodes[y][x].hasLeft) || x==0) {
                    g.drawLine(cellX, cellY, cellX, cellY + ((heigth - 10) / size) );
                    g.drawLine(cellX-1, cellY, cellX-1, cellY + ((heigth - 10) / size) );
                }
                if ((!gridNodes[y][x].hasRight) || x==size-1) {
                    g.drawLine(cellX+((width - 10) / size), cellY, cellX+((width - 10) / size) , cellY +((heigth - 10) / size)  );
                    g.drawLine(cellX+((width - 10) / size)+1, cellY, cellX+((width - 10) / size) +1 , cellY +((heigth - 10) / size)  );
                }
                if (!gridNodes[y][x].hasDown || y==size-1) {
                    g.drawLine(cellX, cellY + ((heigth - 10) / size), cellX + ((width - 10) / size) , cellY + ((heigth - 10) / size) );
                    g.drawLine(cellX+1, cellY + ((heigth - 10) / size)+1, cellX + ((width - 10) / size) +1, cellY + ((heigth - 10) / size)+1);
                    //g.drawLine(cellX+2, cellY + ((heigth - 10) / size)+2, cellX + ((width - 10) / size) +2, cellY + ((heigth - 10) / size)+2);
                }
                if ((!gridNodes[y][x].hasUp) || y==0) {
                   g.drawLine(cellX, cellY, cellX + ((width - 10) / size) , cellY);
                   g.drawLine(cellX, cellY-1, cellX + ((width - 10) / size), cellY-1);
                   //g.drawLine(cellX, cellY+2, cellX + ((width - 10) / size) , cellY+2);
                }
            }
        }
    }

    public void setAsPath(int x, int y) {
        maze.getNodes()[x][y].type = Node.Types.PATH;
        repaint();
    }
    public void setAsEscapePath(int x, int y) {
        maze.getNodes()[x][y].type = Node.Types.ESCAPE_PATH;
        
    }

}
