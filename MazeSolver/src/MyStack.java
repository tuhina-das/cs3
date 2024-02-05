package src;

import java.util.EmptyStackException;

public class MyStack implements StackADT {
    Square[] stack;
    int size;

    public MyStack(){
        stack = new Square[10];
        size=0;
    }

    public MyStack(int initCap){
        stack = new Square[initCap];
        size=0;
    }

    public boolean isEmpty(){
        if (size==0){
            return true;
        }
        return false;
    }

    @Override
    public void clear() {

    }

    public Square peek(){
        if (!isEmpty()){
            return stack[size-1];
        }
        else throw new EmptyStackException();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void push(Square item) {
        //check if at capacity
        if (size==stack.length){
            doubleCapacity();
            stack[size] = item;
        }
        else{
            stack[size] = item;
        }
        size++;
    }

    public Square pop(){
        if(!isEmpty()) {
            Square p = peek();
            stack[size-1]=null;
            size-=1;
            return p;
        }
        else{
            return peek();
        }
    }


    private void doubleCapacity(){
        Square[] temp = stack;
        stack = new Square[stack.length*2];
        for (int i = 0; i < temp.length; i++) {
            stack[i]=temp[i];
        }
    }

    public String toString(){
        String result = "";
        for (int i = size-1; i > -1 ; i--) {
            result += stack[i] + "\n";
        }
        result+="-----------";
        return result;
    }
}
