
/**
 * Write a description of class PathApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PathApp
{
    private PathWindow pWindow;
    private Path path;
    
    public PathApp(PathWindow p)
    {
        pWindow = p;
        path = new Path(p);
    }
    
    public void draw()
    {
        path.plotPath();
    }
    
    public void mouseClicked(int x, int y)
    {
        path.add(new Coord(x,y));
    }
}
