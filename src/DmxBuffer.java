
// can be visualised via FloatBuffer
// might do the interpolations
// sends whole universe to dmx512 at once at the end of each frame


package ControlKit;

import processing.core.*;

public class DmxBuffer extends FloatBuffer{

  public DmxBuffer() {
    this(512, 0); // 512 channels
  }

  public DmxBuffer(int universeSize) {
    this(universeSize, 0);
  }

  public DmxBuffer(int universeSize, float initVal) {
    super(universeSize, initVal); 
  }

}