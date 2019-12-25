
/**
 * Class:           Node
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: Simple Node class that holds a listItem. Various list will be made up of Nodes
 */
public class Node
{
    private ListItem item;
    private Node next;
    
    /*
     * Constructor 1
     */
    public Node(ListItem i)
    {
        item = i;
    }
    
    /*
     * Constructor 2
     */
    public Node(ListItem i, Node n)
    {
        item = i;
        next = n;
    }
    
    /*************Getters************/
    
    public ListItem getItem()
    {
        return item;
    }
    
    public Node getNext()
    {
        return next;
    }
    
    public void setNext(Node n)
    {
        next = n;
    }
}
