package cs5004.animator.controller;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.view.AnimationView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;

/**
 * This class implements the AnimationController interface and is
 * the controller for Shape Animations.
 */
public class AnimationControllerImpl implements AnimationController {

  private AnimatorModelImpl model;
  private AnimationView view;
  
  /**
   * Construct an AnimationController with a given model and view. This
   * AnimationController will communicate with the given model and view
   * to create and animation.
   * @param model an {@link AnimatorModel} model
   * @param view an {@link AnimationView} view
   */
  public AnimationControllerImpl(AnimatorModelImpl model, AnimationView view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void animate() {
    // Get Data from the Model
    Map<String, ShapeAnimation> shapes = model.getShapes();
    Map<Integer, ArrayList<IAnimation>> animations = model.getAnimations();
    SortedSet<Integer> times = model.getTimes();
    
    // Tell view to start the animation and give it a lightweight copy of the data
    try {
      view.showAnimation(AnimationControllerImpl.copyShapes(shapes), 
                         AnimationControllerImpl.copyAnimations(animations, times), 
                         model.getCanvas().cloneCanvas(), this);
    } catch (UnsupportedOperationException uop) {
      view.showAnimation(AnimationControllerImpl.copyShapes(shapes), 
          AnimationControllerImpl.copyAnimations(animations, times), 
          model.getCanvas().cloneCanvas());      
    }
  }
  
  /*
   * Return a copy of the shape data received from the model. The map
   * received from the model should implement a Linked Hash Map which maintains
   * order (based on order in which items were added). If the provided shape Map
   * is not an instance of Linked Hash Map, results may differ from expected.
   */
  private static Map<String, ShapeAnimation> copyShapes(Map<String, ShapeAnimation> shapes) {
    Map<String, ShapeAnimation> cShapes = new LinkedHashMap<>();
    
    for (ShapeAnimation shape: shapes.values()) {
      cShapes.put(shape.getName(), shape.cloneShape());
    }
    return cShapes;
  }
  
  /*
   * Return a lightweight copy of the animation data received from the model. The set of times
   * received from the model should implement a Sorted Set which maintains
   * order (based on ascending order). If the provided times set is not an instance of
   * Sorted Set, results may differ from expected.
   */
  private static ArrayList<IAnimation> copyAnimations(Map<Integer, 
                                                      ArrayList<IAnimation>> animations, 
                                                      SortedSet<Integer> times) {
    ArrayList<IAnimation> cAnimations = new ArrayList<>();
    
    for (Integer time: times) {
      for (IAnimation animation: animations.get(time)) {
        cAnimations.add(animation);
      }
    }
    return cAnimations;
  }
}
