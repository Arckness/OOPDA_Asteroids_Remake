/**
 * Asteroids Game for project 6 of OOPDA
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v1.0, 11-25-2023)
 */

/** TODO
 *  - Inertia ( keep ship moving after key release )
 *  - Make sure ship does not go out of bounds
 *  - Fix the way Projectiles shoot
 *  - Collisions
 *  - Animated sprite for ship ( fire out back )
 *  - Shooting sounds ( *pew* PEW )
 *  - Scoreboard
 */

public class Project6_Game_Template_driver
{
   public static void main(String[] arg)
   {
       Game();
   }
   
   public static void Game()
   {
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
