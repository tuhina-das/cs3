package src;
import java.util.ArrayList;
import java.util.List;

public abstract class MazeSolver {

    private Maze maze;
    private boolean solved;
    private boolean solvable;

    public MazeSolver(Maze maze){
        this.maze=maze;
        makeEmpty();
        add(maze.getStart());
        solved=false;
        solvable=true;
    }

    public abstract void makeEmpty();
    public abstract boolean isEmpty();
    public abstract void add(Square s);
    public abstract Square next();

    public boolean isSolved(){
        if (isEmpty()){
            solved=true;
            solvable=false;
        }
        //if maze exit is in the stack
        else if (!solvable){
            solved=true;
        }
        return solved;
    }

    public void step(){
        //Is the worklist empty? If so, solvable=false & solved=true
        if (isEmpty()){
            solvable=false;
            solved=true;
        }
        else{
            //grab the "next" location
            Square temp = next();

            //check if it's the exit
            if (temp==maze.getExit()){
                solved=true;
            }
            else{
                //if not, then get all adjacent locations
                List<Square> neighbors = maze.getNeighbors(temp);


                //iterate through neighbors
                for (int i = 0; i < neighbors.size(); i++) {
                    Square t = neighbors.get(i);
                    //check if neighbors aren't walls + have not been explored
                    if (t.getType()!=Square.WALL && t.getStatus()!=Square.EXPLORED){
                        if (t==maze.getExit()){
                            solved=true;
                        }
                        else{
                            //if they pass the conditions, update their status
                            t.setStatus(Square.WORKING);
                            //visually update on maze
                            int r = t.getRow();
                            int c = t.getCol();
                            maze.maze[r][c] = t;
                            add(t);
                        }
                    }
                    else{

                    }
                }
                //after iterating and adding, next square has been explored.
                temp.setStatus(Square.EXPLORED);

                //now update the maze
                //get row and col of temp (next square)
                int r = temp.getRow();
                int c = temp.getCol();
                maze.maze[r][c]=temp;
            }
        }


    }

    public String getPath(){

        if (!solvable){
            return "Unsolvable";
        }
        else if (solved){
            return "Solved";
        }
        else return "Not yet solved";

    }

    public void solve(){
        //call step while not solved
        while (!solved){
            step();
        }
    }
}