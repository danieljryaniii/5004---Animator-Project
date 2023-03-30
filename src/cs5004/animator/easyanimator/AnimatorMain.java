package cs5004.animator.easyanimator;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.AnimationControllerImpl;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.util.AnimationReader;
import cs5004.animator.view.AnimationView;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * This class serves as the driver for the EasyAnimation Program.
 */
public class AnimatorMain {
  
  /**
   * Main driver for an Easy Animator Java Application.
   * @param args Command Line Arguments for the easy animator program
   *     Valid arguments are:
   *     -in filename
   *     -out filename
   *     -view viewname
   *     -speed speedInteger
   */
  public static void main(String[] args) {
    
    //******************************************************//
    /*               PARSE THE CLI ARGUMENTS               */
    //******************************************************//
    /*
     * The below section of code parses and validates the given
     * command line arguments. Unless there is no ability to
     * recover, the program will attempt to get valid input
     * from the user instead of crashing. 
     */
        
    /* Create an Arguments HashMap */
    Map<String, String> arguments = new HashMap<String, String>() {{
        put("-in", null);
        put("-out", null);
        put("-view", null);
        put("-speed", null);
      }};
    
    /*
     * Parse arguments and throw errors where necessary
     */
    if (args.length % 2 == 1) {
      // Catch Invalid Number of Arguments
      JOptionPane.showMessageDialog(null, "Invalid number of arguments given. "
                                        + "\n\nREQUIRED ARGUMENTS:"
                                        + "\n -in <filename>"
                                        + "\n::::: valid formats: '.txt' | '.svg' :::::\n"
                                        + "\n -view <view>"
                                        + "\n::::: valid views: 'text' | 'svg' | 'visual' |"
                                            + " 'playback' :::::\n"
                                        + "\n\nOPTIONAL ARGUMENTS:"
                                        + "\n -speed <integer>"
                                        + "\n::::: valid speed: positive integer <= 1000 :::::\n"
                                        + "\n -out <filename>"
                                        + "\n::::: valid formats: '.txt' | '.svg' :::::\n");
      System.exit(0); // End Program
    }
      
    // Map Command Line Arguments to their options 
    for (int option = 0; option < args.length - 1; option += 2) {
      
      // Interrupt / End Program if Arguments are not correctly received
      if (!(arguments.containsKey(args[option]))) {
        // Catch Invalid Number of Arguments
        JOptionPane.showMessageDialog(null, "Invalid Argument(s) given. "
                                        + "'-in' and '-view' are required."
                                        + "\n\nREQUIRED ARGUMENTS:"
                                        + "\n -in <filename>"
                                        + "\n::::: valid formats: '.txt' | '.svg' :::::\n"
                                        + "\n -view <view>"
                                        + "\n::::: valid views: 'text' | 'svg' | 'visual' |"
                                            + " 'playback' :::::\n"
                                        + "\n\nOPTIONAL ARGUMENTS:"
                                        + "\n -speed <integer>"
                                        + "\n::::: valid speed: positive integer <= 1000 :::::\n"
                                        + "\n -out <filename>"
                                        + "\n::::: valid formats: '.txt' | '.svg' :::::\n");
        System.exit(0); // End Program        
      }
      
      // Command is valid and has not been seen
      else if (arguments.containsKey(args[option]) && arguments.get(args[option]) == null) {
        arguments.put(args[option], args[option + 1]);
      }

      // Command is valid and has been been seen (Ask user for preference)
      else if (arguments.containsKey(args[option]) && arguments.get(args[option]) != null) {
        int answer = JOptionPane.showConfirmDialog(null, "A value for '" + args[option] 
                                               + "' has already been provided."
                                               + "\nCurrent Value: " + arguments.get(args[option])
                                               + "\n\nWould you like to update it to:"
                                               + "\nNew value: " + args[option + 1]);
        if (answer == 0) {
          arguments.put(args[option], args[option + 1]);
        }
      }      
    }
    
    // Loop to ensure that a valid input file is received from the user
    while (arguments.get("-in") == null || !AnimatorMain.validFile(arguments.get("-in"))) {
      try {
        String inAnswer = JOptionPane.showInputDialog("An input source was not provied "
                                                    + "or is invalid. "
            + "\n\nREQUIRED ARGUMENTS:"
            + "\n -in <filename>"
            + "\n::::: valid formats: '.txt' | '.svg' :::::\n"
            + "\nEnter a valid input source (excluding '-in'):").trim().toLowerCase();
        
        if (AnimatorMain.validFile(inAnswer)) {
          arguments.put("-in", inAnswer);
          break;
        }
      } catch (NullPointerException npe) { // User pressed 'cancel' or 'x' and exited
        System.exit(0);
      }
    }
    
    // Loop to ensure that a valid view is received from the user
    while (arguments.get("-view") == null || !AnimatorMain.validView(arguments.get("-view"))) {
      try {
        String viewAnswer = JOptionPane.showInputDialog("An view was not provied or is invalid. "
            + "\n\nREQUIRED ARGUMENTS:"
            + "\n -view <view>"
            + "\n::::: valid views: 'text' | 'svg' | 'visual' | 'playback' :::::\n"
            + "\nEnter valid view to use (excluding '-view'):").trim().toLowerCase();
        
        if (AnimatorMain.validView(viewAnswer)) {
          arguments.put("-view", viewAnswer);
          break;
        }
      } catch (NullPointerException npe) { // User pressed 'cancel' or 'x' and exited
        System.exit(0);
      }
    }
    
    // If a speed was given then loop through until a valid speed is received
    boolean speedFlag = false;
    while (arguments.get("-speed") != null && !speedFlag) {
      try {
        int speed = Integer.parseInt(arguments.get("-speed"));
        if (speed > 0 && speed <= 1000) {
          speedFlag = true;
          break;
        }
      } catch (NumberFormatException nfe) {
        // Do Nothing --> Get new Input below
      }
      
      try {        
        arguments.put("-speed", JOptionPane.showInputDialog("The provided speed is invalid. "
            + "\n\nOPTIONAL ARGUMENTS:"
            + "\n -speed <integer>"
            + "\n::::: valid speed: positive integer <= 1000 :::::\n"
            + "\nEnter valid speed to use (excluding '-speed'):").trim());
      } catch (NullPointerException npe) { // User pressed 'cancel' or 'x' and exited
        System.exit(0);
      }
    }
    
    // If an output was given then loop through until a valid output is received
    while (arguments.get("-out") != null && !AnimatorMain.validFile(arguments.get("-out"))) {
      try {
        String outAnswer = JOptionPane.showInputDialog("The output file received is invalid. "
            + "\n\nOPTIONAL ARGUMENTS:"
            + "\n -out <filename>"
            + "\n::::: valid formats: '.txt' | '.svg' :::::\n"
            + "\nEnter valid output file to use (excluding '-out'):").trim().toLowerCase();
        
        if (AnimatorMain.validFile(outAnswer)) {
          arguments.put("-out", outAnswer);
          break;
        }
      } catch (NullPointerException npe) { // User pressed 'cancel' or 'x' and exited
        System.exit(0);
      }
    }
    
    //******************************************************//
    /*           ORGANIZE I/O ELEMENTS AND BUILD MVC       */
    //******************************************************//
    
    boolean inFlag = false;
    boolean outFlag = false;
    int speed;
    
    // All of the below variables initialized to null due to try/catch scoping
    FileReader in = null; 
    FileWriter out = null; 
    AnimationView view = null;
    
    
    // **** INPUT FILE CHECK **** // 
    while (!inFlag) {
      // Check if input file can be found
      try {
        in = new FileReader(arguments.get("-in"));
        inFlag = true; // File found
        break;
      
      } catch (FileNotFoundException fnfe) {
        // File Not Found - try to get new input file
        try {
          String newIn = JOptionPane.showInputDialog("Input file provided was not found. \n"
              + "Please enter a valid file name or file path\n"
              + "\n::::: valid formats: '.txt' | '.svg' :::::\n"
              + "\nEnter a valid file name or file path:").trim().toLowerCase();
          
          // Replace existing input file if new input is valid. Next loop will check if found
          if (AnimatorMain.validFile(newIn)) {
            arguments.put("-in", newIn);
          }
        } catch (NullPointerException npe) { // User pressed 'cancel' or 'x' and exited
          System.exit(0);
        }
        inFlag = false;
      }
    }
    
    // **** OUTPUT FILE CHECK **** // 
    while (!outFlag) {
      // Deal With Output (Optional Arg)
      if (arguments.get("-out") == null) {
        break; // Keep outFlag as false so view knows
        
      } else {
        // Check if File is Found and Designate it as out
        try {
          out = new FileWriter(arguments.get("-out"));
          outFlag = true;
        } catch (FileNotFoundException fne) {
          // Try to get new output file
          try {
            String outAnswer = JOptionPane.showInputDialog("Output file provided was not found. \n"
                + "Please enter a valid file name or file path\n"
                + "\n::::: valid formats: '.txt' | '.svg' :::::\n"
                + "\nEnter a valid file name or file path:").trim().toLowerCase();
            
            if (AnimatorMain.validFile(outAnswer)) {
              arguments.put("-out", outAnswer);
            }
          } catch (NullPointerException npe) { // User pressed 'cancel' or 'x' and exited
            System.exit(0);
          }
        } catch (IOException ioe) {
          System.exit(0);
          outFlag = false;
        }
      }
    }
    
    // **** SPEED CHECK **** // 
    if (arguments.get("-speed") == null) {
      speed = 1;
    } else {
      speed = Integer.parseInt(arguments.get("-speed")); // Pre-validated
    }
    
        
    // GIVE MODEL AND VIEW TO CONTROLLER
    AnimatorModelImpl model = (AnimatorModelImpl) AnimationReader
                                                 .parseFile(in, new AnimatorModelImpl.Builder());
    
    // CREATE THE VIEW
    switch (arguments.get("-view")) {
      case "text":
        if (!outFlag) {
          view = new TextView(System.out, speed);
        } else {
          view = new TextView(out, speed);
        }
        break;
      case "svg":
        if (!outFlag) {
          view = new SVGView(System.out, speed);
        } else {
          view = new SVGView(out, speed);
        }
        break;
      case "visual":
        view = new VisualView(speed);
        break;
      case "playback":
        view = new PlaybackView(speed);
        break;
      default:
        JOptionPane.showMessageDialog(null, "The view could not be processed. Please try again.");
        System.exit(0); // End Program
        break;
    }  
    
    AnimationController controller = new AnimationControllerImpl(model, view);
    
    controller.animate();
    
    // CLOSE OUTPUT FILE
    if (outFlag) {
      try {
        out.close();
      } catch (IOException e) {
        throw new IllegalStateException("Error encountered when closing the file.");
      }
    }
  }
  
  /*
   * Helper method to check if the given view is valid. 
   */
  private static boolean validView(String view) {
    return (view.equalsIgnoreCase("text")
         || view.equalsIgnoreCase("svg")
         || view.equalsIgnoreCase("visual")
         || view.equalsIgnoreCase("playback"));
  }
  
  /*
   * Helper method to ensure the filename given has a valid extension.
   */
  private static boolean validFile(String filename) {
    return (filename.toLowerCase().endsWith(".svg")
         || filename.toLowerCase().endsWith(".txt"));
  }
}
