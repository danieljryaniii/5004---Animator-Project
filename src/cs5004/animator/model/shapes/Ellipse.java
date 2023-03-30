package cs5004.animator.model.shapes;

/**
 * This class represents a oval shape. Oval extends the AbstractShapeAnimation
 * class which supports Shape Animations.  
 */
public class Ellipse extends AbstractShapeAnimation {
  
  /*
   * Construct an Ellipse using the properties of a builder.
   * Refer to the Builder sub-class below for additional details
   * on the type of object to be passed to this constructor. 
   * 
   * In order to construct an Ellipse:
   * Ellipse ellipse = new Ellipse.Builder(name).fieldSetter(args).build();
   */
  private Ellipse(Builder builder) {
    super(builder);
  }
    
  @Override
  public AbstractShapeAnimation cloneShape() {
    return new Ellipse.Builder(this.getName())
                                   .referencePoint(this.getPosition().clone())
                                   .color(this.getColor().cloneColor())
                                   .width(this.getWidth())
                                   .height(this.getHeight())
                                   .appearTime(this.getAppearTime())
                                   .removeTime(this.getRemoveTime())
                                   .visibility(this.isVisible())
                                   .build();
  }
  
  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
        + "Type: " + "ellipse" + "\n"
        + "Center: " + this.getCenterPoint() + "\n"
        + "X Radius: " + this.getWidth() / 2.0 + "\n"
        + "Y Radius: " + this.getHeight() / 2.0 + "\n"
        + "Color: " + this.getColor() + "\n"
        + "Appear At: " + this.getAppearTime() + "\n"
        + "Disappear At: " + this.getRemoveTime();
  }
  
  @Override
  public ShapeType getType() {
    return ShapeType.ELLIPSE;
  }
  
  /**
   * This Builder class is meant to simplify the initialization of this Ellipse's attributes.
   * The only mandatory attribute for an Ellipse is a name (String). All other attributes
   * will be initialize to default values if their builder method is not called on
   * initialization.
   * 
   * <p>This Design Pattern allows for a more simplified constructor as well as flexibility
   * during instantiation by providing functionality similar to Python's optional parameters.
   * 
   * <p>The structure used for this class (Ellipse which extends AbstractShapeAnimation) is 
   * described within "Effective Java - Third Edition - Joshua Bloch" and involves using
   * Builder pattern within class hierarchies.
   */
  public static class Builder extends AbstractShapeAnimation.Builder<Builder> {    

    /**
     * Builder Constructor to initialize the mandatory field "name".
     * @param name the name of this Ellipse
     */
    public Builder(String name) {
      super(name);
    }
        
    @Override 
    public Ellipse build() {
      return new Ellipse(this);
    }
    
    @Override
    protected Builder self() {
      return this;
    }
  }
}
