import java.util.*;
/**
 * Class:           Mine
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: A mine is a type of transaction, it holds and investor, a currency, and a amount that has been mined (units)
 */
public class Mine extends Transaction
{
    private Investor investor;
    private Currency currency;
    private int units;
    
    /*
     * Constructor
     */
    public Mine(Investor in, Currency c, int u)
    {
        investor = in;
        currency = c;
        units    = u;
    }
    
    /*
     * Method:  updateFinancialComponents
     * 
     * Purpose: Adjust the amount of units the currency has to spare.
     *          Add changes to the portfolio and blockchain of the investor and currency
     */
    public void updateFinancialComponents()
    {
        currency.decreaseUnits(units);
        investor.addToList(new PortfolioEntry(currency,units));
        currency.addToList(new BlockEntry(investor,units,"Mine",generateHash()));
    }
    
    /*
     * generateHash
     * 
     * Purpose: Generate a hashCode witht he given information
     * 
     * Return: int 
     */
    public int generateHash()
    {
        return Objects.hash(investor,currency,units);
    }
    
    /***************Getters****************/
    
    public Investor getInvestor()
    {
        return investor;
    }
    
    public Currency getCurrency()
    {
        return currency;
    }
    
    public int getUnits()
    {
        return units;
    }
}
