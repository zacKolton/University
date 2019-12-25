
/**
 * Write a description of class Node here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Node
{
    private double x;
    private double y;
    private Node link;

    public Node(double dx, double dy,Node n )
    {
        x = dx;
        y = dy;
        link = n;
    }

    public Node getLink()
    {
        return link; 
    }

    public String toString()
    {
        return "("+String.format("%5.3f",x)+","+String.format("%5.3f",y)+")";
    }
}
