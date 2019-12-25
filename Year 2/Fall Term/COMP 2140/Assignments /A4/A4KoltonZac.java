import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
 * A4KoltonZac
 * COMP 2140  SECTION A02
 * INSTRUCTOR Guderian (A02)
 * ASSIGNMENT #4
 * Zachary Kolton 7838513
 * Nov. 22nd 2018
 *
 * PURPOSE: Open Lenert Programs from text files and parse them into executable programs through the implementation of a BST
 */
public class A4KoltonZac
{
    /**
     * PURPOSE: 
     *      - This is the main method that calls lenertTest(String) 
     *      - It will ask the user to type in the file to test
     *      - The user must also type in the file type (.txt)
     */
    public static void main(String[] args)
    {
        System.out.println("COMP 2140 ASSIGNMENT 4: Executing Lenert Programs");
        System.out.println("---------------------------------------------------\n");
        System.out.println("Please type in file name including file type (.txt)");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        try{
            Scanner in = null;
            in = new Scanner((new FileReader(input)));
            System.out.println("\n---------------------------------------------------");
            lenertTest(input);
            System.out.println("Program ended normally");
        }catch(FileNotFoundException fn)
        {
            System.out.println(fn.getMessage());
            System.out.println("File was entered wrong or '.txt' may have not been added");
        }
    }

    /**
     * PURPOSE: 
     *      - Clears up code 
     *      - Calls the lenert parsing program in class Table
     */
    private static void lenertTest(String file)
    {
        Table table = new Table();
        table.lenert(file);
    }
}

/**
 * Table
 * COMP 2140  SECTION A02
 * INSTRUCTOR Guderian (A02)
 * ASSIGNMENT #4
 * Zachary Kolton 7838513
 * Nov. 22nd 2018
 *
 * PURPOSE: 
 *      - Class that implements a BST in a Table
 *      - Parses Lenert files and inserts them into a BST (or "Table")
 * 
 * VARIABLES:
 *      - MAX_STRING: Initalizes the errorList length
 *      - errors
 *          - Counts the amount of errors found/created throughout the Lenert program 
 *          - Could be subsituted for "size" in a partially filled array
 *      - errorList
 *          - Holds "errors" amount of errors in a partially filled array 
 *          - Once an error is created, it is added to the array
 *      
 */
class Table
{
    public static final int MAX_STRING = 100;
    private int errors  = 0;
    private String[] errorList = new String[MAX_STRING];
    /**
     * Node
     * COMP 2140  SECTION A02
     * INSTRUCTOR Guderian (A02)
     * ASSIGNMENT #4
     * Zachary Kolton 7838513
     * Nov. 22nd 2018
     *
     * PURPOSE: 
     *      - Normal (private) Node class
     *      - Each Node contains a variable (or "VariableRecord")
     */
    private class Node {
        public Node left;
        public Node right;

        public VariableRecord variable;
        public Node(VariableRecord v) {
            variable = v;
        }
    }
    private Node root;

    /**
     * PURPOSE: Create an empty table
     */
    public Table()
    {
        root = null;
    }

    /*****************************************************Lenert Functions****************************************************/

