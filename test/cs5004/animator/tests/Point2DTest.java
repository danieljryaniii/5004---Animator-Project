package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs5004.animator.model.shapes.Point2D;
import org.junit.Test;


/**
 * JUnit Testing Suite for the Point2D Class.
 */
public class Point2DTest {
  
  /**
   * Test the Constructor of the Point2D class.
   */
  @Test
  public void testConstructor() {
    // Test Standard Case
    Point2D test = new Point2D(1.00, 2.00);
    String expected = "(1.00, 2.00)";   
    assertEquals(expected, test.toString());

    // Test 5000 Instances (Both Ways)
    Point2D test1;
    Point2D test2;

    int j = 5000;
    for (int i = -5000; i < 0; i++) {
      test1 = new Point2D(i, j);
      assertEquals(test1.getX(), i, .001);
      assertEquals(test1.getY(), j, .001);
      
      test2 = new Point2D(j, i);
      assertEquals(test2.getX(), j, .001);
      assertEquals(test2.getY(), i, .001);
    }
  }
  
  /**
   * Test the getX method of the Point2D class.
   */
  @Test
  public void testGetX() {
    // Test standard cases
    Point2D test = new Point2D(1.00, 2.00);   
    assertEquals(1.00, test.getX(), .001);
    
    test = new Point2D(200, 0);
    assertEquals(200.00, test.getX(), .001);
    
    // Test 5000 Instances (Both Ways)
    Point2D test1;
    Point2D test2;

    int j = 5000;
    for (int i = -2500; i < 2500; i++) {
      test1 = new Point2D(i, j);
      assertEquals(test1.getX(), i, .001);
      
      test2 = new Point2D(j, i);
      assertEquals(test2.getX(), j, .001);
    }
  }
  
  /**
   * Test the getY method of the Point2D class.
   */
  @Test
  public void testGetY() {
    // Test standard cases
    Point2D test = new Point2D(1.00, 2.00);   
    assertEquals(2.00, test.getY(), .001);
    
    test = new Point2D(200, 0);
    assertEquals(0.00, test.getY(), .001);
    
    // Test 5000 Instances (Both Ways)
    Point2D test1;
    Point2D test2;

    int j = 5000;
    for (int i = -2500; i < 2500; i++) {
      test1 = new Point2D(i, j);
      assertEquals(test1.getY(), j, .001);
      
      test2 = new Point2D(j, i);
      assertEquals(test2.getY(), i, .001);
    }    
  }
  
  /**
   * Test the clone method of the Point2D class.
   */
  @Test
  public void testClone() {
    // Test standard instance
    Point2D test = new Point2D(100, 200);
    Point2D testClone = test.clone();
    assertEquals(100, testClone.getX(), .001);
    assertEquals(200, testClone.getY(), .001);
    assertFalse(test == testClone);
    
    // Test 5000 Instances
    int j = 5000;
    for (int i = -5000; i < 0; i++) {
      test = new Point2D(i, j);
      testClone = test.clone();
      assertEquals(i, testClone.getX(), .001);
      assertEquals(j, testClone.getY(), .001);
      assertFalse(test == testClone);      
    }
  }
  
  /**
   * Test the toString method of the Point2D class.
   */
  @Test
  public void testToString() {
    // Test Standard Case
    Point2D test = new Point2D(1.00, 2.00);
    String expected = "(1.00, 2.00)";   
    assertEquals(expected, test.toString());
 
    // Test Proper Decimal Place Used in toString
    test = new Point2D(100, 50.555);
    expected = "(100.00, 50.56)";
    assertEquals(expected, test.toString());
    
    test = new Point2D(200.5, 0);
    expected = "(200.50, 0.00)";
    assertEquals(expected, test.toString());
  }

}
