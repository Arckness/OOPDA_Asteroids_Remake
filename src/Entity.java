/**
 * Class that represents all entities in the game
 *
 * @author (Nicholas Rua)
 * @version (V1 11/19/23)
 */

import java.awt.geom.Point2D;
import java.util.Vector;

public class Entity {

    private Vector position;
    private Vector velocity;

    public Entity(Vector position){
        this.position = new Vector(position);
        this.velocity = new Vector(0,0);
    }

    public void update(){
        position.add(this.velocity);
    }

}
