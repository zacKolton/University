import java.util.ArrayList;
/**
 * Write a description of class Path here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Path extends ArrayList<Coord>
{
    private PathWindow pWindow;
    public Coord theOne;
    
    public Path(PathWindow p)
    {
        pWindow = p;
    }
    
    public void plotPath()
    {
        if(size() > 0)
        {
            int x = get(0).xCoord;
            int y = get(0).yCoord;
            for(int i = 0; i<size(); i++)
            {
                int xPrev = x;
                int yPrev = y;
                x = get(i).xCoord;
                y = get(i).yCoord;
                pWindow.circle(x,y,10,theOne != null && get(i).near(theOne)); //check solutions for this one
                pWindow.line(xPrev,yPrev, x,y);
            }
        }
    }
}
