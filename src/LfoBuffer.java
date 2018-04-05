
package ControlKit;

import processing.core.*;
import java.util.ArrayList;

public class LfoBuffer implements PConstants{

  ArrayList<Lfo> lfos;

  public LfoBuffer(int amount) {
    lfos = new ArrayList<Lfo>(); // create 8 LFOs with frequency 1Hz
    for (int i = 0; i < amount; i++) {
      lfos.add(new Lfo(1));
    }
  }

  public void update() {
    for (int i = 0; i < lfos.size(); i++) {
      lfos.get(i).update();
    }
  }

  public void setFrequency(int num, float freq) {
    lfos.get(num).setFrequency(freq);
  }  

  public void setPhaseInvert(int num, boolean phase) {
    lfos.get(num).setPhaseInvert(phase);
  }

  public void setWaveForm(int num, int type) {
    lfos.get(num).setWaveForm(type);
  }

  public float value(int num) {
    return lfos.get(num).value(0, 1);
  }

  public float value(int num, float a, float b) {
    return lfos.get(num).value(a, b, 0);
  }

  public float value(int num, float a, float b, float phase ) {
    return lfos.get(num).value(a, b, phase);
  }
}