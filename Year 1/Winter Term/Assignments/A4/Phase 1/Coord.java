


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
    
    public Coord(int x,int y)
    {
        xCoord = x;
        yCoord = y;
    }
    
    public String toString()
    {
        return "("+xCoord+","+yCoord+")";
    }
}
