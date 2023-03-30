package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.Rectangle;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;
import org.junit.Test;

/**
 * JUnit Testing Suite for the AbstractShapeAnimation class. This suite
 * focuses mainly on the Builder Pattern of the Abstract Class. Refer to implementing
 * concrete classes for testing over other methods.
 */
public class AbstractShapeAnimationTest {
  
  /**
   * Test the Builder Pattern of AbstractAnimation class.
   */
  @Test
  public void testBuilder() {
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
    
    
    // Test Builder with next possible method
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
    
    
    // Test Builder with next possible method
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
    
    
    // Test Builder with next possible method
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
   
    
    // Test Builder with next possible method
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
    
 
    // Test Builder with next possible method
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
    
    
    // Test Builder with next possible method
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
 
     
    // Test Builder with next possible method
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
}