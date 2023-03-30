package cs5004.animator.model.shapes;

/**
 * This class represents a point (x, y) on a 2-Dimensional Plane.
 */
public class Point2D {
  
  private final double x;
  private final double y;
  
  /**
   * Construct a Point2D from a given x and y coordinate.
   * @param x a number representing the x-coordinate of this Point2D
   * @param y a number representing the y-coordinate of this Point2D
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Get the x-coordinate of this Point2D.
   * @return the x-coordinate of this Point2D
   */
  public double getX() {
    return this.x;
  }
  
  /**
   * Get the y-coordinate of this Point2D.
   * @return the y-coordinate of this Point2D
   */
  public double getY() {
    return this.y;
  }
  
  /**
   * Return an independent Point2D object that is identical to this Point2D.
   * @return a new Point2D object that is identical to - but independent of - this
   *     Point2D;
   */
  public Point2D clone() {
    return new Point2D(this.x, this.y);
  }
  
  @Override
  public String toString() {
    return String.format("(%.2f, %.2f)", this.x, this.y);
  }

}
