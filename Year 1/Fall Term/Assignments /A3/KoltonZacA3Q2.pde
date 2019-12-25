float centerX,centerY;

final float CAR_BODYX = -490; // car body 
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
  constantSceneSizes();

}
void draw(){
  makeScenes();
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
  fill(155);
  rect(roadX,roadY,roadSizeX,roadSizeY); //road         
}
//--------------------------------------------------this is where everything for the scenes start
int sceneCount  = 0;
boolean yes;                                         
void mouseClicked(){
  yes = true;
  sceneCount = sceneCount + 1;
  if(sceneCount > 2){
    sceneCount = 0;
  }
}
final float SNOWMAN_TOPX = 50; //-445
final float SNOWMAN_TOPY = 25; //-475
final float TOP_SIZEXY   = 30;
final float SNOWMAN_MIDX = 50; //-445
final float SNOWMAN_MIDY = 50; //-450
final float MID_SIZEXY   = 50;
final float SNOWMAN_BOTX = 50; //-445
final float SNOWMAN_BOTY = 100; //-400
final float BOT_SIZEXY   = 90;
      float snowManTopX,snowManTopY,topSizeXY,
            snowManMidX,snowManMidY,midSizeXY,
            snowManBotX,snowManBotY,botSizeXY; /// all scene 1

final float TREE_TRUNKX = 50;
final float TREE_TRUNKY = 30;
final float TRUNK_SIZEY = 80;
final float TRUNK_SIZEX = 10;
final float BRANCH1X = 40;
final float BRANCH1Y = 30;
final float BRANCH2X = 30;
final float BRANCH2Y = 45;
final float BRANCH3X = 20;
final float BRANCH3Y = 60;
final float BRANCH4X = 10;
final float BRANCH4Y = 75;
final float BRANCH_SIZEY = 10;
final float BRANCH_SIZE1X = 30;
final float BRANCH_SIZE2X = 50;
final float BRANCH_SIZE3X = 70;
final float BRANCH_SIZE4X = 90;
      float trunkX,trunkY,trunkSizeY,trunkSizeX,
            branch1X,branch1Y,
            branch2X,branch2Y,
            branch3X,branch3Y,
            branch4X,branch4Y,
            branchSizeY,
            branchSize1X,
            branchSize2X,
            branchSize3X,
            branchSize4X;

final float ROOFX1 = 50;
final float ROOFX2 = 100;
final float ROOFX3 = 150;
final float ROOFY1 = 100;
final float ROOFY2 = 50;
final float ROOFY3 = 100;
final float HOUSEX = 63;
final float HOUSEY = 100;
final float HOUSE_SIZEX = 75;
final float HOUSE_SIZEY = 80;
final float DOORX = 90;
final float DOORY = 140;
final float DOOR_SIZEX = 20;
final float DOOR_SIZEY = 40;
final float L_WINDOWX  = 70;
final float WINDOWY    = 110;
final float R_WINDOWX  = 110;
final float WINDOW_SIZE= 20;
      float roofX1,roofX2,roofX3,
            roofY1,roofY2,roofY3,
            houseX,houseY,houseSizeX,houseSizeY,
            doorX,doorY,doorSizeX,doorSizeY,
            windowLeftX,windowRightX,windowY,windowSize;



