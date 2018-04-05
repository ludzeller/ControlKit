
package ControlKit;

import processing.core.*;

public class MidiBuffer extends FloatBuffer{

  int channel;
  boolean useKnobsForEasing = false; // Korg NanoControl only: use knobs to set easing for each fader

  static int defaultChannel = 1;
  static int defaultAmount = 128;
  static float defaultValue = 0;
  
  public MidiBuffer() {
    this(MidiBuffer.defaultChannel, MidiBuffer.defaultAmount, MidiBuffer.defaultValue);
  }

  public MidiBuffer(int ch) {
    this(ch, MidiBuffer.defaultAmount, MidiBuffer.defaultValue);
  }

  public MidiBuffer(int ch, int amount) {
    this(ch, amount, MidiBuffer.defaultValue);
  }

  public MidiBuffer(int ch, int amount, float initVal) {
    super(amount, initVal, 0f, 1f);
    channel = ch;
  }
  
  
  public void update() {
    super.update();
    
    if (useKnobsForEasing) { // Korg NanoControl 2 only
      for (int i = 0; i < 8; i++) {
        floats[i].easeRate = value( i+16, 0.001f, 1, 3 ); // 0-7
        floats[i+32].easeRate = value( i+16, 0.001f, 1, 3 ); // 32-39
        floats[i+48].easeRate = value( i+16, 0.001f, 1, 3 ); // 48-55
        floats[i+72].easeRate = value( i+16, 0.001f, 1, 3 ); // 64-71
      }
    }
    
  }

  public void controllerChange(int ch, int cc, int value) {
    
    if ((channel - 1) != ch) { // - 1 for array offset
      PApplet.println("Wrong Midi Channel, should be " + ch);
    } else {
      if(ControlKit.verbose)PApplet.println("Cc: " + cc + ", Value: " + value);
      //set(cc, PApplet.map(value, 0, 127, mapMin, mapMax));
      floats[cc].direct = PApplet.map(value, 0f, 127f, mapMin, mapMax); // store and normalize incoming value
      //if(ControlKit.verbose)PApplet.println(floats[cc].direct);
    }
  }

  //public void noteOn(int channel, int pitch, int velocity) {
  //  if (channel != 0) {
  //    println("Wrong Midi Channel, should be 1");
  //  } else {
  //    println("Note: " + pitch + ", Velocity: " + velocity);
  //    midiFlow.direct[128] = map(pitch, 0, 127, 0, 1); // store and normalize incoming value
  //  }
  //}
}