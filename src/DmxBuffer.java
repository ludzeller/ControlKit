
// can be visualised via FloatBuffer
// might do the interpolations
// sends whole universe to dmx512 at once at the end of each frame


package ControlKit;

import processing.core.*;

public class DmxBuffer extends FloatBuffer{

  public DmxBuffer() {
    this(512, 0f, 0f, 255f); // 512 channels
  }

  public DmxBuffer(int universeSize) {
    this(universeSize, 0f, 0f, 255f);
  }

  public DmxBuffer(int universeSize, float initVal) {
    this(universeSize, initVal, 0f, 255f); 
  }  

  public DmxBuffer(int universeSize, float initVal, float _mapMin, float _mapMax) {
    super(universeSize, initVal, _mapMin, _mapMax); 
  }

}