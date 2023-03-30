package cs5004.animator.model.shapes;

/**
 * This class represents a generic shape that can support various
 * animations. A Builder Pattern is used for instantiation of concrete
 * classes therefore concrete classes should also implement a Builder 
 * using the Builder pattern within class hierarchies pattern.
 */
public abstract class AbstractShapeAnimation implements ShapeAnimation {

  private final String name;
  private Point2D referencePoint;
  private ShapeColor color;
  private double width;
  private double height;
  private int appearTime;
  private int removeTime;
  private boolean visible;
  
  /**
   * Construct an AbstractShapeAnimation using the Builder design pattern.
   * @param builder a Builder object of generic type
   */
  public AbstractShapeAnimation(Builder<?> builder) {
    this.name = builder.name;
    this.referencePoint = builder.referencePoint;
    this.color = builder.color;
    this.width = builder.width;
    this.height = builder.height;
    this.appearTime = builder.appearTime;
    this.removeTime = builder.removeTime;
    this.visible = builder.visible;
  }


  @Override
  public void moveShape(Point2D newPosition) {
    this.referencePoint = newPosition;

  }
  
  @Override
  public void scale(double newWidth, double newHeight) throws IllegalArgumentException {
    this.setWidth(newWidth);
    this.setHeight(newHeight);
  }
  
  @Override
  public void showShape() {
    this.visible = true;

  }

  @Override
  public void hideShape() {
    this.visible = false;

  }
  
  @Override
  public void setWidth(double width) throws IllegalArgumentException {
    if (width <= 0) {
      throw new IllegalArgumentException("Cannot set a negative or zero "
                                       + "width for a ShapeAnimation.");
    }
    this.width = width;
  }
  
  @Override
  public void setHeight(double height) throws IllegalArgumentException {
    if (height <= 0) {
      throw new IllegalArgumentException("Cannot set a negative or zero "
                                       + "height for a ShapeAnimation.");
    }
    this.height = height;
  }
  
  @Override
  public void setAppearTime(int appearTime) throws IllegalArgumentException {
    if (appearTime < 0) {
      throw new IllegalArgumentException("A ShapeAnimation's appearance "
                                       + "time must be non-negative.");
    }
    this.appearTime = appearTime;
  }
  
  @Override
  public void setRemoveTime(int removeTime) throws IllegalArgumentException {
    if (removeTime < 0) {
      throw new IllegalArgumentException("A ShapeAnimation's removal time must be non-negative.");
    }
    this.removeTime = removeTime;
  }

