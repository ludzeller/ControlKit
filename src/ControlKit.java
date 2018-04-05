package ControlKit;

public class ControlKit{
  
  public static final String version = "0.18";
  public static boolean verbose = false;

  static long millisOffset = System.currentTimeMillis();
  
  static int millis() {
    return (int) (System.currentTimeMillis() - ControlKit.millisOffset);
  }
  
}