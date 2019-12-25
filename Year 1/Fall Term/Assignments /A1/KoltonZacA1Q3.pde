final float ORGINAL_SIZE = 500;

final float TRUCK_BEDX = -100; //150
final float TRUCK_BEDY = 0; //250
final float TRUCK_BED_SIZEX = 350; 
final float TRUCK_BED_SIZEY = 100; 
float truckBedX,truckBedY;
float truckBedSizeX, truckBedSizeY;

final float DRIVE_CABX = -250; //0
final float DRIVE_CABY = -100; //150
final float DRIVE_CAB_SIZEX = 150; 
final float DRIVE_CAB_SIZEY = 200;
float driveCabX,driveCabY;
float driveCabSizeX,driveCabSizeY;

final float WINDOW_X = -240; //10
final float WINDOW_Y = -90; //160
final float WINDOW_SIZEX = 50;
final float WINDOW_SIZEY = 70;
float windowX,windowY;
float windowSizeX,windowSizeY;

final float FRONTWHEEL_X = -100; //150
final float FRONTWHEEL_Y = 100; //350
final float FRONTWHEEL_SIZE = 70;
float frontWheelX,frontWheelY;
float frontWheelSize;


final float BACKWHEEL_X = 150; //400
final float BACKWHEEL_Y = 100; //350
final float BACKWHEEL_SIZE = 70;
float backWheelX,backWheelY;
float backWheelSize;


final float CABLE_X1 = -100;//150
final float CABLE_Y1 = -50; //200
final float CABLE_X2 = 50; //300
final float CABLE_Y2 = -50; //200
float cableX1,
      cableX2,
      cableY1,
      cableY2;


final float CARGO_X1 = 50; //300
final float CARGO_Y1 = -50; //200
final float CARGO_X2 = 25; //275
final float CARGO_Y2 = 0; //250
final float CARGO_X3 = 75; //325
final float CARGO_Y3 = 0; //250
float cargoX1,
      cargoX2,
      cargoX3,
      cargoY1,
      cargoY2,
      cargoY3;



int lastX = mouseX;
int lastY = mouseY;
int xChange,yChange,newMouseX,newMouseY;


void setup(){
  size(1000,1000);
  background(#F8FF40);
}

void draw(){
  moveTruck();
  drawTruck();
  println(lastX + "," + lastY);

}




void mouseClicked(){
 print("its working");
 
   newMouseX = mouseX;
   newMouseY = mouseY;
   

}
void moveTruck(){/// you probably have to replace height with mouseY
  int speed = 20;
  xChange = (newMouseX-lastX)/speed;
  yChange = (newMouseY-lastY)/speed;
  lastX = lastX + xChange;
  lastY = lastY + yChange;
  //truck bed
   truckBedX = lastX+((TRUCK_BEDX*lastY)/ORGINAL_SIZE);
   truckBedY = lastY+((TRUCK_BEDY*lastY)/ORGINAL_SIZE);
   truckBedSizeX = ((TRUCK_BED_SIZEX*lastY)/ORGINAL_SIZE);
   truckBedSizeY = ((TRUCK_BED_SIZEY*lastY)/ORGINAL_SIZE);
   
   //drivers cab
         driveCabX = lastX+((DRIVE_CABX*lastY)/ORGINAL_SIZE);
         driveCabY = lastY+((DRIVE_CABY*lastY)/ORGINAL_SIZE);
         driveCabSizeX= ((DRIVE_CAB_SIZEX*lastY)/ORGINAL_SIZE);
         driveCabSizeY = ((DRIVE_CAB_SIZEY*lastY)/ORGINAL_SIZE);
   
   //window
         windowX = lastX+((WINDOW_X*lastY)/ORGINAL_SIZE);
         windowY = lastY+((WINDOW_Y*lastY)/ORGINAL_SIZE);
         windowSizeX = ((WINDOW_SIZEX*lastY)/ORGINAL_SIZE);
         windowSizeY = ((WINDOW_SIZEY*lastY)/ORGINAL_SIZE);
         
   //front wheel 
         frontWheelX = lastX+((FRONTWHEEL_X*lastY)/ORGINAL_SIZE);
         frontWheelY = lastY+((FRONTWHEEL_Y*lastY)/ORGINAL_SIZE);
         frontWheelSize = ((FRONTWHEEL_SIZE*lastY)/ORGINAL_SIZE);
   
   //backwheel
         backWheelX = (lastX+((BACKWHEEL_X*lastY)/ORGINAL_SIZE));
         backWheelY = (lastY+((BACKWHEEL_Y*lastY)/ORGINAL_SIZE));
         backWheelSize = ((BACKWHEEL_SIZE*lastY)/ORGINAL_SIZE);
   
   //cable
         cableX1 = (lastX+((CABLE_X1*lastY)/ORGINAL_SIZE));
         cableX2 = (lastX+((CABLE_X2*lastY)/ORGINAL_SIZE));
         cableY1 = (lastY+((CABLE_Y1*lastY)/ORGINAL_SIZE));
         cableY2 = (lastY+((CABLE_Y2*lastY)/ORGINAL_SIZE));
   //cargo
         cargoX1 = (lastX+((CARGO_X1*lastY)/ORGINAL_SIZE));
         cargoX2 = (lastX+((CARGO_X2*lastY)/ORGINAL_SIZE));
         cargoX3 = (lastX+((CARGO_X3*lastY)/ORGINAL_SIZE));
         cargoY1 = (lastY+((CARGO_Y1*lastY)/ORGINAL_SIZE));
         cargoY2 = (lastY+((CARGO_Y2*lastY)/ORGINAL_SIZE));
         cargoY3 = (lastY+((CARGO_Y3*lastY)/ORGINAL_SIZE));


  
}
void drawTruck(){ // ------------------------------------------- heres where it is -----------------------------//
 // you will need to change centerx and y to the previous location and shit 
 
  background(#F8FF40);
    fill(125);
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
fill(#3205FF);
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
     cableY2); //cable 
fill(#B7962A);
triangle(cargoX1,
         cargoY1,
         cargoX2,
         cargoY2,
         cargoX3,
         cargoY3); //cargo

}

      
   