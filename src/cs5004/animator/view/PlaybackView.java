package cs5004.animator.view;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.AnimationCanvas;
import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.shapes.ShapeAnimation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * This PlaybackView class represents the Frame for an animation
 * program. The VisualView utilizes Java Swing as the GUI
 * for animations.
 */
public class PlaybackView extends JFrame implements AnimationView, ActionListener {

  private AnimationPanel animationPanel;
  private JPanel buttonPanel;
  private JScrollPane scrollPane;
  private AnimationPlayer player;
  private JButton stop;
  private JButton start;
  private JButton upSpeed;
  private JButton downSpeed;
  private JButton reset;
  private JButton loop;
  private JButton quit;
  private AnimationController controller;
  
  /**
   * Construct a VisualView using Java Swing.
   * @param speed the speed of this VisualView's animation. Speed is defined
   *     in terms of the number of ticks per second. Therefore, if the speed
   *     is 1 - the animation will progress at 1 tick-per-second/ 1 tick per
   *     1000ms. 
   */
  public PlaybackView(int speed) {
    super("Shape Animation");
    
    // Set up the Frame
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLocation(0, 0);

    // Add in the AnimationPlayer to control time
    this.player = new AnimationPlayer(speed, this);
    this.controller = null;
    
    // Set up the Animation Panel
    this.animationPanel = new AnimationPanel(this.player);
    animationPanel.setLayout(new BorderLayout());
    
    // Add Button Panel
    this.buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.DARK_GRAY);
    
    // Add a Start Button
    start = new JButton("Start/Resume");
    this.start.setName("Start");
    buttonPanel.add(start);
    this.start.addActionListener(this);
    
    // Create and Add Buttons //
    // Add a Stop Button
    stop = new JButton("Pause");
    this.stop.setName("Stop");
    buttonPanel.add(stop);
    this.stop.addActionListener(this);
    
    // Add an Increase Speed Button
    upSpeed = new JButton("Speed +10 : Speed=" + player.getSpeed());
    this.upSpeed.setName("IncreaseSpeed");
    buttonPanel.add(upSpeed);
    this.upSpeed.addActionListener(this);
  
    // Add an Decrease Speed Button
    downSpeed = new JButton("Speed -10 : Speed=" + player.getSpeed());
    this.downSpeed.setName("DecreaseSpeed");
    buttonPanel.add(downSpeed);
    this.downSpeed.addActionListener(this);

    // Add a Reset Button
    reset = new JButton("Reset");
    this.reset.setName("Reset");
    buttonPanel.add(reset);
    this.reset.addActionListener(this);
    
    // Add a Loop Button
    loop = new JButton("Loop: " + player.getPlaybackFlag());
    this.loop.setName("Loop");
    buttonPanel.add(loop);
    this.loop.addActionListener(this);    

    
    // Add a Quit Button
    quit = new JButton("Quit");
    quit.setName("Quit");
    buttonPanel.add(quit, BorderLayout.SOUTH);
    quit.addActionListener(this);
    
    // Set-up scroll pane
    scrollPane = new JScrollPane(animationPanel);
        
    // Add all the necessary components
    this.add(animationPanel);
    animationPanel.add(buttonPanel, BorderLayout.SOUTH);

    this.pack();
  }

  @Override
  public void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
      AnimationCanvas canvas) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("The PlaybackView requires the controller.");
  }
  
  @Override
  public void showAnimation(Map<String, ShapeAnimation> shapes, Collection<IAnimation> animations,
      AnimationCanvas canvas, AnimationController controller) {

    // Store and give controller to player
    this.controller = controller;
    player.addController(controller);
    
    // Conform dimensions to given canvas
    this.setSize(new Dimension(canvas.getWidth() + 2, canvas.getHeight() + 180));
    this.scrollPane.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
    
    // Set up the animation panel
    animationPanel.setShapes(shapes);
    animationPanel.setAnimations(animations);
    animationPanel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight() + 150)); 
    animationPanel.setShifts(0 - canvas.getTopLeft().getX(), 0 - canvas.getTopLeft().getY());
    
    // Set the size of the button panel
    buttonPanel.setPreferredSize(new Dimension(canvas.getWidth(), 150)); 

    this.setVisible(true);
  }
    
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.stop) {
      player.stopTimer();
    } else if (e.getSource() == this.quit) {
      System.exit(0);
    } else if (e.getSource() == this.start) {
      player.startTimer();
    } else if (e.getSource() == this.upSpeed) {
      player.increaseSpeed();
      upSpeed.setText("Speed +10 : Speed=" + player.getSpeed());
      downSpeed.setText("Speed -10 : Speed=" + player.getSpeed());
    } else if (e.getSource() == this.downSpeed) {
      player.decreaseSpeed();
      upSpeed.setText("Speed +10 : Speed=" + player.getSpeed());
      downSpeed.setText("Speed -10 : Speed=" + player.getSpeed());
    } else if (e.getSource() == this.reset) {
      player.resetTimer();
      controller.animate();
    } else if (e.getSource() == this.loop) {
      player.togglePlaybackFlag();
      loop.setText("Loop: " + player.getPlaybackFlag());
    } else if (e.getSource() == this.reset) {
      controller.animate();
    }
  }
}
