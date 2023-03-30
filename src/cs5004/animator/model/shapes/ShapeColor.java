package cs5004.animator.model.shapes;

/**
 * This class represents a color on the RGB scale.
 */
public class ShapeColor {

  private final double r;
  private final double g;
  private final double b;
  
  /**
   * Construct a Color based on given RGB values. If any value R, G or B is invalid
   * (i.e. not in the range 0-255) an IllegalArgumentException will be thrown.
   * @param r a number from 0 to 255 representing the red intensity
   * @param g a number from 0 to 255 representing the green intensity
   * @param b a number from 0 to 255 representing the blue intensity
   * @throws IllegalArgumentException thrown if any of the RGB intensities are invalid
   */
  public ShapeColor(double r, double g, double b) throws IllegalArgumentException {
    if (!(ShapeColor.inColorRange(r) && ShapeColor.inColorRange(g) && ShapeColor.inColorRange(b))) {
      throw new IllegalArgumentException("RGB values must be in the valid range of 0 - 255.");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }  
  
  /**
   * Get the R-value of this ShapeColor. Value returned will be withing the range
   * 0 -255.
   * @return the R-value of this ShapeColor.
   */
  public double getR() {
    return this.r;
  }

  /**
   * Get the G-value of this ShapeColor. Value returned will be withing the range
   * 0 -255.
   * @return the G-value of this ShapeColor.
   */
  public double getG() {
    return this.g;
  }
  
  /**
   * Get the B-value of this ShapeColor. Value returned will be withing the range
   * 0 -255.
   * @return the B-value of this ShapeColor.
   */
  public double getB() {
    return this.b;
  }

  /**
   * Return an independent Color object that is identical to this Color.
   * @return a new Color object that is identical to - but independent of - this
   *     Color;
   */
  public ShapeColor cloneColor() {
    return new ShapeColor(this.r, this.g, this.b);
  }
  
  @Override
  public String toString() {
    return String.format("(%.2f, %.2f, %.2f)", this.r, this.g, this.b);
  }
  
  /*
   * Return true if the given value is in the valid
   * color range of 0 - 255. Otherwise, return false.
   */
  private static boolean inColorRange(double rgb) {
    return !(rgb < 0 || rgb > 255);
  } 
}
