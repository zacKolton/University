import java.util.*;
/**
 * Class:           Purchase
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: This class represents when a investor using CAD purchases a Cryptocurrency from another investor. 
 *          It holds 1 portfolio, 2 investors, and two amounts of units specified by the input
 */
public class Purchase extends Trade
{
    private PortfolioEntry entryA;
    private Investor investorA;
    private Investor investorB;
    private int unitsA;
    private int unitsB;
    
    /*
     * Constructor
     */
    public Purchase(Investor a, Investor b, PortfolioEntry pe, int u1,int u2)
    {
        investorA = a;
        investorB = b;
        entryA = pe;
        unitsA = u1;
        unitsB = u2;
    }
    
    /*
     * Method:  updateFinancialComponents
     * 
     * Purpose: Adjust the amount of units of the currency in the portolfio of the investor (the seller).
     *          Decrease the investors cash (the buyer or investorB).
     *          Increase the investors cash (the seller or investorA).
     *          Add the transaction to the curreny's blockChain.
     *          Add a new portfolio entry to the investor (the buyer)
     *              - This is where the isEqual(BasicItem) increases the amount in the portfolio
     *          
     */
    public void updateFinancialComponents()
    {
        entryA.decreaseUnits(unitsA);
        investorA.increaseUnits(unitsB);
        investorB.decreaseUnits(unitsB);
        investorB.addToList(new PortfolioEntry(entryA.getCurrency(),unitsA));
        entryA.getCurrency().addToList(new BlockEntry(investorB,unitsA,"Sold",generateHash()));
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
        return Objects.hash(investorA,investorB,entryA,unitsA,unitsB);
    }
    
    /**************Getters**************/
    
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
    
        public int getUnitsA()
    {
        return unitsA;
    }
    
    public int getUnitsB()
    {
        return unitsB;
    }
}
    
    

