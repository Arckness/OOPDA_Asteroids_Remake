import java.awt.*;
import java.util.Random;

public class Octagon extends Shape {

    private int sideLength; // Additional property specific to Octagon
    private Random random = new Random();

    public Octagon(int colorIndex, int xPosition, int yPosition, int sideLength) {
        super(colorIndex, xPosition, yPosition);
        this.sideLength = sideLength;
        this.velocity = random.nextInt(1,5);
    }

    @Override
    public void Move() {
        xPosition += velocity * Math.cos(direction);
        yPosition += velocity * Math.sin(direction);
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

        g.drawPolygon(xPoints, yPoints, 8);
    }

}
