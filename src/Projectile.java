import java.awt.*;
/**
 * The Projectile class represents a projectile in the Asteroids game. It extends the Shape class and defines the
 * behavior and appearance of projectiles.
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v1.0, 11-25-2023)
 */
public class Projectile extends Shape {
    private int width;
    private int height;

    /**
     * Constructs a Projectile object with the specified parameters.
     *
     * @param colorIndex The index representing the color of the projectile
     * @param xPosition  The initial x-coordinate position of the projectile
     * @param yPosition  The initial y-coordinate position of the projectile
     * @param direction  The initial direction of the projectile in radians
     * @param width      The width of the projectile
     * @param height     The height of the projectile
     */
    public Projectile(int colorIndex, double xPosition, double yPosition, double direction, int width, int height) {
        super(colorIndex, xPosition, yPosition);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.velocity = 10; // Set initial velocity
    }

    @Override
    public double getBoundingCircleRadius() {
        // Return the maximum of width and height divided by 2
        return Math.max(width, height) / 2.0;
    }

    /**
     * Overrides the Move method in Shape.
     * Moves the projectile based on its direction and velocity.
     */
    @Override
    public void Move() {
        // Update position based on direction and velocity
        xPosition += velocity * Math.cos(direction);
        yPosition += velocity * Math.sin(direction);

    }

    /**
     * Checks if the projectile is out of bounds.
     *
     * @return true if the projectile is out of bounds, false otherwise.
     */
    public boolean isOutOfBounds() {
        return xPosition < 0 || xPosition > CanvasPanel.getCanvasWidth() || yPosition < 0 || yPosition > CanvasPanel.getCanvasHeight();
    }

    /**
     * Overrides the draw method in Shape.
     * Draws the projectile on the specified Graphics object.
     *
     * @param g The Graphics object on which to draw the projectile.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.drawRect((int) xPosition, (int) yPosition, width, height);

        // UNCOMMENT THIS TO SHOW BOUNDING CIRCLE
        //g2d.drawOval((int) (xPosition - getBoundingCircleRadius()), (int) (yPosition - getBoundingCircleRadius()),
        //(int) (2 * getBoundingCircleRadius()), (int) (2 * getBoundingCircleRadius()));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
