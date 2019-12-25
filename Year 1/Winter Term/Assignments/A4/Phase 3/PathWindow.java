 

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

public class PathWindow extends JFrame { //JFrame means "window", in effect
   
   private static final long serialVersionUID = 1L; //eliminates warnings
   
   //The font used for the buttons, and how far from the left and bottom
   //of each button the characters will be drawn.
   private static final Font myFont = new Font("Helvetica",Font.BOLD,24);
   private static final int LABEL_INSET_X = 5;
   private static final int LABEL_INSET_Y = 15;
   
   //The size and positions and labels of each button, and the
   //public constants that will be used to identify them
   private static final int BUTTON_HEIGHTS = 50;
   private static final String[] LABELS = {"Extend","Delete","Insert","Select","Move","Open","Save"};
   public static final int EXTEND=0, DELETE=1, INSERT=2, SELECT=3, MOVE=4, OPEN=5, SAVE=6; 
   //The x coordinates of the dividing lines between buttons
   private static final int[] DIVIDERS = {0,90,175,260,345,430,515,600};

   private JPanel wholeWindow; //A JPanel is the content within a window
   private PathApp myApp; //A reference to the PathApp handling this window.
   private Graphics myGraphics; //Saved reference to the graphics environment
   private int mode; //The current mode (which button is selected)
   private int width, height; //The current window size (may change any time)
   
   //Constructor
   public PathWindow(int wide, int high) {
      setTitle("Path app");
      setSize(wide,high);
      wholeWindow = new GraphicsPanel(); //GraphicsPanel is defined below
      add(wholeWindow);
      wholeWindow.addMouseListener(new HandleMouse());
      mode = EXTEND; //Always begin in EXTEND mode
      myApp = new PathApp(this); //Set up the user's app
      setVisible(true);
   }//PathWindow constructor
   
   public static void main(String[] args){
      //Main program. All it has to do is create
      //some windows, and then take the rest of the day off.
      //The execution of the program takes place in separate
      //threads created by the windows.
      PathWindow window1 = new PathWindow(900,900);
      PathWindow window2 = new PathWindow(750,750);
      PathWindow window3 = new PathWindow(600,600);
   }//main
   
   //Provide some simple access to the graphics environment
   public void circle(int x, int y, int size, boolean selected){
      if(selected)
         myGraphics.setColor(Color.RED);
      else
         myGraphics.setColor(Color.BLACK);
      myGraphics.fillOval(x-size/2,y-size/2,size,size);
   }
   public void line(int x1, int y1, int x2, int y2){
      myGraphics.setColor(Color.BLACK);
      myGraphics.drawLine(x1,y1,x2,y2);
   }
   
   //public access to the current mode
   public int getMode() { return mode; }
   
   //A static utility to determine if the point (x,y) is within
   //a distance of maxDist from the line segment connecting
   //the points (x1,y1) and (x2,y2).
   public static boolean pointNearLine(int x, int y, //the point
                                       int x1, int y1, int x2, int y2, //the line
                                       double maxDist){//the maximum distance allowable
      //1. Find the point on the line closest to (x,y)
      double closestX,closestY;
      if(x1==x2){
         //vertical line. Easy.
         closestX=x1;
         closestY=y;
      }
      else{
         //Find the line's equation in the form ax+by+c=0
         double a=y1-y2;
         double b=x2-x1;
         double c=x1*y2-y1*x2;
         //Then the formulae are:
         closestX=(b*(b*x-a*y)-a*c)/(a*a+b*b);
         closestY=(a*(a*y-b*x)-b*c)/(a*a+b*b);
      }
      
      //2. Find the distance from (x,y) to the closest point
      double dist = Math.hypot(x-closestX,y-closestY);
      
      //3. It must be very close AND between the endpoints
      return dist<=maxDist
         && closestX>=Math.min(x1,x2) && closestX<=Math.max(x1,x2)
         && closestY>=Math.min(y1,y2) && closestY<=Math.max(y1,y2);    
   }//pointNearLine
   
   private class GraphicsPanel extends JPanel {
      private static final long serialVersionUID = 1L;
      
      public void paintComponent(Graphics g){
         /* This is where all the drawing commands go.
          * Whenever the window needs to be drawn, or redrawn,
          * this method will automatically be called.
          */
         myGraphics = g; //Just remember the Graphics environment
         Rectangle windowRect = g.getClipBounds();
         //Set the width and height every time, since these may change
         width = windowRect.width;
         height = windowRect.height;
         //Draw the buttons along the bottom of the window
         g.setFont(myFont);
         for(int i=0; i<LABELS.length; i++){
            //Use LIGHT_GRAY for unselected ones, GRAY for the selected one
            if(mode==i)
               g.setColor(Color.GRAY);
            else
               g.setColor(Color.LIGHT_GRAY);
            //Draw the rectangle with that colour, and the label in black
            g.fillRect(DIVIDERS[i],height-BUTTON_HEIGHTS,DIVIDERS[i+1]-DIVIDERS[i],BUTTON_HEIGHTS);
            g.setColor(Color.BLACK); 
            g.drawString(LABELS[i],DIVIDERS[i]+LABEL_INSET_X,height-LABEL_INSET_Y);
         }
         //Draw all the dividing lines afterwars so they'll be visible
         g.setColor(Color.BLACK); 
         for(int i=0; i<LABELS.length; i++){
            g.drawLine(DIVIDERS[i+1],height,DIVIDERS[i+1],height-BUTTON_HEIGHTS);
         }
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
         int x = e.getX(), y = e.getY();
         //If it's in the button area at the bottom
         if(y>height-BUTTON_HEIGHTS){
           for(int i=0; i<LABELS.length; i++){
              //CHeck to see if it's in button i
              if(x<DIVIDERS[i+1]){
                 if(i==OPEN)
                    myApp.doOpen(); //Do open immediately
                 else if(i==SAVE)
                    myApp.doSave(); //Do save immediately
                 else
                    mode = i; //Else just change the mode
                 break;
              }//if
           }//for
         }//if in button area
         else {
           //Let the PathApp handle it
           myApp.mouseClicked(x,y);
         }
         repaint(); //Redraw everything, since a change was probably made.
      }//mouseClicked
      
   }//private inner class HandleMouse
   
}//class PathWindow



