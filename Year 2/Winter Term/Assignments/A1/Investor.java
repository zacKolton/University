
/**
 * Class:           Investor
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: An investor is holds a name, userID, certain amount of cash to start, and a List (Portfolio)
 *          A Portfolio holds a list of PortfolioEntry, which is a collection of all the currencies the investor
 *          holds and the amount that each has in the portfolio
 */
public class Investor extends FinancialComponent
{
    private String name;
    private String userID;
    private int cash;
    private List portfolio;
    
    /*
     * Constructor
     */
    public Investor(String n, String id, int c)
    {
        name  = n;
        userID= id;
        cash  = c;
        portfolio = new List();
    }

    /*
     * Method:  toString
     * 
     * Purpose: Return a string to represent the Investor 
     * Return:  String
     */
    public String toString()
    {
        return "\tName:\t"+name +"\n\tUserID:\t"+userID+"\n\tCash:\t"+cash;
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
        return userID.equals(((Investor)in).getUserID());
    }
    
    /*
     * Method:  addToList
     * 
     * Purpose: Add to the held List (PortfolioEntry)
     */
    public void addToList(PortfolioEntry pe)
    {
        portfolio.add(pe);
    }
    
    /*
     * Method:  searchPortfolio
     * 
     * Purpose: Add to the held List (BlockChain)
     * Return:  PortfolioEntry
     */
    public PortfolioEntry searchPortfolio(String s)
    {
        return portfolio.searchPortfolio(s);
    }
    
    /*
     * Method:      decreaseUnits
     * 
     * Purpose:     Decrease the amount of cash held 
     * Parameters:  Accepts an int
     */
    public void decreaseUnits(int units)
    {
        cash -= units;
    }

    /*
     * Method:      increaseUnits
     * 
     * Purpose:     Increase the amount of cash held 
     * Parameters:  Accepts an int
     */
    public void increaseUnits(int units)
    {
        cash += units;
    }
    
    /***********Getters**********/
    
    public List getList()
    {
        return portfolio;
    }

    public String getName()
    {
        return name;
    }

    public String getUserID()
    {
        return userID;
    }

    public int getCash()
    {
        return cash;
    }


}
