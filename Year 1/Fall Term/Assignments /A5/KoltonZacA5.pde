//Constants that control the setup of the game
final int NUM_PITS = 6;  //Number of pits on each side of the board (not including stores)
final int INIT_NUM_OF_STONES = 4; //Initial number of stones in each pit

//The sizes of the rectangles to be used for the pits and stores
//These sizes are calculated based on canvas width and height.
float pitWidth, pitHeight, storeWidth, storeHeight;
boolean clickedOn = false;

int currentPlayer = 1;
void setup() {
  //The canvas size should be about NUM_PITS+4 units by 3 units in shape
  size(1000, 300); //This is good for NUM_PITS=6
  setPitSizes();  //Set the variables that depend on width or height
  drawMessage("This program doesn't do anything yet", 200);
  drawBoard();
  setUpBoard();
}

void draw() {
  drawBoard();
}

int[] pit = new int[(NUM_PITS*2)+2]; //to get two levels of the pits and include the stores
void setUpBoard() {
  for (int i = 0; i<=pit.length-1; i++) {
    if (i == (pit.length-1)/2 || i == pit.length-1) {
      pit[i] = 0;
      continue;
    }
    pit[i] = INIT_NUM_OF_STONES; // at every pit add num stones, skip the stores
  }
}
void drawBoard() {
  setPitSizes();
  if(clickedOn && vaildPlayersPit(index)){
    makeMove(index);
  }
  
  for (int i = 0; i<=pit.length-1; i++) {
    if (i < (pit.length-1)/2) {
      drawStonesInBoxes(pit[i], storeWidth+(pitWidth*i), pitHeight, pitWidth, pitHeight, #0D16FA, 3, 3);
    } else if (i > (pit.length-1)/2 && i < pit.length-1) {
      drawStonesInBoxes(pit[i], width - (storeWidth+(pitWidth*(i-(pit.length-1)/2))), 0, pitWidth, pitHeight, #4DDB16, 3, 3);
    } else if (i == (pit.length-1)/2) {
      drawStonesInBoxes(pit[i], storeWidth+(pitWidth*i), 0, storeWidth, storeHeight, #0D16FA, 6, 6);
    } else if (i == pit.length-1) {
      drawStonesInBoxes(pit[i], 0, 0, storeWidth, storeHeight, #4DDB16, 6, 6);
    }
  }
}

int index;
void mouseClicked() {
  if (mouseY < storeHeight && mouseY > pitHeight && mouseX < width - storeWidth && mouseX > storeWidth) {//bottom
    clickedOn = true;
    index = (int)(mouseX/pitWidth)-2;
    applyRules(index);
    if(vaildPlayersPit(index)){
       makeMove(index);
       drawMessage("Top Players turn", 200);
    }
    else {
      drawMessage("invaild move \n Top Players turn", 200);
    }
  }
  else if (mouseX > width - storeWidth) { // right store
    drawMessage("invalid", 200);
  }
  else if (mouseY < pitHeight && mouseX < width - storeWidth && mouseX > storeWidth) {//top pits 
    clickedOn = true;
    index = (int)((width - mouseX)/(pitWidth))+5;
    applyRules(index);
    if(vaildPlayersPit(index)){
      makeMove(index);
      drawMessage("bottom Players turn", 200);
    }
    else 
      drawMessage("invaild move \n Bottom Players turn", 200);
    }
  else  if (mouseX < storeWidth) { //left store
    drawMessage("invalid", 200);
  }
  if (mouseY > storeHeight ) { //bottom section invaild
    drawMessage("invaild click bottom", 200);
  }
}

void makeMove(int pitIndex) {
  int stones = pit[pitIndex];
  int index = pitIndex;
  pit[pitIndex] = 0; //make value at index 0
  while(stones > 0){
    index++;         //add index to get to go around board
    if(pitIndex >= 0 && pitIndex <= ((pit.length-2)/2)-1 && index == pit.length-1){
      index = 0;
    }
    if(pitIndex > ((pit.length-2)/2) && pitIndex <= pit.length && index == pit.length){
      index = 0;
    }
    if(pitIndex >= (((pit.length-2)/2)+1) && index == ((pit.length-2)/2)){
      index = ((pit.length-2)/2);
    }
    else{
    pit[index]++;  // at each index add one stone
    stones--;      // keep running loop till out of stones
    }
  }
}

boolean vaildPlayersPit(int index){
  boolean goodMove = false;
  if(currentPlayer == 1 && index >= 0 && index < ((pit.length-2)/2)){ // if bottom player moves change to top player 
    goodMove = true;
    currentPlayer = 2;
  }
  else if(currentPlayer == 2 && index > ((pit.length-2)/2) && index < pit.length-1){ // if top player moves change to bottom player 
    goodMove = true;
    currentPlayer = 1;
  }
  else{
    return goodMove = false;
  }
  return goodMove;
}
    
void applyRules(int index){
  int lastStone = pit[index];
  if(index >= 0 && index <= ((pit.length-2)/2)-1 && ((pit.length-2)/2) - (index + lastStone) == 0) {
      currentPlayer = 1; 
      drawMessage("same player plays again \n bottom player goes",200);
    }
    if(index > ((pit.length-2)/2) && index < pit.length && (pit.length-1) - (index + lastStone) == 0){
      currentPlayer = 2;
      drawMessage("same player plays again \n top player goes",200);
    }
}
  



//==============Given Code==========================

void setPitSizes() {
  // This sets the size variables that depend on the canvas width and height
  pitWidth = (width/(NUM_PITS+4)); //NUM_PITS pits plus a double-width store at each end
  pitHeight = (height/3); //Divide into thirds: top pits, bottom pits, and a message area
  storeWidth = (2*pitWidth); //The stores are always exactly twice the size of the pits
  storeHeight = (2*pitHeight);
}

void drawStonesInBoxes(int numStones, float left, float top, float wide, float high, int colour, int rows, int cols) {
  /*This function draws a rectangle, as specified by left, top, wide, high,
   * and with the given colour. It will then draw numStones small black circles
   * within that rectangle, using a rows x cols grid. If numStones is larger than
   * the number of stones that will fit (rows*cols), then a number will be drawn
   * in the centre of the rectangle instead.
   */

  //1. draw the rectangular area
  fill(colour);
  rect(left, top, wide, high);

  //2. If there are too many stones to fit, just draw a big number in the box
  if (numStones>rows*cols) {
    textAlign(CENTER, CENTER); //textgoes right in the middle
    fill(0);
    textSize(high/2); //and about half the size of the box
    text(numStones, left+wide/2, top+high/2);
  } else {
    fill(0);
    //3. Precalculate the spacing for the rows and columns of stone
    float xSpacing = wide/cols;
    float nextX = xSpacing/2;
    float ySpacing = high/rows;
    float nextY = ySpacing/2;
    float stoneSize = wide/(2*cols); //Fill up about half the width with stone circles
    //Draw the correct number of stones in a grid pattern
    for (int i=0; i<numStones; i++) {
      ellipse(left+nextX, top+nextY, stoneSize, stoneSize);
      nextX += xSpacing; //Move one stone to the right
      if (nextX>wide) {  //If outside the box
        nextY += ySpacing;      //go to the next row
        nextX = xSpacing/2;   //and back to the left again
      }
    }
  }
}

void drawMessage(String message, int colour) {
  //This draws a message in the bottom 1/3 of the canvas, against
  //a background of the given colour. The text is black, and sized
  //so that a 2-line message will fit.
  fill(colour);
  rect(0, height*2/3, width, height/3);
  float size = height/12.0;
  textAlign(CENTER, CENTER);
  textSize(size);
  fill(0);
  text(message, width/2, height*5/6);
}