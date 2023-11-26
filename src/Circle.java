/**
 * 2D Circle Class
 * 
 *
 * @author (Prof R)
 * @version (v1.0 11-17-22)
 */

import java.awt.*;
import java.awt.geom.*;

public class Circle extends Shape
{
    private int diameter;// the diameter of the circle
    
    /**
     * Constructor for circles
     */
    public Circle(int colorIndex, int xPosition, int yPosition) {
        super(colorIndex, xPosition, yPosition);
        diameter = 40;
    }

    /**
     * Moves the ball its speed 
     */
    @Override
    public void Move(double xDelta, double yDelta) {
        //move our speed
        xPosition = xDelta;
        yPosition += yDelta;
    }
    
    /**
     * Gets the diamater of the ball
     * @return the diameter of the ball
     */
    public int GetDiameter()
    {
        return diameter;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval((int)xPosition, (int)yPosition, diameter, diameter);
    }
       
}
