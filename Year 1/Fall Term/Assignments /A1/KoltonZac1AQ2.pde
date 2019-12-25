final int ORGINAL_SIZE = 500;

final int TRUCK_BEDX = -100; //150
final int TRUCK_BEDY = 0; //250
final int TRUCK_BED_SIZEX = 350; 
final int TRUCK_BED_SIZEY = 100; 
float truckBedX,truckBedY;
float truckBedSizeX, truckBedSizeY;

final int DRIVE_CABX = -250; //0
final int DRIVE_CABY = -100; //150
final int DRIVE_CAB_SIZEX = 150; 
final int DRIVE_CAB_SIZEY = 200;
float driveCabX,driveCabY;
float driveCabSizeX,driveCabSizeY;

final int WINDOW_X = -240; //10
final int WINDOW_Y = -90; //160
final int WINDOW_SIZEX = 50;
final int WINDOW_SIZEY = 70;
float windowX,windowY;
float windowSizeX,windowSizeY;

final int FRONTWHEEL_X = -100; //150
final int FRONTWHEEL_Y = 100; //350
final int FRONTWHEEL_SIZE = 70;
float frontWheelX,frontWheelY;
float frontWheelSize;


final int BACKWHEEL_X = 150; //400
final int BACKWHEEL_Y = 100; //350
final int BACKWHEEL_SIZE = 70;
float backWheelX,backWheelY;
float backWheelSize;


final int CABLE_X1 = -100;//150
final int CABLE_Y1 = -50; //200
final int CABLE_X2 = 50; //300
final int CABLE_Y2 = -50; //200
float cableX1,
      cableX2,
      cableY1,
      cableY2;


final int CARGO_X1 = 50; //300
final int CARGO_Y1 = -50; //200
final int CARGO_X2 = 25; //275
final int CARGO_Y2 = 0; //250
final int CARGO_X3 = 75; //325
final int CARGO_Y3 = 0; //250
float cargoX1,
      cargoX2,
      cargoX3,
      cargoY1,
      cargoY2,
      cargoY3;



void setup(){
  
size(1000,1000);
background(#F8FF40);


}

void draw(){
  setup();
  moveTruck();
  drawTruck();
  

}

void drawTruck(){ // ------------------------------------------- heres where it is -----------------------------//
  background(#F8FF40);
    fill(125);
rect(mouseX+truckBedX,
     mouseY+truckBedY,
     truckBedSizeX,
     truckBedSizeY); // truck bed
fill(#03BEFF);
rect(mouseX+driveCabX,
     mouseY+driveCabY,
     driveCabSizeX,
     driveCabSizeY); //driver cab 
fill(0);
rect(mouseX+windowX,
     mouseY+windowY,
     windowSizeX,
     windowSizeY); // driver window 
fill(#3205FF);
ellipse(mouseX+frontWheelX,
        mouseY+frontWheelY,
        frontWheelSize,
        frontWheelSize); //front wheel 
ellipse(mouseX+backWheelX,
        mouseY+backWheelY,
        backWheelSize,
        backWheelSize); // back wheel 

fill(#FF0B03);
line(mouseX+cableX1,
     mouseY+cableY1,
     mouseX+cableX2,
     mouseY+cableY2); //cable 
fill(#B7962A);
triangle(mouseX+cargoX1,
         mouseY+cargoY1,
         mouseX+cargoX2,
         mouseY+cargoY2,
         mouseX+cargoX3,
         mouseY+cargoY3); //cargo

}

void moveTruck(){
  
  //truck bed
   truckBedX = ((TRUCK_BEDX*mouseY)/ORGINAL_SIZE);
   truckBedY = ((TRUCK_BEDY*mouseY)/ORGINAL_SIZE);
   truckBedSizeX = (TRUCK_BED_SIZEX*mouseY)/ORGINAL_SIZE;
   truckBedSizeY = (TRUCK_BED_SIZEY*mouseY)/ORGINAL_SIZE;
   
   //drivers cab
         driveCabX = ((DRIVE_CABX*mouseY)/ORGINAL_SIZE);
         driveCabY = ((DRIVE_CABY*mouseY)/ORGINAL_SIZE);
         driveCabSizeX= ((DRIVE_CAB_SIZEX*mouseY)/ORGINAL_SIZE);
         driveCabSizeY = ((DRIVE_CAB_SIZEY*mouseY)/ORGINAL_SIZE);
   
   //window
         windowX = ((WINDOW_X*mouseY)/ORGINAL_SIZE);
         windowY = ((WINDOW_Y*mouseY)/ORGINAL_SIZE);
         windowSizeX = ((WINDOW_SIZEX*mouseY)/ORGINAL_SIZE);
         windowSizeY = ((WINDOW_SIZEY*mouseY)/ORGINAL_SIZE);
         
   //front wheel 
         frontWheelX = ((FRONTWHEEL_X*mouseY)/ORGINAL_SIZE);
         frontWheelY = ((FRONTWHEEL_Y*mouseY)/ORGINAL_SIZE);
         frontWheelSize = ((FRONTWHEEL_SIZE*mouseY)/ORGINAL_SIZE);
   
   //backwheel
         backWheelX = ((BACKWHEEL_X*mouseY)/ORGINAL_SIZE);
         backWheelY = ((BACKWHEEL_Y*mouseY)/ORGINAL_SIZE);
         backWheelSize = ((BACKWHEEL_SIZE*mouseY)/ORGINAL_SIZE);
   
   //cable
         cableX1 = ((CABLE_X1*mouseY)/ORGINAL_SIZE);
         cableX2 = ((CABLE_X2*mouseY)/ORGINAL_SIZE);
         cableY1 = ((CABLE_Y1*mouseY)/ORGINAL_SIZE);
         cableY2 = ((CABLE_Y2*mouseY)/ORGINAL_SIZE);
   //cargo
         cargoX1 = ((CARGO_X1*mouseY)/ORGINAL_SIZE);
         cargoX2 = ((CARGO_X2*mouseY)/ORGINAL_SIZE);
         cargoX3 = ((CARGO_X3*mouseY)/ORGINAL_SIZE);
         cargoY1 = ((CARGO_Y1*mouseY)/ORGINAL_SIZE);
         cargoY2 = ((CARGO_Y2*mouseY)/ORGINAL_SIZE);
         cargoY3 = ((CARGO_Y3*mouseY)/ORGINAL_SIZE);


  
}



  