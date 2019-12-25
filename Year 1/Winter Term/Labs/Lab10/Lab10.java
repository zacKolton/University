import java.util.Arrays;

/**
 * A simple recursive method to find the largest value in a list,
 * and a main program to test it.
 */
public class TemplateLab10Bronze {

    public static void main(String[] args) { 
        double[] test = {2.3,5.6,9.8,6.5,7.2,4.4};
        System.out.println("The largest element of the array "+Arrays.toString(test)+
            "\n is "+largestInList(test.length,test));
        test = new double[]{1.1};
        System.out.println("The largest element of the array "+Arrays.toString(test)+
            "\n is "+largestInList(test.length,test));
    }//main

    //******Add your method here******
    public static double largestInList(int n, double[] list){
        if(n == 1)
        {
            return list[n-1];
        }
        else 
        {
            return Math.max(list[n-1],largestInList(n-1, list));
        }
    }
}

        

