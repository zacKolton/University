size(420,420);
int centreIce   =width/2;
int diam12Foot  =((12*height)/21);
int tLine       =(diam12Foot/2);
int diam1Foot   =((1*height)/21);
int diam4Foot   =((4*height)/21);
int diam8Foot   =((8*height)/21);
int iceWidth    =((16*width/21));
int iceLeft     =((width - iceWidth)/2);
println("the vertcal centre line is at: " + centreIce + " the horizontal centre line is at: " 
+ tLine + " and the ice width is: " + iceWidth);
//ice blue 
fill(0,225,255);
rect(iceLeft, 0, iceWidth,height);
//first circle red
fill(255,0,0);
ellipse(centreIce, tLine, diam12Foot, diam12Foot);
//second circle white 
fill(255);
ellipse(centreIce,tLine, diam8Foot, diam8Foot);
//third circle blue 
fill(0,0,255);
ellipse(centreIce, tLine, diam4Foot, diam4Foot);
//fourth circle yellow
fill(255,255,0);
ellipse(centreIce, tLine, diam1Foot, diam1Foot);


//lines
//horizontal
line(iceLeft, tLine, (iceWidth + iceLeft), tLine);
//vertcal
line(centreIce, 0, centreIce, height);