
/**
 * Class    : ValueInt
 * Author   : Zachary Kolton
 * REMARKS  : Holds a Iint value and implements Value interface
 */
public class ValueInt implements Value 
{
    private int value;
    
    /**
     * Constructor
     */
    public ValueInt(int v)
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
        if(v instanceof ValueInt)
        {
            result = value == ((ValueInt)v).value;
        }
        return result;
    }
    
    public String toString()
    {
        return Integer.toString(value);
    }
}
