import java.awt.*;

public class Rectangle extends Shape {
    private int width;
    private int height;

    /**
     * Constructor for the Rectangle class where you can set its size
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param colorIndex
     */
    public Rectangle(int x, int y, int width, int height, int colorIndex) {
        super(x, y, colorIndex);
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the width of the rectangle
     * @return the height of the rectangle
     */
    public int GetWidth() { return width; }

    /**
     * Gets the height of the rectangle
     * @return the height of the rectangle
     */
    public int GetHeight() { return height; }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)xPosition, (int)yPosition, width, height);
    }
}
