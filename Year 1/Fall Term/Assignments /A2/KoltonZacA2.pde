
final float ORGINAL_SIZE = 500;
/// plain head
final float HEADX = -200;
final float HEADY = -100;
final float HEAD_SIZEX = 400;
final float HEAD_SIZEY = 250; //head
float headX,headY,headSizeX,headSizeY;
///------------------ first eye choice 
final float LEFT_EYEX = -100;
final float LEFT_EYEY = 0;
final float EYE_SIZE  = 50; //left eye
float leftEyeX,leftEyeY,eyeSize;

final float RIGHT_EYEX = 100;
final float RIGHT_EYEY  = 0;
// use eyesize here
float rightEyeX,rightEyeY;
////------------------ first eye choice (end)
//// ----------------- second eye choice --- > it will be scary triangle eyes 
final float F2LEFT_EYEX1 = -150;
final float F2LEFT_EYEY1 = 50;
final float F2LEFT_EYEX2 = -100;
final float F2LEFT_EYEY2 = -50;
final float F2LEFT_EYEX3 = -50;
final float F2LEFT_EYEY3 = 50;
float f2LeftEyeX1,
      f2LeftEyeX2,
      f2LeftEyeX3,
      f2LeftEyeY1,
      f2LeftEyeY2,
      f2LeftEyeY3;
final float F2RIGHT_EYEX1 = 50;
final float F2RIGHT_EYEY1 = 50;
final float F2RIGHT_EYEX2 = 100;
final float F2RIGHT_EYEY2 = -50;
final float F2RIGHT_EYEX3 = 150;
final float F2RIGHT_EYEY3 = 50;
float f2RightEyeX1,
      f2RightEyeX2,
      f2RightEyeX3,
      f2RightEyeY1,
      f2RightEyeY2,
      f2RightEyeY3;
///------------------ second eye choice (end)
// ------------------ third eye choice -> square eyes (use EYE_SIZE)
//left eye 
final float F3LEFT_EYEX = -125;
final float F3LEFT_EYEY = -25;
float f3LeftEyeX,f3LeftEyeY;
//right eye
final float F3RIGHT_EYEX = 75;
final float F3RIGHT_EYEY = -25;
float f3RightEyeX,f3RightEyeY;
//------------ third eye choice -> square eyes (end)

///------------------------ first mouth choice -- regular choice 
final float MOUTHX1 = -100;
final float MOUTHY1 = 100;
final float MOUTHX2 = 100;
final float MOUTHY2 = 100;
float mouthX1,mouthY1,mouthX2,mouthY2;
// ---------------------------first mouth choice ----- regualar choice (end)

// ----------------------------second mouth choice ----- add smile 
//left side 
final float F2L_MOUTHX1 = -100;
final float F2L_MOUTHY1 = 100;
final float F2L_MOUTHX2 = -125;
final float F2L_MOUTHY2 = 75; 
float f2LMouthX1,
      f2LMouthX2,
      f2LMouthY1,
      f2LMouthY2;
//right side
final float F2R_MOUTHX1 = 100; // part that connects to mouth 1
final float F2R_MOUTHX2 = 125; 
final float F2R_MOUTHY1 = 100; // part that connects to mouth 1
final float F2R_MOUTHY2 = 75;
float f2RMouthX1,
      f2RMouthX2,
      f2RMouthY1,
      f2RMouthY2;
/// second mouth choice ---- add smile (end)

// --------------add ears (antenna) choice 1 ---> line to circles 
//left line
float LANTENNAX1 = 0; //part that connects to head
float LANTENNAY1 = -100; //part that connect to head
float LANTENNAX2 = -100;
float LANTENNAY2 = -150;
float leftAntennaX1,leftAntennaX2,leftAntennaY1,leftAntennaY2;

//left circle
float LANTENNA_CTOPX = -100;
float LANTENNA_CTOPY = -150;
float ANTENNA_CSIZE = 50;
float leftantennaCTopX,leftantennaCTopY,antennaCSize;
//right line 
float RANTENNAX1 = 0; // part that connects to head
float RANTENNAY1 = -100; // part that connects to head
float RANTENNAX2 = 100;
float RANTENNAY2 = -150;
float rightAntennaX1,rightAntennaX2, rightAntennaY1,rightAntennaY2;
//right circle  
float RANTENNA_CTOPX = 100;
float RANTENNA_CTOPY = -150; //use antenna size for this size 
float rightAntennaCTopX,rightAntennaCtopY;
// ---------------------------- first ear (antenna)  (end)

// --------------------------- second ear triangles on head

