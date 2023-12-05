import java.awt.*;
/**
 * The abstract Shape class serves as a parent class for creating shapes in the Asteroids game. It provides common
 * properties such as color, position, direction, and velocity.
 * Subclasses must implement the abstract methods Move and draw to define specific behavior for each individual shape.
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v1.0, 11-25-2023)
 */
public abstract class Shape {
    Color  color; // The color of the shape
    private int colorIndex; //The index of the color of the shape in the COLORS array
    double xPosition;
    double yPosition;
    double direction;
    double velocity;

    /**
     * Array of predefines colors for shapes. Each color is represented as a Color object.
     */
    public static final Color[] COLORS = {
            //         R     G    B
            new Color(255,   0,   0), // Red     0
            new Color(  0, 255,   0), // Green   1
            new Color(  0,   0, 255), // Blue    2
            new Color(  0,   0,   0), // Black   3
            new Color(128, 128, 128), // Grey    4
            new Color(255, 255, 255), // White   5
            new Color(255, 255,   0), // Yellow  6
            new Color(  0, 255, 255), // Cyan    7
            new Color(255,   0, 255), // Magenta 8
            new Color(165,  42,  42), // Brown   9
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

    /**
     * Creates a new Shape object with the specified color index, x position, and y position. It initializes color and
     * direction.
     *
     * @param colorIndex The index of the color for the shape
     * @param xPosition The initial x position of the shape
     * @param yPosition The initial y posiiton of the shape
     */
    public Shape(int colorIndex, double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.colorIndex = colorIndex;
        color = COLORS[this.colorIndex];
        this.direction = -Math.PI / 2;
    }

    /**
     * Gets the current x position of the shape.
     *
     * @return the x position of the shape
     */
    public int GetX()
    {
        return (int)xPosition;
    }

    /**
     * Gets the current y position of the shape.
     *
     * @return the y position of the shape
     */
    public int GetY()
    {
        return (int)yPosition;
    }

    /**
     * Sets the x position of the shape.
     *
     * @param x The new x position to set.
     */
    public void SetX(double x) {
        this.xPosition = x;
    }

    /**
     * Sets the y position of the shape.
     *
     * @param y The new y position to set.
     */
    public void SetY(double y) {
        this.yPosition = y;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Moves the shape according to its specific behavior.
     */
    public abstract void Move();

    /**
     * Abstract method to be implemented by subclasses.
     * Draws the shape on the graphics context.
     *
     * @param g The Graphics object on which the shape is drawn.
     */
    public abstract void draw(Graphics g);

}
