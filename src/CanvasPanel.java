import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/**
 *  The CanvasPanel class represents the drawing canvas for the Asteroids game. Game elements such as stars, asteroids,
 *  the player's spaceship, and projectiles are rendered here as well.
 *  It extends JPanel to allow for custom rendering.
 * 
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v2.0 11-17-22)
 */
public class CanvasPanel extends JPanel {
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int CANVAS_WIDTH = 800;
    private final static int CANVAS_HEIGHT = 400;

    private int frameNumber;
    private int score = 0;
    private int highScore = 0;


    private ArrayList<Circle> stars;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Projectile> projectiles;
    private Player player = new Player(5, CANVAS_WIDTH / 2 + 80, CANVAS_HEIGHT - 150, 25);
    private Audio shootSound = new Audio();


    /**
     * Creates a new CanvasPanel object and initializes game elements like asteroids, stars, and the player's spaceship.
     * Also sets up the event listener and starts the render loop.
     */
    public CanvasPanel() {
        // Initialization of game elements
        asteroids = new ArrayList<>();
        projectiles = new ArrayList<>();

        // shootSound.ReadSoundFile("Pew1.wav");
        System.out.println("Pew1.wav read successfully");

        generateAsteroids();

        // Callback for keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");

        // Create a render loop using a Swing Timer that will tick 30 times a second and will invoke the ActionListener
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {
            frameNumber++;
            Simulate();
            repaint();
        }); // Lambda expression for ActionListener implements actionPerformed
        renderLoop.start();

