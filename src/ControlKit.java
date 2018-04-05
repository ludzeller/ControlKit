package ControlKit;

public class ControlKit{
  
  static long millisOffset = System.currentTimeMillis();
  
  static int millis() {
    return (int) (System.currentTimeMillis() - ControlKit.millisOffset);
  }
  
}