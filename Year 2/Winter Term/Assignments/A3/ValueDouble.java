
/**
 * Class    : ValueDouble
 * Author   : Zachary Kolton
 * REMARKS  : Holds a double value and implements Value interface
 */
public class ValueDouble implements Value 
{
    private double value;
    
    /**
     * Constructor
     */
    public ValueDouble(double v)
    {
        value = v;
    }
    
    /**
     * Method       : equals
     * Purpose      : Tests if two objects are equal
     * Parameters   : Value
     * 
     * Return       : boolean
     */
    public boolean equals(Value v)
    {
        boolean result = false;
        if(v instanceof ValueDouble)
        {
            result = value == ((ValueDouble)v).value;
        }
        return result;
    }
    
    public String toString()
    {
        return Double.toString(value);
    }
}
