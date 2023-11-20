import java.util.ArrayList;
import java.util.List;

/**
 * Project6 template driver class for frame(window), and panel(canvas)
 * 
 * template code for building your final project game
 *
 * @author (Nicholas Rua, Norah Micciulla)
 * @version (v1.0, 11-16-2023)
 */
public class Project6_Game_Template_driver
{

    List<Entity> entityList = new ArrayList<>();

   public static void main(String[] arg)
   {
       Game();
   }
   
   public static void Game()
   {
       new CanvasFrame();  // construct the window, which will construct a frame (window), and a panel(canvas)
       gameState state = gameState.titleScreen;

   }
}
