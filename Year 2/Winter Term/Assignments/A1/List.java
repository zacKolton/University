import java.util.*;
/**
 * Class:           List
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: Creates a list that is full of ListItem.
 */
public class List extends ListItem
{
    private Node head;

    /*
     * Contructor 
     */
    public List()
    {
        head = null;
    }

    /*
     * add
     * 
     * Purpose: Add a new ListItem to "this" list.
     *          Cast ListItem to (BasicItem) as there is no isEqual for ListItem.
     *          I found this to be a more efficient way of checking if the investor/currency/or portfolioEntry 
     *          was a duplicate/alredy in the list. 
     *              - It was cleaner in the main method to have this return a boolean if added or not 
     *  
     * Parameters: ListItem
     * 
     * Return: Boolean
     */
    public boolean add(ListItem in)
    {
        boolean added = false;
        boolean found = false;
        if(head == null)
        {
            head = new Node(in);
            added = true;
        }
        else
        {
            Node curr = head;
            Node prev = null;
            while((curr != null) && !found)
            {
                if(((BasicItem)curr.getItem()).isEqual((BasicItem)in))
                {
                    found = true;
                }
                else
                {
                    prev = curr;
                    curr = curr.getNext();
                }
            }
            
            if(!found)
            {
                if(prev == null)
                {
                    curr.setNext(new Node(in));
                }
                else
                {
                    prev.setNext(new Node(in));
                }
                added = true;
            }
        }
        return added;
    }

    /*
     * searchBasicItem
     * 
     * Purpose: Move down the list to try and find a object of basicItem subclass
     *              - Investor/Currency/PortfolioEntry/BlockEntry
     *              
     * Parameters:  ListItem
     * 
     * Return:      boolean
     */
    public BasicItem searchBasicItem(ListItem in)
    {
        BasicItem result = null;
        Node curr = head;
        while((curr != null) && (result == null))
        {
            if(((BasicItem)curr.getItem()).isEqual((BasicItem)in))
            {
                result = (BasicItem)curr.getItem();
            }
            curr = curr.getNext();
        }
        return result;
    }
    
    
    /*
     * searchPortfolio
     * 
     * Purpose: A portfolioEntry isnt always going to be changing (hence the .isEqual method).
     *          This is here to find the portfolio entry, but use a different equals method so that it dosnt change
     *          the contents in the portfolioEntry. It needs to mearly find it first
     *              
     * Parameters:  String
     * 
     * Return:      PortfolioEntry
     */
    public PortfolioEntry searchPortfolio(String s)
    {
        PortfolioEntry result = null;
        Node curr = head;
        while((curr != null) && (result == null))
        {
            if(((PortfolioEntry)curr.getItem()).isEqualString(s)) //checks the currencies symbol
            {
                result = (PortfolioEntry)curr.getItem();
            }
            curr = curr.getNext();
        }
        return result;
    }
    
    /*
     * makeHash
     * 
     * Purpose: Create a hash for a BlockEntry
     * 
     * Return: int
     */
    public int makeHash()
    {
        Node curr = head;
        int hash = 0;
        while(curr != null)
        {
            hash = Objects.hash(((BlockEntry)curr.getItem()).getHash());
            curr = curr.getNext();
        }
        return hash;
    }
    
    /*
     * verify
     * 
     * Purpose: Verify the list has not been altered
     * 
     * Return: boolean
     */
    public boolean verify()
    {
        boolean pass = true;
        Node curr = head;
        while(curr.getNext() != null)
        {
            if((((BlockEntry)curr.getNext().getItem()).getHash()) != (Objects.hash(((BlockEntry)curr.getItem()))))
            {
                pass = false;
                System.out.println("failed");
            }
        }
        return pass;
    }
    
    /*
     * isEmpty
     * 
     * Simple check if the list is empty
     * 
     * Return: boolean
     */
    public boolean isEmpty()
    {
        return (head == null);
    }
    

    /*
     * report
     * 
     * Purpose: Uses polymorphism to not make duplicate code
     *          Makes a nice looking report of the specified type
     */
    public String report()
    {
        Node curr = head;
        String s = "";
        while(curr != null)
        {
            s += "\t\t • "+curr.getItem().toString()+"\n";
            curr = curr.getNext();
        }
        return s;
    }

}
