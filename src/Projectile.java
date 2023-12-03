import java.awt.*;

public class Projectile extends Shape {
    private int width;
    private int height;

    public Projectile(int colorIndex, int xPosition, int yPosition, double direction, int width, int height) {
        super(colorIndex, xPosition, yPosition);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.velocity = 10; // Set initial velocity
    }

    /**
     * Move the projectile based on its direction and velocity.
     * Also, check if the projectile has gone out of the screen bounds.
     */
    @Override
    public void Move() {
        // Update position based on direction and velocity
        xPosition += velocity * Math.cos(direction);
        yPosition += velocity * Math.sin(direction);
    }

    /**
     * Check if the projectile is out of bounds
     */
    public boolean isOutOfBounds() {
        return xPosition < 0 || xPosition > CanvasPanel.getCanvasWidth() || yPosition < 0 || yPosition > CanvasPanel.getCanvasHeight();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.drawRect((int) xPosition, (int) yPosition, width, height);
    }
}
