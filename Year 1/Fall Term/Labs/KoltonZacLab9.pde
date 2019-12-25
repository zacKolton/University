/* Template
   Bronze exercise
   Lab 9 - COMP 1010
   
   Draw an octopus (or a "waving octopus")
   by drawing arms (lines of circles) radiating from the mouse.
   
   
*/

final int NUM_ARMS = 8;         //How many arms
final int CIRCLES_PER_ARM = height/4;  //Number of circles per arm
final int CIRCLE_SPACING = 10;  //How far apart the circles are in an arm
final int SIZE = 10;     //And the diameter of the individual circles

void setup() {
  size(500, 500);
  noStroke();     //No black outline around the circles
}

void draw() {
  background(0);
  stroke(255);
  line(0,height/2,width,height/2);
  line(width/2,0,width/2,height);
  drawOctopus(mouseX, mouseY);
 
}

void drawCircle(float theta, float distance, float xc, float yc){
    float x = xc + cos(theta) * distance;
    float y = yc + sin(theta) * distance;
    ellipse(x,y,SIZE,SIZE);

    
}
  


void drawOctopus(float x, float y){
  
   for(int i = 0; i <= NUM_ARMS; ++i)
    {
      float theta = i * TWO_PI/NUM_ARMS;
      for(float circleCount = 1.2; circleCount <= CIRCLES_PER_ARM; circleCount++) { 
      drawCircle(theta,CIRCLE_SPACING*circleCount,x,y);
      
      }
    }
}
    
   
      
      
      
      