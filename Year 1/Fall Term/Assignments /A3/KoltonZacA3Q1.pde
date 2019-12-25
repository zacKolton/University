float centerX,centerY;

final float CAR_BODYX = -490; // car body // x's --> -490,-465,-465,-365,-365
final float CAR_BODYY = -50;
final float BODY_SIZEX = 200;
final float BODY_SIZEY = 50;
float carX,carY,carSizeX,carSizeY;

final float TOP_L_WHEELX = -465; // top left wheel
final float TOP_L_WHEELY= -75;
final float WHEEL_SIZEX = 50;
final float WHEEL_SIZEY = 25;
float topLeftWheelX,topLeftWheelY,wheelSizeX,wheelSizeY;

final float BOTTOM_L_WHEELX = -465; // bottom left wheel
final float BOTTOM_L_WHEELY = 0 ;
float bottomLeftWheelX,bottomLeftWheelY;

final float TOP_R_WHEELX = -365; // top right wheel
final float TOP_R_WHEELY= -75;
float topRightWheelX,topRightWheelY;

final float BOTTOM_R_WHEELX = -365; //bottom right wheel
final float BOTTOM_R_WHEELY = 0;
float bottomRightWheelX,bottomRightWheelY;

final float ROADX = 0; // road 
final float ROADY = -125;
final float ROAD_SIZEX = width*width;
final float ROAD_SIZEY = 200;
float roadX,roadY,roadSizeX,roadSizeY;

final float LINEX = 0;
final float LINEY = -25;
final float LINE_SIZEX = 100;
final float LINE_SIZEY = 10;
float lineX,lineY,lineSizeX,lineSizeY;

final float CAR_SPEED = 1;

int count;
float lineSpace,carSpace, toHere;
void setup(){
  size(1000,1000);
  centerX = width/2;
  centerY = height/2;
  constantSizes();

}
void draw(){
  moveCars();
  road();
  loopRoadLine();
  loopCar();
}
  
void constantSizes(){
  
  carX = CAR_BODYX; 
  carY = centerY + CAR_BODYY;
  carSizeX = BODY_SIZEX;
  carSizeY = BODY_SIZEY;
  
  topLeftWheelX = TOP_L_WHEELX;
  topLeftWheelY = centerY + TOP_L_WHEELY;
  bottomLeftWheelX = BOTTOM_L_WHEELX;
  bottomLeftWheelY = centerY + BOTTOM_L_WHEELY;
  
  topRightWheelX = TOP_R_WHEELX;
  topRightWheelY = centerY + TOP_R_WHEELY;
  bottomRightWheelX = BOTTOM_R_WHEELX;
  bottomRightWheelY = centerY + BOTTOM_R_WHEELY;
  
  wheelSizeX = WHEEL_SIZEX;
  wheelSizeY = WHEEL_SIZEY;
  
  roadX = ROADX; 
  roadY = centerY + ROADY;
  roadSizeX = ROAD_SIZEX;
  roadSizeY = ROAD_SIZEY;
  
  lineX = LINEX;
  lineY = centerY + LINEY;
  lineSizeX = LINE_SIZEX;
  lineSizeY = LINE_SIZEY;
  
  
}

void loopRoadLine(){
  fill(#F6FF03);
  rect(lineX,lineY,lineSizeX,lineSizeY);
  count = 0;
  lineSpace = lineSizeX + 12;
  while(count <= width){
    rect(lineX+(lineX + (count*lineSpace)), lineY,lineSizeX,lineSizeY);
  count ++;
  }
}
void loopCar(){
  count = 0;
  carSpace = carSizeX + 100;
  while(count <= width / carSpace){
  fill(0,0,255);
  rect(carX + (carX + (count*carSpace)),carY,carSizeX,carSizeY);
  fill(0);
  rect(topLeftWheelX + (carX + (count*carSpace)),
       topLeftWheelY,
        wheelSizeX,
        wheelSizeY);//top left wheel
  rect(bottomLeftWheelX + (carX + (count*carSpace)),
       bottomLeftWheelY,
       wheelSizeX,
       wheelSizeY); //bottom left
  rect(topRightWheelX + (carX + (count*carSpace)),
       topRightWheelY,
       wheelSizeX,
       wheelSizeY); //top right wheel
  rect(bottomRightWheelX + (carX + (count*carSpace)),
       bottomRightWheelY,
       wheelSizeX,
       wheelSizeY);//bottom right
       count++;
  }
  println(count);
}

void moveCars(){
  carX += CAR_SPEED;
  topLeftWheelX += CAR_SPEED;
  topRightWheelX += CAR_SPEED;
  bottomLeftWheelX += CAR_SPEED;
  bottomRightWheelX += CAR_SPEED;
}

void road(){
  fill(255);
  rect(roadX,roadY,roadSizeX,roadSizeY); //road         
}