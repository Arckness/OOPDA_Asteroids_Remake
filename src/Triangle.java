/**
 * Triangle Class, the "ship" that the player controls
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v1.0, 11-25-2023)
 */

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Triangle extends Shape{

    private int sideLength;
    private double velocity;
    private double acceleration;
    private static final double maxVelocity = 7;
    private int shotDelay = 0;

    public Triangle(int colorIndex, int xPosition, int yPosition, int sideLength) {
        super(colorIndex, xPosition, yPosition);
        this.sideLength = sideLength;
        velocity = 0;
        acceleration = 0;
    }

    @Override
    public void Move() {
        updateState();

        xPosition += velocity * Math.cos(direction);
        yPosition += velocity * Math.sin(direction);
    }

    /**
     * Calculates acceleration and velocity
     */
    private void updateState() {
        //Applying acceleration
        velocity += acceleration;

        //Limits velocity
        velocity = Math.min(maxVelocity, velocity);

        if(velocity > 0) {
            decelerate(0.1);
        }
    }

    /**
     * Getter for delay
     * @return shotDelay
     */
    public int getShotDelay() {
        return shotDelay;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = 0;
    }

    /**
     * Allows the growth of the delay clock
     * @param delay
     */
    public void addShotDelay(int delay) {
        this.shotDelay += delay;
    }

    /**
     * Allows decay of the shot delay
     * @param sec
     */
    public void rmvShotDelay(int sec) {
        this.shotDelay -= sec;
    }

    public void decelerate(double brake) {
        if(velocity > 0) {
            velocity -= brake;
        }
    }

    public void Rotate(double angle) {
        double centerX = xPosition + sideLength / 2;
        double centerY = yPosition + sideLength / 2;
        xPosition = centerX + (xPosition - centerX) * Math.cos(angle) - (yPosition - centerY) * Math.sin(angle);
        yPosition = centerY + (xPosition - centerX) * Math.sin(angle) + (yPosition - centerY) * Math.cos(angle);

        // Update the direction
        direction += angle;
    }

    public void Accelerate(double acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        // Create an AffineTransform to rotate and translate the player
        AffineTransform transform = new AffineTransform();
        transform.rotate(direction, xPosition + sideLength / 2, yPosition + sideLength / 2);
        transform.translate(xPosition, yPosition);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(transform);

        // Draw the player as a filled triangle
        int[] xPoints = {0, sideLength, 0};
        int[] yPoints = {0, sideLength / 2, sideLength};
        g.drawPolygon(xPoints, yPoints, 3);

        g2d.setTransform(new AffineTransform());
    }

    public double GetVelocity() { return velocity; }

    public double GetDirection() { return direction; }

    public int GetSideLength() { return sideLength; }

}

