
final float TIME_STEP = .2;
float time;
final float GRAVITY = -9.8;
float angle,mousePosX,mousePosY;

float castlePosY,castleSizeX,castlePosX,castleSizeY,startBallY,startBallX; 
float groundSizeY = 50;   //y value of ground 
boolean fire;             //allows ball to be shot
boolean ignoreKey = true; //so key cant be used during flight or before
boolean useMouse = false; //so mouse cant be used during flight or after 
void setup()
{
  background(0,0,255);
  size(800,600);
  frameRate(60);
  startBallX = 0.0;
  startBallY= height;
  time = 0.0;
  generateCastle();
  
}

void draw(){
  background(0,0,255);
  fill(0);
  strokeWeight(0);
  showSpeed();
  ground();
  drawCastle();
  fill(0);
  checkCollisions(calcBallX(time,getSpeed(),getAngle()),
                  calcBallY(time,getSpeed(),getAngle()));
  updateCannonBall(time);
  strokeWeight(20);
  drawCannon(angle);
  if(fire){
  time += TIME_STEP;
  }
}
void ground(){
  fill(0,255,0);
  rect(0,height-groundSizeY,width,groundSizeY);
}

float calcBallX(float time, float initV, float angle){
  angle = getAngle();
  float Vx = initV*cos(angle);
  float ballPosX = startBallX + (Vx * time);
  return ballPosX;
}

float calcBallY(float time, float initV, float angle){
  angle = getAngle();
  float Vy = initV*sin(angle);
  float ballPosY = startBallY -(time * Vy  + 0.5*GRAVITY*sq(time));
  return ballPosY;
}
float getAngle(){
  float angle = atan2(height - mousePosY,mousePosX);
  return angle;
}
void drawCannon(float angle){
  angle = atan2(height - mouseY,mouseX);
  strokeWeight(10);
  line(0,height,20*cos(angle),height - 20*sin(angle));
}
  

void drawBall(float posX, float posY){
  if(fire){
  ellipse(posX,posY, 10,10);
  }
}


float getSpeed(){
  float scaleFact = .15;
  float distance = sqrt(sq(mousePosX)+sq(height - mousePosY));
  float velocity = distance * scaleFact;
  println(velocity);
  return velocity;
}
//=========================================== this code wasnt required for the assignment but i thought
                                            //that it would be cool to add
void showSpeed(){
  textSize(14);
  fill(255);
  text(round(getSpeed())+"m/s"+" "+"at"+" "+round(degrees(getAngle()))+" "+"degrees",mouseX-10,mouseY - 10);
}
//=========================================================================================================
void mouseClicked(){
  if(!useMouse){
  mousePosX = mouseX;
  mousePosY = mouseY;
  angle = getAngle();
  fire = true;
  time = 0.0;
  ignoreKey = true;
  useMouse = true;
  }
  
}
void updateCannonBall(float time){
    if(hasHitCastle(calcBallX(time,getSpeed(),getAngle()), calcBallY(time,getSpeed(),getAngle())) ||
      hasWon       (calcBallX(time,getSpeed(),getAngle()), calcBallY(time,getSpeed(),getAngle())) ||
      isOutOfBounds(calcBallX(time,getSpeed(),getAngle()), calcBallY(time,getSpeed(),getAngle()))){
      drawBall(calcBallX(time,getSpeed(),getAngle()), calcBallY(time,getSpeed(),getAngle()));
      fire = false;
      ignoreKey = false;
      useMouse = true;
      drawBall(calcBallX(time,getSpeed(),getAngle()), calcBallY(time,getSpeed(),getAngle()));
      ellipse(calcBallX(time,getSpeed(),getAngle()), calcBallY(time,getSpeed(),getAngle()),10,10);
      
     }
     else if(useMouse){
     drawBall(calcBallX(time,getSpeed(),getAngle()), calcBallY(time,getSpeed(),getAngle()));
     }
}
     


void generateCastle(){
  castleSizeY= random(800/4,800/2);
  castleSizeX= 30;
  castlePosX = (random(800/2,800*.75)- castleSizeX/2);
  castlePosY = height - castleSizeY;
  println("               " + castleSizeY);
}
void drawCastle(){
  fill(155);
  rect(castlePosX,castlePosY,castleSizeX,castleSizeY);
}

boolean hasHitCastle(float ballX, float ballY){
  if(ballX >= castlePosX && ballX <= castlePosX + castleSizeX && ballY >= castlePosY){
      return true;
  }
  else return false;
}

boolean hasWon(float ballX, float ballY){
  if((ballX >= castlePosX + castleSizeX && ballX < width) &&
     (ballY >= (height-groundSizeY) + 10) && ballY <= height){
     return true;
     }
     else return false;
}

boolean isOutOfBounds(float ballX,float ballY){
  if(ballX >= width || ballY <= 0 || ballY > height){
    return true;
  }
  else return false;
}
void showMessage(String msg, int size, int Ycord){
  if(hasWon(calcBallX(time,getSpeed(),getAngle()),
            calcBallY(time,getSpeed(),getAngle()))){
              textSize(size);
              text(msg,275,Ycord);
            }
}

void checkCollisions(float ballX, float ballY){
  if(hasHitCastle(ballX,ballY)){
    textSize(20);
    text("Rats you hit the wall...shoot Higher",250,100);
    text("Press R to reset with same castle or C to reset with different castle",100,120);
  }
  else if(isOutOfBounds(ballX, ballY)){
    textSize(20);
    text("Yikes the shot went out of the field...adjust your aim",175,100);
    text("Press R to reset with same castle or C to reset with different castle",100,120);
  }
  else if(hasWon(ballX,ballY)){
    textSize(20);
    showMessage("Congrats you won weeeooo",20,100);
    text("Press R to reset with same castle or C to reset with different castle",100,120);
  }
}

//========================================= PHASE 5//
  
void keyPressed(){
  if(!ignoreKey){
    if(key == 'C' || key == 'c'){
      background(0,0,255);
      startBallX = 0.0;
      startBallY= height;
      time = 0.0;
      useMouse = false;
      ignoreKey =true;
      generateCastle();
    }
    else if(key == 'R' || key == 'r'){
      background(0,0,255);
      startBallX = 0.0;
      startBallY= height;
      useMouse = false;
      ignoreKey = true;
      time = 0.0;
    }
  }
}
      
    
    
  

    

    
    

  