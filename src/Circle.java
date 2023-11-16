/**
 * 2D Circle Class
 * 
 *
 * @author (Prof R)
 * @version (v1.0 11-17-22)
 */

import java.awt.*;
import java.awt.geom.*;
public class Circle
{   
    public static final Color[] COLORS = {
            //         R     G    B
            new Color(255,   0,   0),  // Red     0
            new Color(  0, 255,   0),  // Green   1
            new Color(  0,   0, 255),  // Blue    2
            new Color(  0,   0,   0),  // Black   3
            new Color(128, 128, 128),  // Grey    4
            new Color(255, 255, 255),  // White   5
            new Color(255, 255,   0),  // Yellow  6
            new Color(  0, 255, 255),  // Cyan    7
            new Color(255,   0, 255),  // Magenta 8 
            new Color(165,  42,  42),  // Brown   9
            new Color(255,  38,  38),
            new Color(255, 168,  38),
            new Color(212, 255,  38),
            new Color( 82, 255,  38),
            new Color( 38, 255, 125),
            new Color( 38, 255, 255),
            new Color( 38, 125, 255),
            new Color( 82,  38, 255),
            new Color(212,  38, 255),
            new Color(255,  38, 168),
    }; 
    
    private int    diameter;       // the diameter of the circle
    private double xPosition;   
    private double yPosition;   
    private Color  color;          // the color of the sphere
    private int    colorIndex;     // the index of the color of the sphere in the COLORS array
    
    /**
     * Constructor for shapes
     */
    public Circle()
    {
        diameter = 40;
        xPosition = 20;
        yPosition = 20;

        colorIndex = 0;
        color = COLORS[0];
    }
    
    public Circle(int colorIndex)
    {
        diameter = 40;
        xPosition = 20;
        yPosition = 20;

        this.colorIndex = colorIndex;
        color = COLORS[this.colorIndex];
    }

    /**
     * Moves the ball its speed 
     */
    public void Move(double xDelta, double yDelta)
    {
        //move our speed
        xPosition += xDelta;
        yPosition += yDelta;
    }
    
    /**
     * Gets the current x position of the ball
     * @return the x position of the ball
     */
    public int GetX()
    {
        return (int)xPosition;
    }

    /**
     * Gets the current y position of the ball
     * @return the y position of the ball
     */
    public int GetY()
    {
        return (int)yPosition;
    }
    
    /**
     * Gets the diamater of the ball
     * @return the diameter of the ball
     */
    public int GetDiameter()
    {
        return diameter;
    }
    
    /**
     * Gets the current color of the ball
     * @return the color of the ball as a Color object
     */
    public Color GetColor()
    {
        return color;
    }
    
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval((int)xPosition, (int)yPosition, diameter, diameter);
    }
       
}
