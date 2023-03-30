package cs5004.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

import cs5004.animator.controller.AnimationController;

/**
 * This class works as a Player/Handler for an Animation.
 * This class maintains the time of the Animation, allowing
 * for pause, resume and restart actions. Additionally, this 
 * contains animation helpers to tween attributes of an
 * animation. 
 */
public class AnimationPlayer implements ActionListener {

  private Timer timer;
  private int delay; // Delay in milliseconds
  private int speed;
  private int currentTick;
  private int endTick;
  private boolean playbackFlag;
  private JFrame view;
  private AnimationController controller;
  
  /**
   * Construct an AnimationPlayer to control the actions of a Visual
   * Animation based on a timer.   
   * @param speed the speed at which this animation should be played.
   *     speed is defined as the number of "ticks" per second
   * @param view the view of this MVC application
   */
  public AnimationPlayer(int speed, JFrame view) {
    this.delay = 1000 / speed;
    this.speed = speed;
    this.timer = new Timer(delay, this);
    this.currentTick = 1;
    this.endTick = 1;
    this.view = view;
    this.playbackFlag = false;
    this.controller = null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.currentTick > this.endTick) {
      this.stopTimer();
    }
    
    if (this.timer.isRunning()) {
      view.repaint();
      currentTick++;
    }
    
    // Loop If Requested
    if (this.currentTick > endTick
        && !this.timer.isRunning()
        && playbackFlag) {
      this.resetTimer(); 
      this.controller.animate();
      
    }
  }
  
  /**
   * Return the current "tick"/time of this AnimationPlayer.
   * @return an integer representing the current "tick"/time
   *     of this AnimationPlayer
   */
  public int getCurrentTick() {
    return this.currentTick;
  }
  
  /**
   * Return the value of the last "tick"/time of this AnimationPlayer.
   * @return an integer representing the current "tick"/time of this
   *     AnimationPlayer
   */
  public int getEndTick() {
    return this.endTick;
  }
  
  /**
   * Return the speed of this animation in ticks per second.
   * @return an integer representing the number of ticks per
   *     second in this animation (speed)
   */
  public int getSpeed() {
    return this.speed;
  }
  
  /**
   * Set the value of the last "tick"/time of this AnimationPlayer.
   */
  public void setEndTick(int endTick) {
    this.endTick = endTick;
  }
  
  /**
   * Toggle/Set the playback/loop flag, indicating whether
   * or not the animation is going to loop.
   */
  public void togglePlaybackFlag() {
    this.playbackFlag = !playbackFlag;
  }
  
  /**
   * Get the current value of the playback/loop flag. 
   * @return true if this animation is set to loop or false
   *     if the animation is not going to loop (default)
   */
  public boolean getPlaybackFlag() {
    return this.playbackFlag;
  }
  
  /**
   * Add the controller to this AnimationPlayer to allow for the
   * animate method of the controller to be invoked upon loop.
   * @param controller an {@link AnimationController} object
   */
  public void addController(AnimationController controller) {
    this.controller = controller;
  }
  
  /**
   * Increase the speed of the program by +10 ticks per second. If the speed
   * is equal to 1 then it will be increased by +9 ticks per second.
   */
  public void increaseSpeed() {
    if (this.speed == 1) {
      this.speed += 9;
    } else {
      this.speed += 10;
    }
    this.delay = 1000 / speed;
    this.timer.setDelay(this.delay);
  }
  
  /**
   * Decrease the speed of the program by -10 ticks per second.
   * If the reduction in speed would lead to ticks per second
   * becoming zero, the speed will be set to 1.
   */
  public void decreaseSpeed() {
    if (speed <= 10) {
      this.speed = 1;
    } else {
      this.speed -= 10;
    }
    this.delay = 1000 / speed; 
    this.timer.setDelay(this.delay);      
  }

  /**
   * Start this AnimationPlayer timer.
   */
  public void startTimer() {
    this.timer.start();
  }
  
  /**
   * Stop/Pause this AnimationPlayer timer.
   */
  public void stopTimer() {
    this.timer.stop();
  }
  
  /**
   * Reset this AnimationPlayer timer and the
   * currentTick of this AnimationPlayer.
   */
  public void resetTimer() {
    this.timer.restart();
    this.currentTick = 1;
  }
  
  /**
   * "Tween" a given numeric attribute based on a given start and end time as well
   * as a given start and end state. This function finds the current value for the
   * given attribute based on the current time of this AnimationPlayer.
   * @param startTime the start time of the attribute to be tweened
   * @param endTime the end time of the attribute to be tweened
   * @param startAttribute the starting state of the attribute to be tweened
   * @param endAttribute the ending state of the attribute to be tweened
   * @return the given attributes current "location"/state at a point in time / based
   *     on this AnimationPlayer's current tick
   */
  public double tween(double startTime, double endTime, 
                      double startAttribute, double endAttribute) {
    return startAttribute * ((endTime - this.currentTick) / (endTime - startTime))
         + endAttribute * ((this.currentTick - startTime) / (endTime - startTime));
  }
}
