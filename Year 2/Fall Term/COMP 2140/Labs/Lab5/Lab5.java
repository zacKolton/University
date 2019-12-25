import java.util.Scanner;
import java.io.*;

/*********************************************************************
 * Lab 5 class
 *
 * PURPOSE: Reads Lenert programs from a file and interprets them.
 *          Prints the final values of the variables after the program 
 *          is completely read in. 
 *          Uses a Table to store variable (name,value) pairs.
 *
 * The Table is implemented as a hash table in class Table (below).
 *  
 * COMP 2140 Section - Solutions
 * INSRTUCTORS Helen Cameron and Robert Guderian
 * Lab 5
 * AUTHOR
 * VERSION 26 November 2018
 *
 **********************************************************************/

public class Lab5 {

    /*********** main and the method it calls *************************/

    /*******************************************************************
     * main
     * 
     * PURPOSE: read Lenert program and perform simulation.
     ******************************************************************/
    public static void main ( String[] args ) {
        System.out.println( "\n\nCOMP 2140 Lab 5: Executing Lenert Programs"
            + "\n-------------------------------------------------" );

        executeLenertPrograms( );

        System.out.println( "\n\n---------------\n"
            + "\nProgram ended normally.\n" );

    } // end main

    /********************************************************************
     * executeLenertPrograms
     *
     * PURPOSE: Handle the file I/O.
     *
     *  - Prompts the user for the file of Lenert programs
     *    (uses console input)
     *  - Opens the file as a Scanner, and reads in the first line.
     *  - Gets the number of Lenert programs in the file from the first line.
     *  - Loops around, calling executeOneProgram (which reads in and executes
     *    one Lenert program).
     *
     ********************************************************************/
    private static void executeLenertPrograms( )
    {    
        Scanner keyboard; // To read in the name of the input file
        String fileName; // The name of the input file typed in by the user
        Scanner inFile; // Scanner to read from user's selected file. 
        int numPrograms;  // Number of programs to execute (read from file).
        int currProgNum;  // Number of current program.

        // Allow user to choose file with keyboard (console) input.

        keyboard = new Scanner( System.in );
        System.out.println( "\nEnter the input file name (.txt files only): " );
        fileName = keyboard.nextLine();
        System.out.println( "" );
        try {
            // inFile is now set to read from the file chosen by the user.
            inFile = new Scanner( new File( fileName ) );

            // First, read the number of programs we will process.
            numPrograms = inFile.nextInt();
            inFile.nextLine(); // Ignore any remaining code on first line.

            // Loop for each simulation, aborting if end of file is reached.
            for ( currProgNum = 0; currProgNum < numPrograms && inFile.hasNextLine();
            currProgNum++ ) {
                // Print header for the current program.
                System.out.println( "\nLenert Program " + (currProgNum+1) );
                System.out.println("---------------" + ( (currProgNum+1) < 10 ? "-" : "--"));
                System.out.println("Error messages: ");

                executeOneProgram( inFile, currProgNum );

            } // end for

            // Additional error-checking.
            // If loop was exited due to end of file, print error.
            if ( currProgNum < numPrograms ) 
                System.out.println( "Error: unexpected end-of-file." );
        } catch( FileNotFoundException e ) {
            System.out.println(e.getMessage());
        }
    } // end executeLenertPrograms

    /*********************************************************************
     *  executeOneProgram
     *
     * PURPOSE: To execute one Lenert program:
     *    - reads in lines until either
     *        1) It finds 'Q' signalling the end of the current 
     *           Lenert program, or
     *        2) It finds the end of the file (unexpected end of 
     *           the current program).
     *   
     *********************************************************************/
    public static void executeOneProgram( Scanner inFile, int currProgNum ) {
        String currLine;
        String[] tokens;
        Table programVars = new Table() ;
        boolean donePgm = false; // Flag variable signalling time
        // to read next Lenert program.

        // Read lines from the current program until a 'Q' is read
        // or an unexpected end of file.
        while ( inFile.hasNextLine() && !donePgm ) {
            // Read and split current line.
            currLine = inFile.nextLine();
            currLine = currLine.trim();
            tokens = currLine.split( "\\s+" );

            // Three cases:
            // Q,  x = [var/int] or x = [var/int] [op] [var/int]

            if ( tokens.length == 1 ) {
                // Correct case: token is Q
                if ( tokens[0].length() == 1 && tokens[0].charAt(0) == 'Q' ) {
                    // Print out final values of variables.
                    System.out.println( "\nFinal values of the variables:\n" );
                    programVars.printTable();

                    // Continue to next simulation. 
                    donePgm = true;

                    // Incorrect case: some non-zero length token.
                } else if ( tokens[0].length() != 0 ) {
                    System.out.println( "Error: unexpected symbol in \""
                        + tokens[0] + "\"" );
                } 

                // Otherwise, line is blank! -- ignore it.

            } else if ( tokens.length >= 3 ) {

                handleOneAssignmentStatement( currLine, tokens, programVars );

            } else {
                // Two tokens on line.
                System.out.println( "Invalid input line: Two tokens only in "
                    + "\n      \"" + currLine + "\"" );

            } // end if-else 

        } // end while

        if ( !donePgm ) {
            // Loop was exited due to end of file without a 'Q'.
            System.out.println( "Error: unexpected end of program in program "
                + currProgNum );

            // Print out final values of variables, why not?
            System.out.println( "\nValues of the variables at termination: \n" );
            programVars.printTable();

        }

    } // end executeOneProgram

