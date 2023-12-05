import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * The CanvasFrame class represents the main frame for the Asteroids game and provides a window to display the game
 * canvas.
 * This is a cleaned up version of the original CanvasFrame made by Professor Rabbitz.
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v2.0 11-17-22)
 */
public class CanvasFrame 
{
    private JFrame frame; // The actual frame(window) we'll be showing
    CanvasPanel canvas; // The canvas we'll be drawing
    
    /**
     * Creates a new CanvasFrame object with the title "Asteroids!". Initializes the frame, sets its default close
     * operation, and adds the game canvas to the frame.
     */
    public CanvasFrame() {
        frame = new JFrame("Asteroids!"); // Make the JFrame, and set the window bar title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new CanvasPanel();  // CanvasPanel extends a JPanel
        initializeCanvasSize(); // Width = 850, Height = 450
        frame.getContentPane().add(canvas); // Put the canvas (JPanel) in the frame

        frame.pack(); // Make everything the preferred size
        frame.setVisible(true); // Show the frame
    }

    /**
     * Initializes the size of the game canvas based on the dimensions of the canvas panel.
     */
    private void initializeCanvasSize() {
        canvas.setPreferredSize(new Dimension(2 * canvas.getCanvasXBorder() + canvas.getCanvasWidth(),
                2 * canvas.getCanvasYBorder() + canvas.getCanvasHeight()));
    }
}
