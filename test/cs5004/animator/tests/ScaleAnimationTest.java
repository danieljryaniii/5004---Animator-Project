package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs5004.animator.model.animations.AnimationType;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.animations.ScaleAnimation;
import cs5004.animator.model.shapes.Ellipse;
import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.Rectangle;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;
import org.junit.Test;

/**
 * A JUnit Testing Suite for the ScaleAnimation class.
 */
public class ScaleAnimationTest {


  /**
   * Test the Constructor of the ScaleAnimation Class on valid
   * input.
   */
  @Test
  public void testConstructor() {
    
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );
      
      assertEquals("test", test.getShape().getName());
      assertTrue(shape == test.getShape());
      assertEquals(AnimationType.SCALE, test.getType());
      assertEquals(0, test.getStartPosition().getX(), .001);
      assertEquals(0, test.getStartPosition().getY(), .001);
      assertEquals(100, test.getEndPosition().getX(), .001);
      assertEquals(100, test.getEndPosition().getY(), .001);
      assertEquals(255, test.getStartColor().getR(), .001);
      assertEquals(255, test.getStartColor().getG(), .001);
      assertEquals(255, test.getStartColor().getB(), .001);
      assertEquals(100, test.getEndColor().getR(), .001);
      assertEquals(200, test.getEndColor().getG(), .001);
      assertEquals(50, test.getEndColor().getB(), .001);
      assertEquals(1, test.getStartTime());
      assertEquals(100, test.getEndTime());
      assertEquals(10, test.getStartWidth(), .001);
      assertEquals(20, test.getEndWidth(), .001);
      assertEquals(30, test.getStartHeight(), .001);
      assertEquals(50, test.getEndHeight(), .001);
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
  }
  
  /**
   * Test the Constructor of the ScaleAnimation Class on invalid
   * input.
   */
  @Test
  public void testInvalidConstructor() {
    
    // Test Invalid Start Time
    for (int num = -5000; num < 0; num ++) {
      try {
        IAnimation test = new ScaleAnimation(
              new Rectangle.Builder("test").build(),
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              num, 100, 10, 20, 30, 50
            );
        fail("Exception should have been thrown on ScaleAnimation construction.");
      } catch (IllegalArgumentException iae) {
        // DO Noting -- Pass
      }
    }
    
    // Test Invalid End Time
    for (int num = -5000; num < 0; num ++) {
      try {
        IAnimation test = new ScaleAnimation(
              new Rectangle.Builder("test").build(),
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, num, 10, 20, 30, 50
            );
        fail("Exception should have been thrown on ScaleAnimation construction.");
      } catch (IllegalArgumentException iae) {
        // DO Noting -- Pass
      }
    }
    
    // Test Invalid Start Width
    for (int num = -5000; num < 1; num ++) {
      try {
        IAnimation test = new ScaleAnimation(
              new Rectangle.Builder("test").build(),
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, num, 20, 30, 50
            );
        fail("Exception should have been thrown on ScaleAnimation construction.");
      } catch (IllegalArgumentException iae) {
        // DO Noting -- Pass
      }
    }
    
    // Test Invalid End Width
    for (int num = -5000; num < 1; num ++) {
      try {
        IAnimation test = new ScaleAnimation(
              new Rectangle.Builder("test").build(),
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, 10, num, 30, 50
            );
        fail("Exception should have been thrown on ScaleAnimation construction.");
      } catch (IllegalArgumentException iae) {
        // DO Noting -- Pass
      }
    }
    
    // Test Invalid Start Height
    for (int num = -5000; num < 1; num ++) {
      try {
        IAnimation test = new ScaleAnimation(
              new Rectangle.Builder("test").build(),
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, 10, 20, num, 50
            );
        fail("Exception should have been thrown on ScaleAnimation construction.");
      } catch (IllegalArgumentException iae) {
        // DO Noting -- Pass
      }
    }
    
    // Test Invalid End Height
    for (int num = -5000; num < 1; num ++) {
      try {
        IAnimation test = new ScaleAnimation(
              new Rectangle.Builder("test").build(),
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, 10, 20, 30, num
            );
        fail("Exception should have been thrown on ScaleAnimation construction.");
      } catch (IllegalArgumentException iae) {
        // DO Noting -- Pass
      }
    }
  }

  /**
   * Test the getShape method of the ScaleAnimation class.
   */
  @Test
  public void testGetShape() {
    try {
      ShapeAnimation shape1 = new Rectangle.Builder("test").build();
      ShapeAnimation shape2 = new Ellipse.Builder("test2").build();
      IAnimation test = new ScaleAnimation(
            shape1,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertTrue(shape1 == test.getShape());
      assertFalse(shape2 == test.getShape());
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    try {
      ShapeAnimation shape1 = new Rectangle.Builder("test").build();
      ShapeAnimation shape2 = new Ellipse.Builder("test2").build();
      IAnimation test = new ScaleAnimation(
            shape2,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertTrue(shape2 == test.getShape());
      assertFalse(shape1 == test.getShape());
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
  }
  
  /**
   * Test the getType method of the ScaleAnimation class.
   */
  @Test
  public void testGetType() {
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertEquals(AnimationType.SCALE, test.getType());
      assertNotEquals(AnimationType.MOVE, test.getType());
      assertNotEquals(AnimationType.COLOR_CHANGE, test.getType());
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
  }
  
  /**
   * Test the getStartPosition method of the ScaleAnimation class.
   */
  @Test
  public void testGetStartPosition() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      Point2D startPos = new Point2D(150, 200);
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            startPos,
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertTrue(startPos == test.getStartPosition());
      assertEquals(startPos.getX(), test.getStartPosition().getX(), .001);
      assertEquals(startPos.getY(), test.getStartPosition().getY(), .001);
      
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Create and Get 1000x
    int y = 500;
    for (int x = -500; x < 500; x++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        Point2D startPos = new Point2D(x, y);
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              startPos,
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, 10, 20, 30, 50
            );      
        
        assertTrue(startPos == test.getStartPosition());
        assertEquals(startPos.getX(), test.getStartPosition().getX(), .001);
        assertEquals(startPos.getY(), test.getStartPosition().getY(), .001);
        
        y--;
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the getEndPosition method of the ScaleAnimation class.
   */
  @Test
  public void testGetEndPosition() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      Point2D endPos = new Point2D(150, 200);
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(100, 100),
            endPos,
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertTrue(endPos == test.getEndPosition());
      assertEquals(endPos.getX(), test.getEndPosition().getX(), .001);
      assertEquals(endPos.getY(), test.getEndPosition().getY(), .001);
      
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Create and Get 1000x
    int y = 500;
    for (int x = -500; x < 500; x++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        Point2D endPos = new Point2D(x, y);
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              new Point2D(100, 100),
              endPos,
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, 10, 20, 30, 50
            );      
        
        assertTrue(endPos == test.getEndPosition());
        assertEquals(endPos.getX(), test.getEndPosition().getX(), .001);
        assertEquals(endPos.getY(), test.getEndPosition().getY(), .001);
        
        y--;
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the getStartColor method of the ScaleAnimation class.
   */
  @Test
  public void testGetStartColor() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      ShapeColor startColor = new ShapeColor(100, 200, 250);
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            startColor,
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertTrue(startColor == test.getStartColor());
      assertEquals(startColor.getR(), test.getStartColor().getR(), .001);
      assertEquals(startColor.getG(), test.getStartColor().getG(), .001);
      assertEquals(startColor.getB(), test.getStartColor().getB(), .001);

      
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Create and Get 255x
    int rb = 255;
    for (int g = 0; g < 256; g++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        ShapeColor startColor = new ShapeColor(rb, g, rb);
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              startColor,
              new ShapeColor(100, 200, 50),
              1, 100, 10, 20, 30, 50
            );      
        
        assertTrue(startColor == test.getStartColor());
        assertEquals(startColor.getR(), test.getStartColor().getR(), .001);
        assertEquals(startColor.getG(), test.getStartColor().getG(), .001);
        assertEquals(startColor.getB(), test.getStartColor().getB(), .001);
        
        rb--;
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the getEndColor method of the ScaleAnimation class.
   */
  @Test
  public void testGetEndColor() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      ShapeColor endColor = new ShapeColor(100, 200, 250);
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(100, 200, 50),
            endColor,
            1, 100, 10, 20, 30, 50
          );      
      
      assertTrue(endColor == test.getEndColor());
      assertEquals(endColor.getR(), test.getEndColor().getR(), .001);
      assertEquals(endColor.getG(), test.getEndColor().getG(), .001);
      assertEquals(endColor.getB(), test.getEndColor().getB(), .001);
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Create and Get 255x
    int rb = 255;
    for (int g = 0; g < 256; g++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        ShapeColor endColor = new ShapeColor(rb, g, rb);
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(100, 200, 50),
              endColor,
              1, 100, 10, 20, 30, 50
            );      
        
        assertTrue(endColor == test.getEndColor());
        assertEquals(endColor.getR(), test.getEndColor().getR(), .001);
        assertEquals(endColor.getG(), test.getEndColor().getG(), .001);
        assertEquals(endColor.getB(), test.getEndColor().getB(), .001);
        
        rb--;
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the getStartTime method of the ScaleAnimation class.
   */
  @Test
  public void testGetStartTime() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertEquals(1, test.getStartTime());
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Test Create and Get 5000x
    for (int num = 0; num < 5000; num++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              num, 100, 10, 20, 30, 50
            );      
        
        assertEquals(num, test.getStartTime());
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the getEndTime method of the ScaleAnimation class.
   */
  @Test
  public void testGetEndTime() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertEquals(100, test.getEndTime());
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Test Create and Get 5000x
    for (int num = 0; num < 5000; num++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, num, 10, 20, 30, 50
            );      
        
        assertEquals(num, test.getEndTime());
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the getStartWidth method of the ScaleAnimation class.
   */
  @Test
  public void testGetStartWidth() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertEquals(10, test.getStartWidth(), .001);
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Test Create and Get 5000x
    for (int num = 1; num <= 5000; num++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, num, 20, 30, 50
            );      
        
        assertEquals(num, test.getStartWidth(), .001);
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the getEndWidth method of the ScaleAnimation class.
   */
  @Test
  public void testGetEndWidth() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertEquals(20, test.getEndWidth(), .001);
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Test Create and Get 5000x
    for (int num = 1; num <= 5000; num++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, 10, num, 30, 50
            );      
        
        assertEquals(num, test.getEndWidth(), .001);
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the getStartHeight method of the ScaleAnimation class.
   */
  @Test
  public void testGetStartHeight() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertEquals(30, test.getStartHeight(), .001);
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Test Create and Get 5000x
    for (int num = 1; num <= 5000; num++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, 10, 20, num, 50
            );      
        
        assertEquals(num, test.getStartHeight(), .001);
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the getEndHeight method of the ScaleAnimation class.
   */
  @Test
  public void testGetEndHeight() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("test").build();
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      assertEquals(50, test.getEndHeight(), .001);
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
    
    // Test Create and Get 5000x
    for (int num = 1; num <= 5000; num++) {
      try {
        ShapeAnimation shape = new Rectangle.Builder("test").build();
        IAnimation test = new ScaleAnimation(
              shape,
              AnimationType.SCALE,
              new Point2D(0, 0),
              new Point2D(100, 100),
              new ShapeColor(255, 255, 255),
              new ShapeColor(100, 200, 50),
              1, 100, 10, 20, 30, num
            );      
        
        assertEquals(num, test.getEndHeight(), .001);
        
      } catch (IllegalArgumentException iae) {
        fail("Exception should not have been thrown on ScaleAnimation construction.");
      }
    }
  }
  
  /**
   * Test the toString method of the ScaleAnimation class.
   */
  @Test
  public void testToString() {
    // Test Standard Case
    try {
      ShapeAnimation shape = new Rectangle.Builder("testShape").build();
      IAnimation test = new ScaleAnimation(
            shape,
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );      
      
      String expected = 
          "testShape scaled from width: 10.0 and height: 30.0 "
          + "to width: 20.0 and height: 50.0 from t=1 to t=100";
      
      assertEquals(expected, test.toString());
      
    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
  }
  
  /**
   * Test the cloneAnimation method of the ScaleAnimation class.
   */
  @Test
  public void testCloneAnimation() {
    
    try {
      IAnimation test = new ScaleAnimation(
            new Rectangle.Builder("test").build(),
            AnimationType.SCALE,
            new Point2D(0, 0),
            new Point2D(100, 100),
            new ShapeColor(255, 255, 255),
            new ShapeColor(100, 200, 50),
            1, 100, 10, 20, 30, 50
          );
      
      IAnimation testClone = test.cloneAnimation();
      
      assertEquals("test", testClone.getShape().getName());
      assertEquals(AnimationType.SCALE, testClone.getType());
      assertEquals(0, testClone.getStartPosition().getX(), .001);
      assertEquals(0, testClone.getStartPosition().getY(), .001);
      assertEquals(100, testClone.getEndPosition().getX(), .001);
      assertEquals(100, testClone.getEndPosition().getY(), .001);
      assertEquals(255, testClone.getStartColor().getR(), .001);
      assertEquals(255, testClone.getStartColor().getG(), .001);
      assertEquals(255, testClone.getStartColor().getB(), .001);
      assertEquals(100, testClone.getEndColor().getR(), .001);
      assertEquals(200, testClone.getEndColor().getG(), .001);
      assertEquals(50, testClone.getEndColor().getB(), .001);
      assertEquals(1, testClone.getStartTime());
      assertEquals(100, testClone.getEndTime());
      assertEquals(10, testClone.getStartWidth(), .001);
      assertEquals(20, testClone.getEndWidth(), .001);
      assertEquals(30, testClone.getStartHeight(), .001);
      assertEquals(50, testClone.getEndHeight(), .001);
      
      assertFalse(test == testClone);
      
      // Check that nested objects are not the same object
      // Point2D and Shape Color are both immutable
      assertFalse(test.getShape() == testClone.getShape());
      assertFalse(test.getStartPosition() == testClone.getStartPosition());
      assertFalse(test.getEndPosition() == testClone.getEndPosition());
      assertFalse(test.getStartColor() == testClone.getStartColor());
      assertFalse(test.getEndColor() == testClone.getEndColor());

    } catch (IllegalArgumentException iae) {
      fail("Exception should not have been thrown on ScaleAnimation construction.");
    }
  }
}
