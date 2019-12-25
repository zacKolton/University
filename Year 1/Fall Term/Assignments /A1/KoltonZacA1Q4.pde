/* Zac Kolton --- Assignment 1
*Instructor: Kroll
* The program draws a truck
* The truck goes a point the mouse clicked on the canvas 
* The truck bed and Wheels change colour randomly for a animation effect
*/

//orginal total truck size
final float ORGINAL_SIZE = 500;

//constant truck-bed positions and size
final float TRUCK_BEDX = -100; //150
final float TRUCK_BEDY = 0; //250
final float TRUCK_BED_SIZEX = 350; 
final float TRUCK_BED_SIZEY = 100; 
//truck position and size global variables
float truckBedX,truckBedY;
float truckBedSizeX, truckBedSizeY;

//constant drivers cab positions and size
final float DRIVE_CABX = -250; //0
final float DRIVE_CABY = -100; //150
final float DRIVE_CAB_SIZEX = 150; 
final float DRIVE_CAB_SIZEY = 200;
//driver cab position and size global variables 
float driveCabX,driveCabY;
float driveCabSizeX,driveCabSizeY;

// constant window positions and size
final float WINDOW_X = -240; //10
final float WINDOW_Y = -90; //160
final float WINDOW_SIZEX = 50;
final float WINDOW_SIZEY = 70;
//window position and size global variables 
float windowX,windowY;
float windowSizeX,windowSizeY;

//constant frontwheel positions and size
final float FRONTWHEEL_X = -100; //150
final float FRONTWHEEL_Y = 100; //350
final float FRONTWHEEL_SIZE = 70;
//frontwheel position and size global variables 
float frontWheelX,frontWheelY;
float frontWheelSize;

//constant backwheel positions and size
final float BACKWHEEL_X = 150; //400
final float BACKWHEEL_Y = 100; //350
final float BACKWHEEL_SIZE = 70;
//backwheel positions and size global variables 
float backWheelX,backWheelY;
float backWheelSize;

//constant cable (connecting to cargo) positions 
final float CABLE_X1 = -100;//150
final float CABLE_Y1 = -50; //200
final float CABLE_X2 = 50; //300
final float CABLE_Y2 = -50; //200
//cable positions global variables
float cableX1,
      cableX2,
      cableY1,
      cableY2;

//constant cargo positions 
final float CARGO_X1 = 50; //300
final float CARGO_Y1 = -50; //200
final float CARGO_X2 = 25; //275
final float CARGO_Y2 = 0; //250
final float CARGO_X3 = 75; //325
final float CARGO_Y3 = 0; //250
//cargo positions global variables 
float cargoX1,
      cargoX2,
      cargoX3,
      cargoY1,
      cargoY2,
      cargoY3;


// global mouse position 
int prevX = mouseX;
int prevY = mouseY;
//global mouse variables 
int xChange,yChange,newMouseX,newMouseY;
//global variables to change colour in animation
float randomRed,randomBlue,randomGreen;


void setup(){
  size(1000,1000);
  
}

