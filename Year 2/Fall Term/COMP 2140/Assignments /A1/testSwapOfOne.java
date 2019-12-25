import java.util.Arrays;
/**
 * Write a description of class testSwap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class testSwapOfOne
{
    
    public static void main(String[] args) 
    {
        int[] test = {4,2,6};
        System.out.println(printArray(test));
        swap(test,1,1);
        System.out.println(printArray(test));
        ////-----test random array----//
        int[] array = makeArray(10);
        
        System.out.println("After randomize...\n"+printArray(array));
    }
    
    private static int[] makeArray(int size)
    {
        int[] result = new int[size];
        for(int i = 0; i<size;i++)
        {
            result[i] = (int)(Math.random()*(size));
        }
        System.out.println("Before randomize..\n"+printArray(result));
        for(int i = 0; i<size; i++)
        {
            int xPos = (int)(Math.random()*(size));
            int yPos = (int)(Math.random()*(size));
            if(xPos == yPos) 
            //this is to catch if there are two of the same positions in the array
            //highly unlikely but might as well catch it if it happens
            {
                yPos = (int)(Math.random()*(size-1));
            }
            swap(result,xPos,yPos);
        }
        return result;
    }
    
    
    
    private static void swap(int[] a, int x, int y){
        //Swap a[x] with a[y]
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    
    private static String printArray(int[] a)
    {
        String s = "";
        for(int i = 0; i<a.length; i++)
        {
            s += a[i]+" ";
        }
        return s;
    }
}
