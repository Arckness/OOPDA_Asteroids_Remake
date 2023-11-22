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
    private int diameter;       // the diameter of the circle

    /**
     * Constructor for the circle class where you can set its size
     *
     * @param x
     * @param y
     * @param diameter
     * @param colorIndex
     */
    public Circle(int x, int y, int diameter, int colorIndex)
    {
        super(x, y, colorIndex);
        this.diameter = diameter;
    }

    /**
     * Gets the diamater of the ball
     * @return the diameter of the ball
     */
    public int GetDiameter()
    {
        return diameter;
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval((int)xPosition, (int)yPosition, diameter, diameter);
    }
       
}
