
/**
 * Class:           Trade
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: Abstract parent class to differeniate between a cash purchase and a trade of two currencies.
 *          The reason for this is because a cash purchase will only have one investor portfolio needed,
 *          whereas a trade of two currencies needs two portfolios (two currencies). 
 *          The constructors are different.
 */
public abstract class Trade extends Transaction
{
    public abstract Investor getInvestorA();
    public abstract Investor getInvestorB();
    public abstract int getUnitsA();
    public abstract int getUnitsB();
    public abstract PortfolioEntry getEntryA();
}
