 

/* This gives a graphics window for Assignment 4 in COMP 1020 (Winter 2018).
 * The student must supply:
 *    In a PathApp class:
 *       public PathApp(PathWindow) - a constructor
 *       public void draw()
 *       public void mouseClicked(int x, int y)
 * The student's code may call these instance methods to do the drawing,
 * which MUST be done in response to a call to draw():
 *     circle(int x, int y, int diam)
 *     line(int x1, int y1, int x2, int y2)
 * These are instance methods so the PathApp has to remember which
 * PathWindow created it, and apply those methods to that PathWindow.
 */

import javax.swing.*; //Needed for windows
import java.awt.*;    //Needed for graphics
import java.awt.event.*; //Needed for the mouse events

public class PathWindow  extends JFrame { //JFrame means "window", in effect
   
   private static final long serialVersionUID = 1L; //eliminates warnings
   
   private JPanel wholeWindow; //A JPanel is the content within a window
   private PathApp myApp; //A reference to the PathApp handling this window.
   private Graphics myGraphics; //Saved reference to the graphics environment
   
   //Constructor
   public PathWindow(int wide, int high) {
      setTitle("Path app");
      setSize(wide,high);
      wholeWindow = new GraphicsPanel(); //GraphicsPanel is defined below
      add(wholeWindow);
      wholeWindow.addMouseListener(new HandleMouse());
      myApp = new PathApp(this); //Set up the user's app
      setVisible(true);
   }//PathWindow constructor
   
   public static void main(String[] args){
      //Main program. All it has to do is create
      //some windows, and then take the rest of the day off.
      //The execution of the program takes place in separate
      //threads created by the windows.
      PathWindow window1 = new PathWindow(900,900);
      PathWindow window2 = new PathWindow(700,700);
      PathWindow window3 = new PathWindow(500,500);
   }//main
   
   //Provide some simple access to the graphics environment
   public void circle(int x, int y, int size){
      myGraphics.fillOval(x-size/2,y-size/2,size,size);
   }
   public void line(int x1, int y1, int x2, int y2){
      myGraphics.drawLine(x1,y1,x2,y2);
   }
      
   private class GraphicsPanel extends JPanel {
      private static final long serialVersionUID = 1L;
      
      public void paintComponent(Graphics g){
         /* This is where all the drawing commands go.
          * Whenever the window needs to be drawn, or redrawn,
          * this method will automatically be called.
          */
         myGraphics = g; //Just remember the Graphics environment
         myApp.draw(); //And then let the PathApp do what it wants
      }//paintComponent method
   }//private inner class graphicsPanel
   
   //A private inner class to take care of all mouse actions
   private class HandleMouse implements MouseListener {
      //The five standard methods are required. I don't want these ones:
      public void mousePressed(MouseEvent e){ /*Do nothing */ }
      public void mouseReleased(MouseEvent e){ /*Do nothing */ }
      public void mouseEntered(MouseEvent e){ /*Do nothing */ }
      public void mouseExited(MouseEvent e){ /*Do nothing */ }
      
      //The only one we really want to pay attention to
      public void mouseClicked(MouseEvent e){
         myApp.mouseClicked(e.getX(),e.getY());
         repaint(); //Redraw everything, since a change was probably made.
      }//mouseClicked
      
   }//private inner class HandleMouse
   
}//class PathWindow



