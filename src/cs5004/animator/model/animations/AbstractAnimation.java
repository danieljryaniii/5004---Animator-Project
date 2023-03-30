package cs5004.animator.model.animations;

import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;

/**
 * This abstract class represents an Abstract Shape animation and contains all
 * cross-functional fields and methods applicable to animations to be performed
 * on shapes.
 */
public abstract class AbstractAnimation implements IAnimation {

  private ShapeAnimation shape;
  private AnimationType type;
  private Point2D startPosition;
  private Point2D endPosition;
  private ShapeColor startColor;
  private ShapeColor endColor;
  private int startTime;
  private int endTime;
  private double startWidth;
  private double endWidth;
  private double startHeight;
  private double endHeight;
  
  /**
   * Construct a new AbstractAnimation object. This object should contain all relevant
   * attributes of a ShapeAnimation in both their respective starting state and
   * ending state for the animation to be performed.
   * @param shape an {@link ShapeAnimation} shape associated with this animation
   * @param type the {@link AnimationType} type of animation this AbstractAnimation represents
   * @param startPosition a {@link Point2D} representing the starting position of this animation
   * @param endPosition a {@link Point2D} representing the ending position of this animation
   * @param startColor a {@link ShapeColor} representing the starting color of this animation
   * @param endColor a {@link ShapeColor} representing the ending color of this animation
   * @param startTime the starting time/tick of this animation
   * @param endTime the ending time/tick of this animation
   * @param startWidth the starting width of this animation
   * @param endWidth the ending width of this animation
   * @param startHeight the starting height of this animation
   * @param endHeight the ending height of this animation
   * @throws IllegalArgumentException thrown if the start or end time is negative. Thrown if the
   *     start/end width or start/end height are non-positive.
   */
  public AbstractAnimation(ShapeAnimation shape, AnimationType type,
                       Point2D startPosition, Point2D endPosition, 
                       ShapeColor startColor, ShapeColor endColor,
                       int startTime, int endTime,
                       double startWidth, double endWidth,
                       double startHeight, double endHeight) throws IllegalArgumentException {
    if (startTime < 0 || endTime < 0
        || startWidth < 1 || endWidth < 1
        || startHeight < 1 || endHeight < 1) {
      throw new IllegalArgumentException("Cannot create an animation that has negative start/end"
                            + " times, or that have non-positive starting/ending widths/heights.");
    }
    this.shape = shape;
    this.type = type;
    this.startPosition = startPosition;
    this.endPosition = endPosition;
    this.startColor = startColor;
    this.endColor = endColor;
    this.startTime = startTime;
    this.endTime = endTime;
    this.startWidth = startWidth;
    this.endWidth = endWidth;
    this.startHeight = startHeight;
    this.endHeight = endHeight;
  }
  
  @Override
  public ShapeAnimation getShape() {
    return this.shape;
  }

  @Override
  public AnimationType getType() {
    return this.type;
  }

  @Override
  public Point2D getStartPosition() {
    return this.startPosition;
  }
  
  @Override
  public Point2D getEndPosition() {
    return this.endPosition;
  }
  
  @Override 
  public ShapeColor getStartColor() {
    return this.startColor;
  }
  
  @Override 
  public ShapeColor getEndColor() {
    return this.endColor;
  }
  
  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }
  
  @Override
  public double getStartWidth() {
    return this.startWidth;
  }
  
  @Override 
  public double getEndWidth() {
    return this.endWidth;
  }
  
  @Override 
  public double getStartHeight() {
    return this.startHeight;
  }
  
  @Override 
  public double getEndHeight() {
    return this.endHeight;
  }
}
