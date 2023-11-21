import java.awt.*;

public class Triangle{
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

    private double xPosition;
    private double yPosition;
    private Color  color;          // the color of the sphere
    private int    colorIndex;     // the index of the color of the sphere in the COLORS array

    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param colorIndex
     */
    public Triangle(int x, int y, int colorIndex)
    {
        this.xPosition = x;
        this.yPosition = y;

        this.colorIndex = colorIndex;
        color = COLORS[this.colorIndex];
    }

    /**
     * Moves the triangle its speed
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
     * Gets the current color of the triangle
     * @return the color of the triangle as a Color object
     */
    public Color GetColor()
    {
        return color;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.drawPolygon(new int[] {(int) xPosition, (int) (xPosition + 10), (int) (xPosition + 20)},
                new int[] {(int) yPosition, (int) (yPosition + 20), (int) (yPosition)}, 3);
    }

}
