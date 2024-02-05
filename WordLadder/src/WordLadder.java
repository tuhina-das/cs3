import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class WordLadder {
    public static void main(String[] args) throws FileNotFoundException {

        //scan input
        Queue<String> inputs = initFinalladders(new File("input.txt"));

        //run the program for each pair of inputs and outputs
        while (!inputs.isEmpty()){
            //load dictionary
            File dictionary = new File("dictionary.txt");
            Scanner shrey = new Scanner(dictionary);

            //scan the dictionary into a hashset
            HashSet<String> dict = new HashSet<>();
            while (shrey.hasNext()) {
                dict.add(shrey.nextLine().toLowerCase());
            }

            String initWord = inputs.poll();
            String finalWord = inputs.poll();

            //if initword or finalword isn't a word in the dictionary, skip
            if (!dict.contains(initWord)){
                System.out.println("There is no word ladder for " + initWord + " and " + finalWord);
                continue;
            }

            Queue<Stack<String>> ladders = new LinkedList<>();
            Stack<String> top = new Stack<>();
            top.push(initWord);
            ladders.offer(top);

            boolean found = false; //boolean to track whether a ladder has been found or not

            //while queue is not empty
            while (!ladders.isEmpty()){
                Stack<String> temp = ladders.poll();
                if (temp.peek().equals(finalWord)){
                    //print the stack
                    System.out.println("Found a ladder! >>> " + temp);
                    found=true;
                    break;
                }
                else{
                    //Otherwise, find all ladders one letter different from it.
                    ArrayList<String> diffs = oneOffs(temp.peek(), dict);
                    for (String word: diffs){
                        Stack<String> temp2 = (Stack<String>) temp.clone();
                        temp2.push(word);
                        ladders.offer(temp2);
                    }
                }
            }

            if (!found){ //if a word hasn't been found, then print that there's no ladder
                System.out.println("There is no word ladder for " + initWord + " and " + finalWord);
            }



        }


    }


    //loop to scan file into a 2d array
    public static Queue<String> initFinalladders(File file) throws FileNotFoundException{
        Scanner arin = new Scanner(file);
        Queue<String> result = new LinkedList<>();
        while (arin.hasNextLine()){
            result.offer(arin.next());
        }
        return result;
    }


    /*Get the starting word and search through the dictionary to find all ladders that
    differ by one letter
    //helper method to do that*/

    //search through dictionary to find all ladders that differ by one letter
    //create a loop to get one-off variations of our initial word, then use .contains to populate an arraylist with these vals
    public static ArrayList<String> oneOffs(String initWord, HashSet<String> dict) {
        ArrayList<String> works = new ArrayList<>(); //list of all one-off ladders found in the dictionary
        for  (int i = 0; i < initWord.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String x = initWord.substring(0, i) + c + initWord.substring(i + 1);
                //now check the dictionary to see if it contains. if yes, add to arraylist. remove the word. else cont.
                if (dict.contains(x)) {
                    works.add(x);
                    dict.remove(x);
                }
            }
        }
        return works;
    }
}

