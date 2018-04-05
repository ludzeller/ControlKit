
// MidiFlow
// ludwig.zeller@fhnw.ch
// viscom / IXDM / HGK / FHNW

// todo:
// allow for non-linear mappings also for negative ranges, etc

// usage:
// 

package ControlKit;

import processing.core.*;

public class MidiBuffer {

  String version = "0.17";

  boolean verbose = false;
  int ccAmount = 128;
  int channel;
  //float defaultValue = 0; // default: 0 // maybe this should be settable from the outside
  float defaultEaseValue = 1; // default: no easing

  boolean useKnobsForEasing = false; // Korg NanoControl only: use knobs to set easing for each fader  

  float[] direct; // last read value
  float[] eased; // current animated value
  float[] easeRate; // easing speed
  float[] mapMin; // minimum output range
  float[] mapMax; // maximum output range
  float[] linearity; // output linearity, i.e. bending the curve

  public MidiBuffer() {
    this(1, 1);
  }

  public MidiBuffer(int ch) {
    this(ch, 1);
  }

  public MidiBuffer(int ch, float initVal) {

    PApplet.println("Using MidiFlow Version " + version);
    PApplet.println("");
    
    
    channel = ch;

    // initialize buffers
    direct = new float[ccAmount];
    for ( int i = 0; i < ccAmount; i++ ) {
      direct[i] = initVal;
    }

    eased = new float[ccAmount];
    for ( int i = 0; i < ccAmount; i++ ) {
      eased[i] = initVal;
    }

    easeRate = new float[ccAmount];
    for ( int i = 0; i < ccAmount; i++ ) {
      easeRate[i] = defaultEaseValue;
    }

    mapMin = new float[ccAmount];
    for ( int i = 0; i < ccAmount; i++ ) {
      mapMin[i] = 0;
    }
    
    mapMax = new float[ccAmount];
    for ( int i = 0; i < ccAmount; i++ ) {
      mapMax[i] = 1;
    }
    
    linearity = new float[ccAmount];
    for ( int i = 0; i < ccAmount; i++ ) {
      linearity[i] = initVal;
    }

  }
  
  public void setEaseRate(int cc, float val){
    easeRate[cc] = val;
  }
  
  public void setMapMinimum(int cc, float val){
    mapMin[cc] = val;
  }
  
  public void setMapMaximum(int cc, float val){
    mapMax[cc] = val;
  }
  
  // below 1 bends curve up, above 1 bends curve down
  public void setLinearity(int cc, float val){
    linearity[cc] = val;
  }

  public void update() {

    for ( int i = 0; i < ccAmount; i++ ) {
      float delta = direct[i] - eased[i];
      eased[i] += delta * easeRate[i];
    }

    if (useKnobsForEasing) { // Korg NanoControl 2 only
      for (int i = 0; i < 8; i++) {
        easeRate[i] = value( i+16, 0.001f, 1, 3 ); // 0-7
        easeRate[i+32] = value( i+16, 0.001f, 1, 3 ); // 32-39
        easeRate[i+48] = value( i+16, 0.001f, 1, 3 ); // 48-55
        easeRate[i+71] = value( i+16, 0.001f, 1, 3 ); // 64-71
      }
    }
    
  }

  public float value(int index) {
    return value(index, mapMin[index], mapMax[index], linearity[index]);
  } 

  public float value(int index, float a, float b) {
    return value(index, a, b, linearity[index]);
  }

  public float value(int index, float a, float b, float linearity) {
    return PApplet.map(PApplet.pow(direct[index], linearity), 0, 1, a, b);
  }

  public float eased(int index) {
    return eased(index, mapMin[index], mapMax[index], linearity[index]);
  } 

  public float eased(int index, float a, float b) {
    return eased(index, a, b, linearity[index]);
  }

  public float eased(int index, float a, float b, float linearity) {
    return PApplet.map(PApplet.pow(eased[index], linearity), 0, 1, a, b);
  }


  public void controllerChange(int ch, int number, int value) {
    
    if ((channel - 1) != ch) { // - 1 for array offset
      PApplet.println("Wrong Midi Channel, should be " + ch);
    } else {
      if(verbose)PApplet.println("Number: " + number + ", Value: " + value);
      direct[number] = PApplet.map(value, 0, 127, 0, 1); // store and normalize incoming value
    }
  }

  //public void noteOn(int channel, int pitch, int velocity) {
  //  if (channel != 0) {
  //    println("Wrong Midi Channel, should be 1");
  //  } else {
  //    println("Note: " + pitch + ", Velocity: " + velocity);
  //    midiFlow.direct[128] = map(pitch, 0, 127, 0, 1); // store and normalize incoming value
  //  }
  //}
}