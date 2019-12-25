


/**
 * Write a description of class Coord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Coord
{
    public int xCoord;
    public int yCoord;
    public static final int BY_DOT = 10;
    public Coord(int x,int y)
    {
        xCoord = x;
        yCoord = y;
    }
    
    public boolean near(Coord c)
    {
        return Math.sqrt(Math.pow(c.xCoord-xCoord,2) + Math.pow(c.yCoord - yCoord,2)) < BY_DOT;
    }
    
    public String toString()
    {
        return "("+xCoord+","+yCoord+")";
    }
}
