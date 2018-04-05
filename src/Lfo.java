
package ControlKit;

import processing.core.*;
import java.util.ArrayList;


// LFO ---------------
public class Lfo implements PConstants{


  long millisOffset = 0;
  boolean phaseInvert = false;
  float timer = 0;
  int lastTime = 0;
  float freq = 1; // Hertz
  int waveForm = 1; // 1 = Sin, 2 = Cos, 3 = Saw, 4 = Pulse

  public Lfo(float f) {
    this.setFrequency(f);
    millisOffset = System.currentTimeMillis();
  }

  int millis() {
    return (int) (System.currentTimeMillis() - millisOffset);
  }

  public void update() {
    float diffTime = millis() - lastTime; // time passed
    timer += diffTime * freq; // actual animation
    timer %= 1000; // wrap around
    lastTime = millis();
  }

  public void setFrequency(float f) {
    this.freq = f;
  } 

  public void setPhaseInvert(boolean invert) {
    phaseInvert = invert;
  }

  public void setWaveForm(String type) {

    if (type == "sin") waveForm = 1;
    if (type == "cos") waveForm = 2;
    if (type == "saw") waveForm = 3;
    if (type == "pul") waveForm = 4;
  }

  public void setWaveCos() {
    waveForm = 2;
  }

  public void setWaveSaw() {
    waveForm = 3;
  }

  public void setWavePul() {
    waveForm = 4;
  }

  public float value() {
    return value(0, 1);
  }

  public float value(float a, float b) {
    return value(a, b, 0);
  }

  public float value(float a, float b, float phase ) {

    switch(waveForm) {

    case 1:

      if (phaseInvert) {
        return PApplet.map(PApplet.sin( PApplet.map(timer+phase, 0, 1000f, 0, TWO_PI ) ), -1f, 1f, a, b);
      } else {
        return PApplet.map(PApplet.cos( PApplet.map(timer+phase, 0, 1000f, 0, TWO_PI ) ), -1f, 1f, b, a);
      }


    case 2:

      if (phaseInvert) {
        return PApplet.map(PApplet.cos( PApplet.map(timer+phase, 0, 1000f, 0, TWO_PI ) ), -1f, 1f, a, b);
      } else {
        return PApplet.map(PApplet.sin( PApplet.map(timer+phase, 0, 1000f, 0, TWO_PI ) ), -1f, 1f, b, a);
      }


    case 3:

      if (phaseInvert) {
        return PApplet.map( (timer+phase)%1000f, 0f, 999f, a, b);
      } else {
        return PApplet.map( (timer+phase)%1000f, 0f, 999f, b, a);
      }

    case 4:
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