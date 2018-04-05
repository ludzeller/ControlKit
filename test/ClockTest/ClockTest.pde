
import ControlKit.*;

ClockGen testClock; 

void setup() {

  size(600, 600);  
  frameRate(60);

  testClock = new ClockGen(2);
}

void draw() {    

  testClock.setRate(map(mouseX, 0, width, 0.5, 30));
  
  if(testClock.hasTriggered()){
    background(255);
  } else {
    background(0);
  }
  
}