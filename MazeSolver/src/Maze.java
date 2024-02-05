package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
    Square start;
    Square exit;
    Square[][] maze;

    //main method for testing purposes
    public static void main(String[] args) {
        Maze test = new Maze();
        test.loadMaze("maze-2");
        System.out.println(test.toString());
        System.out.println("\n" + test.getNeighbors(test.maze[6][12]));
        System.out.println(test.getStart());
        System.out.println(test.getExit());
    }

    public Maze() {
        //nothing else here. this just creates a maze.
    }

    public boolean loadMaze(String fileName) {
        try {
            //take the file
            File file = new File(fileName);
            Scanner s = new Scanner(file);
            //scan the first two ints in for the dimensions of the maze
            maze = new Square[s.nextInt()][s.nextInt()];

            //now iterate through the number of squares, based on maze size
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    //create a new square in each part of the maze
                    maze[i][j] = new Square(i, j, s.nextInt());
                    //also check if it's a start or exit square
                    if (maze[i][j].getType()==Square.START){
                        start = maze[i][j];
                    }
                    else if (maze[i][j].getType()==Square.EXIT){
                        exit=maze[i][j];
                    }
                }
            }
            //if loaded successfully, we return true
            return true;
        } catch (FileNotFoundException e) {
            //else if something isn't right, we return false
            System.out.println("Something didn't go right");
            return false;
        }
    }

    public List<Square> getNeighbors(Square s) {
        //create the list we'll return
        //TODO: we use arraylist, correct?
        ArrayList<Square> result = new ArrayList<Square>();
        //iterate around the current square if it's not null
        if (s!=null){
            int r = s.getRow();
            int c = s.getCol();
            for (int k = r - 1; k < r + 2; k++) {
                for (int l = c - 1; l < c + 2; l++) {
                    //if the given dimensions are within bounds, then add that square to the list
                    if (k >= 0 && k < maze.length) {
                        if (l >= 0 && l < maze[k].length) {
                            //make sure it's not the current square or a diagonal square
                            if (maze[k][l]!=maze[r][c]){
                                if ((k==r-1&&l==c)||(k==r&&l==c-1)||(k==r&&l==c+1)||(k==r+1&&l==c))
                                    result.add(maze[k][l]);
                            }
                        }
                    }
                    //else, skip
                }
            }
        }
        return result;
    }

    public Square getStart() {
        return this.start;
    }

    public Square getExit() {
        return this.exit;
    }

    public void reset() {
        //iterate through the maze and call each square's reset method
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j].reset();
            }
        }
    }

    public String toString() {
        String result = "";
        //iterate through the maze and print out each square's toString, IF MAZE ISNT NULL
        if (maze != null) {
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    result += maze[i][j].toString() + " ";
                }
                result += "\n";
            }
        }
        return result;
    }
}