        generateStars();
    }

    /**
     * Generates a random, set number of stars and initializes them.
     */
    public void generateStars() {
        stars = new ArrayList<Circle>();

        Random random = new Random();
        for (int i = 0; i < 150; i++) {
            stars.add(new Circle(5, random.nextInt(825), random.nextInt(425), 1));
        }
    }

    /**
     * Simulates the game logic such as player movement, asteroid movement, projectile handling, and shot delay.
     */
    public void Simulate() {
        // Move asteroids
        for (Asteroid asteroid : asteroids) {
            asteroid.Move();
        }

        // Move the player
        player.Move();


        // Move and check bounds for projectiles
        List<Projectile> projectilesCopy = new ArrayList<>(projectiles);
        for (Projectile projectile : projectilesCopy) {
            projectile.Move();

            // Remove projectiles that are out of bounds
            if (projectile.isOutOfBounds()) {
                projectiles.remove(projectile);
            }
        }

        checkCollisions();

        // Continuously lower the shot delay while the game is ongoing
        if(player.getShotDelay() > 0) {
            player.rmvShotDelay(1);
        }

        generateAsteroids();
    }


    /**
     * Adds points to the current score.
     *
     * @param points The points to be added to the score
     */
    public void addScore(int points) { this.score += points; }

    /**
     * Sets the high score if the current score is higher.
     */
    public void setHighScore() {
        if(this.score > this.highScore) {
            this.highScore = score;
        }
    }

    /**
     * Generates the asteroids on screen, makes sure there are at least 5 asteroids on screen at a time and
     * only places them at the bottom of the screen
     */
    public void generateAsteroids() {
        if(asteroids.size() < 5) {
            for(int i = 5 - asteroids.size(); i > 0; i--) {
                asteroids.add(new Asteroid(i % Shape.COLORS.length, (int) (Math.random() * CANVAS_WIDTH),
                        (CANVAS_HEIGHT)));
            }
        }
    }

    /**
     * Custom rendering method for the canvas.
     * Draws the background, stars, player, asteroids, projectiles, and score.
     *
     * @param g The Graphics object for rendering
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set canvas background to black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0,CANVAS_WIDTH, CANVAS_HEIGHT); //make the canvas black

        // Draw stars
        stars.forEach(star -> star.draw(g2));

        // Draw player
        player.draw(g);

        // Draw asteroids
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(g2);
        }

        // Draw projectiles
        for (Projectile projectile : projectiles) {
            projectile.draw(g2);
        }

        // Display the score
        g.drawString("Score: " + score , 15, 425);
    }

    // Getters for canvas dimensions and borders

    /**
     * Returns the width of the canvas.
     *
     * @return The width of the canvas
     */
    public static int getCanvasWidth() { return CANVAS_WIDTH; }

    /**
     * Returns the height of the canvas.
     *
     * @return The height of the canvas
     */
    public static int getCanvasHeight() { return CANVAS_HEIGHT; }

    /**
     * Returns the x-coordinate border of the canvas.
     *
     * @return The x-coordinate border of the canvas.
     */
    public static int getCanvasXBorder() { return X_CORNER; }

    /**
     * Returns the y-coordinate border of the canvas.
     *
     * @return The y-coordinate border of the canvas.
     */
    public static int getCanvasYBorder() { return Y_CORNER; }

    /**
     * Handles keyboard inputs for the game.
     */
    public class myActionListener extends KeyAdapter {
        private final Set<Integer> pressedKeys = new HashSet<>();

        /**
         * Handles key press events.
         *
         * @param e The KeyEvent representing the key press
         */
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            pressedKeys.add(keyCode);

            handleKeyInputs();
        }

        /**
         * Handles key release events.
         *
         * @param e The KeyEvent representing the key release
         */
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            pressedKeys.remove(keyCode);

            handleKeyInputs();
        }

        /**
         * Handles key inputs based on the pressed keys.
         */
        private void handleKeyInputs() {
            if (pressedKeys.contains(KeyEvent.VK_UP)) {
                player.Accelerate(0.75); // when up arrow is pressed apply acceleration
            }
            if (!pressedKeys.contains((KeyEvent.VK_UP))) {
                player.setAcceleration(0); // when up arrow is released turn off acceleration
            }
            if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                player.Rotate(-Math.PI / 45);
            }
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                player.Rotate(Math.PI / 45);
            }
            if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
                shootProjectile();
               // shootSound.Play();
            }
            if (!pressedKeys.contains(KeyEvent.VK_SPACE)) {
              //  shootSound.Reset();
            }
            if (pressedKeys.contains(KeyEvent.VK_ESCAPE)) {
              //  shootSound.Close();
                System.exit(0);
            }
        }
    }

    /**
     * Initiates the shooting of a projectile.
     */
    private void shootProjectile() {
        int delay = player.getShotDelay(); // Retrieves the delay from player and makes it easy read

        if(delay <= 0) {
            // Make sure the list is initialized before adding new projectile
            if (projectiles == null) {
                projectiles = new ArrayList<>();
            }

            // Calculate the initial position of the projectile at the front of the spaceship
            double projectileX = player.GetX() + Math.cos(player.GetDirection()) * player.GetSideLength() / 2 - 94; // random numbers to center
            double projectileY = player.GetY() + Math.sin(player.GetDirection()) * player.GetSideLength() / 2 - 50;

            // Create a new projectile and add it to the list
            projectiles.add(new Projectile(0, projectileX, projectileY, player.GetDirection(),3, 3));
            player.addShotDelay(30); // Adds a delay to the shot, change the int if you want it faster/slower
        }

    }

    /**
     * The method that will be in simulate checks if any of the projectiles hits any of the asteroids
     */
    public void checkCollisions() {
        List<Asteroid> asteroidsCopy = new ArrayList<>(asteroids);
        List<Projectile> projectilesCopy = new ArrayList<>(projectiles);

        for (Asteroid asteroid : asteroidsCopy) {
            for (Projectile projectile : projectilesCopy) {
                if (isCollision(asteroid, projectile)) {
                    // Handle the collision
                    handleCollision(asteroid, projectile);

                    // Remove asteroid and projectile from their respective lists
                    asteroids.remove(asteroid);
                    projectiles.remove(projectile);

                    // Update score
                    addScore(100);
                }
            }
        }
    }

    /**
     * Actually checks if the projectile and asteroid collides
     *
     * @param asteroid
     * @param projectile
     * @return boolean
     */
    private boolean isCollision(Asteroid asteroid, Projectile projectile) {
        double asteroidRadius = asteroid.getBoundingCircleRadius();
        double projectileRadius = (double) Math.max(projectile.getWidth(), projectile.getHeight()) / 2;

        // Calculate the distance between the centers of the bounding circles
        double distance = Math.sqrt(Math.pow(asteroid.GetX() - projectile.GetX(), 2) +
                Math.pow(asteroid.GetY() - projectile.GetY(), 2));

        // Check if the distance is less than the sum of the radii
        return distance < (asteroidRadius + projectileRadius);
    }

    /**
     * Just removes each of the colliding objects
     *
     * @param asteroid
     * @param projectile
     */
    private void handleCollision(Asteroid asteroid, Projectile projectile) {
        asteroids.remove(asteroid);
        projectiles.remove(projectile);
    }
}
