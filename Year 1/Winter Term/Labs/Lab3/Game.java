
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{
    private HockeyTeam home;
    private HockeyTeam away;
    private int homeScore;
    private int awayScore;

    /**
     * Constructor for objects of class Game
     */
    public Game(HockeyTeam h, HockeyTeam a)
    {
        home = h;
        away = a;
        homeScore = 0;
        awayScore = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String toString()
    {
        return away.getName()+"("+ awayScore+")"+" at "+ home.getName()+"("+homeScore+")";
    }

    public void goal(HockeyTeam x)
    {
        if(x == home){
            homeScore++;
        }
        else if(x == away)
        {
            awayScore++;
        }
        else
        {
            System.out.println("That team isn't playing in this game!");
        }
    }
}