    /********************************************************************
     *
     * handleOneAssignmentStatement
     *
     * PURPOSE: To deal with an input line that is NOT 'Q' and has at
     *          least three tokens --- it should be an assignment statement.
     *
     ********************************************************************/
    private static void handleOneAssignmentStatement( String currLine,
    String[] tokens,
    Table variables ) {
        int rightSideValue;
        VariableRecord leftVariableRecord;  

        if ( tokens[1].equals( "=" ) ) {

            // Evaluate the right side of the assignment statement.
            // If there is an error, evaluateRightSide prints an
            // appropriate error message and returns Integer.MIN_VALUE.

            rightSideValue = evaluateRightSide( currLine, tokens, variables );

            if ( rightSideValue != Integer.MIN_VALUE ) { // Right side is OK

                // Now figure out the left side of the assignment
                if ( isVariableIdentifier( tokens[0] ) ) { // Left side is OK
                    leftVariableRecord = variables.search( tokens[0] );
                    if ( leftVariableRecord != null ) { // Existing variable
                        leftVariableRecord.setValue( rightSideValue );
                    } else { // New variable --- create and insert its variable record
                        variables.insert( tokens[0], rightSideValue );
                    }
                } else { // Left side of the assignment isn't a variable identifier
                    System.out.println( "Invalid input line: left side not "
                        + " a valid variable identifier in "
                        + "\n      \"" + currLine + "\"" );
                }
            } // if right side is OK
        } else { // Some weird input line: abandon it!
            System.out.println( "Invalid input line: expected \"=\", found \""
                + tokens[1] + "\" in "
                + "\n      \"" + currLine + "\"" );
        } // if-else there wasn't an "="

    } // end handleOneAssignmentStatement

    /********************************************************************
     * evaluateRightSide
     *    
     * PURPOSE: Returns the value of the right side (if possible)
     *          of the current assignment statement.
     *          Otherwise, it prints an appropriate error message, and
     *          returns Integer.MIN_VALUE.
     *
     * PARAM currLine
     *       IN
     *       The current assignment statement as a single String
     *
     * PARAM tokens 
     *       IN
     *       An array of tokens making up an assignment statement
     *       (we know the second token is an "=" and there are at
     *       least three tokens).
     * 
     * PARAM variables
     *       IN
     *       A Table of the variables correctly declared so far in this program.
     *
     ********************************************************************/
    public static int evaluateRightSide( String currLine, String[] tokens,
    Table variables ) {
        int returnValue = Integer.MIN_VALUE;
        String op1, op2;  // Tokens containing operands
        String operator; // Token containing an integer operator (+, -, *, /)
        int op1Value, op2Value; // Computed values of integer operands

        // Deal with the first token on the right side
        op1 = tokens[ 2 ];
        op1Value = getOperandValue( currLine, op1, variables );

        if ( op1Value != Integer.MIN_VALUE ) { // First token on the right side is good
            if ( tokens.length == 3 ) { // Right side is one token and we have its value
                returnValue = op1Value;
            } else if ( tokens.length == 5 ) { // Right side should be op1 operator op2
                operator = tokens[ 3 ];
                op2 = tokens[ 4 ];
                if ( isOperator( operator ) ) {
                    op2Value = getOperandValue( currLine, op2, variables );
                    if ( op2Value != Integer.MIN_VALUE ) { // Valid op1 operator op2!
                        returnValue = applyOperator( op1Value, operator, op2Value );
                    }
                } else {
                    System.out.println( "Invalid input line: expecting operator, found "
                        + "\"" + tokens[3] + "\" in \""
                        + currLine + "\"" );
                }
            } else {
                System.out.println( "Invalid input line: Invalid number of tokens in \""
                    + currLine + "\"" );
            }
        } // end if no error in the 1st operand on the right side

        return returnValue;

    } // end evaluateRightSide

