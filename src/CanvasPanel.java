/**
 * 2D CanvasPanel
 *
 * Adapted to fit Asteroids like theme
 *
 * @author (Prof R, Nicholas Rua)
 * @version (v2.0 11-18-23)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CanvasPanel extends JPanel
{
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int CANVAS_WIDTH = 800;
    private final static int CANVAS_HEIGHT = 400;

    private ArrayList<Circle> stars = new ArrayList<>();
    private int frameNumber;
    private Random random = new Random();

    
    public CanvasPanel()
    {

        // Callback for keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");
        
        // Create a render loop
        // Create a Swing Timer that will tick 30 times a second
        // At each tick the ActionListener that was registered via the lambda expression will be invoked
        //Timer renderLoop = new Timer(30, (ActionEvent ev) -> {frameNumber++; Simulate(); repaint();}); // lambda expression for ActionListener implements actionPerformed
        //renderLoop.start();
    }
    
    public void Simulate()
    {

    }

    // This method is called by renderloop
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Set window background to black
        g.setColor(Color.BLACK);
        g.fillRect(0,0,CANVAS_WIDTH + 2 * X_CORNER, CANVAS_HEIGHT + 2 * Y_CORNER); //draw the black border
        
        // Set canvas background to black
        g.setColor(Color.BLACK);
        g.fillRect(X_CORNER, Y_CORNER, CANVAS_WIDTH, CANVAS_HEIGHT); //make the canvas white

        //Makes a bunch of circles that will be "stars" for the background
        for(int i = 0; i <= 250; i++) {
            Circle star = new Circle(random.nextInt(800), random.nextInt(400),
                    1, 5);
            stars.add(star);
        }
        stars.forEach((Circle star) -> star.draw(g)); //draws each star
    }
    
    public static int getCanvasWidth()
    {
        return CANVAS_WIDTH;
    }

    public static int getCanvasHeight()
    {
        return CANVAS_HEIGHT;
    }

    public static int getCanvasXBorder()
    {
        return X_CORNER;
    }

    public static int getCanvasYBorder()
    {
        return Y_CORNER;
    }
    public class myActionListener extends KeyAdapter 
    {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    System.out.println("press up arrow");
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("press down arrow");
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("press left arrow");
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("press right arrow");
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("press space bar");
                    break;
                default:
                    System.out.println("press some other key besides the arrow keys");
            }
        }
        public void keyReleased(KeyEvent e)
        {
            System.out.println("released");
        }
    }
}
