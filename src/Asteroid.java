import java.util.Vector;

/**
 * The class for each asteroid that will appear in the game
 *
 * @author (Nicholas Rua)
 * @version (V1, 11/19/2023)
 */
public class Asteroid extends Entity{

    private int size;

    public Asteroid(Vector position, int size) {
        super(position);
        this.size = size;
    }

    public int getSmall() {
        int small = 25;
        return small;
    }

    public int getMed() {
        int med = 50;
        return med;
    }

    public int getLarge() {
        int large = 100;
        return large;
    }
}
