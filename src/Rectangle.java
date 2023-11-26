import java.awt.*;

public class Rectangle extends Shape{
    private int width;
    private int height;

    public Rectangle(int colorIndex, int xPosition, int yPosition, int width, int height) {
        super(colorIndex, xPosition, yPosition);
        this.width = width;
        this.height = height;
    }

    @Override
    public void Move(double xDelta, double yDelta) {
        xPosition += xDelta;
        yPosition += yDelta;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect((int) xPosition, (int) yPosition, width, height);
    }
}
