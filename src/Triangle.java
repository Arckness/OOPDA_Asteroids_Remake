import java.awt.*;

public class Triangle extends Shape{

    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param colorIndex
     */
    public Triangle(int x, int y, int colorIndex)
    {
        super(x, y, colorIndex);
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.drawPolygon(new int[] {(int) xPosition, (int) (xPosition + 10), (int) (xPosition + 20)},
                new int[] {(int) yPosition, (int) (yPosition + 20), (int) (yPosition)}, 3);
    }

}
