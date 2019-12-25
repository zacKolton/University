
/**
 * Class    : Node
 * Author   : Zachary Kolton
 * REMARKS  : Holds a Value "key" and Value "value"
 */
public class Node
{
    private Node next;
    private Value key;
    private Value value;
    
    /**
     * Constructor
     */
    public Node(Value k, Value v)
    {
        key = k;
        value = v;
    }
    
    /**
     * Method       : equals
     * Purpose      : Tests if two objects are equal
     * Parameters   : Node
     * 
     * Return       : boolean
     */
    public boolean equals(Node n)
    {
        return (key.equals(n.key)) && (value.equals(n.value));
    }
    
    //-------------------------------------
    // Implementing Getters/Setters/Updates
    //-------------------------------------    
    
    public void updateValue(Value v) { value = v;}  
    public void setNext(Node n) { next = n;}
    public boolean hasNext()    { return next != null;}  
    public Value getKey()       { return key;}
    public Value getValue()     { return value;} 
    public Node getNext()       { return next; }
}
