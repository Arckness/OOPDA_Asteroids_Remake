/** TODO
 *  - Make it so Asteroids don't overlap
 *  - Animated sprite
 *  - Shooting sounds ( *pew* PEW )
 */
public class Project6_Game_Template_driver {
   public static void main(String[] arg)
   {
       Game();
   }
   
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
