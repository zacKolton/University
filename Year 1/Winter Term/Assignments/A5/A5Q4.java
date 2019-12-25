import java.util.ArrayList;
/**
 * Class Name: A5Q4
 * 
 * COMP 1020 Section: A01
 * Instructor: John Bate 
 * Name      : Zac Kolton
 * Assignment: 5
 * Question  : 4 Subset Sums
 * 
 * Purpose   : Trys to find a combination of the integers in the list that add up to the 
 *             required sum
 */
public class A5Q4
{
    public static void main(String[] args) 
    { 
        ArrayList<Integer> testList = new ArrayList<Integer>();
        testList.add(3); 
        testList.add(11); 
        testList.add(1); 
        testList.add(5);
        System.out.println("Available numbers: "+testList); 
        for(int sum = 1; sum<=21;sum++)
        {
            ArrayList<Integer> answer = subsetWithSum(testList,sum);
            System.out.println(sum+"-> can be made with: "+answer); 
        }
    }

    public static ArrayList<Integer> subsetWithSum(ArrayList<Integer> numbers,int sum)
    //Purpose   :
    //Input     :
    //Output    :
    //Parameters:
    //Returned  : 
    { 
        ArrayList<Integer> temp = new ArrayList<Integer>();
        if(numbers.s == 0)
        {
            
        }
    }
}

    
   
