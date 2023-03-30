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
import cs5004.animator.view.SVGView;
import org.junit.Test;

import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A JUnit Testing Suite for the SVGView class.
 */
public class SVGViewTest {
  
  /**
   * Test the showAnimation method of the SVGView class
   * where only shapes were declared and no animations
   * were specified.
   */
  @Test
  public void testOnlyShapesNoAnimation() {
    StringBuilder out = new StringBuilder();
    AnimationView view = new SVGView(out, 1);
    
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
        
          "<svg width=\"500\" "
        + "height=\"500\" "
        + "version=\"1.1\"\n"
        + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
        
        + "<rect id=\"R\" "
        + "x=\"200.0\" "
        + "y=\"200.0\" "
        + "width=\"50.0\" "
        + "height=\"100.0\" " 
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"        
        + "</rect>\n\n"
        
        + "<ellipse id=\"C\" "
        + "cx=\"500.0\" "
        + "cy=\"100.0\" "
        + "rx=\"60.0\" "   
        + "ry=\"30.0\" "  
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "</ellipse>\n\n"
        
        + "\n</svg>\n";

    assertEquals(expected, out.toString());
  }
  
  /**
   * Test an animation with a move.
   */
  @Test
  public void testAnimationWithMove() {
    StringBuffer out = new StringBuffer();
    AnimationView view = new SVGView(out, 1);
    
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
        r, AnimationType.MOVE, new Point2D(200, 200), new Point2D(100, 200),
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
        
          "<svg width=\"500\" "
        + "height=\"500\" "
        + "version=\"1.1\"\n"
        + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
        
        + "<rect id=\"R\" "
        + "x=\"200.0\" "
        + "y=\"200.0\" "
        + "width=\"50.0\" "
        + "height=\"100.0\" " 
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"99000ms\" "
        + "attributeName=\"x\" "
        + "from=\"200.0\" "            
        + "to=\"100.0\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"99000ms\" "
        + "attributeName=\"y\" "
        + "from=\"200.0\" "            
        + "to=\"200.0\" "
        + "fill=\"freeze\" />"
        + "\n"        
        + "</rect>\n\n"
        
        + "<ellipse id=\"C\" "
        + "cx=\"500.0\" "
        + "cy=\"100.0\" "
        + "rx=\"60.0\" "   
        + "ry=\"30.0\" "  
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"94000ms\" "
        + "attributeName=\"cx\" "
        + "from=\"500.0\" "            
        + "to=\"500.0\" "
        + "fill=\"freeze\" />"
        + "\n"        
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"94000ms\" "
        + "attributeName=\"cy\" "
        + "from=\"100.0\" "            
        + "to=\"400.0\" "
        + "fill=\"freeze\" />"
        + "\n"        
        + "</ellipse>\n\n"
        
        + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
    
    // Test Ellipse Example
    out = new StringBuffer();
    view = new SVGView(out, 1);
    
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
        
        "<svg width=\"500\" "
        + "height=\"500\" "
        + "version=\"1.1\"\n"
        + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
        
        + "<rect id=\"R\" "
        + "x=\"200.0\" "
        + "y=\"200.0\" "
        + "width=\"50.0\" "
        + "height=\"100.0\" " 
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"99000ms\" "
        + "attributeName=\"x\" "
        + "from=\"200.0\" "            
        + "to=\"300.0\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"99000ms\" "
        + "attributeName=\"y\" "
        + "from=\"200.0\" "            
        + "to=\"300.0\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"70000ms\" "
        + "dur=\"30000ms\" "
        + "attributeName=\"x\" "
        + "from=\"300.0\" "            
        + "to=\"200.0\" "
        + "fill=\"freeze\" />"
        + "\n"       
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"70000ms\" "
        + "dur=\"30000ms\" "
        + "attributeName=\"y\" "
        + "from=\"300.0\" "            
        + "to=\"200.0\" "
        + "fill=\"freeze\" />"
        + "\n"        
        + "</rect>\n\n"
        
        + "<ellipse id=\"C\" "
        + "cx=\"500.0\" "
        + "cy=\"100.0\" "
        + "rx=\"60.0\" "   
        + "ry=\"30.0\" "  
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"94000ms\" "
        + "attributeName=\"cx\" "
        + "from=\"500.0\" "            
        + "to=\"500.0\" "
        + "fill=\"freeze\" />"
        + "\n"        
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"94000ms\" "
        + "attributeName=\"cy\" "
        + "from=\"100.0\" "            
        + "to=\"400.0\" "
        + "fill=\"freeze\" />"
        + "\n"        
        + "</ellipse>\n\n"
        
        + "\n</svg>\n";
    
    assertEquals(expected, out.toString());

  }
  
