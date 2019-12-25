

import java.util.StringTokenizer;
import javax.swing.JOptionPane;
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
    
    public Coord(String s)
    {
        int leftIndex = s.indexOf("(");
        int rightIndex= s.indexOf(")");
        int commaIndex= s.indexOf(",");
        String xString= s.substring(leftIndex+1,commaIndex);
        String yString= s.substring(commaIndex+1,rightIndex);
        StringTokenizer x = new StringTokenizer(xString);
        StringTokenizer y = new StringTokenizer(yString);
        String xFinal = "";
        String yFinal = "";
        while(x.hasMoreTokens())
        {
            xFinal += x.nextToken();
        }
        
        while(y.hasMoreTokens())
        {
            yFinal += y.nextToken();
        }
        
        try
        {
           xCoord = Integer.parseInt(xFinal);
           yCoord = Integer.parseInt(yFinal);
        }
        catch(NumberFormatException nf)
        {
            JOptionPane.showMessageDialog(null,"number format is invalid:"+ nf.getMessage(),
                "number format is invalid :"+ nf.getMessage(),
                JOptionPane.ERROR_MESSAGE);
        }
        
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
