
char newKey;
float leftX,rightX,top,bottom,y,x;
void setup(){
  background(255);
  size(500,500);
  y = height;
  x = width;
 
}

void draw(){

}

void keyPressed(){
  newKey = key;
  leftX = textWidth(newKey);
  rightX= x - leftX;
  top   = textAscent() + textDescent();
  bottom= y - top;
  fill(random(255),random(255),random(255));
  textSize(25);
  text(newKey,random(leftX,rightX),random(top,bottom));
  if(newKey == ENTER){
    background(255);
  }
  print(newKey);
}

 
    

  
  
 

    
    
   
 