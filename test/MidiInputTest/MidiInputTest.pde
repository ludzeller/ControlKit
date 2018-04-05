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

  background(120);
  renderFloatBuffer(nanoBuff1.getAllValuesAsInt(0, 255), 16, 16);

}


void renderFloatBuffer(int[] values, int xRows, int yRows) {

  int counter = 0;
  float w = width / (xRows+1) * 0.9;
  noStroke();
  rectMode(CENTER);
  textAlign(CENTER, CENTER);
  
  for (int y = 1; y <= yRows; y++) {
    for (int x = 1; x <= xRows; x++) {
      if(counter >= values.length) return;
      float xPos = x * width / (xRows+1);
      float yPos = y * height / (yRows+1);
      fill( values[counter] );
      ellipse( xPos, yPos, w, w);
      fill(255, 0, 0);
      text(counter, xPos, yPos);
      counter++;
    }
  }
}