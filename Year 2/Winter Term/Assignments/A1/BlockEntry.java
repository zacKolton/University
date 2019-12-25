import java.util.*;
/**
 * Class:           BlockEntry
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: A blockEntry is a type of entry that goes into the Currency's blockChain
 *          It holds a investor, and the amount of units, a hashCode, and a "action" representing what type of
 *          transaction it was that created the block
 */
public class BlockEntry extends Entry
{
    private Investor investor;
    private int units;
    private int hashForList;
    private int orignalHash;
    private String action;

    /*
     * Constructor
     */
    public BlockEntry(Investor in,int u, String a, int h)
    {
        investor = in;
        units = u;
        action = a;
        orignalHash = h;
    } 

    /* 
     * Method:  isEqual
     * 
     * Purpose: Return a boolean to represent if equal, made it "isEqual()" instead of 
     *          "equals()" to reduce errors through polymorphic objects
     *          
     * Parameters: Accepts a BasicItem (allowing for polymorphism between classes)
     * 
     * Return:  Boolean
     */
    public boolean isEqual(BasicItem in) 
    {
        return hashForList == ((BlockEntry)in).getHash();
    }

    /*
     * Method:  toString
     * 
     * Purpose: Return a string to represent the portfolioEntry 
     * Return:  String
     */
    public String toString()
    {
        return "Action: '"+action+"'\t | Investor: '"+investor.getUserID()+"'\t| Amount: "+units+"\t| Hash: "+hashForList;
    }
    

    /*********************Getters/Setters*****************/
    public Investor getInvestor()
    {
        return investor;
    }

    public void setHash(int i)
    {
        hashForList = i;
    }

    public int getHash()
    {
        return hashForList;
    } 

    public int getUnits()
    {
        return units;
    }

}
