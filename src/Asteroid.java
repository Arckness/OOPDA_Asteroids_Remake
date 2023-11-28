import java.util.Random;
import java.awt.*;

public class Asteroid extends Shape {
    private Polygon polygon; // Declares polygon of type Polygon which will store the shape
    private int size; // 0 for small, 1 for medium, 2 for large

    public Asteroid(int colorIndex, int xPosition, int yPosition, int size) {
        super(colorIndex, xPosition, yPosition);
        this.size = size;
        generateRandomPolygon();
    }

    /**
     * Method that will generate a random polygon for the asteroid
     */
    private void generateRandomPolygon() {
        Random rand = new Random();
        int numPoints = 8; // Set the number of points to 5 for all sizes

        int[] xPoints = new int[numPoints]; // Array to store x-coordinates of points
        int[] yPoints = new int[numPoints]; // Array to store y-coordinates of points

        boolean overlap;

        do {
            overlap = false;

            // Generate random x and y coordinates
            for (int i = 0; i < numPoints; i++) {
                xPoints[i] = (int) (xPosition + rand.nextInt(40) - 20); // Random x-coordinate within a range
                yPoints[i] = (int) (yPosition + rand.nextInt(40) - 20); // Random y-coordinate within a range
            }

            // Check for overlapping points within the same polygon
            for (int i = 0; i < numPoints - 1; i++) {
                for (int j = i + 1; j < numPoints; j++) {
                    if (xPoints[i] == xPoints[j] && yPoints[i] == yPoints[j]) {
                        overlap = true;
                        break;
                    }
                }
                if (overlap) {
                    break;
                }
            }
        } while (overlap);

        polygon = new Polygon(xPoints, yPoints, numPoints); // Creates the new asteroid
    }

    @Override
    public void Move() {
        // Move the asteroid upwards
        yPosition -= 1;

        // Checks if the asteroid is out of the canvas, reset the position if needed
        if (yPosition < -20) {
            yPosition = CanvasPanel.getCanvasHeight() + 20;
            generateRandomPolygon(); // Generate another asteroid
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color.BLACK);
        g.fillPolygon(polygon);

        g.setColor(color.WHITE);
        g.drawPolygon(polygon);
    }

}
