import javafx.scene.shape.Ellipse;
int size = mouseX; 
int shade = mouseY;
final int MAX_SIZE = 100;
void setup(){
  background(0);
  size(700,700);
}

void drawCircle(){
  ellipse(mouseX,mouseY,((MAX_SIZE*mouseX)/width),((MAX_SIZE*mouseX)/width));
}
void setSizeAndShade(){
  fill(mouseY);
}

void draw(){
  fill((255*mouseY)/height);
  ellipse(mouseX,mouseY,((MAX_SIZE*mouseX)/width),((MAX_SIZE*mouseX)/width));
}
  