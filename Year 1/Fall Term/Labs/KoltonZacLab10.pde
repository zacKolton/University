/**
 * Template - Bronze exercise
 * Lab 10 - COMP 1010
 * 
 * Simplified Yahtzee game - phase 1
 * Roll the dice, and display them, when the mouse is clicked.
 * 
 */

//Constants controlling the game.
//The number of sides should not be changed since the
//graphics only handles from 1-6 dots.
//In this version, the number of dice could be varied.
final int NUM_SIDES=6;   //Sides on the dice
final int NUM_DICE=5;    //The number of dice used

//-------- Bronze -------------------------------------

//***Add the variable storing the dice here:
int[] numDice = new int[NUM_DICE];
int[] side    = new int[NUM_SIDES];
int sideOnDie;

int dice;
void rollDice(){
  for(int i = 0; i<=numDice.length-1; i++){
    numDice[i] = i;
    dice = (int)random(0,NUM_DICE);
  }
    
    
  for(int q = 0; q <= side.length-1; q++){

    side[q] = q;
    sideOnDie = (int)random(1,NUM_SIDES);
  }
}
    
  

  
//rollDice

void showDiceRoll(){
  drawDie(dice,sideOnDie);
  println(dice);
}



//showDiceRoll



//==================================================================
//
//    SUPPLIED CODE

void setup(){
  size(500,500);
  displayMessage("Click to roll");
}//setup

//--------------------------------------------------------------

void draw(){
}//draw

//--------------------------------------------------------------

void mouseClicked(){
  rollDice();
  showDiceRoll();
  
}

//--------------------------------------------------------------

void displayMessage(String message){
  //Display the given message in the centre of the window.
  textSize(24);
  fill(0);
  text(message,(width-textWidth(message))/2,height/2);
}

//--------------------------------------------------------------

void drawDie(int position, int value){
  /* Draw one die in thecanvas.
   * **This will only work for dice with up to 6 sides.**
   *   position - must be 0..NUM_DICE-1, indicating which die is being drawn
   *   value    - must be 1..6, the amount showing on that die
   */
  final float X_SPACING = ((float)width/NUM_DICE);       //X spacing of the dice
  final float DIE_SIZE = X_SPACING*0.8;    //width and height of one die
  final float X_LEFT_DIE = X_SPACING*0.1;  //left side of the leftmost die
  final float Y_OFFSET = X_SPACING*0.15;   //slight Y offset of the odd-numbered ones
  final float Y_POSITION = height-DIE_SIZE-Y_OFFSET; //Y coordinate of most dice
  final float PIP_OFFSET = DIE_SIZE/3.5;  //Distance from centre to pips, and between pips
  final float PIP_DIAM = DIE_SIZE/5;    //Diameter of the pips (dots)
  
  //From the constants above, and which die it is, find its top left corner
  float dieX = X_LEFT_DIE+position*X_SPACING;
  float dieY = Y_POSITION-Y_OFFSET*(position%2);
  
  //1.Draw a red square with a black outline
  stroke(0); //Black outline
  fill(255,0,0); //Red fill
  rect(dieX,dieY,DIE_SIZE,DIE_SIZE);
  
  //2.Draw the pips (dots)
  fill(255);   //White dots
  stroke(255); //White outline
  
  //The centre dot (if the value is odd)
  if(1 == value%2)
    ellipse(dieX+DIE_SIZE/2,dieY+DIE_SIZE/2,PIP_DIAM,PIP_DIAM);
    
  //The top-left and bottom-right dots (if the value is more than 1)
  if(value>1){
    ellipse(dieX+DIE_SIZE/2-PIP_OFFSET,
            dieY+DIE_SIZE/2+PIP_OFFSET,PIP_DIAM,PIP_DIAM);
    ellipse(dieX+DIE_SIZE/2+PIP_OFFSET,
            dieY+DIE_SIZE/2-PIP_OFFSET,PIP_DIAM,PIP_DIAM);
  }//if
  
  //The bottom-left and top-right dots (if the value is more than 3)
  if(value>3){
    ellipse(dieX+DIE_SIZE/2+PIP_OFFSET,
            dieY+DIE_SIZE/2+PIP_OFFSET,PIP_DIAM,PIP_DIAM);
    ellipse(dieX+DIE_SIZE/2-PIP_OFFSET,
            dieY+DIE_SIZE/2-PIP_OFFSET,PIP_DIAM,PIP_DIAM);
  }//if
  
  //The left and right dots (only if the value is 6)
  if(value==6){
    ellipse(dieX+DIE_SIZE/2-PIP_OFFSET,
            dieY+DIE_SIZE/2,PIP_DIAM,PIP_DIAM);
    ellipse(dieX+DIE_SIZE/2+PIP_OFFSET,
            dieY+DIE_SIZE/2,PIP_DIAM,PIP_DIAM);
  }//if
  
}//drawDie