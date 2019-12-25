import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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

    //phase 3
    public void doSave()
    {
        JFileChooser chooser = new JFileChooser(".");
        chooser.showSaveDialog(null);
        File chosenFile = chooser.getSelectedFile();
        String outPut = "";
        for(int i = 0; i< path.size(); i++)
        {
            outPut += path.get(i).toString()+"\n";
        }
        try
        {
            PrintWriter outFile = new PrintWriter(new FileWriter(chosenFile));
            outFile.println(outPut);
            outFile.close();
        }
        catch(NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"you ended the file saver :"+ e.getMessage(),
                "you ended the file saver :"+ e.getMessage(),
                JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"this didnt work :"+ e.getMessage(),
                "this didnt work :"+ e.getMessage(),
                JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("file saved");
    }

    public void doOpen()
    {
        JFileChooser chooser = new JFileChooser(".");
        chooser.showOpenDialog(null);
        File chosenFile = chooser.getSelectedFile();
        BufferedReader inFile = null;
        try{
            inFile = new BufferedReader(new FileReader(chosenFile));
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"you ended the file chooser :"+ e.getMessage(),
                "you ended the file chooser :"+ e.getMessage(),
                JOptionPane.ERROR_MESSAGE);
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null,"no such file :"+ e.getMessage(),
                "no such file :"+e.getMessage(),
                JOptionPane.ERROR_MESSAGE);
        }

        String line;
        try
        {
            do{ 
                line = inFile.readLine();
                if(line != null)
                {
                    Coord newCoord = new Coord(line);
                    path.add(newCoord);
                }
            }while(line != null);
            inFile.close();
        }
        catch(NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"you were opening the file, but this happened :"+ e.getMessage(),
                "you were opening the file, but this happened :"+ e.getMessage(),
                JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"you were reading the file, but this happened :"+ e.getMessage(),
                "you were reading the file, but this happened :"+ e.getMessage(),
                JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("file opened");
    }
}
