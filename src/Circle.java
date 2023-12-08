import java.awt.*;
import java.awt.geom.*;
/**
 * The Circle class represents a star shape in the Asteroids game. It extends the Shape class and defines the behavior
 * and appearance of circular stars.
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v1.0 11-17-22)
 */
public class Circle extends Shape {
    private int diameter;// The diameter of the circle

    /**
     * Constructs a Circle object with a specified color index, x and y positions.
     *
     * @param colorIndex The index representing the color of the circle.
     * @param xPosition  The x-coordinate position of the circle.
     * @param yPosition  The y-coordinate position of the circle.
     */
    public Circle(int colorIndex, int xPosition, int yPosition) {
        super(colorIndex, xPosition, yPosition);
        diameter = 40;
    }

    /**
     * Constructs a Circle object with a specified color index, x and y positions, and diameter.
     *
     * @param colorIndex The index representing the color of the circle.
     * @param xPosition  The x-coordinate position of the circle.
     * @param yPosition  The y-coordinate position of the circle.
     * @param diameter   The diameter of the circle.
     */
    public Circle(int colorIndex, int xPosition, int yPosition, int diameter) {
        super(colorIndex, xPosition, yPosition);
        this.diameter = diameter;
    }

    /**
     * Overrides the Move method in Shape.
     * Initializes the x and y position of the circles to 0 since the stars are stagnant.
     */
    public void Move() {
        // Stars do not move and its movement is set to 0
        xPosition = 0;
        yPosition = 0;
    }
    
    /**
     * Gets the diameter of the circle.
     *
     * @return The diameter of the circle
     */
    public int GetDiameter()
    {
        return diameter;
    }

    /**
     * Overrides the draw method in Shape.
     * Draws the circle on the specified Graphics object.
     *
     * @param g The Graphics object on which to draw the circle.
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)xPosition, (int)yPosition, diameter, diameter);
    }
}
