import java.awt.*;
import java.util.Formatter;

/** <b> Version Information: </b>
  * <p>
  * <b>Author</b> Chua Hock-Chuan, Esther Yoo
  * <b>Version #</b> 1
  * <b>Date</b> 05.02.16
  * <b>Time Spent</b> 1 hour
  * 
  * 
  * @author Esther Yoo
  * @author Chua Hock-Chuan for original graphics code, modifed by Esther Yoo.
  * @version 1.1 05.22.16
  * 
  * The Ball class is a data class 
  *
  * <p>
  * <b>Instance variables: </b>
  * <p>
  * <b>x</b> A float variable that stores the Ball's center x-coordinate.
  * <p>
  * <b>y</b> A float variable that stores the Ball's center y-coordinate.
  * <p>
  * <b>speedX</b> A float variable that stores the Ball's x speed.
  * <p>
  * <b>speedY</b> A float variable that stores the Ball's y speed.
  * <p>
  * <b>radius</b> A float variable that stores the Ball's radius.
  * <p>
  * <b>letter</b> A char variable that stores the letter the bubble is holding.
  * <p>
  * <b>wasClicked</b> A boolean variable that checks if the bubble was clicked.
  * <p>
  * <b>angleInDegree</b> A float variable that stores the angle the ball is moving at.
  * <p>
  * <b>color</b> A Color variable that stores the colour of the ball.
  * <p>
  * <b>outline</b> A Color variable that stores the colour of the ball's outline.
  * <p>
  * <b>letterCol</b> A Color variable that stores the ball's letter colour.
  */
public class Ball {
  float x, y;
  float speedX, speedY;
  float radius;
  char letter;
  boolean wasClicked;
  float angleInDegree;
  private Color color;
  private Color outline;
  private Color letterCol;
  
