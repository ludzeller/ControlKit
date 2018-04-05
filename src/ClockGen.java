package ControlKit;

public class ClockGen{
 
  float rate;
  int lastUpdate;
  
  public ClockGen(float _rate){
    setRate(_rate);
    lastUpdate = ControlKit.millis();
  }
  
  public boolean hasTriggered(){
    if (ControlKit.millis() <= lastUpdate + 1000 / rate){
      return false;
    } else {
      lastUpdate = ControlKit.millis();
      return true;
    }
  }
  
  public void setRate(float _rate){
    rate = _rate;
  }
  
  public float getRate(){
    return rate;
  }
  
  public void reset(){
    lastUpdate = ControlKit.millis();
  }
  
}