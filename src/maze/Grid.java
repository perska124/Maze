package maze;
import javax.swing.*;
import java.awt.*;

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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridNodes[i][j] = new GridNode();
                Node n = maze.getNodes()[i][j];
                //dodawanie scian
                for (Node neighbor : n.neighbors) {
                    if (neighbor.position.x > n.position.x) {
                        gridNodes[i][j].hasRight = true;
                        
                    }
                    if (neighbor.position.x < n.position.x) {
                        gridNodes[i][j].hasLeft = true;
                    }
                    if (neighbor.position.y < n.position.y) {
                        gridNodes[i][j].hasUp = true;
                    }
                    if (neighbor.position.y > n.position.y) {
                        gridNodes[i][j].hasDown = true;
                    }
                    //System.out.println("przejscie z ["+n.position.x+" "+n.position.y+ "] do ["+neighbor.position.x+" "+neighbor.position.y+"]");
                }
                System.out.println("gridNode "+i+" "+j);
                gridNodes[i][j].print();
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int cellX = ((width - 10) / size) * j + 5;
                int cellY = ((heigth - 10) / size) * i + 5;

                //g.drawRect(cellX, cellY, (width - 10) / size, (heigth - 10) / size);
                
                //koloruj drogi labiryntu
                if (maze.getNodes()[i][j].type == null) {
                    g.setColor(Color.blue);
                    g.fillRect(cellX, cellY, ((width - 10) / size), ((heigth - 10) / size));
                }
                //koloruj sciezkie poczatek-koniec
                if (maze.getNodes()[i][j].type == Node.Types.PATH) {
                    g.setColor(Color.yellow);
                    g.fillRect(cellX, cellY, ((width - 10) / size), ((heigth - 10) / size));
                }
                //koloruj poczatek i koniec
                if(maze.getNodes()[i][j].type == Node.Types.START || maze.getNodes()[i][j].type == Node.Types.END){
                    g.setColor(Color.red);
                    g.fillRect(cellX, cellY, ((width - 10) / size), ((heigth - 10) / size));
                }
                
                
                
                //edges
                g.setColor(Color.black);
                
               //x,y 
                if (!gridNodes[j][i].hasLeft || j==0) {
                    g.drawLine(cellX, cellY, cellX, cellY + ((heigth - 10) / size) );
                }
                if (!gridNodes[j][i].hasRight || j==size-1) {
                    g.drawLine(cellX+((width - 10) / size), cellY, cellX+((width - 10) / size) , cellY +((heigth - 10) / size)  );
                }
                if (!gridNodes[j][i].hasDown || i== size-1) {
                    g.drawLine(cellX, cellY + ((heigth - 10) / size), cellX + ((width - 10) / size) , cellY + ((heigth - 10) / size) );
                }
                if (!gridNodes[j][i].hasUp || i==0) {
                   g.drawLine(cellX, cellY, cellX + ((width - 10) / size) , cellY);
                }

            }
        }
    }

    public void setAsPath(int x, int y) {
        maze.getNodes()[x][y].type = Node.Types.PATH;
        repaint();
    }

}
