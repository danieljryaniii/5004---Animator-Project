package cs5004.animator.view;

import cs5004.animator.model.animations.IAnimation;
import cs5004.animator.model.shapes.Point2D;
import cs5004.animator.model.shapes.ShapeAnimation;
import cs5004.animator.model.shapes.ShapeColor;
import cs5004.animator.model.shapes.ShapeType;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * This class represents the Panel or drawing area for an
 * Animation. 
 */
public class AnimationPanel extends JPanel {
    
  private Map<String, ShapeAnimation> shapes;
  private Collection<IAnimation> animations;
  private AnimationPlayer player;
  private int xShift;
  private int yShift;

  /**
   * Construct an AnimationPanel for shape animations given an
   * {@link AnimationPlayer} object to control the animation.
   * @param player and {@link AnimationPlayer} object to control the animation
   */
  public AnimationPanel(AnimationPlayer player) {
    super(true);
    
    // Store the Data
    this.shapes = new LinkedHashMap<>();
    this.animations = new ArrayList<>();
    
    // Set Shifts for animations
    this.xShift = 0;
    this.yShift = 0;
    
    // Store view and player component
    this.player = player;   
        
    // Initialize the Panel
    this.setBackground(Color.WHITE);
    this.setLocation(0, 0);
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
                  
    //************************************//
    /* Update shapes based on animations */
    //************************************//
    for (IAnimation animation: animations) {
        
      /*
       * The Below two tests are meant to help with performance by
       * ignoring shapes / sections of the data that should not be
       * animated - based on the current time of the program
       */
      
      // Short circut out if not animated yet -- break whole loop
      if (   animation.getStartTime() > player.getCurrentTick() 
          || animation.getEndTime() < player.getCurrentTick()) {
        continue; // Shape and nothing after should not be animated
      }        
     
      /*
       * Perform animations that should be animated at the current point in
       * time. 
       */
      switch (animation.getType()) {
        case MOVE:
          double tweenX = this.player.tween(animation.getStartTime(), 
                                            animation.getEndTime(), 
                                            animation.getStartPosition().getX(), 
                                            animation.getEndPosition().getX());
          double tweenY = this.player.tween(animation.getStartTime(), 
                                            animation.getEndTime(), 
                                            animation.getStartPosition().getY(), 
                                            animation.getEndPosition().getY());          
          // Move the Shape to current position
          shapes.get(animation.getShape().getName())
                              .moveShape(new Point2D(tweenX, tweenY));
          break;
          
        case SCALE:
          double tweenHeight = this.player.tween(animation.getStartTime(), 
                                                 animation.getEndTime(), 
                                                 animation.getStartHeight(), 
                                                 animation.getEndHeight());
          double tweenWidth = this.player.tween(animation.getStartTime(), 
                                                animation.getEndTime(), 
                                                animation.getStartWidth(), 
                                                animation.getEndWidth());          
          // Scale the Shape to current size
          shapes.get(animation.getShape().getName())
                               .scale(tweenWidth, tweenHeight);
          break;
          
        case COLOR_CHANGE:
          double tweenR = this.player.tween(animation.getStartTime(), 
                                            animation.getEndTime(), 
                                            animation.getStartColor().getR(), 
                                            animation.getEndColor().getR());
          double tweenG = this.player.tween(animation.getStartTime(), 
                                            animation.getEndTime(), 
                                            animation.getStartColor().getG(), 
                                            animation.getEndColor().getG());
          double tweenB = this.player.tween(animation.getStartTime(), 
                                            animation.getEndTime(), 
                                            animation.getStartColor().getB(), 
                                            animation.getEndColor().getB());
          // Change Color of the Shape to current color
          shapes.get(animation.getShape().getName())
                              .setColor(new ShapeColor(tweenR, tweenG, tweenB));
          break;
        
        default:
          break;
      }
    }
    
    //*******************//
    /* PRINT ALL SHAPES */
    //*******************//
    for (ShapeAnimation shape: shapes.values()) {
      if (shape.getRemoveTime() > this.player.getEndTick()) {
        this.player.setEndTick(shape.getRemoveTime());
        
      }
      if (shape.getAppearTime() > player.getCurrentTick()) { // Shape should not be visible
        continue;
      }
      // Set Shape Color
      g.setColor(new Color((int)shape.getColor().getR(),
                           (int)shape.getColor().getG(),
                           (int)shape.getColor().getB()));
      
      if (shape.getType() == ShapeType.RECTANGLE) {
        g.fillRect((int)shape.getPosition().getX() + xShift, 
                   (int)shape.getPosition().getY() + yShift, 
                   (int)shape.getWidth(), 
                   (int)shape.getHeight());
      }
      else if (shape.getType() == ShapeType.ELLIPSE) {
        g.fillOval((int)shape.getPosition().getX() + xShift, 
                   (int)shape.getPosition().getY() + yShift, 
                   (int)shape.getWidth(), 
                   (int)shape.getHeight());       
      }
    }

  }
  
  /**
   * Set the shapes to be animated in this AnimationPanel.
   * @param shapes a Map of {@link String}s and {@link ShapeAnimations}
   */
  public void setShapes(Map<String, ShapeAnimation> shapes) {
    this.shapes = shapes;
  }
  
  /**
   * Set the animations to be animated in this AnimationPanel.
   * @param animations a Collection of {@link IAnimation} objects
   */
  public void setAnimations(Collection<IAnimation> animations) {
    this.animations = animations;
  }
  
  /**
   * Set the coordinate shifts for this Panel based on the provided
   * starting position of the canvas. JPanel's upper left corner represents
   * (0, 0); therefore, if a given canvas does not have an upper left point
   * of (0, 0) - the x and y coordinates of the {@link ShapeAnimation} objects
   * to be animated will be shifted accordingly.
   * @param xShift the amount by which to shift the x-coordinate of animations
   * @param yShift the amount by which to shift the y-coordinate of animations
   */
  public void setShifts(double xShift, double yShift) {
    this.xShift = (int)xShift;
    this.yShift = (int)yShift;
  }
}
