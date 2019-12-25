
/**
 * Abstract class Combatant - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Combatant extends Content
{
    protected int health;
    protected int minAtt;
    protected int maxAtt;
    
    public Combatant(String s,int h, int min, int max)
    {
        super(s);
        health = h;
        minAtt = min;
        maxAtt = max;
    }
    
    public int getHP()
    {
        return health;
    }
    
    public int doAttack()
    {
        return (int)(Math.random() * (maxAtt - minAtt) + minAtt);
    }
    
    public void changeHP()
    {
        health = health + doAttack();
    }
    
    public String toString()
    {
        return "Combatant";
    }
}
