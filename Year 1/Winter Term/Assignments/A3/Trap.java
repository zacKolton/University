
/**
 * Write a description of class Trap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Trap extends Iteam
{
    public Trap()
    {
        super("^");
    }
    
    public int getEffect()
    {
        System.out.println("You set off a trap");
        return -5;
    }
}
