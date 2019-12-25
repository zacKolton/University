
/**
 * Class Name: IntNode
 * 
 * COMP 1020 Section: A01
 * Instructor: John Bate 
 * Name      : Zac Kolton
 * Assignment: 5
 * Question  : 1 Linked Lists 
 * 
 * Purpose   : Implements the nodes in the linked list
 */
public class IntNode
{
    private int data;
    private IntNode link;

    public IntNode(int n, IntNode node)
    //Purpose   : Contructs a node with the given data "n"
    //Input     : int n (the data), IntNode node (the node)
    //Output    : Nothing
    //Parameters: int n, IntNode node
    //Returned  : Nothing 
    {
        data = n;
        link = node;
    }

    public int getInt()
    //Purpose   : Returns the int held by the node
    //Input     : Nothing
    //Output    : int represented by data
    //Parameters: None
    //Returned  : an int (data)
    {
        return data;
    }

    public IntNode getLink()
    //Purpose   : Returns a reference to the next node (the link)
    //Input     : Nothing
    //Output    : IntNode representing by link
    //Parameters: None
    //Returned  : IntNode
    {
        return link;
    }

   

    public void setLink(IntNode x)
    //Purpose   : Sets the link to x (IntNode)
    //Input     : IntNode x (the link)
    //Output    : Nothing
    //Parameters: IntNode x
    //Returned  : Nothing
    {
        link = x;
    }
}
