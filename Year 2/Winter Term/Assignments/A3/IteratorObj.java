
/**
 * Class    : IteratorObj
 * Author   : Zachary Kolton
 * REMARKS  : Iterators over List 
 */
public class IteratorObj implements JSONIter
{
    private List list;
    /**
     * Constructor
     */
    public IteratorObj(List l)
    {
        list = l;
        list.resetCurrNode();
    }
    
    //-------------------------------------
    // Implementing JSONIterator Methods
    //-------------------------------------
    /**
     * Method       : hasNext
     * Purpose      : check if the list has another element
     * 
     * Return       : boolean
     */
    public boolean hasNext()
    {
       boolean result = false;
       if(!list.isEmpty())
       {
           result = list.hasNext();
       }
       return result;
    }
    
    /**
     * Method       : getNext
     * Purpose      : Gets the currNode value in the list and then increments currNode
     * 
     * Return       : Value
     */
    public Value getNext()
    {
        Value result = null;
        if(!list.isEmpty())
        {
           result = list.getNext();
        }
        return result;
    }
    
}
