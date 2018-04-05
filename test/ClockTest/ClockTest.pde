import processing.serial.*;
import dmxP512.*;
import themidibus.*; 
import ControlKit.*;

int universeSize = 512;
String DMXPRO_PORT = "/dev/cu.usbserial-EN229425"; // adapt this address to your serial number
int DMXPRO_BAUDRATE = 115000;

DmxP512 dmxBus;
DmxBuffer dmxBuff;

MidiBus midiBus;
MidiBuffer nanoBuff1;

LfoBuffer lfos;

ClockGen testClock; 

void setup() {

  size(600, 600);  
  frameRate(60);

  dmxBus = new DmxP512(this, universeSize, false);
  dmxBus.setupDmxPro(DMXPRO_PORT, DMXPRO_BAUDRATE);
  MidiBus.list();
  
  nanoBuff1 = new MidiBuffer(1, 10);
  midiBus = new MidiBus(nanoBuff1, "SLIDER/KNOB", "CTRL");

  //dmxBuff = new DmxBuffer(100); // 100 channels

  lfos = new LfoBuffer(4);

  testClock = new ClockGen(2);
}


void draw() {    

  lfos.update();
  nanoBuff1.update();
  //dmxBuff.update();
  
  //testClock.setRate(nanoBuff1.value(16));
  
  ////println(testClock.getRate());
  //println(nanoBuff1.value(16));
  
  
  
  //if(testClock.hasTriggered()){
  //  background(255);
  //} else {
  //  background(0);
  //}

  //nanoBuff1.set(50, 255);
  println(nanoBuff1.getAllValuesAsInt().length);
  //renderFloatBuffer(nanoBuff1.getAllValuesAsInt(), 10, 10);

}


void renderFloatBuffer(int[] values, int xRows, int yRows) {

  int counter = 0;
  float w = width / (xRows+1) * 0.9;
  noStroke();
  rectMode(CENTER);
  textAlign(CENTER, CENTER);
  
  for (int y = 1; y <= yRows; y++) {
    for (int x = 1; x <= xRows; x++) {
        
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