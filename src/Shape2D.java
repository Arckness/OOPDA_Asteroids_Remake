
/**
 * 2D Shape Abstract Class
 * 
 * Polymorhpic Abstract Method Draw
 * 
 * Animate is not abstract but can be overriden
 *
 * @author (Prof R)
 * @version (v1.0 11-17-22)
 */
import java.awt.*;
import java.awt.geom.*;
public abstract class Shape2D
{
    // RGB color table
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
    // Transformation attributes/data members for scale(deform), rotate(spin), translate(move)
    private int     xPos;               // xPos
    private int     yPos;               // yPos
    private double  sX;                 // Scale X
    private double  sY;                 // Scale Y
    private double  rotAngleZ;          // Rotation about the Z axis

    private int     xVel;               // xVel
    private int     yVel;               // yVel
    private Color   fillColor;          // the color of the sphere
    private int     fillColorIndex;     // the index of the color of the sphere in the COLORS array
    private Color   outlineColor;       // outline color
    private int     outlineColorIndex;  // outline color index
    private boolean fill;               // fill flag on/true, off/false
    private boolean outline;            // outline flag on/treu, off/false

    /**
     * Default Constructor 
     */
    public Shape2D()
    {
        this.xPos      = 20;
        this.yPos      = 20;
        this.sX        = 1.0;
        this.sY        = 1.0;
        this.rotAngleZ = 0.0;
        this.xVel      = 0;
        this.yVel      = 0;

        this.fillColorIndex = 2;             // Blue
        this.fillColor      = COLORS[2];     // Blue

        this.outlineColorIndex = 3;          // Black
        this.outlineColor      = COLORS[3];  // Black

        this.fill    = true;
        this.outline = false;
    }

    /**
     * Parametric Constructor
     */

    public Shape2D(int fillColorIndex, int xPosition, int yPosition)
    {
        this.xPos      = xPosition;
        this.yPos      = yPosition;
        this.sX        = 1.0;
        this.sY        = 1.0;
        this.rotAngleZ = 0.0;
        this.xVel      = 0;
        this.yVel      = 0;

        this.fillColorIndex = fillColorIndex;
        this.fillColor = COLORS[fillColorIndex];
        
        this.outlineColorIndex = 3;          // Black
        this.outlineColor      = COLORS[3];  // Black

        this.fill    = true;
        this.outline = true
        ;
    }

    /**
     * Moves the shape by an amount (xDelta, yDelta)
     */
    /**
     * Move - translates the shape by an amount (xDelta, yDelta)
     *
     * @param  xDelta - amount to translate along the x axis
     *         yDelta - amount to translate along the y axis
     * @return None
     */
    public void Move(int xDelta, int yDelta)
    {
        //move the shape
        this.xPos += xDelta;
        this.yPos += yDelta;
    }


    /**
     * Returns the render outline state of true or false
     * @return the render outline state
     */
    public boolean GetOutline()
    {
        return outline;  
    }

    /**
     * Set the shape state such that a outline is drawn
     */
    public void SetOutline(boolean setting)
    {
        this.outline = setting;
    }

    /**
     * Returns the render fill state of true or false
     * @return the render fill state
     */
    public boolean GetFill()
    {
        return fill;  
    }

    /**
     * Set the shape state such that the shape is rendered filled
     */
    public void SetFill(boolean setting)
    {
        this.fill = setting;
    }

    /**
     * Gets the current x position of the shape
     * @return the x position of the shape
     */
    public int GetX()
    {
        return this.xPos;
    }

    /**
     * Gets the current y position of the shape
     * @return the y position of the shape
     */
    public int GetY()
    {
        return this.yPos;
    }

    public double GetScaleX()
    {
        return this.sX;
    }

    public double GetScaleY()
    {
        return this.sY;
    }

    public void SetScale(double sX, double sY)
    {
        this.sX = sX;
        this.sY = sY;
    }

    void SetRotateZ(double degs)
    {
        this.rotAngleZ = degs;
    }

    public double GetZRotate()
    {
        return this.rotAngleZ; 
    }

    /**
     * Moves the shape to the location (x, y)
     */
    public void SetPos(int x, int y)
    {
        this.xPos = x;
        this.yPos = y;
    }
    
    public void IcrSpeedX()
    {
        this.xVel++;
    }
    
    public void NegateSpeedX()
    {
        this.xVel = -this.xVel;
    }
    
    public void NegateSpeedY()
    {
        this.yVel = -this.yVel;
    }

    public void NegateSpeed()
    {
       this.xVel = -this.xVel;   
       this.yVel = -this.yVel;
    }
    /**
     * Sets the speed of the shape for both the xV, and yV
     */
    public void SetSpeed(int xV, int yV)
    {
        this.xVel = xV;
        this.yVel = yV;
    }

    /**
     * Gets the speed of the shape for xVel
     */
    public int GetXSpeed()
    {
        return this.xVel;
    }

    /**
     * Gets the speed of the shape for xVel
     */
    public int GetYSpeed()
    {
        return this.yVel;
    }

    /**
     * Move the shape along its velocity vector's trajectory
     */

    public void Animate()
    {
        //move our speed
        xPos += xVel;
        yPos += yVel;
    }

    /**
     * Gets the current fill color index of the shape
     * @return the color of the ball as a Color object
     */
    public int GetFillColorIndex()
    {
        return this.fillColorIndex;
    }

    /**
     * Gets the current fill color of the shape
     * @return the color of the ball as a Color object
     */
    public Color GetFillColor()
    {
        return this.fillColor;
    }

    /**
     * Sets the current fill color of the shape
     */
    public void SetFillColor(int fillColorIndex)
    {
        this.fillColorIndex = fillColorIndex;
        this.fillColor = COLORS[fillColorIndex];
    }

    /**
     * Gets the current outline color index of the shape
     * @return the color of the shape as a Color object
     */
    public int GetOutlineColorIndex()
    {
        return this.outlineColorIndex;
    }

    /**
     * Gets the current outline color of the shape
     * @return the color of the shape as a Color object
     */
    public Color GetOutlineColor()
    {
        return this.outlineColor;
    }

    /**
     * Sets the current outline color of the shape
     */
    public void SetOutlineColor(int outlineColorIndex)
    {
        this.outlineColorIndex = outlineColorIndex;
        this.outlineColor = COLORS[outlineColorIndex];
    }

    /**
     * Draw the shape to the screen at its current position
     */
    public abstract void Draw(Graphics g);

}
