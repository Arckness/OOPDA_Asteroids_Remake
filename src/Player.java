/**
 * The user controlled class
 *
 * @author (Nicholas Rua)
 * @version (V1, 11-19-23)
 */

import java.util.Vector;

public class Player extends Entity{

    private double rotation;
    private double shotDelay;
    private boolean isVisible;
    private int score;
    //private Triangle sprite = new Triangle();

    public Player(Vector position){
        super(position);
        int lives = 3;
        double rotation = 0.0;
        double shotDelay = 0.0;
        boolean isVisible = true;
    }

    public void shoot(){
        Vector projectilePosition = new Vector(getPosition());
        Projectile projectile = new Projectile(projectilePosition);
        // Add the projectile to the entity list, possibly make projectiles
        // be rectangles since we need more shape objects
    }


}
