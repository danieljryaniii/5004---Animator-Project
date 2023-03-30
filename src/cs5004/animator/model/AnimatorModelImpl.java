package cs5004.animator.model;

import cs5004.animator.model.animations.AnimationType;
import cs5004.animator.model.animations.ChangeColorAnimation;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.animations.MoveAnimation;
import cs5004.animator.model.animations.ScaleAnimation;
import cs5004.animator.model.shapes.Ellipse;
import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.Rectangle;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;
import cs5004.animator.model.util.AnimationBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class is the model for a shape animation program. This model
 * will receive shapes, animations and a canvas which will be passed
 * to the controller and eventually view for the client to view. The model
 * focuses on data acceptance, organization and storage.
 */
public class AnimatorModelImpl implements AnimatorModel {

  private Map<String, ShapeAnimation> shapes;
  private Map<Integer, ArrayList<IAnimation>> animationsMap;
  private SortedSet<Integer> times;
  private AnimationCanvas canvas;
  
  /**
   * Construct a new Animation Model.
   * - Create HashMaps for the shapes and animations
   * - A TreeSet is used to store a list of start times encountered to be used
   *     to iterate through animation HashMap in order without iterating through
   *     empty slots
   * - Create a new canvas
   */
  public AnimatorModelImpl() {
    /* Instantiate Necessary Data Structures */
    this.shapes = new LinkedHashMap<>();  // Linked Hash Map for Shapes (Maintains order)
    this.animationsMap = new HashMap<>(); // Hash Map for Animations
    this.times = new TreeSet<>();         // Tree Set for Unique Start Times

    // Instantiate a placeholder canvas in event one is not provided
    this.canvas = new AnimationCanvas(700, 700, new Point2D(0, 0));  
  }
  
  @Override
  public void addShape(ShapeAnimation shape) throws IllegalArgumentException {
    // Check if a shape with the given name already exists
    if (shapes.containsKey(shape.getName())) {
      throw new IllegalArgumentException("A shape with the given name already exists.");
    }
    // Add shape to then Hash Map
    shapes.put(shape.getName(), shape);
  }
  
  @Override
  public void addAnimation(IAnimation animation) {
    int key = animation.getStartTime(); // Start Time used as Key into Hash Map

    // If the animation contains a shape that was not already declared. Add Shape as well
    if (this.shapes.get(animation.getShape().getName()) == null) {
      this.addShape(animation.getShape());
    }
    
    // Key already exists in Hash Map
    if (animationsMap.containsKey(key)) {
      animationsMap.get(key).add(animation); // Add Animation to existing ArrayList
    // Key does not exist yet
    } else {
      animationsMap.put(key, new ArrayList<>()); // Create new ArrayList
      animationsMap.get(key).add(animation);     // Add Animation to ArrayList
    }
    times.add(animation.getStartTime());
  }

  @Override
  public void addCanvas(AnimationCanvas canvas) {
    this.canvas = canvas;
  }
  
  @Override
  public String toString() {
    // Build the string for the Shapes Hash Map
    StringBuilder shapeStr = new StringBuilder();
    shapes.forEach((k, v) -> shapeStr.append(v.toString() + "\n\n"));
    
    // Build the String for the animations
    StringBuilder animationStr = new StringBuilder();
    ArrayList<IAnimation> currList;
    // Iterate over Hash Map
    for (Integer i: times) {
      currList = animationsMap.get(i);
      // Iterate over values (ArrayLists)
      for (IAnimation each: currList) {
        animationStr = animationStr.append(each.toString() + "\n");
      }
    }
    // Return Shapes followed by ordered Animations
    return "" + shapeStr + animationStr;
  }
  
  /**
   * Get the {@link AnimationCanvas} canvas associated with this AnimationModelImpl.
   * @return the {@link AnimationCanvs} associated with this AnimationModelImpl
   */
  public AnimationCanvas getCanvas() {
    return this.canvas;
  }
  
  /**
   * Return a Map of {@link String} and {@link ShapeAnimation} containing all of the
   * shapes for this AnimationModelImpl.
   * @return a Map of shapes stored in this AnimationModelImpl. 
   *     A Map{String, ShapeAnimation} will be returned
   */
  public Map<String, ShapeAnimation> getShapes() {
    return this.shapes;
  }
  
  /**
   * Return a Map of {@link Integer} and an ArrayList of {@link IAnimation} 
   * containing all of the animations for this AnimationModelImpl.
   * @return a Map of animations stored in this AnimationModelImpl. 
   *     A Map{String, ArrayList{IAnimation}} will be returned
   */  
  public Map<Integer, ArrayList<IAnimation>> getAnimations() {
    return this.animationsMap;
  }

  /**
   * Return a set of all the shape/animation starting times for this AnimationModelImpl.
   * This set contains every unique animation start time in ascending order.
   * @return a SortedSet of animation start times stored in this AnimationModelImpl
   */
  public SortedSet<Integer> getTimes() {
    return this.times;
  }
  
  
  /**
   * This Builder Class Implements the AnimationBulder and uses a Builder Design
   * Pattern to assist with the creation of Animations from a parsed text file.
   */
  public static class Builder implements AnimationBuilder<AnimatorModel> {

