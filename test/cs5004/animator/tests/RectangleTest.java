package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.Rectangle;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;
import cs5004.animator.model.shapes.ShapeType;
import org.junit.Test;

/**
 * A JUnit Testing Suite for the Rectangle class which is a child
 * class of the AbstractShapeAnimation class.
 */
public class RectangleTest {

  /**
   * Test the Constructor/Builder method of the Rectangle class.
   */
  @Test
  public void testConstructor() {
    // Test Builder with only required argument
    ShapeAnimation test = new Rectangle.Builder("test").build();
    // Given Values
    assertEquals("test", test.getName());
    
    // Default Values
    assertEquals(Integer.MAX_VALUE, test.getPosition().getX(), .001);
    assertEquals(Integer.MAX_VALUE, test.getPosition().getY(), .001);
    assertEquals(0, test.getColor().getR(), .001);
    assertEquals(0, test.getColor().getG(), .001);
    assertEquals(0, test.getColor().getB(), .001);
    assertEquals(Integer.MAX_VALUE, test.getWidth(), .001);
    assertEquals(Integer.MAX_VALUE, test.getHeight(), .001);
    assertEquals(Integer.MAX_VALUE, test.getAppearTime(), .001);
    assertEquals(Integer.MAX_VALUE, test.getRemoveTime(), .001);
    assertFalse(test.isVisible());
    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .build();
    // Given Values
    assertEquals("test", test.getName());
    assertEquals(100, test.getPosition().getX(), .001);
    assertEquals(100, test.getPosition().getY(), .001);    

    // Default Values
    assertEquals(0, test.getColor().getR(), .001);
    assertEquals(0, test.getColor().getG(), .001);
    assertEquals(0, test.getColor().getB(), .001);
    assertEquals(Integer.MAX_VALUE, test.getWidth(), .001);
    assertEquals(Integer.MAX_VALUE, test.getHeight(), .001);
    assertEquals(Integer.MAX_VALUE, test.getAppearTime(), .001);
    assertEquals(Integer.MAX_VALUE, test.getRemoveTime(), .001);
    assertFalse(test.isVisible());
    
    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .build();
    // Given Values
    assertEquals("test", test.getName());
    assertEquals(100, test.getPosition().getX(), .001);
    assertEquals(100, test.getPosition().getY(), .001);
    assertEquals(255, test.getColor().getR(), .001);
    assertEquals(10, test.getColor().getG(), .001);
    assertEquals(100, test.getColor().getB(), .001);

    // Default Values
    assertEquals(Integer.MAX_VALUE, test.getWidth(), .001);
    assertEquals(Integer.MAX_VALUE, test.getHeight(), .001);
    assertEquals(Integer.MAX_VALUE, test.getAppearTime(), .001);
    assertEquals(Integer.MAX_VALUE, test.getRemoveTime(), .001);
    assertFalse(test.isVisible());
    
    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .width(10.5)
                        .build();
    // Given Values
    assertEquals("test", test.getName());
    assertEquals(100, test.getPosition().getX(), .001);
    assertEquals(100, test.getPosition().getY(), .001);
    assertEquals(255, test.getColor().getR(), .001);
    assertEquals(10, test.getColor().getG(), .001);
    assertEquals(100, test.getColor().getB(), .001);
    assertEquals(10.5, test.getWidth(), .001);

    // Default Values
    assertEquals(Integer.MAX_VALUE, test.getHeight(), .001);
    assertEquals(Integer.MAX_VALUE, test.getAppearTime(), .001);
    assertEquals(Integer.MAX_VALUE, test.getRemoveTime(), .001);
    assertFalse(test.isVisible());
   
    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .width(10.5)
                        .height(20)
                        .build();
    // Given Values
    assertEquals("test", test.getName());
    assertEquals(100, test.getPosition().getX(), .001);
    assertEquals(100, test.getPosition().getY(), .001);
    assertEquals(255, test.getColor().getR(), .001);
    assertEquals(10, test.getColor().getG(), .001);
    assertEquals(100, test.getColor().getB(), .001);
    assertEquals(10.5, test.getWidth(), .001);
    assertEquals(20, test.getHeight(), .001);

    // Default Values
    assertEquals(Integer.MAX_VALUE, test.getAppearTime(), .001);
    assertEquals(Integer.MAX_VALUE, test.getRemoveTime(), .001);
    assertFalse(test.isVisible());
    
 
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .width(10.5)
                        .height(20)
                        .appearTime(1)
                        .build();
    // Given Values
    assertEquals("test", test.getName());
    assertEquals(100, test.getPosition().getX(), .001);
    assertEquals(100, test.getPosition().getY(), .001);
    assertEquals(255, test.getColor().getR(), .001);
    assertEquals(10, test.getColor().getG(), .001);
    assertEquals(100, test.getColor().getB(), .001);
    assertEquals(10.5, test.getWidth(), .001);
    assertEquals(20, test.getHeight(), .001);
    assertEquals(1, test.getAppearTime(), .001);

    // Default Values
    assertEquals(Integer.MAX_VALUE, test.getRemoveTime(), .001);
    assertFalse(test.isVisible());
    
    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .width(10.5)
                        .height(20)
                        .appearTime(1)
                        .removeTime(100)
                        .build();
    // Given Values
    assertEquals("test", test.getName());
    assertEquals(100, test.getPosition().getX(), .001);
    assertEquals(100, test.getPosition().getY(), .001);
    assertEquals(255, test.getColor().getR(), .001);
    assertEquals(10, test.getColor().getG(), .001);
    assertEquals(100, test.getColor().getB(), .001);
    assertEquals(10.5, test.getWidth(), .001);
    assertEquals(20, test.getHeight(), .001);
    assertEquals(1, test.getAppearTime(), .001);
    assertEquals(100, test.getRemoveTime(), .001);

    // Default Values
    assertFalse(test.isVisible());
 
     
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .width(10.5)
                        .height(20)
                        .appearTime(1)
                        .removeTime(100)
                        .visibility(true)
                        .build();
    // Given Values
    assertEquals("test", test.getName());
    assertEquals(100, test.getPosition().getX(), .001);
    assertEquals(100, test.getPosition().getY(), .001);
    assertEquals(255, test.getColor().getR(), .001);
    assertEquals(10, test.getColor().getG(), .001);
    assertEquals(100, test.getColor().getB(), .001);
    assertEquals(10.5, test.getWidth(), .001);
    assertEquals(20, test.getHeight(), .001);
    assertEquals(1, test.getAppearTime(), .001);
    assertEquals(100, test.getRemoveTime(), .001);
    assertTrue(test.isVisible());
 
  }
  
