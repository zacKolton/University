
/**
 * Class:           Currency
 * 
 * Name:            Zachary Kolton
 * Student Number:  7838513
 * Course :         COMP 2150
 * Instructor:      Mr. Boyer
 * Assignment:      #1
 * Question:        #1
 * 
 * Remarks: A Currency (NOT CANADIAN DOLLARS) holds a name, symbol, units, and a BlockChain
 *          (List)
 *          The BlockChain holds a list of type Block, giving a history of activity related 
 *          to this Currency
 */
public class Currency extends FinancialComponent
{
    private String name;
    private String symbol;
    private int units;
    private List blockChain;
    
    /*
     * Constructor
     */
    public Currency(String n, String s, int amount)
    {
        name     = n;
        symbol   = s;
        units    = amount;
        blockChain = new List();
    }

    /*
     * Method:  toString
     * 
     * Purpose: Return a string to represent the Currency 
     * Return:  String
     */
    public String toString()
    {
        return "\tName:\t"+name+"\n\tSymbol:\t"+symbol+"\n\tQuant:\t"+units;
    }

    /*
     * Method:  isEqual
     * 
     * Purpose: Return a boolean to represent if equal, made it "isEqual()" instead of 
     *          "equals()" to reduce errors through polymorphic objects
     * Return:  Boolean
     */
    public boolean isEqual(BasicItem in)
    {
        return symbol.equals(((Currency)in).getSymbol());
    }
    
    /*
     * makeHash
     * 
     * Purpose: create a hasCode from the whole list
     * 
     * Return: int 
     */
    public int makeHash()
    {
        return blockChain.makeHash();
    }
    
    /*
     * Method:  addToList
     * 
     * Purpose: Add to the held List (BlockChain)
     */
    public void addToList(BlockEntry be)
    {
        be.setHash(makeHash());
        blockChain.add(be);
    }
    
    /*
     * Method:      increaseUnits
     * 
     * Purpose:     Increase the amount of units held 
     * Parameters:  Accepts an int
     */
    public void increaseUnits(int n)
    {
        units += n;
    }

    /*
     * Method:      decreaseUnits
     * 
     * Purpose:     Decrease the amount of units held 
     * Parameters:  Accepts an int
     */
    public void decreaseUnits(int n)
    {
        units -= n;
    }
    
    
    /***************Getters**************/
    
    
    public List getList()
    {
        return blockChain;
    }

    
    public String getName()
    {
        return name;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public int getUnits()
    {
        return units;
    }


}