    /********************************************************************
     * getOperandValue
     *    
     * PURPOSE: Returns the integer value of the operand (if possible)
     *          contained in parameter operand.
     *          Otherwise, it prints an appropriate error message, and
     *          returns Integer.MIN_VALUE.
     *
     * PARAM currLine
     *       IN
     *       The current assignment statement as a single String
     *
     * PARAM operand 
     *       IN
     *       A single token that we hope is either a variable
     *       (in which case we'll look it up in the Table to find its
     *       current value) or an integer constant.
     * 
     * PARAM variables
     *       IN
     *       A Table of the variables correctly declared so far in this program.
     *
     ********************************************************************/
    private static int getOperandValue( String currLine, String operand, Table variables ) {
        VariableRecord operandRecord; // Used if operand is a variable identifier
        int operandValue = Integer.MIN_VALUE; // Correct return value if error found
        Scanner operandScanner;

        if ( isVariableIdentifier( operand ) ) {
            // Operand is a variable's identifier, according to the Lenert definition.

            // Find its value in the table.
            // Otherwise, print an error and abandon the statement
            // because the variable is used before it is declared.
            operandRecord = variables.search( operand );
            if ( operandRecord != null ) {
                operandValue = operandRecord.getValue(); 
            } else {
                System.out.println( "Invalid input line: variable " + operand
                    + " is used before its declaration in \""
                    + currLine + "\"" );
            }
        } else {  // Operand is an integer constant, we hope!
            operandScanner = new Scanner( operand );
            if ( operandScanner.hasNextInt() ) {
                operandValue = operandScanner.nextInt();
            } else {
                System.out.println( "Invalid input line: operand \"" + operand
                    + "\" is neither a variable nor an "
                    + "integer constant in \""
                    + currLine + "\"" );
            }
        }

        return operandValue;
    } // end getOperandValue

    /********************************************************************
     * applyOp
     *
     * PURPOSE: To apply an integer operator (+, -, *, /) to two integer operands.
     * 
     * PARAM leftValue
     *   IN
     *     The value of the left operand.
     *
     * PARAM op 
     *   IN
     *     A String, the operator. one of "+" or "-" or "*" or "/".
     *
     * PARAM rightValue
     *  IN
     *    The value of the right operand.
     *   
     * RETURN the result of applying op to leftValue and rightValue.
     *
     ********************************************************************/
    public static int applyOperator( int leftValue, String op, int rightValue ) {
        char opChar;
        int returnValue = Integer.MIN_VALUE;

        // Get first character of op String - by definition, this
        // character is the operator.

        opChar = op.charAt(0);

        // Assign the return value the proper result, based on the
        // contents of opChar. 

        switch (opChar) 
        {
            case '+' : returnValue = leftValue + rightValue; break;
            case '-' : returnValue = leftValue - rightValue; break;
            case '*' : returnValue = leftValue * rightValue; break;
            case '/' : returnValue = leftValue / rightValue; break;
            default:  System.out.println("Unexpected operator");
        }

        return returnValue;
    } // end applyOperator

    /********************************************************************
     * isVariableIdentifier
     *
     * PURPOSE Return true if token is a valid Lenert variable identifier; 
     *         false otherwise.
     * 
     * PARAM token 
     *   IN
     *     A String representing a token in the Lenert language.
     * 
     ********************************************************************/
    public static boolean isVariableIdentifier( String token ) 
    {
        int currPosn;
        boolean variableFound = true;

        if (token.length() > 0 ) 
        { 
            if ( !Character.isLowerCase( token.charAt(0) ) )
            {
                variableFound = false;
            }
            else
            {
                currPosn = 1;

                // Check all remaining positions for characters or digits.
                while ( currPosn < token.length() && 
                ( Character.isLowerCase( token.charAt(currPosn) ) 
                    || Character.isDigit( token.charAt(currPosn) ) ) )
                    currPosn++;

                // Must reach end of the string in the previous loop.
                // If not, answer false.
                if (currPosn != token.length()) 
                    variableFound = false;
            }
        } 
        else
        {
            // Token is too short. Return false.
            variableFound = false;
        }

        return variableFound;
    }

    /********************************************************************
     * isOperator
     *
     * PURPOSE Return true if token is a valid integer operator; 
     *         false otherwise.
     * 
     * PARAM operator 
     *   IN
     *     A String representing a token in the Lenert language.
     * 
     ********************************************************************/
    private static boolean isOperator( String operator ) {
        return ( operator.length() == 1 && "+-*/".indexOf( operator.charAt(0) ) != -1 );
    }

} // end class Lab5

