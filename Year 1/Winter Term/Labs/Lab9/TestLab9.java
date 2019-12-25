
public class TestLab9Bronze {
  
  public static void main(String[] args) { 
    final int NUM_POINTS = 5;
    //Create a PointList linked list.
    PointList points = new PointList();
    //Add random (x,y) points to it.
    for(int i=0; i<NUM_POINTS; i++)
      points.add(Math.random(),Math.random());
    //Print it out
    System.out.println("Points are: "+points);
  }
  
}
