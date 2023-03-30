package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs5004.animator.model.AnimationCanvas;
import cs5004.animator.model.AnimatorModelImpl;
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
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeSet;


/**
 * A JUnit Testing Suite for the AnimatorModelImpl class.
 */
public class AnimatorModelImplTest {

  /**
   * Test the Constructor of the AnimatorModelImpl class.
   */
  @Test
  public void testConstructor() {
    AnimatorModelImpl test = new AnimatorModelImpl();

    // Check that a Shapes Map was created
    assertTrue(test.getShapes() instanceof LinkedHashMap);
    assertTrue(test.getShapes().isEmpty());
    
    // Check that a Animations Map was created
    assertTrue(test.getAnimations() instanceof HashMap);
    assertTrue(test.getAnimations().isEmpty());
    
    // Check that a Shapes Map was created
    assertTrue(test.getTimes() instanceof TreeSet);
    assertTrue(test.getTimes().isEmpty());
    
    // Check that a Canvas was created to default values\
    assertTrue(test.getCanvas() instanceof AnimationCanvas);
    assertEquals(test.getCanvas().getWidth(), 700);
    assertEquals(test.getCanvas().getHeight(), 700);
    assertEquals(test.getCanvas().getTopLeft().toString(), "(0.00, 0.00)");
  }
  
