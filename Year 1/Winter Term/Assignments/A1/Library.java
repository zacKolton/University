
/**
 * Write a description of class Library here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Library
{
    private Holding[] holdings;
    private int size;
    private String nameLib;
    public static final int NOT_FOUND = -1;
    public Library(String n, int maxSize)
    {
        nameLib = n;
        holdings = new Holding[maxSize];
    }
    
    public String getName()
    {
        return nameLib;
    }
    
    public void newAcquisition(Book b)
    {
        Holding temp = new Holding(b);
        holdings[size] = temp;
        size++;
        b.addLibrary(this);
    }
    
    
    public String toString()
    {
        String result = "";
        result = "Library: "+nameLib+"\n";
        for(int i = 0; i<size; i++)
        {
            result += holdings[i].toString()+"\n";
        }
        return result;
    }
    
    private Holding getAvailable(Book b)
    {
        Holding result = null;
        for(int i = 0; i<size; i++)
        {
            if(holdings[i].getBook().equals(b) && holdings[i].isAvailable())
            {
                result = holdings[i];
                break;
            }
        }
        return result;
    }
    
    private int findHolding(int n)
    {
        int pos = NOT_FOUND;
        for(int i = 0; i<size; i++)
        {
            if(holdings[i].getID() == n)
            {
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    public int borrowBook(Book b)
    {
        int answer = NOT_FOUND;
        Holding temp = getAvailable(b);
        if(temp != null)
        {
            answer = temp.getID();
            int posTemp = findHolding(temp.getID());
            holdings[posTemp].checkOut();
        }
        return answer;
    }
    
    public boolean returnBook(int n)
    {
        int pos = findHolding(n);
        if(pos != NOT_FOUND)
        {
            holdings[pos].checkIn();
        }
        return pos != NOT_FOUND;
    }
    
    public int getNumberAvailable(Book b)
    {
        int total = 0;
        for(int i = 0; i<size; i++)
        {
            Holding hold = holdings[i];
            if(hold.getBook().equals(b) && hold.isAvailable())
            {
                total++;
            }
        }
        return total;
    }
    
    
    
}