  /**
   * Test the moveShape method of the Rectangle class.
   */
  @Test
  public void testMove() {
    Point2D oldPos;
    Point2D newPos;

    // Set Start to (0, 0)
    ShapeAnimation test = new Rectangle.Builder("test").referencePoint(new Point2D(0, 0)).build();
    oldPos = test.getPosition();
    assertEquals(0, oldPos.getX(), .001);
    assertEquals(0, oldPos.getY(), .001);
    
    // Move to 100, 200
    newPos = new Point2D(100, 200);
    test.moveShape(newPos);
    assertEquals(100, newPos.getX(), .001);
    assertEquals(200, newPos.getY(), .001);
    assertEquals(newPos, test.getPosition());
    assertNotEquals(oldPos, test.getPosition());
    
    // Move 5000x
    int y = 2500;
    for (int x = -2500; x < 2500; x++) {
      oldPos = test.getPosition();
      
      newPos = new Point2D(x, y);
      test.moveShape(newPos);
      assertEquals(x, newPos.getX(), .001);
      assertEquals(y, newPos.getY(), .001);
      assertEquals(newPos, test.getPosition());
      assertNotEquals(oldPos, test.getPosition());
      
      y--;
    }
  }
  
  /**
   * Test valid scale method of the Rectangle class.
   */
  @Test
  public void testValidScale() {
    // Size Default = H: Integer Max -- W: Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getWidth(), .001);
    assertEquals(Integer.MAX_VALUE, test.getHeight(), .001);
    
    // Test Scale (Valid)
    try {
      test.scale(10, 100);
      assertEquals(10, test.getWidth(), .001);
      assertEquals(100, test.getHeight(), .001);      
    } catch (IllegalArgumentException e) {
      fail("Error should not have been thrown on scale");
    }
    
    try {
      test.scale(200, 5);
      assertEquals(200, test.getWidth(), .001);
      assertEquals(5, test.getHeight(), .001);      
    } catch (IllegalArgumentException e) {
      fail("Error should not have been thrown on scale");
    }
    
    // Set and Get 5000x (valid)
    int height = 5001;
    for (int width = 1; width <= 5000; width++) {
      try {
        test.scale(width, height);
        assertEquals(width, test.getWidth(), .001);
        assertEquals(height, test.getHeight(), .001);      
      } catch (IllegalArgumentException e) {
        fail("Error should not have been thrown on scale");
      }      
      
      height--;
    }
  }
  