//left
float LEFT_EARX1 = -150; //left side of eye choice 1
float LEFT_EARY1 = -100; //left side of eye choice 1
float LEFT_EARX2 =  -50; //right side of eye choice 1
float LEFT_EARY2 = -100; //right side of eye choice 1
float LEFT_EARX3 = -100;  //middle of eye choice 1
float LEFT_EARY3 = -200; //middle of eye choice 1
float leftEarX1,leftEarX2,leftEarX3,leftEarY1,leftEarY2,leftEarY3;
//right
float RIGHT_EARX1 = 50; //left side of choice 1
float RIGHT_EARY1 = -100;//left side of choice 1
float RIGHT_EARX2 = 150; //right side of choice 1
float RIGHT_EARY2 = -100; //right side of choice 1
float RIGHT_EARX3 = 100;  //middle 
float RIGHT_EARY3 = -200; //middle
float rightEarX1,rightEarX2,rightEarX3,rightEarY1,rightEarY2,rightEarY3;
//---------------------------- second ear triangles on head  (end)
float frame;
float centerX, centerY;
int count = frameCount;
int yesno;
void setup(){
  size(1000,1000);
  frameRate(20);
  constantSizes();
}
void draw(){ // you will probably have to add  frame count or something 
 f1EyeChange();
}
  /// then you can add the functiosn under in the if statement f1 f2 f3 
  
 

void drawPlain(){
  background(255);
  fill(255);
  rect(headX,headY,headSizeX,headSizeY); 
}

void f1EyeChange(){ // you will probably have to put his all into one program at some point
  // add if pressed or something here or maybe not //******* check a2 workpad when your back a this******//
  frameRate(20);
  yesno = 0;
  if(!keyPressed && count < 180){
  background(0);
  rect(headX,headY,headSizeX,headSizeY); 
  ellipse(leftEyeX,leftEyeY,EYE_SIZE,EYE_SIZE);
  ellipse(rightEyeX,rightEyeY,EYE_SIZE,EYE_SIZE);
  } else if (keyPressed && count < 180){
    rect(headX,headY,headSizeX,headSizeY); 
    ellipse(leftEyeX,leftEyeY,EYE_SIZE,EYE_SIZE);
    ellipse(rightEyeX,rightEyeY,EYE_SIZE,EYE_SIZE);
    yesno = 1;
  }
  if(!keyPressed && 180 < count && count < 360 && yesno != 1){
    background(0);
    rect(headX,headY,headSizeX,headSizeY);
    triangle(f2LeftEyeX1,
             f2LeftEyeY1,
             f2LeftEyeX2,
             f2LeftEyeY2,
             f2LeftEyeX3,
             f2LeftEyeY3);
    triangle(f2RightEyeX1,
             f2RightEyeY1,
             f2RightEyeX2,
             f2RightEyeY2,
             f2RightEyeX3,
             f2RightEyeY3);
  }
   if(!keyPressed && 360 < count && count < 540 && yesno != 1 && yesno != 2){
    background(0);
    rect(headX,headY,headSizeX,headSizeY);
    rect(f3LeftEyeX,f3LeftEyeY,EYE_SIZE,EYE_SIZE);
    rect(f3RightEyeX,f3RightEyeY,EYE_SIZE,EYE_SIZE);
   }
}


