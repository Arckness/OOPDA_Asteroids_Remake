import javax.swing.*;
import java.awt.*;

/**
 * The CanvasFrame class represents the main frame for the Asteroids game and provides a window to display the game
 * canvas.
 * This is a cleaned up version of the original CanvasFrame made by Professor Rabbitz.
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v2.0 11-17-22)
 */
public class CanvasFrame {
    private JFrame frame; // The actual frame(window) we'll be showing
    public static CanvasPanel canvas; // The canvas we'll be drawing

    /**
     * Creates a new CanvasFrame object with the title "Asteroids!". Initializes the frame, sets its default close
     * operation, and adds the game canvas to the frame.
     */
    public CanvasFrame() {
        frame = new JFrame("Asteroids!"); // Make the JFrame, and set the window bar title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new CanvasPanel();  // CanvasPanel extends a JPanel
        setPreferredSize();
        frame.getContentPane().add(canvas); // Put the canvas (JPanel) in the frame

        frame.pack(); // Make everything the preferred size
        frame.setVisible(true); // Show the frame
    }

    public static void setPreferredSize() {
        canvas.setPreferredSize(new Dimension(canvas.getCanvasWidth(), canvas.getCanvasHeight()));
        canvas.setBackground(Color.RED);
    }
}
