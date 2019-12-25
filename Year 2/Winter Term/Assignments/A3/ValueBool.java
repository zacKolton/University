

/**
 * Class    : ValueBool
 * Author   : Zachary Kolton
 * REMARKS  : Holds a Boolean value and implements Value interface
 */
public class ValueBool implements Value
{
    private boolean value;
    /**
     * Constructor
     */
    public ValueBool(boolean v)
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
        if(v instanceof ValueBool)
        {
            result = value == ((ValueBool)v).value;
        }
        return result;
    }
    
    public String toString()
    {
        return Boolean.toString(value);
    }
}
