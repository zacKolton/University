
/**
 * Write a description of class Real here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Real
{
    private double num;
    public Real(double x)
    {
        num = x;
    }

    public String toString()
    {
        return String.format("%4.2f",num);
    }

    public double magnitude()
    {
        return num;
    }

}
