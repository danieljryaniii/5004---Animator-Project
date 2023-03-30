package cs5004.animator.model.animations;

import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;

/**
 * This interface contains all relevant methods for animating a shape.
 */
public interface IAnimation {

  /**
   * Get the {@link ShapeAnimation} shape associated with this Animation.
   * @return the {@link ShapeAnimation} associated with this Animation
   */
  ShapeAnimation getShape();
  
  /**
   * Get the {@link AnimationType} type of animation that this object represents.
   * For example an animation that moves a shape may return AnimationType.MOVE.
   * @return an {@link AnimationType}
   */
  AnimationType getType();
  
  /**
   * Get the {@link Point2D} starting position of the Shape to be animated. 
   * @return an {@link Point2D} representing the starting position of the shape
   *     used in this animation.
   */
  Point2D getStartPosition();
  
  /**
   * Get the {@link Point2D} ending position of the Shape to be animated.
   * @return an {@link Point2D} representing the ending position of the shape
   *     used in this animation
   */
  Point2D getEndPosition();
  
  /**
   * Get the {@link ShapeColor} starting color of the Shape to be animated.
   * @return an {@link ShapeColor} representing the starting color of the shape
   *     used in this animation
   */
  ShapeColor getStartColor();
  
  /**
   * Get the {@link ShapeColor} ending color of the Shape to be animated.
   * @return an {@link ShapeColor} representing the ending color of the shape
   *     used in this animation
   */
  ShapeColor getEndColor();
  
  /**
   * Get the starting time of this animation in unitless "ticks".
   * @return an integer representing the starting time of this animation
   */
  int getStartTime();
  
  /**
   * Get the ending time of this animation in unitless "ticks".
   * @return an integer representing the starting time of this animation
   */
  int getEndTime();
  
  /**
   * Get the starting width of the Shape to be animated. Width is represented
   * by the horizontal length from the left-most point to the right-most point -
   * i.e. circular/oval shapes' widths are their diameter/x-diameter.
   * @return the starting width of the Shape to be animated
   */
  double getStartWidth();
  
  /**
   * Get the ending width of the Shape to be animated. Width is represented
   * by the horizontal length from the left-most point to the right-most point -
   * i.e. circular/oval shapes' widths are their diameter/x-diameter. 
   * @return the ending width of the Shape to be animated
   */
  double getEndWidth();
  
  /**
   * Get the starting height of the Shape to be animated. Height is represented
   * by the vertical length from the bottom-most point to the top-most point -
   * i.e. circular/oval shapes' heights are their diameter/y-diameter.
   * @return the starting height of the Shape to be animated
   */
  double getStartHeight();
  
  /**
   * Get the ending height of the Shape to be animated. Height is represented
   * by the vertical length from the bottom-most point to the top-most point -
   * i.e. circular/oval shapes' heights are their diameter/y-diameter.
   * @return the ending height of the Shape to be animated
   */
  double getEndHeight();
  
  /**
   * Return an independent copy of this IAnimation object. The copy should have
   * attributes of the same value; however, they should not be references to
   * objects contained within the original IAnimation object. The returned IAnimation
   * object should be independent and stand on its own.
   */
  IAnimation cloneAnimation();
}
