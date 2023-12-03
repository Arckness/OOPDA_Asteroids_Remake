/**
 * Triangle Class, the "ship" that the player controls
 */

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Triangle extends Shape{

    private int sideLength;
    private double velocity;
    private double acceleration;
    private static final double maxVelocity = 7;

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

        // Inertia
        // velocity -= 0.25;

        // Apply friction to simulate inertia
        if (velocity > 0) { // if ship is moving right
            velocity -= 0.5; // gradually decrease the velocity by 0.1
        } else if (velocity < 0) { // if ship is moving left
            velocity += 0.5; // gradually decrease the velocity by 0.1
        }
    }

    public void decelerate() {
        velocity -= 0.25;
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