  /**
   * Test invalid scale method of the Rectangle class.
   */
  @Test
  public void testInvalidScale() {
    // Size Default = H: Integer Max -- W: Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getWidth(), .001);
    assertEquals(Integer.MAX_VALUE, test.getHeight(), .001);
    
    // Test Scale (Invalid)
    try {
      test.scale(10, -100);
      fail("Error should have been thrown on scale");
    } catch (IllegalArgumentException e) {
      // Do Nothing -- PASS
    }
    
    try {
      test.scale(-10, 100);
      fail("Error should have been thrown on scale");
    } catch (IllegalArgumentException e) {
      // Do Nothing -- PASS
    }
    
    try {
      test.scale(-10, -100);
      fail("Error should have been thrown on scale");
    } catch (IllegalArgumentException e) {
      // Do Nothing -- PASS
    }

    try {
      test.scale(0, 100);
      fail("Error should have been thrown on scale");
    } catch (IllegalArgumentException e) {
      // Do Nothing -- PASS
    }
    
    try {
      test.scale(100, 0);
      fail("Error should have been thrown on scale");
    } catch (IllegalArgumentException e) {
      // Do Nothing -- PASS
    }
    
    // Set and Get 5000x (invalid)
    int height = -2500;
    for (int width = 2500; width > -2500; width--) {
      try {
        test.scale(width, height);
        fail("Error should have been thrown on scale");
      } catch (IllegalArgumentException e) {
        // Do Nothing -- PASS
      }      
      height++;
    }    
  }
  
  /**
   * Test the showShape method of the Rectangle class.
   */
  @Test
  public void testShowShape() {
    // Default = hidden
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertFalse(test.isVisible());
    
    // Test Toggling Visibility
    test.showShape();
    assertTrue(test.isVisible());
    
    test.showShape();
    assertTrue(test.isVisible());
    
    test.hideShape();
    assertFalse(test.isVisible());
    
    test.showShape();
    assertTrue(test.isVisible());
  }
  
  /**
   * Test the hideShape method of the Rectangle class.
   */
  @Test
  public void testHideShape() {
    // Default = hidden
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertFalse(test.isVisible());
    
    // Test Toggling Visibility
    test.showShape();
    assertTrue(test.isVisible());
    
    test.hideShape();
    assertFalse(test.isVisible());
    
    test.hideShape();
    assertFalse(test.isVisible());
    
    test.showShape();
    assertTrue(test.isVisible());
    
    test.hideShape();
    assertFalse(test.isVisible());
    
    // Test if initialized to visible
    test = new Rectangle.Builder("test").visibility(true).build();
    assertTrue(test.isVisible());
    
    test.hideShape();
    assertFalse(test.isVisible());
  }
  
