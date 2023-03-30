package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.AnimationCanvas;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.shapes.ShapeAnimation;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * This VisualView class represents the Frame for an animation
 * program. The VisualView utilizes Java Swing as the GUI
 * for animations.
 */
public class VisualView extends JFrame implements AnimationView {

  private AnimationPanel animationPanel;
  private JScrollPane scrollPane;
  private AnimationPlayer player;
  
  /**
   * Construct a VisualView using Java Swing.
   * @param speed the speed of this VisualView's animation. Speed is defined
   *     in terms of the number of ticks per second. Therefore, if the speed
   *     is 1 - the animation will progress at 1 tick-per-second/ 1 tick per
   *     1000ms. 
   */
  public VisualView(int speed) {
    super("Shape Animation");
    
    // Set up the Frame
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLocation(0, 0);

    // Add in the AnimationPlayer to control time
    this.player = new AnimationPlayer(speed, this);
    
    // Set up the Animation Panel
    this.animationPanel = new AnimationPanel(this.player);
    
    // Set-up scroll pane
    scrollPane = new JScrollPane(animationPanel);
        
    // Add all the necessary components
    this.add(animationPanel);

    this.pack();
  }

  @Override
  public void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
      AnimationCanvas canvas) {

    // Conform sizes to the given canvas
    this.setSize(new Dimension(canvas.getWidth() + 2, canvas.getHeight() + 30));
    this.scrollPane.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
    
    animationPanel.setShapes(shapes);
    animationPanel.setAnimations(animations);
    animationPanel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));    
    animationPanel.setShifts(0 - canvas.getTopLeft().getX(), 0 - canvas.getTopLeft().getY());

    this.setVisible(true);
    this.player.startTimer();
    
  }

  @Override
  public void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
      AnimationCanvas canvas, AnimationController controller) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("The VisualView does not require the controller.");
    
  }
}
