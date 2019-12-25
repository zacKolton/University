
/**
 * Class:           FinancialComponent
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: Abstract parent class for Currency and Investor
 */
public abstract class FinancialComponent extends BasicItem
{
    public abstract void increaseUnits(int n);
    public abstract void decreaseUnits(int n);
    public abstract List getList();
}
