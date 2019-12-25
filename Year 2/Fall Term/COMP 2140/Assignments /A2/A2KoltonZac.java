import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
/**
 * A2KoltonZachary
 * 
 * COMP 2140 SECTION A02
 * Instructor: Mr. Guderian 
 * Assignment #2
 * @ZacKolton7838513
 * @Oct26/2018
 * 
 * Purpose: To run/test class set
 * 
 */
public class A2KoltonZac
{
    /**
     * PURPOSE: 
     *      - This is the main method that calls test(String) in class Set
     *      - It will ask the user to type in the file to test
     *      - The user must also type in the file type (.txt)
     */
    public static void main(String[] args)
    {
        Set a = new Set();
        System.out.println("Please type in file name including file type (.txt)");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        a.test(input);
        System.out.println("Program end");
    }
}

/**
 * A2KoltonZachary
 * 
 * COMP 2140 SECTION A02
 * Instructor: Mr. Guderian 
 * Assignment #2
 * @ZacKolton7838513
 * @Oct26/2018
 * 
 * Purpose: 
 *          - Class that implements a set of integers using an ordered circular linked list with a dummy node. Such
 *            a set is stored in an array of sets.
 *          - Each set is initalized/modified through various commands given by a text file.
 *          - Each set does not have any two integeres that are the same.
 *          
 * 
 */
class Set
{
    public static final int OPERATION = 0; //I,P,U,D, or \ position in the text file
    public static final int SET_POS1  = 1; //set position in array of sets in the text file
    public static final int SET_POS2  = 2; //set position in array of sets in the text file
    public static final int SET_POS3  = 3; //set position in array of sets in the text file

    public static final int VALUE_POS = 2; //position of number to insert/delete in the text file

    private Node first;

    /**
     * PURPOSE: Node class
     */
    private class Node
    {
        public int value;
        public Node next;
        public Node (int val) {
            value = val;
        }

        public Node (int val, Node n) {
            value = val;
            next  = n;
        }
    }

    /**
     * PURPOSE: 
     *      - Create a set with only one Node (the dummy node)
     *      - Make a circular linked list
     */
    public Set() {
        first = new Node(Integer.MAX_VALUE);
        first.next = first;
    }

    /**
     * PURPOSE: 
     *      - This is a test function that calls all the functions
     *      - The array is initalize here with the correct size provided from file input in class A2KoltonZac
     *        or otherwise known as the test class
     *      - The call to makeSets(Set[],int) creates/initializes the indiviual empty circular linked list sets 
     *      - The call to doOperations(Set[],String) performs all the operations that modifies the individual sets
     */
    public void test(String file)
    {
        int arraySize = initArraySize(file);
        Set[] sets = new Set[arraySize];
        makeSets(sets,arraySize);
        doOperations(sets,file);
    }

    /**
     * PURPOSE: 
     *      - This function initializes each set in the set array s
     */
    private void makeSets(Set[] s,int size)
    {
        for(int i = 0; i<size; i++)
        {
            s[i] = new Set();
        }
    }

