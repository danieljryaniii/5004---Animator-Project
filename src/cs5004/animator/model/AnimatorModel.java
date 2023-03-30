package cs5004.animator.model;

import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.shapes.ShapeAnimation;

/**
 * Interface for the model/functionality of a shape animator. A shape animator
 * should be able to receive shapes, animations and a canvas on which it will
 * animate. The data structures used to store the aformentioned are implementation
 * specific.
 */
public interface AnimatorModel {
  
  /**
   * Add a {@link ShapeAnimation} Object to the Animator Model. If the provided shape
   * has the same name as a shape that already exists, an IllegalArgumentException
   * will be thrown. 
   * @param shape a valid {@link ShapeAnimation} object.
   */
  void addShape(ShapeAnimation shape) throws IllegalArgumentException;

  /**
   * Add an {@link IAnimation} Object to the Animator Model. 
   * @param animation a valid {@link IAnimaiton} object.
   */
  void addAnimation(IAnimation animation);
  
  /**
   * Add an {@link AnimationCanvas} to the Animator Model. If a canvas
   * already exists, this will overwrite the current Animation Canvas.
   * @param canvas a valid {@link AnimationCanvas} object.
   */
  void addCanvas(AnimationCanvas canvas);
}
