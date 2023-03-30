package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.AnimationCanvas;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeType;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;


/**
 * This class implements an animation view that builds an
 * SVG/XML format of the given animation. 
 */
public class SVGView implements AnimationView {

  private Appendable out;
  private int delay;
  
  /**
   * Construct an SVG view with a given output source, which
   * implements Appendable and speed. If the given speed is greater than
   * 1000, the animation will default to 1000ticks per second. If the given
   * speed is negative or zero, the animation will be set to a default of
   * 1tick per second.
   * @param out an {@link Appendable} output source
   * @param speed the speed of this animation in "ticks" per second
   */
  public SVGView(Appendable out, int speed) {
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
    throw new UnsupportedOperationException("The SCGView does not require the controller.");
  }
  
  @Override
  public void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
                            AnimationCanvas canvas) {
    
    // Calculate if shift is needed
    double xShift = 0 - canvas.getTopLeft().getX();
    double yShift = 0 - canvas.getTopLeft().getY();
    
    /*
     * ADD THE SVG HEADER TO THE ANIMATION
     */
    try {
      out.append(
            "<svg width=\""     + canvas.getWidth()  + "\" "
          + "height=\""         + canvas.getHeight() + "\" "
          + "version=\"1.1\"\n"
          + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n");
    } catch (IOException ioe1) {
      throw new IllegalStateException("Error encountered when creating header tags/information.");
    }
    
    /*
     * CREATE ALL SHAPE BLOCKS AND ANIMATION TAGS WITHIN (WHERE APPLICABLE)
     */
    int startTime;
    int duration;
    for (ShapeAnimation shape: shapes.values()) {
      // Declare the Shape
      switch (shape.getType()) {
        case RECTANGLE:
          this.declareRectangle(shape, xShift, yShift);
          break;
        case ELLIPSE:
          this.declareEllipse(shape, xShift, yShift);
          break;
        default:
          break;
      }
      
      // Add Animate tag to make the Shape Visible
      try {
        out.append(
            "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\""                 + shape.getAppearTime() * delay         + "ms\" "
            + "dur=\""                   + "1"                                   + "ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n");
      } catch (IOException ioe3) {
        throw new IllegalStateException("Error encountered when creating "
                                      + "<animate> tag (make visible).");
      }

      
      for (IAnimation animation: animations) {
        
        // Get all animations associated with the current shape
        if (animation.getShape().getName().equals(shape.getName())) {
          
          startTime = animation.getStartTime() * delay;
          duration = (animation.getEndTime() * delay) 
                   - (animation.getStartTime() * delay);          
          
          // Create animation tags based on type of animation
          switch (animation.getType()) {
            case MOVE:
              if (animation.getShape().getType() == ShapeType.RECTANGLE) {
                try {
                  out.append(
                      "    <animate "
                      + "attributeType=\"xml\" "
                      + "begin=\""         + startTime                                     + "ms\" "
                      + "dur=\""           + duration                                      + "ms\" "
                      + "attributeName=\"" + "x"                                           + "\" "
                      + "from=\""          + (animation.getStartPosition().getX() + xShift) + "\" " 
                      + "to=\""            + (animation.getEndPosition().getX()   + xShift) + "\" "
                      + "fill=\"freeze\" />"
                      + "\n"
                 
                      + "    <animate "
                      + "attributeType=\"xml\" "
                      + "begin=\""         + startTime                                     + "ms\" "
                      + "dur=\""           + duration                                      + "ms\" "
                      + "attributeName=\"" + "y"                                          + "\" "
                      + "from=\""          + (animation.getStartPosition().getY() + yShift) + "\" " 
                      + "to=\""            + (animation.getEndPosition().getY()   + yShift) + "\" "
                      + "fill=\"freeze\" />"
                      + "\n");
                  break;
                } catch (IOException ioe1) {
                  throw new IllegalStateException("Error encountered when creating "
                                                + "<animate> tag (move).");
                }
              } else if (animation.getShape().getType() == ShapeType.ELLIPSE) {
                try {
                  out.append(
                      "    <animate "
                      + "attributeType=\"xml\" "
                      + "begin=\""         + startTime                                 + "ms\" "
                      + "dur=\""           + duration                                  + "ms\" "
                      + "attributeName=\"" + "cx"                                      + "\" "
                      
                      // Shift performed below to move reference point from min-corner to center
                      + "from=\""          + ( (animation.getStartPosition().getX() + xShift) 
                                             + (animation.getStartWidth() / 2) ) 
                                                                                       + "\" " 
                      + "to=\""            + ( (animation.getEndPosition().getX()   + xShift) 
                                             + (animation.getEndWidth() / 2) ) 
                                                                                       + "\" "
                      + "fill=\"freeze\" />"
                      + "\n"
                 
                      + "    <animate "
                      + "attributeType=\"xml\" "
                      + "begin=\""         + startTime                                 + "ms\" "
                      + "dur=\""           + duration                                  + "ms\" "
                      + "attributeName=\"" + "cy"                                      + "\" "
                      + "from=\""          + ( (animation.getStartPosition().getY() + yShift) 
                                           + (animation.getStartHeight() / 2) ) 
                                                                                       + "\" " 
                      + "to=\""            + ( (animation.getEndPosition().getY()   + yShift) 
                                             + (animation.getEndHeight() / 2) ) 
                                                                                       + "\" "
                      + "fill=\"freeze\" />"
                      + "\n");
                  break;
                } catch (IOException ioe1) {
                  throw new IllegalStateException("Error encountered when creating "
                                                + "<animate> tag (move).");
                }                
              } else {
                break;
              }

            case SCALE:
              if (animation.getShape().getType() == ShapeType.RECTANGLE) {
                try {
                  out.append(
                      "    <animate "
                      + "attributeType=\"xml\" "
                      + "begin=\""                 + startTime                  + "ms\" "
                      + "dur=\""                   + duration                   + "ms\" "
                      + "attributeName=\""         + "width"                    + "\" "
                      + "from=\""                  + animation.getStartWidth()  + "\" " 
                      + "to=\""                    + animation.getEndWidth()    + "\" "
                      + "fill=\"freeze\" />"
                      + "\n"
                   
                      + "    <animate "
                      + "attributeType=\"xml\" "
                      + "begin=\""                 + startTime                   + "ms\" "
                      + "dur=\""                   + duration                    + "ms\" "
                      + "attributeName=\""         + "height"                    + "\" "
                      + "from=\""                  + animation.getStartHeight()  + "\" " 
                      + "to=\""                    + animation.getEndHeight()    + "\" "
                      + "fill=\"freeze\" />"
                      + "\n");
                } catch (IOException ioe2) {
                  throw new IllegalStateException("Error encountered when creating "
                                                + "<animate> tag (scale).");
                }
              }
              else if (animation.getShape().getType() == ShapeType.ELLIPSE) {
                try {
                  out.append(
                      "    <animate "
                      + "attributeType=\"xml\" "
                      + "begin=\""                 + startTime                        + "ms\" "
                      + "dur=\""                   + duration                         + "ms\" "
                      + "attributeName=\""         + "rx"                             + "\" "
                      + "from=\""                  + animation.getStartWidth() / 2.0  + "\" " 
                      + "to=\""                    + animation.getEndWidth()   / 2.0  + "\" "
                      + "fill=\"freeze\" />"
                      + "\n"
                   
                      + "    <animate "
                      + "attributeType=\"xml\" "
                      + "begin=\""                 + startTime                          + "ms\" "
                      + "dur=\""                   + duration                           + "ms\" "
                      + "attributeName=\""         + "ry"                               + "\" "
                      + "from=\""                  + animation.getStartHeight()  / 2.0  + "\" " 
                      + "to=\""                    + animation.getEndHeight()    / 2.0  + "\" "
                      + "fill=\"freeze\" />"
                      + "\n");
                  break;
                } catch (IOException ioe2) {
                  throw new IllegalStateException("Error encountered when creating "
                                                + "<animate> tag (scale).");
                }                
              }
              break;
            
            case COLOR_CHANGE:
              try {
                out.append(
                    "    <animate "
                    + "attributeType=\"xml\" "
                    + "begin=\""                 + startTime                         + "ms\" "
                    + "dur=\""                   + duration                          + "ms\" "
                    + "attributeName=\"fill\" "
                    + "from=\"rgb("              + animation.getStartColor().getR()  + "," 
                                                 + animation.getStartColor().getG()  + "," 
                                                 + animation.getStartColor().getB()  + ")\" "
                    + "to=\"rgb("                + animation.getEndColor().getR()    + "," 
                                                 + animation.getEndColor().getG()    + "," 
                                                 + animation.getEndColor().getB() 
                    + ")\" fill=\"freeze\" />"
                    + "\n");
                break;
              } catch (IOException ioe3) {
                throw new IllegalStateException("Error encountered when creating "
                                              + "<animate> tag (color change).");
              }
              
            default:
              break; // Default to breaking out of Switch/Do Nothing
          }          
        }
      }
      
      /*
       * CLOSE EACH SHAPE'S SVG BLOCK
       */
      switch (shape.getType()) {
        case RECTANGLE:
          this.closeRectangle();
          break;
        case ELLIPSE:
          this.closeEllipse();
          break;
        default:
          break;
      }
    }

    /*
     * END THE ANIMATION -- ADD CLOSING TAG TO INDICATE END
     */
    try {
      out.append("\n</svg>\n");
    } catch (IOException ioe4) {
      throw new IllegalStateException("Error encountered when creating </svg> closing tag.");
    }
  }

  
  //**********************************************//
  /*     PRIVATE STING BUILDER HELPER METHODS    */
  //**********************************************//

  /*
   * Declare a new Rectangle shape. Begin the shape block. Rectangles declared will
   * be declared in a hidden state.
   */
  private void declareRectangle(ShapeAnimation shape, double xShift, double yShift) {
    try {
      out.append(
          "<rect id=\""    + shape.getName()                       + "\" "
          + "x=\""         + (shape.getPosition().getX() + xShift) + "\" "
          + "y=\""         + (shape.getPosition().getY() + yShift) + "\" "
          + "width=\""     + shape.getWidth()                      + "\" "
          + "height=\""    + shape.getHeight()                     + "\" " 
          + "fill=\"rgb("  + shape.getColor().getR()               + "," 
                           + shape.getColor().getG()               + "," 
                           + shape.getColor().getB()               + ")\" "
          + "visibility=\"hidden\" >\n");
    } catch (IOException ioe2) {
      throw new IllegalStateException("Error encountered when creating shape tag <rect>.");
    }
  }

  /*
   * Declare a new Ellipse shape. Begin the shape block. Ellipse declared will
   * be declared in a hidden state.
   */
  private void declareEllipse(ShapeAnimation shape, double xShift, double yShift) {
    try {
      out.append(
          "<ellipse id=\""  + shape.getName()                       + "\" "

          // Shift performed below to move reference point from min-corner to center
          + "cx=\""         + ( (shape.getPosition().getX() + xShift) 
                              + (shape.getWidth() / 2))
                                                                    + "\" "
          + "cy=\""         + ( (shape.getPosition().getY() + yShift)  
                              + (shape.getHeight() / 2)) 
                                                                    + "\" "
          + "rx=\""         + shape.getWidth()  / 2                 + "\" "  // Divide by 2 
          + "ry=\""         + shape.getHeight() / 2                 + "\" "  // Divide by 2
          + "fill=\"rgb("   + shape.getColor().getR()               + "," 
                            + shape.getColor().getG()               + "," 
                            + shape.getColor().getB()               + ")\" "
          + "visibility=\"hidden\" >\n");
    } catch (IOException ioe3) {
      throw new IllegalStateException("Error encountered when creating shape tag <ellipse>.");
    }
  }
  
  /*
   * Close a Rectangle Shape's block.
   */
  private void closeRectangle() {
    try {
      out.append("</rect>\n\n");
    } catch (IOException ioe4) {
      throw new IllegalStateException("Error encountered when closing shape tag </rect>.");
    }
  }
  
  /*
   * Close a Ellipse Shape's block.
   */
  private void closeEllipse() {
    try {
      out.append("</ellipse>\n\n");
    } catch (IOException ioe5) {
      throw new IllegalStateException("Error encountered when closing shape tag </ellipse>.");
    }
  }
}
