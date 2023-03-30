package cs5004.animator.model;

import cs5004.animator.model.shapes.Point2D;

/**
 * This class represents a 2D Canvas which has a width, height and
 * upper-left corner coordinate (x,y). 
 */
public class AnimationCanvas {

  private final int height;
  private final int width;
  private final Point2D topLeft;
  
  /**
   * Construct a new AnimationCanvas from a given width, height and
   * top-left corner coordinate (x, y). The canvas x-coordinates increase
   * in a downward direction and the canvas y-coordinates increase in a
   * rightward direction. If the given height and/or width is negative, it will
   * be initialized to zero.
   * @param width the width of this AnimationCanvas
   * @param height the height of this AnimationCanvas
   * @param topLeft the top-left corner reference point of this AnimationCanvas
   */
  public AnimationCanvas(int width, int height, Point2D topLeft) {
    if (height < 0) {
      this.height = 0;
    } else {
      this.height = height;
    }
    
    if (width < 0) {
      this.width = 0;
    } else { 
      this.width = width;
    }
    
    this.topLeft = topLeft;
  }
  
  /**
   * Get the height of this Animation Canvas.
   * @return the height of this animation canvas
   */
  public int getHeight() {
    return this.height;
  }
  
  /**
   * Get the width of this AnimationCanvas.
   * @return the width of this animation canvas
   */
  public int getWidth() {
    return this.width;
  }
  
  /**
   * Get the top-left corner {@link Point2D} location of this
   * AnimationCanvas.
   * @return a {@link Point2D} representing the top-left corner of this
   *     AnimationCanvas
   */
  public Point2D getTopLeft() {
    return this.topLeft;
  }
  
  /**
   * Make/Return an independent copy of this AnimationCanvas. 
   * @return an independent copy of this AnimationCanvas
   */
  public AnimationCanvas cloneCanvas() {
    return new AnimationCanvas(this.width, this.height, this.topLeft.clone());
  }
}
