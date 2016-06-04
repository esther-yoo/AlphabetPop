import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * 
 * <p>
 * <b> Version Information: </b>
 * <p>
 * <b>Author</b> Esther Yoo
 * <b>Version #</b> 1
 * <b>Date</b> 05.31.16
 * <b>Time Spent</b> 3 hours
 * <p>
 * <b>Author</b> Samantha Unger
 * <b>Version #</b> 1
 * <b>Date</b> 06.02.16
 * <b>Time Spent</b> 5 hours
 * <b>What Was Changed</b> The code organization was improved and startup() method was created.  Some problems involving
 * file IO were fixed.
 * 
 * <p>
 * <b> Instance Variables: </b>
 * <p>
 * <b>ball</b> This creates an array of Ball objects that are used as the bubbles.
 * <p>
 * <b>UPDATE_RATE</b> This final int is used to store the update rate.
 * <p>
 * <b>ball</b> This creates an array of Ball objects that are used as the bubbles.
 * <p>
 * <b>NUM_BUBBLES</b> This final int stores the number of bubbles to be used.
 * <p>
 * <b>word</b> This String stores the word the user must spell.
 * <p>
 * <b>words</b> This ArrayList stores all of the words from the file.
 * <p>
 * <b>box</b> This stores an instance of the ContainerBox class for putting the bubbles.
 * <p>
 * <b>box2</b> This stores an instance of the ContainerBox class for putting the bubbles.
 * <p>
 * <b>canvas</b> This creates an instance of the DrawCanvas class for drawing the box and bubbles on.
 * <p>
 * <b>canvas2</b> This creates an instance of the DrawLetters class for drawing the letters on.
 * <p>
 * <b>canvasWidth</b> This int stores the width of the canvas.
 * <p>
 * <b>canvasHeight</b> This int stores the height of the canvas.
 * <p>
 * <b>xCoord</b> This int stores the x-coordinate of where the user presses.
 * <p>
 * <b>yCoord</b> This int stores the y-coordinate of where the user presses.
 * <p>
 * <b>radius</b> This int stores the radius size for the bubbles.
 * <p>
 * <b>x</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>y</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>speed</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>angleInDegree</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>background</b> This int is not used in the current version and may be removed in future versions.
 * <p>
 * <b>letters</b> This char array stores the letters of the current word to be spelled.
 * <p>
 * <b>currentLetter</b> This int stores the index of the current letter that the user is finding.
 * <p>
 * <b>clip</b> This Clip is not used in the current version and may be removed in future versions.
 * <p>
 * <b>music</b> This Clip is not used in the current version and may be removed in future versions.
 * <p>
 * <b>audio</b> This Clip array stores the music.
 * <p>
 * <b>alphabet</b> This Clip array stores the sound of the letters.
 * <p>
 * <b>click</b> This Clip array stores the sounds to be played when the user clicks.
 * <p>
 * <b>pics</b> This array of BufferedImage objects stores the background images.
 * <p>
 * <b>t</b> This GameTimer is used to time the game.
 * <p>
 * <b>round1Time</b> This int stores the time spent on the first round.
 * <p>
 * <b>round2Time</b> This int stores the time spent on the second round.
 * <p>
 * <b>round3Time</b> This int stores the time spent on the third round.
 * 
 * 
 * 
 * @author Chua Hock-Chuan for original graphics code, modifed by Esther Yoo, Samantha Unger
 * @version 1.1 06.02.16
 */
public abstract class Levels extends JPanel {
  
  protected Ball[] ball = new Ball[26];
  private static final int UPDATE_RATE = 30;  // Frames per second (fps)
  private static final int NUM_BUBBLES = 26;
  String word;
  protected ArrayList<String>words;

  private ContainerBox box;  // The container rectangular box
  private ContainerBox box2;
  
  private DrawCanvas canvas; // Custom canvas for drawing the box/ball
  private DrawLetters canvas2;
  protected int canvasWidth;
  protected int canvasHeight;
  int xCoord;
  int yCoord;
  
  int radius = 50;
  int x;
  int y;
  int speed;
  int angleInDegree;
  
  int background;
  
  char [] letters;
  int currentLetter;
  Clip clip;
  Clip music;
  
  Clip[] audio = new Clip[8];
  Clip[] alphabet = new Clip[26];
  Clip[] click = new Clip[3];
  
  BufferedImage [] pics = new BufferedImage [8];
  
  protected GameTimer t;
  
  Thread gameThread;
  
