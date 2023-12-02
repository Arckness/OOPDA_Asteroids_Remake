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
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Iterator;

public class CanvasPanel extends JPanel {
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int CANVAS_WIDTH = 800;
    private final static int CANVAS_HEIGHT = 400;
    private ArrayList<Circle> stars;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Projectile> projectiles;
    private Triangle player = new Triangle(5, CANVAS_WIDTH / 2, CANVAS_HEIGHT, 25);
    private int frameNumber;


    public CanvasPanel() {
        asteroids = new ArrayList<>();
        projectiles = new ArrayList<>();

        // Add some asteroids to the list
        for (int i = 0; i < 5; i++) {
            asteroids.add(new Asteroid(i % Shape.COLORS.length, (int) (Math.random() * CANVAS_WIDTH),
                    (int) (Math.random() * CANVAS_HEIGHT)));
        }


        // Callback for keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");

        // Create a render loop
        // Create a Swing Timer that will tick 30 times a second
        // At each tick the ActionListener that was registered via the lambda expression will be invoked
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {
            frameNumber++;
            Simulate();
            repaint();
        }); // lambda expression for ActionListener implements actionPerformed
        renderLoop.start();

        generateStars();
    }

    public void Simulate() {
        for (Asteroid asteroid : asteroids) {
            asteroid.Move();
        }

        // Move and check bounds for projectiles
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.Move();

            // Remove projectiles that are out of bounds
            if (projectile.isOutOfBounds()) {
                iterator.remove();
            }
        }
    }

    /**
     * Generates a buncha stars
     */
    public void generateStars() {
        stars = new ArrayList<Circle>();

        Random random = new Random();
        for (int i = 0; i < 150; i++) {
            stars.add(new Circle(5, random.nextInt(1000), random.nextInt(600), 1));
        }
    }


    // This method is called by renderloop
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set window background to black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, CANVAS_WIDTH + 2 * X_CORNER, CANVAS_HEIGHT + 2 * Y_CORNER); //draw the black border

        // Set canvas background to black
        g.setColor(Color.BLACK);
        g.fillRect(X_CORNER, Y_CORNER, CANVAS_WIDTH, CANVAS_HEIGHT); //make the canvas white

        stars.forEach(star -> star.draw(g2));

        player.draw(g);
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(g2);

        for (Projectile projectile : projectiles) {
            projectile.draw(g2);
        }
        }
    }

    public static int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public static int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    public static int getCanvasXBorder() {
        return X_CORNER;
    }

    public static int getCanvasYBorder() {
        return Y_CORNER;
    }


    /**
     * Handles all the input into the game
     */
    public class myActionListener extends KeyAdapter {
        private final Set<Integer> pressedKeys = new HashSet<>();

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            pressedKeys.add(keyCode);

            handleKeyInputs();
        }

        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            pressedKeys.remove(keyCode);

            handleKeyInputs();
        }

        private void handleKeyInputs() {
            if (pressedKeys.contains(KeyEvent.VK_UP)) {
                player.Accelerate(0.75);
                player.Move();
            }
            if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                player.Rotate(-Math.PI / 45);
            }
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                player.Rotate(Math.PI / 45);
            }
            if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
                System.out.println("Press space bar");
                shootProjectile();
            }
        }

        private void shootProjectile() {
            // Make sure the list is initialized before adding new projectile
            if (projectiles == null) {
                projectiles = new ArrayList<>();
            }

            // Calculate the starting position based on the front of the player
            double projectileX = player.GetX() + (player.GetSideLength() / 2) * Math.cos(player.GetDirection());
            double projectileY = player.GetY() + (player.GetSideLength() / 2) * Math.sin(player.GetDirection());

            // Create a new projectile and add it to the list
            projectiles.add(new Projectile(0, player.GetX(), player.GetY(), (int) player.GetDirection(),3, 3));
        }

    }
}
