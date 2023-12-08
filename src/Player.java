import java.awt.*;
import java.awt.geom.AffineTransform;
/**
 * The Player class represents a triangular player entity in the Asteroids game. It extends the Shape class and
 * defines the behavior and appearance of the player.
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v1.0, 11-25-2023)
 */
public class Player extends Shape {
    private int sideLength; // The length of each side of the player
    private double velocity; // The velocity of the player
    private double acceleration; // The acceleration applied to the player
    private static final double maxVelocity = 7; // The maximum allowed velocity
    private int shotDelay = 0; // The delay between consecutive shots
    private int hitDelay = 0; // The delay between getting hit by asteroid

    /**
     * Constructs a Player object with the specified parameters.
     *
     * @param colorIndex The index representing the color of the triangle.
     * @param xPosition  The x-coordinate of the initial position.
     * @param yPosition  The y-coordinate of the initial position.
     * @param sideLength The length of each side of the triangle.
     */
    public Player(int colorIndex, int xPosition, int yPosition, int sideLength) {
        super(colorIndex, xPosition, yPosition);
        this.sideLength = sideLength;
        velocity = 0;
        acceleration = 0;
    }

    /**
     * Overrides the Move method in Shape.
     * Makes a circle around the player to act as a hit box.
     *
     * @return The radius of the bounding circle
     */
    @Override
    public double getBoundingCircleRadius() {
        // For simplicity, we'll return the side length divided by 2
        return sideLength / 2.0;
    }

    /**
     * Overrides the Move method in Shape.
     * Moves the player based on its direction and velocity.
     */
    @Override
    public void Move() {
        updateState();

        // Update the position based on the velocity and direction
        xPosition += velocity * Math.cos(direction);
        yPosition += velocity * Math.sin(direction);

    }

    /**
     * Updates the state of the player, applying acceleration and limiting velocity. Also includes a deceleration
     * mechanism.
     */
    private void updateState() {
        //Applying acceleration
        velocity += acceleration;

        //Limits velocity
        velocity = Math.min(maxVelocity, velocity);

        if(velocity > 0) {
            decelerate(0.05);
        }
    }

    /**
     * Getter for the shot delay
     *
     * @return The current shot delay.
     */
    public int getShotDelay() {
        return shotDelay;
    }

    /**
     * Getter for the hit delay
     *
     * @return hitDelay
     */
    public int getHitDelay() {
        return hitDelay;
    }

    /**
     * Sets the acceleration of the player.
     *
     * @param acceleration The acceleration to be set.
     */
    public void setAcceleration(double acceleration) {
        this.acceleration = 0;
    }

    /**
     * Increases the shot delay, allowing the player to shoot after a certain delay.
     *
     * @param delay The amount by which to increase the shot delay.
     */
    public void addShotDelay(int delay) {
        this.shotDelay += delay;
    }

    /**
     * Decreases the shot delay, simulating the decay of the shot delay clock.
     *
     * @param sec The amount by which to decrease the shot delay.
     */
    public void rmvShotDelay(int sec) {
        this.shotDelay -= sec;
    }

    /**
     * Adds a hit delay
     *
     * @param delay The amount by which to increase the hit delay.
     */
    public void addHitDelay(int delay) {
        this.hitDelay = delay;
    }

    /**
     * Removes a hit delay
     *
     * @param sec The amount by which to decrease the hit delay.
     */
    public void rmvHitDelay(int sec) {
        this.hitDelay -= sec;
    }

    /**
     * Decelerates the player's velocity by a specified amount.
     *
     * @param brake The amount by which to decelerate the player.
     */
    public void decelerate(double brake) {
        if(velocity > 0) {
            velocity -= brake;
        }
    }

    /**
     * Rotates the player by a specified angle around its center.
     *
     * @param angle The angle by which to rotate the triangle.
     */
    public void Rotate(double angle) {
        double centerX = xPosition + (double) sideLength / 2;
        double centerY = yPosition + (double) sideLength / 2;
        xPosition = centerX + (xPosition - centerX) * Math.cos(angle) - (yPosition - centerY) * Math.sin(angle);
        yPosition = centerY + (xPosition - centerX) * Math.sin(angle) + (yPosition - centerY) * Math.cos(angle);

        // Update the direction
        direction += angle;
    }

    /**
     * Accelerates the triangle in its current direction.
     *
     * @param acceleration The acceleration to be applied.
     */
    public void Accelerate(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Gets the current velocity of the player.
     *
     * @return The current velocity.
     */
    public double GetVelocity() { return velocity; }

    /**
     * Gets the current direction of the triangle.
     *
     * @return The current direction in radians.
     */
    public double GetDirection() { return direction; }

    /**
     * Gets the side length of the triangle.
     *
     * @return The side length.
     */
    public int GetSideLength() { return sideLength; }

    /**
     * Overrides the draw method in Shape.
     * Draws the player on the specified Graphics object.
     *
     * @param g The Graphics object on which to draw the player.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        // Create an AffineTransform to rotate and translate the player
        AffineTransform transform = new AffineTransform();
        transform.rotate(direction, xPosition + (double) sideLength / 2, yPosition + (double) sideLength / 2);
        transform.translate(xPosition, yPosition);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(transform);

        // Draw the player as a filled triangle
        int[] xPoints = {0, sideLength, 0};
        int[] yPoints = {0, sideLength / 2, sideLength};
        g.drawPolygon(xPoints, yPoints, 3);

        g2d.setTransform(new AffineTransform());

        // UNCOMMENT THIS TO SHOW BOUNDING CIRCLE
        // g2d.drawOval((int) (xPosition - getBoundingCircleRadius()), (int) (yPosition - getBoundingCircleRadius()),
        // (int) (2 * getBoundingCircleRadius()), (int) (2 * getBoundingCircleRadius()));
    }
}