/*********************************************************************
 * VariableRecord class
 *
 * PURPOSE: Store a (name,value) pair for an integer variable. 
 *
 ********************************************************************/

class VariableRecord { 
    private String identifier;  // variable name.
    private int value;  // current value.

    /********************************************************************
     * Constructor VariableRecord
     * 
     * PARAM varID
     *    IN
     *    Variable's name.
     * 
     * PARAM value
     *    IN
     *    Variable's value at construction.
     *
     ********************************************************************/
    public VariableRecord( String varID, int varValue ) {
        identifier = varID;
        value = varValue;
    }  

    /********************************************************************
     * getIdentifier - Returns the variable identifier (name).
     ********************************************************************/
    public String getIdentifier() {
        return identifier;
    }

    /********************************************************************
     * getValue - Returns the current value of the variable.
     ********************************************************************/
    public int getValue() {
        return value;
    }

    /********************************************************************
     * setValue - Give the variable a new value.
     * 
     * PARAM value
     *       IN
     *       The new value for the variable.
     *
     ********************************************************************/
    public void setValue( int newValue ) {
        value = newValue;
    }

    /********************************************************************
     * toString 
     *    - return a printable version of the
     *      variable (name, pair) entry to be used when 
     *      printing at the end of the program.
     *
     * RETURN String
     *     "identifier = value"
     *
     ********************************************************************/
    public String toString() {
        return identifier + " = " + value;
    }

} // end class VariableRecord

/*********************************************************************
 * Table class
 *
 * PUPROSE: To store (name,value) pairs for all variables in a Lenert 
 *          program. 
 *
 * IMPLEMENTATION: Uses a hash table for storage, where each variable's
 *                 identifier is the key for its variable record.
 *
 ********************************************************************/

class Table {
    private static final int tableSize = 23;
    private static final int hashCoe   = 13;
    private Node[] table;
    /********************************************************************
     * Node class (private): A single node in the BST
     ********************************************************************/
    private class Node {
        public VariableRecord var;  // (name,value) pair
        public Node next;

        /********************************************************************
         * Constructor Node --- Constructs a node
         ********************************************************************/
        public Node( VariableRecord v ) 
        {
            var = v;
        }
    } // end private class Node

    /********************************************************************
     * Table constructor - constructs an empty Table.
     ********************************************************************/
    public Table() 
    { 
        table = new Node[tableSize];
    }

    private static int hash(String varName)
    {
        int hashValue = 0;
        for(int i = 0; i< varName.length(); i++)
        {
            hashValue += (varName.charAt(i)*hashCoe);
        }
        return hashValue;
    }

    /********************************************************************
     * search
     *
     * PURPOSE: Search the Table for a VariableRecord for a variable with
     *          identifier varID.  Return a pointer to the VariableRecord
     *          if found; otherwise, return null.
     * 
     * PARAM varID
     *     IN
     *     The name of the variable to be searched for.
     * 
     ********************************************************************/
    public VariableRecord search ( String varID ) { 
        VariableRecord result = null;
        int arrayPos = hash(varID) % tableSize;
        Node curr = table[arrayPos];
        boolean found = false;
        while(curr != null && !found)
        {
            if(curr.var.getIdentifier().equals(varID))
            {
                result = curr.var;
                found = true;
            }
            else
            {
                curr = curr.next;
            }
        }
        return result;
    } // end search

    /********************************************************************
     * printTable
     *
     * PURPOSE: To print all variables that are in the table.
     *          Each variable is printed in the form "x = VAL" where x 
     *          is the variable identifier and VAL is its integer value.
     * 
     * RETURN void
     ********************************************************************/
    public void printTable()
    {
        for(int i = 0; i < tableSize; i++)
        {
            Node curr = table[i];
            while(curr != null)
            {
                System.out.println(curr.var.toString());
                curr = curr.next;
            }
        }
    } // end printTable

    /********************************************************************
     * insert
     *
     * PURPOSE: Insert a new (name,value) pair into the Table.
     * 
     * PARAM varID
     *     IN
     *     The name of the variable to be inserted.
     * 
     * PARAM varValue
     *     IN
     *     The current value of the variable to be inserted.
     * 
     ********************************************************************/
    public void insert( String varID, int varValue )
    {
        int arrayPos = hash(varID) % tableSize;
        Node curr = table[arrayPos];
        if(search(varID) == null)
        {
            if(curr == null)
            {
                table[arrayPos] = new Node(new VariableRecord(varID,varValue));
            }
            else
            {
                Node prev = null;
                while(curr != null)
                {
                    prev = curr;
                    curr = curr.next;
                }
                prev.next = new Node(new VariableRecord(varID,varValue));
            }
        }
    } // end insert

} // end class Table
