
/**
 * Class    : List
 * Author   : Zachary Kolton
 * REMARKS  : Hodls a list of Nodes
 */
public class List 
{
    private Node head;
    private Node currNode;
    /**
     * Constructor
     */
    public List()
    {
        head = null;
        currNode = head;
    }

    /**
     * Method       : add
     * Purpose      : Add a key value pair to the list
     * Parameters   : Value, Value
     */
    public void add(Value k, Value v)
    {
        if(head == null)
        {
            head = new Node(k,v);
        }
        else
        {
            boolean found = false;
            Node curr = head;
            Node prev = null;
            while((curr != null) && !found)
            {
                if(curr.getKey().equals(k)) 
                {
                    curr.updateValue(v);
                    found = true;
                }
                prev = curr;
                curr = curr.getNext();
            }

            if(!found)
            {
                prev.setNext(new Node(k,v));
            }
        }
    }

    /**
     * Method       : resetCurrNode
     * Purpose      : Reset the currNode to the head of the list
     * 
     * Return       : Node
     */
    public Node resetCurrNode()
    {
        currNode = head;
        return currNode;
    }

    //-------------------------------------
    // Implementing JSONIterator Methods
    //-------------------------------------

    /**
     * Method       : hasNext
     * Purpose      : check if there is another value in the list
     * 
     * Return       : boolean
     */
    public boolean hasNext()
    {
        boolean result = false;
        if(currNode != null)
        {
            result = currNode.hasNext();
        }
        return result;
    }

    /**
     * Method       : getNext
     * Purpose      : get the currNode Value and advance the currNode in the list
     * 
     * Return       : Value
     */
    public Value getNext() 
    {
        Value result = null;
        if((currNode != null) && !currNode.hasNext())
        {
            result = currNode.getKey();
        }
        else if((currNode != null) && currNode.hasNext())
        {
            result = currNode.getKey();
            currNode = currNode.getNext();
        }
        return result;
    }

    //-------------------------------------
    // Implementing Misc. Mehtods
    //-------------------------------------

    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * Method       : getValue
     * Purpose      : Get the value associated with the value key
     * Parameters   : Value
     * 
     * Return       : 
     */
    public Value getValue(Value k)
    {
        Node curr = head;
        Value result = null;
        while((curr != null) && !curr.getKey().equals(k))
        {
            curr = curr.getNext();
        }
        
        if(curr != null)
        {
            result = curr.getValue();
        }
        return result;
    }

    /**
     * Method       : equals
     * Purpose      : Tests if two lists/JSONObjects are equal
     * Parameters   : List
     * 
     * Return       : boolean
     */
    public boolean equals(List list2)
    {
        boolean equal = true;
        Node curr1 = head;
        Node curr2 = list2.head;
        while(((curr1 != null) && (curr2 != null)) && equal)
        {
            if(!curr1.equals(curr2))
            {
                equal = false;
            }
            curr1 = curr1.getNext();
            curr2 = curr2.getNext();
        }

        if(curr1.hasNext() || curr2.hasNext())
        {
            equal = false;
        }

        return equal;
    }
    
    /**
     * Method       : print
     * Purpose      : print out the whole object in one line
     * 
     * Return       : String
     */
    public String print()
    {
        String s = "{ ";
        Node curr = head;
        while(curr != null)
        {
            if(curr.hasNext())
            {
                s += curr.getKey().toString() + " : " + curr.getValue().toString() + " , ";
            }
            else
            {
                s += curr.getKey().toString() + " : " + curr.getValue().toString();
            }
            curr = curr.getNext();
        }
        return s+" }";
    }

}
