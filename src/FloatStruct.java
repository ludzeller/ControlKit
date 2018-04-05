package ControlKit;

class FloatStruct{
  public float direct; // last read value
  public float eased; // current animated value
  public float easeRate; // easing speed
  public float mapMin; // minimum output range
  public float mapMax; // maximum output range
  public float linearity; // output linearity, i.e. bending the curve
  public String name;
}