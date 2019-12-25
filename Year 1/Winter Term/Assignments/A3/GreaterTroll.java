
/**
 * Write a description of class GreaterTroll here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GreaterTroll extends Troll
{
   public GreaterTroll()
   {
       this.symbol = "T";
   }
   public int doAttack()
   {
       return super.doAttack()*2;
    }
   public String toString()
   {
       return "GreaterTroll";
    }
}
