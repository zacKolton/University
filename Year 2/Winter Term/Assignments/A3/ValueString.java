
/**
 * Class    : ValueString
 * Author   : Zachary Kolton
 * REMARKS  : Holds a String value and implements Value interface
 */
public class ValueString implements Value
{
    private String value;
    
    /**
     * Constructor
     */
    public ValueString(String v)
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
        if(v instanceof ValueString)
        {
            result = value.equals(((ValueString)v).value);
        }
        return result;
    }
    
    public String toString()
    {
        return "\""+value+"\"";
    }
}
