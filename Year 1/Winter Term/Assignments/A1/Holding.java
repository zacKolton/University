
/**
 * Write a description of class Holding here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Holding
{
    private Book book;
    private boolean available;
    private int idNum;

    private static int next = 20180001;
    public Holding(Book b)
    {
        book = b;
        available = true;
        idNum = next++;
    }

    public Book getBook()
    {
        return book;
    }

    public int getID()
    {
        return idNum;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public void checkIn()
    {
        available = true;
    }

    public void checkOut()
    {
        available = false;
    }

    public boolean matches(int n)
    {
        return n == getID();
    }

    public String toString()
    {
        String answer = "";
        if(available)
        {
            answer = book.toString() +", copy "+ idNum+": Available";
        }
        else 
        {
            answer = book.toString() +", copy "+ idNum+": Checked out";
        }
        return answer;

    }
}