  /**
   * Test an animation with a scale.
   */
  @Test
  public void testAnimationWithScale() {
    // Test Rectangle scale
    StringBuilder out = new StringBuilder();
    AnimationView view = new SVGView(out, 1);
    
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
        
        "<svg width=\"500\" "
        + "height=\"500\" "
        + "version=\"1.1\"\n"
        + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
        
        + "<rect id=\"R\" "
        + "x=\"200.0\" "
        + "y=\"200.0\" "
        + "width=\"50.0\" "
        + "height=\"100.0\" " 
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"51000ms\" "
        + "dur=\"19000ms\" "
        + "attributeName=\"width\" "
        + "from=\"50.0\" "            
        + "to=\"25.0\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"51000ms\" "
        + "dur=\"19000ms\" "
        + "attributeName=\"height\" "
        + "from=\"100.0\" "            
        + "to=\"100.0\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "</rect>\n\n"
        
        + "<ellipse id=\"C\" "
        + "cx=\"500.0\" "
        + "cy=\"100.0\" "
        + "rx=\"60.0\" "   
        + "ry=\"30.0\" "  
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "</ellipse>\n\n"
        
        + "\n</svg>\n";
    
    assertEquals(expected, out.toString());

   
    // Test Ellipse Example
    out = new StringBuilder();
    view = new SVGView(out, 1);
    
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
        
        "<svg width=\"500\" "
        + "height=\"500\" "
        + "version=\"1.1\"\n"
        + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
        
        + "<rect id=\"R\" "
        + "x=\"200.0\" "
        + "y=\"200.0\" "
        + "width=\"50.0\" "
        + "height=\"100.0\" " 
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"51000ms\" "
        + "dur=\"19000ms\" "
        + "attributeName=\"width\" "
        + "from=\"50.0\" "            
        + "to=\"25.0\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"51000ms\" "
        + "dur=\"19000ms\" "
        + "attributeName=\"height\" "
        + "from=\"100.0\" "            
        + "to=\"100.0\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "</rect>\n\n"
        
        + "<ellipse id=\"C\" "
        + "cx=\"500.0\" "
        + "cy=\"100.0\" "
        + "rx=\"60.0\" "   
        + "ry=\"30.0\" "  
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"20000ms\" "
        + "dur=\"50000ms\" "
        + "attributeName=\"rx\" "
        + "from=\"60.0\" "            
        + "to=\"90.0\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"20000ms\" "
        + "dur=\"50000ms\" "
        + "attributeName=\"ry\" "
        + "from=\"30.0\" "            
        + "to=\"20.0\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "</ellipse>\n\n"
        
        + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
    
  }
  
