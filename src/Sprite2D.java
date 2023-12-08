import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
/**
 * The Sprite2D class represents a 2D sprite with multiple image frames for animation. It extends the Shape2D class and
 * includes methods for drawing the sprite on a graphics context.
 *
 * @author (Nicholas Rua, Norah Micciulla)
 *  * @version (v1.0, 12-5-2023)
 */
public class Sprite2D extends Shape2D
{
    private BufferedImage[] imageFrames;
    private int frame;

    /**
     * Creates a new Sprite2D object with the specified position and image frames for animation.
     *
     * @param xPos The x-coordinate position of the sprite
     * @param yPos The y-coordinate position of the sprite
     * @param imageFrames An array of BufferedImage objects representing the frames of the sprite animation
     */
    public Sprite2D(int xPos, int yPos, BufferedImage[] imageFrames)
    {
        super(0, xPos, yPos);
        this.imageFrames = new BufferedImage[imageFrames.length];
        for (int i = 0; i < imageFrames.length; i++) {
            this.imageFrames[i] = imageFrames[i];
        }
        frame = 0;
    }

    /**
     * Draws the current frame of the sprite on the specified graphics context.
     *
     * @param g The Graphics object on which to draw the sprite
     */
    public void Draw(Graphics g) {
        g.drawImage(imageFrames[frame], GetX(), GetY(), null);
        frame++;
        if (frame == imageFrames.length)
        {
            frame = 0;
        }
    }
}
