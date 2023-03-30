package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.AnimationCanvas;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.shapes.ShapeAnimation;

import java.util.Collection;
import java.util.Map;

/**
 * An Interface for Views associated with animations.
 */
public interface AnimationView {


  /**
   * Show an animation based on the given shapes and animations. Animations should
   * be shown on a screen/canvas of the given canvas width and canvas height. 
   * If an implementing class does not require this version of the showAnimation method, an
   * UnsupportedOperationException should be thrown.
   * @param shapes a Collection of {@link ShapeAnimation} shapes to be animated
   * @param animations a Collection of {@link IAnimation} animations to be animated
   * @param canvas an {@link AnimationCanvas} canvas for animations
   * @throws UnsupportedOperationException thrown if this method should not applicable
   *     for an implementing class
   */
  void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
                     AnimationCanvas canvas) throws UnsupportedOperationException;
  
  
  /**
   * Show an animation based on the given shapes and animations. Animations should
   * be shown on a screen/canvas of the given canvas width and canvas height. A
   * controller is passed so that the view can communicate directly with the controller.
   * If an implementing class does not require this version of the showAnimation method, an
   * UnsupportedOperationException should be thrown.
   * @param shapes a Collection of {@link ShapeAnimation} shapes to be animated
   * @param animations a Collection of {@link IAnimation} animations to be animated
   * @param canvas an {@link AnimationCanvas} canvas for animations
   * @param controller an {@link AnimationController} object
   * @throws UnsupportedOperationException thrown if this method should not applicable
   *     for an implementing class
   */
  void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
                     AnimationCanvas canvas, AnimationController controller)
                         throws UnsupportedOperationException;
}
