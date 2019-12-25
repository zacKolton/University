
/**
 * Write a description of class Complex here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Complex extends Real
{
    private double imag;
    public Complex(double r, double c){
        super(r);
        imag = c;
    }
    
    public String toString()
    {
       String answer = "";
       if(imag > 0)
       {
           answer = super.toString() + "+"+ String.format("%4.2f",imag) + "i";
       }
       else
       {
           answer = super.toString()  + String.format("%4.2f",imag) + "i";
        }
       return answer;
    }
    public double magnitude()
    {
        double realNum = super.magnitude();
        return Math.sqrt((realNum*realNum) + (imag*imag));
    }
}
