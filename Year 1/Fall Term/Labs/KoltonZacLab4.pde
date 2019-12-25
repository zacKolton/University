float angle = 0.0;
final  float SPEED = 0.03;
final int RADIUS_X = 150;
final int RADIUS_Y = 35;
int sunSize = 50;
int earthSize = sunSize;
void setup(){
  size(500,500);
}

void draw(){
  background(0);
  fill(#FFFF00);
  ellipse(width/2,height/2,sunSize,sunSize);
  makeCircle();
  changeCircle();
angle = angle + SPEED;
println(earthSize/2 + "," + angle);
}

void changeCircle(){
  if(angle == atan2(((height/2)+(RADIUS_Y*cos(angle))- height/2),(((width/2)+(RADIUS_X*sin(angle))- width/2)))){
    earthSize = (earthSize + 10)/2;
  } else if(angle > PI && angle < 0.0){
    earthSize = (earthSize - 10)/2;
    }else{
      print("didnt work ");
    }
}

void makeCircle(){
  fill(#001BFF);
  ellipse(((width/2)+(RADIUS_X*sin(angle))),
          ((height/2)+(RADIUS_Y*cos(angle))),
          earthSize/2,earthSize/2);
}