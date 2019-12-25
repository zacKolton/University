
/**
 * Write a description of class Amulet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Amulet extends Iteam
{
    public Amulet()
    {
        super("Y");
    }
    
    public int getEffect()
    {
        System.out.println("You picked up the Amulet of Yendor!");
        return 0;
    }
}
