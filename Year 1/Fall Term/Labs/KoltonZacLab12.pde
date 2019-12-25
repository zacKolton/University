int MAX_SIZE = 1000;
int[] pointX = new int[MAX_SIZE];
int[] pointY = new int[MAX_SIZE];
int amount;
void setup(){
  size(500,500);
  amount = 0;
}

void draw(){
  background(0);
  for(int i = 0; i< amount ; i++){
  fill(random(0,255),random(0,255),random(0,255));
  ellipse(pointX[i],pointY[i],20,20);
  }
}

void mouseClicked(){
  pointX[amount] = mouseX;
  pointY[amount] = mouseY;
  amount++;
}
  

  