  int round1Time, round2Time, round3Time;
  
  
    /**
   * The readWords method reads the words in from a file to an ArrayList and shuffles the order of the elements in the
   * ArrayList.
   * 
   * @param file String that stores the name of the file to read the words in from 
   */
  public void readWords(String file)
  {
    BufferedReader input;
    int randNum;
    String tempString;
    words=new ArrayList<String>();      
    
    try
    {

      String fileName = file;
      input = new BufferedReader (new FileReader (fileName));
      System.out.println("OK");
      if (!input.readLine().equals("This is a Green Eggs & Sam file."))
      {
        JOptionPane.showMessageDialog (this, "This is not a .GSE file!", "Incompatible File Type", JOptionPane.ERROR_MESSAGE);
      }
      while (true)
      {
        tempString=input.readLine();
        System.out.println(tempString);
        if (tempString!=null)
        {
          System.out.println("W");
        words.add(tempString);
        System.out.println("YES");
        }
        else
        {
          break;
        }
      }
    }
    catch (FileNotFoundException l)
    {
      System.out.println("BAD");
    }
    catch (IOException q)
    {
      System.out.println("Open File IO Error");
    }
    catch (Exception e)
    {
      System.out.println("Open Error");
    }
    
    for (int x=words.size()-1; x>0;x--)
    {
      randNum=(int)(Math.random()*x);
      if (randNum!=x)
      {
        tempString=words.get(x);
        words.set(x,words.get(randNum));
        words.set(randNum, tempString);
      }
    }
  }
  
    /**
   * This method loads all of the audio clips into the correct arrays.
   */
  public void loadAudio()
  {
    try
    {
      for (int x = 0; x < 6; x++)
      {
        audio[x] = AudioSystem.getClip();
        File audioClip = new File("Music_" + (x+1) + ".wav");
        AudioInputStream audioClipStream = AudioSystem.getAudioInputStream(audioClip);
        audio[x].open(audioClipStream);
      }
      for (int x = 0; x < 26; x++)
      {
        alphabet[x] = AudioSystem.getClip();
        File alphabetClip = new File(("" + (char)(65+x)) + ".wav");
        AudioInputStream alphabetClipStream = AudioSystem.getAudioInputStream(alphabetClip);
        alphabet[x].open(alphabetClipStream);
      }
    }
    catch (UnsupportedAudioFileException q) {
      q.printStackTrace();
    } catch (IOException q) {
      q.printStackTrace();
    } catch (LineUnavailableException q) {
      q.printStackTrace();
    }
  }
  
    /**
   * This method changes the volume of a given clip.
   * 
   * @param volAdjust float that stores the amount to adjust the volume by
   * @param clipNum int that stores the index of the clip to have its volume altered
   */
  public void volume(float volAdjust, int clipNum)
  {
    FloatControl gainControl = (FloatControl) audio[clipNum].getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(volAdjust); // Reduce volume by 10 decibels.
  }
  
