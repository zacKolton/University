
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
    public static final double BY_LINE = 20;
    private int count = 0;
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
        if(pWindow.getMode() == 0)//extend
        {
            path.add(new Coord(x,y));
        }
        else if(pWindow.getMode() == 1)//delete
        {
            Coord test = new Coord(x,y);
            for(int i = 0; i <path.size(); i++)
            {
                if(test.near(path.get(i)))
                {
                    path.remove(i);
                }
            }
        }
        else if(pWindow.getMode() == 2)//insert
        {
            Coord test = new Coord(x,y);
            if(path.size()> 0)
            {
                for(int i = 1; i<path.size(); i++)
                {
                    if(pWindow.pointNearLine(test.xCoord,test.yCoord,
                        path.get(i-1).xCoord,path.get(i-1).yCoord,
                        path.get(i).xCoord,path.get(i).yCoord,BY_LINE))
                    {
                        int index = i;
                        path.add(index,test);
                        break;
                    }
                }
            }
        }
        else if(pWindow.getMode() == 3) // check solutions for this one
        {
            path.theOne = null;
            Coord test = new Coord(x,y);
            for(int i = 0; i<path.size(); i++)
            {
                if(test.near(path.get(i)) && path.theOne ==  null){
                    path.theOne = test;
                    count++;
                }
            }
            
            if((count%2 ==0) && path.theOne != null)
            {
                path.theOne = null;
            }
            
        }
        else if(pWindow.getMode() == 4)
        {
            for(int i = 0; i < path.size(); i++)
            {
                if((path.theOne != null) && (path.theOne.near(path.get(i))))
                {
                    path.set(i,new Coord(x,y));
                    path.theOne = new Coord(x,y);
                }
            }
        }

    }
}
