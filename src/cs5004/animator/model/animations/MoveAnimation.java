package cs5004.animator.model.animations;

import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;
import cs5004.animator.model.shapes.ShapeType;

/**
 * This class represents an animation where a Shape moves
 * from one position to another position on a 2D Plane.
 */
public class MoveAnimation extends AbstractAnimation {

  /**
   * Construct a MoveAnimation object from a given shape, starting/ending position,
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
  public MoveAnimation(ShapeAnimation shape, AnimationType type,
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
   * move animation to be performed, including the shape name, starting/ending
   * positions and starting/ending times.
   */
  @Override 
  public String toString() {
    if (this.getShape().getType() == ShapeType.ELLIPSE) {
      Point2D startCenter = new Point2D(
                            this.getStartPosition().getX() + (this.getStartWidth() / 2.0),
                            this.getStartPosition().getY() + (this.getStartHeight() / 2.0));
      Point2D endCenter = new Point2D(
                            this.getEndPosition().getX() + (this.getEndWidth() / 2.0),
                           this.getEndPosition().getY() + (this.getEndHeight() / 2.0));

      return                  this.getShape().getName() 
          + " moved from "  + startCenter.toString()
          + " to "          + endCenter.toString()
          + " from t="      + this.getStartTime() 
          + " to t="        + this.getEndTime();
    }
    // Non-Circle/Oval Shapes
    return                this.getShape().getName() 
      + " moved from "  + this.getStartPosition().toString() 
      + " to "          + this.getEndPosition().toString()
      + " from t="      + this.getStartTime() 
      + " to t="        + this.getEndTime();
  }
  
  @Override
  public MoveAnimation cloneAnimation() {
    return new MoveAnimation(
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
