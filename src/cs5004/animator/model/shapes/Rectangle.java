package cs5004.animator.model.shapes;

/**
 * This class represents a rectangle shape. Rectangle extends the AbstractShapeAnimation
 * class which supports Shape Animations.  
 */
public class Rectangle extends AbstractShapeAnimation {
    
  /*
   * Construct a Rectangle using the properties of a builder.
   * Refer to the Builder sub-class below for additional details
   * on the type of object to be passed to this constructor. 
   * 
   * In order to construct a Rectangle:
   * Rectangle rectangle = new Rectangle.Builder(name).fieldSetter(args).build();
   */
  private Rectangle(Builder builder) {
    super(builder);
  }
  
  @Override
  public AbstractShapeAnimation cloneShape() {
    return new Rectangle.Builder(this.getName())
                                     .referencePoint(this.getPosition().clone())
                                     .color(this.getColor().cloneColor())
                                     .height(this.getHeight())
                                     .width(this.getWidth())
                                     .appearTime(this.getAppearTime())
                                     .removeTime(this.getRemoveTime())
                                     .visibility(this.isVisible())
                                     .build();
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
        + "Type: " + "rectangle" + "\n"
        + "Top Left Corner: " + this.getPosition().toString() + "\n"
        + "Height: " + this.getHeight() + "\n"
        + "Width: " + this.getWidth() + "\n"
        + "Color: " + this.getColor() + "\n"
        + "Appear At: " + this.getAppearTime() + "\n"
        + "Disappear At: " + this.getRemoveTime();
  }
  
  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
  }
  
  /**
   * This Builder class is meant to simplify the initialization of this Rectangle's attributes.
   * The only mandatory attribute for an Rectangle is a name (String). All other attributes
   * will be initialize to default values if their builder method is not called on
   * initialization.
   * 
   * <p>This Design Pattern allows for a more simplified constructor as well as flexibility
   * during instantiation by providing functionality similar to Python's optional parameters.
   * 
   * <p>The structure used for this class (Rectangle which extends AbstractShapeAnimation) is 
   * described within "Effective Java - Third Edition - Joshua Bloch" and involves using
   * Builder pattern within class hierarchies.
   */
  public static class Builder extends AbstractShapeAnimation.Builder<Builder> {    
    /**
     * Builder Constructor to initialize the mandatory field "name".
     * @param name the name of this Rectangle
     */
    public Builder(String name) {
      super(name);
    }
        
    @Override 
    public Rectangle build() {
      return new Rectangle(this);
    }
    
    @Override
    protected Builder self() {
      return this;
    }
  }
}
