package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;

import cs5004.animator.model.AnimationCanvas;
import cs5004.animator.model.animations.AnimationType;
import cs5004.animator.model.animations.ChangeColorAnimation;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.animations.MoveAnimation;
import cs5004.animator.model.animations.ScaleAnimation;
import cs5004.animator.model.shapes.Ellipse;
import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.Rectangle;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.TextView;
import org.junit.Test;

import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A JUnit Testing Suite for the TextView class.
 */
public class TextViewTest {
  
  /**
   * Test the showAnimation method of the TextView class
   * where only shapes were declared and no animations
   * were specified.
   */
  @Test
  public void testOnlyShapesNoAnimation() {
    StringBuilder out = new StringBuilder();
    AnimationView view = new TextView(out, 1);
    
    ShapeAnimation r = new Rectangle.Builder("R")
                                    .referencePoint(new Point2D(200, 200))
                                    .width(50)
                                    .height(100)
                                    .appearTime(1)
                                    .removeTime(100)
                                    .build();
    
    ShapeAnimation c = new Ellipse.Builder("C")
                                  .referencePoint(new Point2D(440, 70))
                                  .width(120)
                                  .height(60)
                                  .appearTime(6)
                                  .removeTime(100)
                                  .build();
    
    Map<String, ShapeAnimation> shapes = new HashMap<>();
    ArrayList<IAnimation> animations = new ArrayList<>();
    // Arbitrarily set canvas
    AnimationCanvas canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
  }
  
