/* Template
   Bronze exercise
   Lab 07 - COMP 1010
   
   Draw a spiral in the canvas that rotates
*/

final int NUM_LINES = 500;  //How many lines are drawn to make the spiral
float maxRadius;            //Distance to the corners of the window (needs width/height)
final int NUM_TURNS = 10;   //How many times around to get to that size
float startAngle = 0;       //The angle to use to start the spiral
final float ANGLE_CHANGE = 0.0002; //Change in startAngle each frame
int drawsteps = 20;
void setup() {
  size(500, 500);
  noStroke();
  fill(0);
  smooth();
  maxRadius = sqrt(sq(width/2)+sq(height/2)); //Distance to the corners from the centre
}
 
void draw() {
  background(255);
  translate(width/2,height/2);
  rotate(map(millis()%2000,0,2000,0,TWO_PI));
 beginShape();
  for (float t = 0; t < 20 *TWO_PI; t+=PI/360.0 ) {
    float x = 4 * t * cos(t);
    float y = 4 * t * sin(t);
    ellipse(x,y,2.5,2.5);
  }
  endShape();
 
}