  /**
   * The class constructor assigns all the values passed in its parameter to the appropriate instance variables.
   * 
   * x, y, angleInDegree, radius, color, letter, and wasClicked are determined by their respective parameter passes.
   * speedX and speedY are calculated using the speed passed in.
   * 
   * @param x This float type parameter pass is assigned to the instance-level x variable as the x-coordinate.
   * @param y This float type parameter pass is assigned to the instance-level y variable as the y-coordinate.
   * @param radius This float type parameter pass is assigned to the instance-level radius variable as the radius.
   * @param speed This float type parameter pass is used to calulate speeds and assigned to the instance-level 
   * speedX and speedY variables as the speed.
   * @param angleInDegree This float type parameter pass is assigned to the instance-level angleInDegree variable as the
   * angle the bubble moves at.
   * @param color This Color type parameter pass is assigned to the instance-level color variable as the bubble's interior
   * colour.
   * @param letter This char type parameter pass represents the letter the bubble will contain.
   * @param wasClicked This boolean type parameter pass keeps track of whether the bubble was clicked or not.
   */
  public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color, char letter, boolean wasClicked) 
  {
    this.x = x;
    this.y = y;
    this.angleInDegree = angleInDegree;
    this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
    this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
    this.radius = radius;
    this.color = color;
    outline = Color.white;
    letterCol = Colors.letters;
    this.letter = letter;
    this.wasClicked = wasClicked;
  }
  
  /** The setLocation method is a mutator method that sets the x and y coordinates of the bubble, 
    * determined by their respective parameter passes.
    * 
    * @param x This integer type parameter pass is assigned to the instance-level x variable as the new x-coordinate.
    * @param y This integer type parameter pass is assigned to the instance-level y variable as the new y-coordinate.
    */
  public void setLocation (int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  /** The getWasClicked method is an accessor method that returns the value of wasClicked.
    * 
    * @return wasClicked This is a boolean variable that keeps track of whether the bubble was clicked or not.
    */
  public boolean getWasClicked()
  {
    return wasClicked;
  }
  
  /** The setRadius method is a mutator method that sets the radius of the bubble, 
    * determined by its parameter pass.
    * 
    * @param newRadius This float type parameter pass is assigned to the instance-level radius variable as the new radius.
    */
  public void setRadius (float newRadius)
  {
    radius = newRadius;
  }
  
  /** The setWasClicked method is a mutator method that sets the wasClicked value of the bubble, 
    * determined by its parameter pass.
    * 
    * @param newWasClicked This boolean type parameter pass is assigned to the instance-level wasClicked variable as the new wasClicked.
    */
  public void setWasClicked(boolean newWasClicked)
  {
    wasClicked = newWasClicked;
  }
  
  /** The draw method draws the bubble on the panel.
    * 
    * First, the colour is set to the instance variable, color.<p>
    * Then, the bubble is drawn according to its x-coordinate, y-coordinate, and radius, as specified by the instance variables.<p>
    * Next, the colour is set to the instance variable, outline.<p>
    * Then, the bubble is outlined according to its x-coordinate, y-coordinate, and radius, as specified by the instance variables.<p>
    * Next, the colour is set to the instance variable, letterCol.<p>
    * The font is set to regular "Comic Sans MS", and size 70.
    * Then, the letter is drawn inside the bubble according to the bubble's x-coordinate and y-coordinate, 
    * as specified by the instance variables.
    * 
    * @param g This parameter pass is used to access the Graphics class.
    */
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
    g.setColor(outline);
    g.drawOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
    g.setColor(letterCol);
    g.setFont(new Font("Comic Sans MS", Font.PLAIN, 70));
    g.drawString("" + letter, (int)x-30, (int)y+25);
  }
  
  /** The returnVerticalCenter method is an accessor method that returns the value of the vertical center of the bubble.
    * 
    * @return y This is a float variable that stores the bubble's center y-coordinate.
    */
  public float returnVerticalCenter()
  {
    return y;
  }
  
  /** The returnHorizontalCenter method is an accessor method that returns the value of the horizontal center of the bubble.
    * 
    * @return x This is a float variable that stores the bubble's center x-coordinate.
    */
  public float returnHorizontalCenter()
  {
    return x;
  }
  
  /** The getLetter method is an accessor method that returns the letter the bubble contains.
    * 
    * @return letter This is a float variable that stores the bubble's center x-coordinate.
    */
  public char getLetter()
  {
    return letter;
  }
  
  /** The setColor method is a mutator method that sets the value of the color variable of the bubble, 
    * determined by its parameter pass.
    * 
    * @param newColor This Color type parameter pass is assigned to the instance-level color variable as the new color.
    */
  public void setColor (Color newColor)
  {
    color = newColor;
  }
  
  /** The setSpeed method is a mutator method that sets the value of the speedX and speedY variables of the bubble, 
    * determined by its parameter pass.
    * 
    * @param speed This float type parameter pass is used to calculate speeds and assign new values to the 
    * instance-level speedX and speedY variables as the new speeds.
    */
  public void setSpeed (float speed)
  {
    this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
    this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
  }
  
  /** The setSpeed moveOneStepWithCollisionDetection method checks if the ball is going over the bounds of its
    * ContainerBox and adjusts its position and speed accordingly.
    * 
    * First, the ballMinX, ballMinY, ballMaxX, and ballMaxY are calulated and assigned according to the radius of the
    * ball and the ContainerBox's bounds. This gets the ball's bounds.<p>
    * Next, the ball's new position is calculated and assigned according to its speed.<p>
    * Then, the if statement checks whether the ball is over the bounds of the box's x-coordinates. If so, its position and speed are
    * adjusted.<p>
    * The last if statement checks whether the ball is over the bounds of the box's y-coordinates. If so, its position and speed are
    * adjusted.<p>
    * 
    * 
    * @param box This ContainerBox type parameter pass tells the method which box the bubble is in and what numbers
    * to work with in order to prevent the bubble from going out of bounds.
    */
  public void moveOneStepWithCollisionDetection(ContainerBox box) {
    float ballMinX = box.minX + radius;
    float ballMinY = box.minY + radius;
    float ballMaxX = box.maxX - radius;
    float ballMaxY = box.maxY - radius-150;
    
    x += speedX;
    y += speedY;
    if (x < ballMinX) 
    {
      speedX = -speedX;
      x = ballMinX;
    } 
    else if (x > ballMaxX) 
    {
      speedX = -speedX;
      x = ballMaxX;
    }
    if (y < ballMinY) 
    {
      speedY = -speedY;
      y = ballMinY;
    } 
    else if (y > ballMaxY) 
    {
      speedY = -speedY;
      y = ballMaxY;
    }
  }
  
//  /** Return the magnitude of speed. */
//  public float getSpeed() {
//    return (float)Math.sqrt(speedX * speedX + speedY * speedY);
//  }
//  
//  /** Return the direction of movement in degrees (counter-clockwise). */
//  public float getMoveAngle() {
//    return (float)Math.toDegrees(Math.atan2(-speedY, speedX));
//  }
  
//  /** Return mass */
//  public float getMass() {
//    return radius * radius * radius / 1000f;  // Normalize by a factor
//  }
//  
//  /** Return the kinetic energy (0.5mv^2) */
//  public float getKineticEnergy() {
//    return 0.5f * getMass() * (speedX * speedX + speedY * speedY);
//  }
//  
//  /** Describe itself. */
//  public String toString() {
//    sb.delete(0, sb.length());
//    formatter.format("@(%3.0f,%3.0f) r=%3.0f V=(%2.0f,%2.0f) " +
//                     "S=%4.1f \u0398=%4.0f KE=%3.0f", 
//                     x, y, radius, speedX, speedY, getSpeed(), getMoveAngle(),
//                     getKineticEnergy());  // \u0398 is theta
//    return sb.toString();
//  }
//  // Re-use to build the formatted string for toString()
//  private StringBuilder sb = new StringBuilder();
//  private Formatter formatter = new Formatter(sb);
}