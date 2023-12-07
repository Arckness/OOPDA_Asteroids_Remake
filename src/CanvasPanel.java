import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Random;

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

    private List<Shape2D> spriteList = new ArrayList<>();
    private int frameNumber;
    private int score = 0;
    private int highScore = 0;
    private int lives = 3;

    private ArrayList<Circle> stars;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Projectile> projectiles;
    private Player player = new Player(5, CANVAS_WIDTH / 2 + 80, CANVAS_HEIGHT - 150, 25);
    private Audio shootSound1 = new Audio();
    private Audio shootSound2 = new Audio();
    private gameState state;
    private Random random = new Random();


    /**
     * Creates a new CanvasPanel object and initializes game elements like asteroids, stars, and the player's spaceship.
     * Also sets up the event listener and starts the render loop.
     */
    public CanvasPanel() {
        state = gameState.playing;

        // Initialization of game elements
        asteroids = new ArrayList<>();
        projectiles = new ArrayList<>();

        shootSound1.ReadSoundFile("src/audio/Pew1.wav");
        System.out.println("Pew1.wav read successfully");
        shootSound2.ReadSoundFile("src/audio/Pew2.wav");
        System.out.println("Pew2.wav read successfully");

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

        BufferedImage[] flameSprites = new BufferedImage[8];
        try {
            flameSprites[0] = ImageIO.read(new File("src/flames/flame1.png"));
            flameSprites[1] = ImageIO.read(new File("src/flames/flame2.png"));
            flameSprites[2] = ImageIO.read(new File("src/flames/flame3.png"));
            flameSprites[3] = ImageIO.read(new File("src/flames/flame4.png"));
            flameSprites[4] = ImageIO.read(new File("src/flames/flame5.png"));
            flameSprites[5] = ImageIO.read(new File("src/flames/flame6.png"));
            flameSprites[6] = ImageIO.read(new File("src/flames/flame7.png"));
            flameSprites[7] = ImageIO.read(new File("src/flames/flame8.png"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        spriteList.add(new Sprite2D(10, 300, flameSprites));

    }

    /**
     * Generates a random, set number of stars and initializes them.
     */
    public void generateStars() {
        stars = new ArrayList<Circle>();

        Random random = new Random();
        for (int i = 0; i < 150; i++) {
            stars.add(new Circle(5, random.nextInt(1000), random.nextInt(600), 1));
        }
    }

    /**
     * Simulates the game logic such as player movement, asteroid movement, projectile handling, and shot delay.
     */
    public void Simulate() {
       if(state == gameState.playing) {
           // Move asteroids
           for (Asteroid asteroid : asteroids) {
               asteroid.Move();
           }

           // Move the player
           player.Move();
       }

        // Move and check bounds for projectiles
        Iterator<Projectile> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();
            projectile.Move();

            // Remove projectiles that are out of bounds
            if (projectile.isOutOfBounds()) {
                projectileIterator.remove();
            }
        }

        wrapObjects(asteroids);
        wrapObject(player);

        checkCollisions();

        // Continuously lower the shot delay while the game is ongoing
        if(player.getShotDelay() > 0) {
            player.rmvShotDelay(1);
        }

        if(player.getHitDelay() > 0) {
            player.rmvHitDelay(1);
        }

        if(lives == 0) {
            state = gameState.end;
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

        // Display the scores
        g.drawString("Score: " + score , 5, 15);
        if(highScore != 0) {
            g.drawString("High Score: " + highScore, 80, 15);
        }
        g.drawString("Lives : " + lives, 750, 15);

        if(state == gameState.end) {
            gameOver(g);
        }

        for (Shape2D shape : spriteList)
        {
            shape.Draw(g);
        }
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
            if(state == gameState.playing) {
            if (pressedKeys.contains(KeyEvent.VK_UP)) {
                player.Accelerate(0.75); // when up arrow is pressed apply acceleration
            }
            if (!pressedKeys.contains((KeyEvent.VK_UP))) {
                player.setAcceleration(0); // when up arrow is released turn off acceleration
            }
            if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                player.Rotate(-Math.PI / 15);
            }
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                player.Rotate(Math.PI / 15);
            }
            if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
                shootProjectile();
                 if(random.nextInt(1,10) % 2 > 0) {
                     shootSound1.Play();
                 } else {
                     shootSound2.Play();
                 }
            }
            if (!pressedKeys.contains(KeyEvent.VK_SPACE)) {
                  shootSound1.Reset();
                  shootSound2.Reset();
            }
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

            // Calculate the offset from the center of the player's ship
            double offset = (double) player.GetSideLength() / 2;

            // Calculate the starting position based in the center of the player
            double projectileX = player.GetX() + offset * Math.cos(player.GetDirection());
            double projectileY = player.GetY() + offset * Math.sin(player.GetDirection());

            // Create a new projectile and add it to the list
            projectiles.add(new Projectile(0, projectileX, projectileY, player.GetDirection(),3, 3));
            player.addShotDelay(20); // Adds a delay to the shot, change the int if you want it faster/slower
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

                    // Update score
                    addScore(50);
                }
            }
        }
        // Check collisions between player and asteroids
        for (Asteroid asteroid : asteroidsCopy) {
            if (isCollision(asteroid, player)) {
                    handlePlayerCollision();
            }
        }
    }

    private void handlePlayerCollision() {
        int delay = player.getHitDelay();

        if(highScore < score) {
            highScore = score;
        }

        // Set the score to zero
        score = 0;

        if(delay <= 0) {
            if(lives >= 0) {
                this.lives -= 1;
                player.addHitDelay(60);
            }
        }
    }

    private void gameOver(Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        int x = (getCanvasWidth() - fontMetrics.stringWidth("Game Over")) / 2;
        int y = getCanvasHeight() / 2;
        g.drawString("Game Over", x, y);
    }

    /**
     * Actually checks if the objects collides
     *
     * @param obj1
     * @param obj2
     * @return boolean
     */
    private boolean isCollision(Shape obj1, Shape obj2) {
        double radius1 = obj1.getBoundingCircleRadius();
        double radius2 = obj2.getBoundingCircleRadius();

        // Calculate the distance between the centers of the bounding circles
        double distance = Math.sqrt(Math.pow(obj1.GetX() - obj2.GetX(), 2) +
                Math.pow(obj1.GetY() - obj2.GetY(), 2));

        // Check if the distance is less than the sum of the radii
        return distance < (radius1 + radius2);
    }

    /**
     * Just removes each of the colliding objects
     *
     * @param obj1
     * @param obj2
     */
    private void handleCollision(Shape obj1, Shape obj2) {
        if (obj1 instanceof Player || obj2 instanceof Player) {
            // Handle collision involving the player
            handlePlayerCollision();
        } else {
            // Handle collision involving other objects (e.g., projectiles, asteroids)
            asteroids.remove(obj1);
            projectiles.remove(obj2);
        }
    }

    /**
     * Checks all asteroids in the game
     *
     * @param asteroids
     */
    public void wrapObjects(ArrayList<Asteroid> asteroids) {
        for(Asteroid asteroid : asteroids) {
            if(asteroid != null) {
                wrapObject(asteroid);
            }
        }
    }

    /**
     * Wraps an object in the game if it goes off-screen
     *
     * @param obj
     */
    private void wrapObject(Shape obj) {
        if (obj.GetX() < 0) obj.setX(CanvasPanel.getCanvasWidth()+200);
        if (obj.GetX() > CanvasPanel.getCanvasWidth() + 200) obj.setX(0);
        if (obj.GetY() < 0) obj.setY(CanvasPanel.getCanvasHeight()+100);
        if (obj.GetY() > CanvasPanel.getCanvasHeight()+ 100) obj.setY(0);
    }

}
