
/**
 * Class:           PortfolioEntry
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: A portfolioEntry is a type of entry that goes into the investors portfolio
 *          It holds a currency, and the amount of units of that currency 
 */
public class PortfolioEntry extends Entry
{
    private Currency currency;
    private int units;
    /*
     * Constructor
     */
    public PortfolioEntry(Currency c,int u)
    {
        currency = c;
        units = u;
    }

    /*
     * Method:  isEqual
     * 
     * Purpose: Return a boolean to represent if equal, made it "isEqual()" instead of 
     *          "equals()" to reduce errors through polymorphic objects.
     *          If it is equal it will increase the amount of units that the investor holds of the Currency.
     *          It will always be increasing because this method is only use when adding a new PortfolioEntry
     *          to the portolfio. So if they are equal, it will just increase the units rather than adding the exact
     *          same Currency to the portfolio
     *          
     * Parameters: Accepts a BasicItem (allowing for polymorphism between classes)
     * 
     * Return:  Boolean
     */
    public boolean isEqual(BasicItem in)
    {
        boolean equal = currency.getSymbol().equals(((PortfolioEntry)in).getCurrency().getSymbol());
        if(equal)
        {
            units += ((PortfolioEntry)in).getUnits();
        }
        return equal;
    }
    
    /*
     * Method:  isEqualString
     * 
     * Purpose: Return a boolean to represent if equal, made it "isEqual()" instead of 
     *          "equals()" to reduce errors through polymorphic objects.
     *          This equals method is used for when we are only SEARCHING for a portfolioEntry, not adding it
     *          to the list.
     *          
     * Parameters: Accepts a string (will be a currency symbol)
     * 
     * Return:  Boolean
     */
    public boolean isEqualString(String s)
    {
        return currency.getSymbol().equals(s);
    }

    /*
     * Method:  toString
     * 
     * Purpose: Return a string to represent the portfolioEntry 
     * Return:  String
     */
    public String toString()
    {
        return "Currency: '"+currency.getSymbol()+"'\t| Amount: "+units;
    }
    
    /*
     * Method:      increaseUnits
     * 
     * Purpose:     Increase the amount of units held 
     * Parameters:  Accepts an int
     */
    public void increaseUnits(int u)
    {
        units += u;
    }
    
    /*
     * Method:      decreaseUnits
     * 
     * Purpose:     Decrease the amount of units held 
     * Parameters:  Accepts an int
     */
    public void decreaseUnits(int u)
    {
        units -= u;
    }
    
    /***************Getters*****************/
    
    public Currency getCurrency()
    {
        return currency;
    }

    public int getUnits()
    {
        return units;
    }
}
