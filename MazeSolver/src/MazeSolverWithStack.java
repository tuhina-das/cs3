package src;
public class MazeSolverWithStack extends MazeSolver{

    MyStack worklist;
    public MazeSolverWithStack(Maze maze){
        super(maze);
    }
    @Override
    public void makeEmpty() {
        //creates a new, empty worklist
        MyStack w = new MyStack();
        worklist=w;
    }

    @Override
    public boolean isEmpty() {
        //checks if the worklist is empty
        if (worklist.isEmpty()){
            return true;
        }
        else return false;
    }

    @Override
    public void add(Square s) {
        worklist.push(s);
    }

    @Override
    public Square next() {
        //return the next item from the worklist
        return worklist.pop();
    }
}