    /**
     * Name: lenert
     * PURPOSE:
     *      - Parse a Lenert file in an executable
     * Variables:
     *      - in: basic Scanner
     *      - lineCount
     *          - Counts the lines from the .txt file
     *          - Only to skip over the first line initalizing the amount of Lenert files
     *      - progNum
     *          - Later used to compare with fileCount to make sure all the files were parsed
     *          - Identifies which file is being executed 
     *      - isError
     *          - Errors will only be printed if true
     *          - Made to clean output
     *              - Instead of seeing "Error Message:" every time (even if there isnt an error)
     *      - fileCount
     *          - Gets number of files from the .txt file 
     *          - Confirms all files have been computed with progNum
     *              - If they are not equal, there will be a printout "Didn't go through all the files!"
     *              
     * METHOD: (Variables in loops explained in the loop scope)
     *      - Read file line by line
     *      - Run the parsing if the length of line  and the line count is greater than zero
     *          - To skip over the line defining the number of files
     *      - Split the line (by whitespace) into a String[] of Strings (or "parts" of the line)
     *      - Identify which "part" is a variable/number/operator/end character "Q"
     *          - "Q" reprsents the end of a program, so it will print the table and then reset everything for the next program
     *      - Each part has its own procedure in private helper functions 
     *      - Reapeat till there are no more lines/programs 
     */
    public void lenert(String file)
    {
        Scanner in       = null;
        int lineCount    = 0; 
        int progNum      = 0;
        boolean isError  = false;
        try {
            in = new Scanner((new FileReader(file)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found or file name incorrect\nMessage: "+e.getMessage());
        }
        int fileCount    = amountOfFiles(file);

        while(in.hasNextLine())
        {
            String line = in.nextLine();                // Grabs the line
            if((line.length() > 0) && (lineCount > 0))
            {

                String[] parts = line.split("\\s+");    //Contians all the non-whitespace
                int right = 0;                          //Number on the far right of operator or the operand "="
                int left  = 0;                          //Number on the left side of the operator...
                //...only used if "right" is already holding a value
                int numCount = 0;                       //Counts the number of values saved 
                String operator = null;                 //Saves the operator is there is one 
                for(int i = parts.length-1; i >= 0; i--)//Go right to left through the line/parts
                {
                    if(!parts[i].equals("Q"))
                    {
                        if(isVariable(parts[i]))
                        {
                            String tempVar      = search(parts[i]);                    //Tests whether the variable is defined...
                            //...by finding it in the Table 
                            boolean variable   = (i  > 1) && (tempVar != null);        //The Variable is defined and has a value 
                            boolean error      = (i  > 1) && (tempVar == null);        //The Variable hasnt been defined yet
                            boolean assignment = (i == 0) && (parts[i+1].equals("=")); //A Variable will be created or changed
                            if(variable)
                            {
                                if(numCount == 0)       //"right" will be the first to be used                            
                                {
                                    right = getValue(tempVar);
                                }
                                else if(numCount == 1) //"left" will be the second to be used
                                {
                                    left = getValue(tempVar);
                                }
                                numCount++;
                            }
                            else if(assignment)
                            {
                                if(numCount == 1)      //Use "right" since there is only one value 
                                {
                                    assignVar(tempVar,parts[i],right);
                                }
                                if(numCount == 2)      //Use "left" and "right" since there are two values 
                                {
                                    assignVar(tempVar,parts[i],operator,left,right);
                                }
                            }
                            else if(error)
                            {
                                makeError(parts[i],line); //This creates an error and adds it to errorList
                                isError = true;           //Flags that there is an error to be printed out in this program
                            }
                        }
                        else if(isOperator(parts[i]))
                        {
                            operator = parts[i]; //Saves the operator for "assignment"
                        }
                        else if(isNumber(parts[i]))
                        {
                            if(numCount == 0)      //"right" will be the first to be used
                            {
                                right = Integer.parseInt(parts[i]);
                            }
                            else if(numCount == 1) //"left" will be the second to be used
                            {
                                left = Integer.parseInt(parts[i]);
                            }
                            numCount++;
                        }
                    }
                    else
                    {
                        printConclusion(isError,++progNum); //Cleans up code and increments the program number each time...
                        //...and prints errors if there are any
                        isError = false;                    //resets the isError flag
                        root = null;                        //resets the table using javas garbage collector 
                    }
                }
            }
            lineCount++; //to skip the first line 
        }

        if(!(progNum == fileCount))
        {
            System.out.println("Didn't go through all the files!");
        }
        in.close();
    }

    /**
     * PURPOSE: Get the amount of Lenert files in the .txt file
     */
    public int amountOfFiles(String file)
    {
        Scanner in = null;
        int result = 0;
        try {
            in = new Scanner((new FileReader(file)));
            String line = in.nextLine();
            Scanner row = new Scanner(line);
            result = row.nextInt();
            row.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found or file name incorrect\nMessage: "+e.getMessage());
        }
        return result;
    }

    /*****************************************************Error Functions****************************************************/

    /**
     * PURPOSE:
     *      - Add a new error to the errorList 
     */
    private void makeError(String varName,String line)
    {
        errorList[errors++] = "Invalid input line: variable "+"'"+varName+"'"+" is used before its declaration in "+ line;
    }

    /**
     * PURPOSE: Delete all the errors after a Lenert program is done
     */
    private void deleteErrors()
    {
        for(int i = 0; i<errors; i++)
        {
            errorList[i] = null;
        }
    }

    /*****************************************************Variable Functions****************************************************/

    /**
     * PURPOSE:
     *      - Assign a value to a variable
     *      - Add that variable to the table if it was not found (null)
     *      - No operation is passed since it is only one value 
     */
    private void assignVar(String tempVarKey,String part,int right)
    {
        if(tempVarKey == null)
        {
            tempVarKey = part;
            insert(tempVarKey,right);
        }
        else
        {
            getNode(tempVarKey).variable.assignValue(right);
        }
    }

    /**
     * PURPOSE:     
     *      - Assign a value to a variable
     *      - Add that variable to the table if it was not found (null)
     *      - Since there are more than two values 
     *          - The new value is computed from doOperation()
     */
    private void assignVar(String tempVarKey,String part,String operator,int left, int right)
    {
        int value = doOperation(operator,left,right);
        if(tempVarKey == null)
        {
            tempVarKey = part;
            insert(tempVarKey,value);
        }
        else
        {
            getNode(tempVarKey).variable.assignValue(value);
        }
    }

    /**
     * PURPOSE:
     *      - Return the integer that is made from the two values (left and right) and a specified operation
     */
    private int doOperation(String operation,int left,int right)
    {
        int result = 0;
        if(operation.equals("+"))
        {
            result = left + right;
        }
        else if(operation.equals("-"))
        {
            result = left - right;
        }
        else if(operation.equals("*"))
        {
            result = left * right;
        }
        else if(operation.equals("/"))
        {
            result  = left / right;
        }
        return result;
    }

    /*****************************************************Table Functions****************************************************/

    /**
     * PURPOSE:
     *      - Returns a Node in a table given a variable (key)
     *      - Used to assign a value to the already existing Node (variable)
     */
    private Node getNode(String varKey)
    {
        Node result = null;
        Node curr = root;
        boolean found = false;
        while(!found && curr != null)
        {
            if(curr.variable.getKey().equals(varKey))
            {
                result = curr;
                found = true;
            }
            else
            {
                if(curr.variable.isGreaterThan(varKey))
                {
                    curr = curr.left;
                }
                else
                {
                    curr = curr.right;
                }
            }
        }
        return result;
    }

    /**
     * PURPOSE:
     *      - Returns the value contained in a Node (variable)
     *      - Used to get the value from an already existing Node (variable)
     */
    private int getValue(String varKey)
    {
        int result = 0;
        Node curr = root;
        boolean found = false;
        while(!found && curr != null)
        {
            if(curr.variable.getKey().equals(varKey))
            {
                result = curr.variable.getValue();
                found = true;
            }
            else
            {
                if(curr.variable.isGreaterThan(varKey))
                {
                    curr = curr.left;
                }
                else
                {
                    curr = curr.right;
                }
            }
        }
        return result;
    }

    /**
     * PURPOSE:
     *      - Returns the key of the Node (variable) if it is in the Table
     *      - Returns null if that key does not exist in the Table
     */
    public String search(String name)
    {
        String result = null;
        Node curr = root;
        boolean found = false;
        while(!found && curr != null)
        {
            if(curr.variable.getKey().equals(name))
            {
                result = curr.variable.getKey();
                found = true;
            }
            else
            {
                if(curr.variable.isGreaterThan(name))
                {
                    curr = curr.left;
                }
                else
                {
                    curr = curr.right;
                }
            }
        }
        return result;
    }

    /**
     * PURPOSE:
     *      - Inserts a new Node (variable) into the Table 
     * Notes:   
     *      - It may seem unorthodox not to have search() in this method 
     *      - However, i did not see the point in search twice for the "name"
     *          - in the function lenert, a search is run already
     */
    public void insert(String name, int value)
    {
        if(root == null)
        {
            root = new Node(new VariableRecord(name,value));
        }
        else
        {
            Node curr = root;
            boolean added = false;
            while(!added)
            {
                if(curr.variable.isGreaterThan(name))
                {
                    if(curr.left == null)
                    {
                        curr.left = new Node(new VariableRecord(name,value));
                        added = true;
                    }
                    else
                    {
                        curr = curr.left;
                    }
                }
                else
                {
                    if(curr.right == null)
                    {
                        curr.right = new Node(new VariableRecord(name,value));
                        added = true;
                    }
                    else
                    {
                        curr = curr.right;
                    }
                }
            }
        }
    }

    /*****************************************************Boolean Functions****************************************************/

    /**
     * PURPOSE:
     *      - Return true if the input String is a vaild variable
     *          - if it starts with a lowcase letter
     *          - if it only ahs letters and numbers 
     *          - if it is atleast one character
     */
    private boolean isVariable(String part) 
    {
        boolean result = false;
        if(Character.isLowerCase(part.charAt(0)))
        {
            result = true;         //Yes this makes it a variable but...
            if(part.length() > 1)  //...we have to check if the whole variable is vaild
            {
                for(int i = 1; i< part.length(); i++)
                {
                    if(!(Character.isLetter(part.charAt(i)) || Character.isDigit(part.charAt(i))))
                    {
                        result = false; //if at any point the character isnt a letter or number...
                        //...it is not a variable
                    }
                }
            }
        }
        return result;
    }

    /**
     * PURPOSE:
     *      - Returns true if the given String is a vaild operator 
     *          - if it is either a +,-,*,/
     *          - if the length of the String is exactly 1
     */
    private boolean isOperator(String part) 
    {
        boolean result = false;
        if((part.length() == 1) && (part.equals("+") || part.equals("-") || part.equals("*") || part.equals("/")))
        {
            result = true;
        }
        return result;
    }

    /**
     * PURPOSE:
     *      - Returns true if the given String is a vaild Number 
     *          - if, for every character in the String, it is a digit
     */
    private boolean isNumber(String part) 
    {
        boolean result = true;
        for(int i = part.length()-1; i>=0; i--)  
        {
            if(!Character.isDigit(part.charAt(i)))
            {
                result = false;
            }
        }
        return result;
    }

    /*****************************************************Print out Functions****************************************************/

    /**
     * PURPOSE: 
     *      - Print out the final values of the variables in each program
     *      - The Error or Errors will on be printed out if there are any (isError flag)
     *      - Delete errors (if any) after printing the table 
     * 
     */
    private void printConclusion(boolean isError,int progNum)
    {
        System.out.println("Lenert Program #"+progNum+":\n");
        if(isError)
        {
            printErrors();
        }
        System.out.println("Final values of variables: \n");
        printTable();
        System.out.println("-----------------------");
        if(isError)
        {
            deleteErrors();
        }
    }

    /**
     * PURPOSE:
     *      - Print all the Error or Errors
     *      - Make a clean output
     *          - Instead of printing out "Error message" for every single error 
     *          - Given there is more than one error 
     */
    private void printErrors()
    {
        if(errors > 1)
        {
            System.out.println("Error Messages:");
        }
        else
        {
            System.out.println("Error Message:");
        }

        for(int i = 0; i<errors; i++)
        {
            System.out.println(errorList[i]);
        }
    }

    /**
     * PURPOSE: Bootstrap for printTable(Node)
     */
    public void printTable()
    {
        printTable(root);
    }

    /**
     * PURPOSE: Print the Table recursively 
     */
    private void printTable(Node curr) 
    {
        if(curr != null)
        {
            printTable(curr.left);
            System.out.println(curr.variable.toString());
            printTable(curr.right);
        }
    }
}

/**
 * VariableRecord
 * COMP 2140  SECTION A02
 * INSTRUCTOR Guderian (A02)
 * ASSIGNMENT #4
 * Zachary Kolton 7838513
 * Nov. 22nd 2018
 *
 * PURPOSE:
 *      - Held in a Node
 *      - Contains a key and value for the Table (BST)
 * Variables:
 *      - value: Integer value
 *      - name:  The "key" for the Node
 */
class VariableRecord
{
    int value;
    String name;

    public VariableRecord(String n,int v)
    {
        name = n;
        value = v;
    }

    /*****************************************************Functions****************************************************/

    /**
     * PURPOSE:
     *      - Helps to sort/search/insert alphabetically 
     *      - Compares String values 
     *      - if the "name" comes before "varName" in the alphabet
     *          - it will return true;
     */
    public boolean isGreaterThan(String varName)
    {
        return name.compareTo(varName) > 0;
    }

    /**
     * PURPOSE: 
     *      - Assign a new value to a variable 
     */
    public void assignValue(int val)
    {
        value = val;
    }

    public String toString()
    {
        return name +" = "+value;
    }

    /*****************************************************Get Functions****************************************************/
    public String getKey()
    {
        return name;
    }

    public int getValue()
    {
        return value;
    }
}
