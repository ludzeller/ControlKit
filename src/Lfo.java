      
package ControlKit;

import processing.core.*;
import java.util.ArrayList;


// LFO ---------------
public class Lfo implements PConstants{

  final static String SIN = "sin";
  final static String COS = "cos";
  final static String SAW = "saw";
  final static String PUL = "pul";
  

  long millisOffset = 0;
  boolean phaseInvert = false;
  float timer = 0;
  int lastTime = 0;
  float freq = 1; // Hertz
  
  String waveForm = Lfo.SIN; // 1 = Sin, 2 = Cos, 3 = Saw, 4 = Pulse

  public Lfo(float f) {
    this.setFrequency(f);
    millisOffset = System.currentTimeMillis();
  }


  public void update() {
    float diffTime = ControlKit.millis() - lastTime; // time passed
    timer += diffTime * freq; // actual animation
    timer %= 1000; // wrap around
    lastTime = ControlKit.millis();
  }

  public void setFrequency(float f) {
    this.freq = f;
  } 

  public void setPhaseInvert(boolean invert) {
    phaseInvert = invert;
  }

  public void setWaveForm(String type) {
    
    if(type != Lfo.SIN && type != Lfo.COS && type != Lfo.SAW && type != Lfo.PUL ){
      System.out.println("Wrong waveform given");
      return;
    }
    
    waveForm = type;
    
  }

  public void setWaveSin() {
    waveForm = Lfo.SIN;
  }
  
  public void setWaveCos() {
    waveForm = Lfo.COS;
  }

  public void setWaveSaw() {
    waveForm = Lfo.SAW;
  }

  public void setWavePul() {
    waveForm = Lfo.PUL;
  }

  public float value() {
    return value(0, 1);
  }

  public float value(float a, float b) {
    return value(a, b, 0);
  }

  public float value(float a, float b, float phase ) {

    switch(waveForm) {

    case Lfo.SIN:

      if (phaseInvert) {
        return PApplet.map(PApplet.sin( PApplet.map(timer+phase, 0, 1000f, 0, TWO_PI ) ), -1f, 1f, a, b);
      } else {
        return PApplet.map(PApplet.cos( PApplet.map(timer+phase, 0, 1000f, 0, TWO_PI ) ), -1f, 1f, b, a);
      }


    case Lfo.COS:

      if (phaseInvert) {
        return PApplet.map(PApplet.cos( PApplet.map(timer+phase, 0, 1000f, 0, TWO_PI ) ), -1f, 1f, a, b);
      } else {
        return PApplet.map(PApplet.sin( PApplet.map(timer+phase, 0, 1000f, 0, TWO_PI ) ), -1f, 1f, b, a);
      }


    case Lfo.SAW:

      if (phaseInvert) {
        return PApplet.map( (timer+phase)%1000f, 0f, 999f, a, b);
      } else {
        return PApplet.map( (timer+phase)%1000f, 0f, 999f, b, a);
      }

    case Lfo.PUL:
      if (phaseInvert) {
        return PApplet.map( PApplet.round(((timer+phase)%1000f)/1000f), 0f, 1f, b, a); // pulse
      } else {
        return PApplet.map( PApplet.round(((timer+phase)%1000f)/1000f), 0f, 1f, a, b);
      }
    }

    return -1;
  }

  public void reset() {
    timer = 0;
  }
}