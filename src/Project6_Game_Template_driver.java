/**
 * This class serves as the driver for the Project6_Game_Template.
 * It contains the main method to start the game loop.
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v2.0 11-17-22)
 */
public class Project6_Game_Template_driver {

    /**
     * The main entry point for the program.
     */
   public static void main(String[] arg)
   {
       Game();
   }

   /**
    * Initializes and starts the game loop. It creates a window with a canvas and continuously simulates and updates the canvas.
    */
   public static void Game() {
       CanvasFrame canvasFrame = new CanvasFrame();  // Construct the window, which will construct a frame (window), and a panel(canvas)

       while(true) {
           canvasFrame.canvas.Simulate();
           canvasFrame.canvas.repaint();

           try {
               Thread.sleep(30);
           } catch(InterruptedException e) {
               e.printStackTrace();
           }
       }
   }
}
