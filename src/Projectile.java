import java.util.Vector;
/**
 * The user controlled class
 *
 * @authors (Nicholas Rua, Norah Micciulla)
 * @version (V1, 11-19-23)
 */
public class Projectile extends Entity{

    private int speed = 5;

    public Projectile(Vector position){
        super(position);
        setVelocity(new Vector(speed, 0));
    }
}