package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import cs5004.animator.model.shapes.ShapeColor;
import org.junit.Test;

/**
 * JUnit Testing Suite for the ShapeColor class.
 */
public class ShapeColorTest {

  /**
   * Test the Constructor of the ShapeColor class.
   */
  @Test
  public void testConstructor() {
    // Test Standard Case
    ShapeColor test = new ShapeColor(0, 100, 255);
    assertEquals(0, test.getR(), .001);
    assertEquals(100, test.getG(), .001);
    assertEquals(255, test.getB(), .001);
    
    // Test All Valid Cases
    ShapeColor testR;
    ShapeColor testG;
    ShapeColor testB;
    ShapeColor testRGB;
    for (int num = 0; num < 256; num++) {
      try {
        testR = new ShapeColor(num, 0, 0);
        assertEquals(num, testR.getR(), .001);
        assertEquals(0, testR.getG(), .001);
        assertEquals(0, testR.getB(), .001);
      } catch (IllegalArgumentException e) {
        fail("Exception should not have been thrown on construction.");
      }
      
      try {
        testG = new ShapeColor(0, num, 0);
        assertEquals(0, testG.getR(), .001);
        assertEquals(num, testG.getG(), .001);
        assertEquals(0, testG.getB(), .001);
      } catch (IllegalArgumentException e) {
        fail("Exception should not have been thrown on construction.");
      }

      try {
        testB = new ShapeColor(0, 0, num);
        assertEquals(0, testB.getR(), .001);
        assertEquals(0, testB.getG(), .001);
        assertEquals(num, testB.getB(), .001);
      } catch (IllegalArgumentException e) {
        fail("Exception should not have been thrown on construction.");
      }
      
      try {
        testRGB = new ShapeColor(num, num, num);
        assertEquals(num, testRGB.getR(), .001);
        assertEquals(num, testRGB.getG(), .001);
        assertEquals(num, testRGB.getB(), .001);
      } catch (IllegalArgumentException e) {
        fail("Exception should not have been thrown on construction.");
      }
    }
  }
  
  /**
   * Test Invalid Construction - i.e. ShapeColor with an R, G or B
   * value that is less than zero or greater than 255.
   */
  @Test
  public void testInvalidConstructor() {
    // Test Standard Cases
    try {
      ShapeColor test = new ShapeColor(-1, 0, 255);      
      fail("Exception should have been thrown on construction.");
    } catch (IllegalArgumentException e) {
      // DO Nothing -- PASS
    }
    
    try {
      ShapeColor test = new ShapeColor(0, 256, 255);      
      fail("Exception should have been thrown on construction.");
    } catch (IllegalArgumentException e) {
      // DO Nothing -- PASS
    }
    
    try {
      ShapeColor test = new ShapeColor(0, 100, 300);      
      fail("Exception should have been thrown on construction.");
    } catch (IllegalArgumentException e) {
      // DO Nothing -- PASS
    }

    // Test Various Invalid cases
    ShapeColor testR;
    ShapeColor testG;
    ShapeColor testB;
    ShapeColor testRB;
    ShapeColor testRG;
    ShapeColor testGB;
    ShapeColor testRGB;
    
    int lower = -256;
    int upper = 256;
    for (int num = 0; num < 256; num++) {
      // Test only R (Both ends of Range)
      try {
        testR = new ShapeColor(lower, num, num);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }

      try {
        testR = new ShapeColor(upper, num, num);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }
      
      // Test only G (Both ends of Range)
      try {
        testG = new ShapeColor(num, lower, num);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }

      try {
        testG = new ShapeColor(num, upper, num);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }

      // Test only B (Both ends of Range)
      try {
        testB = new ShapeColor(num, num, lower);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }

      try {
        testB = new ShapeColor(num, num, upper);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }
      
      // Test RB (Both ends of Range)
      try {
        testRB = new ShapeColor(lower, lower, num);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }

      try {
        testRB = new ShapeColor(upper, upper, num);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }
      
      // Test RG (Both ends of Range)
      try {
        testRG = new ShapeColor(lower, num, lower);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }

      try {
        testRG = new ShapeColor(upper, num, upper);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }
      
      // Test GB (Both ends of Range)
      try {
        testGB = new ShapeColor(num, lower, lower);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }

      try {
        testGB = new ShapeColor(num, upper, upper);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }

      // Test RGB (Both ends of Range)
      try {
        testRGB = new ShapeColor(lower, lower, lower);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }

      try {
        testRGB = new ShapeColor(upper, upper, upper);
        fail("Exception should have been thrown on construction.");
      } catch (IllegalArgumentException e) {
        // DO Nothing -- PASS
      }
      
      lower++;
      upper++;
    }
  }
  
  /**
   * Test the getR method of the ShapeColor class.
   */
  @Test
  public void testGetR() {
    // Test Standard Case
    ShapeColor test = new ShapeColor(255, 0, 100);
    assertEquals(255, test.getR(), .001);
    
    // Test all valid cases
    for (int num = 0; num < 256; num++) {
      test = new ShapeColor(num, 0, 255);
      assertEquals(num, test.getR(), .001);
    }
  }
  
  /**
   * Test the getG method of the ShapeColor class.
   */
  @Test
  public void testGetG() {
    // Test Standard Case
    ShapeColor test = new ShapeColor(255, 0, 100);
    assertEquals(0, test.getG(), .001);
    
    // Test all valid cases
    for (int num = 0; num < 256; num++) {
      test = new ShapeColor(0, num, 255);
      assertEquals(num, test.getG(), .001);
    }
  }
  
  /**
   * Test the getG method of the ShapeColor class.
   */
  @Test
  public void testGetB() {
    // Test Standard Case
    ShapeColor test = new ShapeColor(255, 0, 100);
    assertEquals(100, test.getB(), .001);
    
    // Test all valid cases
    for (int num = 0; num < 256; num++) {
      test = new ShapeColor(0, 255, num);
      assertEquals(num, test.getB(), .001);
    }
  }
  
  /**
   * Test the cloneColor method of the ShapeColor class.
   */
  @Test
  public void testCloneColor() {
    // Test Standard Case
    ShapeColor test = new ShapeColor(255, 0, 100);
    ShapeColor testClone = test.cloneColor();
    assertEquals(255, testClone.getR(), .001);
    assertEquals(0, testClone.getG(), .001);
    assertEquals(100, testClone.getB(), .001);
    assertFalse(test == testClone);
    
    // Test 5000 instances
    int j = 255;
    for (int i = 0; i < 256; i++) {
      test = new ShapeColor(i, j, i);
      testClone = test.cloneColor();
      assertEquals(i, testClone.getR(), .001);
      assertEquals(j, testClone.getG(), .001);
      assertEquals(i, testClone.getB(), .001);
      assertFalse(test == testClone);    
    }
  }
  
  /**
   * Test the toString method of the ShapeColor class.
   */
  @Test
  public void testToString() {
    // Test Standard Case
    ShapeColor test = new ShapeColor(255, 100, 0);
    String expected = "(255.00, 100.00, 0.00)";   
    assertEquals(expected, test.toString());
 
    // Test Proper Decimal Place Used in toString
    test = new ShapeColor(250.5, 50.555, 100);
    expected = "(250.50, 50.56, 100.00)";
    assertEquals(expected, test.toString());
    
    test = new ShapeColor(10, 200.0051, 50.1111);
    expected = "(10.00, 200.01, 50.11)";
    assertEquals(expected, test.toString());
  }  
}