  /**
   * Test the addShape method of the AnimatorModelImpl class.
   */
  @Test
  public void testAddShape() {
    AnimatorModelImpl test = new AnimatorModelImpl();
    
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
    
    // Confirm initialized to empty
    assertTrue(test.getShapes().isEmpty());
    
    // Add R
    try {
      test.addShape(r);      
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
    assertFalse(test.getShapes().isEmpty());
    assertEquals(test.getShapes().get(r.getName()), r);
    String expected =         
          "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1\n"
        + "Disappear At: 100\n"
        + "\n";
    assertEquals(expected, test.toString());
    assertEquals("[R]", test.getShapes().keySet().toString());
    
    // Add C
    try {
      test.addShape(c);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
    assertFalse(test.getShapes().isEmpty());
    assertEquals(test.getShapes().get(c.getName()), c);
    expected =        
          "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1\n"
        + "Disappear At: 100\n"
        + "\n"

        +  "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6\n"
        + "Disappear At: 100\n"
        + "\n";
    assertEquals(expected, test.toString());
    assertEquals("[R, C]", test.getShapes().keySet().toString());
    
    // Try Adding Shapes that already exist
    try {
      test.addShape(r);
      fail("Exception should have been thrown when adding shape to model");
    } catch (IllegalArgumentException e) {
      // DO Nothing -- PASS
    }
    
    try {
      test.addShape(c);
      fail("Exception should have been thrown when adding shape to model");
    } catch (IllegalArgumentException e) {
      // DO Nothing -- PASS
    }
  }
  
  /**
   * Test that LinkedHashMap is properly storing shapes in the order they are added.
   */
  @Test
  public void testShapeOrder() {
    AnimatorModelImpl test = new AnimatorModelImpl();
    
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
    
    ShapeAnimation r2 = new Rectangle.Builder("R2")
                                    .referencePoint(new Point2D(200, 200))
                                    .width(50)
                                    .height(100)
                                    .appearTime(1)
                                    .removeTime(100)
                                    .build();
    
    // Permutation 1
    test.addShape(r);
    test.addShape(c);
    test.addShape(r2);
    assertEquals("[R, C, R2]", test.getShapes().keySet().toString());
    
    // Permutation 2
    test.getShapes().clear();
    test.addShape(r);
    test.addShape(r2);
    test.addShape(c);
    assertEquals("[R, R2, C]", test.getShapes().keySet().toString());
    
    // Permutation 3
    test.getShapes().clear();
    test.addShape(r2);
    test.addShape(r);
    test.addShape(c);
    assertEquals("[R2, R, C]", test.getShapes().keySet().toString());
    
    // Permutation 4
    test.getShapes().clear();
    test.addShape(r2);
    test.addShape(c);
    test.addShape(r);
    assertEquals("[R2, C, R]", test.getShapes().keySet().toString());

    // Permutation 5
    test.getShapes().clear();
    test.addShape(c);
    test.addShape(r2);
    test.addShape(r);
    assertEquals("[C, R2, R]", test.getShapes().keySet().toString());

    // Permutation 6
    test.getShapes().clear();
    test.addShape(c);
    test.addShape(r);
    test.addShape(r2);
    assertEquals("[C, R, R2]", test.getShapes().keySet().toString());
  }
  
  /**
   * Test the addAnimation method of the AnimatorModelImpl class.
   */
  @Test
  public void testAddAnimation() {
    /* NOTE -- This class will not throw an exception for
     * trying to add an animation that does not have a corresponding
     * shape, as you are not able to create and IAnimation without 
     * first having a shape. If the shape that exists in the IAnimation
     * does not exist in the shape list, the IAnimation shape will be
     * added
     */
   
    AnimatorModelImpl test = new AnimatorModelImpl();
    
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
    
    
    
    // Add R and C
    try {
      test.addShape(r);
      test.addShape(c);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
    
    // Create and Add Animations
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
    
    
    test.addAnimation(rMove);
    assertEquals(test.getAnimations().get(rMove.getStartTime()).get(0), rMove);
    
    test.addAnimation(cMove);
    assertEquals(test.getAnimations().get(cMove.getStartTime()).get(0), cMove);
    
    String expected = 
              "Name: R\n"
            + "Type: rectangle\n"
            + "Top Left Corner: (200.00, 200.00)\n"
            + "Height: 100.0\n"
            + "Width: 50.0\n"
            + "Color: (0.00, 0.00, 0.00)\n"
            + "Appear At: 1\n"
            + "Disappear At: 100\n"
            + "\n"
            + "Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.00, 100.00)\n"
            + "X Radius: 60.0\n"
            + "Y Radius: 30.0\n"
            + "Color: (0.00, 0.00, 0.00)\n"
            + "Appear At: 6\n"
            + "Disappear At: 100\n"
            + "\n"
            + "R moved from (200.00, 200.00) to (300.00, 300.00) from t=1 to t=100\n"
            + "C moved from (500.00, 100.00) to (500.00, 400.00) from t=6 to t=100\n";
    
    assertEquals(expected, test.toString());

    
    IAnimation rScale = new ScaleAnimation(
        r, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    test.addAnimation(rScale);
    assertEquals(test.getAnimations().get(rScale.getStartTime()).get(0), rScale);
    
    expected = 
              "Name: R\n"
            + "Type: rectangle\n"
            + "Top Left Corner: (200.00, 200.00)\n"
            + "Height: 100.0\n"
            + "Width: 50.0\n"
            + "Color: (0.00, 0.00, 0.00)\n"
            + "Appear At: 1\n"
            + "Disappear At: 100\n"
            + "\n"
            + "Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.00, 100.00)\n"
            + "X Radius: 60.0\n"
            + "Y Radius: 30.0\n"
            + "Color: (0.00, 0.00, 0.00)\n"
            + "Appear At: 6\n"
            + "Disappear At: 100\n"
            + "\n"
            + "R moved from (200.00, 200.00) to (300.00, 300.00) from t=1 to t=100\n"
            + "C moved from (500.00, 100.00) to (500.00, 400.00) from t=6 to t=100\n"
            + "R scaled from width: 50.0 and height: 100.0 to "
            +   "width: 25.0 and height: 100.0 from t=51 to t=70\n";
    
    // Test that adding an animation with a shape that is not in the list
    //will add the shape to the list
    ShapeAnimation r2 = new Rectangle.Builder("R2")
                                      .referencePoint(new Point2D(200, 200))
                                      .width(50)
                                      .height(100)
                                      .appearTime(1)
                                      .removeTime(100)
                                      .build();
    
    IAnimation r2CC = new ChangeColorAnimation(
        r2, AnimationType.COLOR_CHANGE, new Point2D(200, 200), new Point2D(200, 200),
        new ShapeColor(100, 200, 50), new ShapeColor(255, 255, 255),
        30, 70, 50, 25, 100, 100
        );
    
    // R2 was never added to shapes
    test.addAnimation(r2CC);
    assertEquals(test.getAnimations().get(r2CC.getStartTime()).get(0), r2CC);
    assertEquals("[R, C, R2]", test.getShapes().keySet().toString());

    expected = 
            "Name: R\n"
          + "Type: rectangle\n"
          + "Top Left Corner: (200.00, 200.00)\n"
          + "Height: 100.0\n"
          + "Width: 50.0\n"
          + "Color: (0.00, 0.00, 0.00)\n"
          + "Appear At: 1\n"
          + "Disappear At: 100\n"
          + "\n"
          + "Name: C\n"
          + "Type: ellipse\n"
          + "Center: (500.00, 100.00)\n"
          + "X Radius: 60.0\n"
          + "Y Radius: 30.0\n"
          + "Color: (0.00, 0.00, 0.00)\n"
          + "Appear At: 6\n"
          + "Disappear At: 100\n"
          + "\n"
          + "Name: R2\n"
          + "Type: rectangle\n"
          + "Top Left Corner: (200.00, 200.00)\n"
          + "Height: 100.0\n"
          + "Width: 50.0\n"
          + "Color: (0.00, 0.00, 0.00)\n"
          + "Appear At: 1\n"
          + "Disappear At: 100\n"
          + "\n"
          + "R moved from (200.00, 200.00) to (300.00, 300.00) from t=1 to t=100\n"
          + "C moved from (500.00, 100.00) to (500.00, 400.00) from t=6 to t=100\n"
          + "R2 changed color from: (100.00, 200.00, 50.00) "
          + "to: (255.00, 255.00, 255.00) from t=30 to t=70\n"
          + "R scaled from width: 50.0 and height: 100.0 to "
          +   "width: 25.0 and height: 100.0 from t=51 to t=70\n";
    
    assertEquals(expected, test.toString());

    // Test that shapes with the same start time will be added to back of existing array list
    IAnimation rScale2 = new ScaleAnimation(
        r, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    test.addAnimation(rScale2);
    assertEquals(test.getAnimations().get(rScale2.getStartTime()).get(1), rScale2); // index 1
  }
  
  /**
   * Test the addCanvas method of the AnimatorModelImpl class.
   */
  @Test
  public void testAddCanvas() {
    AnimatorModelImpl test = new AnimatorModelImpl();
    
    // Check that a Canvas was created to default values\
    assertTrue(test.getCanvas() instanceof AnimationCanvas);
    assertEquals(test.getCanvas().getWidth(), 700);
    assertEquals(test.getCanvas().getHeight(), 700);
    assertEquals(test.getCanvas().getTopLeft().toString(), "(0.00, 0.00)");
    
    AnimationCanvas testCanvas = new AnimationCanvas(400, 500, new Point2D(200, 100));
    test.addCanvas(testCanvas);
    assertTrue(test.getCanvas() instanceof AnimationCanvas);
    assertEquals(test.getCanvas().getWidth(), 400);
    assertEquals(test.getCanvas().getHeight(), 500);
    assertEquals(test.getCanvas().getTopLeft().toString(), "(200.00, 100.00)");
  }
  
  /**
   * Test the getCanvas method of the AnimatorModelImpl class.
   */
  @Test
  public void testGetCanvas() {
    AnimatorModelImpl test = new AnimatorModelImpl();

    // Check that the getters retrieve the default canvas
    assertEquals(test.getCanvas().getWidth(), 700);
    assertEquals(test.getCanvas().getHeight(), 700);
    assertEquals(test.getCanvas().getTopLeft().toString(), "(0.00, 0.00)");
    
    // Add a canvas and check the getter
    AnimationCanvas testCanvas = new AnimationCanvas(400, 500, new Point2D(200, 100));
    test.addCanvas(testCanvas);
    assertTrue(testCanvas == test.getCanvas());
    assertEquals(test.getCanvas().getWidth(), 400);
    assertEquals(test.getCanvas().getHeight(), 500);
    assertEquals(test.getCanvas().getTopLeft().toString(), "(200.00, 100.00)");    
  }
  
  /**
   * Test the getShapes method of the AnimatorModelImpl class.
   */
  @Test
  public void testGetShapes() {
    AnimatorModelImpl test = new AnimatorModelImpl();
    
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
    
    ShapeAnimation r2 = new Rectangle.Builder("R2")
                                  .referencePoint(new Point2D(200, 200))
                                  .width(50)
                                  .height(100)
                                  .appearTime(1)
                                  .removeTime(100)
                                  .build();
    
    // Add R and Get Shapes 
    try {
      test.addShape(r);      
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
    assertEquals(test.getShapes().get(r.getName()), r);
    assertEquals("[R]", test.getShapes().keySet().toString());
    
    // Add C and Get Shapes 
    try {
      test.addShape(c);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
    assertEquals(test.getShapes().get(c.getName()), c);
    assertEquals("[R, C]", test.getShapes().keySet().toString());
    
    // Add R2 and Get Shapes 
    try {
      test.addShape(r2);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
    assertEquals(test.getShapes().get(r2.getName()), r2);
    assertEquals("[R, C, R2]", test.getShapes().keySet().toString());
  }
  
  /**
   * Test the getAnimations method of the AnimatorModelImpl class.
   */
  @Test
  public void testGetAnimations() {
    AnimatorModelImpl test = new AnimatorModelImpl();
    
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
    
    // Create and Add Animations
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
        c, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );


    
    // Add R and Get Shapes 
    try {
      test.addShape(r);      
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
       
    // Add C and Get Shapes 
    try {
      test.addShape(c);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
   
    test.addAnimation(rMove);
    assertEquals(test.getAnimations().get(rMove.getStartTime()).get(0), rMove);
    
    test.addAnimation(cMove);
    assertEquals(test.getAnimations().get(cMove.getStartTime()).get(0), cMove);
    
    test.addAnimation(rScale);
    assertEquals(test.getAnimations().get(rScale.getStartTime()).get(0), rScale);
    
    // Should return same set
    test.addAnimation(cScale);
    assertEquals(test.getAnimations().get(cScale.getStartTime()).get(1), cScale);
  }
  
  /**
   * Test the getTimes method of the AnimatorModelImpl class.
   */
  @Test
  public void testGetTimes() {
    AnimatorModelImpl test = new AnimatorModelImpl();
    
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
    
    // Create and Add Animations
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
        c, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );


    
    // Add R and Get Shapes 
    try {
      test.addShape(r);      
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
       
    // Add C and Get Shapes 
    try {
      test.addShape(c);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
   
    test.addAnimation(rMove);
    assertEquals("[1]", test.getTimes().toString());
    
    test.addAnimation(cMove);
    assertEquals("[1, 6]", test.getTimes().toString());
    
    test.addAnimation(rScale);
    assertEquals("[1, 6, 51]", test.getTimes().toString());
    
    // Should return same set
    test.addAnimation(cScale);
    assertEquals("[1, 6, 51]", test.getTimes().toString());
  }
  
  /**
   * Test the toString method of the AnimatorModelImpl class.
   */
  @Test
  public void testToString() {
    AnimatorModelImpl test = new AnimatorModelImpl();
    
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
    
    
    
    // ** Test that the order of shapes matters ** //
    
    // Add R and C
    try {
      test.addShape(r);
      test.addShape(c);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
    
    String expected = 
          "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1\n"
        + "Disappear At: 100\n"
        + "\n"
        + "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6\n"
        + "Disappear At: 100\n"
        + "\n";
    
    assertEquals(expected, test.toString());
    
    
    test.getShapes().clear();
    // Add R and C in reverse order
    try {
      test.addShape(c);
      test.addShape(r);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
    
    expected = 
          "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (500.00, 100.00)\n"
        + "X Radius: 60.0\n"
        + "Y Radius: 30.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 6\n"
        + "Disappear At: 100\n"
        + "\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (200.00, 200.00)\n"
        + "Height: 100.0\n"
        + "Width: 50.0\n"
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 1\n"
        + "Disappear At: 100\n"
        + "\n";
  
    assertEquals(expected, test.toString());

    
    // ** Test with shapes and Animations ** //
    test.getShapes().clear();
    try {
      test.addShape(r);
      test.addShape(c);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown when adding shape.");
    }
    
    // Create and Add Animations
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
    
    test.addAnimation(rMove);
    test.addAnimation(cMove);
    
    expected = 
              "Name: R\n"
            + "Type: rectangle\n"
            + "Top Left Corner: (200.00, 200.00)\n"
            + "Height: 100.0\n"
            + "Width: 50.0\n"
            + "Color: (0.00, 0.00, 0.00)\n"
            + "Appear At: 1\n"
            + "Disappear At: 100\n"
            + "\n"
            + "Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.00, 100.00)\n"
            + "X Radius: 60.0\n"
            + "Y Radius: 30.0\n"
            + "Color: (0.00, 0.00, 0.00)\n"
            + "Appear At: 6\n"
            + "Disappear At: 100\n"
            + "\n"
            + "R moved from (200.00, 200.00) to (300.00, 300.00) from t=1 to t=100\n"
            + "C moved from (500.00, 100.00) to (500.00, 400.00) from t=6 to t=100\n";
    
    assertEquals(expected, test.toString());

    // Check that the toString will arrange animations in correct order even if added out of order
    test.getAnimations().clear();
    test.addAnimation(cMove);
    test.addAnimation(rMove);
    
    assertEquals(expected, test.toString());

    // Add in a different type of animation\
    IAnimation rScale = new ScaleAnimation(
        r, AnimationType.SCALE, new Point2D(0, 0), new Point2D(100, 200),
        new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
        51, 70, 50, 25, 100, 100
        );
    
    test.addAnimation(rScale);
    
    expected = 
              "Name: R\n"
            + "Type: rectangle\n"
            + "Top Left Corner: (200.00, 200.00)\n"
            + "Height: 100.0\n"
            + "Width: 50.0\n"
            + "Color: (0.00, 0.00, 0.00)\n"
            + "Appear At: 1\n"
            + "Disappear At: 100\n"
            + "\n"
            + "Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.00, 100.00)\n"
            + "X Radius: 60.0\n"
            + "Y Radius: 30.0\n"
            + "Color: (0.00, 0.00, 0.00)\n"
            + "Appear At: 6\n"
            + "Disappear At: 100\n"
            + "\n"
            + "R moved from (200.00, 200.00) to (300.00, 300.00) from t=1 to t=100\n"
            + "C moved from (500.00, 100.00) to (500.00, 400.00) from t=6 to t=100\n"
            + "R scaled from width: 50.0 and height: 100.0 to "
            +   "width: 25.0 and height: 100.0 from t=51 to t=70\n";
    
    
    // Test both shape order and animation order by adding a third shape and animation in the middle
    ShapeAnimation r2 = new Rectangle.Builder("R2")
                                      .referencePoint(new Point2D(200, 200))
                                      .width(50)
                                      .height(100)
                                      .appearTime(1)
                                      .removeTime(100)
                                      .build();
    
    IAnimation r2CC = new ChangeColorAnimation(
        r2, AnimationType.COLOR_CHANGE, new Point2D(200, 200), new Point2D(200, 200),
        new ShapeColor(100, 200, 50), new ShapeColor(255, 255, 255),
        30, 70, 50, 25, 100, 100
        );
    
    // R2 was never added to shapes
    test.addAnimation(r2CC);

    expected = 
            "Name: R\n"
          + "Type: rectangle\n"
          + "Top Left Corner: (200.00, 200.00)\n"
          + "Height: 100.0\n"
          + "Width: 50.0\n"
          + "Color: (0.00, 0.00, 0.00)\n"
          + "Appear At: 1\n"
          + "Disappear At: 100\n"
          + "\n"
          + "Name: C\n"
          + "Type: ellipse\n"
          + "Center: (500.00, 100.00)\n"
          + "X Radius: 60.0\n"
          + "Y Radius: 30.0\n"
          + "Color: (0.00, 0.00, 0.00)\n"
          + "Appear At: 6\n"
          + "Disappear At: 100\n"
          + "\n"
          + "Name: R2\n"
          + "Type: rectangle\n"
          + "Top Left Corner: (200.00, 200.00)\n"
          + "Height: 100.0\n"
          + "Width: 50.0\n"
          + "Color: (0.00, 0.00, 0.00)\n"
          + "Appear At: 1\n"
          + "Disappear At: 100\n"
          + "\n"
          + "R moved from (200.00, 200.00) to (300.00, 300.00) from t=1 to t=100\n"
          + "C moved from (500.00, 100.00) to (500.00, 400.00) from t=6 to t=100\n"
          + "R2 changed color from: (100.00, 200.00, 50.00) "
          + "to: (255.00, 255.00, 255.00) from t=30 to t=70\n"
          + "R scaled from width: 50.0 and height: 100.0 to "
          +   "width: 25.0 and height: 100.0 from t=51 to t=70\n";
    
    assertEquals(expected, test.toString());
  }
}
