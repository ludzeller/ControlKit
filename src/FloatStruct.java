package ControlKit;

class FloatStruct{
  public float last; // last read value
  public float direct; // last read value
  public int change; // derivation between last and direct, falling: -1, rising: 1, no change: 0

  public float eased; // current animated value
  public float easeRate; // easing speed

  public float linearity; // output linearity, i.e. bending the curve
  
  public String name;
}