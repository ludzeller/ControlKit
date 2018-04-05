import themidibus.*; 
import ControlKit.*;

MidiBus midiBus;
MidiBuffer nanoBuff1;

LfoBuffer lfos;

ClockGen testClock; 

void setup() {

  size(600, 600);  
  frameRate(60);
  
  MidiBus.list();
  
  nanoBuff1 = new MidiBuffer(); // kanal 1, 10
  midiBus = new MidiBus(nanoBuff1, "SLIDER/KNOB", "CTRL");

}


void draw() {    
  
  nanoBuff1.update();
  nanoBuff1.setEaseRate(1, 0.1);
  
  background(
    nanoBuff1.value(0, 0, 255),
    nanoBuff1.eased(1, 0, 255),
    nanoBuff1.value(2, 0, 255, 5)
    );
  
}