/* Lab 05 COMP 1010
   Template
   Bronze exercise
   
   Draw a circle that moves back and forth along a horizontal
   line, freezing in place whenever the mouse button is pressed.
*/

final int X_LEFT = 100;  //The left end of the horizontal line.
final int X_RIGHT = 400; //The right end of the horizontal line.
final int Y = 100;       //The y position of the line and circle.
final int BALL_DIAM = 50;     //The diameter of the circle
final float BALL_SPEED = 2.5; //Its speed (pixels/second)

//****1) The state variables needed go here:
float ballX;
float ballY = Y;
float move;

void setup(){
  size(500,200); 
  ballX = 250;
}

void draw(){
  
  moveBall();
  drawAll();
}

//****2) Add your functions here:

void moveBall(){
  if(ballX >= X_RIGHT-BALL_DIAM/2){
  move = 1;
  }
  if(ballX == X_LEFT + BALL_DIAM/2){
  move = 0;
  }
  if(!mousePressed){
    
    if(move == 0){
      ballX += BALL_SPEED;
    }
      else{
        ballX -= BALL_SPEED;
      }
  }
}

void drawAll(){
  background(255);
  line(X_LEFT,Y,X_RIGHT,Y);
  fill(0);
  ellipse(ballX,ballY,BALL_DIAM,BALL_DIAM);
  
}