package src;

import java.util.Objects;

public class Square
{
    //These values are used to denote what type of Square this is in the maze
    final static int EMPTY = 0; //an empty space
    final static int WALL  = 1; //a wall
    final static int START = 2; //the start
    final static int EXIT  = 3; //the exit

    //These values indicate the status of a particular Square while the maze is being solved, for the GUI
    final static char WORKING      = 'o'; //currently on the work list (the stack)
    final static char EXPLORED     = '.'; //done, already explored
    final static char ON_EXIT_PATH = 'x'; //on the exit path, used in a later lab
    final static char UNKNOWN      = '_'; //not known / visited yet (empty cells only)

    private int  row, col; //r, c location in the maze
    private int  type;     //type of this square, e.g. empty, wall, etc.
    private char status;   //the status of a room being explored, shown by the GUI

    public Square(int row, int col, int type){
        this.row=row;
        this.col=col;
        this.type=type;
    }
    public String toString(){
//    int t = this.type;
//    if (t==EMPTY){
//       //check status
//       if (this.status==UNKNOWN)
//          return ""+UNKNOWN;
//       else if (this.status==EXPLORED){
//          return ""+EXPLORED;
//       }
//       else if (this.status==WORKING)
//          return ""+WORKING;
//    }
//    else if (t==WALL){
//       return "#";
//    }
//    else if (t==START){
//
//    }
        switch(this.type){
            case EMPTY:
                if (this.status==WORKING){
                    return "o";
                }
                else if (this.status==EXPLORED){
                    return ".";
                }
                else return ""+UNKNOWN;
            case WALL: return "#";
            case START: return "S";
            case EXIT: return "E";
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return row == square.row && col == square.col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getType() {
        return type;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    //reset method to erase status
    //TODO: do we change to unknown?
    public void reset(){
        this.status = UNKNOWN;
    }
}