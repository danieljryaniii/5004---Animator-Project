package cs5004.animator.tests;

import static org.junit.Assert.assertEquals;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.AnimationControllerImpl;
import cs5004.animator.model.AnimationCanvas;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.animations.AnimationType;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.animations.MoveAnimation;
import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.Rectangle;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * A JUnit Testing Suite for the AnimationControllerImpl class.
 */
public class AnimationControllerImplTest {

  /**
   * Test the animate of the AnimationControllerImpl class. This method is tested by
   * creating a mock model and mock views and ensuring that the data given from the model
   * to the controller and from the controller to the view is what is expected.
   */
  @Test
  public void testConstructor() {
    StringBuilder textOut = new StringBuilder();
    StringBuilder svgOut = new StringBuilder();
    
    AnimatorModelImpl testModel = new MockModel();
    AnimationView testTextView = new MockTextView(textOut, 1);
    AnimationView testSVGView = new MockSVGView(svgOut, 1);
    AnimationController testController = new AnimationControllerImpl(testModel, testTextView);
    
    testController.animate();
    
    // Test that the Text View got the right informations
    String expected = "testR\n"
                    + "testR moved from (200.00, 200.00) to (300.00, 300.00) from t=1 to t=100";
    assertEquals(expected, textOut.toString());
    
    // Test that the SVG View got the right information
    testController = new AnimationControllerImpl(testModel, testSVGView);
    testController.animate();
    
    expected = "testR\n"
             + "testR moved from (200.00, 200.00) to (300.00, 300.00) from t=1 to t=100";
    assertEquals(expected, svgOut.toString());
    
    
  }
  
  
  /*
   * Create a series of Mock Classes with known information to test that the data being
   * passed around is what is expected.
   */
  
  class MockModel extends AnimatorModelImpl {
    
    @Override
    public Map<String, ShapeAnimation> getShapes() {
      ShapeAnimation testR = new Rectangle.Builder("testR").build();
      return new HashMap<String, ShapeAnimation>() {{
          put("Test", testR);
        }};
    }
      
    @Override
    public Map<Integer, ArrayList<IAnimation>> getAnimations() {
      ShapeAnimation testR = new Rectangle.Builder("testR").build();
      IAnimation testA = new MoveAnimation(
          testR, AnimationType.MOVE, new Point2D(200, 200), new Point2D(300, 300),
          new ShapeColor(255, 255, 255), new ShapeColor(255, 255, 255),
          1, 100, 50, 50, 100, 100
          );
      return new HashMap<Integer, ArrayList<IAnimation>>() {{
          put(1, new ArrayList<IAnimation>() {{
              add(testA);
            }
          }
              );
        }};
    }
    
    @Override
    public SortedSet<Integer> getTimes() {
      return new TreeSet<Integer>() {{
          add(1);
        }};
    }
  }
  
  class MockTextView extends TextView {

    private Appendable out;
    
    public MockTextView(Appendable out, int speed) {
      super(out, speed);
      this.out = out;
    }
    
    @Override
    public void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
        AnimationCanvas canvas) {
      try {
        for (String name: shapes.keySet()) {
          this.out.append(name);
        }
        
        out.append("\n");
        
        for (IAnimation each: animations) {
          this.out.append(each.toString());          
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  class MockSVGView extends SVGView {

    private Appendable out;
    
    public MockSVGView(Appendable out, int speed) {
      super(out, speed);
      this.out = out;
    }
    
    @Override
    public void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
        AnimationCanvas canvas) {
      try {
        for (String name: shapes.keySet()) {
          this.out.append(name);
        }
        
        out.append("\n");
        
        for (IAnimation each: animations) {
          this.out.append(each.toString());          
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
