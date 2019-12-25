import java.io.*;
import java.util.*;

public class Rolen
{
    public static final int N = 5; // compute R(N) recursively and iteratively
    public static long recursiveRolen(int n)
    {
        long result = 0;
        if (n < 2){
            result = 1;
        }
        else{
            result = recursiveRolen(n-1);
            for(int k = 0; k <= (n-2); k++){
                result += recursiveRolen(k) * recursiveRolen(n-2-k);
            }
        }
        return result;
    } // end method recursiveRolen

    public static long iterativeRolen(int n) 
    {
        long result = 0;
        long[] rolenNums = new long[n+1];
        if(n > 1)
        {
            rolenNums[0] = 1;
            rolenNums[1] = 1;
            for(int i = 2; i < n+1; i++)
            {
                result = rolenNums[i-1];
                for(int k = 0; k <= (i-2); k++)
                {
                        result += rolenNums[k] * rolenNums[i-2-k];
                }
                rolenNums[i] = result;
            }
            
        }
        else
        {
            rolenNums[n] = 1;
        }
        return rolenNums[n];
    }
    // end method iterativeRolen

    private static void printReport( String header,int n,long result,long elapsedTime )
    {
        System.out.println(header);
        System.out.println();

        System.out.println( "Time needed to compute R( " + n + " ): " + elapsedTime + " nanoseconds");
        System.out.println( "R( " + n + " ) = " + result );
        System.out.println();

    } // end method printReport

    public static void main( String[] args )
    {
        long start, stop, elapsedTime;  // Time how long computing R(N) takes.
        long result; // result returned when computing R(N)

        System.out.println( "\n\nLab 1 COMP 2140 Solution: Recursion and Iteration\n" );

        start = System.nanoTime();
        result = recursiveRolen(N);
        stop = System.nanoTime();
        elapsedTime = stop - start;
        printReport( "Report on recursiveRolen method:", N, result, elapsedTime );

        start = System.nanoTime();
        result = iterativeRolen(N);
        stop = System.nanoTime();
        elapsedTime = stop - start;
        printReport( "Report on iterativeRolen method:", N, result, elapsedTime );

        System.out.println( "\nProgram ends successfully" );

    } // end method main

} // end class Rolen