  /**
   * Test an animation with a move.
   */
  @Test
  public void testAnimationWithMove() {
    StringBuffer out = new StringBuffer();
    AnimationView view = new TextView(out, 1);
    
    ShapeAnimation r = new Rectangle.Builder("R")
                                    .referencePoint(new Point2D(200, 200))
                                    .width(50)
                                    .height(100)
                                    .appearTime(1)
                                    .removeTime(100)
                                    .build();
    
    ShapeAnimation c = new Ellipse.Builder("C")
                                  .referencePoint(new Point2D(440, 70))
                                  .width(120)
                                  .height(60)
                                  .appearTime(6)
                                  .removeTime(100)
                                  .build();
    
    IAnimation rMove = new MoveAnimation(
        r, AnimationType.MOVE, new Point2D(200, 200), new Point2D(300, 300),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        1, 100, 50, 50, 100, 100
        );
    
    IAnimation cMove = new MoveAnimation(
        c, AnimationType.MOVE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        6, 100, 120, 120, 60, 60
        );            
    
    Map<String, ShapeAnimation> shapes = new HashMap<>();
    ArrayList<IAnimation> animations = new ArrayList<>();
    // Arbitrarily set canvas
    AnimationCanvas canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(rMove);
    animations.add(cMove);
    
    view.showAnimation(shapes, animations, canvas);

    
    String expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R moved from (200.00, 200.00) to (300.00, 300.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (500.00, 100.00) to (500.00, 400.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Test Ellipse Example
    out = new StringBuffer();
    view = new TextView(out, 1);
    
    r = new Rectangle.Builder("R")
                     .referencePoint(new Point2D(200, 200))
                     .width(50)
                     .height(100)
                     .appearTime(1)
                     .removeTime(100)
                     .build();
    
    c = new Ellipse.Builder("C")
                   .referencePoint(new Point2D(440, 70))
                   .width(120)
                   .height(60)
                   .appearTime(6)
                   .removeTime(100)
                   .build();
    
    rMove = new MoveAnimation(
        r, AnimationType.MOVE, new Point2D(200, 200), new Point2D(300, 300),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        1, 100, 50, 50, 100, 100
        );
    
    cMove = new MoveAnimation(
        c, AnimationType.MOVE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        6, 100, 120, 120, 60, 60
        );            
    
    IAnimation rMove2 = new MoveAnimation(
        r, AnimationType.MOVE, new Point2D(300, 300), new Point2D(200, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        70, 100, 50, 50, 100, 100
        );

    
    shapes = new HashMap<>();
    animations = new ArrayList<>();
    // Arbitrarily set canvas
    canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rMove2);
    
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R moved from (200.00, 200.00) to (300.00, 300.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (500.00, 100.00) to (500.00, 400.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R moved from (300.00, 300.00) to (200.00, 200.00) from t=70000ms to t=100000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());

  }
  
  /**
   * Test an animation with a scale.
   */
  @Test
  public void testAnimationWithScale() {
    // Test Rectangle scale
    StringBuilder out = new StringBuilder();
    AnimationView view = new TextView(out, 1);
    
    ShapeAnimation r = new Rectangle.Builder("R")
        .referencePoint(new Point2D(200, 200))
        .width(50)
        .height(100)
        .appearTime(1)
        .removeTime(100)
        .build();

    ShapeAnimation c = new Ellipse.Builder("C")
          .referencePoint(new Point2D(440, 70))
          .width(120)
          .height(60)
          .appearTime(6)
          .removeTime(100)
          .build();

    IAnimation rScale = new ScaleAnimation(
          r, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
          new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
          51, 70, 50, 25, 100, 100
          );
    
    Map<String, ShapeAnimation> shapes = new HashMap<>();
    ArrayList<IAnimation> animations = new ArrayList<>();
    // Arbitrarily set canvas
    AnimationCanvas canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(rScale);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Test Ellipse Example
    out = new StringBuilder();
    view = new TextView(out, 1);
    
    r = new Rectangle.Builder("R")
        .referencePoint(new Point2D(200, 200))
        .width(50)
        .height(100)
        .appearTime(1)
        .removeTime(100)
        .build();

    c = new Ellipse.Builder("C")
          .referencePoint(new Point2D(440, 70))
          .width(120)
          .height(60)
          .appearTime(6)
          .removeTime(100)
          .build();

    rScale = new ScaleAnimation(
        r, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    IAnimation cScale = new ScaleAnimation(
        c, AnimationType.SCALE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        20, 70, 120, 180, 60, 40
        );            
    
    shapes = new HashMap<>();
    animations = new ArrayList<>();
    // Arbitrarily set canvas
    canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(cScale);
    animations.add(rScale);
    
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
        "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());

  }
  
  /**
   * Test an animation with a color change.
   */
  @Test
  public void testAnimationWithColorChange() {
    // Test Rectangle scale
    CharArrayWriter out = new CharArrayWriter();
    AnimationView view = new TextView(out, 1);
    
    ShapeAnimation r = new Rectangle.Builder("R")
                                    .referencePoint(new Point2D(200, 200))
                                    .width(50)
                                    .height(100)
                                    .appearTime(1)
                                    .removeTime(100)
                                    .build();
    
    ShapeAnimation c = new Ellipse.Builder("C")
                                  .referencePoint(new Point2D(440, 70))
                                  .width(120)
                                  .height(60)
                                  .appearTime(6)
                                  .removeTime(100)
                                  .build();
    
    IAnimation rCc = new ChangeColorAnimation(
            r, AnimationType.COLOR_CHANGE, new Point2D(0, 0), new Point2D(100, 200),
            new ShapeColor(100, 200, 50), new ShapeColor(255, 255, 255),
            51, 70, 50, 25, 100, 100
            );
    
    Map<String, ShapeAnimation> shapes = new HashMap<>();
    ArrayList<IAnimation> animations = new ArrayList<>();
    // Arbitrarily set canvas
    AnimationCanvas canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(rCc);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Test Ellipse Example
    out = new CharArrayWriter();
    view = new TextView(out, 1);
    
    r = new Rectangle.Builder("R")
                     .referencePoint(new Point2D(200, 200))
                     .width(50)
                     .height(100)
                     .appearTime(1)
                     .removeTime(100)
                     .build();
    
    c = new Ellipse.Builder("C")
                   .referencePoint(new Point2D(440, 70))
                   .width(120)
                   .height(60)
                   .appearTime(6)
                   .removeTime(100)
                   .build();
    
    rCc = new ChangeColorAnimation(
        r, AnimationType.COLOR_CHANGE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(100, 200, 50), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    IAnimation cCc = new ChangeColorAnimation(
        c, AnimationType.COLOR_CHANGE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(50, 25, 50),
        20, 70, 120, 180, 60, 40
        );            
    
    shapes = new HashMap<>();
    animations = new ArrayList<>();
    // Arbitrarily set canvas
    canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(cCc);
    animations.add(rCc);
    
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
        "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());

  }
  
  /**
   * Test an animation with moves, scales and color changes.
   */
  @Test
  public void testAllAnimations() {
    StringBuilder out = new StringBuilder();
    AnimationView view = new TextView(out, 1);
    
    ShapeAnimation r = new Rectangle.Builder("R")
                                    .referencePoint(new Point2D(200, 200))
                                    .width(50)
                                    .height(100)
                                    .appearTime(1)
                                    .removeTime(100)
                                    .build();
    
    ShapeAnimation c = new Ellipse.Builder("C")
                                  .referencePoint(new Point2D(440, 70))
                                  .width(120)
                                  .height(60)
                                  .appearTime(6)
                                  .removeTime(100)
                                  .build();
    
    IAnimation rMove = new MoveAnimation(
            r, AnimationType.MOVE, new Point2D(0, 0), new Point2D(100, 200),
            new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
            1, 100, 10, 10, 30, 30
            );
    
    IAnimation cMove = new MoveAnimation(
        c, AnimationType.MOVE, new Point2D(50, 20), new Point2D(300, 100),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        6, 100, 10, 10, 30, 30
        );      
    
    IAnimation rScale = new ScaleAnimation(
        r, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    IAnimation cScale = new ScaleAnimation(
        c, AnimationType.SCALE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        20, 70, 120, 180, 60, 40
        );       
    
    IAnimation rCc = new ChangeColorAnimation(
        r, AnimationType.COLOR_CHANGE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(100, 200, 50), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    IAnimation cCc = new ChangeColorAnimation(
        c, AnimationType.COLOR_CHANGE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(50, 25, 50),
        20, 70, 120, 180, 60, 40
        );            

    Map<String, ShapeAnimation> shapes = new HashMap<>();
    ArrayList<IAnimation> animations = new ArrayList<>();
    // Arbitrarily set canvas
    AnimationCanvas canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
  }

  
  /**
   * Test the showAnimation method of the TextView class
   * to ensure shapes are animated in the order in which
   * they were received.
   */
  @Test
  public void testShapeAnimationOrder() {
    StringBuilder out = new StringBuilder();
    AnimationView view = new TextView(out, 1);
    
    ShapeAnimation r = new Rectangle.Builder("R")
                                    .referencePoint(new Point2D(200, 200))
                                    .width(50)
                                    .height(100)
                                    .appearTime(1)
                                    .removeTime(100)
                                    .build();
    
    ShapeAnimation c = new Ellipse.Builder("C")
                                  .referencePoint(new Point2D(440, 70))
                                  .width(120)
                                  .height(60)
                                  .appearTime(6)
                                  .removeTime(100)
                                  .build();
    
    Map<String, ShapeAnimation> shapes = new HashMap<>();
    ArrayList<IAnimation> animations = new ArrayList<>();
    // Arbitrarily set canvas
    AnimationCanvas canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
        
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Flip the Shapes
    out = new StringBuilder();
    view = new TextView(out, 1);
    
    r = new Rectangle.Builder("R")
                     .referencePoint(new Point2D(200, 200))
                     .width(50)
                     .height(100)
                     .appearTime(1)
                     .removeTime(100)
                     .build();
    
    c = new Ellipse.Builder("C")
                   .referencePoint(new Point2D(440, 70))
                   .width(120)
                   .height(60)
                   .appearTime(6)
                   .removeTime(100)
                   .build();
    
    shapes = new LinkedHashMap<>();
    animations = new ArrayList<>();
    // Arbitrarily set canvas
    canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(c.getName(), c);
    shapes.put(r.getName(), r);
        
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());

  }
  
  /**
   * Test the showAnimation method of the TextView class
   * to ensure animations are animated in the order in which
   * they were received.
   */
  @Test
  public void testAnimationOrder() {
    // Continuously move end of the list to the top (in circular way)
    StringBuilder out = new StringBuilder();
    AnimationView view = new TextView(out, 1);
    
    ShapeAnimation r = new Rectangle.Builder("R")
                                    .referencePoint(new Point2D(200, 200))
                                    .width(50)
                                    .height(100)
                                    .appearTime(1)
                                    .removeTime(100)
                                    .build();
    
    ShapeAnimation c = new Ellipse.Builder("C")
                                  .referencePoint(new Point2D(440, 70))
                                  .width(120)
                                  .height(60)
                                  .appearTime(6)
                                  .removeTime(100)
                                  .build();
    
    IAnimation rMove = new MoveAnimation(
            r, AnimationType.MOVE, new Point2D(0, 0), new Point2D(100, 200),
            new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
            1, 100, 10, 10, 30, 30
            );
    
    IAnimation cMove = new MoveAnimation(
        c, AnimationType.MOVE, new Point2D(50, 20), new Point2D(300, 100),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        6, 100, 10, 10, 30, 30
        );      
    
    IAnimation rScale = new ScaleAnimation(
        r, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    IAnimation cScale = new ScaleAnimation(
        c, AnimationType.SCALE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        20, 70, 120, 180, 60, 40
        );       
    
    IAnimation rCc = new ChangeColorAnimation(
        r, AnimationType.COLOR_CHANGE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(100, 200, 50), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    IAnimation cCc = new ChangeColorAnimation(
        c, AnimationType.COLOR_CHANGE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(50, 25, 50),
        20, 70, 120, 180, 60, 40
        );            

    Map<String, ShapeAnimation> shapes = new HashMap<>();
    ArrayList<IAnimation> animations = new ArrayList<>();
    // Arbitrarily set canvas
    AnimationCanvas canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Second Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(cCc);
    
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);

    view = new TextView(out , 1);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Third Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(cScale);
    animations.add(cCc);
    
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);

    view = new TextView(out , 1);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Fourth Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);

    
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);

    view = new TextView(out , 1);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Fifth Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);

    
    animations.add(rMove);
    animations.add(cMove);

    view = new TextView(out , 1);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Sixth Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);
    
    animations.add(rMove);
    
    view = new TextView(out , 1);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Final Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);
    
    view = new TextView(out , 1);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
  }
  
  /**
   * Test that the showAnimation method of the TextView class
   * properly handles speed and converts ticks to milliseconds
   * based on the user-provided speed.
   */
  @Test
  public void testSpeedConversion() {
    /*
     * Speed conversion notes: 
     * if t=1 and speed = ....
     * 
     * Speed of 1 tick per second  = 1000ms
     * Speed of 2 ticks per second = 500ms
     * speed of 5 ticks per second = 200ms 
     * 
     * TESTING NOTE: Not all animations are in order, 
     * The text view is not responsible for sorted ordering
     * it will print animations in the order received.
     */
    
    // Test increasing speeds (Starting at 1)
    StringBuilder out = new StringBuilder();
    AnimationView view = new TextView(out, 1);
    
    ShapeAnimation r = new Rectangle.Builder("R")
                                    .referencePoint(new Point2D(200, 200))
                                    .width(50)
                                    .height(100)
                                    .appearTime(1)
                                    .removeTime(100)
                                    .build();
    
    ShapeAnimation c = new Ellipse.Builder("C")
                                  .referencePoint(new Point2D(440, 70))
                                  .width(120)
                                  .height(60)
                                  .appearTime(6)
                                  .removeTime(100)
                                  .build();
    
    IAnimation rMove = new MoveAnimation(
            r, AnimationType.MOVE, new Point2D(0, 0), new Point2D(100, 200),
            new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
            1, 100, 10, 10, 30, 30
            );
    
    IAnimation cMove = new MoveAnimation(
        c, AnimationType.MOVE, new Point2D(50, 20), new Point2D(300, 100),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        6, 100, 10, 10, 30, 30
        );      
    
    IAnimation rScale = new ScaleAnimation(
        r, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    IAnimation cScale = new ScaleAnimation(
        c, AnimationType.SCALE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        20, 70, 120, 180, 60, 40
        );       
    
    IAnimation rCc = new ChangeColorAnimation(
        r, AnimationType.COLOR_CHANGE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(100, 200, 50), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    IAnimation cCc = new ChangeColorAnimation(
        c, AnimationType.COLOR_CHANGE, new Point2D(440, 70), new Point2D(440, 370),
        new ShapeColor(255, 255, 255), new ShapeColor(50, 25, 50),
        20, 70, 120, 180, 60, 40
        );            

    Map<String, ShapeAnimation> shapes = new HashMap<>();
    ArrayList<IAnimation> animations = new ArrayList<>();
    // Arbitrarily set canvas
    AnimationCanvas canvas = new AnimationCanvas(500, 500, new Point2D(0, 0));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Second Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(cCc);
    
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);

    view = new TextView(out , 2);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 500ms\n"
        + "Disappear At: 50000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 3000ms\n"
        + "Disappear At: 50000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=10000ms to t=35000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=500ms to t=50000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=3000ms to t=50000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=25500ms to t=35000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=25500ms to t=35000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=10000ms to t=35000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Third Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(cScale);
    animations.add(cCc);
    
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);

    view = new TextView(out , 10);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 100ms\n"
        + "Disappear At: 10000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 600ms\n"
        + "Disappear At: 10000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=2000ms to t=7000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=2000ms to t=7000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=100ms to t=10000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=600ms to t=10000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=5100ms to t=7000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=5100ms to t=7000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Fourth Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);

    
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);

    view = new TextView(out , 20);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 50ms\n"
        + "Disappear At: 5000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 300ms\n"
        + "Disappear At: 5000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=2550ms to t=3500ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=1000ms to t=3500ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=1000ms to t=3500ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=50ms to t=5000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=300ms to t=5000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=2550ms to t=3500ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Fifth Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);

    
    animations.add(rMove);
    animations.add(cMove);

    view = new TextView(out , 1000);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1ms\n"
        + "Disappear At: 100ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6ms\n"
        + "Disappear At: 100ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51ms to t=70ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51ms to t=70ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20ms to t=70ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20ms to t=70ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1ms to t=100ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6ms to t=100ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Sixth Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);
    
    animations.add(rMove);
    
    view = new TextView(out , 2000);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1ms\n"
        + "Disappear At: 100ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6ms\n"
        + "Disappear At: 100ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6ms to t=100ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51ms to t=70ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51ms to t=70ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20ms to t=70ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20ms to t=70ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1ms to t=100ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
    // Final Iteration
    out = new StringBuilder();
    animations = new ArrayList<>();

    // View will receive list in sorted order
    animations.add(rMove);
    animations.add(cMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cScale);
    animations.add(cCc);
    
    view = new TextView(out , -1);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
          "******************************\n"
        + "** TEXTUAL SHAPE ANIMATION: **\n"
        + "******************************\n\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6000ms\n"
        + "Disappear At: 100000ms\n"
        + "\n"
        + "R moved from (0.00, 0.00) to (100.00, 200.00) from t=1000ms to t=100000ms\n"
        + "\n"
        + "C moved from (55.00, 35.00) to (305.00, 115.00) from t=6000ms to t=100000ms\n"
        + "\n"
        + "R scaled from width: 50.0 and height: 100.0 to "
        +   "width: 25.0 and height: 100.0 from t=51000ms to t=70000ms\n"
        + "\n"
        + "R changed color from: (100.00, 200.00, 50.00) "
        + "to: (255.00, 255.00, 255.00) from t=51000ms to t=70000ms\n"
        + "\n"
        + "C scaled from X Radius: 60.0 and Y Radius: 30.0 to "
        +   "X Radius: 90.0 and Y Radius: 20.0 from t=20000ms to t=70000ms\n"
        + "\n"
        + "C changed color from: (255.00, 255.00, 255.00) "
        + "to: (50.00, 25.00, 50.00) from t=20000ms to t=70000ms\n"
        + "\n"
        + "******************************\n"
        + "**       END ANIMATION:     **\n"
        + "******************************";
    
    assertEquals(expected, out.toString());
    
  }
}
