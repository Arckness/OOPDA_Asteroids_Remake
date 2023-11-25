import java.awt.*;

public class Triangle extends Shape{

    private int sideLength;

    public Triangle(int colorIndex, int xPosition, int yPosition, int sideLength) {
        super(colorIndex, xPosition, yPosition);
        this.sideLength = sideLength;
    }

    @Override
    public void Move(double xDelta, double yDelta) {
        xPosition += xDelta;
        yPosition += yDelta;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        int[] xPoints = {
                (int) xPosition, (int) (xPosition + sideLength), (int) (xPosition + sideLength / 2)
        };
        int[] yPoints = {
                (int) (yPosition + sideLength), (int) (yPosition + sideLength), (int) (xPosition)
        };

        g.fillPolygon(xPoints, yPoints, 3);
    }
}
