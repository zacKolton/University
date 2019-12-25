
/**
 * Write a description of class HealthPotion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HealthPotion extends Iteam
{
    public HealthPotion()
    {
        super("h");
    }
    
    public int getEffect()
    {
        System.out.println("You picked up a health potion");
        return 5;
    }
}