    /**
   * This method loads in an audio clip and adjusts its volume.
   * 
   * @param fileName String that stores the name of the file to be read in
   * @param loop int that determines whether or not the clip should be looped
   * @param volAdjust float that stores how much to adjust the volume by
   */  
  public void loadAudio(String fileName, int loop, float volAdjust)
  {
      try
      {
        Clip clip = AudioSystem.getClip();
        File effect = new File(fileName + ".wav");
        AudioInputStream effectAudio = AudioSystem.getAudioInputStream(effect);
        clip.open(effectAudio);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volAdjust); // Reduce volume by 10 decibels.
        if (loop == 0)
          clip.start();
        else
          clip.loop(Clip.LOOP_CONTINUOUSLY);
      }
      catch (UnsupportedAudioFileException q) {
        q.printStackTrace();
      } catch (IOException q) {
        q.printStackTrace();
      } catch (LineUnavailableException q) {
        q.printStackTrace();
      }
  }
  
  /**
   * This constructor sets the value of of the canvasHeight, canvasWidth and calls the loadAudio method.
   * 
   * @param width int that contains the screen width
   * @param height int that contains the screen height
   */
  public Levels(int width, int height) {

    canvasWidth = width;
    canvasHeight = height;
    loadAudio();

  }
  
    /**
   * This method constructs the necessary objects to play the game.  A nested MouseAdapter class is necessary so that
   * the user's click location can be taken.  A component listener is added to the screen.  A for loop is used to 
   * check each letter.  If statements are used to check letters.  The gameStart() method is called.
   */
  public void startup()
  {
    box = new ContainerBox(0, 0, canvasWidth, canvasHeight, "underwater.jpg", Color.BLACK);
    
    canvas = new DrawCanvas();
    canvas2 = new DrawLetters();
    this.setLayout(new BorderLayout());
    this.add(canvas, BorderLayout.CENTER);
    this.add(canvas2, BorderLayout.SOUTH);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        

        xCoord = e.getX();
        yCoord = e.getY();

        
        System.out.println("XCoord: " + xCoord+ "      " + "YCoord" +yCoord);
        
        for (int z = NUM_BUBBLES-1;z>=0;z--)
        {
          if (Math.sqrt(Math.pow(xCoord-ball[z].returnHorizontalCenter(),2)+Math.pow(yCoord-ball[z].returnVerticalCenter(),2))<=50 && !ball[z].getWasClicked())
          {
            if (ball[z].getLetter() == letters[currentLetter])
            {
              audio[4].setMicrosecondPosition(0);
              
              ball[z].setColor(Color.green);
              ball[z].setWasClicked(true);
              ball[z].setSpeed(0);
              ball[z].setRadius(-100);
              ball[z].setLocation(-100,-100);
              currentLetter++;
              if (currentLetter <= letters.length-1)
              {
                alphabet[letters[currentLetter]-65].setMicrosecondPosition(0);
                alphabet[letters[currentLetter]-65].start();
              }
            }
            else
            {
              loadAudio("Music_7",0,+5.0f);
            }
            if (currentLetter > letters.length-1)
            {
              currentLetter = 0;
              audio[2].stop();
              Main.switchMenu(0);
            }
            System.out.println("This is bubble number: " + currentLetter);
            System.out.println("Time elapsed is "+ t.getTimeElapsed());

            break;
          }
        }
      }
    });
    
    
    
    // Handling window resize.
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        Component c = (Component)e.getSource();
        Dimension dim = c.getSize();
        canvasWidth = dim.width;
        canvasHeight = dim.height;
        // Adjust the bounds of the container to fill the window
        box.set(0, 0, canvasWidth, canvasHeight);
        //box2.set(0, 0, 100, 100);
      }
    });
    
    // Start the ball bouncing
    gameStart();
  }
  
  /** Start the ball bouncing. */
  public void gameStart() {
    // Run the game logic in its own thread.
    gameThread = new Thread() {
      public void run() {
        while (true) {
          // Execute one time-step for the game 
          gameUpdate();
          // Refresh the display
          repaint();
          // Delay and give other thread a chance
          try {
            Thread.sleep(1000 / UPDATE_RATE);
          } catch (InterruptedException ex) {}
        }
      }
    };
    gameThread.start();  // Invoke GameThread.run()
  }
  
  /** 
   * This method updates the game objects, with proper collision detection and response. It uses a for loop to update
   * each object.
   */
  public void gameUpdate() {
    for (int z = 0; z < 26; z++)
    {
      ball[z].moveOneStepWithCollisionDetection(box);
    }
    //ball[0].moveOneStepWithCollisionDetection(box);
  }
  
  /** This inner class is a custom drawing panel for the bouncing ball. It overrides the paintComponent() method and 
    * uses for loops to draw each bubble.
   */
  class DrawCanvas extends JPanel {
      /**
   * This method paints components on the screen.  It uses a for loop to draw each bubble.
   * @param g Graphics passed in to allow painting on the frame.
   */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);    // Paint background
      // Draw the box and the ball
      //draw(g);
      box.draw(g);
      for (int z = 0; z < NUM_BUBBLES; z++)
      {
        ball[z].draw(g);
      }
      // Display ball's information
      g.setColor(Color.WHITE);
      g.setFont(new Font("Courier New", Font.PLAIN, 12));
      g.drawString("Ball " + ball.toString(), 20, 30);
      
    }
    
    /** This method is overriden to get the preferred size of the component. 
      * @return Dimension that indicates the preferred size
      */
    @Override
    public Dimension getPreferredSize() {
      return (new Dimension(canvasWidth, canvasHeight));
    }
  }
  
    /** This inner class is a custom drawing panel for the letters. It overrides the paintComponent() method and 
    * uses for loops to draw each bubble.
   */
  class DrawLetters extends JPanel {
          /**
   * This method paints components on the screen. 
   * @param g Graphics passed in to allow painting on the frame.
   */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);    // Paint background
      // Draw the box and the ball
      g.setColor(Color.red);
      g.fillRect(0, 0, 100,100);
      g.fillRect(450, 0, 100,100);
      g.setColor(Color.black);
      g.setFont(new Font("Courier New", Font.PLAIN, 12));
      g.drawString("Time: " + t.getTimeElapsed(), 0, 50);
//      for (int z = 0; z < NUM_BUBBLES; z++)
//      {
//        ball[z].draw(g);
//      }
//      // Display ball's information
//      g.setColor(Color.WHITE);
//      g.setFont(new Font("Courier New", Font.PLAIN, 12));
//      g.drawString("Ball " + ball.toString(), 20, 30);
    }
    
        /** This method is overriden to get the preferred size of the component. 
      * @return Dimension that indicates the preferred size
      */
    @Override
    public Dimension getPreferredSize() {
      return (new Dimension(100, 100));
    }
  }
  

  
  

  
//  
////  public void draw (Graphics g)
////  {
////      g.drawImage(pics[background], 0, 0, null);
////      System.out.println(background);
////  }
  

}

  