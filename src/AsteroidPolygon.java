import java.util.Random;
import java.awt.*;

public class AsteroidPolygon extends Shape{
    private Polygon polygon; // Declares polygon of type Polygon which will store the shape
    private int size; // 0 for small, 1 for medium, 2 for large

    public AsteroidPolygon(int colorIndex, int xPosition, int yPosition, int size) {
        super(colorIndex, xPosition, yPosition);
        this.size = size;
        generateRandomPolygon();
    }

    /**
     * Method that will generate a random polygon for the asteroid
     */
    private void generateRandomPolygon() {
        Random rand = new Random();
        int numPoints;

        switch (size) {
            case 0: // Small asteroid
                numPoints = 5;
                break;
            case 1: // Medium asteroid
                numPoints = 6;
                break;
            case 2: // Large asteroid
                numPoints = 7;
                break;
            default:
                throw new IllegalArgumentException("Invalid size parameter");
        }

        int[] xPoints = new int[numPoints]; // Array to store x-coordinates of points
        int[] yPoints = new int[numPoints]; // Array to store y-coordinates of points

        for (int i = 0; i < numPoints; i++) {
            xPoints[i] = (int) (xPosition + rand.nextInt(40) - 20); // Random x-coordinate within a range
            yPoints[i] = (int) (yPosition + rand.nextInt(40) - 20); // Random y-coordinate within a range
        }

        polygon = new Polygon(xPoints, yPoints, numPoints); // Creates the new asteroid
    }

    @Override
    public void Move(double xDelta, double yDelta) {
        xPosition += xDelta;
        yPosition += yDelta;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillPolygon(polygon);
    }

}
