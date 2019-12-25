size(1000,1000);
background(#F8FF40);
// make everythig revaltive to the center
final int TRUCK_BEDX = -100;
final int TRUCK_BEDY = 0;
final int TRUCK_BED_SIZEX = 350;
final int TRUCK_BED_SIZEY = 100;



final int DRIVE_CABX = -250; // or just make zero when drawing
final int DRIVE_CABY = -100;
final int DRIVE_CAB_SIZEX = 150;
final int DRIVE_CAB_SIZEY = 200;


final int WINDOW_X = -240;
final int WINDOW_Y = -90;
final int WINDOW_SIZEX = 50;
final int WINDOW_SIZEY = 70;


final int FRONTWHEEL_X = -100;
final int FRONTWHEEL_Y = 100; // more than 250
final int FRONTWHEEL_SIZE = 70;


final int BACKWHEEL_X = 150; //more than 250
final int BACKWHEEL_Y = 100; //more than 250
final int BACKWHEEL_SIZE = 70;


final int CABLE_X1 = -100;
final int CABLE_Y1 = -50;
final int CABLE_X2 = 100; // more than 250
final int CABLE_Y2 = -50;


final int CARGO_X1 = 50; // more than 250
final int CARGO_Y1 = -50;
final int CARGO_X2 = 25; // more than 250
final int CARGO_Y2 = 0;
final int CARGO_X3 = 75; //more than 250
final int CARGO_Y3 = 0;




fill(125);
rect((width/2)+TRUCK_BEDX,(height/2)+TRUCK_BEDY,TRUCK_BED_SIZEX,TRUCK_BED_SIZEY); // truck bed-final done
fill(#03BEFF);
rect((width/2)+DRIVE_CABX,(height/2)+DRIVE_CABY,DRIVE_CAB_SIZEX,DRIVE_CAB_SIZEY); //driver cab - finla done
fill(0);
rect((width/2)+WINDOW_X,(width/2)+WINDOW_Y,WINDOW_SIZEX,WINDOW_SIZEY); // driver window - final done
fill(#3205FF);
ellipse((width/2)+FRONTWHEEL_X,(height/2)+FRONTWHEEL_Y,FRONTWHEEL_SIZE,FRONTWHEEL_SIZE); //front wheel - final done
ellipse((width/2)+BACKWHEEL_X,(height/2)+BACKWHEEL_Y,BACKWHEEL_SIZE,BACKWHEEL_SIZE); // back wheel - final done

fill(#FF0B03);
line((width/2)+CABLE_X1,(height/2)+CABLE_Y1,(width/2)+CABLE_X2,(height/2)+CABLE_Y2); //cable - final done
fill(#B7962A);
triangle((width/2)+CARGO_X1,(height/2)+CARGO_Y1,(width/2)+CARGO_X2,(height/2)+CARGO_Y2,(width/2)+CARGO_X3,(height/2)+CARGO_Y3); //cargo