    private AnimatorModelImpl model;
    
    /**
     * Constructor for the Builder. This constructor creates a new
     * AnimationModelImpl.
     */
    public Builder() {
      this.model = new AnimatorModelImpl();
    }
    
    @Override
    public AnimatorModel build() {
      return this.model;  
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      model.addCanvas(new AnimationCanvas(width, height, new Point2D(x, y)));
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      // Build a Rectangle with given name - all other attributes default
      if (type.equals("rectangle")) {
        model.addShape(new Rectangle.Builder(name).build());
     
        // Build an Ellipse with given name - all other attributes default
      } else if (type.equals("ellipse")) {
        model.addShape(new Ellipse.Builder(name).build()); 
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      
      // Get Shape will ensure this shape must exist - otherwise exception will be thrown
      try {
        ShapeAnimation shape = model.getShapes().get(name); // Get Shape
        
        // Movement
        if (x1 != x2 || y1 != y2) {
          model.addAnimation(new MoveAnimation(shape, AnimationType.MOVE, 
                                   new Point2D(x1, y1), new Point2D(x2, y2),
                                   new ShapeColor(r1, g1, b1), new ShapeColor(r2, g2, b2),
                                   t1, t2, w1, w2, h1, h2));
          this.updateShapeAttributes(shape, x1, y1, w1, h1, r1, g1, b1, t1, t2); 
        }
        // Color Change
        if (r1 != r2 || g1 != g2 || b1 != b2) {
          model.addAnimation(new ChangeColorAnimation(shape, AnimationType.COLOR_CHANGE, 
                                    new Point2D(x1, y1), new Point2D(x2, y2),
                                    new ShapeColor(r1, g1, b1), new ShapeColor(r2, g2, b2),
                                    t1, t2, w1, w2, h1, h2));
          this.updateShapeAttributes(shape, x1, y1, w1, h1, r1, g1, b1, t1, t2);      }
        // Scale
        if (w1 != w2 || h1 != h2) {
          model.addAnimation(new ScaleAnimation(shape, AnimationType.SCALE, 
                                    new Point2D(x1, y1), new Point2D(x2, y2),
                                    new ShapeColor(r1, g1, b1), new ShapeColor(r2, g2, b2),
                                    t1, t2, w1, w2, h1, h2));
          this.updateShapeAttributes(shape, x1, y1, w1, h1, r1, g1, b1, t1, t2);      }
        
        /* Even if this motion does not represent Move, Scale or Color Change
           Shape "life" start time to end time will be updated if necessary */
        this.updateShapeAttributes(shape, x1, y1, w1, h1, r1, g1, b1, t1, t2);
        return this;

      } catch (NullPointerException npe) {
        throw new IllegalArgumentException("An animation was given for a shape not yet "
            + "created. A shape must be created prior to providing an animation.");
      }
    }
    
    /*
     * This method ensures that a Shape's time attributes (Start and End time) are
     * properly updated based on new motions. For example, if the shape is expected to
     * be removed at t = 10; however, at t = 5 another motion is requested and will last
     * until t = 15 - this method will ensure that the Shape's end time reflects the
     * additional 5 time units.
     * 
     * Integer Max is a default value. As such, this method will look for Integer Max
     * Values as well as new end times that exceed the current end time. 
     */
    private void updateShapeAttributes(ShapeAnimation shape, int startX, int startY,
                                       int startWidth, int startHeight,
                                       int startR, int startG, int startB,
                                       int startTime, int endTime) {
      
      // Update a Shape Appear Time if still in default state
      if (shape.getAppearTime() == Integer.MAX_VALUE) {
        shape.setAppearTime(startTime); // Set Appear Time
      }
      
      // Update a Shape Position if still in default state
      if (shape.getPosition().getX() == Integer.MAX_VALUE) {
        shape.moveShape(new Point2D(startX, startY));
      }
      
      // Update a Shape Width if still in default state
      if (shape.getWidth() == Integer.MAX_VALUE) {
        shape.setWidth(startWidth);
      }
      
      // Update a Shape Height if still in default state
      if (shape.getHeight() == Integer.MAX_VALUE) {
        shape.setHeight(startHeight);
      }
      
      // Update a Shape Color if still in default state
      if (shape.getColor().getR() == 0
          && shape.getColor().getG() == 0
          && shape.getColor().getB() == 0) {
        shape.setColor(new ShapeColor(startR, startG, startB));
      }
      
      // Update a Shape Remove time if in defualt state or if new max Remove time is found
      if (shape.getRemoveTime() == Integer.MAX_VALUE || shape.getRemoveTime() < endTime) {
        shape.setRemoveTime(endTime); // Update remove time 
      }
    }
  }
}