void draw(){
  background(#F8FF40);
  animateTruck();
  moveTruck();
  drawTruck();
}

//change the truck bed and wheel colour
void animateTruck(){
  randomRed = random(255);
  randomGreen = random(100,255);
  randomBlue  = random(255);
}


//save mouse position when clicked, hence updating prevX and Y
void mouseClicked(){
   newMouseX = mouseX;
   newMouseY = mouseY;
}

//move the truck relative to where clicked on canvas
void moveTruck(){
  int speed = 20;
  xChange = (newMouseX-prevX)/speed;
  yChange = (newMouseY-prevY)/speed;
  prevX = prevX + xChange;
  prevY = prevY + yChange;
  
  
  //truck bed
   truckBedX = prevX+((TRUCK_BEDX*prevY)/ORGINAL_SIZE);
   truckBedY = prevY+((TRUCK_BEDY*prevY)/ORGINAL_SIZE);
   truckBedSizeX = ((TRUCK_BED_SIZEX*prevY)/ORGINAL_SIZE);
   truckBedSizeY = ((TRUCK_BED_SIZEY*prevY)/ORGINAL_SIZE);
   
   //drivers cab
   driveCabX = prevX+((DRIVE_CABX*prevY)/ORGINAL_SIZE);
   driveCabY = prevY+((DRIVE_CABY*prevY)/ORGINAL_SIZE);
   driveCabSizeX= ((DRIVE_CAB_SIZEX*prevY)/ORGINAL_SIZE);
   driveCabSizeY = ((DRIVE_CAB_SIZEY*prevY)/ORGINAL_SIZE);
   
   //window
   windowX = prevX+((WINDOW_X*prevY)/ORGINAL_SIZE);
   windowY = prevY+((WINDOW_Y*prevY)/ORGINAL_SIZE);
   windowSizeX = ((WINDOW_SIZEX*prevY)/ORGINAL_SIZE);
   windowSizeY = ((WINDOW_SIZEY*prevY)/ORGINAL_SIZE);
         
   //front wheel 
   frontWheelX = prevX+((FRONTWHEEL_X*prevY)/ORGINAL_SIZE);
   frontWheelY = prevY+((FRONTWHEEL_Y*prevY)/ORGINAL_SIZE);
   frontWheelSize = ((FRONTWHEEL_SIZE*prevY)/ORGINAL_SIZE);
   
   //backwheel
   backWheelX = (prevX+((BACKWHEEL_X*prevY)/ORGINAL_SIZE));
   backWheelY = (prevY+((BACKWHEEL_Y*prevY)/ORGINAL_SIZE));
   backWheelSize = ((BACKWHEEL_SIZE*prevY)/ORGINAL_SIZE);
   
   //cable
   cableX1 = (prevX+((CABLE_X1*prevY)/ORGINAL_SIZE));
   cableX2 = (prevX+((CABLE_X2*prevY)/ORGINAL_SIZE));
   cableY1 = (prevY+((CABLE_Y1*prevY)/ORGINAL_SIZE));
   cableY2 = (prevY+((CABLE_Y2*prevY)/ORGINAL_SIZE));
   //cargo
   cargoX1 = (prevX+((CARGO_X1*prevY)/ORGINAL_SIZE));
   cargoX2 = (prevX+((CARGO_X2*prevY)/ORGINAL_SIZE));
   cargoX3 = (prevX+((CARGO_X3*prevY)/ORGINAL_SIZE));
   cargoY1 = (prevY+((CARGO_Y1*prevY)/ORGINAL_SIZE));
   cargoY2 = (prevY+((CARGO_Y2*prevY)/ORGINAL_SIZE));
   cargoY3 = (prevY+((CARGO_Y3*prevY)/ORGINAL_SIZE));
}

// draw the truck
void drawTruck(){ 
    fill(randomRed,0,randomBlue);
rect(truckBedX,
     truckBedY,
     truckBedSizeX,
     truckBedSizeY); // truck bed
fill(#03BEFF);
rect(driveCabX,
     driveCabY,
     driveCabSizeX,
     driveCabSizeY); //driver cab 
fill(0);
rect(windowX,
     windowY,
     windowSizeX,
     windowSizeY); // driver window 
fill(0,randomGreen,0);
ellipse(frontWheelX,
        frontWheelY,
        frontWheelSize,
        frontWheelSize); //front wheel 
ellipse(backWheelX,
        backWheelY,
        backWheelSize,
        backWheelSize); // back wheel 

fill(#FF0B03);
line(cableX1,
     cableY1,
     cableX2,
     cableY2); //cable connecting to cargo
fill(#B7962A);
triangle(cargoX1,
         cargoY1,
         cargoX2,
         cargoY2,
         cargoX3,
         cargoY3); //cargo ontop of truck bed 

}

      