void f2MouthChange(){
  //add if pressed
  line(mouthX1,mouthY1,mouthX2,mouthY2); //mouth line
  //wait 3 seconds
  line(mouthX1,mouthY1,mouthX2,mouthY2); //mouth line
  line(f2LMouthX1,f2LMouthY1,f2LMouthX2,f2LMouthY2); //left smile
  line(f2RMouthX1,f2RMouthY1,f2RMouthX2,f2RMouthY2); //right smile
}
void f3Ears(){
  //add if pressed
  line(leftAntennaX1,leftAntennaY1,leftAntennaX2,leftAntennaY2); //left antenna
  line(rightAntennaX1,rightAntennaY1,rightAntennaX2,rightAntennaY2); // right antenna
  ellipse(leftantennaCTopX,leftantennaCTopY,antennaCSize,antennaCSize); //left circle 
  ellipse(rightAntennaCTopX,rightAntennaCtopY,antennaCSize, antennaCSize); // right circle 
  //wait 3 seconds 
  triangle(leftEarX1,leftEarY1,leftEarX2,leftEarY2,leftEarX3,leftEarY3);
  triangle(rightEarX1,rightEarY1,rightEarX2,rightEarY2,rightEarX3,rightEarY3);
}
void instructions(){
  //tells the guy what to do 
  
}
void cyclethrough(){
}
  
  
void constantSizes(){
  centerX = width/2;
  centerY = height/2;
  headX = centerX + HEADX;
  headY = centerY + HEADY;
  headSizeX = (HEAD_SIZEX);
  headSizeY = (HEAD_SIZEY);
  ///----------------------------- eye choice 1
  //left
  leftEyeX = centerX + LEFT_EYEX; 
  leftEyeY = centerY + LEFT_EYEY;
  eyeSize = EYE_SIZE;
  //right
  rightEyeX = centerX + RIGHT_EYEX;
  rightEyeY = centerY + RIGHT_EYEY;
  //----------------------------- eye choice 1 (end)
  //----------------------------- eye choice 2 triangle ones
  //left
  f2LeftEyeX1 = centerX + F2LEFT_EYEX1;
  f2LeftEyeX2 = centerX + F2LEFT_EYEX2;
  f2LeftEyeX3 = centerX + F2LEFT_EYEX3;
  f2LeftEyeY1 = centerY + F2LEFT_EYEY1;
  f2LeftEyeY2 = centerY + F2LEFT_EYEY2;
  f2LeftEyeY3 = centerY + F2LEFT_EYEY3;
  //right
  f2RightEyeX1 = centerX + F2RIGHT_EYEX1;
  f2RightEyeX2 = centerX + F2RIGHT_EYEX2;
  f2RightEyeX3 = centerX + F2RIGHT_EYEX3;
  f2RightEyeY1 = centerY + F2RIGHT_EYEY1;
  f2RightEyeY2 = centerY + F2RIGHT_EYEY2;
  f2RightEyeY3 = centerY + F2RIGHT_EYEY3;
  
  /// ---------------------------- second eye choice triangle ones (end)
  //------------------------------ third eye choice -> squares
  //left
  f3LeftEyeX = centerX + F3LEFT_EYEX;
  f3LeftEyeY = centerY + F3LEFT_EYEY;
  //right 
  f3RightEyeX = centerX + F3RIGHT_EYEX;
  f3RightEyeY = centerY + F3RIGHT_EYEY;
  //the size is EYE_SIZE
  // --------------------------------- third eye choice -> squares (end)
  
  ///----------------------------- mouth features 
  // -----------------------------first mouth here normal 
  mouthX1 = centerX + MOUTHX1;
  mouthY1 = centerY + MOUTHY1;
  mouthX2 = centerX + MOUTHX2;
  mouthY2 = centerY + MOUTHY2;
  /// -----------------------------first mouth here normal (end)
   
  //------------------------------- second mouth here -- smile 
  //left 
  f2LMouthX1 = centerX + F2L_MOUTHX1;
  f2LMouthX2 = centerX + F2L_MOUTHX2;
  f2LMouthY1 = centerY + F2L_MOUTHY1;
  f2LMouthY2 = centerY + F2L_MOUTHY2;
  //right
  f2RMouthX1 = centerX + F2R_MOUTHX1;
  f2RMouthX2 = centerX + F2R_MOUTHX2;
  f2RMouthY1 = centerY + F2R_MOUTHY1;
  f2RMouthY2 = centerY + F2R_MOUTHY2;
  // -------------------------------second mouth here ---> smile (end)
  
  //-------------------------------- make first antenna 
  //-------------------------------- first antenna --> line with circle 
  //left (line)
  leftAntennaX1 = centerX + LANTENNAX1;
  leftAntennaX2 = centerX + LANTENNAX2;
  leftAntennaY1 = centerY + LANTENNAY1;
  leftAntennaY2 = centerY + LANTENNAY2;
  //left (circle)
  leftantennaCTopX = centerX + LANTENNA_CTOPX;
  leftantennaCTopY = centerY + LANTENNA_CTOPY;
  // right (line)
  rightAntennaX1 = centerX + RANTENNAX1;
  rightAntennaX2 = centerX + RANTENNAX2;
  rightAntennaY1 = centerY + RANTENNAY1;
  rightAntennaY2 = centerY + RANTENNAY2;
  //right (circle)
  rightAntennaCTopX = centerX + RANTENNA_CTOPX;
  rightAntennaCtopY = centerY + RANTENNA_CTOPY;
  // antenna circle size
  antennaCSize = ANTENNA_CSIZE;
  // ---------------------------------- first ears (antenna) (end) line with circle 
  //----------------------------------- second ears --> triangle on head
  //left
  leftEarX1 = centerX + LEFT_EARX1;
  leftEarX2 = centerX + LEFT_EARX2;
  leftEarX3 = centerX + LEFT_EARX3;
  leftEarY1 = centerY + LEFT_EARY1;
  leftEarY2 = centerY + LEFT_EARY2;
  leftEarY3 = centerY + LEFT_EARY3;
  
  //right
  rightEarX1 = centerX + RIGHT_EARX1;
  rightEarX2 = centerX + RIGHT_EARX2;
  rightEarX3 = centerX + RIGHT_EARX3;
  rightEarY1 = centerY + RIGHT_EARY1;
  rightEarY2 = centerY + RIGHT_EARY2;
  rightEarY3 = centerY + RIGHT_EARY3;
  
  //----------------------------------- second ears --> triangle on head (end) 
}