/**
 * The rocks that float in the game, shoot and they go boom
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v1.0, 11-25-2023)
 */

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