    /**
     * PURPOSE: 
     *      - This function recieves a array of sets (Set[] s) and a file passed from the test function
     *      - The file contains operations and modifications to be performed on certain sets in the array of sets
     *      - Scanner is used to read the lines of the file
     *      - However, we skip the first line in the file since it declares the size of the array of sets, which
     *        done by using a counter (lineCount) and checking if it greater than 0 (the first line)
     *          - Im assuming i could have just made String line equal to the next line right after using 
     *            in.nextLine() again, but that is just what made sense to me to do at the time.
     *      - A message is returned if the file was spelt wrong or is missing.
     *      
     * Commands:
     *      - Print:     This prints out the selected set
     *      - Insert:    This inserts a new integer (in order) into a specific set
     *      - Delete:    This deletes the node that contains a specific integer from a specific set
     *      - Union:     This takes two sets and finds what the Union set is, then overwrites a third set 
     *                   to place the union into 
     *      - Difference:This takes two sets and finds what the Difference set is, then overwrites a third set
     *                   to place the difference into
     *                   
     * Pre Defined Variables: 
     *                  - These state the column (in the file)/the position in the String[] 
     *                    parts array. 
     *                  - They are only used when they are applicable with respect to each command
     *                  
     *      - OPERATION:   
     *                - The first column in the file -> 0
     *                - This is always first
     *                
     *      - SET_POS1:  The array in the 2nd column/the position in the String[] parts array -> 1. 
     *      - SET_POS2:  The array in the 3rd columm/the position in the String[] parts array -> 2.
     *      - SET_POS3:  The array in the 4th column/the position in the String[] parts array -> 3.
     *      - VALUE_POS: The Integer in the 2 column which will either be deleted or inserted 
     *                   dependng on the command/the position in the String[] parts array     -> 1.
     */
    private void doOperations(Set[] s,String file)
    {

        Scanner in = null;
        int lineCount = 0;  // to count the line number
        try {
            in = new Scanner((new FileReader(file)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found or file name incorrect\nMessage: "+e.getMessage());
        }

        while (in.hasNextLine()) 
        {
            String line = in.nextLine();
            String[] parts = line.split("\\s+");
            if(lineCount > 0)
            {
                if(parts[OPERATION].equals("P"))                     //Print command
                {
                    int setPos = Integer.parseInt(parts[SET_POS1]);
                    s[setPos].print(setPos);
                }
                else if(parts[OPERATION].equals("I"))               //Insert command
                {
                    int setPos  = Integer.parseInt(parts[SET_POS1]);
                    int newValue= Integer.parseInt(parts[VALUE_POS]);
                    s[setPos].insert(newValue);
                }
                else if(parts[OPERATION].equals("D"))               //Delete command
                {
                    int setPos  = Integer.parseInt(parts[SET_POS1]);
                    int deleteVal = Integer.parseInt(parts[VALUE_POS]);
                    s[setPos].delete(deleteVal);
                }
                else if(parts[OPERATION].equals("\\"))             //Difference command
                {
                    int setPos1    = Integer.parseInt(parts[SET_POS1]);
                    int setPos2    = Integer.parseInt(parts[SET_POS2]);
                    int diffSetPos = Integer.parseInt(parts[SET_POS3]);
                    s[diffSetPos]  = difference(s[setPos1],s[setPos2]);
                }
                else if(parts[OPERATION].equals("U"))             //Union command
                {
                    int setPos1     = Integer.parseInt(parts[SET_POS1]);
                    int setPos2     = Integer.parseInt(parts[SET_POS2]);
                    int unionSetPos = Integer.parseInt(parts[SET_POS3]);
                    s[unionSetPos] = union(s[setPos1],s[setPos2]);
                }
            }
            lineCount++;
        }
        in.close(); //once done with the file it is closed
    }

    /**
     * PURPOSE: 
     *      - This function gets the size of the array front he first line in the file
     *      - It will print out a message if the file is spelt wrong or missing
     */
    private static int initArraySize(String file)
    {
        Scanner in = null;
        try {
            in = new Scanner((new FileReader(file)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found or file name incorrect\nMessage: "+e.getMessage());
        }
        // read line
        String line = in.nextLine();
        Scanner row = new Scanner (line);
        //just get the first line to get size of the Set[] array
        int size = row.nextInt();
        row.close();
        in.close();
        return size;
    }   

    /**
     * PURPOSE: 
     *      - This function prints out the set
     *      - The set number is recieved for more clarity when viewing which set is which once the program
     *        is run
     *      - It will only print out 10 integers per line
     *          - It checks how many there are by using a count
     */
    public void print(int setNum)
    {
        Node curr = first.next;
        String s ="{";
        int count = 0;
        while(curr.value < Integer.MAX_VALUE)
        {
            if(curr.next != first)
            {
                if(count >= 10)
                {
                    s+="\n "+curr.value+",";
                    count = 0;
                }
                else
                {
                    s += curr.value+ ",";
                }
            }
            else
            {
                if(count >= 10)
                {
                    s+="\n"+curr.value+"";
                    count = 0;
                }
                else
                {
                    s += curr.value+ "";
                }
            }
            curr = curr.next;
            count++;
        }
        System.out.println("Set "+setNum+"\n"+s+"}\n-----------");
    }

    /**
     * PURPOSE: 
     *      - This function inserts a value into a set in order
     *      - It also checks whether that value is already in there or not
     *      - It will not insert the value if it is already in the set
     */
    public void insert(int value)
    {
        //prev is the dummy node at the start
        //curr is the node after the dummy node
        Node prev = first;
        Node curr = first.next;
        while((curr.value < value) && (curr != first))
        {
            prev = curr; 
            curr = curr.next;
        }

        if(curr.value != value)
        {
            prev.next = new Node(value,curr);
        }

    }

    /**
     * PURPOSE: 
     *      - This function creates a new set
     *      - The set created is a union of setA and setB
     */
    public Set union(Set setA,Set setB)
    {
        Set result = new Set();
        //pointers from setA,setB
        Node currA = setA.first.next;
        Node currB = setB.first.next;
        //pointers for the result set 
        Node prevNew = result.first;
        Node currNew = result.first.next;
        while((currA.value < Integer.MAX_VALUE) || (currB.value < Integer.MAX_VALUE))
        {
            if(currA.value < currB.value)
            {
                if(currA.value != prevNew.value)
                {
                    prevNew.next = new Node(currA.value,currNew);
                    currA = currA.next;
                }
            }
            else if(currB.value < currA.value)
            {
                if(currB.value != prevNew.value)
                {
                    prevNew.next = new Node(currB.value,currNew);
                    currB = currB.next;
                }
            }
            else //if equal
            {
                if(currA.value != prevNew.value)
                {
                    prevNew.next = new Node(currA.value,currNew);
                    currB = currB.next;
                    currA = currA.next;
                }
            }
            prevNew = prevNew.next;
        }
        return result;
    }

    /**
     * PURPOSE: 
     *      - This function creates a new set
     *      - The new set created is a difference of two sets: setA and setB
     */
    public Set difference(Set setA,Set setB)
    {
        Set result = new Set();
        //pointers from setA,setB
        Node currA = setA.first.next;
        Node currB = setB.first.next;
        //pointers for the set result 
        Node prevNew = result.first;
        Node currNew = result.first.next;
        while((currA.value < Integer.MAX_VALUE) || (currB.value < Integer.MAX_VALUE))
        {
            if(currA.value < currB.value)
            {
                if(currA.value != prevNew.value)
                {
                    prevNew.next = new Node(currA.value,currNew);
                    prevNew = prevNew.next;
                }
                currA = currA.next;
            }
            else if(currA.value > currB.value)
            {
                currB = currB.next;
            }
            else //if equal
            {
                currA = currA.next;
                currB = currB.next;
            }
        }
        return result;
    }

    /**
     * PURPOSE: 
     *      - This function deletes a value from a set
     *      - If the value is not in the list it will do nothing 
     *          - Thanks to (curr.value < value), if the loop reaches the dummy node it will end
     */
    public void delete(int value)
    {
        if(value == Integer.MAX_VALUE)
        {
            System.out.println("Can't delete the dummy node");
            return;
        }
        else
        {
            //prev is the dummy node at the start
            //curr is the node after the dummy node
            Node prev = first;
            Node curr = first.next;
            while(curr.value < value)
            {
                prev = prev.next;
                curr = curr.next;
            }
            prev.next = curr.next;
        }
    }
}

