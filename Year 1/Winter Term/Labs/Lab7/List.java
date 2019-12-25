
/**
 * Write a description of class List here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class List extends Data
{
    private double[] arrayDub;
    public List(double[] a)
    {
        arrayDub = a;

    }
    public double valueOf(){
        double total = 0;
        for(int i = 0; i<arrayDub.length; i++){
            total += arrayDub[i];
        }
        return total;
    }
}
