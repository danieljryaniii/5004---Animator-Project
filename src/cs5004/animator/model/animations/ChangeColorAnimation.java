package cs5004.animator.model.animations;

import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;

/**
 * This class represents an animation where a Shape changes color
 * from one color to another color on a 2D Plane.
 */
public class ChangeColorAnimation extends AbstractAnimation {
  
  /**
   * Construct a ChangeColorAnimation object from a given shape, starting/ending position,
   * starting/ending color, starting/ending size and starting/ending time.
   * @param shape an {@link ShapeAnimation} shape associated with this animation
   * @param type the {@link AnimationType} type of animation this animation represents
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
   */
  public ChangeColorAnimation(ShapeAnimation shape, AnimationType type,
                              Point2D startPosition, Point2D endPosition,
                              ShapeColor startColor, ShapeColor endColor,
                              int startTime, int endTime,
                              double startWidth, double endWidth,
                              double startHeight, double endHeight) {

    super(shape, type, startPosition, endPosition, startColor, endColor,
          startTime, endTime, startWidth, endWidth, startHeight, endHeight);
  }  

  
  /*
   * This toString provides all the relevant information related to the
   * color changing animation to be performed, including the shape name, starting/ending
   * colors and starting/ending times.
   */
  @Override
  public String toString() {
    return                          this.getShape().getName() 
        + " changed color from: " + this.getStartColor().toString() 
        + " to: "                 + this.getEndColor().toString()
        + " from t="              + this.getStartTime() 
        + " to t="                + this.getEndTime();
  }  
  
  @Override
  public ChangeColorAnimation cloneAnimation() {
    return new ChangeColorAnimation(
        this.getShape().cloneShape(),
        this.getType(),
        this.getStartPosition().clone(),
        this.getEndPosition().clone(),
        this.getStartColor().cloneColor(),
        this.getEndColor().cloneColor(),
        this.getStartTime(),
        this.getEndTime(),
        this.getStartWidth(),
        this.getEndWidth(),
        this.getStartHeight(),
        this.getEndHeight()
        );
  }

}