  /**
   * Test an animation with a color change.
   */
  @Test
  public void testAnimationWithColorChange() {
    // Test Rectangle scale
    CharArrayWriter out = new CharArrayWriter();
    AnimationView view = new SVGView(out, 1);
    
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
        
        "<svg width=\"500\" "
        + "height=\"500\" "
        + "version=\"1.1\"\n"
        + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
        
        + "<rect id=\"R\" "
        + "x=\"200.0\" "
        + "y=\"200.0\" "
        + "width=\"50.0\" "
        + "height=\"100.0\" " 
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"1000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"51000ms\" "
        + "dur=\"19000ms\" "
        + "attributeName=\"fill\" "
        + "from=\"rgb(100.0,200.0,50.0)\" "            
        + "to=\"rgb(255.0,255.0,255.0)\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "</rect>\n\n"
        
        + "<ellipse id=\"C\" "
        + "cx=\"500.0\" "
        + "cy=\"100.0\" "
        + "rx=\"60.0\" "   
        + "ry=\"30.0\" "  
        + "fill=\"rgb(0.0,0.0,0.0)\" "
        + "visibility=\"hidden\" >\n"
        + "    <animate "
        + "attributeType=\"xml\" "
        + "begin=\"6000ms\" "
        + "dur=\"1ms\" "
        + "attributeName=\"visibility\" "
        + "from=\"hidden\" "            
        + "to=\"visible\" "
        + "fill=\"freeze\" />"
        + "\n"
        + "</ellipse>\n\n"
        
        + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
        
    // Test Ellipse Example
    out = new CharArrayWriter();
    view = new SVGView(out, 1);
    
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
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());

  }
  
  /**
   * Test an animation with moves, scales and color changes.
   */
  @Test
  public void testAllAnimations() {
    StringBuilder out = new StringBuilder();
    AnimationView view = new SVGView(out, 1);
    
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
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cMove);
    animations.add(cScale);
    animations.add(cCc);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
  }

  
  /**
   * Test the showAnimation method of the SVGView class
   * to ensure shapes are animated in the order in which
   * they were received.
   */
  @Test
  public void testShapeAnimationOrder() {
    StringBuilder out = new StringBuilder();
    AnimationView view = new SVGView(out, 1);
    
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
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cMove);
    animations.add(cScale);
    animations.add(cCc);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
    
    // Flip the Shapes
    out = new StringBuilder();
    view = new SVGView(out, 1);
        
    shapes = new LinkedHashMap<>();
    // Arbitrarily set canvas
    shapes.put(c.getName(), c);
    shapes.put(r.getName(), r);
        
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());

  }
  
 
  
  /**
   * Test that the showAnimation method of the SVGView class
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
    AnimationView view = new SVGView(out, 1);
    
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
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cMove);
    animations.add(cScale);
    animations.add(cCc);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
    
    
    // Second Iteration
    out = new StringBuilder();
    view = new SVGView(out , 2);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"500ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"500ms\" "
            + "dur=\"49500ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"500ms\" "
            + "dur=\"49500ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"25500ms\" "
            + "dur=\"9500ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"25500ms\" "
            + "dur=\"9500ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"25500ms\" "
            + "dur=\"9500ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"3000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"3000ms\" "
            + "dur=\"47000ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"3000ms\" "
            + "dur=\"47000ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"10000ms\" "
            + "dur=\"25000ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"10000ms\" "
            + "dur=\"25000ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"10000ms\" "
            + "dur=\"25000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());

    
    
    // Third Iteration
    out = new StringBuilder();
    view = new SVGView(out , 10);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"100ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"100ms\" "
            + "dur=\"9900ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"100ms\" "
            + "dur=\"9900ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"5100ms\" "
            + "dur=\"1900ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"5100ms\" "
            + "dur=\"1900ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"5100ms\" "
            + "dur=\"1900ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"600ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"600ms\" "
            + "dur=\"9400ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"600ms\" "
            + "dur=\"9400ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"2000ms\" "
            + "dur=\"5000ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"2000ms\" "
            + "dur=\"5000ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"2000ms\" "
            + "dur=\"5000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
    
    // Fourth Iteration
    out = new StringBuilder();
    view = new SVGView(out , 20);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"50ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"50ms\" "
            + "dur=\"4950ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"50ms\" "
            + "dur=\"4950ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"2550ms\" "
            + "dur=\"950ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"2550ms\" "
            + "dur=\"950ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"2550ms\" "
            + "dur=\"950ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"300ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"300ms\" "
            + "dur=\"4700ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"300ms\" "
            + "dur=\"4700ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"2500ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"2500ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"2500ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
    
    // Fifth Iteration
    out = new StringBuilder();
    view = new SVGView(out , 1000);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1ms\" "
            + "dur=\"99ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1ms\" "
            + "dur=\"99ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51ms\" "
            + "dur=\"19ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51ms\" "
            + "dur=\"19ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51ms\" "
            + "dur=\"19ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6ms\" "
            + "dur=\"94ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6ms\" "
            + "dur=\"94ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20ms\" "
            + "dur=\"50ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20ms\" "
            + "dur=\"50ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20ms\" "
            + "dur=\"50ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
    
    // Sixth Iteration
    out = new StringBuilder();
    view = new SVGView(out , 2000);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1ms\" "
            + "dur=\"99ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1ms\" "
            + "dur=\"99ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51ms\" "
            + "dur=\"19ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51ms\" "
            + "dur=\"19ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51ms\" "
            + "dur=\"19ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6ms\" "
            + "dur=\"94ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6ms\" "
            + "dur=\"94ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20ms\" "
            + "dur=\"50ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20ms\" "
            + "dur=\"50ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20ms\" "
            + "dur=\"50ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
    
    // Final Iteration
    out = new StringBuilder();
    view = new SVGView(out , -1);
    view.showAnimation(shapes, animations, canvas);
    
    expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"200.0\" "
            + "y=\"200.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"x\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"y\" "
            + "from=\"200.0\" "            
            + "to=\"300.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"500.0\" "
            + "cy=\"100.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cx\" "
            + "from=\"500.0\" "            
            + "to=\"500.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cy\" "
            + "from=\"100.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
  }
  
  /**
   * The the x and y shifting of shapes based on canvas that does not use (0,0)
   * as the top left corner.
   */
  @Test
  public void testXYShift() {
    StringBuilder out = new StringBuilder();
    AnimationView view = new SVGView(out, 1);
    
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
    AnimationCanvas canvas = new AnimationCanvas(500, 500, new Point2D(100, 50));
    shapes.put(r.getName(), r);
    shapes.put(c.getName(), c);
    
    // View will receive list in sorted order
    animations.add(rMove);
    animations.add(rScale);
    animations.add(rCc);
    animations.add(cMove);
    animations.add(cScale);
    animations.add(cCc);
    
    view.showAnimation(shapes, animations, canvas);
    
    String expected = 
        
        "<svg width=\"500\" "
            + "height=\"500\" "
            + "version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">;\n\n"
            
            + "<rect id=\"R\" "
            + "x=\"100.0\" "
            + "y=\"150.0\" "
            + "width=\"50.0\" "
            + "height=\"100.0\" " 
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"x\" "
            + "from=\"100.0\" "            
            + "to=\"200.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"1000ms\" "
            + "dur=\"99000ms\" "
            + "attributeName=\"y\" "
            + "from=\"150.0\" "            
            + "to=\"250.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"width\" "
            + "from=\"50.0\" "            
            + "to=\"25.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"height\" "
            + "from=\"100.0\" "            
            + "to=\"100.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"51000ms\" "
            + "dur=\"19000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(100.0,200.0,50.0)\" "            
            + "to=\"rgb(255.0,255.0,255.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</rect>\n\n"
            
            + "<ellipse id=\"C\" "
            + "cx=\"400.0\" "
            + "cy=\"50.0\" "
            + "rx=\"60.0\" "   
            + "ry=\"30.0\" "  
            + "fill=\"rgb(0.0,0.0,0.0)\" "
            + "visibility=\"hidden\" >\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"1ms\" "
            + "attributeName=\"visibility\" "
            + "from=\"hidden\" "            
            + "to=\"visible\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cx\" "
            + "from=\"400.0\" "            
            + "to=\"400.0\" "
            + "fill=\"freeze\" />"
            + "\n"        
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"6000ms\" "
            + "dur=\"94000ms\" "
            + "attributeName=\"cy\" "
            + "from=\"50.0\" "            
            + "to=\"350.0\" "
            + "fill=\"freeze\" />"
            + "\n" 
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"rx\" "
            + "from=\"60.0\" "            
            + "to=\"90.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"ry\" "
            + "from=\"30.0\" "            
            + "to=\"20.0\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "    <animate "
            + "attributeType=\"xml\" "
            + "begin=\"20000ms\" "
            + "dur=\"50000ms\" "
            + "attributeName=\"fill\" "
            + "from=\"rgb(255.0,255.0,255.0)\" "            
            + "to=\"rgb(50.0,25.0,50.0)\" "
            + "fill=\"freeze\" />"
            + "\n"
            + "</ellipse>\n\n"
            
            + "\n</svg>\n";
    
    assertEquals(expected, out.toString());
  }
}
