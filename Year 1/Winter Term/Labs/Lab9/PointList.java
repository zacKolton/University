
/**
 * Write a description of class PointList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PointList
{
    private Node top;

    public PointList()
    {
        top = null;
    }

    public void add(double x,double y)
    {
        Node newNode = new Node(x,y,top);
        top = newNode;
    }
    
    public String toString()
    {
        String answer ="";
        Node next = top;
        while(next != null)
        {
            answer += next.toString() + " ";
            next = next.getLink();
        }
        return "[ "+answer+" ]";
    }
}
