/**
 * Create and perform some simple processing on a
 * list of Data objects which is a mixture of
 * Single and List objects
 */
public class TemplateLab7 {
    public static void main(String[] args) {

        Data[] myData = {
                new Single(2.4),
                new List(new double[] {3.2,6.8}),
                new List(new double[] {1.2,7.9,4.5}),
                new Single(9.8)
        };

        //***** YOUR BRONZE CODE HERE *****
        for(Data d: myData)
        {
            System.out.println("The sum of everything is: "+d.valueOf());
        }

        //***** YOUR SILVER CODE HERE *****
    }//main
}//TemplateLab7
