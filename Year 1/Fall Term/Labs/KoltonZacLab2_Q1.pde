size(420,420);
int iceLeft = ((width-320)-50);
println(iceLeft);
//ice
fill(200,225,255);
rect(iceLeft,0,320,height);
//first circle red
fill(255,0,0);
ellipse(210,120,240,240);
//second circle white
fill(255);
ellipse(210,120,160,160);
//third circle blue 
fill(0,0,255);
ellipse(210,120,80,80);
//fourth circle yellow 
fill(255,255,0);
ellipse(210,120,20,20);

//lines 
//horizontal 
line(iceLeft,120,370,120);
//vertical 
line(210,0,210,500);