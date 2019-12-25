
/**
 * Write a description of class HockeyTeam here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HockeyTeam{

    // instance variables - replace the example below with your own
    private String name;
    private  int wins;
    private  int losses;
    private  int overLosses;

    /**
     * Constructor for objects of class HockeyTeam
     */
    public HockeyTeam(String s,int w,int l,int ol)
    {
        // initialise instance variables
        name = s;
        wins = w;
        losses = l;
        overLosses = ol;
    }

    public void won()
    {
        wins++;
    }

    public void lost(){
        losses++;
    }

    public void lostOvertime(){
        overLosses++;
    }

    public int getWin(){
        return wins;
    }

    public int getLoss(){
        return losses;
    }

    public int getOverLoss(){
        return overLosses;
    }

    public int points(){
        return (wins*2) + overLosses;
    }

    public String toString(){
        return "("+name+","+ wins+","+ losses+","+ overLosses+"="+ points()+")";
    }
}

