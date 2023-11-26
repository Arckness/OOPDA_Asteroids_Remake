/**
 * 2D CanvasPanel
 * 
 *
 * @author (Prof R)
 * @version (v1.0 11-17-22)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CanvasPanel extends JPanel
{
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int CANVAS_WIDTH = 800;
    private final static int CANVAS_HEIGHT = 400;
    
    private List<AsteroidPolygon> asteroids = new ArrayList<>();
    private Triangle triangle = new Triangle(5, 400, 300, 20);
    private int frameNumber;

    
    public CanvasPanel()
    {
        AsteroidPolygon smallAsteroid = new AsteroidPolygon(4, 300, 100, 0); // Small asteroid
        AsteroidPolygon mediumAsteroid = new AsteroidPolygon(4, 400, 200, 1); // Medium asteroid
        AsteroidPolygon largeAsteroid = new AsteroidPolygon(4, 500, 300, 2); // Large asteroid

        asteroids.add(smallAsteroid);
        asteroids.add(mediumAsteroid);
        asteroids.add(largeAsteroid);


        // Callback for keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");
        
        // Create a render loop
        // Create a Swing Timer that will tick 30 times a second
        // At each tick the ActionListener that was registered via the lambda expression will be invoked
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {frameNumber++; Simulate(); repaint();}); // lambda expression for ActionListener implements actionPerformed
        renderLoop.start();
    }
    
    public void Simulate()
    {

    }

    /**
     * Generates a buncha stars
     */
    public void generateStars() {
        ArrayList<Circle> stars = new ArrayList<Circle>();

        Random random = new Random();
        for(int i = 0; i < 150; i++) {
            int x = random.nextInt(getCanvasWidth());
            int y = random.nextInt(getCanvasHeight());
            stars.add(new Circle(5, x, y, 1));
        }
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

        triangle.draw(g);

        for (AsteroidPolygon asteroid : asteroids) {
            asteroid.draw(g);
        }
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


    /**
     * Handles all the input into the game
     */
    public class myActionListener extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    System.out.println("press up arrow");
                    //accelerate forward
                break;
                case KeyEvent.VK_LEFT:
                    System.out.println("press left arrow");
                    //rotate left
                break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("press right arrow");
                    //rotate right
                break;
                case KeyEvent.VK_SPACE:
                    System.out.println("press space bar");
                    //shoot
            }
        }
        public void keyReleased(KeyEvent e)
        {
            System.out.println("released");
        }
    }
}