  @Override
  public void setColor(ShapeColor color) {
    this.color = color;

  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Point2D getPosition() {
    return this.referencePoint;
  }
  
  @Override
  public Point2D getCenterPoint() {
    return new Point2D(this.referencePoint.getX() + (this.width / 2),
                       this.referencePoint.getY() + (this.height / 2));
  }
  
  @Override
  public ShapeColor getColor() {
    return this.color;
  }
  
  @Override
  public double getWidth() {
    return this.width;
  }
  
  @Override
  public double getHeight() {
    return this.height;
  }
  
  @Override
  public int getAppearTime() {
    return this.appearTime;
  }
  
  @Override
  public int getRemoveTime() {
    return this.removeTime;
  }
 
  @Override
  public boolean isVisible() {
    return this.visible;
  }
  
  /**
   * Abstract Builder class to simplify the initialization of this AbstractShapeAnimation's
   * attributes. The only mandatory attribute for an AbstractShapeAnimation is a name (String).
   * All other attributes will be initialize to default values if their builder method is not 
   * called on initialization. Refer to the default values below.
   * 
   * <p>This Design Pattern allows for a more simplified constructor as well as flexibility
   * during instantiation by providing functionality similar to Python's optional parameters.
   * 
   * <p>The structure used for this class (Ellipse which extends AbstractShapeAnimation) is 
   * described within "Effective Java - Third Edition - Joshua Bloch" and involves using
   * Builder pattern within class hierarchies.
   */
  public abstract static class Builder<T extends Builder<T>> {
    
    // Mandatory field
    private final String name;

    // Optional Fields with default values ( All Default to Infinity )
    private Point2D referencePoint = new Point2D(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private ShapeColor color = new ShapeColor(0, 0, 0);     
    private double width = Integer.MAX_VALUE;              
    private double height = Integer.MAX_VALUE;          
    private int appearTime = Integer.MAX_VALUE;        
    private int removeTime = Integer.MAX_VALUE;        
    private boolean visible = false;                
    
    /**
     * Build the AbstractShapeAnimation object. This method should be
     * called after all initialization methods have been invoked 
     * i.e. Class.Builder(arg).argSetter(arg).argSetter(arg).build();
     * 
     * <p>Sub-classes must override this method in order to construct and
     * return the correct concrete/sub-class object.
     * @return a new AbstractShapeAnimation object
     */
    public abstract AbstractShapeAnimation build();

    /**
     * Method to allow for chaining to work properly in implementing
     * sub-classes - "simulated self-type". Sub-classes must override
     * to return "this".
     * @return return an implementing sub-class's object  
     */
    protected abstract T self();
    
    /**
     * Constructor for the Builder. The only mandatory field to construct 
     * is the name. Every AbstractShapeAnimation must have a name.
     * @param name the name of this AbstractShapeAnimation / Builder.
     */
    public Builder(String name) {
      this.name = name;
    }
    
    /**
     * Builder method to set the reference point of this AbstractShapeAnimation
     * / Builder. If this method is not invoked the referencePoint field will be
     * initialized to it's default value.
     * @param referencePoint the {@link Point2D} reference point for this AbstractShapeAnimation
     *     / Builder
     * @return a generic-type object. Object returned should be the invoking sub-class object.
     */
    public T referencePoint(Point2D referencePoint) {
      this.referencePoint = referencePoint;
      return self();
    }

    /**
     * Builder method to set the color of this AbstractShapeAnimation
     * / Builder. If this method is not invoked the color field will be
     * initialized to it's default value.
     * @param color the {@link ShapeColor} color for this AbstractShapeAnimation
     *     / Builder
     * @return a generic-type object. Object returned should be the invoking sub-class object.
     */
    public T color(ShapeColor color) {
      this.color = color;
      return self();
    }
    
    /**
     * Builder method to set the width of this AbstractShapeAnimation
     * / Builder. If this method is not invoked the width field will be
     * initialized to it's default value.
     * @param width the width for this AbstractShapeAnimation
     *     / Builder
     * @return a generic-type object. Object returned should be the invoking sub-class object.
     */
    public T width(double width) {
      this.width = width;
      return self();
    }

    /**
     * Builder method to set the height of this AbstractShapeAnimation
     * / Builder. If this method is not invoked the width field will be
     * initialized to it's default value.
     * @param height the height for this AbstractShapeAnimation
     *     / Builder
     * @return a generic-type object. Object returned should be the invoking sub-class object.
     */
    public T height(double height) {
      this.height = height;
      return self();
    }
    
    /**
     * Builder method to set the appearance time of this AbstractShapeAnimation
     * / Builder. If this method is not invoked the appearTime field will be
     * initialized to it's default value.
     * @param appearTime the appearance time for this AbstractShapeAnimation
     *     / Builder
     * @return a generic-type object. Object returned should be the invoking sub-class object.
     */
    public T appearTime(int appearTime) {
      this.appearTime = appearTime;
      return self();
    }
    
    /**
     * Builder method to set the removal time of this AbstractShapeAnimation
     * / Builder. If this method is not invoked the removeTime field will be
     * initialized to it's default value.
     * @param removeTime the removal time for this AbstractShapeAnimation
     *     / Builder
     * @return a generic-type object. Object returned should be the invoking sub-class object.
     */
    public T removeTime(int removeTime) {
      this.removeTime = removeTime;
      return self();
    }

    /**
     * Builder method to set the visibility of this AbstractShapeAnimation
     * / Builder. If this method is not invoked the visibility field will be
     * initialized to it's default value.
     * @param visible the appearance time for this AbstractShapeAnimation
     *     / Builder
     * @return a generic-type object. Object returned should be the invoking sub-class object.
     */
    public T visibility(boolean visible) {
      this.visible = visible;
      return self();
    }
  }
}
