package cs5004.animator.model.shapes;

/**
 * This interface represents a ShapeAnimation. All shapes should have the below
 * functionalities. If a shape does not have any of the below functionality
 * it should be handled accordingly by implementing classes. This interface should
 * be usable by both square and circular shapes.
 */
public interface ShapeAnimation {
  
  /**
   * Change the state of this ShapeAnimation to visible.
   */
  void showShape();
  
  /**
   * Change the state of the ShapeAnimation to hidden.
   */
  void hideShape();
  
  /**
   * Return true if the ShapeAnimation is visible, otherwise false.
   * @return true if the ShapeAnimation is visible, otherwise false
   */
  boolean isVisible();
  
  /**
   * Move this ShapeAnimation to a new given location. If the location is invalid,
   * no action will be taken and this ShapeAnimation will remain in its current
   * location.
   * @param newPosition a {@link Point2D} representing the position to move to
   */
  void moveShape(Point2D newPosition);
  
  /**
   * Get the current position of this ShapeAnimation as a {@link Point2D}.
   * @return the current position of this Shape animation
   */
  Point2D getPosition();
  
  /**
   * Scale the size of this ShapeAnimation given a new Height and Width. 
   * @param newWidth the new width of this ShapeAnimation. Must be positive
   * @param newHeight the new height of this ShapeAnimation. Must be positive
   * @throws IllegalArgumentException thrown if either the new width or new height
   *     are not positive
   */
  void scale(double newWidth, double newHeight) throws IllegalArgumentException;
  
  /**
   * Set the color of this ShapeAnimation based on given RGB values.
   * @param color an {@link ShapeColor} for this ShapeAnimation
   */
  void setColor(ShapeColor color);

  /**
   * Set the time that this shape should appear during the animation. Time for
   * purposes of this model/ShapeAnimation are arbitrary.
   * @param appearTime the time this ShapeAnimation will appear in the animation
   * @throws IllegalArgumentException thrown if the given appear time is negative
   */
  void setAppearTime(int appearTime) throws IllegalArgumentException;
  
  /**
   * Set the time that this shape should be removed from the animation process. Time
   * for purposes of this model/ShapeAnimation are arbitrary.
   * @param removeTime the time this ShapeAnimation will be removed from the animation process
   * @throws IllegalArgumentException thrown if the given remove time is negative
   */
  void setRemoveTime(int removeTime) throws IllegalArgumentException;
  
  /**
   * Set the width of this ShapeAnimation. Width is the distance from the
   * left-most part of the shape to the right-most part of the shape.
   * @param width the width for this ShapeAnimation
   * @throws IllegalArgumentException thrown if the width given is not positive
   */
  void setWidth(double width) throws IllegalArgumentException;
  
  /**
   * Set the w\ of this ShapeAnimation. Height is the vertical 
   * distance from the bottom of the shape to the top.
   * @param height the height for this ShapeAnimation
   * @throws IllegalArgumentException thrown if the height given is not positive
   */
  public void setHeight(double height) throws IllegalArgumentException;
  
  /**
   * Get the color of this ShapeAnimation.
   */
  ShapeColor getColor();
  
  /**
   * Get the name of this ShapeAnimation.
   */
  String getName();
  
  /**
   * Get the appearance time of this ShapeAnimation.
   * The appearance time is the time that this shape should
   * appear in the view of the program.
   */
  int getAppearTime();
  
  /**
   * Get the removal time of this ShapeAnimation.
   * Removal time is the time that this shape should be removed
   * from the view of the program.
   */
  int getRemoveTime();

  /**
   * Get the height of this ShapeAnimation. Height is the vertical 
   * distance from the bottom of the shape to the top.
   * @return the height of this ShapeAnimation
   */
  double getHeight();
  
  /**
   * Get the width of this ShapeAnimation. Width is the distance from the
   * left-most part of the shape to the right-most part of the shape.
   * @return the width of this ShapeAnimation
   */
  double getWidth();
  
  /**
   * Get the type of this ShapeAnimation.
   */
  ShapeType getType();
  
  /**
   * Get the center point of this ShapeAnimation. The reference point of
   * every ShapeAnimaiton should be the minimum-corner.  
   */
  Point2D getCenterPoint();
  
  /**
   * Make an independent clone of this Shape. The shape returned will be an independent
   * copy of this Shape. 
   * @return an independent copy of this Shape.
   */
  ShapeAnimation cloneShape();  
}