void constantSceneSizes(){ 
  snowManTopX =  SNOWMAN_TOPX;
  snowManTopY =  SNOWMAN_TOPY;
  topSizeXY   = TOP_SIZEXY;
  snowManMidX =  SNOWMAN_MIDX;
  snowManMidY =  SNOWMAN_MIDY;
  midSizeXY   = MID_SIZEXY;
  snowManBotX = SNOWMAN_BOTX;
  snowManBotY = SNOWMAN_BOTY;
  botSizeXY   = BOT_SIZEXY;
  
  trunkX  = TREE_TRUNKX;
  trunkY  = TREE_TRUNKY;
  trunkSizeX  = TRUNK_SIZEX;
  trunkSizeY  = TRUNK_SIZEY;
  branchSizeY = BRANCH_SIZEY;
  branchSize1X= BRANCH_SIZE1X; 
  branchSize2X= BRANCH_SIZE2X;
  branchSize3X= BRANCH_SIZE3X;
  branchSize4X= BRANCH_SIZE4X;
  branch1X   = BRANCH1X;
  branch2X   = BRANCH2X;
  branch3X   = BRANCH3X;
  branch4X   = BRANCH4X;
  branch1Y   = BRANCH1Y;
  branch2Y   = BRANCH2Y;
  branch3Y   = BRANCH3Y;
  branch4Y   = BRANCH4Y;
  
  roofX1 = ROOFX1;
  roofX2 = ROOFX2;
  roofX3 = ROOFX3;
  roofY1 = ROOFY1;
  roofY2 = ROOFY2;
  roofY3 = ROOFY3;
  houseX = HOUSEX;
  houseY = HOUSEY;
  houseSizeX = HOUSE_SIZEX;
  houseSizeY = HOUSE_SIZEY;
  doorX = DOORX;
  doorY = DOORY;
  doorSizeX = DOOR_SIZEX;
  doorSizeY = DOOR_SIZEY;
  windowLeftX = L_WINDOWX;
  windowRightX= R_WINDOWX;
  windowY     = WINDOWY;
  windowSize  = WINDOW_SIZE;
  
}
  
