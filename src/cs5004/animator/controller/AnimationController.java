package cs5004.animator.controller;

/**
 * This interface represents a controller for animation programs
 * designed using MVC Pattern.
 */
public interface AnimationController {

  /**
   * Run/Begin the animation. The animation should instantly begin upon
   * invocation of this method. The animation should end once all animations
   * have completed their actions/the maximum animation time has been reached.
   */
  void animate();
}
