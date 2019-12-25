
/**
 * Class    : ValueObject
 * Author   : Zachary Kolton
 * REMARKS  : Holds List and Iterator Objects and implements Value interface
 */
public class ValueObject implements JSONObject
{
    private String key;
    private Value value;
    private List list;
    private JSONIter iterator;
    /**
     * Constructor
     */
    public ValueObject()
    {
        list = new List();
        iterator = iterator = new IteratorObj(list);
    }
    
    //-------------------------------------
    // Implementing JSONObject Methods
    //-------------------------------------
    /**
     * Method       : addKeyValue
     * Purpose      : Adds a key-value pair to the object
     * Parameters   : Value, Value
     */
    public void addKeyValue(Value k, Value v)
    {
        list.add(k,v);
    }
    
    /**
     * Method       : getValue
     * Purpose      : Get the value associated witht the given key value
     * Parameters   : Value
     * 
     * Return       : Value
     */
    public Value getValue(Value key)
    {
        return list.getValue(key);
    }
    
    /**
     * Method       : iterator
     * Purpose      : get the iterator for this object
     * 
     * Return       : JSONIter
     */
    public JSONIter iterator()
    {
        return iterator;
    }
    
    //-------------------------------------
    // Implementing Value Methods
    //-------------------------------------
    /**
     * Method       : equals
     * Purpose      : Tests if two objects are equal
     * Parameters   : Value
     * 
     * Return       : boolean
     */
    public boolean equals(Value obj)
    {
        boolean result = false;
        if(obj instanceof ValueObject)
        {
            if(!((ValueObject)obj).list.isEmpty())
            {
                result = list.equals(((ValueObject)obj).list);
            }
        }
        return result;
    }
    
    public String toString()
    {
        return list.print();
    }
    
    
    
}
