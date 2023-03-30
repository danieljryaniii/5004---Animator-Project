package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs5004.animator.model.AnimationCanvas;
import cs5004.animator.model.shapes.Point2D;
import org.junit.Test;


/**
 * A JUnit Testing Suite for the AnimationCanvas class.
 */
public class AnimationCanvasTest {

  /**
   * Test the constructor of the AnimationCanvas.
   */
  @Test
  public void testConstructor() {
    // Test Standard Case
    AnimationCanvas test = new AnimationCanvas(100, 200, new Point2D(0, 0));
    assertEquals(100, test.getWidth());
    assertEquals(200, test.getHeight());
    assertEquals("(0.00, 0.00)", test.getTopLeft().toString());
    
    // Test Case where width is negative and height is positive
    test = new AnimationCanvas(-10, 500, new Point2D(100, 200));
    assertEquals(0, test.getWidth());
    assertEquals(500, test.getHeight());
    assertEquals("(100.00, 200.00)", test.getTopLeft().toString());
    
    // Test Case where width is positive and height is negative
    test = new AnimationCanvas(100, -5, new Point2D(0, 100));
    assertEquals(100, test.getWidth());
    assertEquals(0, test.getHeight());
    assertEquals("(0.00, 100.00)", test.getTopLeft().toString());
    
    // test Case where both width and height are negative
    test = new AnimationCanvas(-200, -1, new Point2D(0, 0));
    assertEquals(0, test.getWidth());
    assertEquals(0, test.getHeight());
    assertEquals("(0.00, 0.00)", test.getTopLeft().toString());
  }
  
  /**
   * Test the getHeight method of the AnimationCanvas class.
   */
  @Test
  public void testGetWidth() {
    // Test Standard Case
    AnimationCanvas test = new AnimationCanvas(100, 50, new Point2D(0, 0));
    assertEquals(100, test.getWidth());
    
    // Test Set and Get 5000x
    for (int num = 0; num < 5000; num++) {
      test = new AnimationCanvas(num, num + 1, new Point2D(0, 0));
      assertEquals(num, test.getWidth());
    }
  }
  
  /**
   * Test the getWidth method of the AnimationCanvas class.
   */
  @Test
  public void testGetHeight() {
    // Test Standard Case
    AnimationCanvas test = new AnimationCanvas(100, 50, new Point2D(0, 0));
    assertEquals(50, test.getHeight());
    
    // Test Set and Get 5000x
    for (int num = 0; num < 5000; num++) {
      test = new AnimationCanvas(num + 1, num, new Point2D(0, 0));
      assertEquals(num, test.getHeight());
    }
  }
  
  /**
   * Test the getTopLeft method of the AnimationCanvas class.
   */
  @Test
  public void testGetTopLeft() {
    // Test Standard Case
    AnimationCanvas test = new AnimationCanvas(100, 50, new Point2D(0, 100));
    assertEquals(0, test.getTopLeft().getX(), .001);
    assertEquals(100, test.getTopLeft().getY(), .001);
    
    // Test Set and Get 5000x
    int y = 2500;
    for (int x = -2500; x < 2500; x++) {
      test = new AnimationCanvas(700, 700, new Point2D(x, y));
      assertEquals(x, test.getTopLeft().getX(), .001);
      assertEquals(y, test.getTopLeft().getY(), .001);
      
      y--;
    }
  }
  
  /**
   * Test the cloneCanvas method of the AnimationCanvas class.
   */
  @Test
  public void testCloneCanvas() {
    Point2D oldPoint = new Point2D(0, 0);
    AnimationCanvas test = new AnimationCanvas(100, 200, oldPoint);
    AnimationCanvas testClone = test.cloneCanvas();
    
    assertEquals(100, testClone.getWidth());
    assertEquals(200, testClone.getHeight());
    assertEquals(0, testClone.getTopLeft().getX(), .001);
    assertEquals(0, testClone.getTopLeft().getY(), .001);
    assertFalse(test.getTopLeft() == testClone.getTopLeft());
  }



}
