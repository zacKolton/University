import java.awt.Color;
/**
 * Class Name: A5Q3
 * 
 * COMP 1020 Section: A01
 * Instructor: John Bate 
 * Name      : Zac Kolton
 * Assignment: 5
 * Question  : 3 Fractal Tree
 * 
 * Purpose   : Draws a "fractal tree" through the use of recursion
 */
public class A5Q3
{
    public static void drawTree(double startX, double startY, double length, double theta, int depth)
    //Purpose   : Draw a "Fractal Tree" with depth level of branches, till depth = 0, at which point 
    //            the "leaves" will be drawn with circles 
    //Input     : startX - x1 for a line                   (double)
    //            starty - y1 for a line                   (double)
    //            length - length of the line              (double)
    //            theta  - angle that the line is drawn at (double)
    //            depth  - amount of branches the tree will have, (how int calls the function will make)
    //Output    : depth amount of recursions, represented by a Fractal Tree
    //Parameters: double and int
    //Returned  : a represntation of a Fractal Tree
    {
        double endX = startX + length*Math.sin(theta);
        double endY = startY + length*Math.cos(theta);
        if(depth == 0)
        {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line(startX,startY,endX,endY);
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.filledCircle(endX,endY,.009);
        }
        else 
        {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line(startX,startY,endX,endY);
            startX = endX;
            startY = endY;
            length = length - (length*.15);
            theta  += (Math.random() * (Math.PI)/100);
            double variance = (Math.random()*(Math.toRadians(30)));
            drawTree(startX,startY,length,theta-variance,depth-1);
            drawTree(startX,startY,length,theta+variance,depth-1);
            
            
        }

    }
}