  /**
   * Test valid setWidth method of the Rectangle class.
   */
  @Test
  public void testValidSetWidth() {
    // Default Width = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getWidth(), .001);
    
    try {
      test.setWidth(100);
      assertEquals(100, test.getWidth(), .001);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown on setWidth");
    }
    
    try {
      test.setWidth(1);
      assertEquals(1, test.getWidth(), .001);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown on setWidth");
    }
    
    // Set and Get 5000x (valid)
    for (int num = 1; num < 5000; num++) {
      try {
        test.setWidth(num);
        assertEquals(num, test.getWidth(), .001);
      } catch (IllegalArgumentException e) {
        fail("Exception should not have been thrown on setWidth");
      }
    }
  }
  
  /**
   * Test invalid setWidth method of the Rectangle class.
   */
  @Test
  public void testInvalidSetWidth() {
    // Default Width = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getWidth(), .001);
    
    try {
      test.setWidth(0);
      fail("Exception should have been thrown on setWidth");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    
    try {
      test.setWidth(-1);
      fail("Exception should have been thrown on setWidth");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    
    // Set and Get 5000x (invalid)
    for (int num = 0; num > -5000; num--) {
      try {
        test.setWidth(num);
        fail("Exception should have been thrown on setWidth");
      } catch (IllegalArgumentException e) {
        // Do Nothing
      }
    }
  }
  
  /**
   * Test valid setHeight method of the Rectangle class.
   */
  @Test
  public void testValidSetHeight() {
    // Default Height = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getHeight(), .001);
    
    try {
      test.setHeight(100);
      assertEquals(100, test.getHeight(), .001);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown on setHeight");
    }
    
    try {
      test.setHeight(1);
      assertEquals(1, test.getHeight(), .001);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown on setHeight");
    }
    
    // Set and Get 5000x (valid)
    for (int num = 1; num < 5000; num++) {
      try {
        test.setHeight(num);
        assertEquals(num, test.getHeight(), .001);
      } catch (IllegalArgumentException e) {
        fail("Exception should not have been thrown on setHeight");
      }
    }
  }
  
  /**
   * Test invalid setHeight method of the Rectangle class.
   */
  @Test
  public void testInvalidSetHeight() {
    // Default Height = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getHeight(), .001);
    
    try {
      test.setHeight(0);
      fail("Exception should have been thrown on setHeight");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    
    try {
      test.setHeight(-1);
      fail("Exception should have been thrown on setHeight");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    
    // Set and Get 5000x (invalid)
    for (int num = 0; num > -5000; num--) {
      try {
        test.setHeight(num);
        fail("Exception should have been thrown on setHeight");
      } catch (IllegalArgumentException e) {
        // Do Nothing
      }
    }
  }
  
  /**
   * Test valid setAppearTime method of the Rectangle class.
   */
  @Test
  public void testValidSetAppearTime() {
    // Default Appear Time = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getAppearTime(), .001);
    
    try {
      test.setAppearTime(100);
      assertEquals(100, test.getAppearTime(), .001);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown on setAppearTime");
    }
    
    try {
      test.setAppearTime(1);
      assertEquals(1, test.getAppearTime(), .001);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown on setAppearTime");
    }
    
    // Set and Get 5000x (valid)
    for (int num = 1; num < 5000; num++) {
      try {
        test.setAppearTime(num);
        assertEquals(num, test.getAppearTime(), .001);
      } catch (IllegalArgumentException e) {
        fail("Exception should not have been thrown on setAppearTime");
      }
    }
  }
  
  /**
   * Test invalid setAppearTime method of the Rectangle class.
   */
  @Test
  public void testInvalidSetAppearTime() {
    // Default Appear Time = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getAppearTime(), .001);
    
    try {
      test.setAppearTime(-10);
      fail("Exception should have been thrown on setAppearTime");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    
    try {
      test.setAppearTime(-1);
      fail("Exception should have been thrown on setAppearTime");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    
    // Set and Get 5000x (invalid)
    for (int num = -1; num >= -5000; num--) {
      try {
        test.setAppearTime(num);
        fail("Exception should have been thrown on setAppearTime");
      } catch (IllegalArgumentException e) {
        // Do Nothing
      }
    }          
  }
  
  /**
   * Test valid setRemoveTime method of the Rectangle class.
   */
  @Test
  public void testValidSetRemoveTime() {
    // Default Appear Time = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getRemoveTime(), .001);
    
    try {
      test.setRemoveTime(100);
      assertEquals(100, test.getRemoveTime(), .001);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown on setRemoveTime");
    }
    
    try {
      test.setRemoveTime(1);
      assertEquals(1, test.getRemoveTime(), .001);
    } catch (IllegalArgumentException e) {
      fail("Exception should not have been thrown on setRemoveTime");
    }
    
    // Set and Get 5000x (valid)
    for (int num = 1; num < 5000; num++) {
      try {
        test.setRemoveTime(num);
        assertEquals(num, test.getRemoveTime(), .001);
      } catch (IllegalArgumentException e) {
        fail("Exception should not have been thrown on setRemoveTime");
      }
    }
  }
  
  /**
   * Test invalid setRemoveTime method of the Rectangle class.
   */
  @Test
  public void testInvalidSetRemoveTime() {
    // Default Remove Time = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getRemoveTime(), .001);
    
    try {
      test.setRemoveTime(-10);
      fail("Exception should have been thrown on setRemoveTime");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    
    try {
      test.setRemoveTime(-1);
      fail("Exception should have been thrown on setRemoveTime");
    } catch (IllegalArgumentException e) {
      // Do Nothing
    }
    
    // Set and Get 5000x (invalid)
    for (int num = -1; num >= -5000; num--) {
      try {
        test.setRemoveTime(num);
        fail("Exception should have been thrown on setRemoveTime");
      } catch (IllegalArgumentException e) {
        // Do Nothing
      }
    }      
  }
  
  /**
   * Test the setColor method of the Rectangle class.
   */
  @Test
  public void testSetColor() {
    ShapeColor oldColor;
    ShapeColor newColor;
    
    // Default Color = 000
    ShapeAnimation test = new Rectangle.Builder("test").build();
    oldColor = test.getColor();
    assertEquals(0, oldColor.getR(), .001);
    assertEquals(0, oldColor.getG(), .001);
    assertEquals(0, oldColor.getB(), .001);
    
    // Change color to 255 100 50
    newColor = new ShapeColor(255, 100, 50);
    test.setColor(newColor);
    assertEquals(255, newColor.getR(), .001);
    assertEquals(100, newColor.getG(), .001);
    assertEquals(50, newColor.getB(), .001);
    assertEquals(newColor, test.getColor());
    assertNotEquals(oldColor, test.getColor());
    
    // Change Color 255x
    int r = 255;
    int b = 255;
    for (int g = 0; g < 256; g++) {
      oldColor = test.getColor();
      
      newColor = new ShapeColor(r, g, b);
      test.setColor(newColor);
      assertEquals(r, newColor.getR(), .001);
      assertEquals(g, newColor.getG(), .001);
      assertEquals(b, newColor.getB(), .001);
      assertEquals(newColor, test.getColor());
      assertNotEquals(oldColor, test.getColor());
      
      r--;
      b--;
    }
  }
  
  /**
   * Test the getName method of the Rectangle class.
   */
  @Test
  public void testGetName() {
    // Test a few standard cases
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals("test", test.getName());
    
    test = new Rectangle.Builder("shape1").build();
    assertEquals("shape1", test.getName());
    
    test = new Rectangle.Builder("SHAPE2").build();
    assertEquals("SHAPE2", test.getName());
    
    test = new Rectangle.Builder("SHapE").build();
    assertEquals("SHapE", test.getName());
    
    test = new Rectangle.Builder("!!@SHapE#$%").build();
    assertEquals("!!@SHapE#$%", test.getName());
  }
  
  /**
   * Test the getPosition method of the Rectangle class.
   */
  @Test
  public void testGetPosition() {
    // Default = (Integer MAX, Integer MAX)
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getPosition().getX(), .001);
    assertEquals(Integer.MAX_VALUE, test.getPosition().getY(), .001);
    
    // Build Shape With a Position
    test = new Rectangle.Builder("test").referencePoint(new Point2D(200, 100)).build();
    assertEquals(200, test.getPosition().getX(), .001);
    assertEquals(100, test.getPosition().getY(), .001);
    
    test.moveShape(new Point2D(10, 15.5));
    assertEquals(10, test.getPosition().getX(), .001);
    assertEquals(15.5, test.getPosition().getY(), .001);
    
    // Set and Get 5000x
    int y = -2500;
    for (int x = 2500; x > -2500; x--) {
      test.moveShape(new Point2D(x, y));
      assertEquals(x, test.getPosition().getX(), .001);
      assertEquals(y, test.getPosition().getY(), .001);
      
      y++;
    }
  }
  
  /**
   * Test the getColor method of the Rectangle class.
   */
  @Test
  public void testGetColor() {
    // Default = Color 000
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(0, test.getColor().getR(), .001);
    assertEquals(0, test.getColor().getG(), .001);
    assertEquals(0, test.getColor().getB(), .001);

    // Build Shape With a Color
    test = new Rectangle.Builder("test").color(new ShapeColor(100, 255, 10)).build();
    assertEquals(100, test.getColor().getR(), .001);
    assertEquals(255, test.getColor().getG(), .001);
    assertEquals(10, test.getColor().getB(), .001);
    
    // Set and Get 255x
    double color2 = 255;
    for (int color = 0; color < 256; color++) {
      test.setColor(new ShapeColor(color, color2, color));
      assertEquals(color, test.getColor().getR(), .001);
      assertEquals(color2, test.getColor().getG(), .001);
      assertEquals(color, test.getColor().getB(), .001);
      
      test.setColor(new ShapeColor(color2, color, color2));
      assertEquals(color2, test.getColor().getR(), .001);
      assertEquals(color, test.getColor().getG(), .001);
      assertEquals(color2, test.getColor().getB(), .001);
      
      color2--;
    }
  }
  
  /**
   * Test the getWidth method of the Rectangle class.
   */
  @Test
  public void testGetWidth() {
    // Default Width = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getWidth(), .001);
    
    test.setWidth(200);
    assertEquals(200, test.getWidth(), .001);
    
    // Build shape with given width
    test = new Rectangle.Builder("test").width(100.50).build();
    assertEquals(100.50, test.getWidth(), .001);
    
    // Set and Get 5000x
    for (int num = 1; num <= 5000; num++) {
      test.setWidth(num);
      assertEquals(num, test.getWidth(), .001);
    }
  }
  
  /**
   * Test the getHeight method of the Rectangle class.
   */
  @Test
  public void testGetHeight() {
    // Default Height = Integer Max
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getHeight(), .001);
    
    test.setHeight(200);
    assertEquals(200, test.getHeight(), .001);
    
    // Build Shape with given height
    test = new Rectangle.Builder("test").height(50.5).build();
    assertEquals(50.5, test.getHeight(), .001);
    
    // Set and Get 5000x
    for (int num = 1; num <= 5000; num++) {
      test.setHeight(num);
      assertEquals(num, test.getHeight(), .001);
    }
  }
  
  /**
   * Test the getAppearTime method of the Rectangle class.
   */
  @Test
  public void testGetAppearTime() {
    // Default = IntegerMax
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getAppearTime());
    
    test.setAppearTime(10);
    assertEquals(10, test.getAppearTime());
    
    // Build Shape with remove time
    test = new Rectangle.Builder("test").appearTime(100).build();
    assertEquals(100, test.getAppearTime());
    
    // Set and Get 5000x
    for (int aTime = 0; aTime < 5000; aTime++) {
      test.setAppearTime(aTime);
      assertEquals(aTime, test.getAppearTime());
    }    
  }
  
  /**
   * Test the getRemoveTime method of the Rectangle class.
   */
  @Test
  public void testGetRemoveTime() {
    // Default = IntegerMax
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(Integer.MAX_VALUE, test.getRemoveTime());
    
    test.setRemoveTime(10);
    assertEquals(10, test.getRemoveTime());
    
    // Build Shape with remove time
    test = new Rectangle.Builder("test").removeTime(100).build();
    assertEquals(100, test.getRemoveTime());
    
    // Set and Get 5000x
    for (int aTime = 0; aTime < 5000; aTime++) {
      test.setRemoveTime(aTime);
      assertEquals(aTime, test.getRemoveTime());
    }
  }
  
  /**
   * Test the isVisible method of the Rectangle class.
   */
  @Test
  public void testIsVisible() {
    // Default = Hidden
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertFalse(test.isVisible());
    
    test.showShape();
    assertTrue(test.isVisible());
    
    test.hideShape();
    assertFalse(test.isVisible());
    
    // Build Shape to Visible
    test = new Rectangle.Builder("test").visibility(true).build();
    assertTrue(test.isVisible());
    
    test.hideShape();
    assertFalse(test.isVisible());
    
    test.showShape();
    assertTrue(test.isVisible());
  }
  
  /**
   * Test the toString method of the Rectangle class.
   */
  @Test
  public void testToString() {
    // Test Builder with only required argument
    String expected;
    ShapeAnimation test = new Rectangle.Builder("test").build();

    expected =     
          "Name: test\n"
        + "Type: rectangle\n"
        + "Top Left Corner: (2147483647.00, 2147483647.00)\n"
        + "Height: 2.147483647E9\n"   // Double
        + "Width: 2.147483647E9\n"    // Double
        + "Color: (0.00, 0.00, 0.00)\n"
        + "Appear At: 2147483647\n"
        + "Disappear At: 2147483647";
    
    assertEquals(expected, test.toString());

    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .build();

    expected =     
        "Name: test\n"
      + "Type: rectangle\n"
      + "Top Left Corner: (100.00, 100.00)\n"
      + "Height: 2.147483647E9\n"   // Double
      + "Width: 2.147483647E9\n"    // Double
      + "Color: (0.00, 0.00, 0.00)\n"
      + "Appear At: 2147483647\n"
      + "Disappear At: 2147483647";
  
    assertEquals(expected, test.toString());

    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .build();

    expected =     
        "Name: test\n"
      + "Type: rectangle\n"
      + "Top Left Corner: (100.00, 100.00)\n"
      + "Height: 2.147483647E9\n"   // Double
      + "Width: 2.147483647E9\n"    // Double
      + "Color: (255.00, 10.00, 100.00)\n"
      + "Appear At: 2147483647\n"
      + "Disappear At: 2147483647";
  
    assertEquals(expected, test.toString());
    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .width(10.5)
                        .build();

    expected =     
        "Name: test\n"
      + "Type: rectangle\n"
      + "Top Left Corner: (100.00, 100.00)\n"
      + "Height: 2.147483647E9\n"   // Double
      + "Width: 10.5\n"    // Double
      + "Color: (255.00, 10.00, 100.00)\n"
      + "Appear At: 2147483647\n"
      + "Disappear At: 2147483647";
  
    assertEquals(expected, test.toString());
    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .width(10.5)
                        .height(20)
                        .build();

    expected =     
        "Name: test\n"
      + "Type: rectangle\n"
      + "Top Left Corner: (100.00, 100.00)\n"
      + "Height: 20.0\n"   // Double
      + "Width: 10.5\n"    // Double
      + "Color: (255.00, 10.00, 100.00)\n"
      + "Appear At: 2147483647\n"
      + "Disappear At: 2147483647";
  
    assertEquals(expected, test.toString());
 
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .width(10.5)
                        .height(20)
                        .appearTime(1)
                        .build();

    expected =     
        "Name: test\n"
      + "Type: rectangle\n"
      + "Top Left Corner: (100.00, 100.00)\n"
      + "Height: 20.0\n"   // Double
      + "Width: 10.5\n"    // Double
      + "Color: (255.00, 10.00, 100.00)\n"
      + "Appear At: 1\n"
      + "Disappear At: 2147483647";
  
    assertEquals(expected, test.toString());
    
    //****************************************//
    // Test Builder with next possible method //
    //****************************************//
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 10, 100))
                        .width(10.5)
                        .height(20)
                        .appearTime(1)
                        .removeTime(100)
                        .build();

    expected =     
        "Name: test\n"
      + "Type: rectangle\n"
      + "Top Left Corner: (100.00, 100.00)\n"
      + "Height: 20.0\n"   // Double
      + "Width: 10.5\n"    // Double
      + "Color: (255.00, 10.00, 100.00)\n"
      + "Appear At: 1\n"
      + "Disappear At: 100";
  
    assertEquals(expected, test.toString());
  }
  
  /**
   * Test the getType method of the Rectangle class.
   */
  @Test
  public void testGetType() {
    // Test default rectangle
    ShapeAnimation test = new Rectangle.Builder("test").build();
    assertEquals(ShapeType.RECTANGLE, test.getType());
    
    // Test rectangle with attributes
    test = new Rectangle.Builder("test")
                        .referencePoint(new Point2D(100, 100))
                        .color(new ShapeColor(255, 255, 255))
                        .appearTime(1)
                        .removeTime(100)
                        .build();
    assertEquals(ShapeType.RECTANGLE, test.getType());
  }
  
  /**
   * Test the cloneShape method of the Rectangle class.
   */
  @Test
  public void testCloneShape() {
    // Create Rectangle with instantiated fields
    ShapeAnimation test = new Rectangle.Builder("test")
                                       .referencePoint(new Point2D(100, 200))
                                       .color(new ShapeColor(255, 100, 50))
                                       .width(10)
                                       .height(20.5)
                                       .appearTime(1)
                                       .removeTime(100)
                                       .visibility(true)
                                       .build();
    
    ShapeAnimation testClone = test.cloneShape();
    
    // Check that all the same attributes but not the same object
    assertEquals(test.getName(), testClone.getName());
    assertEquals(test.getPosition().getX(), testClone.getPosition().getX(), .001);
    assertEquals(test.getPosition().getY(), testClone.getPosition().getY(), .001);
    assertEquals(test.getColor().getR(), testClone.getColor().getR(), .001);
    assertEquals(test.getColor().getG(), testClone.getColor().getG(), .001);
    assertEquals(test.getColor().getB(), testClone.getColor().getB(), .001);
    assertEquals(test.getWidth(), testClone.getWidth(), .001);
    assertEquals(test.getHeight(), testClone.getHeight(), .001);
    assertEquals(test.getAppearTime(), testClone.getAppearTime(), .001);
    assertEquals(test.getRemoveTime(), testClone.getRemoveTime(), .001);
    assertEquals(test.isVisible(), testClone.isVisible());
    assertNotEquals(test, testClone);
    
    // Change attributes of test to see if the change attributes of testClone
    test.moveShape(new Point2D(200, 300));
    test.setColor(new ShapeColor(100, 255, 255));
    test.setWidth(15);
    test.setHeight(25);
    test.setAppearTime(10);
    test.setRemoveTime(200);
    test.hideShape();

    // Ensure attributes are no longer equal (Except Name)
    assertEquals(test.getName(), testClone.getName());
    assertNotEquals(test.getPosition().getX(), testClone.getPosition().getX(), .001);
    assertNotEquals(test.getPosition().getY(), testClone.getPosition().getY(), .001);
    assertNotEquals(test.getColor().getR(), testClone.getColor().getR(), .001);
    assertNotEquals(test.getColor().getG(), testClone.getColor().getG(), .001);
    assertNotEquals(test.getColor().getB(), testClone.getColor().getB(), .001);
    assertNotEquals(test.getWidth(), testClone.getWidth(), .001);
    assertNotEquals(test.getHeight(), testClone.getHeight(), .001);
    assertNotEquals(test.getAppearTime(), testClone.getAppearTime(), .001);
    assertNotEquals(test.getRemoveTime(), testClone.getRemoveTime(), .001);
    assertNotEquals(test.isVisible(), testClone.isVisible());
    assertNotEquals(test, testClone);
  }
}
