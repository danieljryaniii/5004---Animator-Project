CS5004            +
Summer 2022       +
Daniel Ryan       +
Easy Animator     +
+++++++++++++++++++

+++++++++ EASY ANIMATOR : JAVA APPLICATION +++++++++

---- Running the Program: ----
- EasyAnimator.jar : The jar file that will run the program

** Running from CLI ** : java -cp EasyAnimator.jar cs5004.animator.easyanimator.AnimatorMain <-in arg> <-view arg> <[-speed arg]> <[-out arg]>

The program must be given an -in and a -view in order to run (other parameters within [] above are optional). If arguments cannot be read
correctly or are invalid, an Option Pane will be presented to the client with a message and remediation option. Java Swing GUI was used
in order to allow for real-time/run-time troubleshooting. 


---- Overview: ----
The easy animator application allows for a client to provide text based shape animations (specifically for Rectangle/Ellipse)
and render animations in Text, SVG or Visual (with or without playback functionality) form. The program is designed using
the Model View Controller architectural pattern. The program should be launched using the command line interface - please
refer to the "Running the Program" section for further details. After launching the program, any additional user interaction
is handled/done through the use of Java Swing Graphical User Interface.


---- Model Design: ----
The Model (AnimatorModelImpl) for the Easy Animator implements a simple AnimatorModel interface. This interface defines methods to add shapes,
animations and a canvas to the model. The model interface does not enforce the use of a specific data structure. Therefore,
getters (where necessary) are implemented in the concrete implementing class. 

  The implementation of the AnimatorModel (AnimatorModelImpl) used for this Easy Animator utilizes various data structures to
  store data. 

  DATA STRUCTURES:

  Shapes: The model must be able to store ShapeAnimations (shapes). This Easy Animator utilizes a Linked Hash Map to store the shapes.
          The shape names are utilized as the key and objects are stored as values. This structure allows for fast access, fast appending
          and fast duplicate checking. Additionally, because the Map is a Linked Hash Map, it stores the shapes in the order in which they
          are received (an important detail) and ensures iterators will traverse the map in that order.

  Animations: The model must be able to store Animations (various animations). This Easy Animator utilizes a Hash Map to store the animations.
              The start time of the animations are utilized as the key and the values are Array Lists of IAnimation objects. This structure
              allows for the simple grouping of animations based on their start times. Additionally, this structure allows for fast access (on average), 
              and fast appending. Because a Hash Map does not maintain any order, the start times of each animation are stored in a separate data structure
              (see below) which utilizes a Tree Set to store and oder the start times in ascending order. This allows for the Animation Hash Map to
              be iterated over in order.
   
  Times: This Easy Animator utilizes a Sorted Tree Set to store all the animation start times. This allows for easy iteration over the Animations
         Hash Map which does not maintain an iterator order. 

  The above data structures were chosen for their ability to organize data in a simple way while allowing for quick access and appending. These data
  structures handle even large animations with ease. Additionally, because the Tree Set of times is kept alongside the Hash Map of animations, the
  animations never have to be sorted - as the items can be accessed in order without the need for the sorting step. This saves a significant amount
  of time as the animations can be iterated over and "sorted" in O(n) time (where n is the number of animations in the Hash Map) - whereas an Array
  or Array List would require (at best) an O(n log n) sort and O(n) iteration.

Refer to the "Other Design/Classes" section for additional information regarding Shape/Animation/etc. classes utilized within the model.


---- Views Design: ---- 
The Easy Animator must support Text, SVG (XML), Visual (Swing) and Playback Visual (Swing) views. Because the program should be able to work with any
option based on client requests, all of the views implement an AnimationView interface. I order to allow for a single controller and for all views to
implement the same AnimationView interface, each view must implement a showAnimation method that takes the controller as a parameter, as well as a
showAnimation method that does not take the controller as a parameter. If the view does not need access to the controller (Text, SVG, Visual) then it
will throw an UnsupportedOperationException for the version of the method that takes the controller. Similarly, the Playback view, which needs access
to the controller, will throw an UnsupportedOperationException for the version of the method that does not take the controller as an argument.

   VIEWS:

   Text (TextView): The text based view does not delegate to the toString methods of the ShapeAnimation and IAnimation objects received from the controller. Instead,
         the text view implements its own logic. Although delegation allows for reduced code duplication (in this implementations current state), the
         delegation tightly couples the view to the ShapeAnimation and IAnimation classes. Since the text view implements its own logic, it can be updated,
         scaled and maintained external of changes that happen to the ShapeAnimation and IAnimation classes.

   SVG (SVGView):  The SVG based view does not delegate to the methods of the ShapeAnimation and IAnimation objects received from the controller. Instead,
         the SVG view implements its own logic. Although delegation could allow for less code in the SVGView, the delegation tightly couples the view
	 to the ShapeAnimation and IAnimation classes. Since the text view implements its own logic, it can be updated, scaled and maintained external
          of changes that happen to the ShapeAnimation and IAnimation classes. Additionally, ShapeAnimations and IAnimation will not be fired to implement
          toSVG methods that they may not otherwise require. 

   Visual (VisualView): The Visual view utilizes Java Swing as a Graphical User Interface. The view presents the animation with the help of a separate AnimationPanel class
           which extends the Swing JPanel class. The AnimationPanel is where animations will be shown. Additionally, the animation speed and clock is handled
           by an AnimationPlayer class which maintains the time of the animation. The visual view will only play the animation once, after which it will end 
           the animation sequence and timer; however, the animation will remain on screen until the Swing Frame/Window is exited.

   Playback (PlaybackView): The Playback view is similar to and builds upon the Visual View. The playback view gives the client the ability to control aspects of the visual
             view, such as speed (increase/decrease), start/pause, restart and looping. The view starts in a waiting state and once the client starts the program
             the animation will begin. The client can then select various options and see immediate results. The PlaybackView leverages the same AnimationPanel and
             AnimationPlayer classes that are used by the VisualView.


---- Controller Design: ---- 
For this Easy Animator implementation, the controller's main job is to get the data from the model (copy it so that the view cannot mutate the model) and pass it
on to the view. Each view will then take the data and iterate over it / present it to the user/client based upon the specific view implementation. The controller
does not pass data to the views at time intervals as this would require multiple controllers - some of which implement action listeners. Because the Text and SVG
views do not present animations at a point-in-time (i.e. they do not show intermediate states of animations) they should only be receiving the data once and not
on time intervals. Because of the aforementioned, in order to keep a single controller (as stated in then project guide) the controller simply passes
data between the model and views.


---- Other Design/Classes: ---- 
SHAPES: All shapes implement an ShapeAnimation interface. Additionally, each of the shapes inherits from an AbstractShapeAnimation class which implements a
        Builder Design pattern to simplify the creation of shape objects and reduce the number of potential constructors needed for shapes.

ANIMATIONS: All animation types  (move, scale, color change) implement an IAnimation interface. Additionally, each of the animations inherits from an AbstractAnimation
            class which contains all of the similar functionalities. By creating animation objects, each animation can be stored with all its respective data.

OTHER: Other classes such as ShapeColor, Point2D, AnimationCanvas, etc. were created to store information related to certain shape attributes or animation objects.





