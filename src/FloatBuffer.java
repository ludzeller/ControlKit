package ControlKit;

import processing.core.*;

public class FloatBuffer {

  String version = "0.18";
  public boolean verbose = false;

  //float defaultValue = 0; // default: 0 // maybe this should be settable from the outside
  public float defaultEaseValue = 1; // default: no easing

  public FloatStruct[] floats;

  float mapMin;
  float mapMax;

  FloatBuffer(int bufferLength){
    this(bufferLength, 0f, 0f, 1f);
  }

  FloatBuffer(int bufferLength, float initVal){
    this(bufferLength, initVal, 0f, 1f);
  }
  
  FloatBuffer(int bufferLength, float initVal, float _mapMin, float _mapMax) {

    PApplet.println(""); 
    PApplet.println("Using ControlKit Version " + version);
    PApplet.println(""); 

    mapMin = _mapMin;
    mapMax = _mapMax;
    
    floats = new FloatStruct[bufferLength];

    // initialize buffer
    for ( int i = 0; i < bufferLength; i++ ) {
      floats[i] = new FloatStruct();
      floats[i].direct = initVal;
      floats[i].eased = initVal;
      floats[i].easeRate = defaultEaseValue;
      floats[i].linearity = initVal;
    }
  }

  public void setEaseRate(int index, float val) {
    floats[index].easeRate = val;
  }

  public void setMapMinimum(float val) {
    mapMin = val;
  }

  public void setMapMaximum(float val) {
    mapMax = val;
  }

  public float value(int index) {
    return floats[index].direct;
  } 

  public float value(int index, float outMin, float outMax) {
    return value(index, outMin, outMax, 1f);
  }

  public float value(int index, float outMin, float outMax, float linearity) {
    return PApplet.map(PApplet.pow(floats[index].direct, linearity), mapMin, mapMax, outMin, outMax);
  }

  public float eased(int index) {
    return eased(index, mapMin, mapMax, 1f);
  } 

  public float eased(int index, float outMin, float outMax) {
    return eased(index, outMin, outMax, 1f);
  }

  public float eased(int index, float outMin, float outMax, float linearity) {
    return PApplet.map(PApplet.pow(floats[index].eased, linearity), mapMin, mapMax, outMin, outMax);
  }  

  public float[] getAllValues(){
    return getAllValues(mapMin, mapMax);
  }

  public float[] getAllValues(float a, float b){
    float[] result = new float[floats.length];
    for(int i = 0; i < floats.length; i++){
      result[i] = value(i, a, b);
    }
    return result;
  }

  public int[] getAllValuesAsInt() {
    return getAllValuesAsInt(mapMin, mapMax);
  }

  public int[] getAllValuesAsInt(float a, float b) {
    int[] result = new int[floats.length];
    for(int i = 0; i < floats.length; i++){
      result[i] = PApplet.floor(value(i, a, b));
    }
    return result;
  }

  public float[] getAllEased(){
    return getAllValues(mapMin, mapMax);
  }

  public float[] getAllEased(float a, float b){
    float[] result = new float[floats.length];
    for(int i = 0; i < floats.length; i++){
      result[i] = eased(i, a, b);
    }
    return result;
  }

  public int[] getAllEasedAsInt() {
    return getAllEasedAsInt(mapMin, mapMax);
  }

  public int[] getAllEasedAsInt(float a, float b) {
    int[] result = new int[floats.length];
    for(int i = 0; i < floats.length; i++){
      result[i] = PApplet.floor(eased(i, a, b));
    }
    return result;
  }  

  public void update() {

    for ( int i = 0; i < floats.length; i++ ) {
      float delta = floats[i].direct - floats[i].eased;
      floats[i].eased += delta * floats[i].easeRate;
    }
  }

  //public void set(int index, int value){
  //  set(index, value);
  //}

  public void set(int index, float value){
    floats[index].direct = value; // this does not map them to mapMin and mapMax!
  }

}