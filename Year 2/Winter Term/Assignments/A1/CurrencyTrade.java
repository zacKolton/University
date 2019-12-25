import java.util.*;
/**
 * Class:           CurrencyTrade
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: This class represents when two investors are trading a cryptocurrency, or where neither are using CAD.
 *          It holds 2 portfolios (for each investor), 2 investors, and two amounts of units specified by the input.
 */
public class CurrencyTrade extends Trade
{
    private PortfolioEntry entryA;
    private PortfolioEntry entryB;
    private Investor investorA;
    private Investor investorB;
    private int unitsA;
    private int unitsB;
    
    /*
     * Constructor
     */
    public CurrencyTrade(Investor a, Investor b, PortfolioEntry pe1, PortfolioEntry pe2, int u1, int u2)
    {
        investorA = a;
        investorB = b;
        entryA    = pe1;
        entryB    = pe2;
        unitsA    = u1;
        unitsB    = u2;
    }
    
    /*
     * Method:  updateFinancialComponents
     * 
     * Purpose: Adjust the amount of units each investor has of the specified currency 
     *          Create a new portfolio entry for each investor
     *              - This is where the isEqual(BasicItem) increases the amount in the portfolio
     *          Add a new block to the blockchain
     */
    public void updateFinancialComponents()
    {
        entryA.decreaseUnits(unitsA);
        entryB.decreaseUnits(unitsB);
        investorA.addToList(new PortfolioEntry(entryB.getCurrency(),unitsB));
        investorB.addToList(new PortfolioEntry(entryA.getCurrency(),unitsA));
        entryA.getCurrency().addToList(new BlockEntry(investorB,unitsA,"Trade",generateHashA()));
        entryB.getCurrency().addToList(new BlockEntry(investorA,unitsB,"Trade",generateHashB()));
    }
    
    /*
     * generateHashA
     * 
     * Purpose: Generate a hashCode witht he given information
     * 
     * Return: int 
     */
    public int generateHashA()
    {
        return Objects.hash(investorA,entryA,unitsA);
    }
    
    /*
     * generateHashB
     * 
     * Purpose: Generate a hashCode witht he given information
     * 
     * Return: int 
     */
    public int generateHashB()
    {
        return Objects.hash(investorB,entryB,unitsB);
    }
    
    /****************Getters****************/
    
    public Investor getInvestorA()
    {
        return investorA;
    }
    
    public Investor getInvestorB()
    {
        return investorB;
    }
    
    public PortfolioEntry getEntryA()
    {
        return entryA;
    }
    
    public PortfolioEntry getEntryB()
    {
        return entryB;
    }
    
    public int getUnitsA()
    {
        return unitsA;
    }
    
    public int getUnitsB()
    {
        return unitsB;
    }
}
