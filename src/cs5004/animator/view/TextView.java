package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.AnimationCanvas;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeType;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;


/**
 * This class implements an animation view that builds an
 * text format of the given animation. 
 */
public class TextView implements AnimationView {

  private Appendable out;
  private int delay;

  /**
   * Construct a Text view with a given output source, which
   * implements Appendable and speed. If the given speed is greater than
   * 1000, the animation will default to 1000ticks per second. If the given
   * speed is negative or zero, the animation will be set to a default of
   * 1tick per second.
   * @param out an {@link Appendable} output source
   * @param speed the speed of this animation in "ticks" per second
   */
  public TextView(Appendable out, int speed) {
    this.out = out;
    
    if (speed > 1000) {
      this.delay = 1;
    } else if (speed < 1) {
      this.delay = 1000;
    } else {
      this.delay = 1000 / speed;
    }
  }
  
  @Override
  public void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
      AnimationCanvas canvas, AnimationController controller) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("The TextView does not require the controller.");
  }

  @Override
  public void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
      AnimationCanvas canvas) {
    
    /* *** DISPLAY A HEADER AT THE TOP OF THE ANIMATION *** */
    try {
      this.out.append("******************************\n"
                    + "** TEXTUAL SHAPE ANIMATION: **\n"
                    + "******************************\n\n");      
    } catch (IOException ioe1) {
      throw new IllegalStateException("Error encountered when displaying animation header");
    }
     
    /* *** PRINT ALL SHAPE INFO (CREATION) *** */
    for (ShapeAnimation shape: shapes.values()) {
      switch (shape.getType()) {
        // Show Rectangle Shape Info
        case RECTANGLE:
          this.rectangleShapeHelper(shape);
          break;
        // Show Ellipse Shape Info
        case ELLIPSE:
          this.ellipseShapeHelper(shape);
          break;
        // Default - Do Nothing
        default:
          break;
      }
    }
    
    /* *** PRINT ALL ANIMATION INFO IN ORDER OF ANIMATION START TIMES *** */
    for (IAnimation animation: animations) {
      switch (animation.getType()) {
        // Show Move Animation Info
        case MOVE:
          this.moveAnimationHelper(animation);
          break;
        // Show Scale Animation Info
        case SCALE:
          this.scaleAnimationHelper(animation);
          break;
        // Show Color Change Animation Info
        case COLOR_CHANGE:
          this.colorChangeAnimationHelper(animation);
          break;
        // Default - Do Nothing
        default:
          break;
      }
    }
    
    /* *** DISPLAY A FOOTER AT THE BOTTOM/END OF THE ANIMATION *** */
    try {
      out.append("******************************\n"
               + "**       END ANIMATION:     **\n"
               + "******************************");
    } catch (IOException ioe3) {
      throw new IllegalStateException("Error encountered when displaying animation footer.");
    }
  }
  
  
  //**********************************************//
  /*     PRIVATE STING BUILDER HELPER METHODS    */
  //**********************************************//
  
  /*
   * Create a text animation containing all relevant attributes of a given
   * rectangle {@link ShapeAnimation}.
   */
  private void rectangleShapeHelper(ShapeAnimation shape) {
    try {
      out.append("Name: "       + shape.getName()                 + "\n"
          + "Type: "            + "rectangle"                     + "\n"
          + "Top Left Corner: " + shape.getPosition().toString()  + "\n"
          + "Height: "          + shape.getHeight()               + "\n"
          + "Width: "           + shape.getWidth()                + "\n"
          + "Color: "           + shape.getColor()                + "\n"
          + "Appear At: "       + (shape.getAppearTime() * delay) + "ms\n"
          + "Disappear At: "    + (shape.getRemoveTime() * delay) + "ms\n\n");
    } catch (IOException ioe4) {
      throw new IllegalStateException("Error encountered when building "
                                    + "rectangle shape information.");
    }  
  }
  
  /*
   * Create a text animation containing all relevant attributes of a given
   * ellipse {@link ShapeAnimation}.
   */
  private void ellipseShapeHelper(ShapeAnimation shape) {
    try {
      out.append("Name: "     + shape.getName()                     + "\n"
          + "Type: "          + "ellipse"                           + "\n"
          + "Center: "        + (shape.getCenterPoint().toString()) + "\n"
          + "X Radius: "      + shape.getWidth()  / 2.0             + "\n"
          + "Y Radius: "      + shape.getHeight() / 2.0             + "\n"
          + "Color: "         + shape.getColor()                    + "\n"
          + "Appear At: "     + (shape.getAppearTime() * delay)     + "ms\n"
          + "Disappear At: "  + (shape.getRemoveTime() * delay)     + "ms\n\n");
    } catch (IOException ioe4) {
      throw new IllegalStateException("Error encountered when building "
                                    + "ellipse shape information.");
    }  
  }
  
  /*
   * Create text animation containing all relevant details pertaining to a given
   * move animation {@link IAnimation}.
   */
  private void moveAnimationHelper(IAnimation animation) {
    if (animation.getShape().getType() == ShapeType.RECTANGLE) {
      try {
        out.append(
            ""                + animation.getShape().getName() 
            + " moved from "  + animation.getStartPosition().toString() 
            + " to "          + animation.getEndPosition().toString()
            + " from t="      + (animation.getStartTime() * delay) + "ms"
            + " to t="        + (animation.getEndTime()   * delay) + "ms" + "\n\n");
      } catch (IOException ioe5) {
        throw new IllegalStateException("Error encountered when building "
                                      + "move animation information.");
      }
    }
    else if (animation.getShape().getType() == ShapeType.ELLIPSE) {
      try {
        Point2D startCenter = new Point2D(animation.getStartPosition().getX() 
                                              + (animation.getStartWidth() / 2.0),
                                          animation.getStartPosition().getY()
                                              + (animation.getStartHeight() / 2.0));
        Point2D endCenter = new Point2D(animation.getEndPosition().getX() 
                                              + (animation.getEndWidth() / 2.0),
                                          animation.getEndPosition().getY()
                                              + (animation.getEndHeight() / 2.0));
        out.append(
            ""                + animation.getShape().getName() 
            + " moved from "  + startCenter.toString()
            + " to "          + endCenter.toString()
            + " from t="      + (animation.getStartTime() * delay) + "ms"
            + " to t="        + (animation.getEndTime()   * delay) + "ms" + "\n\n");
      } catch (IOException ioe5) {
        throw new IllegalStateException("Error encountered when building "
                                      + "move animation information.");
      }
    }
  }
  
  /*
   * Create text animation containing all relevant details pertaining to a given
   * scale animation {@link IAnimation}.
   */
  private void scaleAnimationHelper(IAnimation animation) {    
    if (animation.getShape().getType() == ShapeType.RECTANGLE) {
      try {
        out.append(
            ""                       + animation.getShape().getName() 
            + " scaled from width: " + animation.getStartWidth() 
            + " and height: "        + animation.getStartHeight()
            + " to width: "          + animation.getEndWidth() 
            + " and height: "        + animation.getEndHeight() 
            + " from t="             + (animation.getStartTime() * delay) + "ms"
            + " to t="               + (animation.getEndTime()   * delay) + "ms" + "\n\n");
      } catch (IOException ioe6) {
        throw new IllegalStateException("Error encountered when building "
                                      + "scale animation information.");
      }
    }
    else if (animation.getShape().getType() == ShapeType.ELLIPSE) {
      try {
        out.append(
            ""                           + animation.getShape().getName() 
            + " scaled from X Radius: "  + animation.getStartWidth()  / 2.0
            + " and Y Radius: "          + animation.getStartHeight() / 2.0
            + " to X Radius: "           + animation.getEndWidth()    / 2.0
            + " and Y Radius: "          + animation.getEndHeight()   / 2.0
            + " from t="                 + (animation.getStartTime() * delay) + "ms"
            + " to t="                   + (animation.getEndTime()   * delay) + "ms" + "\n\n");
      } catch (IOException ioe6) {
        throw new IllegalStateException("Error encountered when building "
                                      + "scale animation information.");
      }
    }
  }

  /*
   * Create text animation containing all relevant details pertaining to a given
   * color change animation {@link IAnimation}.
   */
  private void colorChangeAnimationHelper(IAnimation animation) {    
    try {
      out.append(
          ""                        + animation.getShape().getName() 
          + " changed color from: " + animation.getStartColor().toString() 
          + " to: "                 + animation.getEndColor().toString()
          + " from t="              + (animation.getStartTime() * delay) + "ms"
          + " to t="                + (animation.getEndTime()   * delay) + "ms" + "\n\n");
    } catch (IOException ioe7) {
      throw new IllegalStateException("Error encountered when building "
                                    + "color change animation information.");
    }
  }
}
