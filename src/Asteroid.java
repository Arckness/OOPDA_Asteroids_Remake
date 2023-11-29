import java.util.Random;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Asteroid extends Shape {
    private List<Point> asteroidShape;

    public Asteroid(int colorIndex, int xPosition, int yPosition) {
        super(colorIndex, xPosition, yPosition);
        generateRandomAsteroidShape();
        this.velocity = (Math.random() * 2 + 1) / 2;
    }

    /**
     * Generates a random shape for the asteroid with a random number of vertices and lengths
     */
    private void generateRandomAsteroidShape() {
        asteroidShape = new ArrayList<>();

        Random random = new Random();
        int numVertices = random.nextInt(6) + 5; // Random number of vertices between 5 and 10

        for (int i = 0; i < numVertices; i++) {
            int length = random.nextInt(15) + 10; // Random length between 10 and 25
            double angle = Math.toRadians(i * 360.0 / numVertices + Math.random() * 20 - 10);
            int x = (int) (length * Math.cos(angle));
            int y = (int) (length * Math.sin(angle));
            asteroidShape.add(new Point(x, y));
        }
    }

    @Override
    public void Move() {
        xPosition += velocity * Math.cos(direction);
        yPosition += velocity * Math.sin(direction);

        // Wrap around the screen
        if (xPosition < 0) xPosition = CanvasPanel.getCanvasWidth();
        if (xPosition > CanvasPanel.getCanvasWidth()) xPosition = 0;
        if (yPosition < 0) yPosition = CanvasPanel.getCanvasHeight();
        if (yPosition > CanvasPanel.getCanvasHeight()) yPosition = 0;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        int[] xPoints = new int[asteroidShape.size()];
        int[] yPoints = new int[asteroidShape.size()];

        // Translate the asteroid shape to its current position
        for (int i = 0; i < asteroidShape.size(); i++) {
            Point point = asteroidShape.get(i);
            xPoints[i] = (int) (xPosition + point.x);
            yPoints[i] = (int) (yPosition + point.y);
        }

        g2d.drawPolygon(xPoints, yPoints, asteroidShape.size());
    }
}







    /**
     * Method that will generate a random polygon for the asteroid
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
*/