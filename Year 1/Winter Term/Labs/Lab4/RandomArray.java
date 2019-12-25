/**
 * A class for rectangular arrays full of random
 * positive integers.
 */

public class RandomArray {

    //An instance variable to hold an array of integers
    //****YOUR CODE HERE****
    private int[][] array;
    

    public RandomArray(int rows, int cols, int range) { 
        /* Construct a random array with the given number of
         * rows and columns, filled with random integers in 
         * the range  0..range-1.
         * Use the expression (int)(range*Math.random())
         */
        //****YOUR CODE HERE****
        array = new int[rows][cols];
        for(int r = 0; r<rows;r++)
        {
            for(int c = 0; c<cols; c++)
            {
                array[r][c] = (int)(range*Math.random());
            }
        }

    }//constructor
    public int[] getRow(int r){
        //Return a copy (clone) of row r of the array
        //****YOUR CODE HERE****
        int[] arrayRow = array[r].clone();
        return arrayRow;
    }//getRow method

    public int[] getCol(int c){
        //Return an int[] array containing the numbers from column c.
        //****YOUR CODE HERE****
        int[] arrayCol = new int[array.length];
        for(int i = 0;i<arrayCol.length; i++)
        {
            arrayCol[i] = array[i][c];
        }
        
        return arrayCol; 
    }//getCol method

}//RandomArray class
