package ControlKit;

import processing.core.*;

public class FloatBuffer {

  String version = "0.18";
  public boolean verbose = false;

  //float defaultValue = 0; // default: 0 // maybe this should be settable from the outside
  public float defaultEaseValue = 1; // default: no easing

  public FloatStruct[] floats;

  FloatBuffer(int bufferLength, float initVal) {

    PApplet.println("Using ControlKit Version " + version);
    PApplet.println(""); 
    
    floats = new FloatStruct[bufferLength];

    // initialize buffer
    for ( int i = 0; i < bufferLength; i++ ) {
      floats[i] = new FloatStruct();
      floats[i].direct = initVal;
      floats[i].direct = initVal;
      floats[i].eased = initVal;
      floats[i].easeRate = defaultEaseValue;
      floats[i].mapMin = 0;
      floats[i].mapMax = 1;
      floats[i].linearity = initVal;
    }
  }

  public void setEaseRate(int index, float val) {
    floats[index].easeRate = val;
  }

  public void setMapMinimum(int index, float val) {
    floats[index].mapMin = val;
  }

  public void setMapMaximum(int index, float val) {
    floats[index].mapMax = val;
  }

  // below 1 bends curve up, above 1 bends curve down
  public void setLinearity(int index, float val) {
    floats[index].linearity = val;
  }


  public float value(int index) {
    return value(index, floats[index].mapMin, floats[index].mapMax, floats[index].linearity);
  } 

  public float value(int index, float a, float b) {
    return value(index, a, b, floats[index].linearity);
  }

  public float value(int index, float a, float b, float linearity) {
    return PApplet.map(PApplet.pow(floats[index].direct, floats[index].linearity), 0, 1, a, b);
  }

  public float eased(int index) {
    return eased(index, floats[index].mapMin, floats[index].mapMax, floats[index].linearity);
  } 

  public float eased(int index, float a, float b) {
    return eased(index, a, b, floats[index].linearity);
  }

  public float eased(int index, float a, float b, float linearity) {
    return PApplet.map(PApplet.pow(floats[index].eased, linearity), 0, 1, a, b);
  }  

  public void update() {

    for ( int i = 0; i < floats.length; i++ ) {
      float delta = floats[i].direct - floats[i].eased;
      floats[i].eased += delta * floats[i].easeRate;
    }
  }
}