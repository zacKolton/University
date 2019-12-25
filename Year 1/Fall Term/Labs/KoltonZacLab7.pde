float radius,
      centerX,
      centerY,
      angle;
int points;
void setup(){
  background(0);
  size(500,500);
  centerX = (float)width/2;
  centerY = (float)height/2;
}
void draw(){
makeLines();
}

void makeLines(){
  radius = 100.0;
  for(int count = 0; count < 2*PI; count++)
  {
    line(centerX + (radius*cos(angle)),
         centerY + (radius*sin(angle)),
         centerX + (radius*cos(count + angle)),
         centerY + (radius*sin(count + angle)));
  }
  println(centerX + (radius*cos((angle * (1/9)))));
}

  