void makeScenes(){
  if(yes && sceneCount == 0){
    scene1();
  }
  else if(yes && sceneCount == 1){
    scene2();
  }
  else if(yes && sceneCount == 2){
    scene3();
  }
}
int snowNY = 2;
int snowNX = 6;
float snowSpacingH, snowSpacingV;
float overRoad = roadY + roadSizeY;
void scene1(){
  background(#6EC8F7);
  fill(255);
  noStroke();
  snowSpacingH = (float)width/snowNX; 
  snowSpacingV = (float)roadY/snowNY; 
  for(int row=0; row<snowNY; row=row+1){
    float rowY = snowSpacingV*(row + .5); //the vertical position 
    for(int col=0; col<snowNX; col=col+1){
      float colX = snowSpacingH*(col+0.5);
      ellipse(colX,(rowY + snowManTopY)- botSizeXY,topSizeXY,topSizeXY);
      ellipse(colX,(rowY + snowManMidY)- botSizeXY,midSizeXY,midSizeXY);
      ellipse(colX,(rowY + snowManBotY)- botSizeXY,botSizeXY,botSizeXY);
    }
  }
  for(int row=0; row<snowNY; row=row+1){
    float rowY = snowSpacingV*(row + .5); 
    for(int col=0; col<snowNX; col=col+1){
      float colX = snowSpacingH*(col+0.5);
      ellipse(colX,roadY + roadSizeY+((rowY + snowManTopY)- botSizeXY),topSizeXY,topSizeXY);
      ellipse(colX,roadY + roadSizeY+((rowY + snowManMidY)- botSizeXY),midSizeXY,midSizeXY);
      ellipse(colX,roadY + roadSizeY+((rowY + snowManBotY)- botSizeXY),botSizeXY,botSizeXY);
    }
  }
}
int treeNX = 9;
int treeNY = 3;
float treeSpacingX,treeSpacingY;
void scene2(){
      treeSpacingX = ((float)width/treeNX) + 10;
      treeSpacingY = (float)roadY/treeNY;
  background(#67480A);
  noStroke();
  for(int row=0; row<treeNY; row=row+1){
    float rowY = treeSpacingY*(row + .5); 
    for(int col=0; col<treeNX; col=col+1){
      float colX = treeSpacingX*(col+ .5);
      fill(255);
      rect(colX + branch4X,(rowY + trunkY)-trunkSizeY,trunkSizeX,trunkSizeY);
      fill(#115D14);
      rect(colX                   ,((rowY + branch1Y)-trunkSizeY),branchSize1X,branchSizeY);
      rect(colX - (branch2X/3)    ,((rowY + branch2Y)-trunkSizeY),branchSize2X,branchSizeY);
      rect(colX - (branch3X)      ,((rowY + branch3Y)-trunkSizeY),branchSize3X,branchSizeY);
      rect(colX - (branchSize4X/3),((rowY + branch4Y)-trunkSizeY),branchSize4X,branchSizeY);
    }
  }
  for(int row=0; row<treeNY; row=row+1){
    float rowY = treeSpacingY*(row + .5); 
    for(int col=0; col<treeNX; col=col+1){
      float colX = treeSpacingX*(col+ .5);
      fill(255);
      rect(colX + (branch4X)      ,roadY + roadSizeY+((rowY + trunkY)-trunkSizeY),trunkSizeX,trunkSizeY);
      fill(#115D14);
      rect(colX                   ,roadY + roadSizeY+((rowY + branch1Y)-trunkSizeY),branchSize1X,branchSizeY);
      rect(colX - (branch2X/3)    ,roadY + roadSizeY+((rowY + branch2Y)-trunkSizeY),branchSize2X,branchSizeY);
      rect(colX - (branch3X)      ,roadY + roadSizeY+((rowY + branch3Y)-trunkSizeY),branchSize3X,branchSizeY);
      rect(colX - (branchSize4X/3),roadY + roadSizeY+((rowY + branch4Y)-trunkSizeY),branchSize4X,branchSizeY);
    }
  }
}

int houseNX = 10;
int houseNY = 2;
float houseSpacingX,houseSpacingY;
int correct = 100;
void scene3(){
      houseSpacingX = ((float)width/houseNX) + 10;
      houseSpacingY = (float)roadY/houseNY;
  background(0,255,0);
  noStroke();
  for(int row=0; row<houseNY; row=row+1){
    float rowY = houseSpacingY*(row + .5); 
    for(int col=0; col<houseNX; col=col+1){
      float colX = houseSpacingX*(col+ .5);
      fill(#4D3E0C);
      triangle(colX + (roofX1 - correct),(rowY + roofY1) - (houseSizeY + 50),
               colX + (roofX2 - correct),(rowY + roofY2) - (houseSizeY + 50),
               colX + (roofX3 - correct),(rowY + roofY3) - (houseSizeY + 50));
      fill(255,0,0);
      rect(colX + (houseX - correct), (rowY + houseY)- (houseSizeY + 51),houseSizeX,houseSizeY);
      fill(0);
      rect(colX + (doorX - correct), (rowY + doorY) - (houseSizeY + 51),doorSizeX,doorSizeY);
      fill(random(255),random(255),random(255)); /// change window color at random
      rect(colX + (windowLeftX - correct), (rowY + windowY) - (houseSizeY + 51),windowSize,windowSize);
      rect(colX + (windowRightX- correct),(rowY + windowY) - (houseSizeY + 51),windowSize,windowSize);
    }
  }
  for(int row=0; row<houseNY; row=row+1){
    float rowY = houseSpacingY*(row + .5); 
    for(int col=0; col<houseNX; col=col+1){
      float colX = houseSpacingX*(col+ .5);
      fill(#4D3E0C);
      triangle(colX + (roofX1 - correct),roadY + roadSizeY+((rowY + roofY1) - (houseSizeY + 50)),
               colX + (roofX2 - correct),roadY + roadSizeY+((rowY + roofY2) - (houseSizeY + 50)),
               colX + (roofX3 - correct),roadY + roadSizeY+((rowY + roofY3) - (houseSizeY + 50)));
      fill(255,0,0);
      rect(colX + (houseX - correct),roadY + roadSizeY+((rowY + houseY)- (houseSizeY + 51)),houseSizeX,houseSizeY);
      fill(0);
      rect(colX + (doorX - correct),roadY + roadSizeY+((rowY + doorY) - (houseSizeY + 51)),doorSizeX,doorSizeY);
      fill(random(255),random(255),random(255));
      rect(colX + (windowLeftX - correct),roadY + roadSizeY+((rowY + windowY) - (houseSizeY + 51)),windowSize,windowSize);
      rect(colX + (windowRightX- correct),roadY + roadSizeY+((rowY + windowY) - (houseSizeY + 51)),windowSize,windowSize);
    }
  }
}
  

      
      
    
      
     
      
  

  
  

  

     



 
 

  