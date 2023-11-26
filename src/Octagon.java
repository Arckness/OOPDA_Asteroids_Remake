import java.awt.*;

public class Octagon extends Shape {

    private int sideLength; // Additional property specific to Octagon

    public Octagon(int colorIndex, int xPosition, int yPosition, int sideLength) {
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

        // Draw the octagon
        int[] xPoints = new int[8];
        int[] yPoints = new int[8];

        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(i * 45);
            xPoints[i] = (int) (xPosition + sideLength * Math.cos(angle));
            yPoints[i] = (int) (yPosition + sideLength * Math.sin(angle));
        }

        g.fillPolygon(xPoints, yPoints, 8);
    }

}
